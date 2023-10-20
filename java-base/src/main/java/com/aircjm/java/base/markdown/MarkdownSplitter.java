package com.aircjm.java.base.markdown;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownSplitter {
    public static void main(String[] args) {
        // 定义要读取的Markdown文件路径
        String filePath = "a.md";
        // 读取Markdown文件内容
        String markdownContent = readMarkdownFile(filePath);

        // 按照标题切分Markdown内容（包括标题本身）
        List<String> sections = splitMarkdownByHeadings(markdownContent);

        // 输出切分后的部分
        for (String section : sections) {
            System.out.println(section);
            System.out.println("------------");
        }
    }

    private static String readMarkdownFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static List<String> splitMarkdownByHeadings(String markdownContent) {
        List<String> sections = new ArrayList<>();

        // 使用正则表达式匹配标题行
        Pattern pattern = Pattern.compile("^(#+\\s+.+)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(markdownContent);

        int startIndex = 0;
        while (matcher.find()) {
            // 切分Markdown内容并添加到部分列表
            String section = markdownContent.substring(startIndex, matcher.start()).trim();
            sections.add(section);

            startIndex = matcher.start();

            System.out.println("Heading: " + matcher.group(1));
        }

        // 添加最后一个部分
        String lastSection = markdownContent.substring(startIndex).trim();
        sections.add(lastSection);

        return sections;
    }
}
