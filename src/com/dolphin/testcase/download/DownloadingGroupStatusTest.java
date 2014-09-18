package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--各个分组的状态确认——展开与收缩状态
 * @author chhzhang
 *
 */
public class DownloadingGroupStatusTest extends BaseTest{

	public void test_DownloadingGroupStatus(){
		solo.sleep(Resource.TIME_BIG);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
		//点击任意一个分组左侧的圆圈按钮使分组展开
		solo.clickOnText("Yesterday");
		solo.sleep(Resource.TIME_SMALL);
		
		//再次点击任意分组左侧的圆圈按钮
		solo.clickOnText("Yesterday");
		solo.sleep(Resource.TIME_SMALL);
		
		//再次点击任意分组左侧的圆圈按钮
		solo.clickOnText("Yesterday");
		solo.sleep(Resource.TIME_SMALL);
		
		//多次快速重复收缩与展开的操作
		for(int i=0;i<6;i++){
			solo.clickOnText("Yesterday");
			solo.sleep(Resource.TIME_SMALL);
		}
		//展开所有的分组→点击手机的Back键退出下载界面——再次进入下载界面
		if(solo.searchText("Today")){
			solo.clickOnText("Today");
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
		
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
