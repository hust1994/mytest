package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——各个文件夹的定义--正在下载（Downloading） pass
 * @author chhzhang
 *
 */
public class FilefolderDownloadingTest extends BaseTest {

	// 正在下载（Downloading）
	public void test_FilefolderDownloading() {
		solo.sleep(Resource.TIME_BIG);
		for(int i=0;i<10;i++){
			Download.setUpDownload(Download.downloaded, this);
			solo.sleep(Resource.TIME_SMALL);
		}
		FilefolderTestUtil.enterDownloadScreenType(this);
		// 文件夹的定义
		// 点击该文件夹
		solo.clickOnText("Downloading");
		solo.sleep(Resource.TIME_SMALL);
		// 点击二级目录中的返回按钮
		solo.clickOnView("back");
		solo.sleep(Resource.TIME_SMALL);
		// 再次进入该文件夹的二级目录→长按里面的任意文件
		solo.clickOnText("Downloading");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickLongOnView(solo.getViewByPath("downloaded_list[0]"));
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		
		// 滑动文件列表
		solo.scrollToBottom();

		// 该文件夹中有下载任务时进入该文件夹→等待文件下载完成
        while(!solo.searchText(".apk")){
        	solo.sleep(Resource.TIME_SMALL);
        }
        solo.sleep(Resource.TIME_SMALL);
		solo.goBackToActivity("BrowserActivity");
		solo.sleep(Resource.TIME_SMALL);
		
		// 该文件夹中有下载任务时进入该文件夹→长按任意文件→选择取消所有的任务
        Download.setUpDownload(Download.downloading_url, this);
        solo.sleep(Resource.TIME_SMALL);
        Download.setUpDownload(Download.downloading_url, this);
        solo.sleep(Resource.TIME_SMALL);
        FilefolderTestUtil.enterDownloadScreenType(this);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Downloading");
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getViewByPath("downloaded_list[0]"),1);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);
        
		assertTrue("There still are some downloading", solo.searchText("No download entries found"));
		
		solo.sleep(Resource.TIME_BIG);
	}
}
