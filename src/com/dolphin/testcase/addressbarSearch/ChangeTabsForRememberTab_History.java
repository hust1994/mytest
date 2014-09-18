package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class ChangeTabsForRememberTab_History extends Utils_AddressBarSearch{
    String url = "baidu.com";
    boolean flag = true;

    public void testChangeTabsForRememberTab(){

        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        while (true) {
              solo.goBack();
              solo.sleep(1000);
              if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                  solo.goBack();
                  break;
              }
        }

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= getCurrentTab() == 1;
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        flag &= getCurrentTab() == 0;


        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= getCurrentTab() == 0;

        assertTrue("is not history tab after click cancel", flag);
    }
}