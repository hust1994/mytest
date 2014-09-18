package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.view.View;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.adolphin.utils.DisplayManager;

public class FullscreenPortraitShortPageTest extends BaseTest{

    public void testFullscreenPortraitShortPage(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        //打开一个短网页（www.soso.com）-> 快速上下滑动
        visitUrl("www.soso.com");
        solo.sleep(Resource.TIME_LONG);

        //快速下滑动
        solo.drag(10, 10, high, low, 5);
        solo.sleep(Resource.TIME_SMALL);
        assertFalse("notification bar没有隐藏", DisplayManager.getStatusBarHeight(solo.getCurrentActivity()) > 0);
        assertFalse("tabbar&addressbar没有显示", solo.getViewByPath("content_view[0][0][0][0]").getTop() < 0);
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        //向上滑动网页
        solo.drag(10, 10, low, high, 50);
        solo.sleep(Resource.TIME_SMALL);
        assertFalse("notification bar没有隐藏", DisplayManager.getStatusBarHeight(solo.getCurrentActivity()) > 0);
        assertFalse("tabbar&addressbar没有显示", solo.getViewByPath("content_view[0][0][0][0]").getTop() < 0);
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        //缓慢下滑动
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        assertFalse("notification bar没有隐藏", DisplayManager.getStatusBarHeight(solo.getCurrentActivity()) > 0);
        assertFalse("tabbar&addressbar没有显示", solo.getViewByPath("content_view[0][0][0][0]").getTop() < 0);
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
    }
}
