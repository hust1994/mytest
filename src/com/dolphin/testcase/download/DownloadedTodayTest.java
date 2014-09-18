package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--有下载完成的文件——“今天”分组
 * @author chhzhang
 *
 */
public class DownloadedTodayTest extends BaseTest{

	public void test_DownloadedToday(){
		solo.sleep(Resource.TIME_BIG);
		//只有下载完成的文件，没有正在下载的文件时→进入下载界面
		Download.setUpDownload(Download.downloaded, this);
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
