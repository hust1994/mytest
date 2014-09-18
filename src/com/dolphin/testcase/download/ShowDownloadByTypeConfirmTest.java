package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-Type显示方式p--显示方式确认
 * @author chhzhang
 *
 */
public class ShowDownloadByTypeConfirmTest extends BaseTest {

	// 显示方式确认
	public void test_TypeConfirm() {
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		//切换为按照Type显示
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
