package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 剩余空间提示——下载管理——竖屏
 * @author chhzhang
 *
 */
public class DownloadPortraitTest extends BaseTest {

	public void test_DownloadPortrait() {
		solo.sleep(Resource.TIME_BIG);
		// 手机切换到竖屏→进入下载管理→查看剩余空间处的显示
		Download.switchOrientation("Portrait", this);
		solo.sleep(Resource.TIME_Middle);
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
	
		solo.sleep(Resource.TIME_BIG);
	}

}
