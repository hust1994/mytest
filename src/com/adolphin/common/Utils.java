
package com.adolphin.common;

import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Solo;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.util.List;
import junit.framework.Assert;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class Utils {
    public static final int SHARE_PAGE = 0;
    public static final int DOWNLOADS = 1;
    public static final int SETTINGS = 4;
    public static final int EXIT = 5;

    /**
     * 传入顶级父节点ID找到需要的子节点View对象
     *
     * @author WuYang
     * @param viewIdStr 父节点ID
     * @param arr 子节点相对于父节点的层级索引的数组
     * @return 子节点View对象
     */
    public static View getChildView(String viewIdStr, int[] arr, BaseTest test) {

        View view = null;
        // 最顶级父节点
        ViewGroup viewGroup = (ViewGroup) test.solo.getView(viewIdStr);
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                viewGroup = (ViewGroup) viewGroup.getChildAt(arr[i]);
            } else {
                view = viewGroup.getChildAt(arr[i]);
            }
        }
        // test.solo.clickOnView(((ViewGroup)((ViewGroup)((ViewGroup)test.solo.getView("id/content_view")).getChildAt(2)).getChildAt(1)).getChildAt(1));
        return view;
    }

    /**
     * 通过点击dolphinkey后传入对应操作的int类型来点击相应操作
     *
     * @author WuYang
     * @param i 1：Tab list 2：sonar/gesture 3:menu
     */
    public static void dolphinEvents(int i, BaseTest test) {

        // int[] arr = new int[]{};
        int[] arr0 = {
                2, 1, 1
        };
        int[] arr1 = {
                2, 0, 0
        };
        int[] arr2 = {
                2, 0, 1
        };
        int[] arr3 = {
                2, 0, 2
        };

        String strdolID = "id/content_view";
        test.solo.clickOnView(getChildView(strdolID, arr0, test));
        test.solo.sleep(Resource.TIME_SMALL);

        switch (i) {
            case 1:
                test.solo.clickOnView(getChildView(strdolID, arr1, test));
                break;
            case 2:
                test.solo.clickOnView(getChildView(strdolID, arr2, test));
                break;
            case 3:
                test.solo.clickOnView(getChildView(strdolID, arr3, test));
                break;
            default:
                break;
        }
        test.solo.sleep(Resource.TIME_BIG);
    }

    /**
     * 地址栏输入url点击go打开对应的url的web页面
     *
     * @author WuYang
     * @param urlStr ：输入的url
     */
    public static void inputWebUrlGo(String urlStr, BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.scrollToTop();
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/address_bar");
        test.solo.sleep(Resource.TIME_BIG);
        test.solo.clearEditText((EditText) test.solo.getView("id/search_src_text"));
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.enterText((EditText) test.solo.getView("id/search_src_text"), urlStr);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/go");
        // test.solo.sleep(Resource.TIME_OUT_SMALL);
        test.waitForPageLoad(Resource.TIME_OUT_SMALL);
    }

    /**
     * 前置：通过地址栏左侧点击加号后，弹出选择框 1 选择添加书签 2 添加快速访问 3 添加图标到桌面 addboolmark(1);
     */
    public static void addbookmark(int a, BaseTest test) {

        switch (a) {
            case 1:
                test.solo.clickOnView("id/bookmark_btn");
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.clickOnView("id/option_add_bookmark");
                break;
            case 2:
                test.solo.clickOnView("id/bookmark_btn");
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.clickOnView("id/option_add_speeddial");
                break;
            case 3:
                test.solo.clickOnView("id/bookmark_btn");
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.clickOnView("id/option_add_shortcut");
                break;
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 前置：已在书签添加界面 int 1 点击书签名称输入框 int 2 点击网址url输入框 string “” 输入的文字
     * addbookmarkoption(1,"书签");
     */
    public static void addbookmarkoption(int a, String b, BaseTest test) {

        switch (a) {
            case 1:
                test.solo.clearEditText((EditText) test.solo.getView("id/title"));
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.enterText((EditText) test.solo.getView("id/title"), b);
                break;
            case 2:
                test.solo.clearEditText((EditText) test.solo.getView("id/address"));
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.enterText((EditText) test.solo.getView("id/address"), b);
                break;
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 前置：已在书签添加界面 int 3 点击文件夹，可以选择添加至哪个文件夹 int 4 点击分配手势按钮 int 5 点击取消按钮 int 6
     * 点击添加按钮 addbookmarkoption(5);
     */
    public static void addbookmarkoption(int a, BaseTest test) {

        switch (a) {
            case 3:
                test.solo.clickOnButton("id/folder");
                break;
            case 4:
                test.solo.clickOnView("id/gesture_image");
                break;
            case 5:
                test.solo.clickOnView("id/cancel");
                break;
            case 6:
                test.solo.clickOnView("id/OK");
                break;
        }
    }

    /**
     * 前置：MENU键可点击 int 1 点击分享网页 int 2 点击下载管理 int 3 点击插件 int 4 点击书签 int 5 点击设置
     * int 6 点击退出 menuitem(5);
     */
    public static void menuitem(int a, BaseTest test) {

        String[] menuint = {
                "0x1", "0x2", "menu_add_on_bar", "menu_bookmark_bar", "0x3", "0x4"
        };
        test.solo.sendKey(KeyEvent.KEYCODE_MENU);
        if (a > menuint.length) {
            Log.e("error", "please input 1~6");
        } else {
            test.solo.clickOnView(menuint[a]);
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 滑动屏幕
     *
     * @param a 1:右滑出书签栏 2:左滑返回主屏
     */
    @SuppressWarnings("deprecation")
    public static void scorllScreen(int a, BaseTest test) {

        int height, width;
        height = test.getActivity().getWindowManager().getDefaultDisplay().getHeight();
        width = test.getActivity().getWindowManager().getDefaultDisplay().getWidth();

        switch (a) {
            case 1:
                // 右滑出书签栏
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.drag(0, width / 2, height / 2, height / 2, 2);
                break;
            case 2:
                // 左滑返回主屏
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.drag(width, 0, height / 2, height / 2, 2);
                break;
            default:
                break;
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    public static void scrollToRight(BaseTest test) {
        Display display = test.getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        test.solo.drag(0, width/2, height/2, height/2, 2);
    }

    public static void scrollToLeft(BaseTest test) {
        Display display = test.getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        //此处drag的第一个参数需要注意，如果填width可能会划不动
        test.solo.drag(width-1, 0, height/2, height/2, 2);
    }

    public static void scrollToTop(BaseTest test) {
        Display display = test.getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        test.solo.drag(width/2, width/2, height/4, height*4/5, 10);
    }
    /**
     * 新建文件夹
     *
     * @param fStr 文件名
     * @author WuYang
     */
    public static void createFolder(String folderName, BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("more_icon");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView(test.solo.getView("id/title", 0));
        test.solo.sleep(Resource.TIME_SMALL);

        // 点击bookmark栏设置按钮 点击新建文件夹图标 输入文件夹名称"abcd"
        //test.solo.clickOnView("id/bookmark_settings");
        //test.solo.sleep(Resource.TIME_SMALL);

        test.solo.clickOnView("id/btn_create_folder");
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.enterText((EditText) test.solo.getView("id/input"), folderName);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/button2");
        test.solo.sleep(Resource.TIME_SMALL);
        // 点击“对勾”按钮,返回bookmark栏
        test.solo.clickOnView("id/btn_done");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 同步书签
     *
     * @param i 1:Facebook 2:dolphin connect 3:google
     * @author WuYang
     */
    public static void syncBookmark(int i, BaseTest test) {
        //Sign in to Dolphin Connect to sync web content across all your devices.
        test.solo.sleep(Resource.TIME_SMALL);
        Assert.assertTrue("Network error", test.solo.searchText("Sign in to Dolphin Connect to sync web content across all your devices."));
        test.solo.sleep(Resource.TIME_SMALL);
        switch (i) {
            case 1:
                // Facebook
                test.solo.clickOnView("id/facebook_icon");
                break;
            case 2:
                // google
                test.solo.clickOnView("id/google_icon");
                break;
            case 3:
                // dolphin connect
                test.solo.clickOnView("id/btn_login_dolphin");
                break;
            default:
                break;
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * Dolphin connect同步书签输入用户名和密码
     *
     * @author WuYang
     * @param user
     * @param pwd
     */
    public static void syncDolphinConnectLogin(String user, String pwd, BaseTest test) {

        test.solo.enterText((EditText) test.solo.getView("id/ds_email"), user);
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.enterText((EditText) test.solo.getView("id/ds_password"), pwd);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * Facebook connect同步书签输入用户名和密码
     *
     * @author WuYang
     * @param user
     * @param pwd
     */
    public static void syncFacebookLogin(String user, String pwd, BaseTest test) {

        test.solo.enterTextInWebElement(By.name("email"), user);
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.enterTextInWebElement(By.name("pass"), pwd);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * Google同步书签输入用户名和密码
     *
     * @author WuYang
     * @param user
     * @param pwd
     */
    public static void syncGoogleLogin(String user, String pwd, BaseTest test) {
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.enterTextInWebElement(By.name("Email"), user);
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.enterTextInWebElement(By.name("Passwd"), pwd);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 打开关闭隐私模式PrivateMode
     *
     * @author WuYang
     */
    public static void openOffPrivateMode(BaseTest test) {

        dolphinEvents(3, test);
        // test.solo.clickOnText("Settings");
        menuClick(4, test);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Privacy & Personal Data");
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.clickOnText("Private Mode");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.goBack();
        test.solo.goBack();
    }

    /**
     * 清除Most visited历史记录(当前界面在主界面)
     *
     * @author WuYang
     */
    public static void clearMostVHistory(BaseTest test) {

        dolphinEvents(3, test);
        test.solo.clickOnView("menu_bookmark_bar");
        test.solo.sleep(Resource.TIME_SMALL);

        Boolean isBookmark = test.solo.searchText("BOOKMARKS");
        if (isBookmark == false) {
            test.solo.clickOnView("id/back_parent");
            test.solo.sleep(Resource.TIME_SMALL);
            if (isBookmark == false) {
                test.solo.clickOnView("id/back_parent");
                test.solo.sleep(Resource.TIME_SMALL);
            }
        }
        test.solo.sleep(Resource.TIME_SMALL);

        View view = getChildView("id/list", new int[] {
            0
        }, test);
        test.solo.clickOnView(view);
        test.solo.sleep(Resource.TIME_SMALL);

        test.solo.clickOnView(getChildView("id/list", new int[] {
            1
        }, test));
        test.solo.sleep(Resource.TIME_BIG);
        View view1 = getChildView("id/list", new int[] {
            0
        }, test);
        test.clickLongOnViewAndPress(view1, 3);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/button1");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/back_parent");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/back_parent");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 清除Most visited历史记录(当前界面在历史记录界面)
     *
     * @author WuYang
     */
    public static void clearMostVHistoryInMost(BaseTest test) {

        // test.solo.clickOnView(getChildView("id/list", new int[] {
        // 1
        // }, test));
        test.solo.sleep(Resource.TIME_BIG);
        View view1 = getChildView("id/list", new int[] {
            0
        }, test);
        test.clickLongOnViewAndPress(view1, 3);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/button1");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/back_parent");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/back_parent");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 清除历史记录（当前界面在历史记录下）
     *
     * @author WuYang
     */
    public static void clearHistoryInHistory(BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        View view1 = getChildView("id/list", new int[] {
            0
        }, test);
        test.clickLongOnViewAndPress(view1, 3);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/button1");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/back_parent");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 清除历史记录(当前界面在主界面)
     *
     * @author WuYang
     */
    public static void clearHistory(BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        dolphinEvents(3, test);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("menu_bookmark_bar");
        test.solo.sleep(Resource.TIME_SMALL);

        Boolean isBookmark = test.solo.searchText("BOOKMARKS");
        if (isBookmark == false) {
            test.solo.clickOnView("id/back_parent");
            test.solo.sleep(Resource.TIME_SMALL);
            if (isBookmark == false) {
                test.solo.clickOnView("id/back_parent");
                test.solo.sleep(Resource.TIME_SMALL);
            }
        }
        test.solo.sleep(Resource.TIME_SMALL);

        View view = getChildView("id/list", new int[] {
            0
        }, test);
        test.solo.clickOnView(view);
        test.solo.sleep(Resource.TIME_SMALL);

        clearHistoryInHistory(test);
    }

    /**
     * 修改Dolphin help文件名
     *
     * @param str 文件名
     * @author WuYang
     */
    public static void renameDolphinHelp(String str, BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        Assert.assertTrue("not found edit button", test.solo.searchText(Resource.DOLP));
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView(getChildView("id/0x6", new int[] {
                2, 1
        }, test));
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/folder_name");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clearEditText((EditText) test.solo.getView("id/folder_name"));
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.enterText((EditText) test.solo.getView("id/folder_name"), str);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 打开或关闭Most visited 显示开关
     *
     * @author WuYang
     */
    public static void onOffMostVisitedFolder(BaseTest test) {

        dolphinEvents(3, test);
        menuClick(4, test);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Set Homepage");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/show_most_visited_folder");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.goBack();
        test.solo.goBack();
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * menu面板上通过类名找到孩子节点的view
     *
     * @author WuYang
     * @param i 0：sharepage 1：Downloads 4：Settings 5：Exit
     */
    public static void menuClick(int i, BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        dolphinEvents(3, test);
        test.solo.sleep(Resource.TIME_SMALL);
        switchCaseType(i, test);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * menu菜单上对应的操作（通过类名反射找的对应的类）
     *
     * @author WuYang
     * @param childid 子节点相对位置
     */
    public static void switchCaseType(int childid, BaseTest test) {

        Class class1;
        test.solo.sleep(Resource.TIME_SMALL);
        try {
            class1 = Class.forName("com.mgeek.android.ui.MenuBarElementGridBar");
            final View v = test.solo.getView(class1, 0);
            if (v instanceof ViewGroup) {
                final View child = ((ViewGroup) v).getChildAt(childid);
                test.solo.sleep(Resource.TIME_SMALL);
                test.solo.clickOnView(child);
                test.solo.sleep(Resource.TIME_SMALL);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 依次访问str数组中的网址列表
     *
     * @author WuYang
     * @param str 网址列表
     */
//    public static void visitUrl(String[] str, BaseTest test) {
//
//        int screenHeight = test.getActivity().getWindowManager().getDefaultDisplay().getHeight();
//        for (int i = 0; i < str.length; i++) {
//            if (i < str.length - 1) {
//                inputWebUrlGo(str[i], test);
//                test.solo.sleep(Resource.TIME_BIG);
//                test.solo.scrollToTop();
//                test.solo.sleep(Resource.TIME_SMALL);
//                View progressView = test.getTinyTittleBar();
//                test.assertTrue("The web is not loading", test.solo.waitForView(progressView));
//                test.assertTrue("web " + str[i] + " is loading unsuccessed",
//                        test.solo.searchText(str[i]));
//                test.solo.sleep(Resource.TIME_BIG);
//                // 点击+tab
//                // test.solo.clickOnView(getChildView("id/fixed_titlebar_holder",
//                // new int[] {
//                // 0, 0, 1
//                // }, test));
////                test.solo.clickOnView(getChildView("id/content_view", new int[] {
////                        0, 0, 0, 0, 0, 1
////                }, test));
//                test.solo.sleep(Resource.TIME_SMALL);
//            } else {
//                inputWebUrlGo(str[i], test);
//                test.solo.sleep(Resource.TIME_BIG);
//                test.solo.scrollToTop();
//                test.solo.sleep(Resource.TIME_SMALL);
//                View progressView = test.getTinyTittleBar();
//                test.assertTrue("The web is not loading", test.solo.waitForView(progressView));
//                test.assertTrue("web " + str[i] + " is loading unsuccessed",
//                        test.solo.searchText(str[i]));
//                test.solo.sleep(Resource.TIME_BIG);
//            }
//        }
//    }


    public static void visitUrl(String[] str, BaseTest test) {

        for (int i = 0; i < str.length; i++) {
                inputWebUrlGo(str[i], test);
                test.solo.sleep(Resource.TIME_BIG);
                test.solo.scrollToTop();
                test.solo.sleep(Resource.TIME_SMALL);
                View progressView = test.getTinyTittleBar();
                Assert.assertTrue("The web is not loading", test.solo.waitForView(progressView));
                Assert.assertTrue("web " + str[i] + " is loading unsuccessed",
                        test.solo.searchText(str[i]));
                test.solo.sleep(Resource.TIME_BIG);
                test.solo.goBack();
                test.solo.sleep(Resource.TIME_SMALL);
        }
    }
    /**
     * @param str 点击的Icon的Text
     * @author WuYang
     * @param type 1:主屏Icon操作 2：Folder内Icon操作
     * @param type2 type2=1点击当前view type2=2直接返回view
     * @return
     */
    public static View clickScreenIconByType(String str, int type, int type2, BaseTest test) {

        test.solo.sleep(Resource.TIME_SMALL);
        View screenIconView = null;
        int childCount;
        ViewGroup realContent = null;
        try {
            if (type == 1) {
                Class class1 = Class.forName(Resource.SCREEN_ICON_CLASS);
                realContent = test.solo.getView(class1, 0);
                childCount = realContent.getChildCount();

            } else {
                Class class1 = Class.forName(Resource.SCREEN_FOLDER_ICON_CLASS);
                ViewGroup folder = test.solo.getView(class1, 0);
                realContent = (ViewGroup) ((ViewGroup) ((ViewGroup) folder.getChildAt(1))
                        .getChildAt(1)).getChildAt(0);
                childCount = realContent.getChildCount();
            }
            // 找到Icon
            for (int i = 0; i < childCount; i++) {
                final ViewGroup g = (ViewGroup) realContent.getChildAt(i);
                final TextView name = (TextView) g.getChildAt(1);
                if (str.equals(String.valueOf(name.getText()))) {
                    screenIconView = g;
                }
            }
        } catch (ClassNotFoundException e) {
            Log.e("TEST", "classNotFound: " + e);
        }
        // type2=1点击当期那view type2=2直接返回view
        if (type2 == 1) {
            // 点击icon
            test.solo.sleep(Resource.TIME_SMALL);
            test.solo.clickOnView(screenIconView);
            test.solo.sleep(Resource.TIME_BIG);
            return screenIconView;
        } else {
            return screenIconView;
        }
    }

    /**
     * 访问对应网址添加speedial后返回桌面将其拖动至对应view
     *
     * @author WuYang
     * @param speedialName
     * @param speedialUrl
     * @param dragTo
     */
    public static void addSpeedialAndDragTo(String speedialName, String speedialUrl, String dragTo,
            BaseTest test) {
        test.solo.sleep(Resource.TIME_SMALL);
        Utils.clickScreenIconByType("Wallpaper", 1, 1, test);
        test.solo.scrollToTop();
        test.solo.sleep(Resource.TIME_SMALL);
        addbookmark(2, test);
        addbookmarkoption(1, speedialName, test);
        addbookmarkoption(2, speedialUrl, test);
        addbookmarkoption(6, test);
        test.solo.sleep(Resource.TIME_BIG);
        test.solo.goBack();
        test.solo.sleep(Resource.TIME_SMALL);
        try {
            test.clickLongAndDrag(clickScreenIconByType(speedialName, 1, 2, test),
                    clickScreenIconByType(dragTo, 1, 2, test));
            test.solo.sleep(Resource.TIME_SMALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.solo.sleep(Resource.TIME_SMALL);
    }

    public static void clearAllData(BaseTest test) {
        Solo solo = test.solo;
        // 清除数据
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_SMALL);
        ViewGroup viewGroup = (ViewGroup)test.getViewByClassName("PanelMenuTabBar", 1);
        viewGroup = (ViewGroup)viewGroup.getChildAt(0);
        View view = viewGroup.getChildAt(0);
        solo.clickOnView(view);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Privacy");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Clear data");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnButton("Clear selected data");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
    }

    public static void setCheckBoxByName(String name, boolean enable, BaseTest test) {
        View checkbox = getCheckBoxView(name, test);
        if (checkbox != null) {
            if (checkbox instanceof CheckBox) {
                if (((CheckBox) checkbox).isChecked() != enable)
                    test.solo.clickOnView(checkbox);
            } else {
                if (checkbox.isSelected() != enable)
                    test.solo.clickOnView(checkbox);
            }
        }
    }

    public static boolean isCheckBoxByName(String name, BaseTest test) {
        View checkbox = getCheckBoxView(name, test);
        if (checkbox != null) {
            if (checkbox instanceof CheckBox) {
                return ((CheckBox) checkbox).isChecked();
            } else {
                return checkbox.isSelected();
            }
        }
        return false;
    }

    public static boolean isCheckBoxChecked(String checkBoxName, BaseTest test) {

        return ((CheckBox)getCheckBoxView(checkBoxName, test)).isChecked();
    }

    public static View getCheckBoxView(String name, BaseTest test) {
        View textview = null;
        View checkbox = null;
        test.solo.searchText(name);
        List<TextView> t = test.solo.getCurrentViews(TextView.class);
        for (TextView s : t) {
            if (s.getText().toString().contains(name)) {
                textview = s;
                break;
            }
        }
        if (textview != null) {
            View parent = (View) textview.getParent();
            List<View> child = test.solo.getViews(parent);
            for (View v : child) {
                if (v.toString().contains("CheckBox")
                        || v.toString().contains("ImageView")) {
                    checkbox = v;
                    break;
                }
            }
        }
        return checkbox;
    }

    public static int getViewResourceId(String id, BaseTest test) {
        Activity activity = test.solo.getCurrentActivity();
        int resId = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        return resId;
    }

    public static void addBookmark(BaseTest test, String name, String url) {
        test.solo.sendKey(KeyEvent.KEYCODE_MENU);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Add");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("id/option_add_bookmark");
        test.solo.sleep(Resource.TIME_SMALL);
        //验证Add bookmark框是否弹出
        Assert.assertTrue("'Add bookmark' dialog is not opened.", test.solo.waitForText("Add bookmark"));
        Utils.addbookmarkoption(1, name, test);
        Utils.addbookmarkoption(2, url, test);

        // 点击folder中的bookmarks选择文件夹bookmarks 后点击Add
        test.solo.clickOnView("id/folder");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView(test.solo.getViewByPath("select_dialog_listview[0]"));
        test.solo.sleep(Resource.TIME_SMALL);
        Utils.addbookmarkoption(6, test);
    }

    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                            .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
          sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
