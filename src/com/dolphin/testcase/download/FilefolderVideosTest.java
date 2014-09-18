package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——各个文件夹的定义--视频（Videos）  pass
 * @author chhzhang
 *
 */
public class FilefolderVideosTest extends BaseTest {

	// 视频（Videos）
	public void test_FilefolderVideos() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以下的格式：
		// （*.3gpp,*.3gp, *.3g2, *.dl, *.dif, *.dv, *.fli, *.m4v, *.mpeg,
		// *.mpg,*.mpe,*.mp4, *.VOB, //*.qt, *.mov, *.mxu, *.lsf, *.lsx, *.mng,
		// *.asf,*.asx, *.wm, *.wmv, *.wmx, *.wvx, *.avi, *.movie）
		// →下载完成后查看文件的分组
		FilefolderTestUtil.fileFolder(this,"Videos", ".mp4");

		solo.sleep(Resource.TIME_BIG);
	}
}
