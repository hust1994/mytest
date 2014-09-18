package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--Downloading分组较长时-其他分组的显示
 * @author chhzhang
 *
 */
public class DownloadingFolderFileNumBigTest extends BaseTest{

	public void test_DownloadingGroupBig(){
		solo.sleep(Resource.TIME_BIG);
		//确保Downloading分组中有超过一屏的下载任务正在下载中
		for(int i=0;i<10;i++){
			Download.setUpDownload(Download.downloading_url, this);
			solo.sleep(Resource.TIME_SMALL);
		}
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
