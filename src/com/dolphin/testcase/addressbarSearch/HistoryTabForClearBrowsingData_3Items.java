package com.dolphin.testcase.addressbarSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.util.Log;
import android.view.KeyEvent;
import com.adolphin.common.Resource;

public class HistoryTabForClearBrowsingData_3Items extends Utils_AddressBarSearch{
    String url = "www.gmail.com";
    boolean flag = true;
    String line = null;
    BufferedReader reader = null;

    public void testHistoryTabForClearBrowsingData_3Items(){
        solo.sleep(Resource.TIME_SMALL);

        clickOnAddressBar(0);
        if(solo.searchText("http")){
            Log.d("TEST","has baidu");
            solo.clickOnText("Clear browsing data");
            if(solo.waitForDialogToOpen()){
                boolean[] isChecked = validateInitClearHistoryState();
                if(!isChecked[0]) solo.clickOnText("Clear browsing history");
                if(!isChecked[1]) solo.clickOnText("Clear the cache");
                if(!isChecked[2]) solo.clickOnText("Clear cookies");
                solo.clickOnButton(4);
                solo.waitForText("Cleared");
            }
        }

        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        int[] fill = {KeyEvent.KEYCODE_TAB,
                KeyEvent.KEYCODE_M, KeyEvent.KEYCODE_I, KeyEvent.KEYCODE_A,
                KeyEvent.KEYCODE_PERIOD, KeyEvent.KEYCODE_B, KeyEvent.KEYCODE_A, KeyEvent.KEYCODE_I,
                KeyEvent.KEYCODE_N, KeyEvent.KEYCODE_A, KeyEvent.KEYCODE_AT,
                KeyEvent.KEYCODE_G, KeyEvent.KEYCODE_M, KeyEvent.KEYCODE_A, KeyEvent.KEYCODE_I, KeyEvent.KEYCODE_L,
                KeyEvent.KEYCODE_PERIOD, KeyEvent.KEYCODE_C, KeyEvent.KEYCODE_O, KeyEvent.KEYCODE_M,
                KeyEvent.KEYCODE_TAB,
                KeyEvent.KEYCODE_Z, KeyEvent.KEYCODE_X, KeyEvent.KEYCODE_C, KeyEvent.KEYCODE_1,
                KeyEvent.KEYCODE_2, KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_A,
                KeyEvent.KEYCODE_S, KeyEvent.KEYCODE_D,
                KeyEvent.KEYCODE_TAB, KeyEvent.KEYCODE_ENTER};
        AutoFill(fill);

        solo.sleep(Resource.TIME_BIG);
        solo.sleep(Resource.TIME_BIG);

        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }

        clickOnAddressBar(0);
        solo.clickOnText("Clear browsing data");
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(!isChecked[0]) solo.clickOnText("Clear browsing history");
            if(!isChecked[1]) solo.clickOnText("Clear the cache");
            if(!isChecked[2]) solo.clickOnText("Clear cookies");
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }

        flag &= !solo.searchText("Gmail") || solo.searchText("Webpage not available");
        try {
            solo.sleep(Resource.TIME_BIG);
            Process p = Runtime.getRuntime().exec("ls data/data/mobi.mgeek.TunnyBrowser");
            solo.sleep(Resource.TIME_SMALL);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                if(line.equals("cache")){
                    flag &= false;
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scroll(1, 20);

        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        flag &= solo.searchText("Sign in");
        assertTrue("clear history cache cookie failed", flag);

    }
}