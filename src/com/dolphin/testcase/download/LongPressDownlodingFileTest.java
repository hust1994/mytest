package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式
 *   正在下载——长按正在下载的文件
 * @author chhzhang
 *
 */
public class LongPressDownlodingFileTest extends BaseTest {

	// 正在下载——长按正在下载的文件
	public void test_PressDownlodingFile() {
		solo.sleep(Resource.TIME_BIG);
		Download.clearAllDownloadInfo(this);
		
		// 长按正在下载的文件,点击Cancel download
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 0);
		solo.sleep(Resource.TIME_Middle);
		
		// 有一个下载任务正在下载→点击Cancel all download
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 1);
		solo.sleep(Resource.TIME_SMALL);
		
		// 有多个下载任务正在下载时→点击Cancel all download
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 1);
		solo.sleep(Resource.TIME_SMALL);
		
		// 点击Cancel
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		
		// 点击OK
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 1);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
