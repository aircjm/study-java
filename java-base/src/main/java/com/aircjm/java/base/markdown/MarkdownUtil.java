package com.aircjm.java.base.markdown;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.exceptions.ExceptionUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarkdownUtil {
    public static MarkdownSection buildSectionTree(List<String> sections, int level) {
        MarkdownSection root = null;
        MarkdownSection currentSection = null;

        for (String section : sections) {
            int sectionLevel = countHeadingLevel(section);
            if (sectionLevel == level) {
                // 拆分标题和内容
                String[] parts = splitSection(section);
                MarkdownSection newSection = new MarkdownSection(parts[0], parts[1]);
                if (root == null) {
                    root = newSection;
                    root.setPath(newSection.getTitle());
                } else {
                    currentSection.addChild(newSection);
                    newSection.setPath(currentSection.getPath() + "/" + newSection.getTitle());
                }
                currentSection = newSection;
            } else if (sectionLevel > level) {
                if (currentSection != null) {
                    List<String> childSections = new ArrayList<>();
                    childSections.add(section);
                    MarkdownSection childSection = buildSectionTree(childSections, sectionLevel);
                    currentSection.addChild(childSection);
                    childSection.setPath(currentSection.getPath() + "/" + childSection.getTitle());
                }
            } else {
                currentSection = null;
            }
        }

        return root;
    }


    public static String[] splitSection(String section) {
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


    private static int countHeadingLevel(String section) {
        int level = 0;
        while (level < section.length() && section.charAt(level) == '#') {
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        // 定义要读取的Markdown文件路径
        String filePath = "a.md";

        // 读取Markdown文件内容
        String markdownContent = readMarkdownFile(filePath);

        // 按照标题切分Markdown内容（包括标题本身）
        List<String> sections = splitMarkdownByHeadings(markdownContent);

        // 构建树结构
        MarkdownSection root = buildSectionTree(sections, 1);

        // 打印树结构
        root.printSection(0);
    }

    public static String readMarkdownFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> splitMarkdownByHeadings(String markdownContent) {
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


    public static List<MarkdownSection> findH4Sections(MarkdownSection section,Integer level) {
        List<MarkdownSection> h4Sections = new ArrayList<>();
        findH4SectionsRecursively(section, h4Sections, level);
        return h4Sections;
    }

    public static void findH4SectionsRecursively(MarkdownSection section, List<MarkdownSection> h4Sections,
                                                 Integer level) {
        if (countHeadingLevel(section.getTitle()) == level) {
            MarkdownSection result = BeanUtil.copyProperties(section, MarkdownSection.class);
            result.setChildren(null);
            h4Sections.add(result);
        }
        for (MarkdownSection child : section.getChildren()) {
            findH4SectionsRecursively(child, h4Sections, level);
        }
    }
}
