package com.dolphin.testcase.download;

/*
 * Author：Chunhui Zhang
 * 功能：下载管理——各个文件夹的定义--件夹中的文件个数变化确认P
 * 时间： 2014-7-11
 */

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--件夹中的文件个数变化确认P
 * @author chhzhang
 *
 */
public class FilefolderFileNumChangeTest extends BaseTest {

	//文件夹中的文件个数变化确认???
	public void test_FilefolderFileNumChange(){
		solo.sleep(Resource.TIME_BIG);
		//删除文件夹中的文件
		FilefolderTestUtil.enterDownloadScreenType(this);
		solo.clickOnText("APKs");
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getViewByPath("downloaded_list[0]"), 3);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("confirm_delete_download_checkbox");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("back");
		solo.sleep(Resource.TIME_SMALL);
		
		//下载新的文件
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		Download.setUpDownload(Download.downloaded, this);
		solo.sleep(Resource.TIME_SMALL);
		FilefolderTestUtil.enterDownloadScreenType(this);
		
		solo.sleep(Resource.TIME_BIG);
		
	}
}
