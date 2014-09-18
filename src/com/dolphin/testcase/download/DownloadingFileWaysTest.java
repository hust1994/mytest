package com.dolphin.testcase.download;

import android.view.KeyEvent;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;

/**
 * 下载文件的方式
 * @author chhzhang
 *
 */
public class DownloadingFileWaysTest extends BaseTest {

	String baidu="www.baidu.com";
	
	// 打开网页→长按地址栏→长按列表中点击Save Page选项
	public void test_DownloadedsPage() {

		solo.sleep(Resource.TIME_BIG);
		// 打开网页
		visitUrl(baidu);
		waitUrlOpen();
		solo.sleep(Resource.TIME_SMALL);
		Utils.scrollToTop(this);
		solo.sleep(Resource.TIME_SMALL);
		// 长按地址栏，选择"save page"
		clickLongOnViewAndPress(solo.getView("title_design"), 4);
		solo.sleep(Resource.TIME_SMALL);
		//下载弹框——Title
		assertTrue("Save as dialog has not showed successfully",
				solo.searchText("Save as"));

		solo.sleep(Resource.TIME_BIG);
	}
	
	//打开网页→长按网页中的URL超链接→长按列表中点击Save Link选项
	public void test_DownloadedsLink() {

		solo.sleep(Resource.TIME_BIG);
		// 打开网页
		visitUrl(baidu);
		waitUrlOpen();
		solo.sleep(Resource.TIME_SMALL);
		// 长按网页中的URL超链接→长按列表中点击Save Link选项
		solo.clickLongOnTextAndPress("贴吧", 2);
		solo.sleep(Resource.TIME_SMALL);
		//下载弹框——Title
     	assertTrue("Save as dialog has not showed successfully",solo.searchText("Save as"));

		solo.sleep(Resource.TIME_BIG);
	}
	
	//打开网页→长按网页中的图片超链接→长按列表中点击Save Image选项
	public void test_DownloadedsImage() {

		solo.sleep(Resource.TIME_BIG);
		// 打开网页
		visitUrl(baidu);
		waitUrlOpen();
		solo.sleep(Resource.TIME_Middle);
		// 长按网页中的图片超链接→长按列表中点击Save Image选项
		solo.clickLongOnScreen(265, 262, 3000);//“图片”	
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
		 inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
		solo.sleep(Resource.TIME_SMALL);
		//下载弹框——Title
     	assertTrue("Save as dialog has not showed successfully",solo.searchText("Save as"));

		solo.sleep(Resource.TIME_BIG);
	}
}
