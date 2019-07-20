package com.aigodata.jessica.basic.common.web.bind;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import com.aigodata.jessica.basic.common.web.annotation.JsonParam;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 自定义JSON参数解析器, 将JSON属性映射到方法参数中. 直接继承RequestParamMethodArgumentResolver类,
 * 并重写参数解析方法
 * 
 * @author mengxiangyun
 *
 */
public class JsonParamMethodArgumentResolver extends RequestParamMethodArgumentResolver {

	/*
	 * 请求JSON字符串
	 */
	private static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";

	// 原始类型包装类型
	private static final Class<?>[] PRIMITIVE_WRAP_CLASS = { java.lang.String.class, java.lang.Boolean.TYPE,
			java.lang.Character.TYPE, java.lang.Byte.TYPE, java.lang.Short.TYPE, java.lang.Integer.TYPE,
			java.lang.Long.TYPE, java.lang.Float.TYPE, java.lang.Double.TYPE, java.lang.Void.TYPE };

	public JsonParamMethodArgumentResolver(boolean useDefaultResolution) {
		super(useDefaultResolution);
	}

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(JsonParam.class);
	}

	@Override
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
		// 请求字符串
		String body = getRequestBody(request);
		if (body.equals("")) {
			return null;
		}
		JsonObject params = new JsonParser().parse(body.trim()).getAsJsonObject();
		if (!params.has(name)) {
			return null;
		}
		// 方法参数类型
		Class<?> parameterType = parameter.getParameterType();
		if (parameterType.isArray()) { // 数组类型
			// 返回String数组, Spring无法解析原始类型(非包装类型)数组
			JsonArray jsonArray = params.getAsJsonArray(name);
			List<String> paramList = new ArrayList<>();
			jsonArray.forEach(e -> paramList.add(e.getAsString()));
			return paramList.toArray(new String[] {});
		} else if (Collection.class.isAssignableFrom(parameterType)) { // 集合类型
			// 是否包含泛型
			if (parameter.getGenericParameterType() instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) parameter.getGenericParameterType();
				// 获取泛型类型
				Class<?> actualType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
				Gson gson = new Gson();
				List<Object> result = new ArrayList<>();
				JsonArray paramArray = params.getAsJsonArray(name);
				for (JsonElement jsonElement : paramArray) {
					if (jsonElement.isJsonObject()) {
						Object object = gson.fromJson(jsonElement, actualType);
						result.add(object);
					} else { // 基本类型
						result.add(parse(jsonElement, actualType));
					}
				}
				return result;
			} else {
				List<Object> paramList = new ArrayList<>();
				JsonArray paramArray = params.getAsJsonArray(name);
				for (JsonElement jsonElement : paramArray) {
					paramList.add(parse(jsonElement, Object.class));
				}
				return paramList; // 默认ArrayList
			}
		} else if (parameterType.isPrimitive() || Arrays.asList(PRIMITIVE_WRAP_CLASS).contains(parameterType)) { // 基本类型
			return parse(params.get(name), parameterType);
		} else { // 其他对象类型
			if (params.has(name)) {
				return new Gson().fromJson(params.get(name), parameterType);
			} else {
				Object instance = parameterType.newInstance();
				Field[] fields = parameterType.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					if (params.has(fieldName)) {
						field.setAccessible(true);
						field.set(instance, parse(params.get(fieldName), field.getType()));
					}
				}
				return instance;
			}

		}
	}

	/**
	 * 获取请求体, 因为流只能读一次, 而方法参数解析是分开多次解析, 所以在第一次获取请求体之后将其内容保存在request中,
	 * 以备下一个参数解析的时候获取请求内容
	 * 
	 * @param webRequest
	 * @return
	 */
	@SuppressWarnings("resource")
	private String getRequestBody(NativeWebRequest webRequest) {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		String jsonBody = (String) servletRequest.getAttribute(JSON_REQUEST_BODY);
		if (jsonBody == null) {
			try {
				StringBuilderWriter writer = new StringBuilderWriter();
				InputStreamReader reader = new InputStreamReader(servletRequest.getInputStream(),
						Charset.defaultCharset());
				char[] buffer = new char[1024 * 4];
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				jsonBody = writer.toString();
				servletRequest.setAttribute(JSON_REQUEST_BODY, jsonBody);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return jsonBody;
	}

	private Object parse(JsonElement jsonElement, Class<?> type) {
		if (type == Integer.class) {
			return jsonElement.getAsInt();
		} else if (type == Long.class) {
			return jsonElement.getAsLong();
		} else if (type == Boolean.class) {
			return jsonElement.getAsBoolean();
		} else if (type == Double.class) {
			return jsonElement.getAsDouble();
		} else {
			return jsonElement.getAsString();
		}
	}

}
