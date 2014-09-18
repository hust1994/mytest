package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--其他(Others)  pass
 * @author chhzhang
 *
 */
public class FilefolderOthersTest extends BaseTest {

	// 其他(Others)
	public void test_FilefolderOthers() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以上7个分类中以外的文件格式
		FilefolderTestUtil.fileFolder(this,"Others", ".xxx");

		solo.sleep(Resource.TIME_BIG);
	}
}
