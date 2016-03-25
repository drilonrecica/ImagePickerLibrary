package com.reqica.drilon.imagepickerlibrary;

import android.widget.Button;

public class ButtonColorChanger {

    public static void setButtonColor (String buttonColor , Button b1){

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
