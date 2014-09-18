package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullscreenLandscapeShortPageTest extends BaseTest{

    public void testFullscreenLandscapeShortPage(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        //打开一个短网页（www.soso.com）-> 快速上下滑动
        visitUrl("www.soso.com");
        solo.sleep(Resource.TIME_LONG);
        assertTrue("全屏按钮没有显示.", View.VISIBLE == solo.getViewByPath("content_view[2][0]").getVisibility());

        //网页加载完成无任何操作时   全屏按钮 content_view[2][0] 向上滑动到顶后全屏按钮content_view[3][0]
        //网页加载完成无任何操作时   backtotop按钮 content_view[3] 向上滑动到顶后backtotop按钮content_view[3]

        //快速下滑动
        solo.drag(10, 10, high, low, 5);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有消失.", View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[3]").getVisibility());
        //向上滑动网页
        solo.drag(10, 10, low, high, 50);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[3][0]").getVisibility());
        //缓慢下滑动
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有消失.", View.VISIBLE != solo.getViewByPath("content_view[3][0]").getVisibility());
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
    }
}
