package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullscreenLandscapeLoadPictureTest extends BaseTest{

    public void testFullscreenLandscapeLoadPicture(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        visitUrl("http://cn.bing.com/images/search?q=21&go=%E6%8F%90%E4%BA%A4&qs=n&form=QBIR&pq=21&sc=8-2&sp=-1&sk=");
        solo.sleep(Resource.TIME_LONG);

        //网页加载完成content_view【1】是全屏按钮，滑动到顶部后content_view【1】是backtotop按钮

        for(int i=0;i<2;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
        }

        solo.drag(10, 10, high, low, 2);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());

        for(int i=0;i<5;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
        }

        solo.drag(10, 10, high, low, 1);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
    }
}
