package com.dolphin.testcase.addressbarSearch;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adolphin.common.Resource;

public class LoadAWeb extends Utils_AddressBarSearch{
    String url = "163.com";
    boolean flag = true;

    public void testLoadAWeb(){
        solo.sleep(25000);
        solo.clickOnText("Search or enter address");
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, url);
        solo.sleep(Resource.TIME_SMALL);

        //地址栏中是否显示输入的facebook.com/右端是否显示→
        EditText et = (EditText) solo.getView("search_src_text");
        String getUrl = et.getText().toString();
        View go = solo.getView("go");

        flag &= getUrl.equals(url) & go != null;
        assertTrue("url is not equals facebook.com", flag);

        //点击→，开始加载，→消失显示停止按钮
        solo.clickOnView(go);
        solo.sleep(Resource.TIME_SMALLEST);
        View tinyTittleBar = getTinyTittleBar();
        View stop = solo.getView("refresh_stop_btn");

        flag &= tinyTittleBar != null & stop != null;
        assertTrue("the web is not loading", flag);

        //点击停止按钮,停止变刷新，并有toast提示Stopping...
        solo.clickOnView(stop);
        flag &= solo.waitForText("Stopping");
        assertTrue("the web is not stopping", flag);

        //网页加载完，显示完整的url，右端显示刷新按钮
        solo.sleep(Resource.TIME_SMALL);
        View refresh = solo.getView("refresh_stop_btn");
        solo.clickOnView(refresh);
        solo.sleep(30*1000);

//		solo.clickOnView("id/title_bg");
//		solo.sleep(Resource.TIME_SMALL);
//		solo.clickOnView("id/go");
//		solo.sleep(Resource.TIME_SMALL);
//		solo.sleep(30000);

        LinearLayout ll = (LinearLayout) solo.getView("title_bg");
        RelativeLayout rl = (RelativeLayout) ll.getChildAt(3);
        TextView urladdress1 = (TextView) rl.getChildAt(1);
        getUrl = urladdress1.getText().toString();
        flag &= getUrl.equals("3g.163.com/touch/");
        assertTrue("the web is not loaded", flag);

        clickOnAddressBar(0);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Clear browsing data");
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(!isChecked[0]) solo.clickOnText("Clear browsing history");
            if(!isChecked[1]) solo.clickOnText("Clear the cache");
            if(!isChecked[2]) solo.clickOnText("Clear cookies");
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }
    }
}