package com.dolphin.testcase.dolphinWhistle;

import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.Resource;

public class ClickCancel1 extends Utils_dolphinWhistle {

    boolean flag = true;

    public void testClickCancel() {
        waitMainActivity();
        solo.sleep(10000);

        letWhistleShown();

        solo.goBack();
        solo.sleep(Resource.TIME_BIG);

        flag &= solo
                .waitForText("Help me out. Join the UX improvement program!");
        assertTrue("there is no whistle after install a new browser", flag);

        solo.sleep(5 * 60 * 1000);

        flag &= solo
                .searchText("Help me out. Join the UX improvement program!");
        assertTrue("the whistle dismiss after a long time", flag);

        // 点击X
        solo.clickOnView("id/hbtn_cancel");
        solo.sleep(Resource.TIME_BIG);
    }
}