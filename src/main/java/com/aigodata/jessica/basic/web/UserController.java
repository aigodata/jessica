package com.aigodata.jessica.basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aigodata.jessica.basic.domain.User;
import com.aigodata.jessica.basic.model.ResultModel;
import com.aigodata.jessica.basic.service.UserService;
import com.google.common.collect.Maps;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResultModel add(@RequestBody User user) {
		userService.add(user);
		return ResultModel.success(user);
	}

	@PutMapping("/{id}")
	public ResultModel update(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		int count = userService.update(user);
		return ResultModel.success(Maps.immutableEntry("count", count));
	}
}
