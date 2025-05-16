package com.bankcomm.ai.langchain4jchataiservice.controller;

import com.bankcomm.ai.langchain4jchataiservice.service.IChatAssistant;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author shunpeng_hu
 * @date 2025/5/15 19:10
 */
@Slf4j
@RestController
public class AiServiceChatController {

    @Resource(name = "chat")
    private IChatAssistant chatAssistant;

    @Resource(name = "chatFluxDeepSeek")
    private IChatAssistant chatAssistantFlux;

    @Resource(name = "streamingChatLanguageModelByQwen")
    private StreamingChatModel streamingChatLanguageModel;

    // http://localhost:9013/aiservice/chat?prompt=你是谁
    @GetMapping(value = "/aiservice/chat")
    public String chat(@RequestParam(name = "prompt",defaultValue = "你是谁") String prompt)
    {
        String result = chatAssistant.chat(prompt);
        System.out.println("---result: "+result);
        return result;
    }

    // http://localhost:9013/aiservice/chatFluxByDeepSeek?prompt=介绍一下上海的历史
    @GetMapping(value = "/aiservice/chatFluxByDeepSeek")
    public Flux<String> chatFlux(@RequestParam(name = "prompt",defaultValue = "你是谁") String prompt)
    {
        Flux<String> resultFlux = chatAssistantFlux.chatFlux(prompt);
        System.out.println("resultFlux = " + resultFlux);
        return resultFlux;
    }

    // http://localhost:9013/aiservice/chatFlux2ByQwen?prompt=介绍一下上海有什么美食特色
    @GetMapping(value = "/aiservice/chatFlux2ByQwen")
    public Flux<String> chatFlux2(@RequestParam(name = "prompt",defaultValue = "你是谁") String prompt)
    {
        return Flux.create(stringFluxSink ->
        {
            streamingChatLanguageModel.chat(prompt, new StreamingChatResponseHandler()
            {
                @Override
                public void onPartialResponse(String s)
                {
                    stringFluxSink.next(s);
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse)
                {
                    stringFluxSink.complete();
                }

                @Override
                public void onError(Throwable throwable)
                {
                    stringFluxSink.error(throwable);
                }
            });
        });
    }
}
