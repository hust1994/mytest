package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 剩余空间提示——文件管理——横屏
 * @author chhzhang
 *
 */
public class FilemanagerLandscapeTest extends BaseTest {

	public void test_FilemanagerLandscape() {
		solo.sleep(Resource.TIME_BIG);
		// 手机切换到竖屏→进入文件管理→查看剩余空间处的显示
		Download.switchOrientation("Landscape", this);
		solo.sleep(Resource.TIME_Middle);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_Middle);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		
	
		solo.sleep(Resource.TIME_BIG);
	}

}
