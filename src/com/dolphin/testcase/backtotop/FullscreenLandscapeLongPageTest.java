package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullscreenLandscapeLongPageTest extends BaseTest {

    //在第一次网页向下滚动又滚动会顶端之后才有backtotop这个空间  id是content_view【2】，全屏按钮是content_view【1】【0】
    //在backtotop第一次出现之后  backtotop按钮id变成content_view【1】，全屏按钮变成content_view[2][0]

    public void testFullscreenLandscapeLongPage() {

        solo.sleep(Resource.TIME_BIG);
        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;

        View BackwardBtn = solo.getViewByPath("address_go_back");
        int[] BackwardBtnloc = new int[2];
        BackwardBtn.getLocationOnScreen(BackwardBtnloc);
        Log.i("test", "BackwardBtnloc" + BackwardBtnloc[0] + "---"
                + BackwardBtnloc[1]);
        View ForwardBtn = solo.getViewByPath("address_go_forward");
        int[] ForwardBtnloc = new int[2];
        ForwardBtn.getLocationOnScreen(ForwardBtnloc);
        Log.i("test", "ForwardBtnloc" + ForwardBtnloc[0] + "---"
                + ForwardBtnloc[1]);
        View HomeBtn = solo.getViewByPath("address_home_button");
        int[] HomeBtnloc = new int[2];
        HomeBtn.getLocationOnScreen(HomeBtnloc);
        Log.i("test", "HomeBtnloc" + HomeBtnloc[0] + "---" + HomeBtnloc[1]);
        View TabListBtn = solo.getViewByPath("address_open_tablist");
        int[] TabListBtnloc = new int[2];
        TabListBtn.getLocationOnScreen(TabListBtnloc);
        Log.i("test", "TabListBtnloc" + TabListBtnloc[0] + "---"
                + TabListBtnloc[1]);

        BackToTop.addnewwindows(1, this);
        solo.sleep(Resource.TIME_SMALL);

        // 地址栏中输入关键字sun点击搜索按钮进入搜索结果页面
        solo.clickOnView("title_design");
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        //选择搜索引擎
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getView("engine_item_img", 3));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "sun");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("go");
        solo.sleep(Resource.TIME_LONG);

        // 下滑显示完整的tabbar和addressbar以获取刷新按钮的正确位置
        solo.drag(10, 10, high, low, 100);
        solo.sleep(Resource.TIME_SMALL);
        int tabbarheight = solo.getViewByPath("content_view[0][0][0][0]")
                .getHeight();
        View RefreshBtn = solo.getViewByPath("refresh_stop_btn[0]");
        int[] RefreshBtnloc = new int[2];
        RefreshBtn.getLocationOnScreen(RefreshBtnloc);
        Log.i("test", "refresh_stop_btn location:" + RefreshBtnloc[0] + "---"
                + RefreshBtnloc[1]);

        // 向上滑动使tabbar隐藏
        solo.drag(10, 10, low / 2, high / 2, 100);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有显示",
                View.VISIBLE == solo.getViewByPath("content_view[2][0]")
                        .getVisibility());

        // 向上滑到网页底部
        for (int i = 0; i < 8; i++) {
            solo.drag(10, 10, low, high, 30);
            solo.sleep(Resource.TIME_SMALL);
            assertTrue("全屏按钮没有显示",
                    View.VISIBLE == solo.getViewByPath("content_view[1][0]")
                            .getVisibility());
        }

        // 手指按照两行文字移动的速度慢慢向下滑动，直到慢慢到达网页顶部
        solo.drag(10, 10, high * 2, low, 200);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有显示",
                View.VISIBLE == solo.getViewByPath("content_view[1][0]")
                        .getVisibility());
        int[]location = new int[2];
        do{
            solo.drag(10, 10, high, low, 200);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
        assertTrue("全屏按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[1][0]").getVisibility());

        // 手指向上稍微滑动
        solo.drag(10, 10, low, high, 200);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("全屏按钮没有显示",
                View.VISIBLE == solo.getViewByPath("content_view[1][0]")
                        .getVisibility());

        // 手指变为快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        for(int i=0;i<4;i++){
            solo.drag(10,10,low,high,50);
            solo.sleep(Resource.TIME_SMALL);
        }
        solo.drag(10, 10, high, low, 1);
        solo.sleep(Resource.TIME_Middle);
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());

        // 继续下滑到网页顶部
        do{
            solo.drag(10, 10, high, low, 50);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);

        BackToTop.ShowBackToTopBtn(this);
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        // 手指慢慢向上滑动屏幕→当出现的地址栏/Tab Bar整体高度小于一半被屏幕隐藏后松手（先获取tabbar的高度在进行滑动，兼容）
        solo.drag(10, 10, screenHeight / 2, (screenHeight / 2)
                - (tabbarheight / 4), 200);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        // 手指再继续慢慢向上滑动屏幕→使地址栏/Tab Bar整体高度超过一半被屏幕隐藏后松手
        solo.drag(10, 10, screenHeight / 2, (screenHeight / 2)
                - (tabbarheight / 3 * 2), 200);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有隐藏", View.VISIBLE != solo.getViewByPath("center_screen[3]").getVisibility());
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        assertTrue("全屏按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        // 继续向下滑动直至网页顶部
        do{
            solo.drag(10, 10, high, low, 50);
            solo.sleep(Resource.TIME_SMALL);
            solo.getViewByPath("content_view[0][0][0][0]").getLocationOnScreen(location);
            Log.i("test","location[1] "+location[1]);
        }while(location[1] < 0);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());

        // 手指向上滑动→当网页滑到超过2屏的时候停止
        // 手指开始快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        BackToTop.ShowBackToTopBtn(this);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("全屏按钮没有隐藏",View.VISIBLE != solo.getViewByPath("content_view[2][0]").getVisibility());
        // 等待10S
        solo.sleep(10000);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        // 点击back to top 按钮
        solo.clickOnView(solo.getViewByPath("content_view[1]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        // 手指向上滑动→当网页滑到超过2屏的时候停止
        // 手指开始快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        assertTrue("tabbar&addressbar没有显示", View.VISIBLE == solo.getViewByPath("center_screen[3]").getVisibility());
        // 点击Menu中的后退按钮或者是硬件的后退按钮
        Log.i("test", "BackwardBtnloc" + BackwardBtnloc[0] + "---"
                + BackwardBtnloc[1]);
        solo.clickOnScreen(BackwardBtnloc[0] + 10, BackwardBtnloc[1] + 10);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 再次点击前进按钮
        Log.i("test", "ForwardBtnloc" + ForwardBtnloc[0] + "---"
                + ForwardBtnloc[1]);
        solo.clickOnScreen(ForwardBtnloc[0], ForwardBtnloc[1]);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 再次使Back to top按钮呼出
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        // 切换到另一个Tab
        solo.clickOnScreen(TabListBtnloc[0], TabListBtnloc[1]);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getViewByPath("horizontal_item_container[0]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 然后再切换回来
        solo.clickOnScreen(TabListBtnloc[0], TabListBtnloc[1]);
        solo.clickOnView(solo.getViewByPath("horizontal_item_container[1]"));
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 再次使Back to top按钮呼出
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        // 点击Menu中的Home按钮
        solo.clickOnScreen(HomeBtnloc[0], HomeBtnloc[1]);
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 再次从Homepage返回
        while (ForwardBtn.isEnabled()) {
            solo.clickOnScreen(ForwardBtnloc[0], ForwardBtnloc[1]);
            solo.sleep(Resource.TIME_SMALL);
        }
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
        // 再次快速滑动网页使Back to top显示
        BackToTop.ShowBackToTopBtn(this);
        assertTrue("backtotop按钮没有显示", View.VISIBLE == solo.getViewByPath("content_view[1]").getVisibility());
        // 刷新网页
        solo.clickOnScreen(RefreshBtnloc[0], RefreshBtnloc[1]);
        solo.sleep(Resource.TIME_LONG);
        assertTrue("backtotop按钮没有隐藏", View.VISIBLE != solo.getViewByPath("content_view[1]").getVisibility());
    }
}
