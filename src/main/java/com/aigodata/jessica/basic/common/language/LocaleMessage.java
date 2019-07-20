package com.aigodata.jessica.basic.common.language;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/***
 * 获取多语言文本
 * 
 * @author Saps.Weaver
 *
 */
@Component
public class LocaleMessage {
	private static MessageSource $message;
	@Autowired
	private MessageSource messageSource;

	@PostConstruct
	public void ResultModel() {
		$message = messageSource;
	}

	public static String get(String code) {
		return get(code, null, null, LocaleContextHolder.getLocale());
	}

	private static String get(String code, Object[] args, String defaultMessage, Locale locale) {
		return $message.getMessage(code, args, defaultMessage, locale);
	}
}
