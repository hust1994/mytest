package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class NotFullscreenPortraitShortPageTest extends BaseTest{

    public void testNotFullscreenPortraitShortPage(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        //打开一个短网页
        visitUrl("www.soso.com");
        solo.sleep(Resource.TIME_LONG);

        View AddressBar = solo.getViewByPath("content_view[0][0][0][0]");
        View MenuBar = solo.getViewByPath("main_menubar_holder[0]");

        //快速上下滑动
        solo.drag(10, 10, high, low, 5);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("地址栏/tab栏没有显示。", View.VISIBLE == AddressBar.getVisibility());
        assertTrue("菜单栏没有显示。", View.VISIBLE == MenuBar.getVisibility());
        String[] view = solo.getViewByPath("content_view[1]").toString().split("@");
        if(view[0].toString().equals("com.dolphin.browser.ui.v".toString())){
            Log.i("test",view[0]);
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        }
        Log.i("test","mtop:"+AddressBar.getTop());
        assertFalse("tabbar&地址栏隐藏", AddressBar.getTop() < 0);

        solo.drag(10, 10, low, high, 5);
        solo.sleep(Resource.TIME_SMALL);
        Log.i("test","mtop:"+AddressBar.getTop());
        assertFalse("tabbar&地址栏隐藏", AddressBar.getTop() < 0);
        assertTrue("菜单栏没有显示。", View.VISIBLE == MenuBar.getVisibility());
        view = solo.getViewByPath("content_view[1]").toString().split("@");
        if(view[0].toString().equals("com.dolphin.browser.ui.v".toString())){
            Log.i("test",view[0]);
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        }

        //缓慢上下滑动
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        Log.i("test","mtop:"+AddressBar.getTop());
        assertFalse("tabbar&地址栏隐藏", AddressBar.getTop() < 0);
        assertTrue("菜单栏没有显示。", View.VISIBLE == MenuBar.getVisibility());
        view = solo.getViewByPath("content_view[1]").toString().split("@");
        if(view[0].toString().equals("com.dolphin.browser.ui.v".toString())){
            Log.i("test",view[0]);
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        }
        solo.drag(10, 10, low, high, 100);
        solo.sleep(Resource.TIME_SMALL);
        Log.i("test","mtop:"+AddressBar.getTop());
        assertFalse("tabbar&地址栏隐藏", AddressBar.getTop() < 0);
        assertTrue("菜单栏没有显示。", View.VISIBLE == MenuBar.getVisibility());
        view = solo.getViewByPath("content_view[1]").toString().split("@");
        if(view[0].toString().equals("com.dolphin.browser.ui.v".toString())){
            Log.i("test",view[0]);
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        }
    }
}
