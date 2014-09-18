package com.dolphin.testcase.addressbarSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.util.Log;
import com.adolphin.common.Resource;

public class HistoryTabForClearBrowsingData_Cache extends Utils_AddressBarSearch {

    String[] urls = { "http://qq.com", "http://google.com", "http://renren.com",
            "http://taobao.com", "http://baidu.com", "http://sina.com.cn",
            "http://hao123.com", "http://tmall.com", "http://163.com",
            "http://xinhuanet.com" };
    boolean flag = true;

    String line = null;
    BufferedReader reader = null;

    public void testHistoryTabForClearBrowsingData() {
        // 访问网页，产生历史记录
        solo.sleep(Resource.TIME_SMALL);
        visitUrls(urls);

        try {
            solo.sleep(Resource.TIME_BIG);
            Process p = Runtime.getRuntime().exec("ls data/data/mobi.mgeek.TunnyBrowser");
            solo.sleep(Resource.TIME_SMALL);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                Log.d("TEST", line);
                if(line.equals("cache")){
                    flag = true;
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("has no cache before clear Cache", flag);

        clickOnAddressBar(0);
        clearCache();

        try {
            solo.sleep(Resource.TIME_BIG);
            Process p = Runtime.getRuntime().exec("ls data/data/mobi.mgeek.TunnyBrowser");
            solo.sleep(Resource.TIME_SMALL);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                Log.d("TEST", line);
                if(line.equals("cache")){
                    flag = false;
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("clear cache failed", flag);
    }
}