package cn.edu.bjfu.igarden.config;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        logger.error(ExceptionUtils.getFullStackTrace(e));  // 记录错误信息
        JSONObject jsonObject = new JSONObject();
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "无信息";
            int code=400;
            jsonObject.put("code",code);
            jsonObject.put("error", msg);
        }
        jsonObject.put("error", msg);
        return jsonObject;
    }
}
