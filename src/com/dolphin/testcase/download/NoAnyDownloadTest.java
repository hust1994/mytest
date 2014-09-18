package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——Time显示方式
 * 	没有任何的下载文件时
 * @author chhzhang
 *
 */
public class NoAnyDownloadTest extends BaseTest {

	// 没有任何的下载文件时
	public void test_NoDownload() {
		solo.sleep(Resource.TIME_BIG);
		
		Download.clearAllDownloadInfo(this);
		// 启动程序→直接进入下载界面→查看界面显示的内容
		Download.enterDownloadScreen(this);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
