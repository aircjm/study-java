package com.aircjm.java.base.vo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkdownSection {
    private String title;
    private List<MarkdownSection> children;

    public MarkdownSection(String title) {
        this.title = title;
        this.children = new ArrayList<>();
    }

    public void addChild(MarkdownSection child) {
        children.add(child);
    }

    public String getTitle() {
        return title;
    }

    public List<MarkdownSection> getChildren() {
        return children;
    }

    public static MarkdownSection parseMarkdown(List<String> markdownLines, int startLine, int endLine) {
        // 递归地解析Markdown内容并创建树形结构
        MarkdownSection section = null;
        int currentLevel = -1;

        for (int i = startLine; i <= endLine; i++) {
            String line = markdownLines.get(i);
            if (line.matches("^#+\\s+.+")) {
                int level = countHeadingLevel(line);
                String title = line.replaceFirst("^#+\\s+", "");
                MarkdownSection subsection = new MarkdownSection(title);

                if (level > currentLevel) {
                    if (section != null) {
                        section.addChild(subsection);
                    }
                } else if (level <= currentLevel) {
                    int diff = currentLevel - level + 1;
                    MarkdownSection parent = section;
                    for (int j = 0; j < diff; j++) {
                        parent = parent.getParent();
                    }
                    parent.addChild(subsection);
                }

                section = subsection;
                currentLevel = level;
            } else if (section != null) {
                section.addChild(new MarkdownSection(line));
            }
        }

        return section;
    }

    private static int countHeadingLevel(String line) {
        int level = 0;
        while (line.charAt(level) == '#') {
            level++;
        }
        return level;
    }

    public MarkdownSection getParent() {
        // 获取父级部分
        // 在这个示例中，我们假设根部分的父级是null
        return null;
    }

    public void printStructure(String indent) {
        // 打印部分的层次结构
        System.out.println(indent + "- " + title);
        for (MarkdownSection child : children) {
            child.printStructure(indent + "  ");
        }
    }

    public static List<String> readMarkdownFile(String filePath) {
        List<String> markdownLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                markdownLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return markdownLines;
    }

    public static void main(String[] args) {

        String filePath = "a.md";
        // 从Markdown文件中读取内容
        List<String> markdownLines = readMarkdownFile(filePath);

        // 解析Markdown内容为树形结构
        MarkdownSection rootSection = parseMarkdown(markdownLines, 0, markdownLines.size() - 1);

        // 打印部分的层次结构
        rootSection.printStructure("");
    }
}
