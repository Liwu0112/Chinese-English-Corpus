# 中英文语料库系统

## 项目背景

**本课题旨在设计与开发一个基于Web的中英文语料库系统，以支持语言学研究、翻译实践和自然语言处理技术的发展。该系统将实现中英文语料的高效收集、存储、管理和分析，提供用户友好的界面和强大的查询功能。课题的核心研究内容包括系统架构设计、语料采集与处理、以及多维度语料分析工具的开发。通过该系统，研究人员能够便捷地访问和分析大规模双语文本数据，从而深化对语言特性和交流模式的理解，推动语言技术和人工智能领域的研究与应用。该课题不仅具有学术研究价值，也对促进跨文化交流和国际合作具有重要意义。**

## 需求分析

**公共功能模块：登录，退出，登录时需进行鉴权**

**普通用户模块：使用中英互译进行语料查询，按照种类对语料进行查询**，**用户注册**

**管理用户模块：增加语料，删除语料，修改语料，查询语料，管理用户**

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
| 种类标识     | type_id       | int       | 否       | 否       | 是       | 语料种类标识，通过该值查询种类名称         |
| 语料状态     | corpus_status | tinyint   | 否       | 否       | 否       | 通过该值判断语料是否状态，0为下线，1为上线 |
| 语料创建者   | creator       | varchar   | 否       | 否       | 否       | 语料创建者                                 |
| 语料创建时间 | creation_time | Timestamp | 否       | 否       | 否       | 语料创建时间                               |

### 种类表：t_type

| 字段CH   | 字段EN    | 数据类型 | 是否唯一 | 是否主键 | 是否外键 | 描述                         |
| -------- | --------- | -------- | -------- | -------- | -------- | ---------------------------- |
| 种类标识 | type_id   | int      | 是       | 是       | 否       | 自增长整型数据类型，作为主键 |
| 种类名称 | type_name | varchar  | 是       | 否       | 否       | 种类名称，例如公共交通       |

## 接口文档

### 公共功能控制类

#### 用户登录接口

接口描述：该接口的作用为实现管理员和普通用户的登录功能

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

### 普通用户控制类

#### 普通用户注册接口

接口描述：该接口的作用为实现管理员和普通用户的登录功能

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

### 管理员控制类

## 开发文档

### 公共功能模块

### 普通用户模块

### 管理用户模块
