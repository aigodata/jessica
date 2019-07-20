package com.aigodata.jessica.basic.common.shiro.filter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.aigodata.jessica.basic.model.ResultModel;
import com.google.gson.Gson;

/**
 * 登录过滤器, 如果不是登录操作, 返回JSON提示消息NOT_LOGGED
 * 
 * @author mengxiangyun
 *
 */
public class JsonAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
				throw new ServletException("LoginFilter只支持HTTP请求");
			}
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			// 返回JSON
			String result = new Gson().toJson(ResultModel.sessionOut());
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json");
			httpResponse.getWriter().write(result);
			return false;
		}
	}

}
