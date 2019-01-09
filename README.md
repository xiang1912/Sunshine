

#### 晴天娃娃介绍

> 之前通过两篇文章介绍了Mvp架构的使用

- [带实际例子的Android架构MVP简述一](https://www.jianshu.com/p/a05f26e630ce)
- [带实际例子的Android架构MVP简述二](https://www.jianshu.com/p/d00f912d81df)

> 接下来介绍一个简单的天气App，项目使用的就是Mvp+Retrofit+RxJava，作为一个功能简单的小项目，意义仅仅是对前两篇文章的知识进行巩固，以及分享一下项目开发文件整理的技巧

> 效果图

![简单天气APP.gif](https://upload-images.jianshu.io/upload_images/4002920-23e9d7d4d9ba51d6.gif?imageMogr2/auto-orient/strip)


> 看到这里你大概会很熟悉了，没错，这就是第一行代码里面最后天气项目的界面，这个小案例也是基于郭神的酷欧天气，只是在实现的方式上做了一些尝试

- 使用了Mvp+Retrofit+RxJava2
- 没有使用数据库存储地点信息，而是每一次都去进行联网请求
- 列表使用了RecyclerView，下拉选择使用了[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)，也使用了它进行城市选择列表的底部，顶部回弹效果

> 项目源码已经上传到Github[仓库](https://github.com/13531982270/Sunshine)的App-Java文件夹，同时kotlin文件夹下是同样功能的代码，只是使用了kotlin语言，相比起Java，kotlin确实能节约很多代码，很值得学习





#### **UI设计**图的使用

> 实际开发中，我们会拿到UI设计师给的页面UI图以及切图标注，如下图，[链接传送门](https://xd.adobe.com/spec/eaea2b58-f1fd-4a0f-6403-03bdc9239be3-9cdb/)


![UI图.png](https://upload-images.jianshu.io/upload_images/4002920-e1683b8792ef0f29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



> 我们只要把鼠标停留到相应的图标上面，就能看到相应的单位的dp尺寸，距离屏幕的距离，这个小项目的屏幕适配只是使用dp进行适配，如果要使用px适配我们就需要查看px的距离，具体可参考鸿阳前辈的[教程](https://blog.csdn.net/lmj623565791/article/details/45460089)

> 上面的设计图链接是我使用AdobeXD制作的，根据需要，使用Sketch+插件，或者photoshop+插件都是可以的，不过Adobe在win上的版本会出现闪退的问题，具体可查看我的另一篇[文章](https://www.jianshu.com/p/c549c5cb6d6d)。





#### **项目文件夹分类**

![文件夹分类.png](https://upload-images.jianshu.io/upload_images/4002920-4d1f3f26b50ce329.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- App 用来存放项目代码
- Doc 用来存放和项目有关的文档，脑图等
- UIDesign 用来存放设计文件
- Resource 用来存放不是上面种类的其他文件，如程序打包的Key文件，apk安装包，参考代码等



> 以上文件都已经上传到Github的[仓库](https://github.com/13531982270/Sunshine)了，该项目还未完全完成，后续还会更新弹窗，关于页面，热门城市选择页面，语音搜索城市等功能

> 文章到这里就结束了，如果你觉得文本就你有所帮助，希望能帮忙点个喜欢，这对我来说是种很好的鼓励，

![理直气壮](https://upload-images.jianshu.io/upload_images/4002920-19d460133f31b1c1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)