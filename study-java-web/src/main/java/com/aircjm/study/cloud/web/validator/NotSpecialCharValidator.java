package com.aircjm.study.cloud.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author aircjm
 */
public class NotSpecialCharValidator implements ConstraintValidator<NotSpecialChar,String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String result = stringFilterUtil(value);
            return "".equals(result);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否包含特殊字符
     * @param str 如此那
     * @throws PatternSyntaxException 抛出未经检查的异常，表明正则表达式模式中的语法错误
     */
    private static String stringFilterUtil(String str) throws PatternSyntaxException {
        if(null == str){
            str = "";
        }
        StringBuilder result = new StringBuilder();
        // 识别所有特殊字符
        String regEx = "[<>\"'%;()&+《》“‘；（）]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        while(matcher.find()) {
            result.append(matcher.group(0)).append(",");
        }
        if (result.length() > 0) {
            return result.substring(0,result.length() -1);
        }else {
            return "";
        }

    }
}
