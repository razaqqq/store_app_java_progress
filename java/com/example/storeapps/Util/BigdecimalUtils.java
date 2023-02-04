package com.example.storeapps.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigdecimalUtils {
    public static String getValue(BigDecimal value)
    {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        return String.valueOf(df.format(value));
    }

    public static float getFloat(BigDecimal value)
    {
        return value.floatValue();
    }

}
