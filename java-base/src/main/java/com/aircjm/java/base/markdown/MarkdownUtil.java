package com.aircjm.java.base.markdown;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;

import java.util.List;

public class MarkdownUtil {

    public static List<String> splitMarkdownByHeadings(String markdownContent) {
        List<String> sections = Lists.newArrayList();

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
