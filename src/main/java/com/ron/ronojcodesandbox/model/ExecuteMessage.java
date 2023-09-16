package com.ron.ronojcodesandbox.model;

import lombok.Data;

/**
 * 进程执行信息
 * @author Ron_567
 */
@Data
public class ExecuteMessage {

    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;

    private Long memory;
}
