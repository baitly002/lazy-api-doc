[toc]
# 使用文档注解
------
## 注解在类上
使用@Doc注解

当@Doc("api权限管理")时，分组名为"api权限管理",描述为空

当@Doc(value = "api权限管理", remark = "这里是描述")

当没有@Doc注解时，分组名为类名
```java
@RestController
@RequestMapping(value="/api_permission")
@Doc("api权限管理")
public interface ApiPermissionService extends GenericBaseService<ApiPermission>{
    ...
}
```
------
------

## 注解在方法上

### 同样使用@Doc 注解对参数进行说明

@Doc相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
name | 是 | 参数名 | | 当作用于接口参数时，必须<br>当作用于接口时，非必须
value | 否 | 中文名 |  | 参数中文含义<br>当作用于接口时，作为接口标题
remark | 否 | 备注 |  | 说明<br>当作用于接口时，作为接口描述
dataType | 否 | 参数类型 |  |  
example | 否 | 示例 |  | 
defaultValue | 否 | 默认值 |  | 
required | 否 | 是否必须 | false |
ignore | 否 | 是否忽略 | false |
scope | 否 | 作用范围 | true | true：方法中所有参数，包括JAVABEAN <br> false: 仅仅方法上的doc注解 <br>仅在作用于接口时有效


------
### 使用@IgnoreParameter来排除某些不需要的参数

@IgnoreParameter相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 要排除的参数 | | 字符串模式，多个用逗号隔开
ignore | 否 | 要排除的参数 |  | 字符串数组模式

以下三种写法等同
```java
@IgnoreParameter(ignore = {"orderBy", "sord"})
@IgnoreParameter(value = "orderBy,sord")
@IgnoreParameter("orderBy,sord")
```

------
### 使用@RequireParameter来设置某些参数为必须传递的

@RequireParameter相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 必须传的参数 | | 字符串模式，多个用逗号隔开
require | 否 | 必须传的参数 |  | 字符串数组模式
exclude | 否 | 要排除的参数 |  | 字符串数组模式，排除所指定的参数为非必传，其于为必传(**未实现**)

以下三种写法等同
```java
@RequireParameter(require = {"page", "rows"})
@RequireParameter(value = "page,rows")
@RequireParameter("page,rows")
```

------
### 使用@ParameterFilter来设置必传参数，忽略参数，仅包含的参数

@ParameterFilter 集成了@IgnoreParameter 和 @RequireParameter 的功能，另还包括了仅包含指定参数的功能

@ParameterFilter相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 排除某参数 | | 字符串模式，与 excludes作用一样,多个用逗号隔开
includes | 否 | 仅包含所指参数 |  | 字符串数组模式 与excludes互斥
excludes | 否 | 要排除的参数 |  | 字符串数组模式 与includes互斥
requires | 否 | 必须传的参数 |  | 字符串数组模式
unRequires | 否 | 非必传参数 |  | 字符串数组模式 除此之外，其余为必传

------
### 使用@ReturnDoc来描述响应的数据

@ReturnDoc 通常作用于返回基本类型的方法，比如方法返回String Long int boolean等类型时

@ReturnDoc相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 响应数据说明 | | 字符串

------

### 使用@ReturnFilter来过滤响应数据

@ReturnFilter相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 排除某响应字段 | | 字符串模式，与 excludes作用一样,多个用逗号隔开
includes | 否 | 仅包含所指字段 |  | 字符串数组模式 与excludes互斥
excludes | 否 | 要排除的字段 |  | 字符串数组模式 与includes互斥
maxLevel | 否 | 最大层级 | 0 | 
type | 否 | 作用模式 | simple | simple:作用所有同名属性 <br> level:作用指定某层级下的属性
sort | 否 | 排序 | true |  
prefix | 否 | 前缀 |  | 对排除（包含）某属性，指定前缀,仅作用于type=level

------
------
## 注解在JAVABEAN属性上

### 同样使用@Doc 注解对属性进行说明

@Doc相关属性说明

属性 |  是否必须 | 定义 | 默认值 | 说明
--- | --- | --- | --- | ---
value | 否 | 中文名 |  | 参数中文含义
remark | 否 | 备注 |  | 说明
example | 否 | 示例 |  | 
defaultValue | 否 | 默认值 |  | 
required | 否 | 是否必须 | false |
ignore | 否 | 是否忽略 | false | 
