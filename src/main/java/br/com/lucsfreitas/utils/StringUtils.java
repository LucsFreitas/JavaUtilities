package br.com.lucsfreitas.utils;

public class StringUtils {

    /**
     *
     * @param str string value
     * @return the string value with numeric characters only
     */
    public static String removeNonNumericCharacter (String str) {
        return str == null ? null : str.replaceAll("\\D+","");
    }
}
