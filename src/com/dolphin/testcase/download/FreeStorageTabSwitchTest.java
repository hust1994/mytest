package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 剩余空间的显示条——文件管理与下载管理Tab切换 p
 * @author chhzhang
 *
 */
public class FreeStorageTabSwitchTest extends BaseTest {

	public void test_FreeStorageTabSwitch() {
		solo.sleep(Resource.TIME_BIG);
		//点击下载管理界面的Tab→查看底部的剩余空间显示条
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][0]"));
		solo.sleep(Resource.TIME_SMALL);
		
		//点击文件管理的Tab
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		
		//点击下载管理的Tab 
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][0]"));
		solo.sleep(Resource.TIME_SMALL);
		
		//按照上面的步骤进行多次切换两个界面
		for(int i=0;i<10;i++){
			//点击文件管理的Tab
			solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
			solo.sleep(Resource.TIME_SMALL);
			
			//点击下载管理的Tab 
			solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][0]"));
			solo.sleep(Resource.TIME_SMALL);
		}
		
		solo.sleep(Resource.TIME_BIG);
	}

}
