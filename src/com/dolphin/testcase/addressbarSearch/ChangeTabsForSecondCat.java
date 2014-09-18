package com.dolphin.testcase.addressbarSearch;

import android.view.KeyEvent;
import android.view.ViewGroup;
import com.adolphin.common.Resource;

public class ChangeTabsForSecondCat extends Utils_AddressBarSearch{

    String url = "baidu.com";
    boolean flag = true;

    public void testChangeTabsForSecondCat(){

        //创建三级目录
        solo.sleep(Resource.TIME_SMALL);
        scroll(0, 20);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("more_icon");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("Edit");
        solo.sleep(Resource.TIME_SMALLEST);

        solo.clickOnView("btn_create_folder");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.enterText(0, "AA");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("OK");

        solo.clickOnText("AA");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView("btn_create_folder");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.enterText(0, "BB");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("OK");

        solo.clickOnText("BB");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView("btn_create_folder");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.enterText(0, "CC");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("OK");

        solo.clickOnView("searchtab_change_path");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView("searchtab_change_path");
        solo.sleep(Resource.TIME_SMALLEST);

        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }

        typeUrlAndVisit(url);
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
        solo.clickOnText("CC");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/OK");
        solo.waitForText("Saved");
        while (true) {
          solo.goBack();
          solo.sleep(1000);
          if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
              solo.goBack();
              break;
          }
      }

        //开始操作
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("AA");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("/AA") && solo.searchText("BB");

        scroll(0, 20);
        flag &= solo.searchText("Your history will be here");
        scroll(1, 20);
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("/AA") && solo.searchText("BB");

        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("/AA") && solo.searchText("BB");

        solo.clickOnText("BB");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("CC");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("/AA/BB/CC") && solo.searchText("百度一下");

        solo.clickOnView("searchtab_change_path");
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("/AA/BB") && solo.searchText("CC");

        solo.clickOnView("cancel");
        solo.sleep(Resource.TIME_SMALL);

        scroll(0, 20);
        solo.clickOnView("more_icon");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("Edit");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView("searchtab_change_path");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnView("checkbox");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_remove_selected");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen())
            solo.clickOnText("Confirm");

        while (true) {
          solo.goBack();
          solo.sleep(1000);
          if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
              solo.goBack();
              break;
          }
      }


        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.getView("searchtab_change_path") != null && solo.searchText("AA");

        clickOnBookmarksInTab();

        flag &= solo.searchText("AA") && solo.searchText("Dolphin") && solo.searchText("Wikipedia");

        solo.goBack();
        scroll(0, 20);
        solo.clickOnView("more_icon");
        solo.sleep(Resource.TIME_SMALLEST);
        solo.clickOnText("Edit");
        solo.sleep(Resource.TIME_SMALLEST);

        ViewGroup list = (ViewGroup) solo.getView("list");
        ViewGroup line_AA = (ViewGroup) list.getChildAt(1);
        solo.clickOnView(line_AA.getChildAt(0));
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_remove_selected");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen())
            solo.clickOnText("Confirm");
        assertTrue("failed", flag);

    }
}
