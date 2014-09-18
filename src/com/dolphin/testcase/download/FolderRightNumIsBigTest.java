package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-显示方式切换
 * @author chhzhang
 *
 */
public class FolderRightNumIsBigTest extends BaseTest {

	// 文件夹右侧的数字较长时的场景
	public void test_DownloadsNumIsBig() {
		solo.sleep(Resource.TIME_BIG);

		// 文件夹中的文件达到1000+的时候
		for (int i = 0; i < 1000; i++) {
			Download.setUpDownload(Download.downloaded, this);
			solo.sleep(Resource.TIME_SMALLEST);
		}
		Download.enterDownloadScreen(this);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
