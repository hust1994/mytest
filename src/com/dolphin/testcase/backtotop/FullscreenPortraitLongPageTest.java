package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullscreenPortraitLongPageTest extends BaseTest{

    public void testFullscreenPortraitLongPage(){
        solo.sleep(Resource.TIME_BIG);
        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        BackToTop.addnewwindows(1,this);
        solo.sleep(Resource.TIME_SMALL);

        //地址栏中输入关键字sun点击搜索按钮进入搜索结果页面
        solo.clickOnView("title_design");
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);
        //选择搜索引擎
        solo.clickOnView(solo.getView("engine_item_img", 3));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "sun");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("go");
        solo.sleep(Resource.TIME_LONG);

        //获取刷新按钮的正确位置
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        View RefreshBtn = solo.getViewByPath("refresh_stop_btn[0]");
        int[] refreshbtn_xy = new int[2];
        RefreshBtn.getLocationOnScreen(refreshbtn_xy);
        solo.sleep(Resource.TIME_SMALL);
        int tabbarheight = solo.getViewByPath("content_view[0][0][0][0]").getHeight();

        //向上滑动使tabbar隐藏(部分网页加载完成会自动隐藏tabbar)
        solo.drag(10, 10, low / 2, high / 2, 100);
        solo.sleep(Resource.TIME_SMALL);
        String[]view = solo.getViewByPath("content_view[1][0]").toString().split("@");
        Log.i("test",view[0]);
        if(view[0].toString().equals("com.dolphin.browser.ui.v".toString()))
        {
            Log.i("test",solo.getViewByPath("content_view[2][0]").toString());
            assertTrue("全屏按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[2][0]").getVisibility());
        }
        else{
            assertTrue("全屏按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1][0]").getVisibility());
        }
        assertTrue("菜单栏没有隐藏", View.VISIBLE != solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        //滑到网页底部
        for(int i=0;i<8;i++){
            solo.drag(10, 10, low, high, 100);
            solo.sleep(Resource.TIME_SMALL);
            view = solo.getViewByPath("content_view[1][0]").toString().split("@");
            Log.i("test",view[0]);
            if(view[0].toString().equals("com.dolphin.browser.ui.v".toString()))
            {
                Log.i("test",solo.getViewByPath("content_view[2][0]").toString());
                assertTrue("全屏按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[2][0]").getVisibility());
            }
            else{
                assertTrue("全屏按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1][0]").getVisibility());
            }
            assertTrue("菜单栏没有隐藏", View.VISIBLE != solo.getViewByPath("main_menubar_holder[0]").getVisibility());
        }

        //手指按照两行文字移动的速度慢慢向下滑动（速度小于1000px/s）直到慢慢到达网页顶部
        int[]location = new int[2];
        do{
            solo.drag(10, 10, high, low, 200);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        //手指向上稍微滑动
        solo.drag(10, 10, low, high, 200);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("菜单栏没有隐藏", View.VISIBLE != solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        //手指变为快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        //BackToTop.ShowBackToTopBtn(this);
        for(int i=0;i<4;i++){
            solo.drag(10,10,low,high,50);
            solo.sleep(Resource.TIME_SMALL);
        }
        solo.drag(10, 10, high, low, 1);
        solo.sleep(Resource.TIME_Middle);
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        do{
            solo.drag(10, 10, high, low, 50);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
        assertTrue("全屏按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        solo.drag(10, 10, low,high, 30);
        solo.sleep(Resource.TIME_SMALL);
        solo.drag(10, 10, low,high, 30);
        solo.sleep(Resource.TIME_SMALL);
        solo.drag(10, 10, low,high, 30);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有隐藏",View.VISIBLE != solo.getViewByPath("center_screen[3]").getVisibility());
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());

        //手指慢慢向上滑动屏幕→当出现的地址栏/Tab Bar整体高度小于一半被屏幕隐藏后松手（先获取tabbar的高度在进行滑动，兼容）
        solo.drag(10, 10, screenHeight / 2, (screenHeight / 2)- (tabbarheight / 4), 100);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        //手指再继续慢慢向上滑动屏幕→使地址栏/Tab Bar整体高度超过一半被屏幕隐藏后松手
        solo.drag(10, 10, screenHeight / 2, (screenHeight / 2)- (tabbarheight / 3 * 2), 100);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有隐藏",View.VISIBLE != solo.getViewByPath("center_screen[3]").getVisibility());
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("全屏按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());
        //继续向下滑动直至网页顶部
        do{
            solo.drag(10,10,high,low,50);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
        assertTrue("全屏按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());
        //手指向上滑动→当网页滑到超过2屏的时候停止
        //手指开始快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        BackToTop.ShowBackToTopBtn(this);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("全屏按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        assertTrue("菜单栏没有显示", View.VISIBLE == solo.getViewByPath("main_menubar_holder[0]").getVisibility());

        View BackwardBtn = solo.getViewByPath("main_menubar_holder[0][0]");
        View ForwardBtn = solo.getViewByPath("main_menubar_holder[0][1]");
        View HomeBtn = solo.getViewByPath("main_menubar_holder[0][4]");
        View TabListBtn = solo.getViewByPath("main_menubar_holder[0][5]");

        //等待10S
        solo.sleep(10000);

        //点击back to top 按钮
        solo.clickOnView(solo.getViewByPath("content_view[1]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        //手指向上滑动→当网页滑到超过2屏的时候停止
        //手指开始快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有 显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("tabbar&addressbar没有显示",View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        //点击Menu中的后退按钮或者是硬件的后退按钮
        solo.clickOnView(BackwardBtn);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //再次点击前进按钮
        solo.clickOnView(ForwardBtn);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //再次使Back to top按钮呼出
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有 显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        //切换到另一个Tab
        solo.clickOnView(TabListBtn);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getViewByPath("vertical_item_container[1]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //然后再切换回来
        solo.clickOnView(TabListBtn);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getViewByPath("vertical_item_container[2]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //再次使Back to top按钮呼出
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有 显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        //点击Menu中的Home按钮
        solo.clickOnView(HomeBtn);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //再次从Homepage返回
        while(ForwardBtn.isEnabled()){
            solo.clickOnView(ForwardBtn);
            solo.sleep(Resource.TIME_SMALL);
        }
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        //再次快速滑动网页使Back to top显示
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有 显示",View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        //刷新网页
        Log.i("test", refreshbtn_xy[0]+"---"+refreshbtn_xy[1]);
        float[]xy = BackToTop.GetViewCenter(this, RefreshBtn, refreshbtn_xy);
        solo.clickOnScreen(xy[0], xy[1]);
        solo.sleep(Resource.TIME_LONG);
        assertTrue("backtotop按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
    }
}
