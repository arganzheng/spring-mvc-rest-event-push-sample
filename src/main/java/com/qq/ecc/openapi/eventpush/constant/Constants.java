package com.qq.ecc.openapi.eventpush.constant;

/**
 * 类Constants.java的实现描述：公用常量类
 * 
 * @author arganzheng
 */
public abstract class Constants {

    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT   = "yyyy-MM-dd HH:mm:ss";

    /** Date默认时区 **/
    public static final String DATE_TIMEZONE      = "GMT+8";

    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8       = "UTF-8";

    /** JSON 应格式 */
    public static final String FORMAT_JSON        = "json";
    /** XML 应格式 */
    public static final String FORMAT_XML         = "xml";

    /** GET请求方式 */
    public static final String METHOD_GET         = "get";
    /** POST请求方式 */
    public static final String METHOD_POST        = "post";

    public static final String EMPTY_STRING       = "";

    public static final Long   ERROR_CODE_SUCCESS = 0L;

    public static int          ERROR_UNKNOWN      = 10001;
}
