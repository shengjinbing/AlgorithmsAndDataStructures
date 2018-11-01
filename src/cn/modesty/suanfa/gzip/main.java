package cn.modesty.suanfa.gzip;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class main {
    public static void main(String[] args) {
        System.out.println(fro("123.123456789", 8));
    }

    private static String fro(String value,int num){
        BigDecimal bigDecimal   =   new BigDecimal(value);
        double  s  =   bigDecimal.setScale(num,BigDecimal.ROUND_DOWN).doubleValue();

        return String.format("%.8f",s);
    }

    public static String formatDecimalDigits(String value, int num) {
        Double aDouble = 0.0;
        try {
            aDouble = Double.parseDouble(value);
            if (aDouble == 0) {
                return "0.00";
            }
        } catch (Exception e) {
            return value;
        }

        if (num == 8) {
            int i = value.indexOf(".");
            if (i == -1) {
                return value;
            }
            if (i > 0 && i < value.length() && value.substring(i).length() > 9) {
                return String.format("%." + num + "f", aDouble);
            } else {
                return value;
            }
        } else {
            return String.format("%." + num + "f", aDouble);
        }
    }

}
