/*
        MIT License

        Copyright (c) [year] [fullname]

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.

*/

package com.reqica.drilon.imagepickerlibrary;

import android.app.Activity;
import android.widget.Button;

public class ButtonColorChanger {

    public static void setButtonColor (String buttonColor , Button b1 , String textColor , Activity activity){

        switch (textColor) {
            case "WHITE":
                b1.setTextColor(activity.getResources().getColor(R.color.white));
                break;
            case "BLACK":
                b1.setTextColor(activity.getResources().getColor(R.color.black));
                break;
            default:
                b1.setTextColor(activity.getResources().getColor(R.color.white));
                break;
        }

        switch (buttonColor) {
            case "BLUE":
                b1.setBackgroundResource(R.drawable.button_background_blue);
                break;
            case "GREEN":
                b1.setBackgroundResource(R.drawable.button_background_green);
                break;
            case "PINK":
                b1.setBackgroundResource(R.drawable.button_background_pink);
                break;
            case "RED":
                b1.setBackgroundResource(R.drawable.button_background_red);
                break;
            case "BLUEGREY":
                b1.setBackgroundResource(R.drawable.button_background_bluegrey);
                break;
            case "ORANGE":
                b1.setBackgroundResource(R.drawable.button_background_orange);
                break;
            case "YELLOW":
                b1.setBackgroundResource(R.drawable.button_background_yellow);
                break;
            case "BROWN":
                b1.setBackgroundResource(R.drawable.button_background_brown);
                break;
            case "PURPLE":
                b1.setBackgroundResource(R.drawable.button_background_purple);
                break;
            case "TEAL":
                b1.setBackgroundResource(R.drawable.button_background_teal);
                break;
            default:
                b1.setBackgroundResource(R.drawable.button_background_blue);
        }

    }

}
