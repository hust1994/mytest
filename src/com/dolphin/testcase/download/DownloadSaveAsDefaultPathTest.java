package com.dolphin.testcase.download;

import android.util.Log;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 新建下载任务
 * 下载弹框——默认的保存路径:
 * @author chhzhang
 *
 */
public class DownloadSaveAsDefaultPathTest extends BaseTest {

	public void test_SaveAsDefaultPathTest() {
		solo.sleep(Resource.TIME_BIG);
		// 新建下载任务
		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		// 查看下载弹框中的默认的保存路径
		assertNotNull("Cannot show btn_save_path successfully",
				solo.getView("btn_save_path"));
		Log.i("path",solo.getButton("btn_save_path").getText().toString());
		
		solo.sleep(Resource.TIME_BIG);
	}
}
