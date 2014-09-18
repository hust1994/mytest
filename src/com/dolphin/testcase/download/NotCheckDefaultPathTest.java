package com.dolphin.testcase.download;

import android.util.Log;
import android.widget.EditText;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
/**
 * 新建下载任务
 * 设置中更改默认保存路径——不勾选默认下载路径
 * @author chhzhang
 *
 */
public class NotCheckDefaultPathTest extends BaseTest{

	 /**
     * 设置中更改默认保存路径——不勾选默认下载路径
     */
    public void test_NotDefaultpath(){
    	solo.sleep(Resource.TIME_BIG);
    	
    	//新建下载任务→修改新的下载路径→不勾选下载弹框中的设置为默认下载地址左侧的CheckBox→点击OK按钮进行下载
    	visitUrl(Download.downloaded);
    	solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_save_path");
        solo.sleep(Resource.TIME_SMALL);
        //新建文件夹“test”
        solo.clickOnView("btn_new_folder");
        solo.sleep(Resource.TIME_SMALL);        
        solo.enterText((EditText) solo.getView("et_input"), "test");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL); 
        solo.clickOnText("test");
        solo.sleep(Resource.TIME_SMALL); 
        solo.clickOnView("btn_confirm");
        solo.sleep(Resource.TIME_SMALL);        
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL); 
        
        //不退出浏览器直接新建下载任务→查看下载弹框中的保存路径
        visitUrl(Download.downloaded);
        solo.sleep(Resource.TIME_SMALL); 
        Log.i("path",solo.getButton("btn_save_path").getText().toString());
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL); 
        
        //修改新的下载路径→不勾选下载弹框中的设置为默认下载地址左侧的CheckBox→点击OK按钮
        visitUrl(Download.downloaded);
        solo.clickOnView("btn_save_path");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_change_path");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_confirm");
        solo.sleep(Resource.TIME_SMALL);        
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL); 
        
        //退出浏览器3秒钟后再次进入下载文件→查看下载弹框中的路径
//        while (true) {
//            solo.goBack();
//            solo.sleep(500);
//            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
//                solo.clickOnView("button3");
//                break;
//            }
//        }
//        solo.sleep(3000);
//        visitUrl(Download.downloaded);
        
        solo.sleep(Resource.TIME_BIG);        
    }
}
