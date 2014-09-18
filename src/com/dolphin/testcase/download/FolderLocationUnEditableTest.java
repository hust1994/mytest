package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-文件夹的位置不可更改
 * @author chhzhang
 *
 */
public class FolderLocationUnEditableTest extends BaseTest {

	// 文件夹的名称不可修改
	public void test_FolderLocationUnEditable() {
		solo.sleep(Resource.TIME_BIG);

		//尝试进行各种长按或者是其他的操作
		FilefolderTestUtil.enterDownloadScreenType(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickLongOnText("APKs");
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
