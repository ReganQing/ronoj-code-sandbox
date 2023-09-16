package com.ron.ronojcodesandbox.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.ron.ronojcodesandbox.JavaNativeCodeSandBox;
import com.ron.ronojcodesandbox.model.ExecuteCodeRequest;
import com.ron.ronojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ron_567
 */
@RestController("/")
public class MainController {
    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "secretKey";
    @Resource
    private JavaNativeCodeSandBox javaNativeCodeSandBox;

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    /**
     * 执行代码
     * @param executeCodeRequest 执行代码请求
     * @return 返回执行响应结果
     */
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody  ExecuteCodeRequest executeCodeRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        // 基本的认证
        String authHeader = httpServletRequest.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            httpServletResponse.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return javaNativeCodeSandBox.executeCode(executeCodeRequest);
    }
}
