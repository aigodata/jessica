package com.aigodata.jessica.basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aigodata.jessica.basic.common.exception.GlobalException;
import com.aigodata.jessica.basic.common.util.Encryption;
import com.aigodata.jessica.basic.common.web.annotation.JsonParam;
import com.aigodata.jessica.basic.model.ResultModel;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;
import com.aigodata.jessica.basic.service.PasswordService;

@RestController
@RequestMapping("/password")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@PutMapping
	public ResultModel modify(@JsonParam String oldPassword, @JsonParam String newPassword) {
		// 对密码进行解密, 获取原始密码内容
		String decryptnOldPassword = null;
		String decryptnNewPassword = null;
		try {
			decryptnOldPassword = Encryption.desEncrypt(oldPassword).trim();
			decryptnNewPassword = Encryption.desEncrypt(newPassword).trim();
		} catch (Exception e) {
			throw new GlobalException(ResultStatus.USERNAME_PASSWORD_ERROR);
		}
		passwordService.modify(decryptnOldPassword, decryptnNewPassword);
		return ResultModel.success(true);
	}

}
