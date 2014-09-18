package com.dolphin.testcase.download;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理与下载管理界面切换——有下载任务时尝试退出浏览器 p
 * @author chhzhang
 *
 */
public class FileManagerAndlExistBrowserTest extends BaseTest {

	// 当前为文件管理界面
	public void test_FileManagerAndlExistBrowser() {
		solo.sleep(Resource.TIME_BIG);

		// 有下载任务正在下载→切换到文件管理的Tab→退出下载模块，点击工具栏中的退出按钮→提示有下载任务的提示框中点击View
		// downloads按钮???
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo
				.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		exitBrowser();
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);

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
