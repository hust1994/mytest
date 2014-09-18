
package com.dolphin.independentcases.tabpush;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class MiniEnterTabPush extends BaseTest {

    /**
     * ！！！！！！运行前请重新安装用re-sign.jar签名的浏览器！！！！！！！！！ 1.小屏机，不登陆同步账号,查看 Tab
     * Push项，当前为网页页面，当前为Home页面，Tab Push选项为不可点击状态,Tab Push选项正常显示，处于可点击状态，
     * 进入海豚登陆界面: ·界面显示google，facebook，dolphin登陆按钮 ·界面的底部显示一个关于dolphin
     * extention的超链接
     *
     * @author xzhou
     */

    public void test_EnterTabPush() throws Exception {

        solo.sleep(5000);
        Boolean toast;
        View tabview;
        View v;
        View getView;

        solo.clickOnView(solo.getView("id/address_open_menu_more"));// 点击首页dolphin按钮

        solo.sleep(Resource.TIME_SMALL);
        v = solo.getText("Tab push");
        tabview = (View) v.getParent();
        solo.sleep(Resource.TIME_BIG);
        solo.clickOnView(tabview);// 点击tab push
        toast = solo.searchText("Add", true);// 搜索add判断页面没有跳转，说明不能点击
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("can click", toast);

        visitUrl("www.baidu.com/");// 访问sogou
        solo.sleep(20000);
        int fromY,toX,toY;
        fromY = toY = getActivity().getWindowManager().getDefaultDisplay().getHeight() / 2;
        toX = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        solo.drag(toX/2, toX/2, fromY/5, toY*4/5, 20);
//        solo.goBack();// 露出三个点，因为滑动操作不管用
        solo.clickOnView(solo.getView("id/address_open_menu_more"));// 点击网页dolphin按钮

        solo.sleep(Resource.TIME_SMALL);
        v = solo.getText("Tab push");
        tabview = (View) v.getParent();

        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(tabview);// 点击tab push
        toast = solo.searchText("Add", true);// 搜索add判断页面跳转了，说明可以点击
        assertTrue("cannot click", !toast);

        toast = solo.searchText("What is Dolphin Connect extension?");// 判断有超链接
        assertTrue("not link", toast);

        v = solo.getView("btn_login_google");
        toast = solo.waitForView(v);
        assertTrue("no google", toast);

        v = solo.getView("btn_login_facebook");
        toast = solo.waitForView(v);
        assertTrue("no facebook", toast);

        v = solo.getView("btn_login_dolphin");
        toast = solo.waitForView(v);
        assertTrue("no dolphin", toast);

        solo.clickOnView(v);// 点击dolphin
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Register");// 申请新账号
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/ds_email");// 输入Email
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText((EditText) solo.getView("id/ds_email"));
        solo.sleep(Resource.TIME_SMALL);
        int n = (int) (Math.random() * 1000);// 产生随机数
        int n1 = (int) (Math.random() * 100);
        int n2 = (int) (Math.random() * 10);
        String account = "18790032" + (n + n1 + n2) + "@123.com";
        solo.enterText((EditText) solo.getView("id/ds_email"), account);// 每次申请的账号不同

        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("id/ds_password");// 输入密码
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText((EditText) solo.getView("id/ds_password"));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText((EditText) solo.getView("id/ds_password"), "123456");
        solo.sleep(Resource.TIME_SMALL);

        solo.clickOnText("Register");// 确认申请
        int i = 0;
        toast = solo.waitForText("Network error", 1, 60000) ;
        Log.d("TEST", "---"+toast);
        while(toast && i < 5){
            solo.sleep(3000);
            solo.clickOnText("Register");
            toast = solo.waitForText("Network error", 1, 60000);
            i++;
        }
        assertTrue("Network error", !toast);
        toast = solo.searchText("No other devices");
        assertTrue("no message No other devices", toast);
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.searchText("Refresh");
        assertTrue("no message Refresh", toast);
        solo.sleep(Resource.TIME_SMALL);
        Log.i("TEST", "---ok");

    }
}
