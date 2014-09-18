/*******************************************************************************
 *
 *    Copyright (c) Baina Info Tech Co. Ltd
 *
 *    shellEN
 *
 *    MenuTest
 *    TODO File description or class description.
 *
 *    @author: danliu
 *    @since:  Feb 20, 2014
 *    @version: 1.0
 *
 ******************************************************************************/

package com.adolphin.cn.menu;

import com.adolphin.common.BaseTest;
import android.util.Log;
import android.view.KeyEvent;

/**
 * MenuTest of shellEN.
 *
 * @author danliu
 */
public class MenuTest extends BaseTest {

    private static final int TIMES = 40;

    public void testMenu() {

        solo.sleep(10000);

        int time = 0;

        long total = 0;

//        while (time < TIMES) {

            time ++;

            solo.sendKey(KeyEvent.KEYCODE_MENU);

            solo.sleep(2000);

//            String matched = solo.waitForLogMessageReg("\\[TraceLog\\]"+ TestConst.LABEL + " takes (\\d+)ms.", 1);
//
//            if (matched != null) {
//                try {
//                    total += Integer.parseInt(matched);
//                } catch (Exception e) {
//                    time --;
//                }
//            } else {
//                time --;
//            }

            solo.sendKey(KeyEvent.KEYCODE_MENU);

            solo.sleep(2000);
//        }

//        Log.d("TEST", "total time: " + total + " average: " + String.valueOf(total / TIMES));
    }

}
