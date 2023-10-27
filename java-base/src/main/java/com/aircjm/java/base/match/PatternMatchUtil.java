package com.aircjm.java.base.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aircjm
 */
public class PatternMatchUtil {

    public static void main(String[] args) {

        String md = "尽可能减少调用的深度多扇入，少扇出\n" +
                "单入口，单出口\n" +
                "模块的作用域应该在模块之内功能应该是可预测的\n" +
                "    Path: # 系统设计一人机界面设计/# 系统设计一结构化设计/#### 什么是`多扇入，少扇出`\n" +
                "    Title: #### 什么是`多扇入，少扇出`\n" +
                "    Content: 对于一个由多个模块构成的图而言，扇入和扇出分别是入度和出度。多扇入少扇出 意思就是指向自己的要多一点，指出去的要少一点。当一个模块的入度非常大时 说明有很多模块都复用了这个模块，说明你这个模块价值比较高，复用程度比较高。而你去调用其他模块过多了，说明你这个模块自身的职能比较多，所以是不好的现象。所以要求是多扇入少扇出。\n" +
                "    Path: # 系统设计一人机界面设计/# 系统设计一结构化设计/## 软件设计思想\n" +
                "    Title: ## 软件设计思想\n" +
                "    Content: \n" +
                "    Path: # 系统设计一人机界面设计/# 系统设计一结构化设计/#####  设计思想\n" +
                "    Title: #####  设计思想\n" +
                "    Content: 抽象化\n" +
                "自顶而下、逐步求精\n" +
                "信息隐蔽\n" +
                "模块独立（高内聚、低耦合）\n" +
                "\n" +
                "![[Pasted image 20221015102621.png]]";

        System.out.println(replaceDoubleLinkToMd(md));;
    }


    public static String replaceDoubleLinkToMd(String md) {
        String regex = "!\\[\\[(.*?)\\]\\]"; // 匹配双链笔记中的图片链接
        // 创建Pattern对象，并编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象，并应用正则表达式到输入字符串上
        Matcher matcher = pattern.matcher(md);

        // 使用正则表达式进行匹配和替换
        String result = matcher.replaceAll("![]($1)");

        return result;
    }



    public static String replaceDoubleLinkToAnkiMd(String md) {
        String regex = "!\\[\\[(.*?)\\]\\]"; // 匹配双链笔记中的图片链接
        // 创建Pattern对象，并编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象，并应用正则表达式到输入字符串上
        Matcher matcher = pattern.matcher(md);

        // 使用正则表达式进行匹配和替换
        String result = matcher.replaceAll("<img src=\"$1\">");

        return result;
    }

}
