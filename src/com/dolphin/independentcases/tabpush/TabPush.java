
package com.dolphin.independentcases.tabpush;

import java.util.ArrayList;
import java.util.List;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabPush {

    public static ArrayList<View> getAllViewByClassName(final String className, final Solo solo) {

        final ArrayList<View> currentViews = solo.getCurrentViews();

        ArrayList<View> results = new ArrayList<View>();

        for (View view : currentViews) {
            // Log.d("TEST", "class name: " + view.getClass().getName());
            if (view.getClass().getName().endsWith(className)) {
                results.add(view);
            }
        }
        return results;
    }
//首页点setting 是0
    public static void menuClickseeting1(int i, Solo solo) {

        solo.clickOnView(solo.getViewByPath("bottom_container[0][3]"));// 点击首页dolphin按钮
        solo.sleep(Resource.TIME_SMALL);

        ArrayList<View> al = getAllViewByClassName("PanelMenuTabBar", solo);

        solo.sleep(Resource.TIME_SMALL);

        switch (i) {

            case 0:

                final ViewGroup tabView = (ViewGroup) al.get(0);

                solo.clickOnView(tabView.getChildAt(0));

        }

        solo.sleep(Resource.TIME_SMALL);

    }
  //网页点setting 是0
    public static void menuClickseeting2(int i, Solo solo) {

        solo.clickOnView(solo.getViewByPath("main_menubar_holder[0][3]"));// 点击首页dolphin按钮
        solo.sleep(Resource.TIME_SMALL);

        ArrayList<View> al = getAllViewByClassName("PanelMenuTabBar", solo);

        solo.sleep(Resource.TIME_SMALL);

        switch (i) {

            case 0:

                final ViewGroup tabView = (ViewGroup) al.get(0);

                solo.clickOnView(tabView.getChildAt(0));

        }

        solo.sleep(Resource.TIME_SMALL);

    }
    /**
     * 长按图标并拖动 根据坐标拖动
     */
    public void clickLongAndDrag(float fromX, float toX, float fromY, float toY, int stepCount,Solo solo)
            throws Exception {
        Instrumentation inst = new Instrumentation();
        Log.d("TEST", "moving: fx: " + fromX + " fy: " + fromY + " toX: " + toX + " toY: " + toY);

        boolean successfull = false;
        int retry = 0;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, fromX,
                fromY, 0);

        while (!successfull && retry < 10) {
            try {
                inst.sendPointerSync(event);
                successfull = true;
            } catch (SecurityException e) {
                retry++;
            }
        }
        if (!successfull) {
            Assert.assertTrue("Click can not be completed!", false);
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, fromX + 1.0f,
                fromY + 1.0f, 0);
        inst.sendPointerSync(event);

        solo.sleep((int) (ViewConfiguration.getLongPressTimeout() * 2.5f));

        float y = fromY + 1.0f;
        float x = fromX + 1.0f;
        float yStep = (toY - fromY) / stepCount;
        float xStep = (toX - fromX) / stepCount;

        for (int i = 0; i < stepCount; ++i) {
            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, toX, toY, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }


    }


}
