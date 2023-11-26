# web-backend
后端
## 项目层次
——api接口层

——service 服务层

——dao 数据层

——dto 实体与数据中转

——model 实体

- param request/response等参数
- Result为api通用返回类型

——util 工具类

- 自定义工具方法放这里面
- Code定义返回的错误码
- Const定义常量

## 层次调用
controller/api->service->dao
上级可以调用任意下级，下级不可调用上级比如dao->service是不符合的