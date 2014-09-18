package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——Time显示方式
 * 	有下载文件——仅有正在下载的文件
 * @author chhzhang
 *
 */
public class OnlyDownloadingTest extends BaseTest {

	// 有下载文件——仅有正在下载的文件
	public void test_OnlyDownloading() {
		solo.sleep(Resource.TIME_BIG);
		Download.clearAllDownloadInfo(this);
		
		// 仅有正在下载的文件,没有下载完成的文件时→进入下载界面查看分组情况
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
