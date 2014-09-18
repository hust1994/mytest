package com.dolphin.testcase.addressbarSearch;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;
import com.jayway.android.robotium.solo.Solo;

public class Utils_AddressBarSearch extends BaseTest{
    @Override
    public void tearDown() throws Exception {
        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.clickOnView("button3");
                break;
            }
        }
    }

    public void visitUrl(String url){
        Context context=getInstrumentation().getTargetContext();
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName("mobi.mgeek.TunnyBrowser",
                "mobi.mgeek.TunnyBrowser.BrowserActivity");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void changeNoTabBarMode(){
        Display defaultDisplay = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        float startY = defaultDisplay.getHeight() / 2;
        float startX = defaultDisplay.getWidth() - 1;
        float endX = 1;
        float endY = startY;

        solo.drag(startX, endX, startY, endY, 100);
        solo.sleep(Resource.TIME_BIG);

        final ListView rightView = (ListView) solo.getView("list_installed_plugin");
        final ViewGroup iconContainer = (ViewGroup) ((ViewGroup) rightView.getChildAt(0)).getChildAt(0);
        final View NoTabBar = ((ViewGroup) iconContainer.getChildAt(1)).getChildAt(0);
        solo.clickOnView(NoTabBar);
        solo.waitForText("Classic Tabs is truned");
        solo.sleep(Resource.TIME_SMALL);
    }

    public String getAddressBarContext(){
        LinearLayout ll = (LinearLayout) solo.getView("title_bg");
        RelativeLayout rl = (RelativeLayout) ll.getChildAt(3);
        TextView urladdress1 = (TextView) rl.getChildAt(1);
        return urladdress1.getText().toString();
    }

    public String getSearchBarContext(){
        RelativeLayout r2 = (RelativeLayout) solo.getView("search_bar");
        TextView urladdress2 = (TextView) r2.getChildAt(1);
        return urladdress2.getText().toString();
    }

    public void AutoFill(int[] fill){
        for(int i=0;i<fill.length;i++){
            solo.sendKey(fill[i]);
            solo.sleep(1000);
        }
    }

    //0:向左，1：向右，2：向上，3：向下
    public void scroll(int i, int stepCount){
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay()
                .getHeight();
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay()
                .getWidth();
        switch(i){
        case 0:
            solo.drag(1, screenWidth - 1, screenHeight / 2, screenHeight /2, stepCount);
            break;
        case 1:
            solo.drag(screenWidth - 1, 1, screenHeight / 2, screenHeight /2, stepCount);
            break;
        case 2:
            solo.drag(10, 10, screenHeight * 3 / 4, screenHeight / 4, stepCount);
            break;
        case 3:
            solo.drag(10, 10, screenHeight / 4, screenHeight * 3 / 4, stepCount);
            break;
        case 4:
            solo.drag(10, 10, screenHeight / 2, screenHeight - 1, stepCount);
            break;
        }
        solo.sleep(Resource.TIME_SMALL);
    }

    public boolean typeButNotVisit(String url){
        View urladdress = solo.getView("title_design");
        solo.clickOnView(urladdress);
        solo.sleep(2000);
        solo.clearEditText(0);
        solo.sleep(1000);
        solo.enterText(0, url);
        solo.sleep(2000);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALLEST);

        //判断history
        solo.clickOnView(urladdress);
        solo.sleep(Resource.TIME_SMALL);
        return solo.searchText(url);
    }

    public View getPrivateModeView(){
        scroll(1, 50);
        final ListView rightView = (ListView) solo.getView("list_installed_plugin");
        final ViewGroup iconContainer = (ViewGroup) ((ViewGroup) rightView.getChildAt(0)).getChildAt(0);
        return ((ViewGroup) iconContainer.getChildAt(4)).getChildAt(0);
    }

    public void clearHistory(){
        solo.clickOnText("Clear browsing data");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(!isChecked[0]) solo.clickOnText("Clear browsing history");
            solo.sleep(Resource.TIME_SMALL);
            if(isChecked[1]) solo.clickOnText("Clear the cache");
            solo.sleep(Resource.TIME_SMALL);
            if(isChecked[2]) solo.clickOnText("Clear cookies");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }
    }

    public void clearCache(){
        solo.clickOnText("Clear browsing data");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(isChecked[0]) solo.clickOnText("Clear browsing history");
            solo.sleep(Resource.TIME_SMALL);
            if(!isChecked[1]) solo.clickOnText("Clear the cache");
            solo.sleep(Resource.TIME_SMALL);
            if(isChecked[2]) solo.clickOnText("Clear cookies");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }
    }


    public void clearCookies(){
        solo.clickOnText("Clear browsing data");
        solo.sleep(Resource.TIME_SMALL);
        if(solo.waitForDialogToOpen()){
            boolean[] isChecked = validateInitClearHistoryState();
            if(isChecked[0]) solo.clickOnText("Clear browsing history");
            solo.sleep(Resource.TIME_SMALL);
            if(isChecked[1]) solo.clickOnText("Clear the cache");
            solo.sleep(Resource.TIME_SMALL);
            if(!isChecked[2]) solo.clickOnText("Clear cookies");
            solo.sleep(Resource.TIME_SMALL);
            solo.clickOnButton(4);
            solo.waitForText("Cleared");
        }
    }

    public boolean[] validateInitClearHistoryState(){
        boolean[] isChecked = {false, false, false};
        solo.clickOnText("Clear browsing data");
        solo.sleep(Resource.TIME_SMALL);
        CheckBox cb1 = (CheckBox) solo.getView("chkClearBrowsering");
        CheckBox cb2 = (CheckBox) solo.getView("chkClearCache");
        CheckBox cb3 = (CheckBox) solo.getView("chkClearCookies");
        isChecked[0] = cb1.isChecked();
        isChecked[1] = cb2.isChecked();
        isChecked[2] = cb3.isChecked();
        return isChecked;
    }

    public void visitUrls(String[] urls){
        for(int i = 0; i < urls.length; i++){
            typeUrlAndVisit(urls[i]);
            solo.sleep(Resource.TIME_BIG);
            clickThePopUpBox();
        }
    }

    public void clickSetting(){
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.sleep(Resource.TIME_SMALL);
        ArrayList<View> al = getAllViewByClassName("PanelMenuTabBar", solo);
        solo.sleep(Resource.TIME_SMALL);

        final ViewGroup tabView = (ViewGroup) al.get(0);
        solo.clickOnView(tabView.getChildAt(0));
        solo.sleep(Resource.TIME_SMALL);
    }
    public static ArrayList<View> getAllViewByClassName(final String className, final Solo solo) {
        final ArrayList<View> currentViews = solo.getCurrentViews();
        ArrayList<View> results = new ArrayList<View>();
        for (View view : currentViews) {
            if (view.getClass().getName().endsWith(className)) {
                results.add(view);
            }
        }
        return results;
    }

    //0:history 1:bookmarks 2:most visited
    public int getCurrentTab(){
        ViewGroup vg = (ViewGroup) solo.getView("pager");
        if(vg.getChildCount() == 2){
            int num1 = vg.getChildAt(0).getId();
            int num2 = vg.getChildAt(1).getId();
            if((num1 == 0 | num1 == 1) && (num2 == 0 | num2 == 1)){
                return 0;
            }
            if((num1 == 1 | num1 == 2) && (num2 == 1 | num2 == 2)){
                return 2;
            }
        }
        return 1;
    }

    public void clickOnAddressBar(int i){
        scroll(3, 20);
        solo.clickOnView("title_bg");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);
        solo.clearEditText(0);
        solo.sleep(Resource.TIME_SMALL);
        switch(i){
            case 0:
                if(getCurrentTab() != 0){
                    scroll(0, 5);
                    scroll(0, 5);
                }
                break;
            case 1:
                if(getCurrentTab() == 0)
                    scroll(1, 5);
                if(getCurrentTab() == 2)
                    scroll(0, 5);
                break;
            case 2:
                if(getCurrentTab() != 2){
                    scroll(1, 5);
                    scroll(1, 5);
                }
                break;
            default:
                break;

        }
        solo.sleep(Resource.TIME_SMALL);
    }

    public void clickOnBookmarksInTab(){
        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int[] xy1 = new int[2];
        View urladdress = solo.getView("search_bar");
        urladdress.getLocationOnScreen(xy1);
        int urladdress_height = urladdress.getHeight();
        float y0 = xy1[1] + urladdress_height;

        int[] xy2 = new int[2];
        View searchtab_main_container = solo.getView("searchtab_main_container");
        searchtab_main_container.getLocationOnScreen(xy2);
        float y1 = xy2[1];

        float x = screenWidth / 2;
        float y = (y0 + y1) / 2;
        solo.clickLongOnScreen(x, y);
        solo.sleep(Resource.TIME_SMALL);
    }

    public void judgeTheOrder(){
        boolean flag = true;
        LinearLayout options_container = (LinearLayout)solo.getView("options_container");
        flag &= solo.waitForView(options_container);
        assertTrue("no Pop Up Box Shows", flag);
        for (int i = 0; i < 3; i++) {
            TextView tv = solo.getCurrentViews(TextView.class, options_container).get(i);
            String text = tv.getText().toString();
            switch (i) {
                case 0:
                    if(!text.equals("Open in new tab"))
                        assertTrue("the first is not match", false);
                    break;
                case 1:
                    if(!text.equals("Open in background"))
                        assertTrue("the second is not match", false);
                    break;
                case 2:
                    if(!text.equals("Delete"))
                        assertTrue("the third is not match", false);
                    break;

                default:
                    break;
            }
        }
    }

    public void goBackToHomepage(){
        while (true) {
            solo.goBack();
            solo.sleep(1000);
            if (solo.waitForText("Exit Dolphin Browser", 1, 500)) {
                solo.goBack();
                break;
            }
        }
    }

    public void clickThePopUpBox(){
        while(solo.waitForText("Security warning"))
            solo.clickOnText("Continue");
        while(solo.waitForText("wants to know your location"))
            solo.clickOnText("Decline");
    }

    public boolean findRecordAtPosition(String record, int i, int j){
        clickOnAddressBar(j);
        solo.sleep(3000);
        ListView ll = (ListView) solo.getView("searchtab_main_container", 0);
        String firStr = (solo.getCurrentViews(TextView.class, ll).get(0)).getText()
                .toString();
        if(firStr.equals("Dolphin")){
            ll = (ListView) solo.getView("searchtab_main_container", 1);
        }
        String judgeStr = (solo.getCurrentViews(TextView.class, ll).get(i*2)).getText()
                .toString();
        Log.d("TEST", judgeStr);
        solo.goBack();
        solo.sleep(1000);
        if (judgeStr.contains(record))
            return true;
        else
            return false;
    }
}