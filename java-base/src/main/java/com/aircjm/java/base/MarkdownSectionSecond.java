package com.aircjm.java.base;

import java.util.ArrayList;
import java.util.List;

import static com.aircjm.java.base.MarkdownSectionOne.readMarkdownFile;
import static com.aircjm.java.base.MarkdownSectionOne.splitMarkdownByHeadings;

public class MarkdownSectionSecond {
    private String title;
    private String content;
    private List<MarkdownSectionSecond> children;

    public MarkdownSectionSecond(String title, String content) {
        this.title = title;
        this.content = content;
        this.children = new ArrayList<>();
    }

    public void addChild(MarkdownSectionSecond section) {
        children.add(section);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<MarkdownSectionSecond> getChildren() {
        return children;
    }

    public void printSection(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + "Title: " + title);
        System.out.println(indent.toString() + "Content: " + content);

        for (MarkdownSectionSecond child : children) {
            child.printSection(level + 1);
        }
    }

    public static MarkdownSectionSecond buildSectionTree(List<String> sections, int level) {
        MarkdownSectionSecond root = null;
        MarkdownSectionSecond currentSection = null;

        for (String section : sections) {
            int sectionLevel = countHeadingLevel(section);
            if (sectionLevel == level) {
                // 拆分标题和内容
                String[] parts = splitSection(section);
                MarkdownSectionSecond newSection = new MarkdownSectionSecond(parts[0], parts[1]);
                if (root == null) {
                    root = newSection;
                } else {
                    currentSection.addChild(newSection);
                }
                currentSection = newSection;
            } else if (sectionLevel > level) {
                if (currentSection != null) {
                    List<String> childSections = new ArrayList<>();
                    childSections.add(section);
                    currentSection.addChild(buildSectionTree(childSections, sectionLevel));
                }
            } else {
                currentSection = null;
            }
        }

        return root;
    }

    private static int countHeadingLevel(String section) {
        int level = 0;
        while (level < section.length() && section.charAt(level) == '#') {
            level++;
        }
        return level;
    }

    private static String[] splitSection(String section) {
        // 使用第一个换行符将标题和内容拆分
        int index = section.indexOf("\n");
        if (index >= 0) {
            String title = section.substring(0, index).trim();
            String content = section.substring(index + 1).trim();
            return new String[]{title, content};
        }else {
            return new String[]{section, ""};
        }

    }

    public static void main(String[] args) {

        // 定义要读取的Markdown文件路径
        String filePath = "a.md";

        // 读取Markdown文件内容
        String markdownContent = readMarkdownFile(filePath);

        // 按照标题切分Markdown内容（包括标题本身）
        List<String> sections = splitMarkdownByHeadings(markdownContent);

        // 构建树结构
        MarkdownSectionSecond root = buildSectionTree(sections, 1);

        // 打印树结构
        root.printSection(0);
    }
}
