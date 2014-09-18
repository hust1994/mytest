package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import com.adolphin.common.Resource;

public class ChangeTabsForOrientationShow extends Utils_AddressBarSearch{
    String url = "baidu.com";
    boolean flag = true;

    public void testChangeTabsForOrientationShow(){
        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALLEST);

        flag &= solo.searchText("Dolphin") && solo.searchText("Wikipedia");
        Log.d("TEST", flag + "");
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("百度一下") || solo.searchText("Webpage not available");
        scroll(1, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("Dolphin") && solo.searchText("Wikipedia");
        scroll(1, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("百度一下") || solo.searchText("Webpage not available");

        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }

        clickSetting();
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Orientation");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Landscape");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALLEST);

        flag &= solo.searchText("百度一下") || solo.searchText("Webpage not available");
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("Dolphin") && solo.searchText("Wikipedia");
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("百度一下") || solo.searchText("Webpage not available");

        solo.clickOnText("Clear browsing data");
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(!isChecked[0]) solo.clickOnText("Clear browsing history");
            if(!isChecked[1]) solo.clickOnText("Clear the cache");
            if(!isChecked[2]) solo.clickOnText("Clear cookies");
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }
        scroll(1, 20);

        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }

        clickSetting();
        solo.clickOnText("Customize", 1, true);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Orientation");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Portrait");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        assertTrue("orientaition show failed", flag);

    }
}