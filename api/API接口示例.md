# API接口示例

### 1. JSON请求 

- 描述：全局API接口，JSON方式请求数据，请求JSON格式文档https://github.com/mengxianun/air-db


- 地址：/api/action

- 类型：POST

- 示例1 - 分页查询


    - 示例说明：查询sys_user表的第 **0** 条到第 **1** 条记录
    
    - 请求
    
      ```
       {
        	"query":"sys_user", // query关键字代表查询，sys_user为表名
        	"limit":[0, 1] // limit表示分页查询，第一个元素代表从第几条记录开始，第二个元素代表到第几条记录结束
        }
      ```
    
    - 响应
    
      ```
      {
            "code": 200,
            "message": "ok",
            "data": {
                "total": 1, // 总记录数
                "data": [
                    {
                        "password": "038bdaf98f2037b31f1e75b5b4c9b26e", // 密码
                        "salt": "admin", // 盐值
                        "create_time": "2018-06-15 11:41:12.0", // 创建时间
                        "phone": "", // 手机号
                        "name": "管理员", // 用户姓名
                        "id": 1, // 用户ID
                        "email": "", // 邮箱
                        "username": "admin", // 用户名
                        "status": 1 // 用户状态
                    }
                ],
                "start": 0, // 起始记录行数
                "end": 1 // 结束记录行数
            }
        }
      ```

- 示例2 - 单个对象查询

  - 示例说明：查询ID为1的用户

  - 请求

    ```
    {
    	"detail":"sys_user", // detail关键字代表查询单个对象，sys_user为表名
    	"where":"id=1" // where代表查询条件，这里id=1代表查询id为1的记录，具体where使用方法请参考文档 https://github.com/mengxianun/air-db
    }
    ```

  - 响应

    ```
    {
        "code": 200,
        "message": "ok",
        "data": {
            "password": "038bdaf98f2037b31f1e75b5b4c9b26e", // 密码
            "salt": "admin", // 盐值
            "create_time": "2018-06-15 11:41:12.0", // 创建时间
            "phone": "", // 手机号
            "name": "管理员", // 用户姓名
            "id": 1, // 用户ID
            "email": "", // 邮箱
            "username": "admin", // 用户名
            "status": 1 // 用户状态
        }
    }
    ```

- 示例3 - 新增

  - 示例说明：新增一个用户

  - 请求

    ```
    {
    	"insert":"sys_user", // insert关键字代表插入，sys_user为表名
    	"values":{ // values关键字代表插入的数据，key为表的列名，value为表的值
    		"name":"test", // 用户姓名
    		"username":"lili", // 用户名
    		"password":"123456", // 密码
    		"salt":"lili", // 盐值
    		"phone":"13856985632" // 手机号
    	}
    }
    ```

  - 响应

    ```
    {
        "code": 200,
        "message": "ok",
        "data": {
            "password": "123456", // 密码
            "salt": "lili", // 盐值
            "phone": "13856985632", // 手机号
            "name": "test", // 用户姓名
            "username": "lili" // 用户名
        }
    }
    ```

- 示例4 - 更新

  - 示例说明：更新name为test的用户

  - 请求

    ```
    {
    	"update":"sys_user", // update关键字代表更新，sys_user为表名
    	"values":{ // 需要更新的值，key为表列名，value为更新的列值
    		"phone":"13899999999"
    	},
    	"where":"name=test" // 更新的条件
    }
    ```

  - 响应

    ```
    {
        "code": 200,
        "message": "ok",
        "data": [
            1 // 更新的条数
        ]
    }
    ```

- 示例5 - 删除

  - 示例说明：删除ID为2的用户

  - 请求

    ```
    {
    	"delete":"sys_user", // delete关键字代表删除，sys_user为表名
    	"where":"id=2" // 删除的条件
    }
    ```

  - 响应

    ```
    {
        "code": 200,
        "message": "ok",
        "data": [
            1 // 删除的条数
        ]
    }
    ```

    

