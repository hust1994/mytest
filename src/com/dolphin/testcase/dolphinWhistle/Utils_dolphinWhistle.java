package com.dolphin.testcase.dolphinWhistle;

import java.util.ArrayList;

import android.inputmethodservice.Keyboard.Key;
import android.security.KeyChain;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.jayway.android.robotium.solo.Solo;

public class Utils_dolphinWhistle extends BaseTest {
    @Override
    public void tearDown() throws Exception {
        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.clickOnView("button3");
                break;
            }
        }
    }

    public void clickSetting() {
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_SMALL);
        ArrayList<View> al = getAllViewByClassName("PanelMenuTabBar", solo);
        solo.sleep(Resource.TIME_SMALL);

        final ViewGroup tabView = (ViewGroup) al.get(0);
        solo.clickOnView(tabView.getChildAt(0));
        solo.sleep(Resource.TIME_SMALL);
    }

    public static ArrayList<View> getAllViewByClassName(final String className,
            final Solo solo) {
        final ArrayList<View> currentViews = solo.getCurrentViews();
        ArrayList<View> results = new ArrayList<View>();
        for (View view : currentViews) {
            if (view.getClass().getName().endsWith(className)) {
                results.add(view);
            }
        }
        return results;
    }

    public void openUXImprovementProgram() {
        clickSetting();
        solo.clickOnText("About Dolphin", 1, true);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("UX improvement program", 1, true);
        solo.sleep(Resource.TIME_SMALL);
    }

    public boolean isUXChecked() {
        CheckBox cb = (CheckBox) solo.getView("id/normal_data_track_checkbox");
        return cb.isChecked();
    }

    public void letWhistleShown() {
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay()
                .getHeight();
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay()
                .getWidth();
        while (true) {
            solo.drag(screenWidth - 1, screenWidth - 40, screenHeight / 2,
                    screenHeight / 2, 50);
            solo.sleep(Resource.TIME_BIG);
            if (solo.searchText("Drag up to access Gesture & Sonar"))
                break;
        }
    }

    public void goBackToHomepage() {
        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }
    }
}