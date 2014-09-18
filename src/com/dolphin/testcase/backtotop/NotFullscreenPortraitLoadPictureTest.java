package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class NotFullscreenPortraitLoadPictureTest extends BaseTest{

    public void testNotFullscreenPortraitLoadPicture(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        visitUrl("http://cn.bing.com/images/search?q=21&go=%E6%8F%90%E4%BA%A4&qs=n&form=QBIR&pq=21&sc=8-2&sp=-1&sk=");
        solo.sleep(Resource.TIME_LONG);

        solo.drag(10, 10, low, high, 100);
        solo.sleep(Resource.TIME_SMALL);

        solo.drag(10, 10, high, low, 2);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮出现",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());

        for(int i=0;i<4;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
        }

        solo.drag(10, 10, high, low, 1);
        solo.sleep(Resource.TIME_Middle);
        Log.i("test",solo.getViewByPath("content_view[1]").toString());
        assertTrue("backtotop按钮没有显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
    }
}
