package com.aircjm.java.base.anki;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

public class SM2Algorithm {

    // 熟练度等级
    private static final int[] LEVELS = {0, 1, 2, 3, 4, 5};

    // 最小间隔天数
    private static final int MIN_INTERVAL = 1;

    // 第一次复习间隔天数
    private static final int FIRST_INTERVAL = 1;

    // 默认熟练度等级
    private static final int DEFAULT_LEVEL = 0;

    // 熟练度等级最大值
    private static final int MAX_LEVEL = LEVELS[LEVELS.length - 1];

    // 计算下次复习时间
    public static Date getNextReviewDate(Date lastReviewDate, int level) {
        if (level < 0 || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Invalid level");
        }
        if (lastReviewDate == null) {
            return new Date();
        }
        int interval = getInterval(level, lastReviewDate);
        return new Date(lastReviewDate.getTime() + interval * 24 * 60 * 60 * 1000);
    }

    // 计算下次复习时间间隔
    private static int getInterval(int level, Date lastReviewDate) {
        if (level < 0 || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Invalid level");
        }
        if (lastReviewDate == null) {
            return FIRST_INTERVAL;
        }
        if (level == 0) {
            return MIN_INTERVAL;
        }
        if (level == 1) {
            return FIRST_INTERVAL;
        }
        int prevLevel = level - 1;
        int prevInterval = getInterval(prevLevel, lastReviewDate);
        return (int) Math.round(prevInterval * getEFactor(prevLevel));
    }

    // 计算熟练度因子
    private static double getEFactor(int level) {
        if (level < 0 || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Invalid level");
        }
        return 1.3 + 0.1 * level - 0.2 * level * level;
    }

    // 更新熟练度等级
    public static int updateLevel(int level, boolean correct) {
        if (level < 0 || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Invalid level");
        }
        if (correct) {
            if (level < MAX_LEVEL) {
                return level + 1;
            } else {
                return MAX_LEVEL;
            }
        } else {
            return DEFAULT_LEVEL;
        }
    }


    public static void main(String[] args) {
        // 第一次复习
        Date lastReviewDate = null;
        int level = SM2Algorithm.DEFAULT_LEVEL;
        Date nextReviewDate = SM2Algorithm.getNextReviewDate(lastReviewDate, level);
        System.out.println("Next review date: " + DateUtil.formatDateTime(nextReviewDate));

        // 正确
        lastReviewDate = nextReviewDate;
        level = SM2Algorithm.updateLevel(level, true);
        nextReviewDate = SM2Algorithm.getNextReviewDate(lastReviewDate, level);
        System.out.println("Next review date: " + DateUtil.formatDateTime(nextReviewDate));

        // 错误
        lastReviewDate = nextReviewDate;
        level = SM2Algorithm.updateLevel(level, false);
        nextReviewDate = SM2Algorithm.getNextReviewDate(lastReviewDate, level);
        System.out.println("Next review date: " + DateUtil.formatDateTime(nextReviewDate));

        // 正确
        lastReviewDate = nextReviewDate;
        level = SM2Algorithm.updateLevel(level, true);
        nextReviewDate = SM2Algorithm.getNextReviewDate(lastReviewDate, level);
        System.out.println("Next review date: " + DateUtil.formatDateTime(nextReviewDate));


        for (int i = 0; i < 20; i++) {
            int custom = RandomUtil.randomInt(0,5);
            boolean randomBoolean = RandomUtil.randomBoolean();
            lastReviewDate = nextReviewDate;
            level = SM2Algorithm.updateLevel(custom, randomBoolean);
            nextReviewDate = SM2Algorithm.getNextReviewDate(lastReviewDate, level);
            System.out.println(custom+""+randomBoolean+"Next review date: " + DateUtil.formatDateTime(nextReviewDate));

        }


    }



}