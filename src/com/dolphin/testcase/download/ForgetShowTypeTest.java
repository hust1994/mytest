package com.dolphin.testcase.download;

/*
 * Author：Chunhui Zhang
 * 功能：下载管理-显示方式切换
 * 时间： 2014-7-15
 */

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-显示方式切换--退出下载界面——不会记住切换的显示方式
 * @author chhzhang
 *
 */
public class ForgetShowTypeTest extends BaseTest {

	// 退出下载界面——不会记住切换的显示方式
	public void test_ForgetShowType() {
		solo.sleep(Resource.TIME_BIG);
		// 下载界面前确认文件按照Type显示→点击手机的Back键
		Download.enterDownloadScreen(this);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		// 再次进入下载界面
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
