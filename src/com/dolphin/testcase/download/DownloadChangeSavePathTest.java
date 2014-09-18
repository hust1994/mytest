package com.dolphin.testcase.download;

import android.util.Log;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载弹框——修改文件保存路径 
 * @author chhzhang
 *
 */
public class DownloadChangeSavePathTest extends BaseTest{

	public void test_ChangeSavePath(){
		solo.sleep(Resource.TIME_BIG);
		//新建下载任务→点击save path文本框
		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		
		// 点击savepath按钮
		solo.clickOnView("btn_save_path");
		assertTrue("Cannot show btn_save_path successfully",
				solo.searchText("download"));
		solo.sleep(Resource.TIME_BIG);

		// 点击返回上一级按钮
		solo.clickOnView("btn_change_path");
		solo.sleep(Resource.TIME_SMALL);

		solo.clickInList(8, 0);
		solo.sleep(Resource.TIME_SMALL);

		// 点击对号按钮
		solo.clickOnView("btn_confirm");
		solo.sleep(Resource.TIME_BIG);
		Log.i("path",solo.getButton("btn_save_path").getText().toString());
		
		//路径选择界面一直点击后退按钮直到不能后退为止
		solo.clickOnView("btn_save_path");
		while (solo.getView("btn_confirm").isEnabled()) {
			// 点击返回上一级按钮
			solo.clickOnView("btn_change_path");
			solo.sleep(Resource.TIME_SMALL);
		}
		
		solo.sleep(Resource.TIME_BIG);
	}
}
