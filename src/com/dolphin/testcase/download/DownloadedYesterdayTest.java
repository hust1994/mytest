package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--有下载完成的文件——“昨天”分组
 * @author chhzhang
 *
 */
public class DownloadedYesterdayTest extends BaseTest {

	public void test_DownloadedYesterday() {
		solo.sleep(Resource.TIME_BIG);
		// 进入手机的系统设置中将系统的时间向后移一天→再次进入下载界面
		// solo.getCurrentActivity().startActivity(new
		// Intent("com.android.settings.DateTimeSettingsSetupWizard"));//手动设置
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		
		// 此时下载新的文件→进入下载界面
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
		
		// 等待上述的文件下载完成→查看界面的变化
		while(solo.searchText("Downloading")){
			solo.sleep(Resource.TIME_SMALL);
		}
		
		solo.sleep(Resource.TIME_BIG);
	}
}
