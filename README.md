# ElegantBus-IPC-Example
[![](https://jitpack.io/v/codyer/ElegantBus.svg)](https://jitpack.io/#codyer/ElegantBus)


ElegantBus 是一个Android平台的消息总线框架，这是一款非常**优雅**的消息总线框架。
各种情况都做了全面考虑和测试，使用简单，配置灵活安全高效。

ElegantBus 跨进程支持，支持多应用多进程，进程间粘性事件

## 常用消息总线对比

消息总线 | 延迟发送 | 有序接收消息 | Sticky | 生命周期感知 | 跨进程/APP | Customize（定制能力） | 线程分发 | 消息分组 | 跨App安全保障 |
---|---|---|---|---|---|----|---
EventBus | :x: | :white_check_mark: | :white_check_mark: | :x: | :x: | :x: | :white_check_mark: | :x: | :x: |
RxBus | :x: | :x: | :white_check_mark: | :x: | :x: | :x: | :white_check_mark: | :x: | :x: |
LiveEventBus | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :x: | :x: | :x:  | :x: |
ElegantBus | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: | :x: | :white_check_mark:  | :white_check_mark: |

此工程为测试 ElegantBus 的示例代码。
为了进行更全面的测试，以及更好的使用示例说明，特意开了此项目。

manifestPlaceholders = [
                    BUS_SUPPORT_MULTI_APP  : true,
                    BUS_MAIN_APPLICATION_ID: "com.example.bus"
            ]

BUS_SUPPORT_MULTI_APP : 是否支持跨App
BUS_MAIN_APPLICATION_ID : 公用组的主App，必须肯定被安装的app的applicationId
为了App安全性，必须使用相同的密钥签名的App才可以设置为一个公用组，否则直接抛出异常


更多信息请移步至 ElegantBus
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.codyer.ElegantBus:bus-ipc-messenger:2.0.0'
	}
```

LifeCycle 参考图：
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxmophq4dj315r0g7jt7.jpg)
#### 测试场景分析

按照事件类型划分，无论什么场景都要满足的要求:

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

不同场景测试结果：
场景覆盖:{
    单一进程:{
        不需要引入IPC包，直接按事件类型测试OK,
    }

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

