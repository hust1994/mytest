package com.dolphin.testcase.dolphinWhistle;

import com.adolphin.common.Resource;

public class ThirdInstall extends Utils_dolphinWhistle{

    boolean flag = true;
    public void testThirdInstall(){
        waitMainActivity();
        solo.sleep(10000);

        letWhistleShown();

        solo.goBack();
        solo.sleep(Resource.TIME_BIG);

        flag &= solo.searchText("Help me out. Join the UX improvement program!");
        assertTrue("the whistle dismiss after a long time", flag);
    }
}