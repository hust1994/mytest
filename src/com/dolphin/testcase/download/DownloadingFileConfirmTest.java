package com.dolphin.testcase.download;

import android.util.Log;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——Time显示方式
 * 	正在下载——显示的内容确认
 * @author chhzhang
 *
 */
public class DownloadingFileConfirmTest extends BaseTest {

	// 正在下载——显示的内容确认
	public void test_DownloadingShow() {
		solo.sleep(Resource.TIME_BIG);
		
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		// 查看正在下载的文件
		Log.i("filename", ((TextView) solo.getView("download_title", 0)).getText()
				.toString());
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
