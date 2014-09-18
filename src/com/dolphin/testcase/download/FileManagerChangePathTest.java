package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理——改变存储路径 p
 * @author chhzhang
 *
 */
public class FileManagerChangePathTest extends BaseTest {

	//改变存储路径
	public void test_FileManagerChangePath(){
		solo.sleep(Resource.TIME_BIG);
		// 进入下载界面→点击右边的File manager
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		//点击存储路径的返回按钮，选择合适的路径
		while (solo.getView("btn_change_path").isEnabled()) {
			// 点击返回上一级按钮
			solo.clickOnView("btn_change_path");
			solo.sleep(Resource.TIME_SMALL);
		}
		
		solo.clickInList(1,0);
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}

}
