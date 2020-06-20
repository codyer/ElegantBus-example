# ElegantBus-IPC-Example
[![](https://jitpack.io/v/codyer/ElegantBus.svg)](https://jitpack.io/#codyer/ElegantBus)

ElegantBus 是一款 Android 平台，基于LivaData的消息总线框架，这是一款非常 **优雅** 的消息总线框架。

此工程为测试 ElegantBus 的示例代码。
为了进行更全面的测试，以及更好的使用示例说明，特意开了此项目。

如果对 ElegantBus 的实现过程，以及考虑点感兴趣的可以去看看源代码，[传送门](https://github.com/codyer/ElegantBus)


为了 ElegantBus 更好的为大家提供服务，更好的兼容性，我特意做了很多场景的测试，可能会有覆盖不到的，如果遇到问题，欢迎留言评论

#### 测试场景分析

##### 按照事件类型划分，预期结果:
```
事件类型:{
     Normal:{
        注册时:之前的事件不会接收到,
        注册之后:{
            激活:立即收到,
            非激活:变成激活后收到
        }
     },
     Sticky:{
        注册时:会接收到最后一次事件,
        注册之后:{
            激活:立即收到,
            非激活:变成激活后收到
        }
     },
     Forever:{
        注册时:之前的事件不会接收到,
        注册之后:立即收到
     }
}
```
##### 不同场景预期结果：
```
场景覆盖:{
    单一进程:{
        不需要引入IPC包，直接按事件类型测试OK,
    },
    多进程:{// 导入IPC包，APK1、APK2
        单一App:{
           设置（isMultiApp = false）:直接按事件类型测试OK,
        },
        多App:{
             同一个包服务下:{
                 同时打开跨App支持:{
                     按事件类型测试OK
                 },
                 只有一方打开:{
                     本App内正常，跨App无法执行
                 },
             },
             非一个包服务下:不会接收到
         }
    }
}
```

#### LifeCycle 参考图：

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxmophq4dj315r0g7jt7.jpg)


## ElegantBus来龙去脉
### 自吹
ElegantBus 是一款 Android 平台，基于LivaData的消息总线框架，这是一款非常 **优雅** 的消息总线框架。

ElegantBus 支持跨进程，且支持跨应用的多进程，甚至是支持跨进程间的粘性事件，支持事件管理，支持事件分组，支持自定义事件，支持同名事件等。

之所以称之为最优雅的总线，是因为她不仅实现了该有的功能，而且尽量选用最合适，最轻量，最安全的方式去实现所有的细节。
更值得夸赞的是使用方式的优雅！

### 前言
随着LifeCycle的越来越成熟，基于LifeCycle的LiveData也随之兴起，业内基于LiveData实现的EventBus 也如雨后春笋一般拔地而起。

出于对技术的追求，看过了无数大牛们的实现，各位大神们思路也是出奇的神通，最基础的 LiveData 版 EventBus 其实大同小异，一个单例类管理所有的事件LivaData集合。`如果不清楚的可以随便网上找找`

反正基本功能 LivaData 都支持了，实现 EventBus 只需要把所有事件管理起来就完事了。

业内基于LiveData实现的EventBus，其实考虑的无非就是下面提到的五个挑战，有的人考虑的少，有的人考虑的多，于是各种方案都有。

ElegantBus 主要是集合各家之优势，进行全方面的考虑而产生的。

### 五个挑战 之 路途险阻
#### 挑战一 ： 粘性事件
+ 背景
LivaData的设计之初是为了数据的获取，因此无论是观察开始之前产生的数据，还是观察开始之后产生的数据，都是用户需要的数据，只要是有数据，当LifeCycle处于激活状态，数据就会传递给观察者。这个我们称之为 **粘性数据**。
这种设计对于事件来说有时候就不那么友好了，之前的事件用户可能并不关心，只希望收到注册之后发生的事件。

#### 挑战二 ： 多线程发送事件可能丢失
+ 背景
同样是因为使用场景的原因，LivaData设计在跨线程时，使用post提交数据，只会保留最后一次数据提交的值，因为作为数据来说，用户只需要关心现在有的数据是什么。

#### 挑战三 ： 跨进程事件总线
+ 背景
有时候我们应用需要设置多进程，不同模块可能允许在不同进程中，因为单例模式每个进程都有一份实体，所有无法达到跨进程，这时候设计IP方案选择。

+ 说明
这里提一下为什么不选用广播方式，对广播有一定了解的都知道，全局广播会有信息泄露，信息干扰等问题，而且开销也比较大，因此全局广播并不适合这种情况。
也许有人会说可以用本地广播，然而，本地广播目前来说并不是很好的选择。

Google官方也在 LocalBroadcastManager 的说明里面建议使用LiveData替代：
[原文地址](https://developer.android.google.cn/jetpack/androidx/releases/localbroadcastmanager?hl=zh_cn)

##### 原文如下：

> 2018 年 12 月 17 日
> 
> 版本 1.1.0-alpha01 中将弃用 androidx.localbroadcastmanager。
> 
> 原因
> 
> LocalBroadcastManager 是应用级事件总线，在您的应用中使用了层违规行为；任何组件都可以监听来自其他任何组件的事件。
它继承了系统 BroadcastManager 不必要的用例限制；开发者必须使用 Intent，即使对象只存在且始终存在于一个进程中。由于同一原因，它未遵循功能级 BroadcastManager。
这些问题同时出现，会对开发者造成困扰。
>
> 替换
>
> 您可以将 LocalBroadcastManager 替换为可观察模式的其他实现。合适的选项可能是 LiveData 或被动流，具体取决于您的用例。

更明显的原因是，本地广播好像并不支持跨进程~


#### 挑战四 ： 跨应用（权限问题）
+ 背景
跨进程相对来说还比较好实现，但是有的时候用户会有跨应用的需求，其实这个也是IPC范畴，为什么单独提出来呢？
因为跨应用设计信息安全，权限校验问题，开放给其他应用，但是同时又要兼顾不被非法滥用。

#### 挑战五 ： 兼容性，简洁性
+ 背景
一个好的事件总线需要很好的兼容，不同事件应该有个很好的管理，不会造成冲突，事件可以进行多种配置，如某事件是否支持跨进程，是否激活，属于什么分组等等。



### 和常见LivaData实现的EventBus比较

消息总线 | 使用反射 | 入侵系统包名 | 进程内Sticky | 跨进程Sticky | 跨APP Sticky | 事件可配置化 | 线程分发 | 消息分组 | 跨App安全考虑 |常驻事件Sticky
---|---|---|---|---|---|----|---|---|---|---
LiveEventBus | :white_check_mark: | :white_check_mark: | :white_check_mark: | :x: | :x: | :x: | :x: | :x:  | :x: |:x: 
ElegantBus | :x: | :x: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark:  | :white_check_mark: | :white_check_mark: 


#### 使用说明以及更多信息请移步至 [ElegantBus](https://github.com/codyer/ElegantBus) 
- 欢迎 Star 和提交 Issue
