package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理与下载管理界面切换——退出下载界面  p
 * @author chhzhang
 *
 */
public class FileManagerPanelExistTest extends BaseTest {

	// 当前为文件管理界面退出下载模块
	public void test_FileManagerPanelExist() {
		solo.sleep(Resource.TIME_BIG);

		// 进入下载模块→切换到文件管理的Tab→退出下载界面→再次进入下载界面
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo
				.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}

}
