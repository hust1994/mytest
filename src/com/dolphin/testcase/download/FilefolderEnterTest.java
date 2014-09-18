package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--各个分类文件夹点击进入的性能
 * @author chhzhang
 *
 */
public class FilefolderEnterTest extends BaseTest {

	//各个分类文件夹点击进入的性能(APK要注意，可能会慢，Apk图片失真的情况，OOM等，ANR等)???
	public void test_EnterDownloadFolder(){
		solo.sleep(Resource.TIME_BIG);
		for(int i=0;i<101;i++){
			Download.setUpDownload(Download.downloaded, this);
			solo.sleep(Resource.TIME_SMALL);
		}
		//D_FilefolderTestUtil.enterDownloadScreenType(this);
		Download.enterDownloadScreen(this);
		//等待下载完成
		while(solo.searchText("Downloading")){
			solo.sleep(Resource.TIME_SMALL);
		}
		solo.clickOnView("btn_menu");
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("Type");
		solo.sleep(Resource.TIME_SMALL);
		//点击任意的分类文件夹
		solo.clickOnView(solo.getView("category_title",1));
		solo.sleep(Resource.TIME_SMALL);
		
		//二级目录中点击左上角的返回按钮
		solo.clickOnView("back");
		solo.sleep(Resource.TIME_SMALL);
		
		//文件夹中的文件较多时（100+），点击文件夹（注重测试APK的文件夹）
		solo.clickOnView(solo.getView("category_title",7));
		solo.sleep(Resource.TIME_SMALL);
		
		solo.sleep(Resource.TIME_BIG);
	}

}
