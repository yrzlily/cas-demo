cas-Demo
====

项目目录
-------

* cas-client        测试项目A
* cas-server    测试项目B
* cloud-config    spring cloud的一些基本配置，虽然也只有个注册服务中心


# 配置认证中心

首先先配置个tomcat，这个你会的我就不讲了，
先去cas的官方github下载项目 https://github.com/apereo/cas-overlay-template （我下的版本是5.3）
然后运行build.cmd生成war放在tomcat的webapp文件夹下运行就可以了
运行之后tomcat会生成一个cas文件夹，就你运行war后生成的东西

##  Cas开始只支持https，所以要用的话有两个方案，一是配置项目使用https，二是关闭https，步骤如下
* 1、进入webapps\cas\WEB-INF\classes 路径修改application.properties文件
  * 在最下面添加，主要是关闭https
  * cas.tgc.secure=false
  * cas.serviceRegistry.initFromJson=true
* 2、再进入webapps\cas\WEB-INF\classes\services 修改 HTTPSandIMAPS-10000001.json文件
  * 修改第三行的"serviceId" : "^(https|imaps)://.*"，在里面加个http
  * "serviceId" : "^(https|http|imaps)://.*"
  
### 修改完后再次运行tomcat，记得把war删了，访问http://127.0.0.1:8080/cas/login （实际路劲看你的，不过一般都是这个路劲），进入cas的登陆界面，用户密码在webapps\cas\WEB-INF\classes下文件application.properties最下面可以配置，就是你刚刚打开过的，配置的代码如下（这个我已经弄过的了）cas.authn.accept.users=admin::123456，能登陆成功的话这个配置就已经成功了。

# 测试项目配置

测试项目的话随便建立一个boot项目就行了，需要用到security和cas整合，项目的话你看cas-client就可以了，cas-server是我复制client的，里面都写了注解，配置三个类，控制器一个类文件，你这里先建立两个测试项目运行，用security设置路劲权限，然后试试访问需要权限的路径，如果有设置权限的话都会跳到cas自带的登陆页面，这里的cas我还没完美整合，认证的话security已经可以使用，登陆的话还是跳到cas自带的登陆页面，你从其中一个模块登陆之后试试访问另一个需要权限的页面，应该是可以访问的到的

