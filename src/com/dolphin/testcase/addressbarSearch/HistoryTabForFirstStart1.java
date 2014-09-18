package com.dolphin.testcase.addressbarSearch;

import android.util.Log;

public class HistoryTabForFirstStart1 extends Utils_AddressBarSearch{

    boolean flag = true;
    public void testHistoryTabForFirstStart(){

        solo.sleep(25000);

        //初次安装程序，进入程序中点击地址栏查看地址栏下方的列表
        clickOnAddressBar(1);
        //判断列表中显示书签数据
        flag &= solo.searchText("Amazon") && solo.searchText("Wikipedia");
        assertTrue("the bookmark tab is not right", flag);
        Log.d("TEST", flag + "");
        //点击或滑动到HISTORY，并判断列表是否为空
        scroll(0, 20);

    }
}




