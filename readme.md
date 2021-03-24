## UploadMavenPlugin

> 为简化Android Library上传到jitpack.io的流程，简化相关配置

### 最新版本号
 [![](https://jitpack.io/v/mr-woody/UploadMavenPlugin.svg)](https://jitpack.io/#mr-woody/UploadMavenPlugin)
### 如何接入

```

//In the project build.gradle.

buildscript {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        ...
        classpath "com.github.dcendents:android-maven-gradle-plugin:2.1"
        classpath 'com.github.mr-woody.UploadMavenPlugin:jitpack:1.2.0'
}


```


### 如何使用


```
apply plugin: 'jitpack.io.upload'
jitpack{
    module{
        //对应的library name
        name "library1"
        //对应格式为com.github.[YourUsername],例如：https://github.com/mr-woody/UploadMavenPlugin ,那么对应的UserName为：mr-woody
        group "com.github.mr-woody"
    }
    module{
        //对应的library name
        name "library2"
        group "com.github.mr-woody"
    }
}
```

### jitpack.io 发布注意事项
   
   1. jitPack针对的项目对象：github公开项目（public）
   2. jitPack发布library时,需要区分两种情况：
    第一种： 当前项目就只有一个library的时候，那么发布后，gradle访问方式`只能`(重点强调,趟过坑人伤不起啊)是：com.github.[YourUsername]:[GithubProjectName]:[Tag Version or Release Version]
    第二种： 当前项目中有2个及其以上的library的时候，那么发布后，gradle访问方式为：
        ```
           com.github.[YourUsername].[GithubProjectName].[libraryName]:[Tag Version or Release Version]
           //或者
           com.github.[YourUsername]:[GithubProjectName]:[Tag Version or Release Version]
        ```
      同理使用上面的插件，只配置一个module，同样符合上面的规则。
   3. 当前项目使用了上述插件以后，需要上传代码到github上，执行github上的打一个新的Tag或者一个新的Release，最后就是去https://jitpack.io/ 网站，输入你当前git项目的path地址，通过Look up按钮，选择你在github上对应的版本号，执行Get it.