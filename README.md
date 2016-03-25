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
