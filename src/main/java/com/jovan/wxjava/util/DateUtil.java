package com.jovan.wxjava.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jovan
 * @create 2019/8/12
 */
public class DateUtil {
    public static String format(Date date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }
}
