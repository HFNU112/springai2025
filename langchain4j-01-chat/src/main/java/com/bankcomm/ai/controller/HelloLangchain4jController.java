package com.bankcomm.ai.controller;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shunpeng_hu
 * @date 2025/5/10 0:06
 */
@Slf4j
@RestController
public class HelloLangchain4jController {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    /**
     * GET http://localhost:9001/langchain4j/hello?prompt=我是自媒体小白，如何从零起步做个人IP？
     */
    @GetMapping("/langchain4j/hello")
    public String helloLangchain4j(@RequestParam(value = "prompt", defaultValue = "你是谁？", required = true) String prompt){
        try {
            String response = chatLanguageModel.generate(prompt);
            System.out.println("通过langchain4j调用deepseek-r1模型返回结果：\n " + response);
            return response;
        } catch (Exception e) {
            System.out.println("通过langchain4j调用deepseek-r1模型异常e： " + e);
        }
        return null;
    }



}
