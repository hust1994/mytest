package com.dolphin.testcase.download;

import java.io.File;
import java.util.List;

import junit.framework.Assert;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.adolphin.utils.StorageHelper;
//import com.dolphin.testcase.addressbar.EnterStringTest;


public class Download {
    public static String downloading_url = "http://172.16.7.123/test/downloadtest/apk/DolphinBrowserCN_20131211102918_signed.apk";
    public static String downloaded = "http://172.16.7.123/test/downloadtest/apk/downapk.apk";
    public static String downloadedpicture = "http://172.16.7.123/test/downloadtest/img/kimi.jpg";
    public static String downloadedhtml = "http://172.16.7.123/test/downloadtest/";
    public static String downloadedpdf = "http://172.16.7.123/test/downloadtest/downpdf.pdf";
    public static String downloadretry ="http://172.16.7.123/test/downloadtest/apk/6m.apk ";
    public static String BIG_FILE = "http://172.16.7.123/test/downloadtest/90m.zip";

    /*
     * 功能：构建测试环境后建立下载任务
     */
    public static void setUpDownload(String a, BaseTest test) {
        test.visitUrl(a);
        test.solo.sleep(Resource.TIME_SMALLEST);
        Assert.assertTrue("Network problem. The 'Save as' dialog doesn't apprear.", test.solo.waitForDialogToOpen());
        test.solo.clickOnView("button2");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    public static void enterDownloadScreen(BaseTest test) {
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.sendKey(KeyEvent.KEYCODE_MENU);
        test.solo.sleep(Resource.TIME_SMALL);
        Assert.assertTrue("Menu has not come out.", test.solo.searchText("Downloads"));
        test.solo.clickOnText("Downloads");
    }

    /*
     * 功能：建立一个下载任务后进入下载管理界面
     */
    public static void downloads(String a, BaseTest test) {
        setUpDownload(a, test);
        enterDownloadScreen(test);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /*
     * 功能：建立一个下载任务后查看是否下载完成后进入FILE MANAGER界面
     */
    public static void filemanager(String a, BaseTest test) {

        downloads(a, test);
        timeout("Today", test);
        test.solo.clickOnText("Manager");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /*
     * 功能：根据classname来获取view(未断言)
     */
    public static View getViewByClassName(String classname, int index,
            BaseTest test) {

        View myview = null;
        int i = 0;
        List<View> curViews = test.solo.getCurrentViews();
        for (View view : curViews) {
            if (view.toString().contains(classname)) {
                if (++i == index) {
                    Log.d("Get", view.toString());
                    myview = view;
                    break;
                }
            }
        }
        return myview;
    }

    /*
     * 功能：在下载管理界面清除下载任务，还原下载测试环境
     */
    public static void clearAllDownloadInfo(BaseTest test) {
        Download.enterDownloadScreen(test);
        test.solo.sleep(Resource.TIME_SMALL);
//        test.solo.clickOnView(test.solo
//                .getViewByPath("tabs_bg[0][0]"));
        test.solo.clickOnText("Downloaded");
        test.solo.sleep(Resource.TIME_SMALLEST);
        if (test.solo.waitForText("Downloading",1,1000)) {
            test.clickLongOnViewAndPress(
                   test.solo.getViewByPath("time_list[0][1]"), 1);
            test.solo.sleep(Resource.TIME_SMALL);
        }

        if (test.solo.waitForText("Today",1,1000)) {
            test.clickLongOnViewAndPress(
                    test.solo.getViewByPath("time_list[0][1]"), 5);
            test.solo.sleep(Resource.TIME_SMALL);
            test.solo.clickOnButton("OK");
            test.solo.sleep(Resource.TIME_SMALL);
        }
        Download.myDeleteAllFiles(test);
        test.solo.goBack();
    }
    /*
     * 功能：删除dowmload路径下的所有文件
     * */
    public static boolean myDeleteAllFiles(BaseTest test) {
        final File sdcardDir = StorageHelper.getExternalStorageDirectory();
        if (sdcardDir != null && sdcardDir.exists()) {
           test.deleteAllFile(new File(sdcardDir, "download"));
            return true;
        }
        return false;
    }

    /*
     * 功能：等待并查看多任务下载完成并正确显示下载数目
     */
    public static boolean timeOut(String a, BaseTest test) {
        int timeout = 20;
        while (!test.solo.waitForText("Today",1,1000) && timeout > 0) {
            test.solo.sleep(Resource.TIME_BIG);
            timeout--;
        }
        if (timeout <= 0) {
            BaseTest.assertTrue("timeout", false);
        }

        if (((TextView) test.solo.getView("group_title", 0)).getText()
                .toString().equals(a)) {
            return true;
        }
        return false;

    }

    /*
     * 功能：等待任务下载完成，出现Today栏
     */
    public static void timeout(String a, BaseTest test) {
        int timeout = 10;
        while (!test.solo.searchText(a) && timeout > 0) {
            test.solo.sleep(Resource.TIME_BIG);
            timeout--;

            if (timeout <= 0) {
                BaseTest.assertTrue("Download timeout.", false);
            }

        }

    }
    /*
     * 通过判断list的第一个child是否包含Today来判断下载是否已经结束，超时时间是60s
     * */
    public static boolean isDownloadFinished(BaseTest test) {
        boolean isFinished = false;
        int sleepCount = 0;

        while(sleepCount < 3) {
            if(!((TextView)test.solo.getView("group_title")).getText().toString().contains("Today")) {
                test.solo.sleep(Resource.TIME_LONG);
                sleepCount++;
            } else {
                isFinished = true;
                break;
            }
        }
        return isFinished;
    }

    public static void clickLongOnScreenAndPress(float x,float y, int index,BaseTest test) {

        test.solo.clickLongOnScreen(x, y,2000);
        test.solo.waitForDialogToOpen();
        try {
            test.inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
        } catch (SecurityException e) {
            Assert.assertTrue("Can not press the context menu!", false);
        }
        for (int i = 0; i < index; i++) {
            test.solo.sleep(500);
            test.inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
        }
        test.inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);

    }

    public static void restoreTestEnv(BaseTest test) {
        test.solo.goBack();
        test.solo.sleep(Resource.TIME_SMALL);
        Download.clearAllDownloadInfo(test);
    }
    
    /**
     * 前置：选择锁定屏幕：竖屏（Landscape)，横屏(Portrait)，自动(Auto)
     *
     * @param text
     *               选择锁定的屏幕
     */
    public static void switchOrientation(String text,BaseTest test){
        enterSettingScreen(test);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Customize");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Orientation");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText(text);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.goBackToActivity("BrowserActivity");
    }
    
    public static void enterSettingScreen(BaseTest test) {
        test.solo.sendKey(KeyEvent.KEYCODE_MENU);
        test.solo.sleep(Resource.TIME_Middle);
        ViewGroup viewGroup = null;

        while(viewGroup == null) {
            viewGroup = (ViewGroup)test.getViewByClassName("PanelMenuTabBar", 1);
        }
        viewGroup = (ViewGroup)viewGroup.getChildAt(0);
        View view = viewGroup.getChildAt(0);
        test.solo.clickOnView(view);
    }
}
