package com.dolphin.testcase.backtotop;

import android.util.Log;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

public class NotFullscreenLandscapeInit extends BaseTest{

    public void testNotFullscreenLandscapeInit(){
        solo.sleep(Resource.TIME_BIG);
        //测试环境初始化
        //左划屏幕
        BackToTop.switchOrientation("Landscape", this);
        solo.sleep(Resource.TIME_Middle);
        BackToTop.scrollToLeft(this);
        solo.sleep(Resource.TIME_SMALL);
        //查看当前全屏状态
        boolean isFullScreen = solo.getViewByPath("list_installed_plugin[0][0][0][0]").isSelected();
        Log.i("isSelected", String.valueOf(isFullScreen));
        //是全屏则关闭全屏
        if(isFullScreen){
            solo.clickOnView(solo.getViewByPath("list_installed_plugin[0][0][0][0]"));
            //马上通过判断toast来判断是否成功关闭全屏
            assertTrue("FullScreen is turned on. The testcase will be affected.", solo.waitForText("Fullscreen is turned off"));
        }
        else{
            solo.goBack();
            solo.sleep(Resource.TIME_SMALL);
        }
    }
}
