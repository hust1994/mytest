package com.dolphin.testcase.download;

import junit.framework.Assert;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FilefolderTestUtil {

	
	//确认对应分类文件夹的文件数是否正确
	public static void fileNumbers(BaseTest test,int i) {
		int num;
        String title = ((TextView)test.solo.getView("item_count",i)).getText().toString();
        num = Integer.parseInt(title.substring(1, title.length()-1));
        test.solo.clickOnView(test.solo.getView("category_title",i));
        if(num != getdownloadNum(test)) {
        	test.assertTrue("The count of downloaded items is not correct. " + "Actual result is " + getdownloadNum(test) + " and expect one is " + num, false);
        }
        test.solo.sleep(Resource.TIME_SMALL);
	}

	// 进入下载界面，以type形式显示
	public static void enterDownloadScreenType(BaseTest test) {
		Download.enterDownloadScreen(test);
		test.solo.clickOnView("btn_menu");
		test.solo.sleep(Resource.TIME_SMALL);
		test.solo.clickOnText("Type");
		test.solo.sleep(Resource.TIME_SMALL);
	}

	/*
	 * 进入文件管理界面
	 */
	public static void enterFilemanagerScreen(BaseTest test){
		Download.enterDownloadScreen(test);
		test.solo.sleep(Resource.TIME_SMALL);
	    test.solo.clickOnView(test.solo
	                .getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
	    test.solo.sleep(Resource.TIME_SMALL);
	}
	
	
	//
	public static void fileFolder(BaseTest test,String filefolder, String suffix) {

		test.visitUrl(Download.downloaded);
		test.solo.sleep(Resource.TIME_SMALL);
		boolean get = test.solo.waitForDialogToOpen();
	    test.solo.sleep(Resource.TIME_SMALLEST);
	    Assert.assertTrue("Network problem. The 'Save as' dialog doesn't apprear.", get);
		String input[] = ((EditText) test.solo.getView("input")).getText()
				.toString().split("[.]");
		test.solo.clearEditText((EditText) test.solo.getView("input"));
		test.solo.sleep(Resource.TIME_SMALL);
		test.solo.enterText((EditText) test.solo.getView("input"), input[0] + suffix);
		test.solo.sleep(Resource.TIME_SMALL);
		test.solo.clickOnView("button2");
		test.solo.sleep(Resource.TIME_SMALL);

		enterDownloadScreenType(test);
		//等待下载完成
		 String title = ((TextView)test.solo.getView("item_count",0)).getText().toString();
	     int  num = Integer.parseInt(title.substring(1, title.length()-1));
	     while(num!=0){
	    	 test.solo.sleep(Resource.TIME_SMALL);
	    	 num = Integer.parseInt(title.substring(1, title.length()-1));
	     }
		// 点击该文件夹
		test.solo.clickOnText(filefolder);
		test.solo.sleep(Resource.TIME_SMALL);
		// 点击二级目录中的返回按钮
		test.solo.clickOnView("back");
		test.solo.sleep(Resource.TIME_SMALL);
		// 再次进入该文件夹的二级目录→长按里面的任意文件
		test.solo.clickOnText(filefolder);
		test.solo.sleep(Resource.TIME_SMALL);
		test.solo.clickLongOnView(test.solo.getViewByPath("downloaded_list[0]"));
		test.solo.sleep(Resource.TIME_SMALL);
		test.solo.goBack();
		test.solo.sleep(Resource.TIME_SMALL);
		// 滑动文件列表
		test.solo.scrollToBottom();
	}

	/*
	 * 求得某个分类文件夹的文件数(可见的文件数）
	 */
	public static int getdownloadNum(BaseTest test) {
		int downloadNum;
		ViewGroup viewGroup = (ViewGroup) test.solo.getView("id/downloaded_list");
		downloadNum = viewGroup.getChildCount();
		return downloadNum;
	}
}
