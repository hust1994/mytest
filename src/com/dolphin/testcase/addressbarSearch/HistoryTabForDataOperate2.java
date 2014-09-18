package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HistoryTabForDataOperate2 extends Utils_AddressBarSearch{
    public void testHistoryTab(){
        boolean toast = false;
        solo.sleep(Resource.TIME_BIG);

        //开bookmark中的icon
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        solo.drag(1, screenWidth - 1, screenHeight / 2, screenHeight /2, 20);
//        Utils.scorllScreen(1,this);
        solo.clickOnText("Bing");
        solo.sleep(5000);
        clickThePopUpBox();
        //查找是否有对应的记录
        toast = findRecordAtPosition("Bing", 0, 0);
        assertTrue("no record found in history", toast);

        //点击Speeddial
        ViewGroup vg = (ViewGroup) solo.getView("main_menubar_holder");
        vg = (ViewGroup) vg.getChildAt(0);
        solo.clickOnView(vg.getChildAt(4));
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Google");
        solo.sleep(5000);
        clickThePopUpBox();
        toast = findRecordAtPosition("Google",0, 0);
        assertTrue("no record found in history", toast);

        //开三个网页
        typeUrlAndVisit("baidu.com");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("google.com");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("renren.com");
        solo.sleep(5000);
        clickThePopUpBox();
        //验证
        toast = findRecordAtPosition("百度一下", 2, 0);
        assertTrue("no record of baidu.com found in history", toast);
        toast = findRecordAtPosition("Google", 1, 0);
        assertTrue("no record of google.com found in history", toast);
        toast = findRecordAtPosition("人人网客户端下载", 0, 0);
        assertTrue("no record of renre.com found in history", toast);

//        再访问
        typeUrlAndVisit("1");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("2");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("3");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("hao123.com");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("sohu.com");
        solo.sleep(5000);
        clickThePopUpBox();
        typeUrlAndVisit("yahoo.com");
        solo.sleep(5000);
        clickThePopUpBox();

        toast = findRecordAtPosition("Yahoo", 0, 0);
        assertTrue("no record of yahoo.com found in history", toast);
        toast = findRecordAtPosition("搜狐网", 1, 0);
        assertTrue("no record of sohu.com found in history", toast);
        toast = findRecordAtPosition("hao123", 2, 0);
        assertTrue("no record of hao123.com found in history", toast);
        toast = findRecordAtPosition("Google Search", 3, 0);
        assertTrue("no record of 3 found in history", toast);
        toast = findRecordAtPosition("Google Search", 4, 0);
        assertTrue("no record of 2 found in history", toast);
        toast = findRecordAtPosition("Google Search", 5, 0);
        assertTrue("no record of 1 found in history", toast);
        toast = findRecordAtPosition("人人网客户端下载", 6, 0);
        assertTrue("no record of renre.com found in history", toast);
        toast = findRecordAtPosition("Google", 7, 0);
        assertTrue("no record of google.com found in history", toast);


        vg = (ViewGroup) solo.getView("main_menubar_holder");
        vg = (ViewGroup) vg.getChildAt(0);
        solo.clickOnView(vg.getChildAt(4));
        solo.sleep(Resource.TIME_SMALL);

        //点击关键字abc
        typeUrlAndVisit("abc");
        solo.sleep(5000);
        clickThePopUpBox();
        LinearLayout ll = (LinearLayout) solo.getView("title_bg");
        RelativeLayout rl = (RelativeLayout) ll.getChildAt(3);
        TextView urladdress1 = (TextView) rl.getChildAt(1);
        String url = urladdress1.getText().toString();
        View urladdress = solo.getView("title_design");
        solo.sleep(2000);
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        ListView l = (ListView) solo.getView("searchtab_main_container", 0);
        solo.clickOnView(l.getChildAt(0));
        solo.sleep(5000);
        clickThePopUpBox();
        ll = (LinearLayout) solo.getView("title_bg");
        rl = (RelativeLayout) ll.getChildAt(3);
        urladdress1 = (TextView) rl.getChildAt(1);
//        solo.clickOnView(urladdress1);
        String url2 = urladdress1.getText().toString();
        if( !url2.equals(url) )
            assertTrue("the search result is not right", false);
        //打开百度主页
        typeUrlAndVisit("baidu.com");
        solo.sleep(5000);
        clickThePopUpBox();
        ll = (LinearLayout) solo.getView("title_bg");
        rl = (RelativeLayout) ll.getChildAt(3);
        urladdress1 = (TextView) rl.getChildAt(1);
        url = urladdress1.getText().toString();
        urladdress = solo.getView("title_design");
        solo.sleep(2000);
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        l = (ListView) solo.getView("searchtab_main_container", 0);
        solo.clickOnView(l.getChildAt(0));
        solo.sleep(5000);
        clickThePopUpBox();
        ll = (LinearLayout) solo.getView("title_bg");
        rl = (RelativeLayout) ll.getChildAt(3);
        urladdress1 = (TextView) rl.getChildAt(1);
//        solo.clickOnView(urladdress1);
        url2 = urladdress1.getText().toString();
        if( !url2.equals(url) )
            assertTrue("the open url is not baidu", false);

      //长按列表中的任一项
        urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 0);
        solo.clickLongOnView(rl.getChildAt(1));
        solo.sleep(1000);
        judgeTheOrder();
        solo.sleep(500);
        solo.goBack();
        solo.sleep(1000);

        //点击Open in new tab
        View v = rl.getChildAt(1);
        solo.clickLongOnView(v);
        solo.sleep(1000);
        solo.clickOnView("option_open_newtab");
        solo.sleep(2000);
        View progress_bar = solo.getView("tiny_title_bar", 0);
        if (!(progress_bar.getVisibility() == View.VISIBLE)) {
            assertTrue("the web page is not loading", false);
        }
        FrameLayout titlebar = (FrameLayout) solo.getView("fixed_titlebar_holder");
        HorizontalScrollView hsv = solo.getCurrentViews(HorizontalScrollView.class, titlebar).get(0);
        ll = (LinearLayout) hsv.getChildAt(0);
        if ( ll.getChildCount() != 2 ) {
            assertTrue("the web page is not open in new tab", false);
        }
        FrameLayout buttom_view = (FrameLayout)solo.getView("main_menubar_holder");
        TextView tabcount = solo.getCurrentViews(TextView.class, buttom_view).get(0);
        if(!tabcount.getText().toString().equals("2"))
            assertTrue("the tab count has not added by one", false);
        solo.sleep(10000);  //wait url to open


        //点击Open in background
        urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        solo.scrollToTop();
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 0);
        v = rl.getChildAt(1);
        solo.clickLongOnView(v);
        solo.sleep(1000);
        solo.clickOnView("option_open_bgtab");
        toast = solo.waitForText("Opening");
        assertTrue("no toast: Opening", toast);
        toast = solo.waitForText("Search or enter address");
        assertTrue("tab has changed", toast);
        solo.sleep(1000);
        buttom_view = (FrameLayout)solo.getView("main_menubar_holder");
        tabcount = solo.getCurrentViews(TextView.class, buttom_view).get(0);
        if(!tabcount.getText().toString().equals("3"))
            assertTrue("the tab count has not added by one", false);
        solo.sleep(10000);


        //点击Delete
        solo.goBack();
        solo.sleep(1000);
        //delete tab, and jump to the first one
        titlebar = (FrameLayout) solo.getView("fixed_titlebar_holder");
        hsv = solo.getCurrentViews(HorizontalScrollView.class, titlebar).get(0);
        ll = (LinearLayout) hsv.getChildAt(0);
        LinearLayout newtab = (LinearLayout)ll.getChildAt(1);
        solo.clickOnView(newtab.getChildAt(1));
        solo.sleep(1000);
        newtab = (LinearLayout)ll.getChildAt(1);
        solo.clickOnView(newtab.getChildAt(1));
        solo.sleep(1000);
        v= ll.getChildAt(0);
        solo.clickOnView(v);
        solo.sleep(2000);
        urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        solo.scrollToTop();
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 3);
        ll = (LinearLayout) rl.getChildAt(2);
        TextView suggestion_url = (TextView) ll.getChildAt(0);
        String  urlTitle = suggestion_url.getText().toString();
        Log.d("test", urlTitle + "asdf");
        solo.clickLongOnView(ll);
        solo.sleep(2000);
        solo.clickOnView("option_remove");
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 3);
        ll = (LinearLayout) rl.getChildAt(2);
        suggestion_url = (TextView) ll.getChildAt(0);
        if(suggestion_url.getText().toString().equals(urlTitle))
            assertTrue("item is not deleted", false);
        solo.goBack();
        solo.sleep(1000);
        urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 3);
        ll = (LinearLayout) rl.getChildAt(2);
        suggestion_url = (TextView) ll.getChildAt(0);
        if(suggestion_url.getText().toString().equals(urlTitle))
            assertTrue("item is not deleted after goback to home page", false);
        solo.goBack();
        solo.sleep(1000);
        solo.drag(1, screenWidth - 1, screenHeight / 2, screenHeight /2, 20);
        solo.sleep(2000);
        solo.clickOnText("History");
        solo.sleep(1000);
        suggestion_url = (TextView) solo.getView("title", 4);
        Log.d("test", "asdf"+suggestion_url.getText().toString());
        if(suggestion_url.getText().toString().equals(urlTitle))
            assertTrue("item is not deleted in History", false);
        if(solo.searchText("History"))
            solo.clickOnText("History");
        if(solo.searchText("HISTORY"))
            solo.clickOnText("HISTORY");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(1000);

        urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(4000);
        solo.goBack();
        solo.sleep(1000);
        solo.clearEditText(0);
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 7);
        ll = (LinearLayout) rl.getChildAt(2);
        suggestion_url = (TextView) ll.getChildAt(0);
        urlTitle = suggestion_url.getText().toString();
        solo.clickLongOnView(ll);
        solo.sleep(1000);
        solo.clickOnView("option_remove");
        solo.sleep(1000);
        rl = (RelativeLayout) solo.getView("suggestion_container", 7);
        ll = (LinearLayout) rl.getChildAt(2);
        suggestion_url = (TextView) ll.getChildAt(0);
        if(suggestion_url.getText().toString().equals(urlTitle))
            assertTrue("item is not auto fill in", false);
    }
}
