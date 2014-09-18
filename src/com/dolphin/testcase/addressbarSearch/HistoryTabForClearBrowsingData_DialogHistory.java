package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.adolphin.common.Resource;

public class HistoryTabForClearBrowsingData_DialogHistory extends Utils_AddressBarSearch{

    String historyUrl = "www.baidu.com";
    boolean flag = true;

    public void testHistoryTabForClearBrowsingData(){
        //访问网页，产生历史记录
        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit(historyUrl);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        //判断是否有历史记录
        clickOnAddressBar(0);

        flag &= solo.searchText("百度一下") && solo.getView("searchtab_bottom_continer").getVisibility() == 0;
        assertTrue("does no have the history for baidu", flag);

        //判断clear history dialog 显示是否正常，只有第一项是被选中的
        boolean[] isChecked1 = validateInitClearHistoryState();
        flag &= isChecked1[0] && !isChecked1[1] && !isChecked1[2];

        solo.clickOnView((CheckBox) solo.getView("chkClearCache"));
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView((CheckBox) solo.getView("chkClearCookies"));
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("Cancel");

        boolean[] isChecked2 = validateInitClearHistoryState();
        flag &= isChecked2[0] && isChecked2[1] && isChecked2[2];
        assertTrue("clear browsing data dialog is not normal", flag);

        //清除历史记录后，判断地址栏下方的history是否已经清除
        solo.clickOnButton(4);
        solo.waitForText("Cleared");

        flag &= solo.getView("searchtab_bottom_continer").getVisibility() != 0;
        assertTrue("clear history failed 1", flag);

        //清除历史记录后，看左边菜单栏里是否被清除
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("History");
        solo.sleep(Resource.TIME_SMALL);
        ViewGroup historyList = (ViewGroup) solo.getView("list");
        Log.d("TEST", historyList.getChildCount() + "");
        flag &= historyList.getChildCount() == 1;
        if(solo.searchText("History"))
            solo.clickOnText("History");
        if(solo.searchText("HISTORY"))
            solo.clickOnText("HISTORY");
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("clear history failed 2", flag);
    }
}