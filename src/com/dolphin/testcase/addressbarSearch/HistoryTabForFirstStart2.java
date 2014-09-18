package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import android.view.View;

public class HistoryTabForFirstStart2 extends Utils_AddressBarSearch{

    boolean flag = true;
    public void testHistoryTabForFirstStart(){

        solo.sleep(2000);

        //History tab
        clickOnAddressBar(0);

        View bottom_continer = solo.getView("searchtab_bottom_continer");
        flag &= bottom_continer.getVisibility() != 0;

        Log.d("TEST", flag + "");

        assertTrue("the data is not empty for first start", flag);

    }
}




