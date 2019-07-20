package com.aigodata.jessica.basic.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * Ajax Session 超时处理, 返回未登录状态码
 * 
 * @author mengxiangyun
 *
 */
public class AjaxSessionTimeoutFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) { // Ajax请求
			((HttpServletResponse) response).setStatus(403);
			return false;
		}
		return true;
	}

}
