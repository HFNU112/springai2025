package com.hu.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author shunpeng_hu
 * @date 2025/4/24 22:07
 */
public class demo01 {

    public static void main(String[] args) {
        try {
            // 请替换为你的实际文件路径
            Path filePath = Paths.get("deepseek4j\\src\\main\\resources\\test.txt");

            // 读取文件内容为字符串（保留所有原始字符）
            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            System.out.println("content = " + content);

            // 统计总字符数（包含所有特殊符号和空格）
            int totalCharacters = content.length();

            System.out.println("Total characters: " + totalCharacters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
