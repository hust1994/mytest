package com.dolphin.testcase.download;

import junit.framework.Assert;
import android.view.KeyEvent;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--皮肤(Themes)  pass
 * @author chhzhang
 *
 */
public class FilefolderThemesTest extends BaseTest {

	// 皮肤(Themes)
	public void test_FilefolderThemes() {
		solo.sleep(Resource.TIME_BIG);
		// 下载程序中的壁纸（*.dwp）
		solo.sendKey(KeyEvent.KEYCODE_MENU);
		solo.sleep(Resource.TIME_SMALL);
		Assert.assertTrue("Menu has not come out.", solo.searchText("Themes"));
		solo.clickOnText("Themes");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView(solo.getView("skin_icon", 6));
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);

		FilefolderTestUtil.enterDownloadScreenType(this);
		// 点击该文件夹 
		solo.clickOnText("Themes");
		solo.sleep(Resource.TIME_SMALL);
		// 点击二级目录中的返回按钮
		solo.clickOnView("back");
		solo.sleep(Resource.TIME_SMALL);
		// 再次进入该文件夹的二级目录→长按里面的任意文件
		solo.clickOnText("Themes");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickLongOnView(solo.getViewByPath("downloaded_list[0]"));
		solo.sleep(Resource.TIME_SMALL);
		// 滑动文件列表
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);
		solo.scrollToBottom();
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}
}
