package com.dolphin.testcase.backtotop;

import android.util.Log;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class FullscreenLandscapeInit extends BaseTest{

    public void testFullscreenLandscapeInit(){
        solo.sleep(Resource.TIME_BIG);
        //测试环境初始化
        BackToTop.switchOrientation("Landscape", this);
        solo.sleep(Resource.TIME_Middle);
        //左划屏幕
        BackToTop.scrollToLeft(this);
        solo.sleep(Resource.TIME_SMALL);
        //查看当前全屏状态
        boolean isFullScreen = solo.getViewByPath("list_installed_plugin[0][0][0][0]").isSelected();
        Log.i("isSelected", String.valueOf(isFullScreen));
        //不是是全屏则开启全屏
        if(!isFullScreen){
            solo.clickOnView(solo.getViewByPath("list_installed_plugin[0][0][0][0]"));
            //马上通过判断toast来判断是否成功开启全屏
            assertTrue("FullScreen is turned off. The testcase will be affected.", solo.waitForText("Fullscreen is turned on"));
        }
        else{
            solo.goBack();
            solo.sleep(Resource.TIME_SMALL);
        }
    }
}
