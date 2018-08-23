# DiceLoadingView

A dice 3D rotation loading view.

You can specify the number, color and background of the dice on each side of the cube, and you can also specify the duration and interpolator of the animation.

### [中文](https://github.com/samlss/DiceLoadingView/blob/master/README-ZH.md)

### [More](https://github.com/samlss/FunnyViews)

 <br/>

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/DiceLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/DiceLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)


![gif1](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot1.gif)

![gif2](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot2.gif)

![gif3](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot3.gif)

------

## Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:DiceLoadingView:1.0'
}
```
------

Before we introduce DiceLoadingView, let's take a look at the composition of each dice in the cube.

## DiceView
This is a **DiceView**  that use in the DiceLoadingView.

The following is the effect chart:<br>
![DiceView](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot4.png)

### Use<br>
in layout.xml：

```
<com.iigo.library.DiceView
            android:layout_marginTop="10dp"
            app:number="one"
            app:bgColor="@android:color/white"
            app:borderColor="@color/colorPrimary"
            app:pointColor="@color/colorPrimary"
            android:layout_width="50dp"
            android:layout_height="50dp" />
```

in java code：

```
diceView.setNumber(2); //set the dice number, must be 1-6
diceView.setPointColor(Color.RED); //set the point color
diceView.setBgColor(Color.RED); //set the bg color
diceView.setBorderColor(Color.RED); //set the border color
```

Attributes description：

Before describe the attributes, let's look at a attributes pitures firstly.

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description2.png)

| attr            |         description         |
| --------------- | :-------------------------: |
| number | the dice number, one(1), two(2), three(3), four(4), five(5), six(6) |
| pointColor  | the point color |
| bgColor  | set the bg color |
| borderColor | set the border color |

## DiceLoadingView
in layout.xml：
```
 <com.iigo.library.DiceLoadingView
                    android:id="@+id/dlv_loading1"
                    app:animDuration="2000"
                    app:animInterpolator="AccelerateDecelerateInterpolator"
                    app:firstSideDiceNumber="1"
                    app:firstSideDicePointColor="@color/colorPrimary"
                    app:firstSideDiceBgColor="@android:color/white"
                    app:firstSideDiceBorderColor="@color/colorPrimary"
                    app:secondSideDiceNumber="2"
                    app:secondSideDicePointColor="@android:color/holo_orange_dark"
                    app:secondSideDiceBgColor="@android:color/white"
                    app:secondSideDiceBorderColor="@android:color/holo_orange_dark"
                    app:thirdSideDiceNumber="3"
                    app:thirdSideDicePointColor="@android:color/holo_red_dark"
                    app:thirdSideDiceBgColor="@android:color/white"
                    app:thirdSideDiceBorderColor="@android:color/holo_red_dark"
                    app:fourthSideDiceNumber="4"
                    app:fourthSideDiceBgColor="@android:color/white"
                    app:fourthSideDiceBorderColor="@android:color/holo_green_dark"
                    app:fourthSideDicePointColor="@android:color/holo_green_dark"
                    android:layout_width="50dp"
                    android:layout_height="50dp" />

```

<br>

in java code：
```java
diceLoadingView.start(); //start animation
diceLoadingView.stop(); //stop animation
diceLoadingView.pause(); //pause animation
diceLoadingView.resume(); //resume animation
diceLoadingView.release(); //Can 'released' when you don't need to use the loading view, for example in the activity's onDestroy()

diceLoadingView.setDuration(3000); //set the animation 's duration
diceLoadingView.setInterpolator(new AnticipateOvershootInterpolator()); //set the animation's interpolator
diceLoadingView.setFirstSideDiceNumber(2); //set the dice number{1-6} of the first dice side
diceLoadingView.setFirstSidePointColor(Color.parseColor("#FF7D81")); //set the point color of the first dice side
diceLoadingView.setFirstSideDiceBgColor(Color.WHITE); // set the bg color of the first dice side
diceLoadingView.setFirstSideDiceBorderColor(Color.GRAY); //set the border color of the first dice side

diceLoadingView.setSecondSideDiceNumber(3); //set the dice number{1-6} of the second dice side
diceLoadingView.setSecondSidePointColor(Color.BLUE);//set the point color of the second dice side
diceLoadingView.setSecondSideDiceBgColor(Color.WHITE); //set the bg color of the second dice side
diceLoadingView.setSecondSideDiceBorderColor(Color.BLUE); //set the border color of the second dice side

diceLoadingView.setThirdSideDiceNumber(4); //set the dice number{1-6} of the third dice side
diceLoadingView.setThirdSidePointColor(Color.GREEN);//set the point color of the third dice side
diceLoadingView.setThirdSideDiceBgColor(Color.WHITE); //set the bg color of the third dice side
diceLoadingView.setThirdSideDiceBorderColor(Color.GREEN); //set the border color of the third dice side

diceLoadingView.setFourthSideDiceNumber(5); //set the dice number{1-6} of the fourth dice side
diceLoadingView.setFourthSidePointColor(Color.RED);//set the point color of the fourth dice side
diceLoadingView.setFourthSideDiceBgColor(Color.WHITE);//set the bg color of the fourth dice side
diceLoadingView.setFourthSideDiceBorderColor(Color.RED);//set the border color of the fourth dice side
```
<br>


Attributes description：

Before describe the attributes, let's look at a attributes pitures firstly.

For the cube:

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description1.png)

For the each side:

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description2.png)


| attr            |         description         |
| --------------- | :-------------------------: |
| animDuration  | the animation 's duration |
| animInterpolator | the animation's interpolator |
| firstSideDiceNumber  | the dice number{1-6} of the first dice side |
| firstSideDicePointColor | the point color of the first dice side |
| firstSideDiceBgColor  | the bg color of the first dice side |
| firstSideDiceBorderColor | the border color of the first dice side |
| secondSideDiceNumber  | the dice number{1-6} of the second dice side |
| secondSideDicePointColor | the point color of the second dice side |
| secondSideDiceBgColor  | the bg color of the second dice side |
| secondSideDiceBorderColor | the border color of the second dice side |
| thirdSideDiceNumber  | the dice number{1-6} of the third dice side |
| thirdSideDicePointColor | the point color of the third dice side |
| thirdSideDiceBgColor  | the bg color of the third dice side |
| thirdSideDiceBorderColor | the border color of the third dice side |
| fourthSideDiceNumber  | the dice number{1-6} of the fourth dice side |
| fourthSideDicePointColor | the point color of the fourth dice side |
| fourthSideDiceBgColor  | the bg color of the fourth dice side |
| fourthSideDiceBorderColor | the border color of the fourth dice side |

<br>

### Note
In order to show better effect, you should specify that the DiceLoadingView's width and height are equal.


## [LICENSE](https://github.com/samlss/DiceLoadingView/blob/master/LICENSE)
