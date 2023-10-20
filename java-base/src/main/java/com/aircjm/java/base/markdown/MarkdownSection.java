package com.aircjm.java.base.markdown;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.aircjm.java.base.markdown.MarkdownSectionOne.readMarkdownFile;
import static com.aircjm.java.base.markdown.MarkdownSectionOne.splitMarkdownByHeadings;

@Data
public class MarkdownSection {

    private String path;

    private String title;

    private String content;
    private List<MarkdownSection> children;

    public MarkdownSection(String title, String content) {
        this.title = title;
        this.content = content;
        this.children = new ArrayList<>();
        this.path = "";
    }

    public void addChild(MarkdownSection section) {
        children.add(section);
    }



    public void printSection(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + "Path: " + path);
        System.out.println(indent.toString() + "Title: " + title);
        System.out.println(indent.toString() + "Content: " + content);

        for (MarkdownSection child : children) {
            child.printSection(level + 1);
        }
    }
}
