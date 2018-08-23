# DiceLoadingView

一个骰子3D旋转loading view 

你可以指定立方体中每一面骰子的点数，颜色和背景，同时也可以指定执行的动画时间和动画插值器

### [更多](https://github.com/samlss/FunnyViews)

 <br/>

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/DiceLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/DiceLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)


![gif1](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot1.gif)

![gif2](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot2.gif)

![gif3](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot3.gif)

------

在开始介绍DiceLoadingView之前，先看一些骰子即DeiceView的组成

## DiceView
在DiceLoadingView中应用的一个骰子view

以下为效果图：
![DiceView](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/screenshot4.gif)

### 使用<br>
布局中：

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

代码：

```
diceView.setNumber(2); //设置骰子点数，必须为1-6
diceView.setPointColor(Color.RED); //设置点的颜色
diceView.setBgColor(Color.RED); //设置背景颜色
diceView.setBorderColor(Color.RED); //设置边界颜色
```

属性说明：

开始描述效果图之前，先看一张说明图：

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description2.png)

| attr            |         description         |
| --------------- | :-------------------------: |
| number | 骰子点数 one(1), two(2), three(3), four(4), five(5), six(6) |
| pointColor  | 点的颜色 |
| bgColor  | 背景颜色 |
| borderColor | 边界颜色 |

## DiceLoadingView
### Use<br>
在根目录的build.gradle添加这一句代码： 

```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用： 

```
dependencies {
    implementation 'com.github.samlss:DiceLoadingView:1.0'
}
```


布局中使用： 

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

代码中使用： 

```java
diceLoadingView.start(); //开始动画
diceLoadingView.stop(); //停止动画
diceLoadingView.pause(); //暂停动画
diceLoadingView.resume(); //恢复动画
diceLoadingView.release(); //不需要使用该loading view的时候可手动释放，例如在activity的ondestroy()中

diceLoadingView.setDuration(3000); //设置动画时间
diceLoadingView.setInterpolator(new AnticipateOvershootInterpolator()); //设置动画插值器
diceLoadingView.setFirstSideDiceNumber(2); //设置第一面骰子点数(1-6)
diceLoadingView.setFirstSidePointColor(Color.parseColor("#FF7D81")); //设置第一面骰子点的颜色
diceLoadingView.setFirstSideDiceBgColor(Color.WHITE); //设置第一面骰子背景颜色
diceLoadingView.setFirstSideDiceBorderColor(Color.GRAY); //设置第一面骰子边界颜色

diceLoadingView.setSecondSideDiceNumber(3); //设置第二面骰子点数(1-6)
diceLoadingView.setSecondSidePointColor(Color.BLUE); //设置第二面骰子点的颜色
diceLoadingView.setSecondSideDiceBgColor(Color.WHITE); //设置第二面骰子背景颜色
diceLoadingView.setSecondSideDiceBorderColor(Color.BLUE); //设置第二面骰子边界颜色

diceLoadingView.setThirdSideDiceNumber(4); //设置第三面骰子点数(1-6)
diceLoadingView.setThirdSidePointColor(Color.GREEN); //设置第三面骰子点的颜色
diceLoadingView.setThirdSideDiceBgColor(Color.WHITE); //设置第三面骰子背景颜色
diceLoadingView.setThirdSideDiceBorderColor(Color.GREEN); //设置第三面骰子边界颜色

diceLoadingView.setFourthSideDiceNumber(5); //设置第四面骰子点数(1-6)
diceLoadingView.setFourthSidePointColor(Color.RED); //设置第四面骰子点的颜色
diceLoadingView.setFourthSideDiceBgColor(Color.WHITE); //设置第四面骰子背景颜色
diceLoadingView.setFourthSideDiceBorderColor(Color.RED); //设置第四面骰子边界颜色
```
<br>


属性说明：

开始描述效果图之前，先看两张张说明图：

针对立方体而言

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description1.png)

针对每一个骰子面而言:

![picture](https://github.com/samlss/DiceLoadingView/blob/master/screenshots/description2.png)


| attr            |         description         |
| --------------- | :-------------------------: |
| animDuration  | 动画时间 |
| animInterpolator | 动画加速器 |
| firstSideDiceNumber  | 第一面骰子点数(1-6) |
| firstSideDicePointColor | 第一面骰子点的颜色 |
| firstSideDiceBgColor  | 第一面骰子背景颜色 |
| firstSideDiceBorderColor | 第一面骰子边界颜色 |
| secondSideDiceNumber  | 第二面骰子点数(1-6) |
| secondSideDicePointColor | 第二面骰子点的颜色 |
| secondSideDiceBgColor  | 第二面骰子背景颜色 |
| secondSideDiceBorderColor | 第二面骰子边界颜色 |
| thirdSideDiceNumber  | 第三面骰子点数(1-6) |
| thirdSideDicePointColor | 第三面骰子点的颜色 |
| thirdSideDiceBgColor  | 第三面骰子背景颜色 |
| thirdSideDiceBorderColor | 第三面骰子边界颜色 |
| fourthSideDiceNumber  | 第四面骰子点数(1-6) |
| fourthSideDicePointColor | 第四面骰子点的颜色 |
| fourthSideDiceBgColor  | 第四面骰子背景颜色 |
| fourthSideDiceBorderColor | 第四面骰子边界颜色 |

<br>

### 注意
为了更好的显示效果，请设置DiceLoadingView的宽高为相等的


## [LICENSE](https://github.com/samlss/DiceLoadingView/blob/master/LICENSE)