package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——Time显示方式
 * 	正在下载——暂停按钮
 * @author chhzhang
 *
 */
public class PauseDownloadingTest extends BaseTest {

	// 正在下载——暂停按钮
	public void test_PauseDownloading() {
		solo.sleep(Resource.TIME_BIG);

		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);

		// 点击正在下载的文件右侧的按钮
		solo.clickOnView("control_button");
		// 不点击弹框中的任意按钮
		// 点击Cancel按钮

		//点击OK按钮
		
		solo.sleep(Resource.TIME_SMALL);
	}
}
