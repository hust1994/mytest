package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 剩余空间提示——下载管理——横屏
 * @author chhzhang
 *
 */
public class DownloadLandscapeTest extends BaseTest {

	public void test_DownloadLandscape() {
		solo.sleep(Resource.TIME_BIG);
		// 手机切换到横屏→进入下载管理→查看剩余空间处的显示
		Download.switchOrientation("Landscape", this);
		solo.sleep(Resource.TIME_Middle);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
	
		solo.sleep(Resource.TIME_BIG);
	}

}
