package com.bankcomm.ai.langchain4jchataiservice.service;

import reactor.core.publisher.Flux;

/**
 * 定义AIService聊天助手接口
 * @Author shunpeng_hu
 * @date 2025/5/14 0:21
 */
public interface IChatAssistant {

    /**
     * 标准版对话聊天服务
     * @param prompt 提示词
     * @return String
     */
    String chat(String prompt);

    /**
     * 流式对话聊天服务
     * @param prompt 提示词
     * @return Flux<String>
     */
    Flux<String> chatFlux(String prompt);
}
