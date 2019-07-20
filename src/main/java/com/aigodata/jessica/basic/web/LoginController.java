package com.aigodata.jessica.basic.web;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aigodata.jessica.basic.common.exception.GlobalException;
import com.aigodata.jessica.basic.common.shiro.util.SubjectUtil;
import com.aigodata.jessica.basic.common.util.CaptchaUtil;
import com.aigodata.jessica.basic.common.util.Encryption;
import com.aigodata.jessica.basic.common.util.StringUtil;
import com.aigodata.jessica.basic.common.web.annotation.JsonParam;
import com.aigodata.jessica.basic.model.ResultModel;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;
import com.aigodata.jessica.basic.service.LoginService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Value("${service.security.captchaEnabled}")
	private boolean captchaEnabled;

	/**
	 * 登录
	 * 
	 * @param username   用户名
	 * @param password   密码
	 * @param rememberMe 记住我
	 * @param captcha    验证码
	 * @return
	 * @throws Exception
	 */
	 
	@GetMapping("/login/sso")
	public ResultModel loginSSO(HttpServletRequest request) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			loginService.login(username, password, false);
		} catch (Exception e) {
			return ResultModel.fail(ResultStatus.SERVER_ERROR.code(), e.getMessage());
		}
		return ResultModel.success(SubjectUtil.getUser());
	}
	
	@PostMapping("/login")
	public ResultModel login(@JsonParam String params, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String username = null;
		String password = null;
		boolean rememberMe = false;
		String captcha = null;
		try {
			// 对请求参数进行解密
			params = Encryption.desEncrypt(params).trim();
			JsonObject pms = new Gson().fromJson(params, JsonObject.class);
			username = pms.get("username").getAsString();
			password = pms.get("password").getAsString();
			if (captchaEnabled) {
				captcha = pms.get("captcha").getAsString();
			}
			if (pms.has("rememberMe")) {
				rememberMe = pms.get("rememberMe").getAsBoolean();
			}
		} catch (Exception e) {
			session.removeAttribute("captcha");
			throw new GlobalException(500, "数据异常,请重试");
		}

		if (captchaEnabled) {
			Object sessionCaptcha = session.getAttribute("captcha");
			if (StringUtil.isNull(sessionCaptcha)) {
				return ResultModel.fail(ResultStatus.SERVER_ERROR.code(),ResultStatus.CAPTCHA_LOSE.message(),
						Maps.immutableEntry("errorCode", ResultStatus.CAPTCHA_LOSE.code()));
			}
			if (Strings.isNullOrEmpty(captcha)) {
				return ResultModel.fail(ResultStatus.SERVER_ERROR.code(), ResultStatus.CAPTCHA_ERROR.message(),
						Maps.immutableEntry("errorCode", ResultStatus.CAPTCHA_ERROR.code()));
			}
			if (!captcha.equals(sessionCaptcha)) {
				return ResultModel.fail(ResultStatus.SERVER_ERROR.code(), ResultStatus.CAPTCHA_ERROR.message(),
						Maps.immutableEntry("errorCode", ResultStatus.CAPTCHA_ERROR.code()));
			}
		}
		session.removeAttribute("captcha");
		loginService.login(username, password, rememberMe);
		return ResultModel.success(SubjectUtil.getUser());
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@PostMapping("/logout")
	public ResultModel logout() {
		loginService.logout();
		return ResultModel.success(true);
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 调用工具类生成的验证码和验证码图片
		Map<String, Object> codeMap = CaptchaUtil.generateCodeAndPic();

		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		session.setAttribute("captcha", codeMap.get("code").toString());

		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);

		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
