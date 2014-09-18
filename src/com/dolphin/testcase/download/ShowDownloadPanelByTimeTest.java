package com.dolphin.testcase.download;

import junit.framework.Assert;
import android.view.KeyEvent;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理-显示方式切换 pass
 * 显示方式——按照时间显示
 * @author chhzhang
 *
 */
public class ShowDownloadPanelByTimeTest extends BaseTest {
	
	//进入下载管理界面
	public void DownloadShowByTime() {

		solo.sleep(Resource.TIME_BIG);
		// 进入下载页面
		solo.sendKey(KeyEvent.KEYCODE_MENU);
		solo.sleep(Resource.TIME_SMALL);
		Assert.assertTrue("Menu has not come out.",
				solo.searchText("Downloads"));
		solo.clickOnText("Downloads");
		// 判断当前页面为下载页面
		assertTrue("Downloads page not open successfully",
				solo.waitForActivity("BrowserDownloadPage", Resource.TIME_BIG));
		
		solo.sleep(Resource.TIME_BIG);
	}
}
