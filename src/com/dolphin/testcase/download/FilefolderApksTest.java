package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——各个文件夹的定义--应用(APKs)  pass
 * @author chhzhang
 *
 */
public class FilefolderApksTest extends BaseTest {

	// 应用(APKs)
	public void test_FilefolderAPKs() {
		solo.sleep(Resource.TIME_BIG);
		// 下载APK文件
		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);

		FilefolderTestUtil.enterDownloadScreenType(this);
		// 点击该文件夹
		solo.clickOnText("APKs");
		solo.sleep(Resource.TIME_SMALL);
		// 点击二级目录中的返回按钮
		solo.clickOnView("back");
		solo.sleep(Resource.TIME_SMALL);
		// 再次进入该文件夹的二级目录→长按里面的任意文件
		solo.clickOnText("APKs");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickLongOnView(solo.getViewByPath("downloaded_list[0]"));
		solo.sleep(Resource.TIME_SMALL);
		// 滑动文件列表
		solo.goBack();
		solo.scrollListToBottom(0);
		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
