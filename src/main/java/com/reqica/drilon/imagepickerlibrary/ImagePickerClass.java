/*
        MIT License

        Copyright (c) 2016 Drilon Reçica

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
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImagePickerClass  {

    private Dialog dialogImagePicker , dialogScaleImage;
    private Uri imageUri;
    private Context context;
    private CheckBox scaleCheckBox;
    private EditText rawWidth , rawHeight;
    private Bitmap finalBitmap;
    private ImageView imageView;
    private Activity activity;

    public ImagePickerClass (Activity activity){
        this.activity = activity;
    }

    public void callImagePickerDialog (Context receivedContext , String dialogTitleString , ImageView finalImageView , String buttonColor , String textColor){

        this.context = receivedContext;
        this.imageView = finalImageView;

        dialogImagePicker = new Dialog(context);
        dialogImagePicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogImagePicker.setContentView(R.layout.dialog_image_picker);
        dialogImagePicker.show();

        Button openCameraBtn = (Button) dialogImagePicker.findViewById(R.id.openCamera);
        Button openGalleryBtn = (Button) dialogImagePicker.findViewById(R.id.openGallery);
        TextView dialogTitle = (TextView) dialogImagePicker.findViewById(R.id.dialogTitle);
        dialogTitle.setText(dialogTitleString);

        ButtonColorChanger.setButtonColor(buttonColor , openGalleryBtn , textColor , activity);
        ButtonColorChanger.setButtonColor(buttonColor , openCameraBtn , textColor , activity);

        openCameraBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        imageUri = activity.getContentResolver().insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        activity.startActivityForResult(intent, CONSTANTS.RESULT_CAMERA_IMAGE);

                        dialogImagePicker.dismiss();
                    }
                });

        openGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        activity.startActivityForResult(i, CONSTANTS.RESULT_GALLERY_IMAGE);

                        dialogImagePicker.dismiss();
                    }
                });
    }

    public void setImageInImageView (Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    // scales the received bitmap or not and then calls the method to set it to the imageview
    public void scaleImageDialog (final Bitmap bitmap , String buttonColor , String textColor) {
        dialogScaleImage = new Dialog(context);
        dialogScaleImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogScaleImage.setContentView(R.layout.dialog_scale_image);
        dialogScaleImage.setCancelable(false);
        dialogScaleImage.show();

        scaleCheckBox = (CheckBox) dialogScaleImage.findViewById(R.id.scaleCheckBox);
        rawHeight = (EditText) dialogScaleImage.findViewById(R.id.height);
        rawWidth = (EditText) dialogScaleImage.findViewById(R.id.width);
        Button scaleBtn = (Button) dialogScaleImage.findViewById(R.id.scaleBtn);

        ButtonColorChanger.setButtonColor(buttonColor , scaleBtn , textColor , activity);

        scaleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (scaleCheckBox.isChecked()) {
                    rawWidth.setEnabled(true);
                    rawHeight.setEnabled(true);
                }
                else {
                    rawWidth.setEnabled(false);
                    rawHeight.setEnabled(false);
                }
            }
        });

        scaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (scaleCheckBox.isChecked()){

                    if (!rawHeight.getText().toString().equals("") || !rawWidth.getText().toString().equals("")){

                        int width = Integer.parseInt(rawWidth.getText().toString());
                        int height = Integer.parseInt(rawHeight.getText().toString());

                        finalBitmap = BitmapReScaler.reScale(bitmap , width , height);
                        setImageInImageView(finalBitmap);
                        dialogScaleImage.dismiss();

                    }else {

                        Toast.makeText(context, "You need to give measures in order to scale the image!", Toast.LENGTH_SHORT).show();

                    }

                }else {

                    finalBitmap = bitmap;
                    setImageInImageView(finalBitmap);
                    dialogScaleImage.dismiss();

                }

            }
        });

    }

    //calls the methods that automatically set the received image bitmap to the needed ImageView
    public void onActivityResultLogic (int requestCode, int resultCode, Intent data , String buttonColor , String textColor){

        if (requestCode == CONSTANTS.RESULT_CAMERA_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            activity.getContentResolver(), imageUri);

                    scaleImageDialog(thumbnail , buttonColor , textColor);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Log.d("CANCELED", "CAMERA");
            }
        }else if (requestCode == CONSTANTS.RESULT_GALLERY_IMAGE){
            if (resultCode == Activity.RESULT_OK){

                try {
                    imageUri = data.getData();
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            activity.getContentResolver(), imageUri);

                    scaleImageDialog(thumbnail , buttonColor , textColor);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                Log.d("CANCELED", "GALLERY");
            }
        }else {
            Log.d("ERROR" , "SOMETHING WENT WRONG");
        }

    }

}
