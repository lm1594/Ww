package lkm.ww.comn.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.commons.io.Charsets;

/**
 * 스트링 유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public class StringUtil {

	private static final long serialVersionUID = 1535984825760751155L;
	/** 빈문자열 */
	public static final String EMPTY = "";
	/** 공백문자열 */
	public static final String BLANK = " ";

	private static final String[] XML_ESCAPES;
	static {
		int size = '>' + 1;
		XML_ESCAPES = new String[size];
		XML_ESCAPES['<'] = "&lt;";
		XML_ESCAPES['>'] = "&gt;";
		XML_ESCAPES['&'] = "&amp;";
		XML_ESCAPES['\''] = "&#039;";
		XML_ESCAPES['"'] = "&#034;";
	}
	private static final String[] XSS_REMOVESET;
	static {
		int size = '>' + 1;
		XSS_REMOVESET = new String[size];
		XSS_REMOVESET['\''] = BLANK;
		XSS_REMOVESET['"'] = BLANK;
	}

	/**
	 * 오른쪽 공백제거.
	 * <pre>
	 * StringUtil.rtrim(null)           = null
	 * "|" + StringUtil.rtrim("") + "|" = "||"
	 * "|" + StringUtil.rtrim("test  ") + "|" = "|test|"
	 * "|" + StringUtil.rtrim("  test") + "|" = "|  test|"
	 * </pre>
	 * @param str 원본문자열
	 * @return 변환값
	 */
	public static String rtrim(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		char[] val = str.toCharArray();
		int st = 0;
		int len = str.length();
		while (st < len && val[len - 1] <= ' ') {
			--len;
		}
		return str.substring(0, len);
	}

	/**
	 * 왼쪽 공백제거.
	 * <pre>
	 * StringUtil.ltrim(null)           = null
	 * "|" + StringUtil.ltrim("") + "|" = "||"
	 * "|" + StringUtil.ltrim("test  ") + "|" = "|test  |"
	 * "|" + StringUtil.ltrim("  test") + "|" = "|test|"
	 * </pre>
	 * @param str 원본문자열
	 * @return 변환값
	 */
	public static String ltrim(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		char[] val = str.toCharArray();
		int st = 0;
		int len = str.length();
		while (st < len && val[st] <= ' ') {
			++st;
		}
		return str.substring(st, len);
	}

	/**
	 * 공백제거.
	 * @param str 원본문자열
	 * @return 변환값
	 */
	public static String trim(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return str.trim();
	}

	/**
	 * 숫자데이터만 추출.
	 * <pre>
	 * StringUtil.numberChar(null)        = null
	 * StringUtil.numberChar("")          = ""
	 * StringUtil.numberChar("123-30/33") = "1233033"
	 * </pre>
	 * @param  str 원본문자열
	 * @return 변환문자열
	 */
	public static String numberChar(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int strLen = str.length();
		StringBuilder buff = new StringBuilder(strLen);
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (chr >= '0' && chr <= '9') {
				buff.append(chr);
			}
		}
		return buff.toString();
	}

	/**
	 * 한글이 포함되어 있는지 검사하는 함수.
	 * <pre>
	 * StringUtil.isContainKor(null)   = false
	 * StringUtil.isContainKor("")     = false
	 * StringUtil.isContainKor("1234") = false
	 * StringUtil.isContainKor("한글") = true
	 * </pre>
	 * @param str      검증대상 문자열
	 * @return         한글일 경우 true
	 */
	public static boolean isContainKor(String str) {
		if (str == null) {
			return false;
		}
		for (int ii = 0; ii < str.length(); ++ii) {
			char chr = str.charAt(ii);
			if ((0xAC00 <= chr && chr <= 0xD7A3) || (0x3131 <= chr && chr <= 0x318E)) { //korean : one character (consonant or vowel)
				return true;
			}
//			else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) { //english
//			} else if (0x30 <= c && c <= 0x39) { //integer/decimal
//			} else { // unknown
//			}
		}
		return false;
	}

	/**
	 * 문자열 치환 함수.
	 * <pre>
	 * StringUtil.replace(null, "a", "A")   = null
	 * StringUtil.replace("", "a", "A")     = ""
	 * StringUtil.replace("1234", "a", "A") = "1234"
	 * StringUtil.replace("aaaa", "a", "A") = "AAAA"
	 * </pre>
	 * @param source     원본 문자열
	 * @param subject    바뀔 문자열
	 * @param object     바꿀 문자열
	 * @return           바뀐 문자열
	 * @see #replace(String, String, String, boolean)
	 */
	public static String replace(String source, String subject, String object) {
		return replace(source, subject, object, true);
	}

	/**
	 * 문자열 치환 함수.
	 * <pre>
	 * StringUtil.replace(null, "a", "A", false)   = null
	 * StringUtil.replace("", "a", "A", false)     = ""
	 * StringUtil.replace("1234", "a", "A", false) = "1234"
	 * StringUtil.replace("aaaa", "a", "A", false) = "Aaaa"
	 * </pre>
	 * @param source     원본 문자열
	 * @param subject    바뀔 문자열
	 * @param object     바꿀 문자열
	 * @param doEnd      원본 문자열 끝자지 반복할지 여부
	 * @return           바뀐 문자열
	 */
	public static String replace(String source, String subject, String object, boolean doEnd) {
		if (isNullOrEmpty(source)) {
			return source;
		}
		StringBuilder buff = null;
		String nextStr = null;
		int idx;
		while ((idx = source.indexOf(subject)) >= 0) {
			if (nextStr == null) {
				buff = new StringBuilder();
			}
			String preStr = source.substring(0, idx);
			nextStr = source.substring(idx + subject.length(), source.length());
			source = nextStr;
			buff.append(preStr).append(object);
			if (!doEnd) {
				break;
			}
		}
		if (nextStr != null) {
			return buff.append(nextStr).toString();
		}
		return source;
	}

	/**
	 * 지정된 패턴에 해당하는 문자열를 제외한 나머지 문자열을 반환.
	 * <pre>
	 * StringUtil.remove(null, ".")   = null
	 * StringUtil.remove("", ".")     = ""
	 * StringUtil.remove("2012.01.21", ".") = "20120121"
	 * StringUtil.remove("2012.01.21 12:13:01", ".: ") = "20120121121301"
	 * </pre>
	 * @param original 원본 문자열
	 * @param charSequence 패턴을 제외한 문자열(char...)
	 * @return 바뀐 문자열
	 * @see #replace(String, String, String)
	 */
	public static String remove(String original, String charSequence) {
		if (isNullOrEmpty(original)) {
			return original;
		}
		if (charSequence.length() < 2) {
			return replace(original, charSequence, EMPTY);
		}
		return original.replaceAll("["
						+ charSequence.replaceAll("(\\p{Punct})", "\\\\$1")
						+ "]", EMPTY);
	}

	/**
	 * 공백제거 함수.
	 * <pre>
	 * StringUtil.removeBlank(null)      = null
	 * StringUtil.removeBlank("")        = ""
	 * StringUtil.removeBlank(" 1,000 ") = "1,000"
	 * StringUtil.removeBlank("1 2 3 4") = "1234"
	 * </pre>
	 * @param str 원본문자열
	 * @return 바뀐 문자열
	 */
	public static String removeBlank(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return str.replaceAll("\\p{Z}", EMPTY);
	}

	/**
	 * 콤마제거 함수.
	 * <pre>
	 * StringUtil.removeComma(null)      = null
	 * StringUtil.removeComma("")        = ""
	 * StringUtil.removeComma("1,000")   = "1000"
	 * StringUtil.removeComma("1,000.0") = "1000.0"
	 * </pre>
	 * @param str 원본문자열
	 * @return 바뀐 문자열
	 */
	public static String removeComma(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return removeCommaNotNull(str);
	}

	/**
	 * 콤마제거 함수 처리중 null 입력 시 오류발생.
	 * @param str
	 * @return 변환값
	 * @see #removeComma(String)
	 */
	public static String removeCommaNotNull(String str) {
		boolean comma = false;
		StringBuilder buff = null;
		int strLen = str.length();
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (chr == ',') {
				if (!comma) {
					buff = new StringBuilder(strLen);
					buff.append(str.substring(0, ii));
					comma = true;
				}
			} else if (comma) {
				buff.append(chr);
			}
		}
		if (comma) {
			return buff.toString();
		}
		return str;
	}

	/**
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 함수(실패 시 null 반환).
	 * <pre>
	 * String str = "가나다라마바사아";
	 * String rst = StringUtil.changeCharset(str,"8859_1","ksc5601");
	 * </pre>
	 * @param source    원본 문자열
	 * @param origin    원본 CharSet
	 * @param target    지정 CharSet
	 * @return          지정 CharSet으로 변환된 문자열
	 */
	public static String changeCharset(String source, String origin, String target) {
		if (!isNullOrEmpty(source)) {
			try {
				return new String(
							source.getBytes(Charsets.toCharset(origin)),
							Charsets.toCharset(target));
			} catch (Throwable ignore) {

			}
		}
		return source;
	}

	/**
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 함수(실패 시 null 반환).
	 * <pre>
	 * String str = "가나다라마바사아";
	 * String rst = StringUtil.changeCharset(str,"ksc5601");
	 * </pre>
	 * @param source    원본 문자열
	 * @param target    지정 CharSet
	 * @return          지정 CharSet으로 변환된 문자열
	 */
	public static String changeCharset(String source, String target) {
		return changeCharset(source, (String) null, target);
	}

	/**
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 함수(실패 시 null 반환).
	 * <pre>
	 * String str = "가나다라마바사아";
	 * String rst = StringUtil.changeCharset(str,"8859_1","ksc5601");
	 * </pre>
	 * @param source    원본 문자열
	 * @param origin    원본 CharSet
	 * @param target    지정 CharSet
	 * @return          지정 CharSet으로 변환된 문자열
	 */
	public static String changeCharset(String source, Charset origin, Charset target) {
		if (!isNullOrEmpty(source)) {
			try {
				return new String(
							source.getBytes(Charsets.toCharset(origin)),
							Charsets.toCharset(target));
			} catch (Throwable ignore) {

			}
		}
		return source;
	}

	/**
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 함수(실패 시 null 반환).
	 * <pre>
	 * String str = "가나다라마바사아";
	 * String rst = StringUtil.changeCharset(str,"ksc5601");
	 * </pre>
	 * @param source    원본 문자열
	 * @param target    지정 CharSet
	 * @return          지정 CharSet으로 변환된 문자열
	 */
	public static String changeCharset(String source, Charset target) {
		return changeCharset(source, (Charset) null, target);
	}

	/**
	 * 문자열이 null 인지 확인하는 함수.
	 * @param str 원본 문자열
	 * @return boolean
	 * @see #nvl(String, String)
	 */
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 문자열이 null 또는 Empty 인지 확인하는 함수.
	 * <pre>
	 * boolean chk = StringUtil.isNullOrEmpty(request.getParameter("id1"));
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  boolean
	 * @see #nvl2(String, String)
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
     * 스트링내의 임의의 문자열을 새로운 문자열로 대치하는 메소드
     *
     * @param source    스트링
     * @param before    바꾸고자하는 문자열
     * @param after     바뀌는 문자열
     * @return 변경된 문자열
     */
    public static String change(String source, String before, String after) {
        int i = 0;
        int j = 0;
        if(source==null){
        	return "";
        }
        StringBuffer sb = new StringBuffer();

        while ((j = source.indexOf(before, i)) >= 0) {
            sb.append(source.substring(i, j));
            sb.append(after);
            i = j + before.length();
        }

        sb.append(source.substring(i));
        return sb.toString();
    }


	/**
	 * 문자열이 null, Empty, 공백(Blank) 인지 확인하는 함수.
	 * <pre>
	 * boolean chk = StringUtil.isNullOrBlank(request.getParameter("id1"));
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  boolean
	 * @see #nvl3(String, String)
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static String isEmpty(String str1, String str2) {
		if(isNullOrBlank(str1)) {
			return str2;
		} else {
			return str1;
		}
	}

	/**
	 * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 함수.
	 * <pre>
	 * String id1 = StringUtil.nvl(request.getParameter("id1"),"");
	 * 서블릿 요청 파라미터 id1에 대한 값이 null이면 "" 로 바꾼다.
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     null일 경우 바뀔 문자열
	 * @return  문자열
	 */
	public static String nvl(String str, String def) {
		return isNull(str) ? def : str;
	}

	/**
	 * 문자열이 null인지 확인하고 null인 경우 ""({@link #EMPTY})로 바꾸는 함수.
	 * <pre>
	 * String id1 = StringUtil.nvl(request.getParameter("id1"),"");
	 * 서블릿 요청 파라미터 id1에 대한 값이 null이면 "" 로 바꾼다.
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  문자열
	 * @see #nvl(String, String)
	 */
	public static String nvl(String str) {
		return nvl(str, EMPTY);
	}

	/**
	 * 문자열이 null 또는 Empty인 경우 지정된 문자열로 바꾸는 함수.
	 * <pre>
	 * String id1 = StringUtil.nvl2(request.getParameter("id1"),"unKnown");
	 * 서블릿 요청 파라미터 id1에 대한 값이 null 또는 Empty 이면 "unKnown" 로 바꾼다.
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     null 또는 Empty 일 경우 바뀔 문자열
	 * @return  문자열
	 */
	public static String nvl2(String str, String def) {
		return isNullOrEmpty(str) ? def : str;
	}

	/**
	 * 문자열이 null, Empty, 공백(Blank)인 경우 지정된 문자열로 바꾸는 함수(문자열 trim()함).
	 * <pre>
	 * String id1 = StringUtil.nvl3(request.getParameter("id1"),"unKnown");
	 * 서블릿 요청 파라미터 id1에 대한 값이 null, Empty, 공백(Blank) 이면 "unKnown" 로 바꾼다.
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     null 또는 Empty 일 경우 바뀔 문자열
	 * @return  문자열
	 */
	public static String nvl3(String str, String def) {
		if (str == null) {
			return def;
		}
		str = str.trim();
		if (str.length() == 0) {
			return def;
		}
		return str;
	}

	/**
	 * 문자열 길이(1이상)가 있는지 검증.
	 * @param str 문자열
	 * @return boolean
	 */
	public static boolean hasLength(String str) {
		return getLength(str) > 0;
	}

	/**
	 * 문자열 길이(NULL일 경우 0).
	 * @param str 문자열
	 * @return 문자열 길이
	 */
	public static int getLength(String str) {
		return str == null ? 0 : str.length();
	}

	/**
	 * 검색 문자열 중 검색 대상 문자열이 있는 시작 위치, 검색대상 문자열이 없거나 null인 경우 -1.
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf("", "")           = 0
	 * StringUtil.indexOf("aabaabaa", "a")  = 0
	 * StringUtil.indexOf("aabaabaa", "b")  = 2
	 * StringUtil.indexOf("aabaabaa", "ab") = 1
	 * StringUtil.indexOf("aabaabaa", "")   = 0
	 * StringUtil.indexOf("aabaabaa", "x")  = -1
	 * </pre>
	 *
	 * @param str  검색 문자열
	 * @param searchStr  검색 대상문자열
	 * @return 문자열 위치값
	 */
	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return -1;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * 검색 문자열 중 검색 대상 문자가 있는 시작 위치, 검색대상 문자가 없거나 null인 경우 -1.
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf("aabaabaa", 'a')  = 0
	 * StringUtil.indexOf("aabaabaa", 'b')  = 2
	 * StringUtil.indexOf("aabaabaa", 'ab') = 1
	 * StringUtil.indexOf("aabaabaa", 'x')  = -1
	 * </pre>
	 *
	 * @param str  검색 문자열
	 * @param searchChar  검색 대상문자
	 * @return 문자 위치값
	 */
	public static int indexOf(String str, char searchChar) {
		if (str == null) {
			return -1;
		}
		return str.indexOf(searchChar);
	}

	/**
	 * Object의 {@link #toString()} 메소드 실행 함수.
	 * <pre>
	 * StringUtil.toString(null, "") = ""
	 * StringUtil.toString("", "")   = ""
	 * StringUtil.toString((Map) null, "") = ""
	 * StringUtil.toString(Integer.valueOf("1234"), "0") = "1234"
	 * </pre>
	 * @param obj Object
	 * @param def 기본값4
	 * @return Object 내용
	 */
	public static String toString(Object obj, String def) {
		if (obj == null) {
			return def;
		}
		return obj.toString();
	}

	/**
	 * Object의 {@link #toString()} 메소드 실행 함수.
	 * <pre>
	 * StringUtil.toString(null) = null
	 * StringUtil.toString("")   = ""
	 * StringUtil.toString((Map) null) = null
	 * StringUtil.toString(Integer.valueOf("1234")) = "1234"
	 * </pre>
	 * @param obj Object
	 * @return Object 내용
	 * @see #toString(Object, String)
	 */
	public static String toString(Object obj) {
		return toString(obj, null);
	}

	/**
	 * Object 배열의 인덱스에 해당하는 {@link #toString()} 메소드 실행 함수.
	 * @param array Object 배열
	 * @param index 배열인덱스
	 * @param def 기본값
	 * @return Object 내용
	 * @see #toString(Object, String)
	 */
	public static String toString(Object[] array, int index, String def) {
		if (array == null || array.length <= index) {
			return def;
		}
		return array[index].toString();
	}

	/**
	 * Object 배열의 인덱스에 해당하는 {@link #toString()} 메소드 실행 함수.
	 * @param array Object 배열
	 * @param index 배열인덱스
	 * @return Object 내용
	 * @see #toString(Object[], int, String)
	 */
	public static String toString(Object[] array, int index) {
		return toString(array, index, null);
	}

	/**
	 * 데이타 구분자(예:'=')을 기준으로 키와 값으로 분리하여 배열로 반환.
	 * <pre>
	 * StringUtil.parseData(null, '=') = []
	 * StringUtil.parseData("  ", '=') = []
	 * StringUtil.parseData("key = 123", null)  = ["key", "123"]
	 * StringUtil.parseData("key = =123", null) = ["key", "=123"]
	 * </pre>
	 * @param str 원본문자열
	 * @param dataDelim 데이타 구분자
	 * @return 배열[키/값]
	 */
	public static String[] parseData(String str, char dataDelim) {
		return parseData(str, dataDelim, true);
	}

	/**
	 * '='을 기준으로 키와 값으로 분리하여 배열로 반환.
	 * <pre>
	 * StringUtil.parseData(null) = null
	 * StringUtil.parseData("  ") = null
	 * StringUtil.parseData("key = 123")  = ["key", "123"]
	 * StringUtil.parseData("key = =123") = ["key", "=123"]
	 * </pre>
	 * @param str 원본문자열
	 * @return 배열[키/값]
	 * @see #parseData(String, char)
	 */
	public static String[] parseData(String str) {
		return parseData(str, '=');
	}


	/**
	 * 데이타 구분자(예:'=')을 기준으로 키와 값으로 분리하여 배열로 반환.
	 * <pre>
	 * StringUtil.parseData(null, '=', true) = []
	 * StringUtil.parseData("  ", '=', true) = []
	 * StringUtil.parseData("key = 123", null, true)  = ["key", "123"]
	 * StringUtil.parseData("key = =123", null, true) = ["key", "=123"]
	 * </pre>
	 * @param str 원본문자열
	 * @param dataDelim 데이타 구분자
	 * @param trim 트림여부
	 * @return 배열[키/값]
	 */
	public static String[] parseData(String str, char dataDelim, boolean trim) {
		if (!isNullOrEmpty(str)) {
			int idx = str.indexOf(dataDelim);
			if (idx > -1) {
				String key = str.substring(0, idx);
				if (trim) {
					key = key.trim();
				}
				++idx;
				String value;
				if (str.length() > idx) {
					value = str.substring(idx);
					if (trim) {
						value = value.trim();
					}
				} else {
					value = EMPTY;
				}
				return new String[] { key, value };
			}
		}
		return new String[0];
	}

	/**
	 * '='을 기준으로 키와 값으로 분리하여 배열로 반환.
	 * <pre>
	 * StringUtil.parseData(null, true) = null
	 * StringUtil.parseData("  ", true) = null
	 * StringUtil.parseData("key = 123", true)  = ["key", "123"]
	 * StringUtil.parseData("key = =123", true) = ["key", "=123"]
	 * </pre>
	 * @param str 원본문자열
	 * @param trim 트림여부
	 * @return 배열[키/값]
	 * @see #parseData(String, char)
	 */
	public static String[] parseData(String str, boolean trim) {
		return parseData(str, '=', trim);
	}

	/**
	 * 문자열을 boolean 형으로 변환.
	 * <pre>
	 * String str = "true";
	 * boolean rtn = StringUtil.parseBoolean(str,true);
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  boolean ("true","on","yes","y" 일때만 true)
	 */
	public static boolean parseBoolean(String str, boolean def) {
		if (isNullOrEmpty(str)) {
			return def;
		}
		str = str.trim();
		if (str.equalsIgnoreCase("true")
				|| str.equalsIgnoreCase("on")
				|| str.equalsIgnoreCase("yes")
				|| str.equalsIgnoreCase("y")) {
			return true;
		}
		return false;
	}

	/**
	 * 문자열을 boolean 형으로 변환.
	 * <pre>
	 * String str = "true";
	 * boolean rtn = StringUtil.parseBoolean(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  boolean ("true","on","yes","y" 일때만 true)
	 */
	public static boolean parseBoolean(String str) {
		return parseBoolean(str, false);
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str) {
		return parseBytes(str, (String) null, null);
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str, "EUC-KR");
	 * </pre>
	 * @param   str		원본 문자열
	 * @param   charset	supported character encoding
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str, String charset) {
		return parseBytes(str, charset, null);
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str, "EUC-KR", new byte[]{0});
	 * </pre>
	 * @param   str		원본 문자열
	 * @param   charset	supported character encoding
	 * @param   def		변환이 실패할 경우의 기본값
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str, String charset, byte[] def) {
		if (str == null) {
			return def;
		}
		try {
			return str.getBytes(Charsets.toCharset(charset));
		} catch (Throwable ignore) {
			return def;
		}
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str, new byte[]{0});
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str, byte[] def) {
		return parseBytes(str, (Charset) null, def);
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str, "EUC-KR");
	 * </pre>
	 * @param   str			원본 문자열
	 * @param   charset		supported character encoding
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str, Charset charset) {
		return parseBytes(str, charset, null);
	}

	/**
	 * 문자열을 byte[] 형으로 변환.
	 * <pre>
	 * String str = "1000";
	 * byte[] rtn = StringUtil.parseBytes(str, "EUC-KR", new byte[]{0});
	 * </pre>
	 * @param   str			원본 문자열
	 * @param   charset		supported character encoding
	 * @param   def			변환이 실패할 경우의 기본값
	 * @return  byte[]
	 */
	public static byte[] parseBytes(String str, Charset charset, byte[] def) {
		if (str == null) {
			return def;
		}
		try {
			return str.getBytes(Charsets.toCharset(charset));
		} catch (Throwable ignore) {
			return def;
		}
	}

	/**
	 * 문자열을 double 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * double rtn = StringUtil.parseDouble(str,0.0d);
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  double
	 */
	public static double parseDouble(String str, double def) {
		if (isNullOrEmpty(str)) {
			return def;
		}
		try {
			return Double.parseDouble(removeCommaNotNull(str));
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 문자열을 double 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * double rtn = StringUtil.parseDouble(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  double
	 */
	public static double parseDouble(String str) {
		return parseDouble(str, 0.0d);
	}

	/**
	 * 문자열을 float 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * float rtn = StringUtil.parseFloat(str,0.0f);
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  float
	 */
	public static float parseFloat(String str, float def) {
		if (isNullOrEmpty(str)) {
			return def;
		}
		try {
			return Float.parseFloat(removeCommaNotNull(str));
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 문자열을 float 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * float rtn = StringUtil.parseFloat(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  float
	 */
	public static float parseFloat(String str) {
		return parseFloat(str, 0.0f);
	}

	/**
	 * 문자열을 long 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * long rtn = StringUtil.parseLong(str,0L);
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  long
	 */
	public static long parseLong(String str, long def) {
		if (isNullOrEmpty(str)) {
			return def;
		}
		str = str.trim();
		try {
			return Long.parseLong(removeCommaNotNull(str));
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 문자열을 long 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * long rtn = StringUtil.parseLong(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  long
	 */
	public static long parseLong(String str) {
		return parseLong(str, 0L);
	}

	/**
	 * 문자열을 int 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * int rtn = StringUtil.parseInt(str,0);
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  int
	 */
	public static int parseInt(String str, int def) {
		if (isNullOrEmpty(str)) {
			return def;
		}
		str = str.trim();
		try {
			return Integer.parseInt(removeCommaNotNull(str));
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 문자열을 int 형으로 변환(콤마는 자동제거).
	 * <pre>
	 * String str = "1,000";
	 * int rtn = StringUtil.parseInt(str);
	 * </pre>
	 * @param   str     원본 문자열
	 * @return  int
	 */
	public static int parseInt(String str) {
		return parseInt(str, 0);
	}

	/**
	 * 문자열을 금액형으로 변환(콤마는 자동제거).
	 * <pre>
	 * java.text.NumberFormat을 이용한 소수점 3자리 금액 표현,
	 * String str = "12345";
	 * String rtn = StringUtil.parseMoney(str,"0");
	 * ( rtn == "12,345" )
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   def     변환이 실패할 경우의 기본값
	 * @return  3자리 콤마
	 */
	public static String parseMoney(String str, String def) {
		try {
			double val = parseDouble(str, 0.0d);
			return NumberUtil.NUMBER_FORMAT.format(val);
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 문자열을 지정한 분리자에 의해 리스트 객체로 리턴하는 함수.
	 * @param str 문자열
	 * @param delim 분리자
	 * @param trim 트림여부
	 * @return
	 */
	private static List<String> splitListInternal(String str, String delim, boolean trim) {
		List<String> array = new ArrayList<String>();
		String token;
		int pos;
		do {
			pos = str.indexOf(delim);
			if (pos >= 0) {
				token = str.substring(0, pos);
				str = str.substring(pos + 1);
			} else {
				token = str;
			}
			array.add(trim ? token.trim() : token);
		} while (pos >= 0);
		return array;
	}

	/**
	 * 문자열을 지정한 분리자에 의해 리스트 객체로 리턴하는 함수(값을 trim 함).
	 * <pre>
	 * StringUtil.splitList(null, "-") == []
	 * StringUtil.splitList("", "-") == []
	 * StringUtil.splitList("2002-01-20", "-") == [2002, 01, 20]
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @return  분리자로 나뉘어진 리스트 객체
	 */
	public static List<String> splitList(String str, String delim) {
		return splitList(str, delim, true);
	}

	/**
	 * 문자열을 지정한 분리자에 의해 리스트 객체로 리턴하는 함수.
	 * <pre>
	 * StringUtil.splitList(null, "-") == []
	 * StringUtil.splitList("", "-") == []
	 * StringUtil.splitList("2002-01-20", "-") == [2002, 01, 20]
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @return  분리자로 나뉘어진 리스트 객체
	 */
	public static List<String> splitList(String str, String delim, boolean trim) {
		if (isNullOrEmpty(str)) {
			return new ArrayList<String>();
		}
		return splitListInternal(str, delim, trim);
	}

	/**
	 * 문자열을 지정한 분리자에 의해  분리하고 데이타 구분자를 이용해서 맵 객체로 리턴하는 함수.
	 * @param str 문자열
	 * @param delim 분리자
	 * @param dataDelim 데이타 구분자
	 * @param trim 트림여부
	 * @return
	 */
	private static Map<String, String> splitMapInternal(String str, String delim, char dataDelim, boolean trim) {
		Map<String, String> map = new HashMap<String, String>();
		List<String> list = splitListInternal(str, delim, false);
		for (String token : list) {
			int idx = token.indexOf(dataDelim);
			if (idx > -1) {
				String key = token.substring(0, idx);
				if (trim) {
					key = key.trim();
				}
				++idx;
				String value;
				if (token.length() > idx) {
					value = token.substring(idx);
					if (trim) {
						value = value.trim();
					}
				} else {
					value = EMPTY;
				}
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 문자열을 지정한 분리자에 의해  분리하고 데이타 구분자("=")를 이용해서 맵 객체로 리턴하는 함수(키와 값을 trim 함).
	 * <pre>
	 * StringUtil.splitMap(null, "&") == {}
	 * StringUtil.splitMap("", "&") == {}
	 * StringUtil.splitMap("dd=01&MM=01&yyyy=2000", "&") == {dd=01, yyyy=2000, MM=01}
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @return  맵 객체
	 */
	public static Map<String, String> splitMap(String str, String delim) {
		return splitMap(str, delim, true);
	}

	/**
	 * 문자열을 지정한 분리자에 의해  분리하고 데이타 구분자("=")를 이용해서 맵 객체로 리턴하는 함수.
	 * <pre>
	 * StringUtil.splitMap(null, "&") == {}
	 * StringUtil.splitMap("", "&") == {}
	 * StringUtil.splitMap("dd=01&MM=01&yyyy=2000", "&") == {dd=01, yyyy=2000, MM=01}
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   trim 트림여부
	 * @return  맵 객체
	 */
	public static Map<String, String> splitMap(String str, String delim, boolean trim) {
		if (isNullOrEmpty(str)) {
			return new HashMap<String, String>();
		}
		return splitMapInternal(str, delim, '=', trim);
	}

	/**
	 * 문자열을 지정한 분리자에 의해  분리하고 데이타 구분자를 이용해서 맵 객체로 리턴하는 함수(키와 값을 trim 함).
	 * <pre>
	 * StringUtil.splitMap(null, "&", '=') == {}
	 * StringUtil.splitMap("", "&", '=') == {}
	 * StringUtil.splitMap("dd=01&MM=01&yyyy=2000", "&", '=') == {dd=01, yyyy=2000, MM=01}
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   dataDelim 데이타 구분자
	 * @return  맵 객체
	 */
	public static Map<String, String> splitMap(String str, String delim, char dataDelim) {
		return splitMap(str, delim, dataDelim, true);
	}

	/**
	 * 문자열을 지정한 분리자에 의해  분리하고 데이타 구분자를 이용해서 맵 객체로 리턴하는 함수.
	 * <pre>
	 * StringUtil.splitMap(null, "&", '=') == {}
	 * StringUtil.splitMap("", "&", '=') == {}
	 * StringUtil.splitMap("dd=01&MM=01&yyyy=2000", "&", '=') == {dd=01, yyyy=2000, MM=01}
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   dataDelim 데이타 구분자
	 * @param   trim 트림여부
	 * @return  맵 객체
	 */
	public static Map<String, String> splitMap(String str, String delim, char dataDelim, boolean trim) {
		if (isNullOrEmpty(str)) {
			return new HashMap<String, String>();
		}
		return splitMapInternal(str, delim, dataDelim, trim);
	}

	/**
	 * 문자열을 지정한 분리자에 의해 배열로 리턴하는 함수(값을 trim 함).
	 * <pre>
	 * String[] rst = StringUtil.split("2002-01-20","-");
	 * 결과 rst[0] = 2002, rst[1] = 01, rst[2] = 20
	 *
	 * String[] rst = StringUtil.split("20020120","-");
	 * 결과 rst[0] = 20020120
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @return  분리자로 나뉘어진 문자열배열 or null
	 */
	public static String[] split(String str, String delim) {
		return split(str, delim, true);
	}

	/**
	 * 문자열을 지정한 분리자에 의해 배열로 리턴하는 함수.
	 * <pre>
	 * String[] rst = StringUtil.split("2002-01-20","-");
	 * 결과 rst[0] = 2002, rst[1] = 01, rst[2] = 20
	 *
	 * String[] rst = StringUtil.split("20020120","-");
	 * 결과 rst[0] = 20020120
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   trim 트림여부
	 * @return  분리자로 나뉘어진 문자열배열 or null
	 */
	public static String[] split(String str, String delim, boolean trim) {
		if (isNullOrEmpty(str)) {
			return new String[0];
		}
		return splitListInternal(str, delim, trim).toArray(new String[0]);
	}

	/**
	 * 문자열을 지정한 분리자에 의해 지정한 길이의 배열로 리턴하는 함수(값을 trim 함).
	 * <pre>
	 * String[] rst = StringUtil.split("2002-01-20","-",2);
	 * 결과 rst[0] = 2002, rst[1] = 01
	 *
	 * String[] rst = StringUtil.split("20020120","-",3);
	 * 결과 rst[0] = 20020120, rst[1]="", rst[2]=""
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   len     최소배열길이
	 * @return  분리자로 나뉘어진 문자열배열 or null
	 */
	public static String[] split(String str, String delim, int len) {
		return split(str, delim, len, true);
	}

	/**
	 * 문자열을 지정한 분리자에 의해 지정한 길이의 배열로 리턴하는 함수.
	 * <pre>
	 * String[] rst = StringUtil.split("2002-01-20","-",2);
	 * 결과 rst[0] = 2002, rst[1] = 01
	 *
	 * String[] rst = StringUtil.split("20020120","-",3);
	 * 결과 rst[0] = 20020120, rst[1]="", rst[2]=""
	 * </pre>
	 * @param   str     원본 문자열
	 * @param   delim   분리자
	 * @param   len     최소배열길이
	 * @param   trim    트림여부
	 * @return  분리자로 나뉘어진 문자열배열 or null
	 */
	public static String[] split(String str, String delim, int len, boolean trim) {
		if (len < 0) {
			throw new StringIndexOutOfBoundsException(len);
		}
		List<String> list = splitListInternal(nvl(str), delim, trim);
		int listSize = list.size();
		if (listSize == len) {
			return list.toArray(new String[0]);
		}
		String[] rtn = new String[len];
		for (int ii = 0; ii < rtn.length; ++ii) {
			if (ii < listSize) {
				rtn[ii] = list.get(ii);
			} else {
				rtn[ii] = EMPTY;
			}
		}
		return rtn;
	}

	/**
	 * 문자에 정해진 길이마다 분리자를 추가하는 함수.
	 * <pre>
	 * StringUtil.splitByLength(null, "\n", 2) == ""
	 * StringUtil.splitByLength("", "\n", 2) == ""
	 * StringUtil.splitByLength("112233", "\n", 2) == "11\n22\n33"
	 * </pre>
	 * @param str      원본 문자열
	 * @param delim    분리자
	 * @param length   분리길이
	 * @return 합친 문자열
	 */
	public static String joinByLength(String str, String delim, int length) {
		if (isNullOrEmpty(str)) {
			return EMPTY;
		}
		if (delim == null) {
			delim = EMPTY;
		}
		int size = str.length();
		int count = size / length;
		if (count == 0) {
			return str;
		}
		int loop = 0;
		StringBuilder buff = new StringBuilder();
		for (int ii = 0; ii < count; ++ii) {
			buff.append(str.substring(loop, loop + length)).append(delim);
			loop += length;
		}
		return buff.append(str.substring(loop, size)).toString();
	}

	/**
	 * 배열을 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * <pre>
	 * test[0] = "2000", test[1] = "02", test[2] = "01"
	 * String rst = StringUtil.join(request.getParameterValues("test"), "-");
	 * 결과 rst = "2002-02-01"
	 * </pre>
	 * @param   objects     원본 배열
	 * @param   delim       분리자
	 * @return  분리자 합친 문자열
	 */
	public static String join(Object[] objects, String delim) {
		if (ObjectUtil.isEmpty(objects)) {
			return EMPTY;
		}
		if (delim == null) {
			delim = EMPTY;
		}
		StringBuilder buff = new StringBuilder();
		boolean appendDelim = false;
		for (Object object : objects) {
			if (appendDelim) {
				buff.append(delim);
			} else {
				appendDelim = true;
			}
			buff.append(toString(object, EMPTY));
		}
		return buff.toString();
	}

	/**
	 * Collection을 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * <pre>
	 * List<String> list = new ArrayList<String>();
	 * list.add("2000");
	 * list.add("01");
	 * list.add("01");
	 * String rst = StringUtil.join(list, "-");
	 * 결과 rst = "2002-02-01"
	 * </pre>
	 * @param collection Collection
	 * @param delim 분리자
	 * @return 분리자 합친 문자열
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Collection collection, String delim) {
		if (ObjectUtil.isEmpty(collection)) {
			return EMPTY;
		}
		if (delim == null) {
			delim = EMPTY;
		}
		StringBuilder buff = new StringBuilder();
		boolean appendDelim = false;
		for (Object object : collection) {
			if (appendDelim) {
				buff.append(delim);
			} else {
				appendDelim = true;
			}
			buff.append(toString(object, EMPTY));
		}
		return buff.toString();
	}

	/**
	 * 맵를 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * <pre>
	 * Map<String, String> map = new HashMap<String, String>();
	 * map.put("yyyy", "2000");
	 * map.put("MM", "01");
	 * map.put("dd", "01");
	 * String rst = StringUtil.join(map, "&");
	 * 결과 rst = dd=01&MM=01&yyyy=2000
	 * </pre>
	 * @param map 맵
	 * @param delim 분리자
	 * @return 분리자 합친 문자열
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Map map, String delim) {
		return join(map, delim, '=');
	}

	/**
	 * 맵를 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * <pre>
	 * Map<String, String> map = new HashMap<String, String>();
	 * map.put("yyyy", "2000");
	 * map.put("MM", "01");
	 * map.put("dd", "01");
	 * String rst = StringUtil.join(map, "&", '=');
	 * 결과 rst = dd=01&MM=01&yyyy=2000
	 * </pre>
	 * @param map 맵
	 * @param delim 분리자
	 * @param dataDelim 데이터 분리자
	 * @return 분리자 합친 문자열
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Map map, String delim, char dataDelim) {
		if (ObjectUtil.isEmpty(map)) {
			return EMPTY;
		}
		if (delim == null) {
			delim = EMPTY;
		}
		StringBuilder buff = new StringBuilder();
		boolean appendDelim = false;
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			if (appendDelim) {
				buff.append(delim);
			} else {
				appendDelim = true;
			}
			Object key = it.next();
			buff.append(toString(key, EMPTY))
				.append(dataDelim)
				.append(toString(map.get(key), EMPTY));
		}
		return buff.toString();
	}

	/**
	 * char 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @return 변환값
	 */
	public static String repeat(char format, int len) {
		char ch[] = new char[len];
		for (int ii = 0; ii < len; ++ii) {
			ch[ii] = format;
		}
		return new String(ch);
	}

	/**
	 * char(1byte) 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @return 변환값
	 */
	public static String repeatByte(char format, int len) {
		return repeatByte(format, len, Charset.defaultCharset());
	}

	/**
	 * char(1byte) 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String repeatByte(char format, int len, String charset) {
		return repeatByte(format, len, Charsets.toCharset(charset));
	}

	/**
	 * char(1byte) 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String repeatByte(char format, int len, Charset charset) {
		byte[] padBytes = String.valueOf(format).getBytes(charset);
		if (padBytes.length > 1) {
			throw new IllegalArgumentException("1byte char is not");
		}
		return repeatByte(padBytes[0], len, charset);
	}

	/**
	 * byte 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @return 변환값
	 */
	public static String repeatByte(byte format, int len) {
		return repeatByte(format, len, Charset.defaultCharset());
	}

	/**
	 * byte 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String repeatByte(byte format, int len, String charset) {
		return repeatByte(format, len, Charsets.toCharset(charset));
	}

	/**
	 * byte 를 지정한 만큼 채워서 반환.
	 * @param format
	 * @param len
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String repeatByte(byte format, int len, Charset charset) {
		byte[] buff = new byte[len];
		for (int ii = 0; ii < len; ++ii) {
			buff[ii] = format;
		}
		return new String(buff, charset);
	}

	/**
	 * char 를 지정한 길이만큼 왼쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.lpad(null, 4, '0')   = "0000"
	 * StringUtil.lpad("", 4, '0')     = "0000"
	 * StringUtil.lpad("test", 6, '0') = "00test"
	 * StringUtil.lpad("test", 2, '0') = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @return 변환값
	 */
	public static String lpad(String str, int len, char format) {
		if (str == null) {
			str = EMPTY;
		}
		int paddingLength = len - str.length();
		if (paddingLength > 0) {
			return repeat(format, paddingLength) + str;
		} else if (paddingLength < 0) {
			return str.substring(0, len);
		}
		return str;
	}

	/**
	 * char 를 지정한 길이만큼 오른쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.rpad(null, 4, '0')   = "0000"
	 * StringUtil.rpad("", 4, '0')     = "0000"
	 * StringUtil.rpad("test", 6, '0') = "test00"
	 * StringUtil.rpad("test", 2, '0') = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @return 변환값
	 */
	public static String rpad(String str, int len, char format) {
		if (str == null) {
			str = EMPTY;
		}
		int paddingLength = len - str.length();
		if (paddingLength > 0) {
			return str + repeat(format, paddingLength);
		} else if (paddingLength < 0) {
			return str.substring(0, len);
		}
		return str;
	}

	/**
	 * 지정한 길이만큼 문자열을 자름.
	 * <pre>
	 * StringUtil.cut(null, 4)   = null
	 * StringUtil.cut("", 4)     = ""
	 * StringUtil.cut("test", 2) = "te"
	 * StringUtil.cut("test", 5) = "test"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 길이
	 * @return	변환값
	 */
	public static String cut(String str, int len) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		if (str.length() > len) {
			return str.substring(0, len);
		}
		return str;
	}

	/**
	 * char 를 지정한 byte 길이만큼 왼쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.lpadB(null, 4, '0')   = "0000"
	 * StringUtil.lpadB("", 4, '0')     = "0000"
	 * StringUtil.lpadB("test", 6, '0') = "00test"
	 * StringUtil.lpadB("test", 2, '0') = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @return 변환값
	 */
	public static String lpadB(String str, int len, char format) {
		return lpadB(str, len, format, Charset.defaultCharset());
	}

	/**
	 * char 를 지정한 byte 길이만큼 왼쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.lpadB(null, 4, '0', "EUC-KR")   = "0000"
	 * StringUtil.lpadB("", 4, '0', "EUC-KR")     = "0000"
	 * StringUtil.lpadB("test", 6, '0', "EUC-KR") = "00test"
	 * StringUtil.lpadB("test", 2, '0', "EUC-KR") = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String lpadB(String str, int len, char format, String charset) {
		return lpadB(str, len, format, Charsets.toCharset(charset));
	}

	/**
	 * char 를 지정한 byte 길이만큼 왼쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.lpadB(null, 4, '0', charset)   = "0000"
	 * StringUtil.lpadB("", 4, '0', charset)     = "0000"
	 * StringUtil.lpadB("test", 6, '0', charset) = "00test"
	 * StringUtil.lpadB("test", 2, '0', charset) = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String lpadB(String str, int len, char format, Charset charset) {
		if (str == null) {
			str = EMPTY;
		}
		int total = 0;
		for (int ii = 0; ii < str.length(); ++ii) {
			int loopLen = total +
					String.valueOf(str.charAt(ii)).getBytes(charset).length;
			if (loopLen > len) {
				str = str.substring(0, ii);
				break;
			} else {
				total = loopLen;
			}
		}
		if (total == len) {
			return str;
		}
		return repeatByte(format, len - total, charset) + str;
	}

	/**
	 * char 를 지정한 byte 길이만큼 오른쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.rpadB(null, 4, '0')   = "0000"
	 * StringUtil.rpadB("", 4, '0')     = "0000"
	 * StringUtil.rpadB("test", 6, '0') = "test00"
	 * StringUtil.rpadB("test", 2, '0') = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @return 변환값
	 */
	public static String rpadB(String str, int len, char format) {
		return rpadB(str, len, format, Charset.defaultCharset());
	}

	/**
	 * char 를 지정한 byte 길이만큼 오른쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.rpadB(null, 4, '0', "EUC-KR")   = "0000"
	 * StringUtil.rpadB("", 4, '0', "EUC-KR")     = "0000"
	 * StringUtil.rpadB("test", 6, '0', "EUC-KR") = "test00"
	 * StringUtil.rpadB("test", 2, '0', "EUC-KR") = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String rpadB(String str, int len, char format, String charset) {
		return rpadB(str, len, format, Charsets.toCharset(charset));
	}

	/**
	 * char 를 지정한 byte 길이만큼 오른쪽으로 채움(자름).
	 * <pre>
	 * StringUtil.rpadB(null, 4, '0', charset)   = "0000"
	 * StringUtil.rpadB("", 4, '0', charset)     = "0000"
	 * StringUtil.rpadB("test", 6, '0', charset) = "test00"
	 * StringUtil.rpadB("test", 2, '0', charset) = "te"
	 * </pre>
	 * @param str 원본문자열
	 * @param len 채울길이
	 * @param format 채울문자
	 * @param charset 캐릭터세트
	 * @return 변환값
	 */
	public static String rpadB(String str, int len, char format, Charset charset) {
		if (str == null) {
			str = EMPTY;
		}
		int total = 0;
		for (int ii = 0; ii < str.length(); ++ii) {
			int loopLen = total +
					String.valueOf(str.charAt(ii)).getBytes(charset).length;
			if (loopLen > len) {
				str = str.substring(0, ii);
				break;
			} else {
				total = loopLen;
			}
		}
		if (total == len) {
			return str;
		}
		return str + repeatByte(format, len - total, charset);
	}

	/**
	 * 지정한 byte 길이만큼 문자열을 자름.
	 * <pre>
	 * StringUtil.cutB(null, 4)   = null
	 * StringUtil.cutB("", 4)     = ""
	 * StringUtil.cutB("test", 2) = "te"
	 * StringUtil.cutB("test", 5) = "test"
	 * </pre>
	 * @param str 원본문자열
	 * @param len byte 길이
	 * @return	변환값
	 */
	public static String cutB(String str, int len) {
		return cutB(str, len, Charset.defaultCharset());
	}

	/**
	 * 지정한 byte 길이만큼 문자열을 자름.
	 * <pre>
	 * StringUtil.cutB(null, 4, "EUC-KR")   = null
	 * StringUtil.cutB("", 4, "EUC-KR")     = ""
	 * StringUtil.cutB("test", 2, "EUC-KR") = "te"
	 * StringUtil.cutB("test", 5, "EUC-KR") = "test"
	 * </pre>
	 * @param str 원본문자열
	 * @param len byte 길이
	 * @param charset 캐릭터세트
	 * @return	변환값
	 */
	public static String cutB(String str, int len, String charset) {
		return cutB(str, len, Charsets.toCharset(charset));
	}

	/**
	 * 지정한 byte 길이만큼 문자열을 자름.
	 * <pre>
	 * StringUtil.cutB(null, 4, charset)   = null
	 * StringUtil.cutB("", 4, charset)     = ""
	 * StringUtil.cutB("test", 2, charset) = "te"
	 * StringUtil.cutB("test", 5, charset) = "test"
	 * </pre>
	 * @param str 원본문자열
	 * @param len byte 길이
	 * @param charset 캐릭터세트
	 * @return	변환값
	 */
	public static String cutB(String str, int len, Charset charset) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int total = 0;
		for (int ii = 0; ii < str.length(); ++ii) {
			total += String.valueOf(str.charAt(ii)).getBytes(charset).length;
			if (total > len) {
				return str.substring(0, ii);
			}
		}
		return str;
	}

	/**
	 * 첫번째 문자를 대문자 또는 소문자로 변경.<br>
	 * <pre>
	 * StringUtil.swapFirstLetterCase(null)   = null
	 * StringUtil.swapFirstLetterCase("")     = ""
	 * StringUtil.swapFirstLetterCase("Password") = "password"
	 * StringUtil.swapFirstLetterCase("password") = "Password"
	 * </pre>
	 * @param str 원본 문자열
	 * @return 변환값
	 */
	public static String swapFirstLetterCase(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		String first = str.substring(0, 1);
		if (Character.isLowerCase(first.charAt(0))) {
			return first.toUpperCase() + str.substring(1);
		}
		return first.toLowerCase() + str.substring(1);
	}

	/**
	 * 첫번째 문자를 대문자로 변경.<br>
	 * <pre>
	 * StringUtil.toUpperFirstLetterCase(null)   = null
	 * StringUtil.toUpperFirstLetterCase("")     = ""
	 * StringUtil.toUpperFirstLetterCase("Password") = "Password"
	 * StringUtil.toUpperFirstLetterCase("password") = "Password"
	 * </pre>
	 * @param str
	 * @return 변환값
	 */
	public static String toUpperFirstLetterCase(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 *첫번째 문자를 소문자로 변경.<br>
	 * <pre>
	 * StringUtil.toUpperFirstLetterCase(null)   = null
	 * StringUtil.toUpperFirstLetterCase("")     = ""
	 * StringUtil.toUpperFirstLetterCase("Password") = "password"
	 * StringUtil.toUpperFirstLetterCase("password") = "password"
	 * </pre>
	 * @param str
	 * @return 변환값
	 */
	public static String toLowerFirstLetterCase(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 문자열중 포함 문자배열에 속한 포함된 문자가 있는지  확인하는 함수.
	 * <pre>
	 * StringUtil.containsInvalidChars(null, 'a')             = false
	 * StringUtil.containsInvalidChars("a", null)             = false
	 * StringUtil.containsInvalidChars("", new char[] {'a'})  = false
	 * StringUtil.containsInvalidChars("ab", 'x', 'y', 'z')   = false
	 * StringUtil.containsInvalidChars("xbz", 'x', 'y', 'z')  = true
	 * </pre>
	 * @param str        원본 문자열
	 * @param invalidChars  포함 문자배열
	 * @return 포함문자가 있을 경우 true
	 */
	public static boolean containsInvalidChars(String str, char... invalidChars) {
		if (str == null || invalidChars == null) {
			return false;
		}
		int validSize = invalidChars.length;
		for (int ii = 0; ii < str.length(); ++ii) {
			char ch = str.charAt(ii);
			for (int jj = 0; jj < validSize; ++jj) {
				if (invalidChars[jj] == ch) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 문자열중 포함 문자열에 포함된 문자가 있는지  확인하는 함수.
	 * <pre>
	 * StringUtil.containsInvalidChars(null, "a")     = false
	 * StringUtil.containsInvalidChars("a", null)     = false
	 * StringUtil.containsInvalidChars("", "a")       = false
	 * StringUtil.containsInvalidChars("ab", "xyz")   = false
	 * StringUtil.containsInvalidChars("xbz", "xyz")  = true
	 * </pre>
	 * @param str        검증대상 문자열
	 * @param invalidChars  포함 문자열
	 * @return 포함문자가 있을 경우 true
	 */
	public static boolean containsInvalidChars(String str, String invalidChars) {
		if (str == null || invalidChars == null) {
			return true;
		}
		return containsInvalidChars(str, invalidChars.toCharArray());
	}

	/**
	 * 문자와 숫자 조합 문자열인지 확인하는 함수.
	 * <pre>
	 * StringUtil.isLetterNumeric(null)   = false
	 * StringUtil.isLetterNumeric(&#034;&#034;)     = false
	 * StringUtil.isLetterNumeric(&#034;  &#034;)   = false
	 * StringUtil.isLetterNumeric(&#034;abc&#034;)  = true
	 * StringUtil.isLetterNumeric(&#034;ab c&#034;) = false
	 * StringUtil.isLetterNumeric(&#034;ab2c&#034;) = true
	 * StringUtil.isLetterNumeric(&#034;ab-c&#034;) = false
	 * </pre>
	 * @param str  원본 문자열
	 * @return boolean (문자와 숫자 조합 문자열일때만 true)
	 */
	public static boolean isLetterNumeric(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		for (int ii = 0; ii < str.length(); ++ii) {
			if (!Character.isLetterOrDigit(str.charAt(ii))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 문자만 포함하는 문자열인지 확인하는 함수.
	 * <pre>
	 * StringUtil.isLetter(null)   = false
	 * StringUtil.isLetter(&#034;&#034;)     = false
	 * StringUtil.isLetter(&#034;  &#034;)   = false
	 * StringUtil.isLetter(&#034;abc&#034;)  = true
	 * StringUtil.isLetter(&#034;ab2c&#034;) = false
	 * StringUtil.isLetter(&#034;ab-c&#034;) = false
	 * </pre>
	 * @param str  원본 문자열
	 * @return boolean (문자만 포함하는 문자열일때만 true)
	 */
	public static boolean isLetter(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		for (int ii = 0; ii < str.length(); ++ii) {
			if (!Character.isLetter(str.charAt(ii))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 숫자만 포함하는 문자열인지 확인하는 함수.
	 * <pre>
	 * StringUtil.isNumeric(null)   = false
	 * StringUtil.isNumeric(&#034;&#034;)     = false
	 * StringUtil.isNumeric(&#034;  &#034;)   = false
	 * StringUtil.isNumeric(&#034;123&#034;)  = true
	 * StringUtil.isNumeric(&#034;12 3&#034;) = false
	 * StringUtil.isNumeric(&#034;ab2c&#034;) = false
	 * StringUtil.isNumeric(&#034;12-3&#034;) = false
	 * StringUtil.isNumeric(&#034;12.3&#034;) = false
	 * </pre>
	 * @param str  원본 문자열
	 * @return boolean (숫자만 포함하는 문자열일때만 true)
	 */
	public static boolean isNumeric(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		for (int ii = 0; ii < str.length(); ++ii) {
			if (!Character.isDigit(str.charAt(ii))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 문자 순서를 역의 줄 순서의 순서로 치환하여 반환 {@link java.lang.StringBuilder#reverse()}.
	 * <pre>
	 * StringUtil.reverse(null)  = null
	 * StringUtil.reverse(&#034;&#034;)    = &#034;&#034;
	 * StringUtil.reverse(&#034;bat&#034;) = &#034;tab&#034;
	 * </pre>
	 * @param str  원본 문자열
	 * @return 변환값
	 */
	public static String reverse(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * 구분값을 기준으로 다음 문자를 대문자로 변환하고 구분값은 삭제하여 반환(Camel case name).
	 * <pre>
	 * StringUtil.convertToCamelCase(null, '_')    = null
	 * StringUtil.convertToCamelCase("", '_')      = ""
	 * StringUtil.convertToCamelCase("string_util", '_') = "stringUtil"
	 * </pre>
	 * @param  str 원본 문자열
	 * @param  posChar 구분값
	 * @return 변환값
	 */
	public static String convertToCamelCase(String str, char posChar) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		String allLower = str.toLowerCase();

		for (int ii = 0; ii < allLower.length(); ++ii) {
			char currentChar = allLower.charAt(ii);
			if (currentChar == posChar) {
				nextUpper = true;
			} else {
				if (nextUpper) {
					currentChar = Character.toUpperCase(currentChar);
					nextUpper = false;
				}
				result.append(currentChar);
			}
		}
		return result.toString();
	}

	/**
	 * '_' 기준으로 다음 문자를 대문자로 변환하고 '_' 삭제하여 반환(Camel case name).
	 * <pre>
	 * StringUtil.convertToCamelCase(null)    = null
	 * StringUtil.convertToCamelCase("")      = ""
	 * StringUtil.convertToCamelCase("string_util") = "stringUtil"
	 * </pre>
	 * @param  str 원본 문자열
	 * @return 변환값
	 */
	public static String convertToCamelCase(String str) {
		return convertToCamelCase(str, '_');
	}

	/**
	 * 대문자 이전 위치에 '_'를 삽입하고 소문자로 변환하여 반환.
	 * <pre>
	 * StringUtil.convertToUnderScore(null)    = null
	 * StringUtil.convertToUnderScore("")      = ""
	 * StringUtil.convertToUnderScore("string_util") = "stringUtil"
	 * </pre>
	 * @param str 원본 문자열(Camel case name).
	 * @return 변환값.
	 */
	public static String convertToUnderScore(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		String result = EMPTY;
		for (int ii = 0; ii < str.length(); ++ii) {
			char currentChar = str.charAt(ii);
			if (ii > 0 && Character.isUpperCase(currentChar)) {
				result = result.concat("_");
			}
			result = result.concat(Character.toString(currentChar).toLowerCase());
		}
		return result;
	}

	private static String toEscapeString(char c) {
		if (c < XML_ESCAPES.length) {
			return XML_ESCAPES[c];
		} else {
			return null;
		}
	}

	/**
	 * XML 마크업으로 해석될 수있는 문자 이스케이프 처리.
	 * <pre>
	 * StringUtil.escapeXml(null)   = null
	 * StringUtil.escapeXml("")     = ""
	 * StringUtil.escapeXml("test") = "test"
	 * StringUtil.escapeXml("'<a href=\"#\">링크</a>'") = "&amp;#039;&amp;lt;a href=&amp;#034;#&amp;#034;&amp;gt;링크&amp;lt;/a&amp;gt;&amp;#039;"
	 * </pre>
	 * @param str 원본문자열
	 * @return 변환된 문자열
	 */
	public static String escapeXml(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int strLen = str.length();
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (toEscapeString(chr) != null) {
				StringBuilder buff = new StringBuilder(strLen + 30);
				buff.append(str.substring(0, ii));
				for (int jj = ii; jj < strLen; ++jj) {
					chr = str.charAt(jj);
					String escape = toEscapeString(chr);
					if (escape != null) {
						buff.append(escape);
					} else {
						buff.append(chr);
					}
				}
				return buff.toString();
			}
		}
		return str;
	}

	private static String toXSSRemoveString(char c) {
		if (c < XSS_REMOVESET.length) {
			return XSS_REMOVESET[c];
		} else {
			return null;
		}
	}

	/**
	 * XSS에 이용되는 문자 이스케이프(<>&) 또는 공백("') 처리.
	 * <pre>
	 * StringUtil.removeXSS(null)   = null
	 * StringUtil.removeXSS("")     = ""
	 * StringUtil.removeXSS("test") = "test"
	 * StringUtil.removeXSS("'<a href=\"#\">링크</a>'") = "&nbsp;&amp;lt;a href=&nbsp;#&nbsp;&amp;gt;링크&amp;lt;/a&amp;gt;&nbsp;"
	 * </pre>
	 * @param str 원본문자열
	 * @return 변환된 문자열
	 */
	public static String removeXSS(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int strLen = str.length();
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (toEscapeString(chr) != null) {
				StringBuilder buff = new StringBuilder(strLen + 30);
				buff.append(str.substring(0, ii));
				for (int jj = ii; jj < strLen; ++jj) {
					chr = str.charAt(jj);
					String remove = toXSSRemoveString(chr);
					if (remove != null) {
						buff.append(remove);
					} else {
						String escape = toEscapeString(chr);
						if (escape != null) {
							buff.append(escape);
						} else {
							buff.append(chr);
						}
					}
				}
				return buff.toString();
			}
		}
		return str;
	}
	
	/**
	 * 따옴표 엔터값 등등 제거시 사용 
	 * @param value
	 * @return
	 */
	public static String removeXSSJS(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		String returnValue = value;

		returnValue = returnValue.replaceAll("\"", "");
		returnValue = returnValue.replaceAll("\'", "");

		returnValue = returnValue.replaceAll("\r\n", "");
		returnValue = returnValue.replaceAll("\n", "");
		returnValue = returnValue.replaceAll("\r", "");

		return returnValue;
	}
	
	/**
	 * 문자열에 있는 태그를 제거하여 문자열을 반환한다.
	 * @param String
	 * @return String
	 */
	public static String removeTagScript(String content) {
		Pattern SCRIPTS = Pattern.compile("(?i)\\<script(.*?)</script>",Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",Pattern.DOTALL);
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");
		Matcher m;
		m = SCRIPTS.matcher(content);
		content = m.replaceAll("");
		m = STYLE.matcher(content);
		content = m.replaceAll("");
		content = content.replaceAll("\\<.*?\\>", "");
		m = ENTITY_REFS.matcher(content);
		content = m.replaceAll("");
		m = WHITESPACE.matcher(content);
		content = m.replaceAll(" ");
		return content;
	}

	/**
	* 반각문자로 변경한다.
	* @param str 변경할값
	* @return String 변경된값
	*/
	public static String toHalfChar(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int strLen = str.length();
		StringBuilder buff = new StringBuilder(strLen);
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (chr >= '！' && chr <= '～') { //영문이거나 특수 문자 일경우.
				chr -= 0xfee0;
			} else if (chr == '　') { //공백
				chr = 0x20;
			}
			buff.append(chr);
		}
		return buff.toString();
	}

	/**
	* 전각문자로 변경한다.
	* @param str 변경할값
	* @return String 변경된값
	*/
	public static String toFullChar(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		int strLen = str.length();
		StringBuilder buff = new StringBuilder(strLen);
		for (int ii = 0; ii < strLen; ++ii) {
			char chr = str.charAt(ii);
			if (chr >= 0x21 && chr <= 0x7e) { //영문이거나 특수 문자 일경우.
				chr += 0xfee0;
			} else if (chr == 0x20) { //공백
				chr = 0x3000;
			}
			buff.append(chr);
		}
		return buff.toString();
	}
	
	/**
	 * 이메일 유효성 체크
	 * @param String
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		if(isNullOrBlank(str)) return false;
		
		boolean chk = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", str.trim());
		
		return chk;
	}
	
	/**
	 * 문자열 바이트 수 계산
	 * @param String
	 * @return int
	 */
	public static int getByte(String str) {
		return str.getBytes().length;
	}
	
	/**
	 * 태그 제거후 자르기
	 * @param String, int, String
	 * @return String
	 */
	public static String getStringNoTag(String src, int len, String tail){
		if(src==null){
     		return "";
     	}
		src = removeTagScript(src);
		src = src.replaceAll("&nbsp;", "");
		src = src.replaceAll("&nbsp", "");
		return getString(src,len,tail);
	}
	
	/**
	 * 특수 문자와 영문 한글 체크 해서 길이를 가지고 온다.
	 * @param String, int, String
	 * @return String
	 */
	public static String getString(String src, int len, String tail){
     	if(src==null){
     		return "";
     	}
         float rstLen=0;
         String rst="";
         char c[]=src.toCharArray();
         int i=0;
         for (i = 0; i < c.length; i++) {
             if (c[i] == 60) { /* < 시작하는거 체크*/
                 rstLen += 1;
                 rst+=src.substring(i,i+1);
             } else if ((byte)c[i] == 62) { /* >끝나는거 체크*/
                 rstLen += 1;
                 rst+=src.substring(i,i+1);
             } else if ((int) src.charAt(i) >255) { /* 한글로 시작하는 부분 체크 */
                 rstLen += 1.21;
                 rst+=src.substring(i,i+1);
             } else if ((byte)c[i] >= 97 && (byte)c[i] <= 122) { /* 영문(소문자)으로 시작하는 부분 체크 */
                 rstLen += 0.47;
                 rst+=src.substring(i,i+1);
             } else if ((byte)c[i] >= 65 && (byte)c[i] <= 90) { /* 영문(대문자)으로 시작하는 부분 체크 */
                 rstLen += 0.67;
                 rst+=src.substring(i,i+1);
             } else if ((byte)c[i] >= 48 && (byte)c[i] <= 57) { /* 숫자 인지 체크 */
                 rstLen += 0.45;
                 rst+=src.substring(i,i+1);
             } else { /* 특수 문자 기호값 */
                 rstLen += 0.71;
                 rst+=src.substring(i,i+1);
             }

             if (rstLen >= len) {
                 rst+=tail;
                 break;
             }
         }
         return rst;
     }
	
	/**
	 * 파라미터의 값 중 숫자만 남기고 다른 문자열을 제거한다.
	 * @param String
	 * @return String
	 */
	public static String remainNumber(String str) {
		String num = str.replaceAll("[^0-9]", "");
		return num;
	}
	
	/**
	 * 랜덤 문자 생성
	 * @param int
	 * @return String
	 */
	public static String randomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<length; i++) {
			if(random.nextBoolean()) {
				sb.append((char)((int)(random.nextInt(26)) + 97));
			} else {
				sb.append((random.nextInt(10)));
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 두 문자열을 비교하여 같으면 true 다르면 false를 반환한다.
	 * @param String, String
	 * @return boolean
	 */
	public static boolean isEquals(String src1, String src2) {
		return src1.equals(src2);
	}
	
	public static String isEquals(String a, String b, String success, String fail) {
		if(StringUtil.isEquals(a, b)) {
			return success;
		} else {
			return fail;
		}
	}
	
	/**
	 * RTF String을 일반 String으로 반환한다.
	 * @param String
	 * @return String
	 */
	public static String rtfToString(String str) {
		byte[] bytes = null;
		bytes = str.getBytes();
		InputStream is = new ByteArrayInputStream(bytes);
		String convertString = null;
		
		try {
			RTFEditorKit kit = new RTFEditorKit();
			Document doc = kit.createDefaultDocument();
			kit.read(is, doc, 0);
			String plainText = doc.getText(0, doc.getLength());
			convertString = new String(plainText.getBytes("8859_1"), "KSC5601");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertString;
	}
	
	/**
	 * 사업자번호를 파라미터로 받아 사업자등록번호의 유효성 체크 및 개인, 법인 구분을 한다.
	 * @param String
	 * @return int
	 */
	public static int bizNoChk(String bizNo) {
		int chk = 0;

		bizNo = bizNo.replaceAll("-", "");
		bizNo = removeBlank(bizNo);
		
		if(bizNo.length() != 10) return 0;
		
		String sCode = "137137135";
		
		int sum = 0;
		
		for(int i=0; i<9; i++) {
			sum += Integer.parseInt(String.valueOf(bizNo.charAt(i))) * Integer.parseInt(String.valueOf(sCode.charAt(i)));
		}
		
		sum += Integer.parseInt(String.valueOf(bizNo.charAt(8))) * 5 / 10;
		
		int sid = sum % 10;
		int sidChk = (sid != 0) ? 10 - sid : 0;
		int chkVal = Integer.parseInt(String.valueOf(bizNo.charAt(9)));
		
		if(sidChk != chkVal) return 0;
		
		//----------------------------------------------------------------
		int officeCd = Integer.parseInt(String.valueOf(bizNo.charAt(0)));
		String officeNm = null;
		
		if(officeCd == 1) {
			officeNm = "서울지방국세청";
		} else if(officeCd == 2) {
			officeNm = "중부지방국세청";
		} else if(officeCd == 3) {
			officeNm = "대전지방국세청";
		} else if(officeCd == 4) {
			officeNm = "광주지방국세청";
		} else if(officeCd == 5) {
			officeNm = "대구지방국세청";
		} else if(officeCd == 6) {
			officeNm = "부산지방국세청";
		} else {
			officeNm = "기타";
		}
		//----------------------------------------------------------------
		
		int gubunCd = Integer.parseInt(bizNo.substring(3, 5));
		
		if((gubunCd >= 1 && gubunCd <= 79) || (gubunCd >= 90 && gubunCd <= 99) || gubunCd == 89 || gubunCd == 80) {
			System.out.println(officeNm + " : 개인사업자");
			chk = 4;
		} else if(gubunCd == 81 || (gubunCd >= 86 && gubunCd <= 88) || gubunCd == 82 || gubunCd == 83 || gubunCd == 84 || gubunCd == 85) {
			System.out.println(officeNm + " : 법인사업자");
			chk = 2;
		} else {
			chk = 0;
		}
		
		return chk;
	}
	
	/**
	 * 전화번호를 마스킹 처리
	 * @param String
	 * @return String
	 */
	public static String maskTel(String tel) {
		String formatTel = "";
		String maskTel = "";
		String origTel = tel;
		
		if(StringUtil.isNullOrBlank(tel)) {
			return origTel;
		}
		
		tel = tel.replaceAll("\\D", "");
		
		try {
			if (tel.length() == 8) {
				formatTel = tel.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
			} else if (tel.length() == 12) {
				formatTel = tel.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
				maskTel = formatTel.split("-")[0] + "-****-" + formatTel.split("-")[2];
			} else {
				formatTel = tel.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
				maskTel = formatTel.split("-")[0] + "-";
				
				for(int i=0; i<formatTel.split("-")[1].length(); i++) {
					maskTel += "*";
				}
				maskTel += "-" + formatTel.split("-")[2];
			}
		} catch (Exception e) {
			return origTel;
		}
		
		return maskTel;
	}
	
	/**
	 * 휴대폰 번호 마스킹 처리
	 * @param String
	 * @return String
	 * @see 휴대폰 번호가 아닌 경우 ERR를 반환한다.
	 */
	public static String maskPhone(String phone) {
		if(phone == null || "".equals(phone)) return phone;
		
		String maskPhone = "";
		String origPhone = phone;
		phone = phone.replaceAll("\\D", "");
		
		if(phone.length() == 10 || phone.length() == 11) {
			maskPhone = phone.substring(0,  3);

			if(phone.length() == 10) {
				maskPhone += "-***-";
				maskPhone += phone.substring(6, 10);
			} else if (phone.length() == 11) {
				maskPhone += "-****-";
				maskPhone += phone.substring(7, 11);
			}
		} else {
			maskPhone = origPhone;
		}
		
		return maskPhone;
	}
	
	/**
	 * 카드번호 마스킹 처리
	 * @param String
	 * @return String
	 */
	public static String maskCard(String cardNo) {
		if(cardNo == null || cardNo.equals("")) return cardNo;
		
		String maskCardNo = "";
		String origCardNo = cardNo;
		cardNo = cardNo.replaceAll("\\D", "");
		
		if(cardNo.length() == 16) {
			maskCardNo = cardNo.substring(0, 4) + "-****-****-" + cardNo.substring(12, 16);
		} else {
			maskCardNo = origCardNo;
		}
		
		return maskCardNo;
	}
	
	/**
	 * 계좌 또는 카드번호 마스킹 처리
	 * @param String
	 * @return String
	 */
	public static String maskAccount(String account) {
		if(account == null || account.equals("")) return account;
		
		String maskAccount = "";
		String origAccount = account;
		
		account = account.replaceAll("\\D", "");
		
		if(account.length() < 9) {
			maskAccount = origAccount;
		} else {
			maskAccount = account.substring(0, 8);

			for(int i=9; i<account.length() + 1; i++) {
				maskAccount += "*";
			}
		}
		
		return maskAccount;
	}
	
	/**
	 * 주민번호 마스킹 처리
	 * @param String
	 * @return String
	 */
	public static String maskJuminNo(String juminNo) {
		if(juminNo == null || juminNo.equals("")) return juminNo;
		
		String maskJuminNo = "";
		String origJuminNo = juminNo;
		
		juminNo = juminNo.replaceAll("\\D", "");
		
		if(juminNo.length() != 13) {
			maskJuminNo = origJuminNo;
		} else {
			maskJuminNo = juminNo.substring(0, 6) + "-" + juminNo.substring(6, 7) + "******";
		}
		
		return maskJuminNo;
	}
	
	/**
	 * 주민등록번호 유효성 확인
	 * @param String
	 * @return boolean
	 */
	public static boolean isJuminNo(String str) {
		if(isNullOrBlank(str)) return false;
		
		String juminNo = str.replaceAll("\\D", "");
		
		if(juminNo.length() != 13) return false;
		
		String leftSid = juminNo.substring(0, 6);
        String rightSid = juminNo.substring(6, 13);
 
        int yy = Integer.parseInt(leftSid.substring(0, 2));
        int mm = Integer.parseInt(leftSid.substring(2, 4));
        int dd = Integer.parseInt(leftSid.substring(4, 6));
        
        if (yy > 99 || mm > 12 || mm < 1 || dd < 1 || dd > 31) return false;
 
        int digit1 = Integer.parseInt(leftSid.substring(0, 1)) * 2;
        int digit2 = Integer.parseInt(leftSid.substring(1, 2)) * 3;
        int digit3 = Integer.parseInt(leftSid.substring(2, 3)) * 4;
        int digit4 = Integer.parseInt(leftSid.substring(3, 4)) * 5;
        int digit5 = Integer.parseInt(leftSid.substring(4, 5)) * 6;
        int digit6 = Integer.parseInt(leftSid.substring(5, 6)) * 7;
 
        int digit7 = Integer.parseInt(rightSid.substring(0, 1)) * 8;
        int digit8 = Integer.parseInt(rightSid.substring(1, 2)) * 9;
        int digit9 = Integer.parseInt(rightSid.substring(2, 3)) * 2;
        int digit10 = Integer.parseInt(rightSid.substring(3, 4)) * 3;
        int digit11 = Integer.parseInt(rightSid.substring(4, 5)) * 4;
        int digit12 = Integer.parseInt(rightSid.substring(5, 6)) * 5;
 
        int last_digit = Integer.parseInt(rightSid.substring(6, 7));
 
        int error_verify = (digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + digit10 + digit11 + digit12) % 11;
 
        int sum_digit = 0;
        if (error_verify == 0) {
            sum_digit = 1;
        } else if (error_verify == 1) {
            sum_digit = 0;
        } else {
            sum_digit = 11 - error_verify;
        }

        if (last_digit == sum_digit) return true;
        return false;
	}
	
	/**
	 * 외국인등록번호 유효성 확인
	 * @param String
	 * @return boolean
	 */
	public static boolean isForeignNo(String str) {
		int odd = 0;
		int sum = 0;
		
		if(isNullOrBlank(str)) return false;
		
		String foreignNo = str.replaceAll("\\D", "");
		
		if(foreignNo.length() != 13) return false;
		
		int[] buf = new int[13];
		
		for(int i=0; i<13; i++) {
			buf[i] = Integer.parseInt(foreignNo.charAt(i) + "");
		}
		
		odd = buf[7] * 10 + buf[8];
		
		if(odd % 2 != 0) return false;
		if((buf[6] != 5) && (buf[6] != 6) && (buf[6] != 7) && (buf[6] != 8)) return false;
		
		int[] multipliers = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
		
		for(int i=0; i<12; i++) {
			sum += (buf[i] *= multipliers[i]);
		}
		sum = 11 - (sum % 11);
		
		if(sum >= 10) sum -= 10;
		sum += 2;
		if(sum >= 10) sum -= 10;
		if(sum != buf[12]) return false;
		return true;
	}
}