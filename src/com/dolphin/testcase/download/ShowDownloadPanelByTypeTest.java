package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-显示方式切换p
 * 显示方式——按照类型显示
 * @author chhzhang
 *
 */
public class ShowDownloadPanelByTypeTest extends BaseTest {

	// 显示方式——按照类型显示
	public void test_DownloadsShowTypesSwitch() {
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		//点击Type
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);
		//点击三点组成的按钮
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
