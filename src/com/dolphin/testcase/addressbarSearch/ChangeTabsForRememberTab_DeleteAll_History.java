package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class ChangeTabsForRememberTab_DeleteAll_History extends Utils_AddressBarSearch{
    boolean flag = true;

    public void testChangeTabsForRememberTab(){
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= getCurrentTab() == 1;

        if(solo.searchText("Dolphin")){
            solo.clickOnText("Manage");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnView("btn_select_or_deselect_all");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnView("btn_remove_selected");
            solo.sleep(Resource.TIME_SMALL);
            if(solo.waitForDialogToOpen())
                solo.clickOnText("Confirm");
            solo.waitForText("Deleted");
            solo.goBack();
            solo.sleep(Resource.TIME_SMALL);
        }

        scroll(0, 20);
        if(solo.searchText("http"))
            clearHistory();
        flag &= getCurrentTab() == 0;

        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= getCurrentTab() == 0;

        scroll(1,20);

        assertTrue("is not history tab after click cancel", flag);
    }
}