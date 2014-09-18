package com.dolphin.testcase.download;

import java.io.DataOutputStream;
import java.io.IOException;
import android.util.Log;
import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——Time显示方式--有下载完成的文件——“一周内”分组
 * @author chhzhang
 *
 */
public class DownloadedWeekTest extends BaseTest {

	public void test_DownloadedWeek() {
		solo.sleep(Resource.TIME_BIG);
		// 再次将手机的系统时间向后移3天→进入下载界面
		// 系统时间涉及权限，使用手动设置
//		int now[] = TimeHelp.getSystemTime();
//		Log.i("time", now[0] + " " + now[1] + " " + now[2] + " " + now[3] + ":"
//				+ now[4] + ":" + now[5]);
//		setDate();
//		int now1[] = TimeHelp.getSystemTime();
//		Log.i("time1", now[0] + " " + now1[1] + " " + now1[2] + " " + now1[3]
//				+ ":" + now1[4] + ":" + now1[5]);
//
//		Download.enterDownloadScreen(this);
//		solo.sleep(Resource.TIME_SMALL);

		solo.sleep(Resource.TIME_BIG);
	}

	public void setDate() {
		try {
			Log.i("setdate","zzzz");
			Process process = Runtime.getRuntime().exec("su");
			String datetime = "20140724.112800"; // 测试的设置的时间【时间格式
													// yyyyMMdd.HHmmss】
			DataOutputStream os = new DataOutputStream(
					process.getOutputStream());
			os.writeBytes("setprop persist.sys.timezone GMT\n");
			os.writeBytes("/system/bin/date -s " + datetime + "\n");
			os.writeBytes("clock -w\n");
			os.writeBytes("exit\n");
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
