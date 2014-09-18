package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--有下载完成的文件——“一个月内”分组
 * @author chhzhang
 *
 */
public class DownloadedMonthTest extends BaseTest{

	public void test_DownloadedMonth(){
		solo.sleep(Resource.TIME_BIG);
		//再次将手机的系统时间向后移8天→进入下载界面
		//系统时间涉及权限，使用手动设置
//		Download.enterDownloadScreen(this);
//		solo.sleep(Resource.TIME_SMALL);
		
        solo.sleep(Resource.TIME_BIG);
	}
}
