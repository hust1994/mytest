package com.dolphin.testcase.addressbarSearch;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.adolphin.common.Resource;

public class LoadDifferentWebs extends Utils_AddressBarSearch {

    String url1 = "https://mail/bainainfo.com";
    String url2 = "www.baidu.com";
    boolean flag = true;

    public void testLoadDifferentWebs() {

        //访问https://开头的网站
        solo.sleep(Resource.TIME_SMALL);
        typeUrlAndVisit(url1);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();

        String getUrl = getAddressBarContext();
        flag &= getUrl.equals(url1);
        View lock = solo.getView("lock");
        flag &= lock != null;
        assertTrue("the url is not https://mail/bainainfo.com", flag);

        //480*800 切换到无tab bar模式
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int device_height = dm.heightPixels;
        int device_width = dm.widthPixels;
        Log.d("TEST", device_height + " " + device_width);
        if (device_height == 800 & device_width == 480) {
            changeNoTabBarMode();
            typeUrlAndVisit(url2);
            solo.sleep(Resource.TIME_BIG);
            clickThePopUpBox();
            View favicon = solo.getView("favicon");
            boolean hasNoFavicion = favicon.getVisibility() == 8;
            changeNoTabBarMode();
            assertTrue("showed favicon", hasNoFavicion);
        }

        //点击地址栏查看完整的URL
        visitUrl(url2);
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);

        String getUrl2 = getSearchBarContext();
        String urlTitle = getUrl2.split(":")[0];
        Log.d("TEST",urlTitle);
        flag &= urlTitle.equals("http");
        Log.d("TEST",flag + "");

        View engine_img = solo.getView("engine_img");
        flag &= engine_img != null;
        Log.d("TEST",flag + "");

        View go = solo.getView("go");
        flag &= go.getVisibility() == 0;
        Log.d("TEST",flag + "");
        Log.d("TEST",go.getVisibility() + "");
        assertTrue("failed", flag);
    }
}