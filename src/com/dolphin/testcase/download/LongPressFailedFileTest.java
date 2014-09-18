package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--正在下载——长按下载失败的文件
 * @author chhzhang
 *
 */
public class LongPressFailedFileTest extends BaseTest{

	public void test_LongPressFailedFile(){
		solo.sleep(Resource.TIME_BIG);
		//进入首页的yahoo页面→长按地址栏→点击save page
		visitUrl("www.yahoo.com");
		solo.sleep(Resource.TIME_SMALL);
		clickLongOnViewAndPress(solo.getView("title_design"), 4);
		solo.sleep(Resource.TIME_SMALL);
		
		//下载弹框中点击OK按钮
		assertTrue("Save as dialog has not showed successfully",
				solo.searchText("Save as"));
		solo.clickOnView("button2");
		solo.sleep(Resource.TIME_SMALL);
		
		//下载列表中点击该文件右侧的播放按钮
		
		//长按下载失败的文件
		
		//点击第一个选项“Retry”
		
		//点击第二个选项“Clear from list”
		
		//点击第三个选项“Clear list”
		
		//点击Cancel按钮
		
		//点击OK
		
        solo.sleep(Resource.TIME_BIG);
	}
}
