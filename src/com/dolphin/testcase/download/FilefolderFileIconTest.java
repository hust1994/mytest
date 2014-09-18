package com.dolphin.testcase.download;

import junit.framework.Assert;
import android.widget.EditText;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--分类中文件的标识
 * @author chhzhang
 *
 */
public class FilefolderFileIconTest extends BaseTest {

	//分类中文件的标识
	//下载不同文件类型iMage,zip,video,ducement,audio,others
	public void test_FilefolderFileIcon(){
		solo.sleep(Resource.TIME_BIG);
		fileFolder(".bmp");
		fileFolder(".zip");
		fileFolder(".mp4");
		fileFolder(".doc");
		fileFolder(".mp3");
		fileFolder(".xxx");
		
		Download.enterDownloadScreen(this);
		
		solo.sleep(Resource.TIME_BIG);
	}
	
	public void fileFolder(String suffix) {

		visitUrl(Download.downloaded);
		solo.sleep(Resource.TIME_SMALL);
		boolean get = solo.waitForDialogToOpen();
	    solo.sleep(Resource.TIME_SMALLEST);
	    Assert.assertTrue("Network problem. The 'Save as' dialog doesn't apprear.", get);
		String input[] = ((EditText) solo.getView("input")).getText()
				.toString().split("[.]");
		solo.clearEditText((EditText)solo.getView("input"));
		solo.sleep(Resource.TIME_SMALL);
		solo.enterText((EditText) solo.getView("input"), input[0] + suffix);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
	}
}
