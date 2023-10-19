package com.aircjm.java.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MarkdownSection {
    private String title;
    private List<MarkdownSection> children;

    public MarkdownSection(String title) {
        this.title = title;
        this.children = new ArrayList<>();
    }

    public void addChild(MarkdownSection section) {
        children.add(section);
    }

    public String getTitle() {
        return title;
    }

    public List<MarkdownSection> getChildren() {
        return children;
    }

    public void printSection(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + title);

        for (MarkdownSection child : children) {
            child.printSection(level + 1);
        }
    }

    public static MarkdownSection buildSectionTree(List<String> sections, int level) {
        MarkdownSection root = null;
        MarkdownSection currentSection = null;

        for (String section : sections) {
            int sectionLevel = countHeadingLevel(section);
            if (sectionLevel == level) {
                MarkdownSection newSection = new MarkdownSection(section);
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

    public static void main(String[] args) {
        // 定义要读取的Markdown文件路径
        String filePath = "/Users/chenjiaming/Developer/code/github/Obsidian/lucida/0-INBOX/00软考-系统分析师/系统分析师-需求工程.md";


        // 读取Markdown文件内容
        String markdownContent = readMarkdownFile(filePath);

        // 按照标题切分Markdown内容（包括标题本身）
        List<String> sections = splitMarkdownByHeadings(markdownContent);

        // 构建树结构
        MarkdownSection root = buildSectionTree(sections, 1);

        // 打印树结构
        root.printSection(0);
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
        String[] lines = markdownContent.split("\n");
        for (String line : lines) {
            if (line.matches("^#+\\s+.+")) {
                sections.add(line.trim());
            } else if (!sections.isEmpty()) {
                int lastIndex = sections.size() - 1;
                String lastSection = sections.get(lastIndex);
                lastSection += "\n" + line;
                sections.set(lastIndex, lastSection);
            }
        }

        return sections;
    }
}
