package com.dolphin.testcase.addressbarSearch;

import java.util.ArrayList;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adolphin.common.Resource;

public class SearchEngine extends Utils_AddressBarSearch{

    boolean flag = true;

    public void testSearchEngine(){
        solo.sleep(Resource.TIME_SMALL);
        clickOnAddressBar(1);
        solo.sleep(Resource.TIME_SMALL);

        flag &= !solo.searchText("Yandex");
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("Yandex");
        assertTrue("the search engine list is not open", flag);

        int screenHeight = getActivity().getWindowManager().getDefaultDisplay()
                .getHeight();
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay()
                .getWidth();
        solo.clickOnScreen(screenWidth / 2, screenHeight / 3);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);

        flag &= !solo.searchText("Yandex");
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);
        flag &= solo.searchText("Yandex");
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        flag &= !solo.searchText("Yandex");

        //显示顺序
        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);

        LinearLayout v1 = (LinearLayout) solo.getView("engine_list_layout");
        ArrayList<TextView> list1 = solo.getCurrentViews(TextView.class, v1);

        Log.d("TEST", list1.size() + "" + list1.get(0).getText().equals("Google") + list1.get(1).getText().equals("Bing")
                + list1.get(2).getText().equals("Yahoo! ") + list1.get(3).getText().equals("DuckDuckGo")
                + list1.get(4).getText().equals("Yandex"));

        flag &= list1.get(0).getText().equals("Google") && list1.get(1).getText().equals("Bing")
                && list1.get(2).getText().equals("Yahoo! ") && list1.get(3).getText().equals("DuckDuckGo")
                && list1.get(4).getText().equals("Yandex");
        solo.clickOnText("Yandex");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("the original order is not right", flag);

        solo.clickOnView("search_engine");
        solo.sleep(Resource.TIME_SMALL);

        LinearLayout v2 = (LinearLayout) solo.getView("engine_list_layout");
        ArrayList<TextView> list2 = solo.getCurrentViews(TextView.class, v2);
        flag &= list2.get(0).getText().equals("Google") && list2.get(1).getText().equals("Bing")
                && list2.get(2).getText().equals("Yahoo! ") && list2.get(3).getText().equals("DuckDuckGo")
                && list2.get(4).getText().equals("Yandex");
        solo.clickOnText("Google");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("the order is not right after changed search engine", flag);

        goBackToHomepage();

        typeUrlAndVisit("abc");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        clickOnAddressBar(0);
        flag &= solo.searchText("abc - Google Search");
        assertTrue("can't search keyword", flag);

        goBackToHomepage();

        typeUrlAndVisit("baidu.com");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        clickOnAddressBar(0);
        flag &= solo.searchText("百度一下");
        assertTrue("can't search url", flag);
        clearHistory();
    }
}