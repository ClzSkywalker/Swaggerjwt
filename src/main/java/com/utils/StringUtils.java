package com.utils;

/**
 * 描述：
 * 〈工具类〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
public class StringUtils {
    /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs){
        int strLen;
        if (cs==null || (strLen=cs.length())==0){
            return true;
        }
        for (int i=0;i<strLen;i++){
            if (!Character.isWhitespace(cs.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
