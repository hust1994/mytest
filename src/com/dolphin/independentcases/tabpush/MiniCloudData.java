
package com.dolphin.independentcases.tabpush;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class MiniCloudData extends BaseTest {

    /**
     * 小屏机 2.访问网页，添加书签，tab，获得对应的书签，历史，tab数量，并同步数据，进入cloud data
     * 看相应数据是否和已获得的数据对应相等 点击Stop sync and erase data按钮，弹出提示框：It will stop
     * syncing and clear all cloud data.
     * ·弹框的按钮：Cancel按钮，OK按钮，点击Cancel按钮，弹框消失，界面不做任何改变，点击OK按钮，弹框消失，提示Erasing data…
     * ·账号接着自动登出，自动回到Settings界面 ·同时提示Sync turned off. Cloud data
     * detected.点击Settings界面的Dolphin Connect，进入Dolphin connect的登陆界面，进入Dolphin
     * connect的登陆界面，进入Dolphin connect的登陆界面
     *
     * @author xzhou
     */

    public void test_CloudData() throws Exception {

        solo.sleep(5000);
        Boolean toast;
        TextView bookmark;
        TextView opentab;
        TextView history;
        TextView acc;
        ViewGroup realContent;
        View v;
        String account = "";
        float screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        float screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();

        int bookmarkNum = 0;
        int historyNum = 0;
        // 允许同步

        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getView("id/address_open_menu_more"));// 首页点击设置
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Settings");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Dolphin Connect");
        solo.sleep(Resource.TIME_SMALL);
        CheckBox cb = (CheckBox) solo.getView("history_sync_state");
        if(!cb.isChecked()){
            solo.clickOnView(cb);
            solo.sleep(2000);
            solo.clickOnText("OK");
            solo.sleep(2000);
        }
//        solo.clickOnText("Auto sync History");// 允许同步历史
//        solo.sleep(Resource.TIME_SMALL);
//        solo.clickOnText("OK");
//        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.goBack();// 返回首页

        solo.drag(10, screenWidth - 10, screenHeight/2, screenHeight/2, 20);// 拖出书签栏
        solo.sleep(Resource.TIME_SMALL);
        if (!solo.searchText("BOOKMARKS", true)) {// 如果打开时是历史
            solo.clickOnView(solo.getView("id/back"));// 点击返回书签
            solo.sleep(Resource.TIME_SMALL);
        }
        realContent = (ViewGroup) TabPush.getAllViewByClassName("CustomMenuListView", this.solo).get(0);//
        // 书签列表
        solo.drag(screenWidth/3, screenWidth/3, screenHeight*3/4, screenHeight/4, 20);
        solo.sleep(2000);
        bookmarkNum = TabPush.getAllViewByClassName("c", this.solo).size();//
        // 获得书签的个数
        Log.i("TEST", "-----the bookmark num at first is:   " + bookmarkNum);
        if (bookmarkNum < 6) {// 判断安装浏览器是否自带书签，如果没有，添加两个书签
            solo.goBack();
            solo.sleep(Resource.TIME_SMALL);
            // 添加2个书签，
            // 添加百度为书签--------------
            visitUrl("www.baidu.com");// 访问百度
            solo.sleep(Resource.TIME_BIG);
            if (solo.searchText("Decline")) {
                solo.goBack();
                solo.sleep(Resource.TIME_SMALL);
            }
            solo.clickOnView(solo.getView("id/address_open_menu_more"));// 点击首页dolphin按钮
            // 点击网页页dolphin按钮
            solo.sleep(Resource.TIME_SMALL);
            v = solo.getText("Add");
            v = (View) v.getParent();
            solo.clickOnView(v);
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnText("Add bookmark");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnView(solo.getView("id/OK"));
            solo.sleep(Resource.TIME_SMALL);

            // 添加sogou为书签
            visitUrl("www.sogou.com/");// 访问sogo
            solo.sleep(Resource.TIME_BIG);
            solo.drag(screenWidth/2, screenWidth/2, screenHeight/4, screenHeight*3/4, 20);
            solo.clickOnView(solo.getView("id/address_open_menu_more"));// 点击首页dolphin按钮

            solo.sleep(Resource.TIME_SMALL);
            v = solo.getText("Add");
            v = (View) v.getParent();
            solo.clickOnView(v);
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnText("Add bookmark");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnView(solo.getView("id/OK"));
        } else {// 如果自带书签，就之间添加tab
            visitUrl("www.sogou.com/");// 访问sogo
            solo.sleep(Resource.TIME_BIG);
        }
        visitUrl("www.baidu.com");// 访问百度，增加一条历史
        solo.sleep(Resource.TIME_BIG);
        if (solo.searchText("Decline")) {
            solo.goBack();
            solo.sleep(Resource.TIME_SMALL);
        }
        solo.drag(screenWidth/2, screenWidth/2, screenHeight/4, screenHeight*3/4, 20);
        solo.sleep(Resource.TIME_SMALL);


        // 添加2个tab，共3个
        for (int i = 0; i < 2; i++) {
            solo.clickOnView(solo.getView("id/address_open_tablist"));// 点击首页dolphin按钮
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnText("New");
            solo.sleep(Resource.TIME_SMALL);

            visitUrl("www.baidu.com/");// 访问baidu
            solo.sleep(Resource.TIME_BIG);
            if (solo.searchText("Decline")) {
                solo.goBack();
                solo.sleep(Resource.TIME_SMALL);
            }
            solo.drag(screenWidth/2, screenWidth/2, screenHeight/4, screenHeight*3/4, 20);
            solo.sleep(Resource.TIME_SMALL);


        }

        solo.drag(10, screenWidth - 10, screenHeight/2, screenHeight/2, 20);// 拖出书签栏
        solo.sleep(Resource.TIME_SMALL);
        if (!solo.searchText("BOOKMARKS", true)) {// 如果打开时是历史
            solo.clickOnView(solo.getView("id/back"));// 点击返回书签
            solo.sleep(Resource.TIME_SMALL);
        }
        acc = (TextView) solo.getView("id/email_txt");
        account = (String) acc.getText();// 获得当前账号
        Log.i("TEST", "-----the account is:     " + account);
        solo.clickOnView(solo.getView("id/sync_container"));// 点击同步云
        toast = solo.waitForText("Sync successfully");
        int i = 0;
        while(!toast && i < 5){
            solo.clickOnView(solo.getView("id/sync_container"));// 点击同步云
            toast = solo.waitForText("Sync successfully");
            i++;
        }
        assertTrue("network error,sync failed", toast);
        solo.drag(screenWidth/3, screenWidth/3, screenHeight*2/5, screenHeight-20, 20);
        realContent = (ViewGroup) TabPush.getAllViewByClassName("CustomMenuListView", this.solo).get(0);// 书签列表
        solo.drag(screenWidth/3, screenWidth/3, screenHeight*3/4, screenHeight/4, 20);
        solo.sleep(2000);
        bookmarkNum = TabPush.getAllViewByClassName("c", this.solo).size() + 4;// 获得书签的个数
        Log.i("TEST", "-----the bookmark num after add is:      " + String.valueOf(bookmarkNum));

        solo.clickOnText("History");// 点击历史
        solo.sleep(Resource.TIME_SMALL);
        solo.drag(screenWidth/3, screenWidth/3, screenHeight*3/4, screenHeight/4, 20);
        solo.sleep(2000);
        historyNum = TabPush.getAllViewByClassName("c", this.solo).size();// 获得历史个数
        Log.i("TEST", "-----the history record num after add is:      " + String.valueOf(historyNum));

        solo.goBack();// 返回网页
        // 进入cloud data
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo.getView("id/address_open_menu_more"));// 首页点击设置
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Settings");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Dolphin Connect");
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnText("Cloud data");
        toast = solo.waitForText("Stop sync and Erase Data",1, 60000);
        i = 0;
        while(!toast && i < 5){
            solo.clickOnText("Cloud data");
            toast = solo.waitForText("Stop sync and Erase Data",1, 60000);
            i++;
        }
        assertTrue("network error", toast);

        bookmark = (TextView) solo.getView("id/count_text", 0);
        int bm_sync = Integer.parseInt((String) bookmark.getText());
        Log.i("TEST", "-----bookmark num after sync:    " + bm_sync);
        opentab = (TextView) solo.getView("id/count_text", 1);
        int tab_sync = Integer.parseInt((String) opentab.getText());
        Log.i("TEST", "-----tab count after sync:   " + tab_sync);
        history = (TextView) solo.getView("id/count_text", 3);
        int hr_sync = Integer.parseInt((String) history.getText());
        Log.i("TEST", "-----history record count after sync:    " + hr_sync);
        toast = (bm_sync == bookmarkNum);
        assertTrue("bookmarknum is not same", toast);
        toast = (hr_sync == historyNum);
        assertTrue("historynum is not same", toast);
        toast = (tab_sync == 3);
        assertTrue("opentabnum is not same", toast);
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnText("Stop sync and Erase Data");
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.searchText("It will stop syncing and clear all cloud data.");
        assertTrue("no tosat", toast);
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.searchText("Cancel");
        assertTrue("no cancel", toast);
        solo.clickOnText("Cancel");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Stop sync and Erase Data");
        solo.sleep(Resource.TIME_SMALL);

        toast = solo.searchText("OK");
        assertTrue("no OK", true);
        solo.clickOnText("OK");
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.searchText("Erasing data…");
        assertTrue("no Erasing data…", true);
        toast = solo.waitForText("SETTINGS");
        assertTrue("erasing data failed",toast);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Dolphin Connect");
        solo.sleep(Resource.TIME_SMALL);
        v = solo.getViewByPath("btn_login_dolphin");
        solo.clickOnView(v);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/ds_email");// 输入Email
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText((EditText) solo.getView("id/ds_email"));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText((EditText) solo.getView("id/ds_email"), account);
        solo.clickOnView("id/ds_password");// 输入密码
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText((EditText) solo.getView("id/ds_password"));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText((EditText) solo.getView("id/ds_password"), "123456");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Sign in");
        solo.sleep(20000);
        solo.clickOnText("Cloud data");
        solo.sleep(5000);
        bookmark = (TextView) solo.getViewByPath("data_container[0][2]");
        bm_sync = Integer.parseInt((String) bookmark.getText());
        Log.i("TEST", "-----" + bm_sync);
        opentab = (TextView) solo.getViewByPath("data_container[1][2]");
        tab_sync = Integer.parseInt((String) opentab.getText());
        Log.i("TEST", "-----" + tab_sync);
        history = (TextView) solo.getViewByPath("data_container[3][2]");
        hr_sync = Integer.parseInt((String) history.getText());
        Log.i("TEST", "-----" + hr_sync);
        toast = (bm_sync == 0);
        assertTrue("bookmarknum is not 0", toast);
        toast = (hr_sync == 0);
        assertTrue("historynum is not 0", toast);
        toast = (tab_sync == 0);
        assertTrue("opentabnum is not 0", toast);
        solo.sleep(Resource.TIME_SMALL);
        Log.i("TEST", "---finish");
    }
}
