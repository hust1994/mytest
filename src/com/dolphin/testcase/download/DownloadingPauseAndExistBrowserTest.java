package com.dolphin.testcase.download;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式
 * 正在下载——暂停下载后退出浏览器
 * @author chhzhang
 *
 */
public class DownloadingPauseAndExistBrowserTest extends BaseTest{

	public void test_DownloadingPauseAndExistBrowser(){
		solo.sleep(Resource.TIME_BIG);
        //点击暂停下载，退出浏览器
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("control_button");
		solo.sleep(Resource.TIME_SMALL);
		exitBrowser();
        solo.sleep(Resource.TIME_BIG);
	}
	
	public void exitBrowser(){
		solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_Middle);
        ViewGroup viewGroup = null;
        while(viewGroup == null) {
            viewGroup = (ViewGroup)getViewByClassName("PanelMenuTabBar", 1);
        }
        viewGroup = (ViewGroup)viewGroup.getChildAt(2);
        View view = viewGroup.getChildAt(0);
        Log.i("exit", view.toString());
        solo.clickOnView(view);
        solo.sleep(Resource.TIME_SMALL);
	}
}
