package lkm.ww.comn.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;

/**
 * 쿠키유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public final class CookieUtil {

	//보안취약성 점검사항으로 도메인 고정
	public static final String DOMAIN = "*.childfund.or.kr";
	public static final String PATH = "/";
	//보안취약성 점검사항으로 최대 7일로 제한함
	public static final int MAX_AGE = 7 * 24 * 60 * 60;

	/** 생성자 **/
	private CookieUtil() {}

	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, name, getEncodingCharsetInternal());
	}

	public static String getCookie(HttpServletRequest request, String name, Charset encoding) {
		Cookie cookie = getCookieInternal(request, name);
		if (cookie == null) {
			return null;
		}
		String value = cookie.getValue();
		try {
			if (value != null) {
				return URLDecoder.decode(value, encoding.name());
			}
		} catch (Exception ignore) {
			ignore.printStackTrace();
		}
		return value;
	}

	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, Integer.MIN_VALUE, null, getEncodingCharsetInternal());
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		setCookie(response, name, value, maxAge, null, getEncodingCharsetInternal());
	}

	public static void setCookie(HttpServletResponse response, String name, String value, String path) {
		setCookie(response, name, value, Integer.MIN_VALUE, path, getEncodingCharsetInternal());
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
		setCookie(response, name, value, maxAge, path, getEncodingCharsetInternal());
	}

	public static void setCookie(HttpServletResponse response, String name, String value, Charset encoding) {
		setCookie(response, name, value, Integer.MIN_VALUE, null, encoding);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, Charset encoding) {
		setCookie(response, name, value, maxAge, null, encoding);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, String path, Charset encoding) {
		setCookie(response, name, value, Integer.MIN_VALUE, path, encoding);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path, Charset encoding) {
		String cValue = null;
		try {
			if (value != null) {
				cValue = URLEncoder.encode(value, encoding.name());
			}
		} catch (Exception ignore) {
			cValue = value;
		}
		Cookie cookie = new Cookie(name, StringUtil.nvl(cValue, StringUtil.EMPTY));
		cookie.setDomain(DOMAIN);
		if (maxAge > Integer.MIN_VALUE) {
			if (MAX_AGE < maxAge) {
				maxAge = MAX_AGE;
			}
			cookie.setMaxAge(maxAge);
		}
		if (path != null) {
			cookie.setPath(path);
		} else {
			cookie.setPath(PATH);
		}
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		removeCookie(request, response, name, null);
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path) {
		Cookie cookie = getCookieInternal(request, name);
		if (cookie != null) {
			cookie.setDomain(DOMAIN);
			if (path != null) {
				cookie.setPath(path);
			} else {
				cookie.setPath(PATH);
			}
			cookie.setValue(StringUtil.EMPTY);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	private static Cookie getCookieInternal(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	private static Charset getEncodingCharsetInternal() {
		return Charsets.toCharset("UTF-8");
	}
}
