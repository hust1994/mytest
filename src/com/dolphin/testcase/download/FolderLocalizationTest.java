package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 下载管理-本地化相关
 * @author chhzhang
 *
 */
public class FolderLocalizationTest extends BaseTest {

	// 本地化相关
	public void test_DownloadLocalization() {
		solo.sleep(Resource.TIME_BIG);

		// 浏览器切换到不同的语言→查看每种语言下文件夹的名称显示
		Download.enterSettingScreen(this);
		solo.sleep(Resource.TIME_SMALL);
		// 点击Language
		solo.clickOnView(solo.getViewByPath("list[1][0][0]"));
		solo.sleep(Resource.TIME_SMALL);

		// 1.跟随系统
		solo.clickOnView(solo.getViewByPath("list_view[3]"));
		solo.sleep(Resource.TIME_SMALL);
		// 重启
//		solo.clickOnView("button2");
//		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}
}
