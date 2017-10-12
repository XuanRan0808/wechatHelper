package com.nuoxin.virtual.rep.api.web.controller;

import com.alibaba.fastjson.JSON;
import com.nuoxin.virtual.rep.api.common.bean.DefaultResponseBean;
import com.nuoxin.virtual.rep.api.common.controller.BaseController;
import com.nuoxin.virtual.rep.api.web.controller.request.analysis.TargetAnalysisRequestBean;
import com.nuoxin.virtual.rep.api.web.controller.response.analysis.ta.MettingTargetResponseBean;
import com.nuoxin.virtual.rep.api.web.controller.response.analysis.ta.TargetResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by fenggang on 10/11/17.
 */
@Api(value = "目标分析", description = "目标分析接口")
@RestController
@RequestMapping(value = "/target/analysis")
public class TargetAnalysisController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "汇总统计接口", notes = "汇总统计接口")
    @PostMapping("/summation")
    public DefaultResponseBean<TargetResponseBean> summation(@RequestBody TargetAnalysisRequestBean bean,
                                                  HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<TargetResponseBean> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-电话人数", notes = "客户统计接口")
    @PostMapping("/client/tel/person")
    public DefaultResponseBean<List<TargetResponseBean>> client_tel_person(@RequestBody TargetAnalysisRequestBean bean,
                                                                    HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-电话次数", notes = "客户统计接口")
    @PostMapping("/client/tel/count")
    public DefaultResponseBean<List<TargetResponseBean>> client_tel_count(@RequestBody TargetAnalysisRequestBean bean,
                                                                HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-短信人数", notes = "客户统计接口")
    @PostMapping("/client/sms/person")
    public DefaultResponseBean<List<TargetResponseBean>> client_sms(@RequestBody TargetAnalysisRequestBean bean,
                                                                HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-微信人数", notes = "客户统计接口")
    @PostMapping("/client/wechat/person")
    public DefaultResponseBean<List<TargetResponseBean>> client_wechat(@RequestBody TargetAnalysisRequestBean bean,
                                                                HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-电话总时长", notes = "客户统计接口")
    @PostMapping("/client/tel/sum")
    public DefaultResponseBean<List<TargetResponseBean>> client_tel_sum(@RequestBody TargetAnalysisRequestBean bean,
                                                                HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "客户统计接口-电话平均时长", notes = "客户统计接口")
    @PostMapping("/client/tel/avg")
    public DefaultResponseBean<List<TargetResponseBean>> client_tel_avg(@RequestBody TargetAnalysisRequestBean bean,
                                                                HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<List<TargetResponseBean>> responseBean = new DefaultResponseBean();

        return responseBean;
    }

    @ApiOperation(value = "会议统计接口", notes = "会议统计接口")
    @PostMapping("/meeting")
    public DefaultResponseBean<MettingTargetResponseBean> meeting(@RequestBody TargetAnalysisRequestBean bean,
                                               HttpServletRequest request, HttpServletResponse response) {
        logger.info("{}-接口的请求参数【{}】",request.getServletPath(), JSON.toJSONString(bean));
        DefaultResponseBean<MettingTargetResponseBean> responseBean = new DefaultResponseBean();

        return responseBean;
    }

}
