package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--长按已经下载完成的文件
 * @author chhzhang
 *
 */
public class LongPressDownloadedFileTest extends BaseTest{

	public void test_LongPressDownloadedFile(){
		solo.sleep(Resource.TIME_BIG);
		//长按已经下载的文件
		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickLongOnView(solo.getViewByPath("list[1]"));
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		
		//未登陆share中的Box的情况→点击Upload to Box

		//输入Box的用户名和密码登陆
		
		//选择需要上传的目录→点击左上角的对号
		
		//已经登陆share中Box的情况→点击Upload to Box
		
		//选择需要上传的目录→点击左上角的对号
		
		//点击share选项
//		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 1);
//		solo.sleep(Resource.TIME_SMALL);
//		solo.goBack();
//		solo.sleep(Resource.TIME_SMALL);
		
		//点击retry选项
//		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 2);
//		solo.sleep(Resource.TIME_SMALL);
//		while(solo.searchText("Downloading")){
//			solo.sleep(Resource.TIME_SMALL);
//		}
//		solo.sleep(Resource.TIME_Middle);
		//点击clear from list
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 3);
		solo.sleep(Resource.TIME_SMALL);
		
		//不勾选Remove source file点击Delete按钮p
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		
		//勾选Remove source file点击Delete按钮p
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 3);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("confirm_delete_download_checkbox");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		
		//点击Properties p
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 4);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("OK");
		solo.sleep(Resource.TIME_SMALL);
		
		//点击clear list p
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 5);
		solo.sleep(Resource.TIME_SMALL);
		assertTrue("Clear dialog don't appear", solo.searchText("Clear"));
		
		//点击Cancel按钮 p
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);
		
		//点击OK按钮 p
		clickLongOnViewAndPress(solo.getViewByPath("list[1]"), 5);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
