package com.ron.ronojcodesandbox;

import com.ron.ronojcodesandbox.model.ExecuteCodeRequest;
import com.ron.ronojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;


/**
 * @author Ron_567
 */
@Component
public class JavaNativeCodeSandBox extends JavaCodeSandboxTemplate {

    /**
     * Java代码原生沙箱实现
     * @param executeCodeRequest 请求执行代码
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
