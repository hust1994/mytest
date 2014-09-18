package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class SearchSwitch extends Utils_AddressBarSearch{
    String url1 = "youtube";
    String url2 = "yahoo";
    boolean flag = true;

    public void testSearchSwitch(){
        solo.sleep(Resource.TIME_SMALL);
        clickSetting();
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Show search suggestions", 1, true);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "y");
        solo.sleep(Resource.TIME_SMALL);
        flag &= !solo.searchText(url1) && !solo.searchText(url2);
        assertTrue("has suggestion with switch closed", flag);

        goBackToHomepage();

        clickSetting();
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Show search suggestions", 1, true);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "y");
        solo.sleep(Resource.TIME_BIG);
        flag &= solo.searchText(url1) || solo.searchText(url2);
        assertTrue("has not suggestion with switch open", flag);
    }
}