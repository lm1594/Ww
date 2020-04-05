package lkm.ww.comn.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.jexl2.JexlEngine;

/**
 * 숫자 유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public class NumberUtil {

	/** 기본 숫자 포맷 **/
	public static final DecimalFormat NUMBER_FORMAT;
	static {
		NUMBER_FORMAT = new DecimalFormat("#,##0.##");
		NUMBER_FORMAT.setGroupingUsed(true);
	}

	private static JexlEngine jexlEngine;

	/**
	 * EL 표현식을 이용해서java.lang.Number을 생성하여 반환한다.
	 * @param expression
	 * @return 변환값
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T evaluate(String expression) {
		if (jexlEngine == null) {
			initJexlEngine();
		}
		return (T) jexlEngine.createExpression(expression).evaluate(null);
	}

	private static synchronized void initJexlEngine() {
		if (jexlEngine == null) {
			jexlEngine = new JexlEngine();
		}
	}

	/**
	 * 숫자형(String, 콤마는 자동제거) 데이터를 문자열화 한다(포맷:#,##0.##).
	 * <pre>
	 * String strNum = NumberUtil.format("1234");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @return	3자리 콤마
	 */
	public static String format(String value) {
		value = StringUtil.removeCommaNotNull(value);
		return NUMBER_FORMAT.format(Double.parseDouble(value));
	}

	/**
	 * 숫자형(String, 콤마는 자동제거) 데이터를 문자열화 한다.
	 * <pre>
	 * String strNum = NumberUtil.format("1234","#,##0.##");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @param   pattern 숫자포맷
	 * @return	변환값
	 */
	public static String format(String value, String pattern) {
		value = StringUtil.removeCommaNotNull(value);
		return format(Double.parseDouble(value), pattern);
	}

	/**
	 * 숫자형(Number) 데이터를 문자열화 한다.
	 * <pre>
	 * String strNum = NumberUtil.format(Number,"#,##0.##");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @return	변환값
	 */
	public static String format(Number value) {
		return NUMBER_FORMAT.format(value);
	}

	/**
	 * 숫자형(Number) 데이터를 문자열화 한다.
	 * <pre>
	 * String strNum = NumberUtil.format(Number,"#,##0.##");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @param   pattern 숫자포맷
	 * @return	변환값
	 */
	public static String format(Number value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		formatter.setGroupingUsed(true);
		return formatter.format(value);
	}

	/**
	 * 숫자형(long) 데이터를 문자열화 한다(포맷:#,##0.##).
	 * <pre>
	 * String strNum = NumberUtil.format(1234L);
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @return	3자리 콤마
	 */
	public static String format(long value) {
		return NUMBER_FORMAT.format(value);
	}

	/**
	 * 숫자형(long) 데이터를 문자열화 한다.
	 * <pre>
	 * String strNum = NumberUtil.format(1234L,"#,##0.##");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @param   pattern 숫자포맷
	 * @return	변환값
	 */
	public static String format(long value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		formatter.setGroupingUsed(true);
		return formatter.format(value);
	}

	/**
	 * 숫자형(double) 데이터를 문자열화 한다(포맷:#,##0.##).
	 * <pre>
	 * String strNum = NumberUtil.format(1234D);
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @return	3자리 콤마
	 */
	public static String format(double value) {
		return NUMBER_FORMAT.format(value);
	}

	/**
	 * 숫자형(double) 데이터를 문자열화 한다.
	 * <pre>
	 * String strNum = NumberUtil.format(1234D,"#,##0.##");
	 * ( number == "1,234" )
	 * </pre>
	 * @param   value 원본값
	 * @param   pattern 숫자포맷
	 * @return	변환값
	 */
	public static String format(double value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		formatter.setGroupingUsed(true);
		return formatter.format(value);
	}

	/**
	 * 숫자 문자열이 숫자 패턴과 일치하는지 검증
	 * @param value 숫자 문자열
	 * @param pattern 숫자 패턴
	 * @return 숫자 포맷팅 검증값
	 */
	public static boolean isValid(String value, String pattern) {
		if (!StringUtil.isNullOrEmpty(value)) {
			try {
				DecimalFormat formatter = new DecimalFormat(pattern);
				formatter.setGroupingUsed(true);
				formatter.format(value);
				return true;
			} catch (Throwable ignore) {

			}
		}
		return false;
	}

	/**
	 * 지정한 길이만큼 반올림 하여 표시한다(콤마는 자동제거).
	 * <pre>
	 * NumberUtil.round("545.925", -2) = "500.0";
	 * NumberUtil.round("545.925", -1) = "550.0";
	 * NumberUtil.round("545.925", 0) = "546.0";
	 * NumberUtil.round("545.925", 1) = "545.9";
	 * NumberUtil.round("545.925", 2) = "545.92";
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double round(String value, int pos) {
		value = StringUtil.removeCommaNotNull(value);
		if (pos < 0) {
			return Math.round(Double.parseDouble(value) * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value.trim()).setScale(pos, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 지정한 길이만큼 반올림 하여 표시한다.
	 * <pre>
	 * NumberUtil.round(new BigDecimal(545.925), -2) = "500.0";
	 * NumberUtil.round(new BigDecimal(545.925), -1) = "550.0";
	 * NumberUtil.round(new BigDecimal(545.925), 0) = "546.0";
	 * NumberUtil.round(new BigDecimal(545.925), 1) = "545.9";
	 * NumberUtil.round(new BigDecimal(545.925), 2) = "545.92";
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double round(Number value, int pos) {
		return round(value.doubleValue(), pos);
	}

	/**
	 * 지정한 길이만큼 반올림 하여 표시한다.
	 * <pre>
	 * NumberUtil.round((long)545.925, -2) = "500.0";
	 * NumberUtil.round((long)545.925, -1) = "550.0";
	 * NumberUtil.round((long)545.925, 0) = "545.0";
	 * NumberUtil.round((long)545.925, 1) = "545.0";
	 * NumberUtil.round((long)545.925, 2) = "545.0";
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double round(long value, int pos) {
		return round((double)value, pos);
	}

	/**
	 * 지정한 길이만큼 반올림 하여 표시한다.
	 * <pre>
	 * NumberUtil.round(545.925, -2) = "500.0";
	 * NumberUtil.round(545.925, -1) = "550.0";
	 * NumberUtil.round(545.925, 0) = "546.0";
	 * NumberUtil.round(545.925, 1) = "545.9";
	 * NumberUtil.round(545.925, 2) = "545.93";
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double round(double value, int pos) {
		if (pos < 0) {
			return Math.round(value * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value).setScale(pos, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 지정한 길이만큼 절삭하여 표시한다(콤마는 자동제거).
	 * <pre>
	 * NumberUtil.floor("545.925", -2) = "500.0";
	 * NumberUtil.floor("545.925", -1) = "540.0";
	 * NumberUtil.floor("545.925", 0) = "545.0";
	 * NumberUtil.floor("545.925", 1) = "545.9";
	 * NumberUtil.floor("545.925", 2) = "545.92";
	 * </pre>
	 * @param value  원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double floor(String value, int pos) {
		value = StringUtil.removeCommaNotNull(value);
		if (pos < 0) {
			return Math.floor(Double.parseDouble(value) * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value.trim()).setScale(pos, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 지정한 길이만큼 절삭하여 표시한다.
	 * <pre>
	 * NumberUtil.floor(new BigDecimal(545.925), -2) = "500.0";
	 * NumberUtil.floor(new BigDecimal(545.925), -1) = "540.0";
	 * NumberUtil.floor(new BigDecimal(545.925), 0) = "545.0";
	 * NumberUtil.floor(new BigDecimal(545.925), 1) = "545.9";
	 * NumberUtil.floor(new BigDecimal(545.925), 2) = "545.92";
	 * </pre>
	 * @param value  원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double floor(Number value, int pos) {
		return floor(value.doubleValue(), pos);
	}

	/**
	 * 지정한 길이만큼 절삭하여 표시한다.
	 * <pre>
	 * NumberUtil.floor((long)545.925, -2) = "500.0";
	 * NumberUtil.floor((long)545.925, -1) = "540.0";
	 * NumberUtil.floor((long)545.925, 0) = "545.0";
	 * NumberUtil.floor((long)545.925, 1) = "545.0";
	 * NumberUtil.floor((long)545.925, 2) = "545.0";
	 * </pre>
	 * @param value  원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double floor(long value, int pos) {
		return floor((double)value, pos);
	}

	/**
	 * 지정한 길이만큼 절삭하여 표시한다.
	 * <pre>
	 * NumberUtil.floor(545.925, -2) = "500.0";
	 * NumberUtil.floor(545.925, -1) = "540.0";
	 * NumberUtil.floor(545.925, 0) = "545.0";
	 * NumberUtil.floor(545.925, 1) = "545.9";
	 * NumberUtil.floor(545.925, 2) = "545.92";
	 * </pre>
	 * @param value  원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double floor(double value, int pos) {
		if (pos < 0) {
			return Math.floor(value * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value).setScale(pos, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 지정한 길이만큼 올림하여 표시한다(콤마는 자동제거).
	 * <pre>
	 * NumberUtil.ceil("545.925", -2) = "600.0"
	 * NumberUtil.ceil("545.925", -1) = "550.0"
	 * NumberUtil.ceil("545.925", 0) =  "546.0"
	 * NumberUtil.ceil("545.925", 1) =  "546.0"
	 * NumberUtil.ceil("545.925", 2) =  "545.93"
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double ceil(String value, int pos) {
		value = StringUtil.removeCommaNotNull(value);
		if (pos < 0) {
			return Math.ceil(Double.parseDouble(value) * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value.trim()).setScale(pos, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 지정한 길이만큼 올림하여 표시한다.
	 * <pre>
	 * NumberUtil.ceil(new BigDecimal(545.925), -2) = "600.0"
	 * NumberUtil.ceil(new BigDecimal(545.925), -1) = "550.0"
	 * NumberUtil.ceil(new BigDecimal(545.925), 0) =  "546.0"
	 * NumberUtil.ceil(new BigDecimal(545.925), 1) =  "546.0"
	 * NumberUtil.ceil(new BigDecimal(545.925), 2) =  "545.93"
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double ceil(Number value, int pos) {
		return ceil(value.doubleValue(), pos);
	}

	/**
	 * 지정한 길이만큼 올림하여 표시한다.
	 * <pre>
	 * NumberUtil.ceil((long)545.925, -2) = "600.0"
	 * NumberUtil.ceil((long)545.925, -1) = "550.0"
	 * NumberUtil.ceil((long)545.925, 0) =  "545.0"
	 * NumberUtil.ceil((long)545.925, 1) =  "545.0"
	 * NumberUtil.ceil((long)545.925, 2) =  "545.0"
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double ceil(long value, int pos) {
		return ceil((double)value, pos);
	}

	/**
	 * 지정한 길이만큼 올림하여 표시한다.
	 * <pre>
	 * NumberUtil.ceil(545.925, -2) = "600.0"
	 * NumberUtil.ceil(545.925, -1) = "550.0"
	 * NumberUtil.ceil(545.925, 0) =  "546.0"
	 * NumberUtil.ceil(545.925, 1) =  "546.0"
	 * NumberUtil.ceil(545.925, 2) =  "545.93"
	 * </pre>
	 * @param value 원본값
	 * @param pos 자리수(음수 : 정수, 양수 : 소수점)
	 * @return 변환된 값
	 */
	public static double ceil(double value, int pos) {
		if (pos < 0) {
			return Math.ceil(value * Math.pow(10, pos)) / Math.pow(10, pos);
		}
		return new BigDecimal(value).setScale(pos, BigDecimal.ROUND_UP).doubleValue();
	}
	
	public static String isSelected(String a,String b){
 		return StringUtil.isEquals(a, b, "selected", "");
 	}

 	public static String isSelected(String a,String b, String c){
 		return StringUtil.isEquals(a, b, "selected", c);
 	}
 	
 	public static String isChecked(String a,String b){
 		return StringUtil.isEquals(a, b, "checked", "");
 	}
}
