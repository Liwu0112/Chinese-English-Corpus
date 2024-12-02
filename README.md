# 中英文语料库系统

## 项目背景

本课题旨在设计与开发一个基于Web的中英文语料库系统，以支持语言学研究、翻译实践和自然语言处理技术的发展。该系统将实现中英文语料的高效收集、存储、管理和分析，提供用户友好的界面和强大的查询功能。课题的核心研究内容包括系统架构设计、语料采集与处理、以及多维度语料分析工具的开发。通过该系统，研究人员能够便捷地访问和分析大规模双语文本数据，从而深化对语言特性和交流模式的理解，推动语言技术和人工智能领域的研究与应用。该课题不仅具有学术研究价值，也对促进跨文化交流和国际合作具有重要意义。

## 项目架构

### 整体架构描述

该系统采用前后端分离的架构模式，前端使用 Vue.js 框架负责用户界面的展示和交互逻辑，后端基于 Spring Boot 框架处理业务逻辑、数据存储以及与前端的接口通信。

### 主要模块介绍

主要分为三个模块，管理者模块，普通用户模块，公共模块；其中管理者模块主要功能是对语料的增删改查，以及修改个人信息，管理用户；普通用户模块主要功能是语料的中英文互查，分类查找，用户注册；公共模块主要功能用户的登录，退出

## 数据库设计

### 用户表：t_user

| 字段CH       | 字段EN            | 数据类型  | 是否唯一 | 是否主键 | 是否外键 | 描述                                                         |
| ------------ | ----------------- | --------- | -------- | -------- | -------- | ------------------------------------------------------------ |
| 用户标识     | user_id           | int       | 是       | 是       | 否       | 自增长整数类型，作为主键                                     |
| 用户名       | username          | varchar   | 是       | 否       | 否       | 最大长度为50，不能为空，用户登录时使用，具有唯一性           |
| 用户密码     | password          | varchar   | 否       | 否       | 否       | 使用加密算法加密，不能为空，用户登录时使用                   |
| 用户注册日期 | registration_date | Timestamp | 否       | 否       | 否       | 默认当前日期，记录用户首次注册时间                           |
| 用户角色     | role              | Enum      | 否       | 否       | 否       | 枚举类型，包含admin（管理员），regular_user（普通用户），不同用户具有不同权限 |

### 语料表：t_corpus

| 字段CH       | 字段EN        | 数据类型  | 是否唯一 | 是否主键 | 是否外键 | 描述                                       |
| ------------ | ------------- | --------- | -------- | -------- | -------- | ------------------------------------------ |
| 语料标识     | corpus_id     | int       | 是       | 是       | 否       | 自增长整数类型，作为主键                   |
| 中文文本     | chinese_text  | text      | 否       | 否       | 否       | 中文文本内容                               |
| 英文文本     | english_text  | text      | 否       | 否       | 否       | 英文文本内容                               |
| 领域标识     | kind_id       | int       | 否       | 否       | 是       | 语料所属领域标识                           |
| 种类标识     | type_id       | int       | 否       | 否       | 是       | 语料所属种类标识，通过该值查询种类名称     |
| 语料状态     | corpus_status | tinyint   | 否       | 否       | 否       | 通过该值判断语料是否状态，0为下线，1为上线 |
| 语料创建者   | creator       | varchar   | 否       | 否       | 否       | 语料创建者                                 |
| 语料创建时间 | creation_time | Timestamp | 否       | 否       | 否       | 语料创建时间                               |

### 分类表：t_type

| 字段CH   | 字段EN    | 数据类型 | 是否唯一 | 是否主键 | 是否外键 | 描述                         |
| -------- | --------- | -------- | -------- | -------- | -------- | ---------------------------- |
| 种类标识 | type_id   | int      | 是       | 是       | 否       | 自增长整型数据类型，作为主键 |
| 领域标识 | kind_id   | int      | 否       | 否       | 是       | 种类所属领域                 |
| 种类名称 | type_name | varchar  | 是       | 否       | 否       | 种类名称，例如公共交通       |

### 种类表：t_kind

| 字段CH   | 字段EN    | 数据类型 | 是否唯一 | 是否主键 | 是否外键 | 描述           |
| -------- | --------- | -------- | -------- | -------- | -------- | -------------- |
| 领域标识 | kind_id   | int      | 是       | 是       | 否       | 领域标识，自增 |
| 领域名称 | kind_name | varchar  | 是       | 否       | 否       | 领域名称       |

## 接口说明

### 公共功能控制类

#### 用户登录

请求方式：POST

请求地址：/common/login

请求参数：

```json
{
    "userName":"admin",
    "passWord":"123456"
}
```

响应成功返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": {
        "userId": 1,
        "username": "admin",
        "password": "E10ADC3949BA59ABBE56E057F20F883E",
        "registrationDate": "2024-11-22T04:31:16.000+00:00",
        "role": "admin"
    }
}
```

响应失败返回参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

#### 用户退出

请求方式：POST

请求地址：/common/logout

响应成功返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": null
}
```

响应失败参数：响应失败返回状态码为401

#### 修改用户名

请求方式：POST

请求地址：/common/updateusername

请求参数：

```json
{
    "userName":"user1",
    "userNewName":"cjs"
}
```

响应成功返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": null
}
```

响应失败参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

#### 修改密码

请求方式：POST

请求地址：/common/updatepassword

请求参数：

```json
{
    "userName": "lys",
    "userNewPassword": "12345678"
}
```

响应成功返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": null
}
```

响应失败参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

### 普通用户控制类

#### 普通用户注册

请求方式：POST

请求地址：/regularuser/enroll

请求参数：

```json
{
    "userName":"user1",
    "passWord":"123456"
}
```

响应成功返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": null
}
```

响应失败返回参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

#### 普通用户中英字段查询语料

请求方式：POST

请求地址：/regularuser/translationbytext

请求参数：/regularuser/translationbytext?text=

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "chineseText": "保安室；门卫室;值班岗",
            "englishText": "Security 或Security Booth/Guard/Office/Room",
            "kindName": "通则",
            "typeName": "安全保卫、消防类"
        }
    ]
}
```

响应失败参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

#### 查看所有种类名

请求方式：POST

请求地址：/regularuser/selectallkindname

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "kindName": "通则"
        },
        {
            "kindName": "交通"
        },
        {
            "kindName": "旅游"
        },
        {
            "kindName": "文化娱乐"
        },
        {
            "kindName": "体育"
        },
        {
            "kindName": "教育"
        },
        {
            "kindName": "医疗卫生"
        },
        {
            "kindName": "邮政电信"
        },
        {
            "kindName": "餐饮住宿"
        },
        {
            "kindName": "商业金融"
        }
    ]
}
```

#### 通过种类名查看种类下所有分类

请求方式：POST

请求地址：/regularuser/selecttnbykn

请求参数：/regularuser/selecttnbykn?kindName=

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "typeName": "安全保卫、消防类"
        }
    ]
}
```

#### 查看分类下的所有语料

请求方式：POST

请求地址：/regularuser/selecttypecorpus

请求参数：

```json
{
    "kindName":"通则",
    "typeName":"安全保卫、消防类"
}
```

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "chineseText": "保安室；门卫室;值班岗",
            "englishText": "Security 或Security Booth/Guard/Office/Room",
            "kindName": "通则",
            "typeName": "安全保卫、消防类"
        },
        {
            "chineseText": "110",
            "englishText": "110",
            "kindName": "通则",
            "typeName": "安全保卫、消防类"
        }
    ]
}
```

响应失败参数：

```json
{
    "code": 500,
    "msg": "fail",
    "data": null
}
```

#### 查看库中所有状态为上线（corpus_status=1)的语料总数

请求方式：GET

请求地址：/regularuser/reselectcountcorpus

返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 14
}
```

#### 查看种类总数

请求方式：GET

请求地址：/regularuser/reselectcountkind

返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 10
}
```

#### 查看分类总数

请求方式：GET

请求地址：/regularuser/reselectcounttype

返回参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 2
}
```

#### 查看库中种类所属的语料总数且状态为上线(corpus_status=1)

请求方式：GET

请求地址：/regularuser/rescorpusbykindname

请求参数：/regularuser/rescorpusbykindname?kindName=

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 13
}
```

### 管理员控制类

#### 查询语料总数

请求方式：GET

请求地址：/admin/selectallcors

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 14
}
```

#### 查询语料总数

请求方式：GET

请求地址：/regularuser/rescorpusbykindname

请求参数：/regularuser/rescorpusbykindname?kindName=

响应成功参数：

#### 查看种类总数

请求方式：GET

请求地址：/admin/selectallkind

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 10
}
```

#### 查询语料总数

请求方式：GET

请求地址：/admin/selectallkind

响应成功参数：

#### 查询语料总数

请求方式：GET

请求地址：/regularuser/rescorpusbykindname

请求参数：/regularuser/rescorpusbykindname?kindName=

响应成功参数：

#### 查看分类总数

请求方式：GET

请求地址：/admin/selectalltype

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 11
}
```

#### 查看种类下语料数

请求方式：GET

请求地址：/admin/selectKindcors

请求参数：admin/selectKindcors?kindName=

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 13
}
```

#### 按种类名查询语料上线数

请求方式：GET

请求地址：/admin/selectonlinebykindname

请求参数：/admin/selectonlinebykindname?kindName=

响应成功参数：

```
{
    "code": 200,
    "msg": "ok",
    "data": 12
}
```

#### 按种类名查询语料下线数

请求方式：GET

请求地址：/admin/selectofflinebykindname

请求参数：/selectofflinebykindname?kindName=

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 1
}
```

#### 查看普通用户数量

请求方式：GET

请求地址：/admin/selectreusercount

响应成功参数：

```json
{
    "code": 200,
    "msg": "ok",
    "data": 2
}
```

