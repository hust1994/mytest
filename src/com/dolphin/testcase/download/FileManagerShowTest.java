package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理——文件管理界面的显示P
 * @author chhzhang
 *
 */
public class FileManagerShowTest extends BaseTest {

	// 文件管理界面的显示
	public void test_FileManagerShow() {
		solo.sleep(Resource.TIME_BIG);
		// 进入下载界面→点击右边的File manager
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
