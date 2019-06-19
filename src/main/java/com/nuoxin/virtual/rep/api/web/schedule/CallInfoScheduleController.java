package com.nuoxin.virtual.rep.api.web.schedule;

import com.nuoxin.virtual.rep.api.common.bean.DefaultResponseBean;
import com.nuoxin.virtual.rep.api.service.CallBackService;
import com.nuoxin.virtual.rep.api.utils.SpeechRecognitionUtil;
import com.nuoxin.virtual.rep.api.web.controller.request.call.Call7mmorRequestBean;
import com.nuoxin.virtual.rep.api.web.controller.request.call.IdentifyCallUrlRequestBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * V2.5电话记录补偿,手动调用
 * @author tiancun
 * @date 2018-09-30
 */
@RestController
@Api(value = "V2.5电话记录补偿手动调用")
@RequestMapping(value = "/call/info")
public class CallInfoScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(CallInfoScheduleController.class);

    @Resource
    private CallBackService callBackService;

    @ApiOperation(value = "没有回调的电话记录重试", notes = "没有回调的电话记录重试")
    @PostMapping(value = "/retry")
    public DefaultResponseBean<String> repeatSaveOrUpdateCall(@RequestBody Call7mmorRequestBean bean) {
        logger.info("CallInfoScheduleController repeatSaveOrUpdateCall start....");
        long starTime = System.currentTimeMillis();
        callBackService.repeatSaveOrUpdateCall(bean);
        long endTime = System.currentTimeMillis();
        logger.info("CallInfoScheduleController repeatSaveOrUpdateCall end , cost {}s", (endTime-starTime)/1000);

        DefaultResponseBean<String> responseBean = new DefaultResponseBean<>();
        responseBean.setData("success");
        return responseBean;
    }



    @ApiOperation(value = "没有回调的电话记录重试,不包含WAV文件", notes = "没有回调的电话记录重试,不包含WAV文件")
    @PostMapping(value = "/retry/no/wav")
    public DefaultResponseBean<String> repeatSaveOrUpdateCallNoWav(@RequestBody Call7mmorRequestBean bean) {
        logger.info("CallInfoScheduleController repeatSaveOrUpdateCallNoWav start....");
        long starTime = System.currentTimeMillis();
        callBackService.repeatSaveOrUpdateCall(bean);
        long endTime = System.currentTimeMillis();
        logger.info("CallInfoScheduleController repeatSaveOrUpdateCallNoWav end , cost {}s", (endTime-starTime)/1000);

        DefaultResponseBean<String> responseBean = new DefaultResponseBean<>();
        responseBean.setData("success");
        return responseBean;
    }



    @ApiOperation(value = "识别录音文件", notes = "识别录音文件")
    @PostMapping(value = "/url/identify")
    public DefaultResponseBean<String> identifyCallUrl(@RequestBody IdentifyCallUrlRequestBean bean) {
        logger.info("CallInfoScheduleController identifyCallUrl start....");
        long starTime = System.currentTimeMillis();
        Integer limitNum = bean.getLimitNum();
        if (limitNum == null){
            // 如果没有就设置300
            bean.setLimitNum(300);
        }

        callBackService.identifyCallUrl(bean);
        long endTime = System.currentTimeMillis();
        logger.info("CallInfoScheduleController identifyCallUrl end , cost {}s", (endTime-starTime)/1000);

        DefaultResponseBean<String> responseBean = new DefaultResponseBean<>();
        responseBean.setData("success");
        return responseBean;
    }


    @ApiOperation(value = "将非阿里云录音上传到阿里云", notes = "将非阿里云录音上传到阿里云")
    @GetMapping(value = "/not/aliyun/url/update")
    public DefaultResponseBean<String> handleNotAliyunCallUrl() {
        logger.info("CallInfoScheduleController handleNotAliyunCallUrl start....");
        long starTime = System.currentTimeMillis();
        callBackService.handleNotAliyunCallUrl();
        long endTime = System.currentTimeMillis();
        logger.info("CallInfoScheduleController handleNotAliyunCallUrl end , cost {}s", (endTime-starTime)/1000);

        DefaultResponseBean<String> responseBean = new DefaultResponseBean<>();
        responseBean.setData("success");
        return responseBean;
    }

    /**
     * 手动分割本地录音文件并上传阿里云
     *  1.下载阿里云上的电话录音到本地
     * 	2.之后进行MP3转WAV
     * 	3.进行左右声道分割
     * 	4.分别进行语音识别转成文本
     * 	5.入库
     */
    @ApiOperation(value = "手动分割本地录音文件并上传阿里云", notes = "手动分割本地录音文件并上传阿里云")
    @GetMapping(value = "/split/speech/aliyun/url/update")
    public DefaultResponseBean<String> splitSpeechAliyunUrlUpdate(@RequestParam(value = "ossFilePath") String ossFilePath,@RequestParam(value = "sinToken") String sinToken) {
        String aliUrl = callBackService.getFileOSSPathByLocalFilePath(ossFilePath);
        Map<String,String> pathMap = callBackService.splitSpeechAliyunUrlUpdate(aliUrl);

        Integer callId = 1;

//        Map<String,String> pathMaps = new HashMap<String,String>(16);
//        pathMaps.put("leftOSSPath","https://nuoxin-virtual-rep-storage.oss-cn-beijing.aliyuncs.com/virtual/2019051616/af668ce5-1e0c-40b3-ace6-57bec0681f37_left.wav");
//        pathMaps.put("rightOSSPath","https://nuoxin-virtual-rep-storage.oss-cn-beijing.aliyuncs.com/virtual/2019051616/af668ce5-1e0c-40b3-ace6-57bec0681f37_right.wav");

        //根据左右声道的阿里云地址进行语音识别，进行入库
//        boolean result = callBackService.saveSpeechRecognitionResultCallInfo(pathMap);
        boolean result = callBackService.saveSpeechRecognitionResultCallInfo(pathMap, sinToken);
        String resultStr = "";
        if(result == true){
            resultStr = "分割录音文件并上传阿里云入库成功";
        }

        DefaultResponseBean<String> responseBean = new DefaultResponseBean<>();
        responseBean.setData(resultStr);
        return responseBean;
    }

    /**
     *  手动刷新电话录音分割-语音转文字
     */
    @ApiOperation(value = "手动刷新电话录音分割-语音转文字", notes = "手动刷新电话录音分割-语音转文字")
    @GetMapping(value = "/manual/refresh/recording/segmentation")
    public DefaultResponseBean<Integer> manualRefreshRecordingSegmentation() {
        Integer resultNum = callBackService.manualRefreshRecordingSegmentation();
        DefaultResponseBean<Integer> responseBean = new DefaultResponseBean<>();
        responseBean.setData(resultNum);
        return responseBean;
    }

    /**
     * 根据url和token手动刷新电话录音分割-语音转文字
     * @return Integer
     */
    @ApiOperation(value = "根据url和token手动刷新电话录音分割-语音转文字", notes = "根据url和token手动刷新电话录音分割-语音转文字")
    @GetMapping(value = "/save/refresh/by/url/and/token")
    public DefaultResponseBean<Integer> saveRecordingByUrlAndToken(@RequestParam(value = "url") String url, @RequestParam(value = "token") String token){
        Integer resultNum = callBackService.saveRecordingByUrlAndToken(url,token);
        DefaultResponseBean<Integer> responseBean = new DefaultResponseBean<>();
        responseBean.setData(resultNum);
        return responseBean;
    }
}
