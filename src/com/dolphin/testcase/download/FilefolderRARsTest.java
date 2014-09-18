package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--压缩包(RARs)  pass
 * @author chhzhang
 *
 */
public class FilefolderRARsTest extends BaseTest {

	// 压缩包(RARs)
	public void test_FilefolderRARs() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以下的格式：
		// （*.rar,*.zip, *.7z, *.gzip, *.z, *.tar, *.cab, *.jar, *.iso ）
		// →下载完成后查看文件的分组
		FilefolderTestUtil.fileFolder(this, "RARs", ".rar");

		solo.sleep(Resource.TIME_BIG);
	}
}
