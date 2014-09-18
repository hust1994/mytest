package com.dolphin.testcase.backtotop;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class BackToTop {

    /*
     * 呼出backtotop按钮
     */
    public static void ShowBackToTopBtn(BaseTest test){
        Point screen = new Point();
        test.getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        int screenHeight = screen.y;
        float high = screenHeight / 6;
        float low = screenHeight * 5 / 6;
        //手指向上滑动→当网页滑到超过2屏的时候停止
        test.solo.drag(10,10,low,high,50);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.drag(10,10,low,high,50);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.drag(10,10,low,high,50);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.drag(10,10,low,high,50);
        test.solo.sleep(Resource.TIME_Middle);
        //手指开始快速地向下滑动，使网页在瞬间移动了半屏的位移（速度1000px/s）
        test.solo.drag(10, 10, high, low, 10);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    public static float[] GetViewCenter(BaseTest test ,View view,int []location){
        float[] xy = new float[2];
        final int viewWidth = view.getWidth();
        final int viewHeight = view.getHeight();
        xy[0] = location[0] + (viewWidth / 2.0f);
        xy[1] = location[1] + (viewHeight / 2.0f);
        Log.i("test", xy[0]+"---"+xy[1]);
//		// 计算缩放比例，将要点击的x, y坐标恢复到缩放前的情况。
//		Activity activity = test.solo.getCurrentActivity();
//		DisplayMetrics rdm = activity.getResources().getDisplayMetrics();
//		DisplayMetrics wdm = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(wdm);
//		Log.i("test", wdm.scaledDensity +"---"+rdm.scaledDensity);
//		xy[0] *= wdm.scaledDensity / rdm.scaledDensity;
//		xy[1] *= wdm.scaledDensity / rdm.scaledDensity;
//		Log.i("test", xy[0]+"---"+xy[1]);
        return xy;
    }

    public static void switchOrientation(String text,BaseTest test){
        //----modified------
        //SettingsEnter.enterSettingScreen(test);
        test.solo.sendKey(KeyEvent.KEYCODE_MENU);
        test.solo.sleep(Resource.TIME_Middle);
        ViewGroup viewGroup = null;

        while(viewGroup == null) {
            viewGroup = (ViewGroup)test.getViewByClassName("PanelMenuTabBar", 1);
        }
        viewGroup = (ViewGroup)viewGroup.getChildAt(0);
        View view = viewGroup.getChildAt(0);
        test.solo.clickOnView(view);
        //----modified end------
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Customize");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText("Orientation");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnText(text);
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("btn_done");
        test.solo.sleep(Resource.TIME_SMALL);
        test.solo.clickOnView("btn_done");
        test.solo.sleep(Resource.TIME_SMALL);
    }

    public static void scrollToLeft(BaseTest test) {
        Display display = test.getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        //此处drag的第一个参数需要注意，如果填width可能会划不动
        test.solo.drag(width-30, 0, height/2, height/2, 10);
        test.solo.sleep(Resource.TIME_SMALL);
    }

    /**
     * 前置：通过选择新建窗口的个数，执行新建窗口操作
     *      只能在home页新建窗口
     * @param number
     *           新建窗口数量
     */
    public static void addnewwindows(int number, BaseTest test){
        int i = 1;
        do {
            test.solo.clickOnView(test.solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/top_container[0][0][1]"));
            i++;
            test.solo.sleep(Resource.TIME_SMALL);
        }
            while(i<=number);
    }
}
