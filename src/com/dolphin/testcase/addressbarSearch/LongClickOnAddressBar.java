package com.dolphin.testcase.addressbarSearch;

import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongClickOnAddressBar extends Utils_AddressBarSearch{
    @SuppressLint("NewApi")
    public void testLongClickOnAddressBar() throws IOException{
        Boolean toast;

        //get version
        String version = null;
        Process p = Runtime.getRuntime().exec("getprop ro.build.version.release");
        solo.sleep(Resource.TIME_SMALL);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            version = line;
        }
        boolean versionFlag = false;
        String [] versions = version.split("\\.");
        if(versions[0].equals("2") && versions[1].equals("3"))
            versionFlag = true;

        solo.sleep(Resource.TIME_BIG);

        //get addressBar
        LinearLayout ll = (LinearLayout) solo.getView("title_bg");
        RelativeLayout rl = (RelativeLayout) ll.getChildAt(3);
        TextView addressBar = (TextView) rl.getChildAt(1);

        //长按显示的内容
        typeUrlAndVisit("baidu.com");
        solo.sleep(Resource.TIME_BIG);
        clickThePopUpBox();
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        solo.clickLongOnView(addressBar);
        solo.sleep(Resource.TIME_SMALL);
        View v = solo.getView("select_dialog_listview");
        toast = solo.waitForView(v);
        assertTrue("long click not found view", toast);
        String s = ((TextView) addressBar).getText().toString();
        boolean flag = true;
        int child = ((ListView)v).getChildCount();
        if(versionFlag)
            flag = (child == 5)?true:false;
        else{
            if(child == 5)
                assertTrue("the paste is not null", false);
            flag = (child == 4)?true:false;
        }
        assertTrue("the open list count is not right", flag);
        for(int i = 0; i < child; i++){
            ViewGroup viewGroup = (ViewGroup) ((ListView)v).getChildAt(i);
            RelativeLayout rl_1 = (RelativeLayout) viewGroup.getChildAt(0);
            TextView tv = (TextView) rl_1.getChildAt(0);
            String message = tv.getText().toString();
            switch (i) {
                case 0:
                    flag = message.equals("Add bookmark");
                    assertTrue("not found text:Add bookmark", flag);
                    break;
                case 1:
                    flag = message.equals("Copy page URL");
                    assertTrue("not found text:Copy page URL", flag);
                    break;
                case 2:
                    flag = message.equals("Create gesture for this page");
                    assertTrue("not found text:Create gesture for this page", flag);
                    break;
                case 3:
                    if(versionFlag){
                        flag = message.equals("Select text");
                        assertTrue("not found text:Select text", flag);
                        break;
                    }
                    flag = message.equals("Save page");
                    assertTrue("not found text:Save page", flag);
                    break;
                case 4:
                    flag = message.equals("Select text");
                    assertTrue("not found text:Select text", flag);
                    break;

                default:
                    break;
            }

        }

        //add bookmarks
        solo.goBack();
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        clickLongOnViewAndPress(addressBar,0);
        solo.sleep(Resource.TIME_SMALL);
        v = solo.getView("add_bookmark_content");
        toast = solo.waitForView(v);
        assertTrue("not found view:Add Bookmark", toast);
        EditText titleView = (EditText) solo.getView("title");
        EditText addressView = (EditText) solo.getView("address");
        if(titleView.getText() == null){
            assertTrue("not auto fill in title", false);
        }
        String url = addressView.getText().toString();
        if(!url.toLowerCase().contains(s.toLowerCase())){
            assertTrue("not auto fill in url address", false);
        }
        Utils.addbookmarkoption(6, this);
        solo.sleep(Resource.TIME_SMALL);

        //create gesture for the page
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        clickLongOnViewAndPress(addressBar,2);
        solo.sleep(Resource.TIME_SMALL);
        v = solo.getView("gestures_overlay");
        toast = solo.waitForView(v);
        assertTrue("can't open gestures page", toast);

        //save page
        solo.goBack();
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        clickLongOnViewAndPress(addressBar,3);
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.waitForText("Save as");
        assertTrue("can't open save page", toast);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();

        //copy url
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        clickLongOnViewAndPress(addressBar,1);
        toast = solo.waitForText("Copied successfully");
        assertTrue("Copied url failed", toast);
        solo.sleep(Resource.TIME_SMALL);
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        solo.clickLongOnView(addressBar);
        solo.sleep(Resource.TIME_SMALL);
        flag = true;
        v = solo.getView("select_dialog_listview");
        child = ((ListView)v).getChildCount();
        if(versionFlag)
            flag = (child == 6)?true:false;
        else
            flag = (child == 5)?true:false;
        assertTrue("the open list count is not right", flag);
        for(int i = 0; i < child; i++){
            ViewGroup viewGroup = (ViewGroup) ((ListView)v).getChildAt(i);
            RelativeLayout rl_1 = (RelativeLayout) viewGroup.getChildAt(0);
            TextView tv = (TextView) rl_1.getChildAt(0);
            String message = tv.getText().toString();
            switch (i) {
                case 0:
                    flag = message.equals("Add bookmark");
                    assertTrue("not found text:Add bookmark", flag);
                    break;
                case 1:
                    flag = message.equals("Copy page URL");
                    assertTrue("not found text:Copy page URL", flag);
                    break;
                case 2:
                    flag = message.equals("Paste");
                    assertTrue("not found text:Paste", flag);
                    break;
                case 3:
                    flag = message.equals("Create gesture for this page");
                    assertTrue("not found text:Create gesture for this page", flag);
                    break;
                case 4:
                    if(versionFlag){
                        flag = message.equals("Select text");
                        assertTrue("not found text:Select text", flag);
                        break;
                    }
                    flag = message.equals("Save page");
                    assertTrue("not found text:Save page", flag);
                    break;
                case 5:
                    flag = message.equals("Save page");
                    assertTrue("not found text:Save page", flag);
                    break;

                default:
                    break;
            }

        }

        //select Text ---for 2.3---pass

        //paste
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        clickLongOnViewAndPress(addressBar,2);
        solo.sleep(Resource.TIME_SMALL);
        toast = solo.searchText("Paste");
        assertFalse("view is not disappeared", toast);
        toast = s.equals(((TextView) addressBar).getText().toString());
        assertTrue("addressbar url has changed", toast);
        View v1 = solo.getImage(2);
        solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, 3);
        solo.clickOnView(v1);
        solo.sleep(Resource.TIME_SMALL);
        ll = (LinearLayout) solo.getView("title_bg");
        rl = (RelativeLayout) ll.getChildAt(3);
        addressBar = (TextView) rl.getChildAt(1);
        solo.clickLongOnView(addressBar);
        solo.sleep(Resource.TIME_SMALL);
        v = solo.getView("select_dialog_listview");
        child = ((ListView) v).getChildCount();
        solo.sleep(Resource.TIME_SMALL);
        if(child != 1 || !solo.waitForText("Paste"))
            assertTrue("view is not only paste view", false);
        solo.goBack();
        clickLongOnViewAndPress(addressBar,0);
        solo.sleep(Resource.TIME_SMALL);
        toast = s.toLowerCase().equals(((TextView) addressBar).getText().toString().toLowerCase());
        assertTrue("after open new tab and paste, addressbar url has changed", toast);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
    }

}
