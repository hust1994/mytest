package com.dolphin.testcase.addressbarSearch;

import android.view.KeyEvent;
import com.adolphin.common.Resource;

public class HistoryTabForClearBrowsingData_Cookies extends Utils_AddressBarSearch{

    String url = "www.gmail.com";
    boolean flag = true;

    public void testHistoryTabForClearBrowsingData_Cookies(){
        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
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
        clickThePopUpBox();
        clickThePopUpBox();

        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }

        clickOnAddressBar(0);
        clearCookies();
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        flag &= solo.searchText("Sign in");
        assertTrue("clear cookies failed", flag);


    }

}

