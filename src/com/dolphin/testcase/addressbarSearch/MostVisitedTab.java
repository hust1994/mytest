package com.dolphin.testcase.addressbarSearch;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.adolphin.common.Resource;

public class MostVisitedTab extends Utils_AddressBarSearch {

    boolean flag = true;

    public void testMostVisitedTab() {

        solo.sleep(25000);
        clickOnAddressBar(2);
        goBackToHomepage();

        for (int i = 1; i <= 9; i++) {
            typeUrlAndVisit(i + "");
            solo.sleep(Resource.TIME_BIG);
            clickThePopUpBox();
        }


        solo.sleep(Resource.TIME_BIG);
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("History");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Most Visited");
        solo.sleep(Resource.TIME_SMALL);

        flag &= solo.searchText("1") && solo.searchText("2")
                && solo.searchText("3") && solo.searchText("4")
                && solo.searchText("5") && solo.searchText("6")
                && solo.searchText("7") && solo.searchText("8")
                && solo.searchText("9");

        solo.clickOnText("Most Visited");
        solo.sleep(Resource.TIME_SMALL);
        if (solo.searchText("History"))
            solo.clickOnText("History");
        if (solo.searchText("HISTORY"))
            solo.clickOnText("HISTORY");
        solo.sleep(Resource.TIME_SMALL);

        assertTrue("does not have the records", flag);

        goBackToHomepage();

        solo.sleep(Resource.TIME_SMALL);

        clickOnAddressBar(2);
        solo.sleep(Resource.TIME_SMALL);
        ListView lll = (ListView) solo.getView("searchtab_main_container", 0);
        String firStr1 = (solo.getCurrentViews(TextView.class, lll).get(0))
                .getText().toString();
        if (firStr1.equals("Dolphin")) {
            lll = (ListView) solo.getView("searchtab_main_container", 1);
        }
        String[] judgeStr2 = {
                (solo.getCurrentViews(TextView.class, lll).get(0)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(2)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(4)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(6)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(8)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(10)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(12)).getText()
                        .toString(),
                (solo.getCurrentViews(TextView.class, lll).get(14)).getText()
                        .toString() };
        Log.d("TEST", judgeStr2[1]);
        if (judgeStr2[0].contains("1 - ") && judgeStr2[1].contains("2 - ")
                && judgeStr2[2].contains("3 - ") && judgeStr2[3].contains("4 - ")
                && judgeStr2[4].contains("5 - ") && judgeStr2[5].contains("6 - ")
                && judgeStr2[6].contains("7 - ") && judgeStr2[7].contains("8 - "))
            flag &= true;
        else
            flag &= false;

        assertTrue("does not have the right records (order) for 1-8", flag);

        flag &= solo.searchText("http") && solo.getView("icon2") != null;
        assertTrue("most visited tab does not show correctly", flag);

        // clickOnAddressBar(2);
        solo.clickOnText("3 - ");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        TextView title = (TextView) solo.getView("title");
        flag &= !title.getText().equals("");

        clickOnAddressBar(2);
        scroll(3, 5);

        int j = 0;
        ListView ll1 = (ListView) solo.getView("searchtab_main_container", j);
        String firStr = (solo.getCurrentViews(TextView.class, ll1).get(2))
                .getText().toString();
        while (!firStr.contains("1 - ")) {
            ll1 = (ListView) solo.getView("searchtab_main_container", ++j);
            firStr = (solo.getCurrentViews(TextView.class, ll1).get(2))
                    .getText().toString();
        }
        Log.d("TEST", firStr);
        ImageView iv = (solo.getCurrentViews(ImageView.class, ll1).get(0));
        solo.clickOnView(iv);

        //
        // ViewGroup vg = (ViewGroup) solo.getView("0x2");
        // ArrayList<ImageView> ali = solo.getCurrentViews(ImageView.class, vg);
        // // View v = solo.getView("icon2", 5);
        // solo.clickOnView(ali.get(1));

        solo.sleep(Resource.TIME_SMALL);
        EditText ev = (EditText) solo.getView("search_src_text");
        flag &= !ev.getText().equals("Search or enter address");

        assertTrue("click in most visited failed", flag);

        // 长按
        goBackToHomepage();
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);

        if (getCurrentTab() != 2) {
            scroll(1, 20);
            scroll(1, 20);
        }

        solo.clickLongOnText("3 - ");
        solo.sleep(Resource.TIME_SMALL);
        judgeTheOrder();
        solo.sleep(Resource.TIME_SMALL);

        // 点击Open in new tab
        solo.clickOnView("option_open_newtab");
        waitUrlOpen();
        solo.sleep(Resource.TIME_BIG);
        flag &= solo.searchText("google.com");

        FrameLayout titlebar = (FrameLayout) solo
                .getView("fixed_titlebar_holder");
        HorizontalScrollView hsv = solo.getCurrentViews(
                HorizontalScrollView.class, titlebar).get(0);
        LinearLayout ll = (LinearLayout) hsv.getChildAt(0);
        if (ll.getChildCount() != 2) {
            assertTrue("the web page is not open in new tab", false);
        }
        FrameLayout buttom_view = (FrameLayout) solo
                .getView("main_menubar_holder");
        TextView tabcount = solo.getCurrentViews(TextView.class, buttom_view)
                .get(0);
        if (!tabcount.getText().toString().equals("2"))
            assertTrue("the tab count has not added by one", false);

        // 点击Open in background
        clickOnAddressBar(2);
        flag &= solo.searchText("3 - ");
        solo.clickLongOnText("3 - ");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("option_open_bgtab");
        flag &= solo.waitForText("Opening");
        assertTrue("no toast: Opening", flag);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.waitForText("Search or enter address");
        assertTrue("tab has changed", flag);
        buttom_view = (FrameLayout) solo.getView("main_menubar_holder");
        tabcount = solo.getCurrentViews(TextView.class, buttom_view).get(0);
        if (!tabcount.getText().toString().equals("3"))
            assertTrue("the tab count has not added by one", false);
        solo.sleep(10000);

        // 点击delete
        clickOnAddressBar(2);
        flag &= solo.searchText("3 - ");
        solo.clickLongOnText("3 - ");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("option_remove");
        solo.sleep(Resource.TIME_SMALL);
        flag &= !solo.searchText("3");
        assertTrue("delete item failed 1", flag);

        goBackToHomepage();

        clickOnAddressBar(2);
        flag &= !solo.searchText("3 - ");
        assertTrue("delete item failed 2", flag);

        goBackToHomepage();

        scroll(0, 20);
        solo.clickOnText("History");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Most Visited");
        solo.sleep(Resource.TIME_SMALL);

        View list = solo.getView("list");
        ArrayList<TextView> tv = solo.getCurrentViews(TextView.class, list);

        for (int i = 0; i < tv.size(); i++) {
            if (tv.get(i).getText().equals("3 - "))
                flag &= false;
        }

        Log.d("TEST", flag + "");
        assertTrue("delete item failed 3", flag);

        solo.clickOnText("Most Visited");
        solo.sleep(Resource.TIME_SMALL);
        if (solo.searchText("History"))
            solo.clickOnText("History");
        if (solo.searchText("HISTORY"))
            solo.clickOnText("HISTORY");
        solo.sleep(Resource.TIME_SMALL);

        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit("baidu.com");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        clickOnAddressBar(2);
        solo.clickLongOnText("4 - ");
        solo.sleep(3000);
        solo.clickOnView("option_remove");

        flag &= !solo.searchText("4 - ") && solo.searchText("百度一下");
        assertTrue("delete item failed 4", flag);
        clearHistory();
    }

}