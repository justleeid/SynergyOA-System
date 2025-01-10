## 一、架构说明
项目是基于若依3.8.3前后端分离版本开发的，官网是www.ruoyi.vip。
想深入学习代码架构，需学习一下若依框架。
## 二、代码运行说明
### 1.配置jdk8；
### 2.配置maven3.8+，最好用阿里云镜像仓库；
### 3.初始化数据库，具体操作见数据库文档；
### 4.用idea 导入项目代码，下载依赖；直到项目不报错；
### 5.项目结构见目录下<<项目代码结构图.jpg>>所示；

### 6.根节点项目的pom.xml中模块配置

```xml
<modules>
<module>fno-admin</module>
<module>fno-framework</module>
<module>fno-system</module>
<module>fno-quartz</module>
<module>fno-generator</module>
<module>fno-common</module>
<module>fno-pro</module>
<module>fno-workflow</module>
<module>fno-oa</module>
</modules>
```
### 7.修改fno-admin下的application-dev.yml(开发环境)和application-prod.yml(生产环境)中的mysql和redis配置信息；属性profile: 表示下载存储路径，根据需要配置windows和linux下的路径；
### 8.项目启动入口是fno-admin的Application,控制台打印出"启动成功，欢迎使用"表示启动成功；
### 9.项目打包参考下图，执行后，生成的fno-admin.jar在fno-admin目录下的target目录下。

### 10.把生成的fno-admin.jar上传到linux。执行
```shell
java -Xms64m -Xmx512m -Xmn128m -Xss512k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m \-XX:CompressedClassSpaceSize=64m -jar pointlion-admin.jar &
```