package com.zjh.myblog.controller;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.config.RedisCacheService;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.utlis.IdUtils;
import com.zjh.myblog.utlis.VerifyCodeUtils;
import com.zjh.myblog.utlis.sign.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**验证码
 * @auther zhengjianghai
 * @create 2022-01-21 15:50
 */
@Slf4j
@RestController
public class CaptchaController {

    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        //生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtils.simpleUUID();
        String key = Constants.CAPTCHA_CODE_KEY + uuid;
        redisCacheService.setCacheObject(key,verifyCode,Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111;
        int h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w,h,stream,verifyCode);
        try {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        } finally {
            stream.close();
        }
    }

}
