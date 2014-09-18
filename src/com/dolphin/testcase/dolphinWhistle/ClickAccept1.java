package com.dolphin.testcase.dolphinWhistle;

import com.adolphin.common.Resource;

public class ClickAccept1 extends Utils_dolphinWhistle{

    boolean flag = true;
    public void testClickCancel(){
        waitMainActivity();
        solo.sleep(10000);

        letWhistleShown();

        solo.goBack();
        solo.sleep(60 * 1000);

        flag &= solo.searchText("Help me out. Join the UX improvement program!");
        assertTrue("the whistle dismiss after a long time", flag);

        //点击√
        solo.clickOnView("id/hbtn_accept");
        solo.sleep(Resource.TIME_SMALL);
    }
}