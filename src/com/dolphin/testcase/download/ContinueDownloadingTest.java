package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式
 * 	     正在下载——继续下载按钮
 * @author chhzhang
 *
 */
public class ContinueDownloadingTest extends BaseTest {

	// 正在下载——继续下载按钮
	public void test_ContinueDownloading() {
		solo.sleep(Resource.TIME_BIG);

		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		// 点击正在下载的文件右侧的按钮
		solo.clickOnView("control_button");
		solo.sleep(Resource.TIME_SMALL);
		
		// 点击已经暂停下载的文件的继续下载按钮
		solo.clickOnView("control_button");
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
