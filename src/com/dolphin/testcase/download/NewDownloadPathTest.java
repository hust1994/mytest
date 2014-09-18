package com.dolphin.testcase.download;

import java.io.File;

import android.widget.EditText;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 新建下载路径
 * @author chhzhang
 *
 */
public class NewDownloadPathTest extends BaseTest{
	/*
	 * 新建下载路径
	 */
	public void test_Newpath(){
		solo.sleep(Resource.TIME_BIG);
		
    	//新建下载任务→点击save path→点击左侧的新建文件夹图标
    	visitUrl(Download.downloaded);
    	solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_save_path");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("btn_new_folder");
        assertTrue("Cannot show btn_new_folder dialog successfully", solo.searchText("New folder"));
        solo.sleep(Resource.TIME_Middle);
        
        //不输入内容直接点击弹框的OK按钮
        solo.clickOnView("button2");
        assertTrue("Cannot show Alert dialog successfully", 
        		solo.searchText("File name can not be empty"));
        solo.sleep(Resource.TIME_SMALL);
        // OK 按钮（点击后提示框消失）
        solo.clickOnView("button1");
        solo.sleep(Resource.TIME_Middle);
        
        //再次点击新建文件夹的图标→输入文件夹名称后，点击Cancel
        solo.clickOnView("btn_new_folder");
        assertTrue("Cannot show btn_new_folder dialog successfully", solo.searchText("New folder"));
        solo.sleep(Resource.TIME_SMALL);        
        solo.enterText((EditText) solo.getView("et_input"), "123");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("button1");
        solo.sleep(Resource.TIME_Middle);
    	
    	//再次点击新建文件夹的图标→输入文件夹名称“123”后点击OK
        solo.clickOnView("btn_new_folder");
        assertTrue("Cannot show btn_new_folder dialog successfully", solo.searchText("New folder"));
        solo.sleep(Resource.TIME_SMALL);        
        solo.enterText((EditText) solo.getView("et_input"), "123");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL);       
        assertTrue("Filefolder 123 created not successfully",
                solo.searchText("123"));
        solo.sleep(Resource.TIME_Middle);
        
        //点击文件夹123→点击界面顶部的对号按钮
        solo.clickOnText("123");
        solo.sleep(Resource.TIME_SMALL);       
        solo.clickOnView("btn_confirm");
        solo.sleep(Resource.TIME_SMALL);
        assertTrue("Save path is not new", solo.searchText("123"));
        
        //点击下载框中的OK按钮
        solo.clickOnView("button2");
        solo.sleep(Resource.TIME_SMALL);
        
        //文件下载完成后，显示在文件夹123中
        Download.enterDownloadScreen(this);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(solo
                .getViewByPath("mobi.mgeek.TunnyBrowser:id/tabs_bg[0][1]"));
        assertTrue("The file is not in 123", solo.searchText("downapk.apk"));
        solo.sleep(Resource.TIME_SMALL);
        
        
        //还原
        deleteAllFile(new File("/storage/sdcard0/download/123"));
        solo.sleep(Resource.TIME_BIG);
    }
}
