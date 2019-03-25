package com.dan.library.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dan on 2017/10/23.
 */
public class StringUtil {

    /**
     * 转换字符 1-9，A-I
     *
     * @param num
     * @return
     */
    public static String changeStr(int num) {
        if (num == 0) {
            return "";
        } else {
            char c = (char) (num + 64);
            return String.valueOf(c);
        }
    }

    /**
     * 最后一位，替换字符 1-9替换ABC 0替换 空
     */
    public static String replaceLastStr(String str) {

        if (StringUtils.isEmpty(str)) {
            return "";
        }
        //截取最后一位字符
        String lastStr = str.substring(str.length() - 1);

        //删除最后一位字符
        str = str.substring(0, str.length() - 1);

        try {
            int num = Integer.parseInt(lastStr);
            //替换字符
            String toChar = changeStr(num);
            return str + toChar;
        } catch (NumberFormatException e) {
            System.out.println("最后一位不是数字...[ " + lastStr + " ]");
        }
        return str + lastStr;
    }

    /**
     * 根据键值对填充字符串，如("hello ${name}",{name:"xiaoming"})
     * 输出：
     *
     * @param content
     * @param map
     * @return
     */
    public static String renderString(String content, Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            Set<Map.Entry<String, Object>> sets = map.entrySet();
            for (Map.Entry<String, Object> entry : sets) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(content);
                content = matcher.replaceAll(judgeType(entry.getValue()));
            }
        }
        return content;
    }

    public static Object judgeConversionType(Object param) {
        if (param == null) {
            return "";
        }
        if (param instanceof Integer) {
            return String.valueOf(param);
        } else if (param instanceof Double) {
            String numberStr = String.valueOf(param);
            if (numberStr.contains(".") || numberStr.contains("e") || numberStr.contains("E")) {
                Double v = Double.parseDouble(numberStr);
                if (numberStr.endsWith(".0")) {
                    return v.longValue();
                }
                return Double.parseDouble(numberStr);
            }
            return Long.parseLong(numberStr);
        } else if (param instanceof Float) {
            return String.valueOf(param);
        } else if (param instanceof Long) {
            return String.valueOf(param);
        } else if (param instanceof Boolean) {
            return String.valueOf(param);
        }
        return String.valueOf(param);
    }

    /**
     * 判断类型-String类型返回结果加单引号
     */
    public static String judgeType(Object param) {
        if (param == null) {
            return "";
        }
        if (param instanceof Integer) {
            return String.valueOf(param);
        } else if (param instanceof Double) {
            return String.valueOf(param);
        } else if (param instanceof Float) {
            return String.valueOf(param);
        } else if (param instanceof Long) {
            return String.valueOf(param);
        } else if (param instanceof Boolean) {
            return String.valueOf(param);
        }
        /* else if (param instanceof String) {
        }*/
        return String.valueOf("'" + param + "'");

    }

    /**
     * 在指定位置插入字符
     *
     * @param str      原字符串
     * @param dec      要插入的字符串
     * @param position 位置
     * @return
     */
    public static String insertStringInParticularPosition(String str, String dec, int position) {
        StringBuffer stringBuffer = new StringBuffer(str);
        return stringBuffer.insert(position, dec).toString();
    }

    public static String removeStrBlank(String str, String replacement) {
        String regex = "\\s*|\t|\n";
        return removeStrBlank(str, replacement, regex);
    }

    public static String removeStrBlank(String str, String replacement, String regex) {
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(str);
            str = m.replaceAll(replacement);
        }
        return str;
    }

    /**
     * 判断对象值是否为空:
     * 若对象为字符串，判断对象值是否为null或空格;
     * 若对象为数组，判断对象值是否为null，或数组个数是否为0;
     * 若对象为List，判断对象值是否为null，或List元素是否个数为0;
     * 其他类型对象，只判断值是否为null.
     *
     * @param value
     * @return true
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if ((value instanceof String) && (((String) value).trim().length() < 1)) {
            return true;
        } else if (value.getClass().isArray()) {
            if (0 == java.lang.reflect.Array.getLength(value)) {
                return true;
            }
        } else if (value instanceof List) {
            if (((List<?>) value).isEmpty()) {
                return true;
            }
        } else if (value instanceof Map) {
            if (((Map<?, ?>) value).isEmpty()) {
                return true;
            }
        } else if (value instanceof Set) {
            if (((Set<?>) value).isEmpty()) {
                return true;
            }
        }
        return false;
    }

   /* public static void main(String[] args) {
        String s = "safsghgj\n\n\n\n\n\tfdhfghdfg\tdsfgsdfgs\t";
        System.out.println("====="+s);
        String s1 = removeStrBlank(s,"/");
        System.out.println("++++++"+s1);
    }*/


}
