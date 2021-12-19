package com.iohao.little.game.net.kit;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * 时间段范围
 * <pre>
 *     23:00~1:30
 * </pre>
 */
public class TimeBetweenKit implements Serializable {
    /**
     * 一天的分钟总数
     */
    static final int oneDayMinute = 24 * 60;

    /**
     * 指定时间是否在 时间段范围内
     * <pre>
     * 示例:
     *     起始时间 23:00, 结束时间 1:30
     *
     *     当前时间在起始时间与结束时间之内就是时间段内,
     *     如果当前时间是 1:31, 就不属于时间段内
     * </pre>
     *
     * @param startTime 起始时间 格式 [小时:分钟] . 如: 23:00, 1:30
     * @param endTime   结束时间 格式 [小时:分钟] . 如: 23:00, 1:30
     * @param nowTime   当前时间是否在 起始时间与结束时间之内
     * @return true 起始时间与结束时间之内
     */
    public static boolean betweenNowTime(String startTime, String endTime, LocalTime nowTime) {

        int startTimeIndex = convertTimeIndex(startTime);
        int endTimeIndex = convertTimeIndex(endTime);

        return betweenNowTime(startTimeIndex, endTimeIndex, nowTime);
    }

    /**
     * 指定时间是否在 时间段范围内
     * <pre>
     * 示例:
     *     起始时间 23:00, 结束时间 1:30
     *
     *     当前时间在起始时间与结束时间之内就是时间段内,
     *     如果当前时间是 1:31, 就不属于时间段内
     * </pre>
     *
     * @param startTimeIndex 起始时间数值 把格式 [小时:分钟] . 如: 23:00, 1:30的时间格式转换成 时间数值
     * @param endTimeIndex   结束时间数值 格式 [小时:分钟] . 如: 23:00, 1:30的时间格式转换成 时间数值
     * @param nowTime        当前时间是否在 起始时间与结束时间之内
     * @return true 起始时间与结束时间之内
     */
    public static boolean betweenNowTime(int startTimeIndex, int endTimeIndex, LocalTime nowTime) {
        if (startTimeIndex == endTimeIndex) {
            return false;
        }

        int nowTimeIndex = convertTimeIndex(nowTime);

        boolean success;
        // 跨天
        if (startTimeIndex > endTimeIndex) {
            if (nowTimeIndex >= startTimeIndex) {
                // 说明是当天的时间段比较
                success = true;
            } else {
                /*
                 * 跨天时间段:
                 * 1 给当前时间加上全天分钟数
                 * 2 给结束时间加上全天分钟数
                 */
                int upNowTimeIndex = nowTimeIndex + oneDayMinute;
                int upEndIndex = endTimeIndex + oneDayMinute;
                success = upNowTimeIndex >= startTimeIndex && upNowTimeIndex <= upEndIndex;
            }
        } else {
            success = nowTimeIndex >= startTimeIndex && nowTimeIndex <= endTimeIndex;
        }

        return success;
    }

    /**
     * 当前时间是否在 时间段范围内
     * <pre>
     * 示例:
     *     起始时间 23:00, 结束时间 1:30
     *
     *     当前时间在起始时间与结束时间之内就是时间段内,
     *     如果当前时间是 1:31, 就不属于时间段内
     * </pre>
     *
     * @param startTime 起始时间 格式 [小时:分钟] . 如: 23:00, 1:30
     * @param endTime   结束时间 格式 [小时:分钟] . 如: 23:00, 1:30
     * @return true 起始时间与结束时间之内
     */
    public static boolean betweenNowTime(String startTime, String endTime) {
        return betweenNowTime(startTime, endTime, LocalTime.now());
    }

    /**
     * 将 LocalTime 的时间和分钟转换成 时间数值
     *
     * @param localTime LocalTime
     * @return 转换后的 时间数值
     */
    private static int convertTimeIndex(LocalTime localTime) {
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        return convertTimeIndex(hour, minute);
    }

    /**
     * 将小时和分钟转换成 时间数值
     *
     * @param time 小时和分钟. 格式 [小时:分钟] . 如: 23:00, 1:30
     * @return 转换后的 时间数值
     */
    public static int convertTimeIndex(String time) {
        String[] split = time.split(":");
        int hours = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return convertTimeIndex(hours, minute);
    }

    /**
     * 将小时和分钟转换成 时间数值
     *
     * @param hours  小时
     * @param minute 分钟
     * @return 转换后的 时间数值
     */
    private static int convertTimeIndex(int hours, int minute) {
        return hours * 60 + minute;
    }

}