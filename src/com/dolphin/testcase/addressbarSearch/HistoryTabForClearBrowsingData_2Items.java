package com.dolphin.testcase.addressbarSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.util.Log;
import com.adolphin.common.Resource;

public class HistoryTabForClearBrowsingData_2Items extends Utils_AddressBarSearch{

    String url = "baidu.com";
    boolean flag = true;
    String line = null;
    BufferedReader reader = null;

    public void testHistoryTabForClearBrowsingData_2Items(){
        clickOnAddressBar(0);
        if(solo.searchText("百度一下")){
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

        clickOnAddressBar(0);
        flag &= solo.searchText("百度一下") || solo.searchText("Webpage not available");
        Log.d("TEST",flag + " ");
        try {
            solo.sleep(Resource.TIME_BIG);
            Process p = Runtime.getRuntime().exec("ls data/data/mobi.mgeek.TunnyBrowser");
            solo.sleep(Resource.TIME_SMALL);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                if(line.equals("cache")){
                    flag &= true;
                    Log.d("TEST",flag + " ");
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("does not have history and cache", flag);

        solo.clickOnText("Clear browsing data");
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(!isChecked[0]) solo.clickOnText("Clear browsing history");
            if(!isChecked[1]) solo.clickOnText("Clear the cache");
            if(isChecked[2]) solo.clickOnText("Clear cookies");
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }

        flag &= solo.getView("searchtab_bottom_continer").getVisibility() != 0;
        Log.d("TEST",flag + " ");
        try {
            solo.sleep(Resource.TIME_BIG);
            Process p = Runtime.getRuntime().exec("ls data/data/mobi.mgeek.TunnyBrowser");
            solo.sleep(Resource.TIME_SMALL);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                if(line.equals("cache")){
                    flag &= false;
                    Log.d("TEST",flag + " ");
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scroll(1, 20);
        assertTrue("does not have history and cache", flag);


    }

}