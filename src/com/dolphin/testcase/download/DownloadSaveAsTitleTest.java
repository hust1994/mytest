package com.dolphin.testcase.download;

import android.util.Log;
import android.widget.TextView;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 新建下载任务
 * 下载弹框——Title
 * @author chhzhang
 *
 */
public class DownloadSaveAsTitleTest extends BaseTest{

	public void test_SaveAsTitle(){
		solo.sleep(Resource.TIME_BIG);
		//新建下载任务→点击save path文本框
		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		//查看弹框的Title
		assertTrue("Save as dialog has not showed successfully",
				solo.searchText("Save as"));
		Log.i("dialog_title",((TextView)solo.getView("alertTitle")).getText().toString());
		
		solo.sleep(Resource.TIME_BIG);
	}
}
