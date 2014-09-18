package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--查看横竖屏切换的场景
 * @author chhzhang
 *
 */
public class FoldersDirectionSwitchTest extends BaseTest{

	public void test_DirectionSwitch(){
		solo.sleep(Resource.TIME_BIG);
		//横竖屏切换
		setDirection("Landscape");
		setDirection("Portrait");
		
        solo.sleep(Resource.TIME_BIG);
	}
	
	public void setDirection(String direction){
		Download.switchOrientation(direction, this);
		solo.sleep(Resource.TIME_Middle);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.goBackToActivity("BrowserActivity");
		solo.sleep(Resource.TIME_SMALL);
	}
}
