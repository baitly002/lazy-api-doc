### &nbsp;&nbsp;&nbsp;&nbsp; 系统介绍
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [1.1 用户功能介绍](#1.1用户功能介绍)
### &nbsp;&nbsp;&nbsp;&nbsp; 地址
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.1 添加地址](#2.1添加地址)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.2 获取地址信息](#2.2获取地址信息)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.3 修改地址信息](#2.3修改地址信息)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.4 删除地址](#2.4删除地址)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.5 excel导出](#2.5excel导出)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.6 查询地址列表](#2.6查询地址列表)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.7 excel导入](#2.7excel导入)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.8 查询所有子地址列表](#2.8查询所有子地址列表)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.9 excel模板下载](#2.9excel模板下载)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.10 级联刷新地点详细地址](#2.10级联刷新地点详细地址)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 统计数相关
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.11.1 获取监控设备总数](#2.11.1获取监控设备总数)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.11.2 获取已接入钢厂总数](#2.11.2获取已接入钢厂总数)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.11.3 获取监测数据](#2.11.3获取监测数据)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.11.4 获取城市节水节电除尘](#2.11.4获取城市节水节电除尘)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api权限管理
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.1 删除api权限](#2.12.1删除api权限)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.2 获取api权限列表](#2.12.2获取api权限列表)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.3 获取api权限树](#2.12.3获取api权限树)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.4 保存或修改](#2.12.4保存或修改)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.5 修改api权限状态](#2.12.5修改api权限状态)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.6 updateSelective](#2.12.6updateSelective)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [2.12.7 根据api权限id获取详情](#2.12.7根据api权限id获取详情)
### &nbsp;&nbsp;&nbsp;&nbsp; test1
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [3.1 111111111222](#3.1111111111222)
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; aaa
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [3.2.1 ddd](#3.2.1ddd)
### &nbsp;&nbsp;&nbsp;&nbsp; test2
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [4.1 测试接口](#4.1测试接口)

----

### 用户功能介绍
<p>&nbsp;</p>
<p>safsdaf</p>
<p>sadf</p>
<pre class="language-c"><code>{
"status": "[int] 调用接口是否成功，0：失败，1：成功",
"errorCode": "[string] 错误码，当status=0时，必须返回错误码",
"message": "[string] 错误具体信息",
"normalisedPath": "[string] 标准化路径",
"expressionPath": "[string] 匹配的表达式路径",
"data_": " 返回信息的具体内容",
"data": {
}
}</code></pre>


----


### 添加地址
#### 描述


<p>sadfasdfasdfds</p>

#### 请求地址
/mydemo/address
#### 请求方式
POST
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
id | long |  | 否 | form-data | 
name | string | 地点名称 | 是 | form-data | 
pathCode | string | 路径代码 | 是 | form-data | 暂定备用
superId | long | 所属公司编号 | 否 | form-data | 所属公司编号
parentId | long | 上级地点编号 | 是 | form-data | 
creatorId | long | 创建者编号 | 否 | form-data | 
createTime | date | 创建时间 | 否 | form-data | 
updaterId | long | 更新者编号 | 否 | form-data | 
updateTime | date | 更新时间 | 否 | form-data | 
address | string | 详细地址 | 是 | form-data | 
parentArr | string | 所有上级编号 | 否 | form-data | 逗号隔开
sort | integer | 排序序号 | 否 | form-data | 
image | string | 图片 | 否 | form-data | 
bgImage | string | 背景图片 | 否 | form-data | 
remark | string | 备注 | 否 | form-data | 
type | integer | 类型 | 否 | form-data | 地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的
typeName | string |  | 是 | form-data | 
level | integer | 层级 | 否 | form-data | parentID为0的是第一级，以此递增
childAddress | java.util.List<com.dashu.repair.core.model.Address> | 子级地址 | 否 | form-data | 
childArr | string | 所有下级编号 | 否 | form-data | 逗号隔开 第一个为本级
superCode | long | 所属公司编号 | 是 | form-data | 
childCount | integer | 子级地址数量 | 否 | form-data | 
parentNameArr | string | 所有上级名称 | 否 | form-data | >号隔开
parentName | string | 所有上级名称(不含自己) | 否 | form-data | >号隔开
parentArrFilter | long | 查询该节点所有子级 | 否 | form-data | 包含当前节点

#### 响应格式
```json
{
	"data":{
		"creatorId":"[long] 创建者编号",
		"typeName":"[string] ",
		"remark":"[string] 备注",
		"bgImage":"[string] 背景图片",
		"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
		"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
		"updaterId":"[long] 更新者编号",
		"id":"[long] ",
		"superId":"[long] 所属公司编号【所属公司编号】",
		"image":"[string] 图片",
		"address":"[string] 详细地址",
		"level":"[int] 层级【parentID为0的是第一级，以此递增】",
		"childAddress":[
			{
				"creatorId":"[long] 创建者编号",
				"typeName":"[string] ",
				"remark":"[string] 备注",
				"bgImage":"[string] 背景图片",
				"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
				"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
				"updaterId":"[long] 更新者编号",
				"id":"[long] ",
				"superId":"[long] 所属公司编号【所属公司编号】",
				"image":"[string] 图片",
				"address":"[string] 详细地址",
				"level":"[int] 层级【parentID为0的是第一级，以此递增】",
				"childAddress":[
					{}
				],
				"superCode":"[long] 所属公司编号",
				"updateTime":"[date] 更新时间",
				"childCount":"[int] 子级地址数量",
				"sort":"[int] 排序序号",
				"parentId":"[long] 上级地点编号",
				"parentArr":"[string] 所有上级编号【逗号隔开】",
				"parentNameArr":"[string] 所有上级名称【>号隔开】",
				"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
				"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
				"createTime":"[date] 创建时间",
				"childAddress_":" 子级地址",
				"name":"[string] 地点名称",
				"pathCode":"[string] 路径代码【暂定备用】"
			}
		],
		"superCode":"[long] 所属公司编号",
		"updateTime":"[date] 更新时间",
		"childCount":"[int] 子级地址数量",
		"sort":"[int] 排序序号",
		"parentId":"[long] 上级地点编号",
		"parentArr":"[string] 所有上级编号【逗号隔开】",
		"parentNameArr":"[string] 所有上级名称【>号隔开】",
		"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
		"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
		"createTime":"[date] 创建时间",
		"childAddress_":" 子级地址",
		"name":"[string] 地点名称",
		"pathCode":"[string] 路径代码【暂定备用】"
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【返回添加结果，true成功,false失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```

#### 请求示例
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8;"><span class="vjs-tree__brackets" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; cursor: pointer;">{</span></div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"status":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">"[int] 调用接口是否成功，0：失败，1：成功",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"errorCode":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">"[string] 错误码，当status=0时，必须返回错误码",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"message":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">"[string] 错误具体信息",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"normalisedPath":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">"[string] 标准化路径",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"expressionPath":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">"[string] 匹配的表达式路径",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"data_":&nbsp;</span><span class="vjs-value vjs-value__string" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; color: #13ce66;">" 返回信息的具体内容",</span></div>
</div>
</div>
<div class="vjs-tree__content has-line" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px 0px 0px 1em; border-width: 0px 0px 0px 1px; border-image: initial; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8; border-color: initial initial initial #bfcbd9; border-style: initial initial initial dotted;">
<div class="vjs-tree" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-key" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;">"data":</span>&nbsp;<span class="vjs-tree__brackets" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; cursor: pointer;">{</span></div>
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline;"><span class="vjs-tree__brackets" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; cursor: pointer;">}</span></div>
</div>
</div>
<div style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; font-size: 14px; vertical-align: baseline; color: #333333; font-family: Monaco, Menlo, Consolas, 'Bitstream Vera Sans Mono', monospace; background-color: #f7f7f8;"><span class="vjs-tree__brackets" style="box-sizing: border-box; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; border: 0px; vertical-align: baseline; cursor: pointer;">}</span></div>

#### 响应示例
<p>正常结果</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>异常结果</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
add()
#### 方法详细
```json
public com.dashu.repair.core.model.Address com.dashu.repair.core.web.AddressController.add(com.dashu.base.common.model.Token,com.dashu.repair.core.model.Address)
```


----


### 获取地址信息
#### 描述


<p><span style="background-color: #f1c40f;"><strong>dksjlvfladjlask</strong></span></p>

#### 请求地址
/mydemo/address/{id}
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
id | long | 地址编号 | 否 | form-data | 

#### 响应格式
```json
{
	"data":{
		"creatorId":"[long] 创建者编号",
		"typeName":"[string] ",
		"remark":"[string] 备注",
		"bgImage":"[string] 背景图片",
		"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
		"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
		"updaterId":"[long] 更新者编号",
		"id":"[long] ",
		"superId":"[long] 所属公司编号【所属公司编号】",
		"image":"[string] 图片",
		"address":"[string] 详细地址",
		"level":"[int] 层级【parentID为0的是第一级，以此递增】",
		"childAddress":[
			{
				"creatorId":"[long] 创建者编号",
				"typeName":"[string] ",
				"remark":"[string] 备注",
				"bgImage":"[string] 背景图片",
				"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
				"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
				"updaterId":"[long] 更新者编号",
				"id":"[long] ",
				"superId":"[long] 所属公司编号【所属公司编号】",
				"image":"[string] 图片",
				"address":"[string] 详细地址",
				"level":"[int] 层级【parentID为0的是第一级，以此递增】",
				"childAddress":[
					{}
				],
				"superCode":"[long] 所属公司编号",
				"updateTime":"[date] 更新时间",
				"childCount":"[int] 子级地址数量",
				"sort":"[int] 排序序号",
				"parentId":"[long] 上级地点编号",
				"parentArr":"[string] 所有上级编号【逗号隔开】",
				"parentNameArr":"[string] 所有上级名称【>号隔开】",
				"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
				"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
				"createTime":"[date] 创建时间",
				"childAddress_":" 子级地址",
				"name":"[string] 地点名称",
				"pathCode":"[string] 路径代码【暂定备用】"
			}
		],
		"superCode":"[long] 所属公司编号",
		"updateTime":"[date] 更新时间",
		"childCount":"[int] 子级地址数量",
		"sort":"[int] 排序序号",
		"parentId":"[long] 上级地点编号",
		"parentArr":"[string] 所有上级编号【逗号隔开】",
		"parentNameArr":"[string] 所有上级名称【>号隔开】",
		"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
		"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
		"createTime":"[date] 创建时间",
		"childAddress_":" 子级地址",
		"name":"[string] 地点名称",
		"pathCode":"[string] 路径代码【暂定备用】"
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
get()
#### 方法详细
```json
public com.dashu.repair.core.model.Address com.dashu.repair.core.web.AddressController.get(com.dashu.base.common.model.Token,java.lang.Long)
```


----


### 修改地址信息
#### 描述


#### 请求地址
/mydemo/address
#### 请求方式
PUT
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
id | long |  | 是 | form-data | 
name | string | 地点名称 | 否 | form-data | 
pathCode | string | 路径代码 | 是 | form-data | 暂定备用
superId | long | 所属公司编号 | 否 | form-data | 所属公司编号
parentId | long | 上级地点编号 | 否 | form-data | 
creatorId | long | 创建者编号 | 否 | form-data | 
createTime | date | 创建时间 | 否 | form-data | 
updaterId | long | 更新者编号 | 否 | form-data | 
updateTime | date | 更新时间 | 否 | form-data | 
address | string | 详细地址 | 否 | form-data | 
parentArr | string | 所有上级编号 | 否 | form-data | 逗号隔开
sort | integer | 排序序号 | 否 | form-data | 
image | string | 图片 | 否 | form-data | 
bgImage | string | 背景图片 | 否 | form-data | 
remark | string | 备注 | 否 | form-data | 
type | integer | 类型 | 否 | form-data | 地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的
typeName | string |  | 是 | form-data | 
level | integer | 层级 | 否 | form-data | parentID为0的是第一级，以此递增
childAddress | java.util.List<com.dashu.repair.core.model.Address> | 子级地址 | 否 | form-data | 
childArr | string | 所有下级编号 | 否 | form-data | 逗号隔开 第一个为本级
superCode | long | 所属公司编号 | 否 | form-data | 
childCount | integer | 子级地址数量 | 否 | form-data | 
parentNameArr | string | 所有上级名称 | 否 | form-data | >号隔开
parentName | string | 所有上级名称(不含自己) | 否 | form-data | >号隔开
parentArrFilter | long | 查询该节点所有子级 | 否 | form-data | 包含当前节点

#### 响应格式
```json
{
	"data":"[boolean] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【返回添加结果，true成功,false失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
update()
#### 方法详细
```json
public boolean com.dashu.repair.core.web.AddressController.update(com.dashu.base.common.model.Token,com.dashu.repair.core.model.Address)
```


----


### 删除地址
#### 描述


#### 请求地址
/mydemo/address
#### 请求方式
DELETE
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
ids | string | 地址编号 | 否 | form-data | 字符串，多个逗号隔开

#### 响应格式
```json
{
	"data":"[boolean] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【返回删除结果，true成功,false失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```


#### 响应示例
<p>222222</p>

#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
delete()
#### 方法详细
```json
public boolean com.dashu.repair.core.web.AddressController.delete(com.dashu.base.common.model.Token,java.lang.String)
```


----


### excel导出
#### 描述


#### 请求地址
/mydemo/address/exportExcel
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
fileName | string |  | 是 | form-data | 
id | long |  | 否 | form-data | 
name | string | 地点名称 | 否 | form-data | 
pathCode | string | 路径代码 | 是 | form-data | 暂定备用
superId | long | 所属公司编号 | 否 | form-data | 所属公司编号
parentId | long | 上级地点编号 | 否 | form-data | 
creatorId | long | 创建者编号 | 否 | form-data | 
createTime | date | 创建时间 | 否 | form-data | 
updaterId | long | 更新者编号 | 否 | form-data | 
updateTime | date | 更新时间 | 否 | form-data | 
address | string | 详细地址 | 否 | form-data | 
parentArr | string | 所有上级编号 | 否 | form-data | 逗号隔开
sort | integer | 排序序号 | 否 | form-data | 
image | string | 图片 | 否 | form-data | 
bgImage | string | 背景图片 | 否 | form-data | 
remark | string | 备注 | 否 | form-data | 
type | integer | 类型 | 否 | form-data | 地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的
typeName | string |  | 是 | form-data | 
level | integer | 层级 | 否 | form-data | parentID为0的是第一级，以此递增
childAddress | java.util.List<com.dashu.repair.core.model.Address> | 子级地址 | 否 | form-data | 
childArr | string | 所有下级编号 | 否 | form-data | 逗号隔开 第一个为本级
superCode | long | 所属公司编号 | 否 | form-data | 
childCount | integer | 子级地址数量 | 否 | form-data | 
parentNameArr | string | 所有上级名称 | 否 | form-data | >号隔开
parentName | string | 所有上级名称(不含自己) | 否 | form-data | >号隔开
parentArrFilter | long | 查询该节点所有子级 | 否 | form-data | 包含当前节点

#### 响应格式
```json
[void]请求无需返回数据
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
exportExcel()
#### 方法详细
```json
public void com.dashu.repair.core.web.AddressController.exportExcel(com.dashu.base.common.model.Token,java.lang.String,com.dashu.repair.core.model.Address,javax.servlet.http.HttpServletResponse) throws java.lang.Exception
```


----


### 查询地址列表
#### 描述


#### 请求地址
/mydemo/address
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
id | long |  | 否 | form-data | 
name | string | 地点名称 | 否 | form-data | 
pathCode | string | 路径代码 | 是 | form-data | 暂定备用
superId | long | 所属公司编号 | 否 | form-data | 所属公司编号
parentId | long | 上级地点编号 | 否 | form-data | 
creatorId | long | 创建者编号 | 否 | form-data | 
createTime | date | 创建时间 | 否 | form-data | 
updaterId | long | 更新者编号 | 否 | form-data | 
updateTime | date | 更新时间 | 否 | form-data | 
address | string | 详细地址 | 否 | form-data | 
parentArr | string | 所有上级编号 | 否 | form-data | 逗号隔开
sort | integer | 排序序号 | 否 | form-data | 
image | string | 图片 | 否 | form-data | 
bgImage | string | 背景图片 | 否 | form-data | 
remark | string | 备注 | 否 | form-data | 
type | integer | 类型 | 否 | form-data | 地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的
typeName | string |  | 是 | form-data | 
level | integer | 层级 | 否 | form-data | parentID为0的是第一级，以此递增
childAddress | java.util.List<com.dashu.repair.core.model.Address> | 子级地址 | 否 | form-data | 
childArr | string | 所有下级编号 | 否 | form-data | 逗号隔开 第一个为本级
superCode | long | 所属公司编号 | 否 | form-data | 
childCount | integer | 子级地址数量 | 否 | form-data | 
parentNameArr | string | 所有上级名称 | 否 | form-data | >号隔开
parentName | string | 所有上级名称(不含自己) | 否 | form-data | >号隔开
parentArrFilter | long | 查询该节点所有子级 | 否 | form-data | 包含当前节点
page | integer | 当前页数 | 否 | form-data | 
rows | integer | 每页数据量 | 否 | form-data | 
orderBy | string | 根据什么排序 | 否 | form-data | 
sord | string | 升序还是降序 | 否 | form-data | 升序：asc, 降序：desc

#### 响应格式
```json
{
	"data":{
		"total":"[long] 数据总数",
		"rows_":" 当前分页的具体数据",
		"rows":[
			{
				"creatorId":"[long] 创建者编号",
				"typeName":"[string] ",
				"remark":"[string] 备注",
				"bgImage":"[string] 背景图片",
				"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
				"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
				"updaterId":"[long] 更新者编号",
				"parentAddress":{},
				"id":"[long] ",
				"superId":"[long] 所属公司编号【所属公司编号】",
				"image":"[string] 图片",
				"address":"[string] 详细地址",
				"level":"[int] 层级【parentID为0的是第一级，以此递增】",
				"childAddress":[
					{}
				],
				"superCode":"[long] 所属公司编号",
				"updateTime":"[date] 更新时间",
				"childCount":"[int] 子级地址数量",
				"sort":"[int] 排序序号",
				"parentId":"[long] 上级地点编号",
				"parentArr":"[string] 所有上级编号【逗号隔开】",
				"parentNameArr":"[string] 所有上级名称【>号隔开】",
				"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
				"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
				"createTime":"[date] 创建时间",
				"childAddress_":" 子级地址",
				"name":"[string] 地点名称",
				"pathCode":"[string] 路径代码【暂定备用】"
			}
		]
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
select()
#### 方法详细
```json
public com.dashu.base.common.model.PageList<com.dashu.repair.core.model.AddressDetail> com.dashu.repair.core.web.AddressController.select(com.dashu.base.common.model.Token,com.dashu.repair.core.model.Address,com.dashu.base.common.model.PageParameter)
```


----


### excel导入
#### 描述


#### 请求地址
/mydemo/address/importExcel
#### 请求方式
POST
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
file | file | 文件 | 是 | form-data | 

#### 响应格式
```json
{
	"data":[
		{
			"creatorId":"[long] 创建者编号",
			"typeName":"[string] ",
			"remark":"[string] 备注",
			"bgImage":"[string] 背景图片",
			"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
			"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
			"updaterId":"[long] 更新者编号",
			"id":"[long] ",
			"superId":"[long] 所属公司编号【所属公司编号】",
			"image":"[string] 图片",
			"address":"[string] 详细地址",
			"level":"[int] 层级【parentID为0的是第一级，以此递增】",
			"childAddress":[
				{
					"creatorId":"[long] 创建者编号",
					"typeName":"[string] ",
					"remark":"[string] 备注",
					"bgImage":"[string] 背景图片",
					"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
					"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
					"updaterId":"[long] 更新者编号",
					"id":"[long] ",
					"superId":"[long] 所属公司编号【所属公司编号】",
					"image":"[string] 图片",
					"address":"[string] 详细地址",
					"level":"[int] 层级【parentID为0的是第一级，以此递增】",
					"childAddress":[
						{}
					],
					"superCode":"[long] 所属公司编号",
					"updateTime":"[date] 更新时间",
					"childCount":"[int] 子级地址数量",
					"sort":"[int] 排序序号",
					"parentId":"[long] 上级地点编号",
					"parentArr":"[string] 所有上级编号【逗号隔开】",
					"parentNameArr":"[string] 所有上级名称【>号隔开】",
					"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
					"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
					"createTime":"[date] 创建时间",
					"childAddress_":" 子级地址",
					"name":"[string] 地点名称",
					"pathCode":"[string] 路径代码【暂定备用】"
				}
			],
			"superCode":"[long] 所属公司编号",
			"updateTime":"[date] 更新时间",
			"childCount":"[int] 子级地址数量",
			"sort":"[int] 排序序号",
			"parentId":"[long] 上级地点编号",
			"parentArr":"[string] 所有上级编号【逗号隔开】",
			"parentNameArr":"[string] 所有上级名称【>号隔开】",
			"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
			"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
			"createTime":"[date] 创建时间",
			"childAddress_":" 子级地址",
			"name":"[string] 地点名称",
			"pathCode":"[string] 路径代码【暂定备用】"
		}
	],
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
importExcel()
#### 方法详细
```json
public java.util.List<com.dashu.repair.core.model.Address> com.dashu.repair.core.web.AddressController.importExcel(com.dashu.base.common.model.Token,org.springframework.web.multipart.MultipartFile) throws java.lang.Exception
```


----


### 查询所有子地址列表
#### 描述


#### 请求地址
/mydemo/address/tree
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
parentId | long |  | 否 | form-data | 
superCode | string |  | 否 | form-data | 
all | int | 是否查询所有子级数据，1是， 0否， 默认为0，只查当前子级数据 | 否 | form-data | 

#### 响应格式
```json
{
	"data":[
		{
			"creatorId":"[long] 创建者编号",
			"typeName":"[string] ",
			"remark":"[string] 备注",
			"bgImage":"[string] 背景图片",
			"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
			"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
			"updaterId":"[long] 更新者编号",
			"id":"[long] ",
			"superId":"[long] 所属公司编号【所属公司编号】",
			"image":"[string] 图片",
			"address":"[string] 详细地址",
			"level":"[int] 层级【parentID为0的是第一级，以此递增】",
			"childAddress":[
				{
					"creatorId":"[long] 创建者编号",
					"typeName":"[string] ",
					"remark":"[string] 备注",
					"bgImage":"[string] 背景图片",
					"type":"[int] 类型【地点所属类型比如1小区、2分区、3楼宇、4单元、5附属之类的】",
					"childArr":"[string] 所有下级编号【逗号隔开 第一个为本级】",
					"updaterId":"[long] 更新者编号",
					"id":"[long] ",
					"superId":"[long] 所属公司编号【所属公司编号】",
					"image":"[string] 图片",
					"address":"[string] 详细地址",
					"level":"[int] 层级【parentID为0的是第一级，以此递增】",
					"childAddress":[
						{}
					],
					"superCode":"[long] 所属公司编号",
					"updateTime":"[date] 更新时间",
					"childCount":"[int] 子级地址数量",
					"sort":"[int] 排序序号",
					"parentId":"[long] 上级地点编号",
					"parentArr":"[string] 所有上级编号【逗号隔开】",
					"parentNameArr":"[string] 所有上级名称【>号隔开】",
					"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
					"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
					"createTime":"[date] 创建时间",
					"childAddress_":" 子级地址",
					"name":"[string] 地点名称",
					"pathCode":"[string] 路径代码【暂定备用】"
				}
			],
			"superCode":"[long] 所属公司编号",
			"updateTime":"[date] 更新时间",
			"childCount":"[int] 子级地址数量",
			"sort":"[int] 排序序号",
			"parentId":"[long] 上级地点编号",
			"parentArr":"[string] 所有上级编号【逗号隔开】",
			"parentNameArr":"[string] 所有上级名称【>号隔开】",
			"parentName":"[string] 所有上级名称(不含自己)【>号隔开】",
			"parentArrFilter":"[long] 查询该节点所有子级【包含当前节点】",
			"createTime":"[date] 创建时间",
			"childAddress_":" 子级地址",
			"name":"[string] 地点名称",
			"pathCode":"[string] 路径代码【暂定备用】"
		}
	],
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
findAll4Tree()
#### 方法详细
```json
public java.util.List<com.dashu.repair.core.model.Address> com.dashu.repair.core.web.AddressController.findAll4Tree(com.dashu.base.common.model.Token,java.lang.Long,java.lang.String,int) throws java.lang.Exception
```


----


### excel模板下载
#### 描述


#### 请求地址
/mydemo/address/download
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
fileName | string |  | 是 | form-data | 
parentId | long |  | 否 | form-data | 

#### 响应格式
```json
[void]请求无需返回数据
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
download()
#### 方法详细
```json
public void com.dashu.repair.core.web.AddressController.download(com.dashu.base.common.model.Token,java.lang.String,java.lang.Long,javax.servlet.http.HttpServletResponse) throws java.lang.Exception
```


----


### 级联刷新地点详细地址
#### 描述


#### 请求地址
/mydemo/address/refresh
#### 请求方式
GET
#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
token | string | 身份令牌 | 是 | form-data | 
superCode | long | 所属公司编号 | 否 | form-data | 该参数只对超级管理员有效，普通用户传了也只认自己所属的公司编号

#### 响应格式
```json
{
	"data":"[boolean] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【返回结果，true成功,false失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"status":"[int] 调用接口是否成功，0：失败，1：成功",
	"errorMsg":"[string] 错误具体信息"
}
```



#### 类名
com.dashu.repair.core.web.AddressController
#### 方法名
refreshAddress()
#### 方法详细
```json
public boolean com.dashu.repair.core.web.AddressController.refreshAddress(com.dashu.base.common.model.Token,java.lang.Long)
```


----


### 获取监控设备总数
#### 描述


#### 请求地址
/mydemo/web/count/get_count_device
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---

#### 响应格式
```json
{
	"data":"[int] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【设备总数】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
com.wuanhj.web.service.CountService
#### 方法名
getCountDevice()
#### 方法详细
```json
public abstract java.lang.Integer com.wuanhj.web.service.CountService.getCountDevice()
```


----


### 获取已接入钢厂总数
#### 描述


#### 请求地址
/mydemo/web/count/get_count_company
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---

#### 响应格式
```json
{
	"data":"[int] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【已接入钢厂总数】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
com.wuanhj.web.service.CountService
#### 方法名
getCountCompany()
#### 方法详细
```json
public abstract java.lang.Integer com.wuanhj.web.service.CountService.getCountCompany()
```


----


### 获取监测数据
#### 描述


#### 请求地址
/mydemo/web/count/get_count_data
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---

#### 响应格式
```json
{
	"data":"[int] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【监测总数】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
com.wuanhj.web.service.CountService
#### 方法名
getCountData()
#### 方法详细
```json
public abstract java.lang.Integer com.wuanhj.web.service.CountService.getCountData()
```


----


### 获取城市节水节电除尘
#### 描述


#### 请求地址
/mydemo/web/count/get_count_economize_city
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
statisticsDate | date | 日期 | 否 | form-data | 

#### 响应格式
```json
{
	"data":{
		"economizeElectricity":"[float] 节电量",
		"economizeWater":"[float] 节水量",
		"dustElimination":"[float] 除尘量"
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
com.wuanhj.web.service.CountService
#### 方法名
getEconomizeCityDaily()
#### 方法详细
```json
public abstract com.wuanhj.web.dto.EconomizeCity com.wuanhj.web.service.CountService.getEconomizeCityDaily(java.util.Date)
```


----


### 删除api权限
#### 描述


#### 请求地址
/mydemo/api_permission/del
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
apiId | long | 接口编号 | 是 | form-data | 

#### 响应格式
```json
{
	"data":"[boolean] 【true=成功,false=失败】",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【true=成功,false=失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
delete()
#### 方法详细
```json
public abstract boolean org.basecode.web.sys.service.ApiPermissionService.delete(java.lang.Long)
```


----


### 获取api权限列表
#### 描述


#### 请求地址
/mydemo/api_permission/list
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
parentId | long | 父权限id | 否 | form-data | 

#### 响应格式
```json
{
	"data":{
		"total":"[long] 数据总数",
		"rows":[
			{
				"note":"[string] ",
				"code":"[string] api权限编码(请以\"api_\"开头设置编码)",
				"method":"[string] http请求方法【type=2必填】",
				"updateTime":"[date] ",
				"type":"[int] 权限类别【1=模块，2=方法】",
				"isChecked":"[boolean] ",
				"parentId":"[long] 父操作ID",
				"url":"[string] api请求地址【type=2必填】",
				"path":"[string] 路径",
				"updateId":"[long] ",
				"parentName":"[string] 父权限名称",
				"createTime":"[date] ",
				"createId":"[long] ",
				"name":"[string] api名称",
				"id":"[long] ",
				"status":"[boolean] 状态【1=启用，0=停用】"
			}
		],
		"rows_":" 当前分页的具体数据"
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
getList()
#### 方法详细
```json
public abstract org.basecode.common.criterion.model.PageList<org.basecode.web.sys.model.ApiPermission> org.basecode.web.sys.service.ApiPermissionService.getList(java.lang.String,java.lang.Long,org.basecode.common.criterion.model.PageParameter)
```


----


### 获取api权限树
#### 描述


#### 请求地址
/mydemo/api_permission/get_tree_data
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
roleId | long | 角色id | 否 | form-data | 可不传

#### 响应格式
```json
{
	"data":[
		{
			"children_":" 子节点列表",
			"code":"[string] 权限编码",
			"method":"[string] http请求方法",
			"children":[
				{}
			],
			"context":"[string] 上下文",
			"checked":"[boolean] 节点是否被选中【true=选中,false=未选中】",
			"id":"[long] id",
			"label":"[string] 节点名称",
			"type":"[int] 权限类别【1=模块，2=方法】",
			"parentId":"[long] 父节点id",
			"url":"[string] 访问地址"
		}
	],
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
getTreeData()
#### 方法详细
```json
public abstract java.util.List<org.basecode.web.sys.dto.ApiPermissionTree> org.basecode.web.sys.service.ApiPermissionService.getTreeData(java.lang.Long)
```


----


### 保存或修改
#### 描述


#### 请求地址
/mydemo/api_permission/save_or_update
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
appId | string |  | 是 | form-data | 
id | long |  | 否 | form-data | 
parentId | long | 父操作ID | 否 | form-data | 
name | string | api名称 | 否 | form-data | 
code | string | api权限编码(请以"api_"开头设置编码) | 否 | form-data | 
url | string | api请求地址 | 否 | form-data | type=2必填
method | string | http请求方法 | 否 | form-data | type=2必填
type | integer | 权限类别【1=模块，2=方法】 | 否 | form-data | 
path | string | 路径 | 否 | form-data | 
context | string | 模块上下文 | 否 | form-data | type=1必填
status | boolean | 状态【1=启用，0=停用】 | 否 | form-data | 
createId | long |  | 否 | form-data | 
updateId | long |  | 否 | form-data | 
updateTime | date |  | 否 | form-data | 
note | string |  | 否 | form-data | 
parentName | string | 父权限名称 | 否 | form-data | 
isChecked | boolean |  | 否 | form-data | 

#### 响应格式
```json
{
	"data":"[boolean] 【true=成功,false=失败】",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【true=成功,false=失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
saveOrUpdate2()
#### 方法详细
```json
public abstract boolean org.basecode.web.sys.service.ApiPermissionService.saveOrUpdate2(java.lang.String,org.basecode.web.sys.model.ApiPermission)
```


----


### 修改api权限状态
#### 描述


#### 请求地址
/mydemo/api_permission/update/status
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
apiId | long | api权限id | 否 | form-data | 
status | int | 状态值 | 否 | form-data | 已改变的状态值,0=禁用，1=正常

#### 响应格式
```json
{
	"data":"[boolean] 【true=成功,false=失败】",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容【true=成功,false=失败】",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
updateApiStatus()
#### 方法详细
```json
public abstract boolean org.basecode.web.sys.service.ApiPermissionService.updateApiStatus(java.lang.Long,int)
```


----


### updateSelective
#### 描述


#### 请求地址
/mydemo/api_permission/
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---

#### 响应格式
```json
{
	"data":"[int] ",
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
updateSelective()
#### 方法详细
```json
public abstract int org.basecode.common.generic.service.GenericService.updateSelective(java.util.Map<java.lang.String, java.lang.Object>)
```


----


### 根据api权限id获取详情
#### 描述


#### 请求地址
/mydemo/api_permission/get_api_by_id
#### 请求方式

#### 请求参数
参数 | 类型 | 名称 | 必传 | 位置 | 备注
---|--- |---| --- | --- |---
apiId | long |  | 否 | form-data | 

#### 响应格式
```json
{
	"data":{
		"note":"[string] ",
		"code":"[string] api权限编码(请以\"api_\"开头设置编码)",
		"method":"[string] http请求方法【type=2必填】",
		"updateTime":"[date] ",
		"type":"[int] 权限类别【1=模块，2=方法】",
		"isChecked":"[boolean] ",
		"parentId":"[long] 父操作ID",
		"url":"[string] api请求地址【type=2必填】",
		"path":"[string] 路径",
		"updateId":"[long] ",
		"parentName":"[string] 父权限名称",
		"createTime":"[date] ",
		"createId":"[long] ",
		"name":"[string] api名称",
		"context":"[string] 模块上下文【type=1必填】",
		"id":"[long] ",
		"status":"[boolean] 状态【1=启用，0=停用】"
	},
	"normalisedPath":"[string] 标准化路径",
	"errorCode":"[string] 错误码，当status=0时，必须返回错误码",
	"data_":" 返回信息的具体内容",
	"expressionPath":"[string] 匹配的表达式路径",
	"message":"[string] 错误具体信息",
	"status":"[int] 调用接口是否成功，0：失败，1：成功"
}
```



#### 类名
org.basecode.web.sys.service.ApiPermissionService
#### 方法名
getApiById()
#### 方法详细
```json
public abstract org.basecode.web.sys.model.ApiPermission org.basecode.web.sys.service.ApiPermissionService.getApiById(java.lang.Long)
```


----

### 111111111222
<p>11111111122222333333edddd</p>
<pre class="language-css"><code>&lt;style lang="scss" type="text/scss"&gt;

    .base-table-component .el-table {
        border-left: none;

        th {
            padding: 3px 0;
        }

        td {
            padding: 8px 0;
        }

        tr {
            th:nth-last-child(2),
            td:last-child {
                border-right: none;
            }
        }

        &amp;:before, &amp;:after {
            display: none;
        }
    }
&lt;/style&gt;
</code></pre>


----

### ddd
<p>aaaddd2222222222223333</p>


----

### 测试接口
<p>我是一些测试的东西</p>
<pre class="language-java"><code>	/**
	 * 获取变更行程列表
	 * 
	 * @param params
	 * @return
	 */
	public List&lt;Record&gt; getChangeJourneyList(Map&lt;String, Object&gt; params) {

		return dao.createNamedQuery("usher.journey.change.list").params(params).list();
	}</code></pre>


----
