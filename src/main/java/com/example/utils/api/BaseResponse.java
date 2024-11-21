package com.example.utils.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version V1.0
 * @description: 微服务接口统一返回码
 * @author: 97后程序员 余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
@Data
@ApiModel(value = "BaseResponse", description = "返回base数据")
public class BaseResponse<T> {

   /**
    * 返回码
    */
   @ApiModelProperty("状态码")
   private Integer code;
   /**
    * 消息
    */
   @ApiModelProperty("返回msg")
   private String msg;
   /**
    * 返回
    */
   @ApiModelProperty("返回data数据")
   private T data;
   // 分页

   public BaseResponse() {

   }

   public BaseResponse(Integer code, String msg, T data) {
      super();
      this.code = code;
      this.msg = msg;
      this.data = data;
   }

}