package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class NotFullscreenLandscapeLoadPictureTest extends BaseTest{

    public void testNotFullscreenLandscapeLoadPicture(){
        solo.sleep(Resource.TIME_BIG);

        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        visitUrl("http://cn.bing.com/images/search?q=21&go=%E6%8F%90%E4%BA%A4&qs=n&form=QBIR&pq=21&sc=8-2&sp=-1&sk=");
        solo.sleep(Resource.TIME_LONG);

        for(int i=0;i<2;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
        }

        solo.drag(10, 10, high, low, 2);
        solo.sleep(Resource.TIME_SMALL);
        String[] view = solo.getViewByPath("id/content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0] == "com.dolphin.browser.ui.v"){
            assertTrue("backtoptop按钮显示", View.VISIBLE != solo.getViewByPath("content_view[2]").getVisibility());
        }
        for(int i=0;i<5;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
        }

        solo.drag(10, 10, high, low, 1);
        solo.sleep(Resource.TIME_Middle);
        view = solo.getViewByPath("id/content_view[2]").toString().split("@");
        Log.i("test",view[0]);
        assertTrue("backtoptop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[2]").getVisibility());
    }
}
