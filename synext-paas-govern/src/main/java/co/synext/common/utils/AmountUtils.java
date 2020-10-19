/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package co.synext.common.utils;


import lombok.experimental.UtilityClass;

import java.text.DecimalFormat;

@UtilityClass
public class AmountUtils {

    /**
     * 分转元
     * @param amount
     * @return
     */
    public static String centToYuan(String amount){
        DecimalFormat df = new DecimalFormat("######0.00");
        Double d = Double.parseDouble(amount)/100;
        return df.format(d);
    }

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static String yuanToCent(String amount){
        DecimalFormat df = new DecimalFormat("######0");
        Double d = Double.parseDouble(amount)*100;
        return df.format(d);
    }
}
