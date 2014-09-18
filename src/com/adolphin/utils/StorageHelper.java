/*******************************************************************************
 *
 *    Copyright (c) Dolphin Browser
 *
 * *    Dolphin Browser HD
 *
 *    StorageHelper
 *
 *
 *    @author: derron
 *    @since:  Jun 11, 2010
 *    @version: 1.0
 *
 ******************************************************************************/

package com.adolphin.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * StorageHelper of TunnyBrowser.Can work on Incredible(Only have internal
 * storage)
 *
 * @author derron
 */
public class StorageHelper {
    private static final String LOG_TAG = "StorageHelper";
    private static final boolean HAS_PHONE_STARAGE;
    private static Method getPhoneStorageStateMethod;
    private static Method getPhoneStorageDirectoryMethod;
    static {
        boolean hasPhoneStorage = false;
        try {
            Class<Environment> cls = Environment.class;
            getPhoneStorageDirectoryMethod = cls.getDeclaredMethod("getPhoneStorageDirectory");
            getPhoneStorageDirectoryMethod.setAccessible(true);

            getPhoneStorageStateMethod = cls.getDeclaredMethod("getPhoneStorageState");
            getPhoneStorageStateMethod.setAccessible(true);
            hasPhoneStorage = true;
        } catch (Exception e) {
            //Not HTC Sense device, do nothing
        }
        HAS_PHONE_STARAGE = hasPhoneStorage;
    }

    /**
     * Gets the Android external storage directory.
     */

    public static File getExternalStorageDirectory() {
        File dir = null;
        if (HAS_PHONE_STARAGE) {
            if (Environment.MEDIA_REMOVED.equals(getExternalStorageStateInternal())) {
                dir = getPhoneStorageDirectory();
            } else {
                dir = Environment.getExternalStorageDirectory();
            }
        } else {
            dir = getAvaliedStorageDirectory();
        }
        return dir;
    }

    /**
     * Gets the current state of the external storage device.
     */

    public static String getExternalStorageState() {
        String state = Environment.MEDIA_REMOVED;
        if (HAS_PHONE_STARAGE) {
            if (Environment.MEDIA_REMOVED.equals(getExternalStorageStateInternal())) {
                state = getPhoneStorageStateInternal();
            } else {
                state = Environment.getExternalStorageState();
            }
        } else {
            state = getStorageState();
        }
        return state;
    }

    private static String getExternalStorageStateInternal() {
        return Environment.getExternalStorageState();
    }

    private static String getPhoneStorageStateInternal() {
        try {
            if (HAS_PHONE_STARAGE) {
                return (String) getPhoneStorageStateMethod.invoke(null);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
        return Environment.MEDIA_REMOVED;
    }

    private static File getPhoneStorageDirectory() {
        try {
            if (HAS_PHONE_STARAGE) {
                return (File) getPhoneStorageDirectoryMethod.invoke(null);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
        return null;
    }


    public static long getFileSize(File f) {
        long size = 0;
        File list[] = f.listFiles();
        if (list == null) {
            return 0;
        }
        for (int i = 0; i < list.length; i++) {
            File child = list[i];
            if (child.isDirectory()) {
                size += getFileSize(child);
            } else {
                size += child.length();
            }
        }
        return size;
    }


    public static boolean isDirSizeLargerThan(File f, long size) {
        long total = 0;
        File list[] = f.listFiles();
        if (null != list) {
            for (int i = 0; i < list.length; i++) {
                File child = list[i];
                total += child.length();
                if (total > size) {
                    break;
                }
            }
        }
        return total > size;
    }

    @SuppressWarnings("deprecation")
    static long getExternalStoreAvaliedSize(String path) {
        StatFs state = new StatFs(path);
        long cont = state.getAvailableBlocks();
        long blockSize = state.getBlockSize();
        return cont * blockSize;
    }

    private static final long MIN_AVALIED_SIZE = 1024 * 1024;

    private static boolean isExternalStorageStateAvalied() {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageStateInternal());
    }

    private static String getStorageState() {
        String status = getExternalStorageStateInternal();
        if (isExternalStorageStateAvalied()) {
            return status;
        }
        File[] files = getAllStoreRootPathFiles();
        if (files != null && files.length > 0) {
            return Environment.MEDIA_MOUNTED;
        }
        return status;
    }

    private static File getAvaliedStorageDirectory() {

        File defaultDir = Environment.getExternalStorageDirectory();
        if (isExternalStorageStateAvalied()) {
            return defaultDir;
        }

        File[] files = getAllStoreRootPathFiles();
        if (files != null) {
            for (File file : files) {
                long size = getExternalStoreAvaliedSize(file.getPath());
                if (size > MIN_AVALIED_SIZE) {
                    return file;
                }
            }
        }
        if (files != null && files.length > 0) {
            return files[0];
        }

        return defaultDir;
    }

    public static File[] getAllStoreRootPathFiles() {
        String[] fileStrings = getStorageDirectories();
        File[] files = null;
        if (fileStrings != null) {
            int length = fileStrings.length;
            files = new File[length];
            for (int i = 0; i < length; i++) {
                files[i] = new File(fileStrings[i]);
            }
        }
        return files;
    }

    /**
     * Similar to android.os.Environment.getExternalStorageDirectory(), except that
     * here, we return all possible storage directories. The Environment class only
     * returns one storage directory. If you have an extended SD card, it does not
     * return the directory path. Here we are trying to return all of them.
     *
     * @return
     */
    private static String[] getStorageDirectories() {
        String[] dirs = null;
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(newUtf8OrDefaultInputStreamReader(
                    new FileInputStream("/proc/mounts")));
            HashSet<String> list = new HashSet<String>();
            String defaultSdcard = Environment.getExternalStorageDirectory().getPath();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.contains("vfat") || line.contains(defaultSdcard)) {
                    StringTokenizer tokens = new StringTokenizer(line, " ");
                    String s = tokens.nextToken();
                    s = tokens.nextToken(); // Take the second token, i.e. mount point

                    if (s.equals(defaultSdcard)) {
                        list.add(s);
                    } else if (line.contains("/dev/block/vold")) {
                        if (!line.contains("/mnt/secure")
                                && !line.contains("/mnt/asec")
                                && !line.contains("/mnt/obb")
                                && !line.contains("/dev/mapper")
                                && !line.contains("tmpfs")) {
                            list.add(s);
                        }
                    }
                }
            }

            dirs = new String[list.size()];
            final Iterator<String> iterator = list.iterator();
            if (iterator != null) {
                int i = 0;
                while (iterator.hasNext()) {
                    dirs[i] = iterator.next();
                    i ++;
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(LOG_TAG, String.valueOf(e));
        } finally {
            closeStream(bufReader);
        }

        return dirs;
    }

    public static boolean isSDCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState());
    }

    @SuppressWarnings("deprecation")
    public static long getAvailedStorageSize() {
        File storageDir = StorageHelper.getExternalStorageDirectory();
        if (storageDir != null) {
            StatFs statFs = new StatFs(storageDir.getAbsolutePath());
            long blockSize = statFs.getBlockSize();
            long availedCount = statFs.getAvailableBlocks();
            return blockSize * availedCount;
        }
        return 0;
    }

    public static long getTotalStorageSize() {
        File storageDir = StorageHelper.getExternalStorageDirectory();
        if (storageDir != null) {
            StatFs statFs = new StatFs(storageDir.getAbsolutePath());
            long blockSize = statFs.getBlockSize();
            long blockCount = statFs.getBlockCount();
            return blockSize * blockCount;
        }
        return 0;
    }

    public static InputStreamReader newUtf8OrDefaultInputStreamReader(InputStream stream) {
        try {
            return new InputStreamReader(stream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new InputStreamReader(stream);
        }
    }

    public static OutputStreamWriter newUtf8OrDefaultOutputStreamWriter(OutputStream stream) {
        try {
            return new OutputStreamWriter(stream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new OutputStreamWriter(stream);
        }
    }

    public static void closeStream(SQLiteDatabase db) {
        if (db != null) {
            try {
                db.close();
            } catch (Exception e) {
                android.util.Log.e(LOG_TAG, "Could not close db", e);
            }
        }
    }

    //Some one use this method to close a cursor, but cursor isn't extends interface Closeable before 4.1,
    //so add this method make it works before 4.1
    public static void closeStream(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                android.util.Log.e(LOG_TAG, "Could not close cursor", e);
            }
        }
    }

    /**
     * Closes the specified stream.
     *
     * @param stream The stream to close.
     */
    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                android.util.Log.e(LOG_TAG, "Could not close stream", e);
            }
        }
    }
}
