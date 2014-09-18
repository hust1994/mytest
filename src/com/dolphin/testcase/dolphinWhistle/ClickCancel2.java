package com.dolphin.testcase.dolphinWhistle;

import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.Resource;

public class ClickCancel2 extends Utils_dolphinWhistle {

    boolean flag = true;

    public void testClickCancel() {
        waitMainActivity();
        solo.sleep(Resource.TIME_SMALL);

        // 进入setting看是否是没勾选的状态
        openUXImprovementProgram();
        flag &= !isUXChecked();
        assertTrue("the checkbox in setting is checked", flag);

        goBackToHomepage();

        // 判断是否还会出现dolphin whistle
        flag &= !solo
                .searchText("Help me out. Join the UX improvement program!");
        assertTrue("the whistle is not disappear", flag);

    }
}