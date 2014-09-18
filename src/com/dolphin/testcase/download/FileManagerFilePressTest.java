package com.dolphin.testcase.download;

import android.view.KeyEvent;
import android.widget.EditText;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 文件管理——长按文件管理中的文件P
 * @author chhzhang
 *
 */
public class FileManagerFilePressTest extends BaseTest {

	// 长按文件管理中的文件
	public void test_FileManagerFilePress() {
		solo.sleep(Resource.TIME_BIG);

		Download.enterDownloadScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo
				.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
		solo.sleep(Resource.TIME_SMALL);
		// 长按文件路径下的文件
		solo.clickLongOnView(solo.getViewByPath("list[0]"));
		solo.sleep(Resource.TIME_SMALL);
		// 点击rename
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
		solo.sleep(Resource.TIME_SMALL);
		// 点击Cancel按钮
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);

		// 清除原始的名称，输入新的名称→点击OK按钮
		clickLongOnViewAndPress(solo.getViewByPath("list[0]"), 0);
		solo.sleep(Resource.TIME_SMALL);
		// 判断重命名编辑框是否弹出
		assertTrue("Rename dialog has not showed successfully",
				solo.searchText("Rename"));
		// 给文件重命名
		solo.clearEditText((EditText) solo.getView("et_input_name"));
		solo.sleep(Resource.TIME_SMALL);
		solo.enterText((EditText) solo.getView("et_input_name"), "test10.apk");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		// 判断文件是否重命名成功
		assertTrue("Downloaded file not rename successfully",
				solo.searchText("test10.apk"));
		// 删除重命名文件
		solo.clickLongOnTextAndPress("test10.apk", 1);
		solo.sleep(Resource.TIME_SMALL);
		// 点击Cancel按钮
		solo.clickOnView("button1");
		solo.sleep(Resource.TIME_SMALL);
		// 点击Delete按钮
		solo.clickLongOnTextAndPress("test10.apk", 1);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		assertFalse("File has not cleared successfully",
				solo.searchText("test10.apk"));

		// 点击Properties
		clickLongOnViewAndPress(solo.getViewByPath("list[0]"), 2);
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("OK");
		
		solo.sleep(Resource.TIME_BIG);

	}
}
