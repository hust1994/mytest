package com.dolphin.testcase.addressbarSearch;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.adolphin.common.Resource;

public class EditAddressBar extends Utils_AddressBarSearch {

    String url = "www.baidu.com";
    String url2 = "renren.com";
    boolean flag = true;

    public void testEditAddressBar() {
        solo.sleep(Resource.TIME_SMALL);

        // 新开一个tab，点击address bar
        ImageView v = (ImageView) solo.getImage(2);
        solo.clickOnView(v);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("title_bg");
        // 判断
        View engine_img = solo.getView("engine_img");
        View select_engine_arrow = solo.getView("select_engine_arrow");
        View cancel = solo.getView("cancel");
        flag &= engine_img != null && select_engine_arrow != null
                && cancel != null;
        assertTrue("click on address bar failed", flag);

        // 点击X
        solo.clickOnView(cancel);
        solo.sleep(Resource.TIME_SMALL);
        // 判断
        flag &= solo.searchText("Google") && solo.searchText("Facebook");
        assertTrue("click cancel can't go back to the homepage", flag);

        // 加载一个网页，点击地址栏
        typeUrlAndVisit(url);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        scroll(3, 30);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);

        // 判断
        View engine_img2 = solo.getView("engine_img");
        View select_engine_arrow2 = solo.getView("select_engine_arrow");
        View go2 = solo.getView("go");
        flag &= engine_img2 != null && select_engine_arrow2 != null
                && go2 != null;

        String getUrl2 = getSearchBarContext();
        String urlTitle = getUrl2.split(":")[0];
        Log.d("TEST", urlTitle);
        flag &= urlTitle.equals("http");
        assertTrue("load a web and click on the address bar failed", flag);
        solo.sleep(Resource.TIME_SMALL);

        // exchange URL
        solo.clearEditText(0);
        solo.sleep(1000);
        solo.enterText(0, url2);
        solo.sleep(2000);
        solo.clickOnView("id/go");
        solo.sleep(Resource.TIME_SMALL);
        View loadBar = getTinyTittleBar();
        flag &= loadBar != null;
        solo.sleep(Resource.TIME_BIG);
        scroll(3, 30);
        flag &= solo.searchText("renren");
        assertTrue("exchange URL and go failed", flag);

        //退出编辑状态的方式--再次加载网页
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        View go3 = solo.getView("go");
        //是否是可编辑状态
        flag &= go3 != null;
        solo.clickOnView(go3);
        solo.sleep(Resource.TIME_BIG);
        View loadBar3 = getTinyTittleBar();
        //是否开始加载，退出编辑状态
        flag &= loadBar3 != null;
        assertTrue("use go to exit edit state", flag);

        //编辑状态点击手机的back键
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        View go4 = solo.getView("go");
        //是否是可编辑状态
        flag &= go4 != null;
        solo.goBack();
        assertTrue("go back can't go to the edit state", flag);

        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        clickOnAddressBar(0);
        solo.clickOnText("Clear browsing data");
        solo.sleep(Resource.TIME_SMALL);
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