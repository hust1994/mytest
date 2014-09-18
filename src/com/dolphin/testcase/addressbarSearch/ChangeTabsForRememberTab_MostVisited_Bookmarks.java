package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class ChangeTabsForRememberTab_MostVisited_Bookmarks extends Utils_AddressBarSearch{
    boolean flag = true;

    public void testChangeTabsForRememberTab(){
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= getCurrentTab() == 2;
        assertTrue("is not most visited tab after restart the browser", flag);

        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);


        flag &= getCurrentTab() == 1;


        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= getCurrentTab() == 1;

        assertTrue("is not bookmarks tab after click cancel", flag);


    }
}