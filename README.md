# SpringAI2025

#### 介绍
spring-ai-ollama、deepseek4j、langchain4j连接大模型（diffusion_pytorch_model等）与微服务应用模块整合

#### 基础概念
LLM大模型
![service_langchain_model](deepseek4j/src/main/resources/image/service_langchain_model.png)

单模态：文生文、文生图、音生文 一问一答
多模态：

知识蒸馏：deepseek-算法、GPT-算力

蒸馏：
微调：
RAG（检索增强生成）：

#### 安装

cmd命令行自定义安装路径
OllamaSetup.exe /DIR=E:\ai_model_work\ollama

环境变量
OLLAMA_MODELS
E:\ai_model_work\soft

ollama --version
netstat -ano | findstr 11434

> ollama list
> ollama ps
> ollama stop
> ollama serve

langchain4j官网：https://docs.langchain4j.dev/

AiService是langchain4j提供调用大模型的高阶接口。



#### 使用说明

> 学历、资历、能力

你负责的功能和模块
用到了哪些技术
做了哪些设计
碰到过哪些难题，说说细节

#### 效果图
##### 普通对话
![aliQwen-chat](https://springai2025.oss-cn-shanghai.aliyuncs.com/langchain4j/aliQwen-chat.gif)

##### 流式对话
![aliQwen-chatflux](https://springai2025.oss-cn-shanghai.aliyuncs.com/langchain4j/aliQwen-chatflux.gif)
