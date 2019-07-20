# 登录API接口

### 1. 用户登录

- 描述：用户登录系统

- 地址：/api/login

- 类型：POST

- 请求：

  ```
  {
    "username":"admin", // 用户名, ***必填项
    "password":"123456", // 密码, ***必填项
    "captcha":"2828" // 验证码 ***必填项
  }
  ```

- 响应

  ```
  {
      "code": 200,
      "message": "ok",
      "data": {
          "id": 1, // 用户ID
          "name": "管理员", // 用户姓名
          "username": "admin", // 登录用户名
          "roleId": "1", // 登录角色ID
          "role": "管理员" // 登录角色显示名称
      }
  }
  ```


### 2. 用户登出

- 描述：用户登出系统

- 地址：/api/logout

- 类型：POST

- 请求：无

- 响应

  ```
  {
      "code": 200,
      "message": "ok",
      "data": true // true代表登出成功，false代表登出失败，具体失败原因查看message
  }
  ```

### 3. 获取验证码

- 描述：获取登录的验证码
- 地址：/api/captcha
- 类型：GET
- 请求：无
- 响应：验证码图片