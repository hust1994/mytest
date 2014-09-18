package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;

public class SearchSuggestion extends Utils_AddressBarSearch{

    String url1 = "yandex.com";
    String url2 = "yy.com";
    String url3 = "yinyuetai.com";
    String url4 = "iqiyi.com";
    String url5 = "yahoo.com";
    String url6 = "youtube.com";
    String url7 = "youku.com";

    boolean flag = true;

    public void testSearchSuggestion(){
        solo.sleep(Resource.TIME_SMALL);

        //数据准备
        for(int i = 7; i > 0; i--){
            switch(i){
            case 1:
                typeUrlAndVisit(url1);
                break;
            case 2:
                typeUrlAndVisit(url2);
                break;
            case 3:
                typeUrlAndVisit(url3);
                break;
            case 4:
                typeUrlAndVisit(url4);
                break;
            case 5:
                typeUrlAndVisit(url5);
                solo.sleep(Resource.TIME_BIG);
                clickThePopUpBox();
                break;
            case 6:
                typeUrlAndVisit(url6);
                break;
            case 7:
                typeUrlAndVisit(url7);
                break;
            default:
                break;
            }
            solo.sleep(Resource.TIME_BIG);
            clickThePopUpBox();
        }

        clickOnAddressBar(0);
        flag &= solo.searchText("yandex") && solo.searchText("yy")
                && solo.searchText("yinyuetai") && solo.searchText("iqiyi")
                && solo.searchText("yahoo") && solo.searchText("youtube")
                && solo.searchText("youku");
        assertTrue("does not have the records", flag);

        goBackToHomepage();

        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= solo.getView("indicator") != null;
        assertTrue("does not have the indicator while the search bar has context", flag);
        solo.enterText(0, "y");
        solo.sleep(Resource.TIME_SMALLEST);

        flag &= solo.searchText("youku.com/") && solo.searchText("www.yahoo.com")
                && solo.searchText("iqiyi.com") && solo.searchText("www.yinyuetai.com")
                && solo.searchText("www.yy.com") && solo.searchText("www.yandex.com");
        assertTrue("does not have the right suggestion for y", flag);

        solo.clearEditText(0);
        solo.enterText(0, "视频");
        solo.sleep(Resource.TIME_SMALLEST);
        flag &= solo.searchText("www.youku.com/") && solo.searchText("m.youtube.com/")
                && solo.searchText("iqiyi.com");
        assertTrue("does not have the right suggestion for 视频", flag);

        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText(0, "renren.com");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("go");
        waitUrlOpen();
        solo.sleep(Resource.TIME_BIG);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);

        flag &= solo.getView("indicator") != null;
        assertTrue("does not have the indicator 2", flag);

        goBackToHomepage();

        solo.clickOnView("title_bar");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);

        solo.enterText(0, "y");
        solo.sleep(Resource.TIME_SMALLEST);

        flag &= solo.searchText("yahoo") && solo.searchText("youtube")
                && solo.searchText("yy") && solo.searchText("yandex");
        assertTrue("does not have the indicator for y 1 ", flag);

        goBackToHomepage();

        typeUrlAndVisit(url1);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit(url1);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit(url3);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit(url3);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        typeUrlAndVisit(url3);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        clickOnAddressBar(0);
        solo.sleep(Resource.TIME_BIG);

        solo.enterText(0, "y");
        solo.sleep(Resource.TIME_SMALLEST);

        flag &= solo.searchText("yahoo") && solo.searchText("youtube")
                && solo.searchText("yy") && solo.searchText("yandex");
        assertTrue("the suggestion order is not right", flag);

        clearHistory();

    }
}