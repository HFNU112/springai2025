package com.bankcomm.ai.langchain4jchataiservice.config;

import com.bankcomm.ai.langchain4jchataiservice.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Author shunpeng_hu
 * @date 2025/5/14 0:40
 */
@Configuration
public class LLMConfig {

    @Bean(name = "chatLanguageModel")
    public ChatModel chatLanguageModelByQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean(name = "streamingChatLanguageModel")
    public StreamingChatModel streamingChatLanguageModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("aliQwen-api"))
                .modelName("qwen-plus")
                .timeout(Duration.ofSeconds(60))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean(name = "streamingChatLanguageModelByDeepSeek")
    public StreamingChatModel streamingChatLanguageModelByDeepSeek() {

        return OpenAiStreamingChatModel.builder()
                        .apiKey(System.getenv("deepseek-api"))
                        .modelName("deepseek-chat")
                        .baseUrl("https://api.deepseek.com/v1")
                        .build();
    }

    @Bean(name = "chat")
    public ChatAssistant chat(ChatModel chatLanguageModelByQwen)
    {
        return AiServices.create(ChatAssistant.class, chatLanguageModelByQwen);
    }

    @Bean(name = "chatFlux")
    public ChatAssistant chatFlux(StreamingChatModel streamingChatLanguageModel)
    {
        return AiServices.create(ChatAssistant.class, streamingChatLanguageModel);
    }

    @Bean(name = "chatFluxDeepSeek")
    public ChatAssistant chatFlux2(StreamingChatModel streamingChatLanguageModelByDeepSeek)
    {
        return AiServices.create(ChatAssistant.class, streamingChatLanguageModelByDeepSeek);
    }

}
