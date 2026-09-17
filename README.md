# ImagePickerLibrary

> **Archived / discontinued**
>
> A lightweight Android library originally created to simplify selecting an image from the gallery or capturing one with the camera and displaying the result in an `ImageView`.
>
> **Do not use this library in new Android applications.**

This repository is preserved for historical and reference purposes and is no longer maintained.

## Background

ImagePickerLibrary was created in 2016 to reduce the amount of boilerplate required for a common Android workflow:

1. Ask the user whether they want to choose an existing image or take a new photo.
2. Handle the required storage permissions.
3. Launch the gallery or camera.
4. Receive the result in `onActivityResult()`.
5. Optionally resize the selected image.
6. Display the final image in an `ImageView`.

At the time, Android did not provide the modern Photo Picker and Activity Result APIs available today, so applications commonly implemented this functionality themselves.

The library wrapped that workflow behind a small API and configurable dialogs.

## Why is this project archived?

Android's media access, storage, permission, and activity-result APIs have changed substantially since this library was created.

Modern Android applications should generally use platform and AndroidX APIs directly instead of this library.

For selecting existing media, Android now provides the system Photo Picker through:

* `ActivityResultContracts.PickVisualMedia`
* `ActivityResultContracts.PickMultipleVisualMedia`

For taking a photo, AndroidX provides:

* `ActivityResultContracts.TakePicture`
* `ActivityResultContracts.TakePicturePreview`

The Android Photo Picker allows users to grant access only to the images or videos they select rather than giving an application broad access to the device's media library.

The original implementation also relies on APIs and practices that are now obsolete, including:

* `READ_EXTERNAL_STORAGE` / `WRITE_EXTERNAL_STORAGE` for image selection;
* `startActivityForResult()` / `onActivityResult()`;
* the legacy Android Support Library;
* pre-scoped-storage filesystem assumptions;
* an Android 6.0-era permission model.

For new applications, use current Android platform guidance instead.

Relevant documentation:

* [Android Photo Picker](https://developer.android.com/training/data-storage/shared/photo-picker)
* [Activity Result APIs](https://developer.android.com/training/basics/intents/result)
* [Android storage guidance](https://developer.android.com/training/data-storage)
* [Minimize permission requests](https://developer.android.com/privacy-and-security/minimize-permission-requests)

## Historical usage

The following examples document how ImagePickerLibrary was originally used.

### Dependency

The final published version was:

```gradle
compile 'com.reqica.drilon:iplibrary:1.1.1'
```

This dependency declaration is preserved for historical reference only and should not be used in new projects.

### Create the image picker

```java
private ImagePickerClass imagePickerClass;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    imagePickerClass = new ImagePickerClass(this);
}
```

### Open the image source dialog

```java
imagePickerClass.callImagePickerDialog(
    this,
    "Choose Image Source!",
    imageView,
    CONSTANTS.BLUE,
    CONSTANTS.TEXT_BLACK
);
```

The dialog allowed the user to choose between an existing image and capturing a new one.

The selected image was then processed and displayed in the provided `ImageView`.

### Forward the activity result

The original API required the application's activity result to be forwarded to the library:

```java
@Override
protected void onActivityResult(
        int requestCode,
        int resultCode,
        Intent data
) {
    super.onActivityResult(requestCode, resultCode, data);

    imagePickerClass.onActivityResultLogic(
        requestCode,
        resultCode,
        data,
        CONSTANTS.BLUE,
        CONSTANTS.TEXT_BLACK
    );
}
```

Modern Android applications should use Activity Result contracts instead.

## Modern Android equivalent

Selecting an image today can be implemented directly using AndroidX:

```kotlin
private val pickImage =
    registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageView.setImageURI(uri)
        }
    }
```

Launch the picker with:

```kotlin
pickImage.launch(
    PickVisualMediaRequest(
        ActivityResultContracts.PickVisualMedia.ImageOnly
    )
)
```

AndroidX automatically uses the system Photo Picker where available and provides compatible behavior on older supported Android versions.

A photo captured by the system camera can similarly be handled using:

```kotlin
ActivityResultContracts.TakePicture()
```

For applications that need their own integrated camera interface rather than launching the system camera, CameraX is the modern Android camera API.

## Original features

ImagePickerLibrary provided:

* gallery image selection;
* camera image capture;
* automatic display in an `ImageView`;
* image resizing;
* Android runtime permission handling;
* configurable dialog colors;
* a simple API intended to hide the underlying activity-result boilerplate.

## Historical implementation

The final version of the project was `1.1.1`.

The codebase targets the Android development environment of its time, including:

* `compileSdkVersion 23`;
* `targetSdkVersion 23`;
* Android Support Library `23.2.1`;
* the legacy Gradle `compile` dependency configuration.

The repository should therefore be treated as historical source code rather than a usable modern Android dependency.

## Project status

**Status:** Archived
**Maintenance:** Discontinued
**Latest historical version:** `1.1.1`
**Recommended for new projects:** No
**Purpose of repository:** Historical reference

## License

ImagePicker
