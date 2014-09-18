package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import android.view.View;
import com.adolphin.common.Resource;

public class HistoryTabForDataOperate1 extends Utils_AddressBarSearch{

    boolean flag = true;
    public void testHistoryTab(){
        solo.sleep(Resource.TIME_SMALL);

        //确保隐私模式关闭，点击地址栏种输入关键字abc,点击地址栏右侧取消按钮，再次点击地址栏，history列表中不会出现abc的访问记录。
        final View privateModeIcon = getPrivateModeView();
        if(privateModeIcon.isSelected() != false){
            solo.clickOnView(privateModeIcon);
        };
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        assertFalse("private mode open", privateModeIcon.isSelected());

//        clickOnAddressBar(0);
//        if(solo.getView("searchtab_bottom_continer").getVisibility() == 0)
//        	clearHistory();

        // 输入网址不访问
        flag &= !typeButNotVisit("abc");
        flag &= !typeButNotVisit("baidu.com");
        assertTrue("has the abc and baidu history without visit abc and baidu", flag);

        goBackToHomepage();

        //输入并访问
        typeUrlAndVisit("abc");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit("baidu.com");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        clickOnAddressBar(0);

        flag &= solo.searchText("abc - Google Search");
        Log.d("TEST",flag + "");
        flag &= solo.searchText("www.baidu.com");
        Log.d("TEST",flag + "");
        flag &= solo.getView("icon2", 1) != null;
        Log.d("TEST",flag + "");
        assertTrue("has no history for baidu and abc", flag);

        solo.enterText(0, "y");
        if(solo.waitForText("youtube"))
            solo.clickOnText("youtube");
        solo.sleep(20*1000);
        if(solo.waitForDialogToOpen())
            solo.clickOnText("Continue");

        goBackToHomepage();

        clickOnAddressBar(0);
        flag &= solo.searchText("youtube");
        Log.d("TEST",flag + "");
        assertTrue("has no history for namespace", flag);

        //清除数据
        clearHistory();
    }
}