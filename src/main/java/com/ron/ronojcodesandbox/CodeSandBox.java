package com.ron.ronojcodesandbox;

import com.ron.ronojcodesandbox.model.ExecuteCodeRequest;
import com.ron.ronojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱的接口定义
 * @author Ron_567
 */
public interface CodeSandBox {
    /**
     *
     * @param executeCodeRequest 请求执行代码
     * @return 返回执行结果
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
