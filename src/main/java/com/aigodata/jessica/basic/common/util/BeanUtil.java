package com.aigodata.jessica.basic.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java Bean 工具类
 * 
 * @author mengxiangyun
 *
 */
public class BeanUtil {

	/**
	 * 通过Java Bean属性名数组和属性值数组列表生成多个Java Bean对象, 属性名数组和属性值数据元素位置必须一一对应
	 * 
	 * @param fields
	 *            属性名称数组
	 * @param listValues
	 *            属性值数组列表
	 * @param beanClass
	 *            Java Bean 类型
	 * @return
	 */
	public static <T> List<T> toBeanList(String[] fields, List<Object[]> listValues, Class<T> beanClass) {
		List<T> beans = new ArrayList<>();
		for (Object[] values : listValues) {
			if (values != null) {
				T t = toBean(fields, values, beanClass);
				beans.add(t);
			}
		}
		return beans;
	}

	/**
	 * 通过Java Bean属性名数组和属性值数组生成Java Bean对象, 属性名数组和属性值数据元素位置必须一一对应
	 * 
	 * @param fields
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @param beanClass
	 *            Java Bean 类型
	 * @return
	 */
	public static <T> T toBean(String[] fields, Object[] values, Class<T> beanClass) {
		Map<String, Field> totalFields = getAllNameFields(beanClass);
		T t = null;
		try {
			t = beanClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int times = Math.min(fields.length, values.length);
		for (int i = 0; i < times; i++) {
			if (values[i] == null) {
				continue;
			}
			try {
				Field field = totalFields.get(fields[i]);
				field.setAccessible(true);
				// 处理数值形式的文本
				if (field.getType() == String.class && values[i].getClass() == Double.class) {
					field.set(t, new BigDecimal((Double) values[i]).toPlainString());
				} else {
					field.set(t, values[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * 通过Java Bean属性名数组和属性值数组列表生成多个Java Bean对象, 属性名数组和属性值数据元素位置必须一一对应
	 * 
	 * @param fields
	 *            属性名称数组
	 * @param listValues
	 *            属性值数组列表
	 * @param beanClass
	 *            Java Bean 类型
	 * @return
	 */
	public static <T> List<T> toBeanListWithStringValue(String[] fields, List<String[]> listValues,
			Class<T> beanClass) {
		List<T> beans = new ArrayList<>();
		for (Object[] values : listValues) {
			if (values != null) {
				T t = toBean(fields, values, beanClass);
				beans.add(t);
			}
		}
		return beans;
	}

	/**
	 * 通过Java Bean属性名数组和属性值数组生成Java Bean对象, 属性名数组和属性值数据元素位置必须一一对应
	 * 
	 * @param fields
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @param beanClass
	 *            Java Bean 类型
	 * @return
	 */
	public static <T> T toBeanWithStringValue(String[] fields, String[] values, Class<T> beanClass) {
		Map<String, Field> totalFields = getAllNameFields(beanClass);
		T t = null;
		try {
			t = beanClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int times = Math.min(fields.length, values.length);
		for (int i = 0; i < times; i++) {
			if (values[i] == null) {
				continue;
			}
			try {
				Field field = totalFields.get(fields[i]);
				field.setAccessible(true);
				if (field.getType() == Integer.class) {
					field.set(t, Integer.parseInt(values[i]));
				} else if (field.getType() == LocalDateTime.class) {
					field.set(t, LocalDateTime.parse(values[i]));
				} else if (field.getType() == LocalDate.class) {
					field.set(t, LocalDate.parse(values[i]));
				} else {
					field.set(t, values[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * 将Map对象转换为Java Bean
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(Map<String, Object> map, Class<T> beanClass) {
		Map<String, Field> totalFields = getAllNameFields(beanClass);
		T t = null;
		try {
			t = beanClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String fieldName = entry.getKey();
			Object fieldValue = entry.getValue();
			Field field = totalFields.get(fieldName);
			field.setAccessible(true);
			try {
				field.set(t, convert(field.getType(), fieldValue));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * Java Bean集合转为List<Map>集合
	 * 
	 * @param beans
	 * @return
	 */
	public static <T> List<Map<String, Object>> toMapList(List<T> beans) {
		List<Map<String, Object>> list = new ArrayList<>();
		beans.stream().forEach(e -> list.add(toMap(e)));
		return list;

	}

	/**
	 * Java Bean 转为Map
	 * 
	 * @param t
	 * @return
	 */
	public static <T> Map<String, Object> toMap(T t) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Field> totalFields = getAllNameFields(t.getClass());
		for (Map.Entry<String, Field> entry : totalFields.entrySet()) {
			try {
				Field field = entry.getValue();
				field.setAccessible(true);
				map.put(entry.getKey(), field.get(t));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 获取Java Bean所有字段, 包括父类. 过滤静态字段
	 * 
	 * @param beanClass
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> beanClass) {
		List<Field> totalFields = new ArrayList<>();
		while (beanClass != null) {
			Field[] fields = beanClass.getDeclaredFields();
			totalFields.addAll(Arrays.asList(fields));
			beanClass = beanClass.getSuperclass();
		}
		// 过滤静态字段
		totalFields = totalFields.stream().filter(field -> !Modifier.isStatic(field.getModifiers()))
				.collect(Collectors.toList());
		return totalFields;
	}

	/**
	 * 获取Java Bean所有字段Map(fieldName -> Field)集合, 包括父类. 过滤静态字段
	 * 
	 * @param beanClass
	 * @return
	 */
	public static Map<String, Field> getAllNameFields(Class<?> beanClass) {
		Map<String, Field> totalFields = new HashMap<>();
		while (beanClass != null) {
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				int modifiers = field.getModifiers();
				if (!Modifier.isStatic(modifiers)) {
					totalFields.put(field.getName(), field);
				}
			}
			beanClass = beanClass.getSuperclass();
		}
		return totalFields;
	}

	/**
	 * 转换对象到指定类型
	 * 
	 * @param targetType
	 *            目标类型
	 * @param value
	 *            被转换的对象
	 * @return
	 */
	public static <T> T convert(final Class<T> targetType, final Object value) {
		if (value == null) {
			return null;
		}
		if (targetType.equals(value.getClass())) {
			return targetType.cast(value);
		}
		// Integer
		if (targetType.equals(Integer.class)) {
			return targetType.cast(Integer.parseInt((String) value));
		}
		// Long
		if (targetType.equals(Long.class)) {
			return targetType.cast(Long.parseLong((String) value));
		}
		// Float
		if (targetType.equals(Float.class)) {
			return targetType.cast(Float.parseFloat((String) value));
		}
		// Double
		if (targetType.equals(Double.class)) {
			return targetType.cast(Double.parseDouble((String) value));
		}
		// Boolean
		if (value instanceof Boolean) {
			return targetType.cast(Boolean.parseBoolean((String) value));
		}
		// Date --> Long
		if (value instanceof Date && Long.class.equals(targetType)) {
			return targetType.cast(new Long(((Date) value).getTime()));
		}
		// Calendar --> Long
		if (value instanceof Calendar && Long.class.equals(targetType)) {
			return targetType.cast(new Long(((Calendar) value).getTime().getTime()));
		}
		// 其他类型全部转换为String
		return targetType.cast(((String) value).toString().trim());
	}

}
