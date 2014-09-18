package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class NotFullscreenLandscapeShortPageTest extends BaseTest{

    public void testNotFullscreenLandscapeShortPage(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        //打开一个短网页
        visitUrl("www.soso.com");
        solo.sleep(Resource.TIME_LONG);

        //快速上下滑动
        solo.drag(10, 10, high, low, 5);
        solo.sleep(Resource.TIME_SMALL);
        String[] view = solo.getViewByPath("content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0] == "com.dolphin.browser.ui.v"){
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        }

        solo.drag(10, 10, low, high, 5);
        solo.sleep(Resource.TIME_SMALL);
        view = solo.getViewByPath("content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0] == "com.dolphin.browser.ui.v"){
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        }
        //缓慢上下滑动
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        view = solo.getViewByPath("content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0] == "com.dolphin.browser.ui.v"){
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        }
        solo.drag(10, 10, low, high, 100);
        solo.sleep(Resource.TIME_SMALL);
        view = solo.getViewByPath("content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0] == "com.dolphin.browser.ui.v"){
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        }
    }
}
