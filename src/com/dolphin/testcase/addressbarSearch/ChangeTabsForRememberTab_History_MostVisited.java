package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class ChangeTabsForRememberTab_History_MostVisited extends Utils_AddressBarSearch{
    boolean flag = true;

    public void testChangeTabsForRememberTab(){
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= getCurrentTab() == 0;
        assertTrue("is not history tab after restart the browser", flag);

        scroll(1, 20);
        scroll(1, 20);

        flag &= getCurrentTab() == 2;


        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= getCurrentTab() == 2;

        assertTrue("is not most visited tab after click cancel", flag);


    }
}