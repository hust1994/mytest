package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理-显示方式切换-展开选项收缩方式
 * @author chhzhang
 *
 */
public class DownloadPanelTypeExpandTest extends BaseTest {

	// 展开选项收缩方式
	public void test_DownloadTypeExpand() {
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
		// 选项展开后再次点击三点按钮
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		// 选项展开后，点击三点按钮以外的任意区域
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnScreen(50, 50);
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
