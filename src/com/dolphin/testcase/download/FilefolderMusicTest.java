package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理——各个文件夹的定义--音乐（Music）  pass
 * @author chhzhang
 *
 */
public class FilefolderMusicTest extends BaseTest {

	// 音乐（Music）
	public void test_FilefolderMusic() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以下的格式：
		// （*.3gpp, *.snd, *.mid, *.midi, *.kar, *.mpga, *.mpega, *.mp2, *.mp3,
		// *.m4a, *.m3u, *.sid, *.aif, //*.aiff, *.aifc, *.gsm, *.m3u, *.wma,
		// *.wax, *.ra, *.rm, *.ram, *.ra, *.pls, *.sd2, *.wav）
		// →下载完成后查看文件的分组
		FilefolderTestUtil.fileFolder(this,"Music", ".mp3");

		solo.sleep(Resource.TIME_BIG);
	}
}
