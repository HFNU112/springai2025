package com.bankcomm.ai.langchain4jchataiservice.config;

import com.bankcomm.ai.langchain4jchataiservice.service.IChatAssistant;
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

//    @Bean(name = "chatLanguageModelDeepSeekR1")
    public ChatModel chatLanguageModelByDeepSeekR1() {
        return OpenAiChatModel.builder()
                .apiKey("sk-869cc0141e114cbc9d63a8cf45d78606")
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    @Bean(name = "streamingChatLanguageModelByQwen")
    public StreamingChatModel streamingChatLanguageModelByQwen() {
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

//    @Bean(name = "chat")
    public IChatAssistant chatByDeepSeek(ChatModel chatLanguageModelByDeepSeekR1)
    {
        return AiServices.create(IChatAssistant.class, chatLanguageModelByDeepSeekR1);
    }

    @Bean(name = "chat")
    public IChatAssistant chat(ChatModel chatLanguageModelByQwen)
    {
        return AiServices.create(IChatAssistant.class, chatLanguageModelByQwen);
    }

    @Bean(name = "chatFluxByQwen")
    public IChatAssistant chatFlux(StreamingChatModel streamingChatLanguageModelByQwen)
    {
        return AiServices.create(IChatAssistant.class, streamingChatLanguageModelByQwen);
    }

    @Bean(name = "chatFluxDeepSeek")
    public IChatAssistant chatFlux2(StreamingChatModel streamingChatLanguageModelByDeepSeek)
    {
        return AiServices.create(IChatAssistant.class, streamingChatLanguageModelByDeepSeek);
    }

}
