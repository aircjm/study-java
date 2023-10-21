package com.aircjm.java.base.file;

import cn.hutool.core.io.FileUtil;
import com.aircjm.java.base.markdown.MarkdownSection;
import com.aircjm.java.base.markdown.MarkdownUtil;

import java.io.File;
import java.util.List;

public class ReadDir {

    public static void main(String[] args) {

        List<File> files = FileUtil.loopFiles("dir");

        files.stream().filter(file -> file.getName().endsWith(".md")).forEach(file -> {
            System.out.println(file.getPath());
            List<String> sections = MarkdownUtil.splitMarkdownByHeadings(FileUtil.readUtf8String(file));
            // 构建树结构
            boolean haveH1 = sections.stream().anyMatch(item -> item.startsWith("# "));

            if (!haveH1) {
                throw new RuntimeException("hello");
            }
            MarkdownSection root = MarkdownUtil.buildSectionTree(sections, 1);

            // 打印树结构
            root.printSection(0);


            List<MarkdownSection> h4Sections = MarkdownUtil.findH4Sections(root, 4);
            h4Sections.forEach(item -> {
                System.out.println("------------ Begin");
                System.out.println("Path: " + item.getPath());
                System.out.println("Title: " + item.getTitle());
                System.out.println("Content: " + item.getContent());
                System.out.println("------------ End");

            });

        });



    }


}
