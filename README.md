#ClearEditTextProject
-------------------
前两天项目中需要用到带清除按钮的输入框，Android的控件就是不好，都不自带的，看iOS的多好，自带光环……哈哈

不吹牛了，本来是打算找一个的，但是一想也不是很难就自己撸一个。

####效果展示

![image](https://github.com/MZCretin/ClearEditTextProject/blob/master/pic/ezgif.com-video-to-gif.gif)

---------------

我用了三种方式实现了一下这个功能。

**第一种**

直接用ViewGroup包住两个控件，一个EditText，一个ImageView，用代码布局好，然后去监听EditText的输入状态，当EditText内容不为空的时候显示ImageView，在EditText内容为空的时候隐藏ImageView，然后给ImageView添加监听事件，然后清空EditText，这是最简单想到的。下面贴出代码。

这种实现方式，需要分别获取到EditText和ImageView然后分别用Java代码对两者的属性进行设置，修改展示的样式不是很方便。

**第二种**

自定义一个View继承自EditText，然后通过设置setCompoundDrawables(null, null, mClearDrawable, null);来设置右边的图片，然后通过监听onTouchEvent并且通过判断点击的位置判断是否点击到清除按钮来清除EditText的内容与否。

这种实现的方式比上一种实现的方式好的地方在于直接继承自EditText，拥有EditText的所有方法，比较方便修改自定义样式，但是还是又一个明显的缺点，因为内部是使用setCompoundDrawables(null, null, mClearDrawable, null)设置清除按钮的图片，所以使用者在自己使用setCompoundDrawables方法的时候会覆盖原有的逻辑，所以不能使用这个方法。

**第三种（推荐）**

自定义View继承自EditText，然后通过计算View的宽和高，确定右边的删除按钮的大小，在onDraw方法里面，我们会绘制一个删除按钮，按钮的大小由用户控制，我提供一个对外的接口，用于设置删除按钮的缩放大小，默认设置成0.5，也就是说按钮的大小是控件高度的一般，这样做的好处是方便适配所有情况。在安放好删除按钮之后，通过监听onTouchEvent方法监听按钮所在位置范围的点击事件的监听，检测到点击事件并且有文本框内容的时候，就清空文本框的内容。再监听addTextChangedListener，当内容不为空的时候，重绘删除按钮。

这种实现的方式好在不会占用EditText本身的方法，最大化放宽EditText的自定义范围。

-------------

推荐的是第三种方式，所以我打了个library上传上去了，至于其他两种方式，感兴趣的同学可以去clone源码下来试试水！

提供一个简单集成方式：

**Step 1**. Add the JitPack repository to your build file 

```
 allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

**Step 2**. Add the dependency

```
dependencies {
        compile 'com.github.MZCretin:ClearEditTextProject:1.1.0'
    }
```

####有什么意见或者建议欢迎与我交流，觉得不错欢迎Star

使用过程中如果有什么问题或者建议 欢迎在issue中提出来 嘿嘿

**博客地址** http://blog.csdn.net/u010998327/article/details/77104711

####如果您觉得对您有帮助，请打赏我一杯咖啡的钱：

![image](https://github.com/MZCretin/ClearEditTextProject/blob/master/pic/WEIXIN.png)
