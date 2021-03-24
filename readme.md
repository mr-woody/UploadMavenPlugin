## UploadMavenPlugin

> 为简化Android Library上传到jitpack.io的流程，简化相关配置

### 版本
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
        classpath 'com.github.mr-woody.UploadMavenPlugin:jitpack:1.1.3'
}


```


### 如何使用


```
apply plugin: 'jitpack.io.upload'
jitpack{
    module{
        name "library1"
        group "com.woodys.upload"
    }
    module{
        name "library2"
        group "com.woodys.upload"
    }
}
```



