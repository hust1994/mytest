package com.dolphin.testcase.download;

import android.widget.EditText;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--修改任意分类中文件的后缀 p
 * @author chhzhang
 *
 */
public class FilefolderFileSuffixTest extends BaseTest {

	public void test_FilefolderFileSuffix(){
		solo.sleep(Resource.TIME_BIG);
		//修改任意分类中文件的后缀（参照Dev的实现逻辑???
		FilefolderTestUtil.enterDownloadScreenType(this);
		solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
        solo.sleep(Resource.TIME_SMALL);
        // 长按第一个文件并给它重命名
        clickLongOnViewAndPress(solo.getView("icon_type", 0), 0);
        solo.sleep(Resource.TIME_SMALL);
        // 判断重命名编辑框是否弹出
        assertTrue("Rename dialog has not showed successfully",
                solo.searchText("Rename"));
        // 给文件重命名
        String input[] = ((EditText) solo.getView("et_input_name")).getText()
				.toString().split("[.]");
        solo.clearEditText((EditText) solo.getView("et_input_name"));
        solo.sleep(Resource.TIME_SMALL);
        solo.enterText((EditText) solo.getView("et_input_name"), input[0]+".xxx");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL);
        
        solo.clickOnView(solo.getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][0]"));
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
        solo.sleep(Resource.TIME_SMALL);
        
        solo.sleep(Resource.TIME_BIG);
        
	}
	
	
}
