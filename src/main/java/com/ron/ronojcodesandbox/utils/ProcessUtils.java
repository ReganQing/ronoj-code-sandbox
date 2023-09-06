package com.ron.ronojcodesandbox.utils;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.ron.ronojcodesandbox.model.ExecuteMessage;

import java.io.*;

/**
 * 进程工具类
 * @author Ron_567
 */
public class ProcessUtils {
    /**
     * 执行进程并获取信息
     * @param runProcess 执行进程
     * @param operation 操作类型
     * @return 返回执行信息
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String operation) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            // 计算每个用例的执行时间
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 等到程序执行完成拿到一个状态码，来判断程序是正常退出，还是异常退出
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            if (exitValue == 0) {
                // 正常退出
                System.out.println(operation + "成功");
                // 分批获取控制台进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 再逐行读取
                String compileOutputLine;
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());
            } else {
                // 异常退出
                System.out.println(operation + "失败，错误码：" + exitValue);
                // 分批获取控制台进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 再逐行读取
                String compileOutputLine;
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                System.out.println(compileOutputStringBuilder);
                // 分批获取控制台进程的异常输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                StringBuilder errorCompileOutputStringBuilder = new StringBuilder();
                // 再逐行读取
                String errorCompileOutputLine;
                while ((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorCompileOutputStringBuilder.append(errorCompileOutputLine).append("\n");

                }
                executeMessage.setErrorMessage(errorCompileOutputStringBuilder.toString());

            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }

    /**
     * 执行交互式进程并获取信息
     * @param runProcess 运行进程
     * @param operation 操作类型
     * @param args 用户输入的值
     * @return 返回执行信息
     */
    public static ExecuteMessage runInteractionProcessAndGetMessage(Process runProcess, String operation, String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            // 向控制台输入程序
            OutputStream outputStream = runProcess.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] inputString = args.split(" ");
            String joinInput = StrUtil.join("\n", inputString) + "\n";
            outputStreamWriter.write(joinInput);
            // 相当于按了回车，执行输入的发送
            outputStreamWriter.flush();

            // 分批获取进程的正常输出
            InputStream inputStream = runProcess.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder compileOutputStringBuilder = new StringBuilder();
            // 逐行读取
            String compileOutputLine;
            while ((compileOutputLine = bufferedReader.readLine()) != null) {
                compileOutputStringBuilder.append(compileOutputLine);
            }
            executeMessage.setMessage(compileOutputStringBuilder.toString());
            // 记得释放资源，不然会卡死
            outputStreamWriter.close();
            outputStream.close();
            inputStream.close();
            runProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }
}
