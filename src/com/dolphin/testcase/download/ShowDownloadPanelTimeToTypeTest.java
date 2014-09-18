package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-显示方式切换-按照时间显示列表中有多个下载任务时切换到按照Type显示
 * @author chhzhang
 *
 */
public class ShowDownloadPanelTimeToTypeTest extends BaseTest {

	// 按照时间显示列表中有多个下载任务时切换到按照Type显示
	public void test_DownloadShowTimeToType() {
		solo.sleep(Resource.TIME_BIG);

		// 进入程序中下载多个任务（4个以上）→进入下载管理界面→点击三点按钮选择Type
		for (int i = 0; i < 5; i++) {
			solo.sleep(Resource.TIME_SMALL);
			Download.setUpDownload(Download.downloading_url, this);
		}
		solo.sleep(Resource.TIME_SMALL);
		Download.enterDownloadScreen(this);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
