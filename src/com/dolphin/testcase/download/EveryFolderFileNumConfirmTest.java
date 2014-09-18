package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--各个分组的数字统计确认
 * @author chhzhang
 *
 */
public class EveryFolderFileNumConfirmTest extends BaseTest{

	public void test_DownloadingGroupNumConfirm(){
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		//展开各个分组→查看分组中的文件个数
		if(solo.searchText("Today")){
			solo.clickOnText("Today");
			solo.sleep(Resource.TIME_SMALL);
		}
		if(solo.searchText("Yesterday")){
			solo.clickOnText("Yesterday");
			solo.sleep(Resource.TIME_SMALL);
		}
		if(solo.searchText("Last 7 days")){
			solo.clickOnText("Last 7 days");
			solo.sleep(Resource.TIME_SMALL);
		}
		if(solo.searchText("Last month")){
			solo.clickOnText("Last month");
			solo.sleep(Resource.TIME_SMALL);
		}
		if(solo.searchText("Older")){
			solo.clickOnText("Older");
			solo.sleep(Resource.TIME_SMALL);
		}
		
		//Downloading分组中的文件下载完成后
		solo.goBack();
		Download.downloads(Download.downloaded, this);
		while(solo.searchText("Downlonding")){
			solo.sleep(Resource.TIME_SMALL);
		}
		
		//Downloading以外的其他分组→删除分组中的部分文件
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 3);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
        solo.sleep(Resource.TIME_BIG);
	}
}
