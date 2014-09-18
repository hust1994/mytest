package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-Type显示方式
 * 竖屏很横屏多次切换
 * @author chhzhang
 *
 */
public class TypeDirectionSwitchTest extends BaseTest {

	//竖屏很横屏多次切换
	public void test_TypeDirectionSwitch(){
		solo.sleep(Resource.TIME_BIG);
		//横竖屏多次切换
		for(int i=0;i<3;i++){
			setDirection("Landscape");	

			setDirection("Portrait");
		}
	}
	
	public void setDirection(String direction){
		Download.switchOrientation(direction, this);
		solo.sleep(Resource.TIME_Middle);
		Download.enterDownloadScreen(this);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);
		solo.goBackToActivity("BrowserActivity");
		solo.sleep(Resource.TIME_SMALL);
	}
}
