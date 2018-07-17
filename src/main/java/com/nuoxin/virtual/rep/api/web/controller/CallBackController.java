package com.nuoxin.virtual.rep.api.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nuoxin.virtual.rep.api.common.bean.ResponseObj;
import com.nuoxin.virtual.rep.api.common.controller.BaseController;
import com.nuoxin.virtual.rep.api.service.CallBackService;
import com.nuoxin.virtual.rep.api.utils.CollectionsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "电话回调 Controller 类", description = "电话回调 Controller 类")
@RestController
@RequestMapping(value = "/callback")
public class CallBackController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CallBackController.class);

    @Resource
    private CallBackService callBackService;
    
    private ExecutorService executorService = Executors.newFixedThreadPool(50);

    /**
     * 七陌回调入口方法
     * 参考链接 https://developer.7moor.com/event/
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "回调接口方法", notes = "回调接口方法")
    @RequestMapping("/7moor")
    public ResponseObj callback(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> paramsMap = this.getParamsMap(request);
    	
		ResponseObj responseObj = new ResponseObj();
		responseObj.setData(paramsMap);
		logger.warn("7moor request params:{}", JSONObject.toJSONString(paramsMap));
		
		if (CollectionsUtil.isNotEmptyMap(paramsMap)) {
			executorService.execute(() -> {
				try {
					callBackService.callBack(paramsMap);
				} catch (Exception e) {
					logger.error("7moor 信息异步文件处理及入库失败:{}", JSONObject.toJSONString(paramsMap), e);
					// 后期追加补偿机制代码 TODO
				}
			});
		} else {
			logger.error("7moor request params is blank!");
		}
		
    	return responseObj;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Map<String, String> getParamsMap (HttpServletRequest request) {
    	Map<String, String> paramsMap = new HashMap<>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String parameter = paramNames.nextElement();
			String[] parameterValues = request.getParameterValues(parameter);
			if (parameterValues.length == 1) {
				String parameterValue = parameterValues[0];
				paramsMap.put(parameter, parameterValue);
				logger.info("{}={}", parameter, parameterValue);
			}
		}
		
		return paramsMap;
		
    }

}
