## 一、架构说明
本项目是基于 若依框架 3.8.3 版本开发的前后端分离系统，适用于企业级应用开发。若依框架是一个开源的 Java 开发框架，提供了丰富的功能模块和高效的开发体验。
项目地址：https://www.ruoyi.vip
## 二、架构设计
项目采用模块化设计，包含多个子模块，每个模块负责不同的功能。以下是主要模块的说明：
- fno-admin：管理模块，提供 Web 服务，是项目的启动入口。
- fno-framework：核心框架模块，提供基础的框架支持。
- fno-system：系统模块，管理用户、角色、权限等功能。
- fno-quartz：定时任务模块，支持任务调度。
- fno-generator：代码生成模块，用于快速生成代码。
- fno-common：通用工具模块，提供通用工具类。
- fno-pro：核心业务模块。
- fno-workflow：流程引擎模块，基于 Flowable 实现工作流管理。
- fno-oa：办公模块，提供办公自动化功能。
## 三、代码运行说明
### 1. 环境准备
   - JDK：配置 JDK 8+。
   - Maven：配置 Maven 3.8+，推荐使用阿里云 Maven 镜像仓库。
   - 数据库：初始化 MySQL 数据库，脚本位于项目根目录下的 fno.sql 文件，需要 MySQL8.0+。
   - Redis：配置 Redis，相关信息在 fno-admin 模块的 application-dev.yml（开发环境）和 application-prod.yml（生产环境）中。
### 2. 项目导入
   使用 IntelliJ IDEA 导入项目代码。
   下载 Maven 依赖，确保项目无报错。
### 3. 配置文件
   修改 fno-admin 下的 application-dev.yml 和 application-prod.yml，配置 MySQL 和 Redis 的连接信息。
   配置文件中的 profile 属性表示文件下载存储路径，根据需要配置 Windows 和 Linux 下的路径。
### 4. 启动项目
   启动入口为 fno-admin 模块中的 Application 类。
   控制台打印出“启动成功，欢迎使用”表示项目启动成功。
### 5. 打包部署
   在项目根目录执行以下命令进行打包：
   ```bash
   mvn clean package -DskipTests
   ```
   打包完成后，fno-admin.jar 文件位于 fno-admin/target 目录下。
   将 fno-admin.jar 上传至 Linux 服务器，执行以下命令启动：
   ```bash
   java -Xms64m -Xmx512m -Xmn128m -Xss512k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=64m -jar fno-admin.jar &
  ```
## 四、技术栈
### 1. 核心框架
   - Spring Boot：底层框架，提供依赖管理和自动配置。
   - Spring Framework：用于构建 Web 应用和服务，包括 Spring Web、Spring AOP 等模块。
### 2. 持久层
   - MyBatis：用于数据库操作，支持 SQL 映射和动态 SQL。
   - Druid：阿里提供的数据库连接池，支持 SQL 监控和统计。
   - PageHelper：分页插件，与 MyBatis 集成。
### 3. 数据库
   - MySQL：主要数据库。
   - Redis：缓存和临时数据存储，通过 Spring Boot 集成。
### 4. API 文档
   - Swagger 3：生成 RESTful API 文档，支持 API 描述、测试和文档生成。
### 5. 工具类库
   - Hutool：Java 工具类库，简化开发。
   - Commons IO：文件和 I/O 操作。
   - Commons FileUpload：文件上传处理。
   - Commons Collections：额外的集合工具类。
   - Commons Lang3：字符串和工具类。
### 6. JSON 处理
   - Fastjson：JSON 序列化和反序列化。
   - Jackson：Spring Boot 默认的 JSON 处理库。
### 7. 安全与认证
   - JWT：生成和解析 JSON Web Tokens，用于身份验证和授权。
   - Spring Security：安全认证和授权。
### 8. 其他功能
   - Kaptcha：验证码生成。
   - Lombok：简化 Java 代码，减少样板代码。
   - Flowable：工作流和业务流程管理。
### 9. 其他技术
   - Spring Boot DevTools：开发工具支持，如热部署。
   - Spring Boot Starter Websocket：WebSocket 支持。
   - Spring Boot Starter Validation：数据校验。
   - JAXB：XML 绑定。
   - SnakeYAML：YAML 文件解析。