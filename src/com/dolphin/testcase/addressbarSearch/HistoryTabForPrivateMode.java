package com.dolphin.testcase.addressbarSearch;

import android.view.View;
import com.adolphin.common.Resource;

public class HistoryTabForPrivateMode extends Utils_AddressBarSearch{
    String searchContent1 = "test";
    String searchContent2 = "bing.com";
    String historyUrl = "baidu.com";
    boolean flag = true;

    public void testVisitWebWithPrivateMode(){
        solo.sleep(Resource.TIME_SMALL);
        clickOnAddressBar(0);
        if(solo.getView("searchtab_bottom_continer").getVisibility() == 0)
            clearHistory();

        goBackToHomepage();

        typeUrlAndVisit(historyUrl);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        clickOnAddressBar(0);
        flag &= solo.searchText("百度一下");
        assertTrue("Does not have history for baidu with private mode off", flag);

        goBackToHomepage();

        View privateModeIcon1 = getPrivateModeView();
        solo.clickOnView(privateModeIcon1);
        if(solo.waitForDialogToOpen()){
            solo.clickOnText("Yes");
        }
        solo.waitForText("Private Browsing is turned on");

        typeUrlAndVisit(searchContent1);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit(searchContent2);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        clickOnAddressBar(0);

        flag &= !solo.searchText(searchContent1) && !solo.searchText(searchContent2);


        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        View privateModeIcon2 = getPrivateModeView();
        solo.clickOnView(privateModeIcon2);
        if(solo.waitForText("Private Mode")){
            solo.clickOnText("Yes");
        }
        solo.waitForText("Private Browsing is turned off");

        clickOnAddressBar(0);
        clearHistory();
        assertTrue("has history in private mode", flag);
    }
}