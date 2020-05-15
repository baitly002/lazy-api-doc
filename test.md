  

# [数据库设计规范](#数据库设计规范)

### [表名规范](#表名规范)

例如：**repair\_account\_process**

-   表名、字段名全小写，用下划线隔开。
-   表名第一个为模块（组）名称，后面的才是表的具体名称
-   repair为模块名称，account\_process为具体表名，对应的实体名为AccountProcess
-   根据表名规范，对应的请求路径格式为：/系统名称/模块名/具体表名

例如：

> [http://127.0.0.1/systemName/repair/accountProcess](http://127.0.0.1/systemName/repair/accountProcess)

> [http://127.0.0.1/systemName/repair/accountProcess/1234455666](http://127.0.0.1/systemName/repair/accountProcess/1234455666)

![table](http://10.201.62.26:8666/confluence/download/attachments/20578312/table.png?version=1&modificationDate=1588036401804&api=v2)

-   每张表必须含有一个主键（关联表除外），统一为id，long类型，为了分布式，到时通过雪花算法生成
-   如果业务处理中涉及到逻辑删除的，字段名统一定义为is\_delete，0未删除，1已删除
-   每个字段须写清楚含义，第一个空格前的为字段中文名称，后面的全部为备注说明

**其他说明**：

-   对于数据库中的触发器，命名前缀为trigger\_\*\*\* 如：trigger\_repair\_process
-   对于数据库中的视图，命名前缀为view\_\*\*\* 如：view\_account

### [注释规范](#注释规范)

-   项目开发中，主要有4个地方是要写清楚注释的
    1.  **controller类**
        -   类上面要写清楚该类的功能类别。
        -   对外返回数据的方法（即面向前端的接口）则要写清楚该方法的功能，必要时还要加备注。
        -   方法的参数也要写清楚注释，主要是基本类型的参数，实体类型的参数则由框架生成处理。
        -   参数的传入方式统一用spring的参数绑定模式，不要采用从request请求中获取这种方式。
        -   对于需要用户登录的接口，统一注入用户基本信息（方便后面扩展记录用户行为操作）。
        -   方法的返回类型必须清楚定义，不要返回map，object……这些不明结构的数据
        -   写法参考如下：

![controller](http://10.201.62.26:8666/confluence/download/attachments/20578312/controller.png)

2.  **接口类（service服务接口、dubbo服务接口）**
    -   写法参考如下：

![interface](http://10.201.62.26:8666/confluence/download/attachments/20578312/interface.png?version=1&modificationDate=1588037786217&api=v2)

3.  **实体类**
    -   各成员属性写清楚注释，每个set方法都返回自身对象。
    -   写法参考如下：

![bean](http://10.201.62.26:8666/confluence/download/attachments/20578312/bean.png?version=1&modificationDate=1588037742174&api=v2)

4.  **提供给他人使用的封装类（主要为工具类）**
    -   方法及参数说明要写清楚

![paramter](http://10.201.62.26:8666/confluence/download/attachments/20578312/paramter.png?version=1&modificationDate=1588037829824&api=v2)

### [异常定义规范](#异常定义规范)

-   **异常定义为7位数**

* * *

0

00

0000

0

00

0000

第一位表示异常类型

第2、3位数字代表系统编号

具体业务编号

1代表系统异常<br/>即一些公用组件抛出的异常<br/>

比如：<br/>01代表支付系统<br>02代表认证系统

开发人员自己定义，配置完成后记录到配置文件或配置中心<br/>

2代表业务异常<br/>主要是业务系统抛出的异常

-   特殊编码：1 00 0000 表示系统未知异常，即开发里没有封装到的
-   注意考虑，当接口返回NULL时，是否特殊处理
-   参考如下：

![exp1](http://10.201.62.26:8666/confluence/download/attachments/20578312/exp1.png?version=1&modificationDate=1588037904373&api=v2) ![exp2](http://10.201.62.26:8666/confluence/download/attachments/20578312/exp2.png?version=1&modificationDate=1588037920033&api=v2)

这样抛出异常后，给框架封装返回的数据。因为开发中返回的信息不一定符合上线后面向用户的信息，到时配合配置中心使用，如果配置中心有相应异常编码的信息配置，将会替换代码中的提示。

另外，需要向用户返回的提示信息也应设计成编号与消息内容对应的方式。

### [统一数据格式返回](#统一数据格式返回)

-   对外接口的数据返回格式统一，由框架包装

```json
{
    status: "int调用接口是否成功，0：失败，1：成功",
    errorCode: "string错误码，当status=0时，必须返回错误码",
    message: "string错误具体信息",
    data: {
       //具体内容
    }
}

```

-   方法的返回类型必须清楚定义，不要返回map，object……这些不明结构的数据
    
-   是否需要参考httpstatus封装
    

### [基于规范后的功能扩展](#基于规范后的功能扩展)

-   根据以上各项规范，提供自动生成代码模块功能，即普通的增删改查功能

![](http://10.201.62.26:8666/confluence/download/attachments/20578312/org.png?version=1&modificationDate=1588038028326&api=v2)

-   自动生成的mapper文件，不能修改，重新生成时会覆盖

![](http://10.201.62.26:8666/confluence/download/attachments/20578312/mapper.png?version=1&modificationDate=1588038001603&api=v2)

-   可扩展的mapper文件。除了返回数据的映射不能修改，其于的都可扩展修改

![](http://10.201.62.26:8666/confluence/download/attachments/20578312/mapper2.png?version=1&modificationDate=1588038017921&api=v2)

//<!\[CDATA\[ AJS.$('\[data-macro-name="markdown"\] code').each(function(i, block) { hljs.highlightBlock(block); }); //\]\]> .hljs {display: inline;} pre > code {display: block !important;} //<!\[CDATA\[ AJS.$('\[data-macro-name="markdown"\] table thead th').each(function(i, block) { block.classList.add("confluenceTh"); }); AJS.$('\[data-macro-name="markdown"\] table tbody tr td').each(function(i, block) { block.classList.add("confluenceTd"); }); //\]\]>

赞