package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式
 * 正在下载——最大并行下载数
 * @author chhzhang
 *
 */
public class DownloadingNumLargestTest extends BaseTest{

	public void test_DownloadingNumLargest(){
		solo.sleep(Resource.TIME_BIG);
        //打开网页www.hao123.com,选择游戏分类，点击链接进入推介游戏栏里，下载游戏(apk文件)，同时下载7个文件
		for(int i=0;i<7;i++)
		{
			Download.setUpDownload(Download.downloading_url, this);
			solo.sleep(Resource.TIME_SMALL);
		}
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
