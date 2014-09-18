
package com.adolphin.common;

import java.io.File;
import java.util.List;
import junit.framework.Assert;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.jayway.android.robotium.solo.Solo;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
@SuppressLint("DefaultLocale")
public abstract class BaseTest extends ActivityInstrumentationTestCase2<Activity> {
    public Instrumentation inst;
    public Solo solo;
    public boolean get = false;
    private static final int TimeOut = 50000;

    @SuppressWarnings({"deprecation" })
    public BaseTest() {
        super(Constants.TARGET_PACKAGE_ID, Constants.launcherActivityClass);
    }

    @Override
    public void setUp() throws Exception {
        this.inst = getInstrumentation();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        while (true) {
            solo.goBack();
            solo.sleep(500);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.clickOnView("button3");
                break;
            }
        }

    }

    /**
     * 获取url在打开时的进度条 getTinyTittleBar()
     */
    public View getTinyTittleBar() {

        View progressView = null;
        List<View> curViews = solo.getCurrentViews();
        for (View view : curViews) {
            if (view.toString().contains("TinyTitleBar")) {
                Log.d("mingoo", "TinyTitleBar");
                progressView = view;
                break;
            }
        }
        assertNotNull("Could not find the url loading progress bar", progressView);
        return progressView;
    }

    /**
     * 等待url在打开时的进度条消失
     *
     * @param progressView 进度条的view
     */
    public boolean waitUrlOpen(View progressView) {

        boolean urlOpened = false;
        int state = progressView.getVisibility();
        if (state == View.GONE) {
            // firstly it is gone, then visible when certain
            // bookmark has been clicked
            while (progressView.getVisibility() != View.VISIBLE) {
                solo.sleep(100);
            }
        }
        int maxWaitCount = 300;
        // visible when loading web page, so just wait it to vanish
        while (progressView.getVisibility() == View.VISIBLE && maxWaitCount > 0) {
            solo.sleep(100);
            maxWaitCount--;
        }
        // progress bar vanishing in limited times makes success result
        if (maxWaitCount > 0)
            urlOpened = true;

        return urlOpened;
    }

    /**
     * 等待网页打开,超时时间15秒 此方法只能用于加载网页,上一步骤后不要加sleep
     */
    public boolean waitUrlOpen() {

        boolean urlOpened = false;
        View progress_bar = solo.getView("tiny_title_bar", 0);
        // View progress_bar =getViewByClassName("TinyTitleBar",1);
        solo.sleep(800);
        // final long endTime1 = SystemClock.uptimeMillis() + 2000;
        // while(progress_bar.getVisibility()!=View.VISIBLE&&SystemClock.uptimeMillis()<endTime1){
        // solo.sleep(10);
        // }
        final long endTime = SystemClock.uptimeMillis() + TimeOut;
        while (progress_bar.getVisibility() == View.GONE && SystemClock.uptimeMillis() < endTime) {
            Log.i("find file", "KKKK");
            solo.sleep(100);
        }
        if (SystemClock.uptimeMillis() < endTime) {
            urlOpened = true;
            Log.i("find file", "KKKK2");
        } else
            assertTrue("The page load time out", false);
        return urlOpened;
    }

    public void waitPageLoadFinish() {
        boolean isFinished = false;
        long delay = 60000;
        long endTime = SystemClock.uptimeMillis() + delay;
        Log.i("time1", String.valueOf(endTime));
        while(isFinished == false && SystemClock.uptimeMillis() <= endTime) {
            if(solo.getViewByPath("main_menubar_holder[0][2]").getVisibility() == View.GONE) {
                isFinished = true;
            }
        }
        if(SystemClock.uptimeMillis() > endTime) {
            Log.i("time2", String.valueOf(SystemClock.uptimeMillis()));
            assertTrue("load page timeout is more than 1 min", false);
        }
        Log.i("time3", "isFinished is:" + String.valueOf(isFinished));
    }

    /**
     * 根据classname来获取view
     *
     * @param classname view的类名
     * @param index view的索引，1表示第一个
     */
    public View getViewByClassName(String classname, int index) {

        View myview = null;
        int i = 0;
        List<View> curViews = solo.getCurrentViews();
        for (View view : curViews) {
            if (view.toString().contains(classname)) {
                if (++i == index) {
                   // Log.d("Get", view.toString());
                    myview = view;
                    break;
                }
            }
        }
      //  assertNotNull("Could not find the view by classname", myview);
        return myview;
    }

    /**
     * 点击dolphin四键
     *
     * @param index 键的索引 1后退 2前进 3海豚 4手势 5新窗口
     */
    public void clickOnDolphinMenuBar(int index) {

        if (index < 1 || index > 5)
            Assert.assertTrue("the index of dolphin four key should between 1 and 5", false);
        View dolphinKey=getViewByClassName("MenuBarItem",index);
        if(dolphinKey==null){
            clickOnDolphinMenuItem("Full screen");
            solo.sleep(1000);
            dolphinKey=getViewByClassName("MenuBarItem",index);
        }
        solo.clickOnView(dolphinKey);
    }

    /**
     * 点击dolphin菜单里里的选项
     *
     * @param menuitem 菜单里面要点击的项，就是该项的名字
     */
    @SuppressLint("DefaultLocale")
    public void clickOnDolphinMenuItem(String menuitem) {

        int i = 0, j = 0;
        boolean get = false;
        View mitem = null;
        String[][] Menu_Item = {
                {
                        "Mobile Pages", "Fullscreen","Classic Tabs", "Private Mode","No Image", "Night Mode"
                }, {
                        "Add", "Share", "Devices", "Find in page", "Home", "Exit"
                }, {
                        "Click to add varieties of add-ons!"
                }
        };

        for (i = 0; i < Menu_Item.length; i++) {
            for (j = 0; j < Menu_Item[i].length; j++)
                if (Menu_Item[i][j].toLowerCase().contains(menuitem.toLowerCase())) {
                    get = true;
                    break;
                }
            if (get)
                break;
        }
        Assert.assertTrue("the menu item you may not spell right,check it " + menuitem, get);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(1500);
        ViewGroup PanelMenuTabBar=(ViewGroup)getViewByClassName("PanelMenuTabBar",1);
        View web=getViewByClassName("WebView",1);
        if(web==null){
        solo.clickOnView(PanelMenuTabBar.getChildAt(i));
        solo.sleep(1000);
        }else{
            if(i!=1)
                solo.clickOnView(PanelMenuTabBar.getChildAt(i));
        }
        List<TextView> result = solo.getCurrentViews(TextView.class);
        for (TextView re : result) {
            if (re.getText().toString().toLowerCase().contains(menuitem.toLowerCase())) {
                mitem = re;
                break;
            }

        }
        assertNotNull("Could not find the item " + mitem, mitem);
        solo.clickOnView(mitem);
    }

    @SuppressLint("DefaultLocale")
    public void visitUrl(String url){
        Context context=getInstrumentation().getTargetContext();
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName("mobi.mgeek.TunnyBrowser",
                "mobi.mgeek.TunnyBrowser.BrowserActivity");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void clickControlPanel(String which){
        while(!solo.waitForActivity("BrowserActivity",1000)){
            solo.sendKey(KeyEvent.KEYCODE_BACK);
            solo.sleep(1000);
         }
        solo.clickOnView("open_control_panel");
        solo.sleep(Resource.TIME_SMALL);
        //solo.scrollToSide(solo.LEFT);
        solo.clickOnText(which);
    }

    /**
     * 前置：MENU键可点击 int 1 点击分享网页 int 2 点击下载管理 int 3 点击插件 int 4 点击书签 int 5 点击设置
     * int 6 点击退出 menuitem(5);
     */
    public void clickonmenuitem(int a) {

        solo.sendKey(82);
        String[] menuint = {
                "Share page", "Downloads", "Add-ons", "Bookmarks", "Settings", "Exit"
        };
        if (a > menuint.length) {
            Log.e("error", "please input 1~6");
        } else {
            solo.sleep(3000);
            solo.clickOnText(menuint[a - 1]);
        }
    }

    /**
     * 在地址栏输入网址访问url 要访问的网址
     *
     * @param url 要访问的网址
     */
    public void typeUrlAndVisit(String url) {

        @SuppressWarnings("deprecation")
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        solo.sleep(2000);
        View urladdress = solo.getView("title_design");
        solo.sleep(2000);
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.clearEditText(0);
        solo.sleep(1000);
        // 输入网址访问
        solo.enterText(0, url);
        solo.sleep(2000);
        solo.clickOnView("id/go");
    }

    /**
     * 长按地址栏在点击弹出框里面的内容
     *
     * @param index 需要点击弹出框的第几行
     */
    public void clickLongOnAddressAndList(int index) {

        String[] list = {
                "添加书签", "复制网页网址", "粘贴", "为此网页创建一个手势", "分享网页", "保存网页", "页内搜索"
        };
        @SuppressWarnings("deprecation")
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        View urladdress = getViewByClassName("HintTextView", 1);
        solo.sleep(2000);
        solo.clickLongOnView(urladdress, 1000);
        solo.sleep(2000);
        // solo.clickInList(index, 0);
        View listtoclick = null;
        List<TextView> result = solo.getCurrentViews(TextView.class);
        for (TextView re : result) {
            if (re.getText().toString().equals(list[index])) {
                listtoclick = re;
                break;
            }

        }

        // View listtoclick = getViewByClassName("ListMenuItemView", index + 1);
        solo.clickOnView(listtoclick);
    }

    /**
     * 清除海豚的设置项里面的历史记录
     */
    public void clearDolphinHistory() {

        clickOnDolphinMenuItem("书签/历史");
        solo.sleep(5000);
        TextView title = (TextView) solo.getView("bookmark_path");
        Log.i("iiiiii", title.getText().toString());
        if (title.getText().toString().equals("书签"))
            solo.clickInList(1, 0);
        solo.sleep(2000);
        if (((ViewGroup) solo.getView("list", 0)).getChildCount() != 1) {
            solo.clickOnView("bookmark_settings_btn");
            solo.sleep(2000);
            solo.clickOnView("quick_menu_icon");
            solo.sleep(2000);
            solo.clickOnView("button1");
            solo.sleep(2000);
            solo.clickOnView("done_btn");
            solo.sleep(2000);
        }
        solo.clickOnView("back_parent_btn");
        solo.sleep(2000);
        solo.sendKey(KeyEvent.KEYCODE_BACK);
        solo.sleep(2000);
    }

    /**
     * 长按view并拖动到另一个view，可以用于图标的拖动
     *
     * @param viewFrom 被长按view
     * @param viewTo 目标view
     */

    // public void clickLongAndDrag(View viewFrom, View viewTo) throws Exception
    // {
    //
    // final int[] location = new int[2];
    // final int[] location2 = new int[2];
    // viewFrom.getLocationOnScreen(location);
    // viewTo.getLocationOnScreen(location2);
    // int stepCount = 3;
    //
    // final int viewFromWidth = viewFrom.getWidth();
    // final int viewFromHeight = viewFrom.getHeight();
    // final float xStart = location[0] + (viewFromWidth / 2.0f);
    // float yStart = location[1] + (viewFromHeight / 2.0f);
    //
    // final int viewToWidth = viewFrom.getWidth();
    // final int viewToHeight = viewFrom.getHeight();
    // final float xStop = location2[0] + (viewToWidth / 2.0f);
    // float yStop = location2[1] + (viewToHeight / 2.0f);
    //
    // float yStep = (xStop - xStart) / stepCount;
    // float xStep = (yStop - yStart) / stepCount;
    // float y = xStart;
    // float x = yStart;
    //
    // long downTime = SystemClock.uptimeMillis();
    // long eventTime = SystemClock.uptimeMillis();
    // try {
    // // eventTime = SystemClock.uptimeMillis() + 1000;
    // // for (int i = 0; i < stepCount; ++i) {
    // // y += yStep;
    // // x += xStep;
    // // eventTime = SystemClock.uptimeMillis();
    // // event = MotionEvent.obtain(downTime,
    // // eventTime,MotionEvent.ACTION_MOVE, x, y, 0);
    // // try {
    // // inst.sendPointerSync(event);
    // // } catch (SecurityException ignored) {}
    // // }
    // MotionEvent event = MotionEvent.obtain(downTime, eventTime,
    // MotionEvent.ACTION_DOWN,
    // xStart, yStart, 0);
    // inst.sendPointerSync(event);
    //
    // eventTime = SystemClock.uptimeMillis() + 3000;
    //
    // event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE,
    // location[0],
    // location[1], 0);
    // inst.sendPointerSync(event);
    // eventTime = SystemClock.uptimeMillis() + 3000;
    //
    // event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE,
    // xStop, yStop,
    // 0);
    // inst.sendPointerSync(event);
    // //eventTime = SystemClock.uptimeMillis() + 3000;
    //
    // event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP,
    // xStop, yStop, 0);
    // inst.sendPointerSync(event);
    // //eventTime = SystemClock.uptimeMillis() + 3000;
    // } catch (Exception ignored) {
    // }
    // }

    public void clickLongAndDrag(View viewFrom, View viewTo) {

        final Rect rct = new Rect();
        final Rect rct1 = new Rect();
        viewFrom.getGlobalVisibleRect(rct);
        viewTo.getGlobalVisibleRect(rct1);

        int stepCount = 10;
        float fromX = rct.left + viewFrom.getWidth() / 2;
        float fromY = rct.top + viewFrom.getHeight() / 2;
        float toX = rct1.left + viewTo.getWidth() / 2;
        float toY = rct1.top + viewTo.getHeight() / 2;
        Log.d("TEST", "moving: fx: " + fromX + " fy: " + fromY + " toX: " + toX + " toY: " + toY);

        boolean successfull = false;
        int retry = 0;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, fromX,
                fromY, 0);

        while (!successfull && retry < 10) {
            try {
                inst.sendPointerSync(event);
                successfull = true;
            } catch (SecurityException e) {
                retry++;
            }
        }
        if (!successfull) {
            Assert.assertTrue("Click can not be completed!", false);
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, fromX + 1.0f,
                fromY + 1.0f, 0);
        inst.sendPointerSync(event);

        solo.sleep((int) (ViewConfiguration.getLongPressTimeout() * 2.5f));

        float y = fromY + 1.0f;
        float x = fromX + 1.0f;
        float yStep = (toY - fromY) / stepCount;
        float xStep = (toX - fromX) / stepCount;

        for (int i = 0; i < stepCount; ++i) {
            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }
        solo.sleep(Resource.TIME_Middle);
        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, toX, toY, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }

    }

    /**
     * 长按图标并拖动 根据坐标拖动
     */
    public  void clickLongAndDrag(float fromX, float toX, float fromY, float toY, int stepCount)
            throws Exception {

    }

    /**
     * 查找sd卡文件
     *
     * @param filename 查找的关键词
     * @param filepath 查找的目录
     * @param delete 找到文件后是否删除，false表示不删除
     */
    public boolean searchFile(String filename, File filepath, boolean delete) {

        // boolean get=false;
        // 判断SD卡是否存在

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File[] files = filepath.listFiles();

            if (files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory() && !get) {
                        //判断文件夹名是否为查找目标
                        if(file.getName().equals(filename)) {
                            get = true;
                            Log.i("findFile", file.getName());
                            break;
                        }
                        // 如果目录可读就执行
                        if (file.canRead()) {
                            searchFile(filename, file, delete); // 如果是目录，递归查找
                        }
                    } else {
                        // 判断是文件，则进行文件名判断
                        try {
                            if (file.getName().indexOf(filename) > -1) {
                                Log.i("findFile", file.getName().toString());
                                Log.i("findFile", "Found");
                                get = true;
                                if (delete) {
                                    file.delete();
                                }
                                break;
                            } else {
                                Log.i("findFile", "Not found");
                            }
                        } catch (Exception e) {
                        }
                    }

                }

            }
        } else {
            assertTrue("no sd card found in your phone", false);
        }

        return get;

    }

    /**
     * 启动海豚时候的等待主界面出现，通常用于测试函数的第一行
     */
    public boolean waitMainActivity() {

        boolean start = solo.waitForView(TextView.class, 1, 50000);
        if (!start)
            assertTrue("未能成功启动主界面", false);
        solo.sleep(3000);
        return true;
    }

    /**
     * 清空所有的海豚数据缓存
     */

    public void clearAllDolphinData() {

        File dir = new File("/data/data/mobi.mgeek.TunnyBrowser/");
        File[] files = dir.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                if (!file.getName().equals("lib")) {
                    deleteAllFile(file);
                }
            }
        }
    }

    /**
     * 删除给定路径下的所有文件
     *
     * @param file 给定的的文件或者路径，如/data/m/
     */

    public void deleteAllFile(File file) {

        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                if (files.length > 0) {
                    for (File f : files) {
                        deleteAllFile(f);
                    }
                }
            }
            file.delete();
        }
    }

    /**
     * 加载网页时的超时设定，debug版本才能用
     *
     * @param timeout 设定的超时时间，需要等待页面资源完全加载完，所以尽量设置大一点
     */
    public void waitForPageLoad(int timeout) {

        boolean page_download_finish = solo.waitForLogMessage("onPageFinished", timeout);
        if (!page_download_finish)
            assertTrue("网页加载超时", false);
    }

    /**
     * 长按view等待弹出框，并选择弹出框的第几个条目
     *
     * @param view 需要进行点击的view
     * @param index 要选择弹出框里面的第几行，0表示第一行
     */

    public void clickLongOnViewAndPress(View view, int index) {

        solo.clickLongOnView(view, 2000);
        solo.waitForDialogToOpen();
        try {
            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
        } catch (SecurityException e) {
            Assert.assertTrue("Can not press the context menu!", false);
        }
        for (int i = 0; i < index; i++) {
            solo.sleep(500);
            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
        }
        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);

    }

    public void restoreFullScreen(BaseTest test) {

        Utils.scrollToLeft(test);
        test.solo.sleep(Resource.TIME_SMALL);
        if(test.solo.getViewByPath("list_installed_plugin[0][0][0][0]").isSelected()) {
            test.solo.clickOnView(test.solo.getViewByPath("list_installed_plugin[0][0][0][0]"));
            assertTrue("Can not turn off FullScreen.", test.solo.waitForText("Fullscreen is turned off"));
        }
    }

    public void clickFullScreen() {

        Utils.scrollToLeft(this);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getViewByPath("list_installed_plugin[0][0][0][0]"));
    }

    public void enterSonarGestureScreen() {
        Display display = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int height = point.y;

        View view = solo.getViewByPath("bottom_container[0][3][0]");
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.i("location", "x is " + location[0] + " , y is " + location[1]);
        solo.drag(location[0], location[0], location[1], height/2, 2);
    }
}
