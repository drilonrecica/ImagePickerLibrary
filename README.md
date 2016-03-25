# Image Picker Library

The Image Picker Library was a tool create by me for the sole purpose of over-simplifying the process of picking an Image from the gallery or shooting one with the camera and then immediately setting it to an ImageView (also with the possibility of scaling the image based on the users need and desires).

## Project set-up

#### Image Picker Library currently supports API LEVEL 14+

If you're using a Gradle-based project, then you can add ImagePickerLibrary as a dependency directly:

```
compile 'com.reqica.drilon:iplibrary:1.1.0'
```
To be able to use it you also have to add this snippet to your app module's build.gradle:
```
repositories {
    maven {
        url 'https://dl.bintray.com/drilonreqica/maven/'
    }
}
```
If you're using Maven (but not Gradle), you can add the APKlib as a dependency:
```
<dependency>
  <groupId>com.reqica.drilon</groupId>
  <artifactId>iplibrary</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

If you're using a standard project without either Maven or Gradle, you'll have to download the project, and the add the library manually to your project.

## Usage

To use the Image Picker Library you have to follow a few simple steps, so bear with me.

First you need to create an Instance of the CheckMPermission class which automatically adds the needed WRITE_EXTERNAL_STORAGE & READ_EXTERNAL_STORAGE permissions and if the user is using ANDROID M or higher shows a popup to alert the user about the permissions and let him enable them.
```
CheckMPermissions checkMPermissions = new CheckMPermissions(currentActivity.this, currentActivity.this);
```
Replace currentActivity with the name of the main activity of your project.
Then you need to call the method which shows the dialog for the permissions:
```
checkMPermissions.checkMPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE , "You need to give access to Image Picker Permission!");
```
So the first parameter used is the actual permission and the second is the Title of the Dialog which will be shown to the user, see Pictures at bottom of page.
###### Note "You can use this method for any permission you want, just change this part Manifest.permission.NAME_OF_PERMISSION , and also type the title you want the user to see for that specific Permission."

###### I'm also creating a new library that will deal only with Android M Permissions and alerts for them, so I'll keep you up-to-date about that.

So now let's start with the main function of the library.

Create an empty Instance ov ImagePickerClass in the root of you class so that it will be accessible from all you methods in that class:
```
private ImagePickerClass imagePickerClass;
```
And then initialize that Instance in the onCreate of your activity, like this:
```
imagePickerClass = new ImagePickerClass(currentActivity.this);
```
Keep in mind, wherever you see currentActivity, you have to replace that with the name of the Activity you are using the library in.

So let's continue, now you can call the main method of the library, that is usually done inside an onClickListener of a button, but feel free to try other things out, depending on your needs.
```
imagePickerClass.callImagePickerDialog(currentActivity.this , "Choose Image Source!" , imageView , CONSTANTS.BLUE , CONSTANTS.TEXT_BLACK);
```     
The important parts here are currentActivity (DUHH), a title for the dialog, the reference variable of the ImageView where the image will be shown at the end, the background color of the dialog button, and the text color of the dialog button.
The colors must be called from CONSTANTS.NameOfColor, where you already have a variety of colors possible, all taken from the Google Material Design Palette.

And in the end you have to add this piece of code to the root of your class, obviously as it is a method,:
```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        imagePickerClass.onActivityResultLogic(requestCode , resultCode , data , CONSTANTS.BLUE , CONSTANTS.TEXT_BLACK);

    }
```
In this part , you don't need to change the first three parameters, only the ones for the colors (these will be used to color the dialog for the scaling of the image, see Pictures at bottom of page).

If you have any issues, question or simply want to add better features, contact me or even better contribute to the project.

## What's new in Version 1.1.0

* Added Android M Permission checking
* Color of dialog buttons can be changed
* Text color of the dialog button can also be changed

License
----

**Free Software, Hell Yeah!**

```
        MIT License
        
        Copyright (c) [2016] [Drilon Reçica]

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
```
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_source_dialog1.png "Image Source Dialog")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_source_camera.png "Camera Source Image")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_scaling.png "Scaling Image")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_camera_showing.png "Showing Scaled Camera Image")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_source_gallery.png "Gallery Source Image")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_no_scaling.png "Not Scaling Image")
![Image](http://drilon.reqica.com/imagepickerlibrary/screenshots/image_gallery_showing.png "Showing not Scaled Gallery Image")
