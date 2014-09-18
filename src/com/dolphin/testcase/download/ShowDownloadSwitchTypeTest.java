package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理-显示方式切换p
 * 显示方式切换
 * @author chhzhang
 *
 */
public class ShowDownloadSwitchTypeTest extends BaseTest {

	// 显示方式切换
	public void test_DownloadsShowTypesSwitch() {
		solo.sleep(Resource.TIME_BIG);
		// 进入下载管理界面
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);

		// 显示方式切换
		// 点击下载管理界面右下方的三点组成的按钮
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
