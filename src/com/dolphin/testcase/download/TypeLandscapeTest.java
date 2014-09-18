package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-Type显示方式p
 * 显示方式确认——横屏
 * @author chhzhang
 *
 */
public class TypeLandscapeTest extends BaseTest {

	//显示方式确认——横屏
	public void test_TypeLandscape(){
		solo.sleep(Resource.TIME_BIG);
		//查看横屏时界面的文件显示布局
		Download.switchOrientation("Landscape", this);
		solo.sleep(Resource.TIME_SMALL);
		Download.enterDownloadScreen(this);
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
