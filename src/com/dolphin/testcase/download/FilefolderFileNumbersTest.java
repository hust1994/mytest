package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--各个分类文件夹扫描的文件数确认  pass
 * @author chhzhang
 *
 */
public class FilefolderFileNumbersTest extends BaseTest {

	// 各个分类文件夹扫描的文件数确认
	public void test_FileNumbersConfirm() {
		solo.sleep(Resource.TIME_BIG);
		// 依次点击文件夹进入其二级目录中→统计其中的文件个数
		FilefolderTestUtil.enterDownloadScreenType(this);
		for (int i = 0; i < 9; i++) {
			FilefolderTestUtil.fileNumbers(this,i);
			solo.goBack();
			solo.sleep(Resource.TIME_SMALL);
		}

		solo.sleep(Resource.TIME_BIG);
	}

}
