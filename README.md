# provider_template

语言： [中文简体](https://juejin.cn/post/6968272002515894303)

## provider usage

- 掘金：[使用+源码剖析：Flutter Provider的另一面](https://juejin.cn/post/6968272002515894303) 

## Renderings

- Plug-in effect

   - There are some optional functions, so make it into a multi-button style 
   - you can operate according to your own needs

![provider_plugin](https://cdn.jsdelivr.net/gh/CNAD666/MyData@master/pic/flutter/blog/20210521162031.gif)

- Alt + Enter ： Consumer、Selector、ChangeNotifirProvider

![image-20210605152137006](https://cdn.jsdelivr.net/gh/CNAD666/MyData@master/pic/android/flutter/blog/20210605152140.png)

- enter the **provider**  prefix

![image-20210605152343392](https://cdn.jsdelivr.net/gh/CNAD666/MyData@master/pic/android/flutter/blog/20210605152735.png)

## Description

The description of the plugin

- Model: generates the provider mode,

    - Default: the Default mode. two files are generated: view, provider
    - High: in simple mode, three files are generated: view, provider, state
    - Extended: a extended usage

- Function: Function selection
    - useFolder: use a file. After you select it, a folder is generated. The Hump name is automatically converted to lowercase + underscore.
    - usePrefix: use the prefix, the generated file front prefix, prefix for: large hump named automatically converted to: lowercase + underline
    - null-safety: support to null-safety

- Module Name: the Name of the Module. Use the hump Name.
