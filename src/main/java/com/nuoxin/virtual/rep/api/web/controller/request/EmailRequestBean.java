package com.nuoxin.virtual.rep.api.web.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by fenggang on 10/12/17.
 */
@ApiModel
public class EmailRequestBean implements Serializable {
    private static final long serialVersionUID = 477753468333711704L;

    @ApiModelProperty(value = "邮件发送医生的id（多个医生中间用英文逗号分隔）")
    private String doctorIds;
    @ApiModelProperty(value = "邮件标题")
    private String title;
    @ApiModelProperty(value = "邮件内容")
    private String content;
    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "不用传")
    private Long drugUserId;

    public String getDoctorIds() {
        return doctorIds;
    }

    public void setDoctorIds(String doctorIds) {
        this.doctorIds = doctorIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDrugUserId() {
        return drugUserId;
    }

    public void setDrugUserId(Long drugUserId) {
        this.drugUserId = drugUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
