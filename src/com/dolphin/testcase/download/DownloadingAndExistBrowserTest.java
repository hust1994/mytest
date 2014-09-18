package com.dolphin.testcase.download;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 正在下载——退出浏览器
 * @author chhzhang
 *
 */
public class DownloadingAndExistBrowserTest extends BaseTest{

	public void test_DownloadingAndExistBrowser(){
		solo.sleep(Resource.TIME_BIG);
		//下载未完成时，点击退出程序
		Download.setUpDownload(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		exitBrowser();
        assertTrue("Reminder dialog failed", solo.searchText("Reminder"));
        
        //点击View Downloads按钮
        solo.clickOnView("button1");
        solo.sleep(Resource.TIME_SMALL);
        
        //点击Exit按钮
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        exitBrowser();
        //solo.clickOnView("button2");
        
        //再次启动程序→进入下载列表中
        //点击暂停按钮
        
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
