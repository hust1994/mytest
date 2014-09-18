package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理——文件管理可进入路径确认 p
 * @author chhzhang
 *
 */
public class FileManagerRootPathTest extends BaseTest {

	//文件管理可进入路径确认
	public void test_FileManagerRootPath(){
		solo.sleep(Resource.TIME_BIG);
		// 进入下载界面→点击右边的File manager
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		
		//点击返回键后退，直至不能再后退
		while (solo.getView("btn_change_path").isEnabled()) {
			// 点击返回上一级按钮
			solo.clickOnView("btn_change_path");
			solo.sleep(Resource.TIME_SMALL);
		}
		
		solo.sleep(Resource.TIME_BIG);
	}
}
