package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--仅有正在下载的任务向有下载完成的任务过渡
 * @author chhzhang
 *
 */

public class WaitDownloadingFinishedTest extends BaseTest{

	public void test_WaitDownloadingFinished(){
		solo.sleep(Resource.TIME_BIG);
		Download.clearAllDownloadInfo(this);
		solo.sleep(Resource.TIME_SMALL);
		Download.downloads(Download.downloading_url, this);
		solo.sleep(Resource.TIME_SMALL);
        //等待上述的文件下载完成→查看界面的变化
		while(solo.searchText("Downloading")){
			solo.sleep(Resource.TIME_SMALL);
		}
		
		assertFalse("Downloading not disapear", solo.searchText("Downloading"));
		
		//特定的场景：手机的系统时间调整为当天的23:59分
		//→下载一个小文件→当该文件在第二天的00:00点下载完成时，查看文件在下载管理中的分类
		
        solo.sleep(Resource.TIME_BIG);
	}
}
