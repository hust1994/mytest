package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adolphin.common.Resource;

public class BookmarksTab extends Utils_AddressBarSearch {

    String url1 = "baidu.com";
    String url2 = "google.com";
    boolean flag = true;

    public void testBookmarksTab() {

        solo.sleep(25000);
        // 左侧看书签
        scroll(0, 10);
        ViewGroup list = (ViewGroup) solo.getView("list");
        ViewGroup list1 = (ViewGroup) list.getChildAt(1);
        ViewGroup list2 = (ViewGroup) list.getChildAt(2);
        ViewGroup list3 = (ViewGroup) list.getChildAt(3);
        ViewGroup list4 = (ViewGroup) list.getChildAt(4);
        ViewGroup list5 = (ViewGroup) list.getChildAt(5);
        TextView tv1 = (TextView) list1.getChildAt(2);
        TextView tv2 = (TextView) list2.getChildAt(2);
        TextView tv3 = (TextView) list3.getChildAt(2);
        TextView tv4 = (TextView) list4.getChildAt(2);
        TextView tv5 = (TextView) list5.getChildAt(2);

        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        clickOnAddressBar(1);

        ViewGroup searchtab_main_container = (ViewGroup) solo
                .getView("searchtab_main_container");
        ViewGroup searchtab_main_container1 = (ViewGroup) searchtab_main_container
                .getChildAt(0);
        ViewGroup searchtab_main_container2 = (ViewGroup) searchtab_main_container
                .getChildAt(1);
        ViewGroup searchtab_main_container3 = (ViewGroup) searchtab_main_container
                .getChildAt(2);
        ViewGroup searchtab_main_container4 = (ViewGroup) searchtab_main_container
                .getChildAt(3);
        ViewGroup searchtab_main_container5 = (ViewGroup) searchtab_main_container
                .getChildAt(4);

        TextView t1 = solo.getCurrentViews(TextView.class,
                searchtab_main_container1).get(0);
        TextView t2 = solo.getCurrentViews(TextView.class,
                searchtab_main_container2).get(0);
        TextView t3 = solo.getCurrentViews(TextView.class,
                searchtab_main_container3).get(0);
        TextView t4 = solo.getCurrentViews(TextView.class,
                searchtab_main_container4).get(0);
        TextView t5 = solo.getCurrentViews(TextView.class,
                searchtab_main_container5).get(0);

        flag &= tv1.getText().equals(t1.getText())
                && tv2.getText().equals(t2.getText())
                && tv3.getText().equals(t3.getText())
                && tv4.getText().equals(t4.getText())
                && tv5.getText().equals(t5.getText());

        Log.d("TEST", "" + tv1.getText() + t1.getText() + tv2.getText()
                + t2.getText() + tv3.getText() + t3.getText()
                + tv4.getText() + t4.getText() + tv5.getText() + t5.getText());

        assertTrue("bookmark tab shows different with bookmark menu" + tv1.getText() + t1.getText() + tv2.getText()
                + t2.getText() + tv3.getText() + t3.getText()
                + tv4.getText() + t4.getText() + tv5.getText() + t5.getText(), flag);

        //添加至满一屏的书签
        goBackToHomepage();
        solo.sleep(Resource.TIME_SMALL);
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("more_icon");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("Edit");
        solo.sleep(Resource.TIME_SMALLEST);

        for(int i = 0; i < 5; i++){
            solo.clickOnView("btn_create_folder");
            solo.sleep(Resource.TIME_SMALLEST);
            solo.enterText(0, i + " folder");
            solo.sleep(Resource.TIME_SMALLEST);
            solo.clickOnText("OK");
            solo.sleep(Resource.TIME_SMALLEST);
        }

        goBackToHomepage();

        typeUrlAndVisit(url1);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Add");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Add bookmark");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/OK");
        solo.waitForText("Saved");
        solo.sleep(Resource.TIME_SMALL);


        typeUrlAndVisit(url2);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Add");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Add bookmark");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Bookmarks");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("3 folder");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/OK");
        solo.waitForText("Saved");

        goBackToHomepage();

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        if (getCurrentTab() == 0)
            scroll(1, 20);
        if (getCurrentTab() == 2)
            scroll(0, 20);

        flag &= solo.searchText("0 folder") && solo.searchText("1 folder")
                && solo.searchText("2 folder") && solo.searchText("3 folder")
                && solo.searchText("4 folder") && solo.searchText("百度一下")
                && solo.searchText("Bing");

        assertTrue("failed", flag);

        solo.clickOnText("百度一下");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        flag &= solo.searchText("www.baidu.com");
        assertTrue("click on bookmark item can not open a web", flag);

        clickOnAddressBar(1);
        scroll(3, 20);
        solo.clickOnText("3 folder");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("Google");
        solo.clickOnText("Google");
        waitUrlOpen();
        solo.sleep(Resource.TIME_BIG);
        flag &= solo.searchText("google.com");
        assertTrue("click on bookmark item in a folder can not open a web", flag);

        clickOnAddressBar(1);
        flag &= solo.searchText("Google");
        solo.clickLongOnText("Google");
        solo.sleep(Resource.TIME_SMALL);

        judgeTheOrder();

        //点击Open in new tab
        solo.clickOnView("option_open_newtab");
        waitUrlOpen();
        solo.sleep(Resource.TIME_BIG);
        flag &= solo.searchText("google.com");

        FrameLayout titlebar = (FrameLayout) solo.getView("fixed_titlebar_holder");
        HorizontalScrollView hsv = solo.getCurrentViews(HorizontalScrollView.class, titlebar).get(0);
        LinearLayout ll = (LinearLayout) hsv.getChildAt(0);
        if ( ll.getChildCount() != 2 ) {
            assertTrue("the web page is not open in new tab", false);
        }
        FrameLayout buttom_view = (FrameLayout)solo.getView("main_menubar_holder");
        TextView tabcount = solo.getCurrentViews(TextView.class, buttom_view).get(0);
        if(!tabcount.getText().toString().equals("2"))
            assertTrue("the tab count has not added by one", false);

        //点击Open in background
        clickOnAddressBar(1);
        flag &= solo.searchText("Google");
        solo.clickLongOnText("Google");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("option_open_bgtab");
        flag &= solo.waitForText("Opening");
        assertTrue("no toast: Opening", flag);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.waitForText("Search or enter address");
        assertTrue("tab has changed", flag);
        buttom_view = (FrameLayout)solo.getView("main_menubar_holder");
        tabcount = solo.getCurrentViews(TextView.class, buttom_view).get(0);
        if(!tabcount.getText().toString().equals("3"))
            assertTrue("the tab count has not added by one", false);
        solo.sleep(10000);

        Log.d("TEST", flag + "");
        //点击delete
        clickOnAddressBar(1);
        flag &= solo.searchText("Google");
        Log.d("TEST", flag + "");
        solo.clickLongOnText("Google");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("option_remove");
        solo.sleep(Resource.TIME_SMALL);

        clickOnBookmarksInTab();
        solo.clickLongOnText("4 folder");
        solo.waitForDialogToOpen();
        flag &= solo.searchText("Delete folder") && solo.searchText("Are you sure you want to delete the folder");
        Log.d("TEST", flag + "");
        solo.clickOnButton(1);
        solo.sleep(Resource.TIME_SMALL);
        flag &= !solo.searchText("4 folder");
        Log.d("TEST", flag + "");
        assertTrue("delete the folder failed", flag);

        //管理
        solo.clickOnText("Manage");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.waitForView(solo.getView("btn_select_or_deselect_all"));
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.waitForText("Manage");

        //刪除
        solo.clickOnText("Manage");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_select_or_deselect_all");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_remove_selected");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen()){
            solo.clickOnText("Confirm");
            solo.waitForText("Deleted");
        }

    }
}