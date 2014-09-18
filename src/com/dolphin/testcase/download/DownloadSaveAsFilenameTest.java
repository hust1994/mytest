package com.dolphin.testcase.download;

import android.util.Log;
import android.widget.EditText;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 新建下载任务
 * 下载弹框——文件名
 * @author chhzhang
 *
 */
public class DownloadSaveAsFilenameTest extends BaseTest {

	public void test_SaveAsFilename() {
		solo.sleep(Resource.TIME_BIG);
		// 新建下载任务
		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		// 查看下载弹框中的文件名
		assertNotNull("Cannot show tv_file_name successfully",
				solo.getView("tv_file_name"));
		Log.i("fielname",((EditText)solo.getView("id/input")).getText().toString());
		
		solo.sleep(Resource.TIME_BIG);
	}
}
