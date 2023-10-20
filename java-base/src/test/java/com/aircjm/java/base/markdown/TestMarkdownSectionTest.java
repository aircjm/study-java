package com.aircjm.java.base.markdown;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.aircjm.java.base.markdown.MarkdownSectionOne.readMarkdownFile;
import static com.aircjm.java.base.markdown.MarkdownSectionOne.splitMarkdownByHeadings;
import static org.junit.jupiter.api.Assertions.*;

public class TestMarkdownSectionTest {


    @Test
    public void testMarkdown() {

        TestMarkdownSection section = new TestMarkdownSection();

        // 定义要读取的Markdown文件路径
        String filePath = "testMd.md";

        // 读取Markdown文件内容
        String markdownContent = FileUtil.readString(filePath, "UTF-8");

        // 按照标题切分Markdown内容（包括标题本身）
        List<String> sections = MarkdownUtil.splitMarkdownByHeadings(markdownContent);

        // 构建树结构
        MarkdownSection root = section.buildSectionTree(sections, 1);

        // 打印树结构
        root.printSection(0);


        List<MarkdownSection> h4Sections = section.findH4Sections(root, 3);

        h4Sections.forEach(item -> {
            System.out.println("\n------------ Begin");
            System.out.println("Path: " + item.getPath());
            System.out.println("Title: " + item.getTitle());
            System.out.println("Content: " + item.getContent());
            System.out.println("------------ End\n");

        });
    }

}