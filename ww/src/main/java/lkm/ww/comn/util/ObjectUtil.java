package lkm.ww.comn.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 오브젝트 유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public class ObjectUtil {
	public static boolean isWrapClass(Object value) {
		if (value == null) {
			return false;
		}
		try {
			Class<?> clazz = value.getClass();
			return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
		} catch (Throwable ignore) {
			return false;
		}
	}

	public static boolean isWrapClass(Class<?> clazz) {
		try {
			return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
		} catch (Throwable ignore) {
			return false;
		}
	}

	public static boolean isBaseType(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String
				|| value instanceof Date
				|| value.getClass().isPrimitive()
				|| isWrapClass(value.getClass())) {
			return true;
		}
		return false;
	}

	public static boolean isBaseType(Class<?> clazz) {
		if (clazz == String.class
				|| clazz == Date.class
				|| clazz == java.sql.Date.class
				|| clazz == java.sql.Timestamp.class
				|| clazz.isPrimitive()
				|| isWrapClass(clazz)) {
			return true;
		}
		return false;
	}

	public static boolean isCollectionOrMap(Object value) {
		if (Collection.class.isInstance(value) || Map.class.isInstance(value)) {
			return true;
		}
		return false;
	}

	public static boolean isArray(Object object) {
		return object == null ? false : object.getClass().isArray();
	}

	public static boolean isContainer(Object object) {
		return isCollectionOrArray(object) || isMap(object);
	}

	public static boolean isCollectionOrArray(Object object) {
		return isArray(object) || isCollection(object);
	}

	public static boolean isMap(Object object) {
		return isOf(object, Map.class);
	}

	public static boolean isCollection(Object object) {
		return isOf(object, Collection.class);
	}

	public static boolean isOf(Object object, Class<?> type) {
		if (object == null) {
			return false;
		}
		return type.isAssignableFrom(object.getClass());
	}

	public static boolean is(Object object, Class<?> type) {
		if (object == null) {
			return false;
		}
		return object.getClass() == type;
	}

	public static boolean isString(Object object) {
		return is(object, String.class);
	}

	public static boolean isChar(Object object) {
		return is(object, char.class) || is(object, Character.class);
	}

	public static boolean isEnum(Object object) {
		return object == null ? false : object.getClass().isEnum();
	}

	public static boolean isBoolean(Object object) {
		return is(object, boolean.class) || is(object, Boolean.class);
	}

	public static boolean isFloat(Object object) {
		return is(object, float.class) || is(object, Float.class);
	}

	public static boolean isDouble(Object object) {
		return is(object, double.class) || is(object, Double.class);
	}

	public static boolean isInteger(Object object) {
		return is(object, int.class) || is(object, Integer.class);
	}

	public static boolean isBigDecimal(Object object) {
		return is(object, BigDecimal.class);
	}

	public static boolean isInterface(Object object) {
		return object == null ? false : object.getClass().isInterface();
	}

	public static boolean isDecimal(Object object) {
		return isFloat(object) || isDouble(object);
	}

	public static boolean isLong(Object object) {
		return is(object, long.class) || is(object, Long.class);
	}

	public static boolean isShort(Object object) {
		return is(object, short.class) || is(object, Short.class);
	}

	public static boolean isByte(Object object) {
		return is(object, byte.class) || is(object, Byte.class);
	}

	public static boolean isDateTime(Object object) {
		if (object == null) {
			return false;
		}
		Class<?> klass = object.getClass();
		return Calendar.class.isAssignableFrom(klass)
				|| java.util.Date.class.isAssignableFrom(klass)
				|| java.sql.Date.class.isAssignableFrom(klass)
				|| java.sql.Time.class.isAssignableFrom(klass);
	}

	public static boolean isPrimitiveNumber(Object object) {
		if (object == null) {
			return false;
		}
		return isInteger(object)
				|| isLong(object)
				|| isFloat(object)
				|| isDouble(object)
				|| isByte(object)
				|| isShort(object);
	}

	public static boolean isInstance(Class<?> clazz, Object value) {
		return clazz.isInstance(value);
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isEmpty(Object[] array) {
		return getLength(array) == 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

	public static boolean isEmpty(Object arrayObject) {
		return getLength(arrayObject) == 0;
	}

	public static int getLength(Object[] array) {
		return array == null ? 0 : array.length;
	}

	public static int getLength(Object arrayObject) {
		if (isArray(arrayObject)) {
			return Array.getLength(arrayObject);
		}
		return 0;
	}

	public static int getLengthByReflection(Object object) {
		if (object != null) {
			Class<?> clazz = object.getClass();
			try {
				Method method = clazz.getMethod("length");
				return (Integer) method.invoke(object);
			} catch (Throwable ignore) {

			}
			try {
				Method method = clazz.getMethod("getLength");
				return (Integer) method.invoke(object);
			} catch (Throwable ignore) {

			}
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public static int getSize(Collection collection) {
		return collection == null ? 0 : collection.size();
	}

	@SuppressWarnings("rawtypes")
	public static int getSize(Map map) {
		return map == null ? 0 : map.size();
	}

	public static int getSizeByReflection(Object object) {
		if (object != null) {
			Class<?> clazz = object.getClass();
			try {
				Method method = clazz.getMethod("size");
				if (method != null) {
					return (Integer) method.invoke(object);
				}
			} catch (Throwable ignore) {

			}
			try {
				Method method = clazz.getMethod("getSize");
				if (method != null) {
					return (Integer) method.invoke(object);
				}
			} catch (Throwable ignore) {

			}
		}
		return 0;
	}

	/**
	 * List의 인덱스에 해당하는 객체반환.
	 * @param list 리스트
	 * @param index 인덱스
	 * @return 객체
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject(List list, int index) {
		if (list != null && index > -1 && list.size() > index) {
			return list.get(index);
		}
		return null;
	}

	/**
	 * 배열의 인덱스에 해당하는 객체반환.
	 * @param array 배열
	 * @param index 인덱스
	 * @return 객체
	 */
	public static Object getObject(Object[] array, int index) {
		if (array != null && index > -1 && array.length > index) {
			return array[index];
		}
		return null;
	}

	/**
	 * Object의 인덱스에 해당하는 객체반환.
	 * @param arrayObject Object
	 * @param index 인덱스
	 * @return 객체
	 */
	public static Object getObject(Object arrayObject, int index) {
		if (isArray(arrayObject) && index > -1 && Array.getLength(arrayObject) > index) {
			return Array.get(arrayObject, index);
		}
		return null;
	}

	/**
	 * List의 마지막 인덱스의 객체반환.
	 * @param list 리스트
	 * @return 객체
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject(List list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	/**
	 * 배열의 마지막 인덱스의 객체반환.
	 * @param array 배열
	 * @return 객체
	 */
	public static Object getObject(Object[] array) {
		if (isEmpty(array)) {
			return null;
		}
		return array[array.length - 1];
	}

	/**
	 * Object의 마지막 인덱스의 객체반환.
	 * @param arrayObject Object
	 * @return 객체
	 */
	public static Object getObject(Object arrayObject) {
		if (arrayObject == null || !isArray(arrayObject)) {
			return null;
		}
		int length = Array.getLength(arrayObject);
		if (length == 0) {
			return null;
		}
		return Array.get(arrayObject, length - 1);
	}

	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ObjectUtil.class.getClassLoader();
		}
		return classLoader;
	}

	@SuppressWarnings("rawtypes")
	public static Class getClass(String clazz) throws ClassNotFoundException {
		return getClassLoader().loadClass(clazz);
	}

	public static Object getInstance(String clazz)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getClass(clazz).newInstance();
	}

	public static String reflectionToString(Object object) {
		return reflectionToString(object, true);
	}

	public static String reflectionToString(Object object, boolean debugEnabled) {
		if (!debugEnabled) {
			return StringUtil.EMPTY;
		}
		if (object == null) {
			return "null";
		}
		return ToStringBuilder.reflectionToString(object);
	}

	public static String invokeMethodToString(Object object) {
		return invokeMethodToString(object, true, false);
	}

	public static String invokeMethodToString(Object object, boolean debugEnabled) {
		return invokeMethodToString(object, debugEnabled, false);
	}

	private static final Pattern GETMETHOD_PATTERN = Pattern.compile("^(get|is)([a-zA-Z]{1})(\\w*)");

	@SuppressWarnings("rawtypes")
	public static String invokeMethodToString(Object object, boolean debugEnabled, boolean allMethods) {
		if (!debugEnabled) {
			return StringUtil.EMPTY;
		}
		if (object == null) {
			return "null";
		}
		Class<?> clazz = object.getClass();
		Method[] methods = allMethods ? clazz.getMethods() : clazz.getDeclaredMethods();

		StringBuilder buff = new StringBuilder();
		buff.append(clazz.getSimpleName())
			.append("{")
			.append(allMethods);

		for (Method m : methods) {
			if (!m.getReturnType().equals(Void.TYPE)
					&& Modifier.isPublic(m.getModifiers())
					&& !Modifier.isStatic(m.getModifiers())
					&& m.getParameterTypes().length == 0
					&& !"getClass".equals(m.getName())
				) {
				Matcher matcher = GETMETHOD_PATTERN.matcher(m.getName());
				if (matcher.find()) {
					Object obj = null;
					try {
						m.setAccessible(true);
						obj = m.invoke(object);
					} catch (Throwable ignore) {

					}
					buff.append(", ")
						.append(matcher.group(2).toLowerCase())
						.append(matcher.group(3))
						.append("=");
					if (obj != null) {
						if (obj instanceof List) {
							List list = (List) obj;
							buff.append("[");
							StringBuilder tmp1 = new StringBuilder();
							for (Object vo : list) {
								tmp1.append(vo).append(", ");
							}
							String tmp2 = tmp1.toString();
							buff.append(StringUtil.cut(tmp2, tmp2.length() - 2)).append("]");
						} else if (obj instanceof Map) {
							Map map = (Map) obj;
							buff.append("{");
							StringBuilder tmp1 = new StringBuilder();
							for (Iterator it = map.keySet().iterator(); it.hasNext();) {
								Object key = it.next();
								tmp1.append(key)
									.append("=")
									.append(map.get(key))
									.append(", ");
							}
							String tmp2 = tmp1.toString();
							buff.append(StringUtil.cut(tmp2, tmp2.length() - 2)).append("}");
						} else if (obj.getClass().isArray()) {
							buff.append("[");
							int len = Array.getLength(obj);
							StringBuilder tmp1 = new StringBuilder();
							for (int ii = 0; ii < len; ++ii) {
								tmp1.append(Array.get(obj, ii)).append(", ");
							}
							String tmp2 = tmp1.toString();
							buff.append(StringUtil.cut(tmp2, tmp2.length() - 2)).append("]");
						} else {
							buff.append(obj);
						}
					} else {
						buff.append("null");
					}
				}
			}
		}
		return buff.append("}").toString();
	}

	public static Object invokeGetterMethod(Object object, String fieldName) {
		if (object == null || StringUtil.isNullOrEmpty(fieldName)) {
			return null;
		}
		Object rObject = null;
		try {
			Class<?> clazz = object.getClass();
			String methodName = toMethodNameInternal("get", fieldName);
			Method method = clazz.getMethod(methodName);
			rObject = method.invoke(object);
		} catch (Throwable ignore) {

		}
		return rObject;
	}

	public static void invokeSetterMethod(Object object, String fieldName, Class<?> valueType, Object value) {
		if (object == null || StringUtil.isNullOrEmpty(fieldName)) {
			return;
		}
		try {
			Class<?> clazz = object.getClass();
			String methodName = toMethodNameInternal("set", fieldName);
			Method method = clazz.getMethod(methodName, valueType);
			method.invoke(object, value);
		} catch (Throwable ignore) {

		}
	}

	public static String toGetterMethodName(String fieldName) {
		if (StringUtil.isNullOrEmpty(fieldName)) {
			throw new IllegalArgumentException("Argument 'fieldName' must not be null or empty");
		}
		return toMethodNameInternal("get", fieldName);
	}

	public static String toSetterMethodName(String fieldName) {
		if (StringUtil.isNullOrEmpty(fieldName)) {
			throw new IllegalArgumentException("Argument 'fieldName' must not be null or empty");
		}
		return toMethodNameInternal("set", fieldName);
	}

	private static String toMethodNameInternal(String prefix, String fieldName) {
		String methodName;
		int length = StringUtil.getLength(fieldName);
		if (length == 1) {
			methodName = prefix + fieldName;
		} else {
			char index2Char = fieldName.charAt(1);
			if (Character.isUpperCase(index2Char)) {
				methodName = prefix + fieldName;
			} else {
				methodName = prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			}
		}
		return methodName;
	}

	public static String[] getFieldNames(Class<?> clazz, boolean superclass) {
		if (superclass) {
			Map<String, String> map = new HashMap<String, String>();
			for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
				Field[] fields = c.getDeclaredFields();
				for(Field field : fields) {
					map.put(field.getName(), null);
				}
			}
			return map.keySet().toArray(new String[0]);
		}
		return getFieldNames(clazz);
	}

	public static String[] getFieldNames(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		String[] names = new String[fields.length];
		for (int ii = 0; ii < fields.length; ++ii) {
			names[ii] = fields[ii].getName();
		}
		return names;
	}
}
