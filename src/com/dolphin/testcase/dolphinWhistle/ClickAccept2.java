package com.dolphin.testcase.dolphinWhistle;

import com.adolphin.common.Resource;

public class ClickAccept2 extends Utils_dolphinWhistle{

    boolean flag = true;
    public void testClickCancel(){
        waitMainActivity();
        solo.sleep(Resource.TIME_BIG);

        flag &= !solo.searchText("Help me out. Join the UX improvement program!");
        assertTrue("the whistle is not disappear", flag);

        //setting里看是否被勾选
        openUXImprovementProgram();
        flag &= isUXChecked();
        assertTrue("the checkbox in setting is not checked", flag);
    }
}