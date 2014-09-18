package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullScreenPortraitSlideTest extends BaseTest{

    public void testFullScreenPortraitSlide(){
        solo.sleep(Resource.TIME_BIG);
        BackToTop.switchOrientation("Portrait", this);
        BackToTop.scrollToLeft(this);
         //打开全屏
        boolean isFullScreen = solo.getViewByPath("list_installed_plugin[0][0][0][0]").isSelected();
        if(!isFullScreen){
            solo.clickOnView(solo.getViewByPath("list_installed_plugin[0][0][0][0]"));
            assertTrue("FullScreen can not turn on.", solo.waitForText("Fullscreen is turned on"));
        }
        else{
            solo.goBack();
        }
        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        Log.i("test", "screenhight="+screenHeight);
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        //地址栏中输入关键字→进入搜索结果页面
        solo.clickOnView("title_design");
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);
        //选择搜索引擎
        solo.clickOnView(solo.getView("engine_item_img", 2));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "sun");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("go");
        solo.sleep(Resource.TIME_LONG);

        View FullScreenBtn = solo.getViewByPath("content_view[1][0]");
        assertTrue("Fullscreen按钮没有显示.", solo.waitForView(FullScreenBtn));
        assertTrue("Menubar没有隐藏", View.VISIBLE != solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        //拖动悬浮的全屏按钮
        int[] location = new int[2];
        FullScreenBtn.getLocationOnScreen(location);
        Log.i("test", "FullscreenBtn location:"+location[0]+"----"+location[1]);
        solo.drag(location[0], location[0], location[1], location[1] / 4, 100);
        solo.sleep(Resource.TIME_SMALL);
        FullScreenBtn.getLocationOnScreen(location);
        solo.drag(location[0], location[0], location[1], low , 100);
        solo.sleep(Resource.TIME_SMALL);

        //进行横竖屏切换
        BackToTop.switchOrientation("Landscape", this);
        solo.sleep(Resource.TIME_SMALL);
        BackToTop.switchOrientation("Portrait", this);
        solo.sleep(Resource.TIME_Middle);
        solo.drag(10, 10, low, high, 100);
        solo.sleep(Resource.TIME_SMALL);
        //点击悬浮的全屏按钮
        solo.clickOnView(FullScreenBtn);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("addressbar&tabbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        assertTrue("Menubar没有显示",View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());
        assertTrue("Fullscreen按钮没有隐藏", View.VISIBLE != FullScreenBtn.getVisibility());
        solo.sleep(Resource.TIME_SMALL);

        //向上滑动网页
        solo.drag(10, 10, low, high, 100);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("Menubar没有隐藏",View.VISIBLE != solo.getViewByPath("main_menubar_holder[0]").getVisibility());
        assertTrue("Fullscreen按钮没有显示", View.VISIBLE == FullScreenBtn.getVisibility());
        assertTrue("addressbar&tabbar没有隐藏",View.VISIBLE != solo.getViewByPath("center_screen[2]").getVisibility());
        //继续向下拉网页使地址栏/Tab Bar呼出→查看地址栏呼出过程的动画
        location = new int[2];
        do{
            solo.drag(10, 10, high, low, 100);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
    }
}
