# wg-base-backend

## SpringBoot

### Return Result && Exception

### JPA && QueryDsl
- 自动生成CreateTime和UpdateTime 

  JPA save()后的返回值与数据库实际存储的时间数据不一致，列表展示等不建议采用。
- 规范分页查询
- 多表联合查询
### Token
- true
### Redis 
- true
### Kafka
- false
### mongo
- 集成
- CRUD公共类

## deploy
```
1. 因为用的是容器数据库，用docker部署需要先修改yml配置文件,如 prod：
 - mysql：改为容器名称
 - redis：改为容器名称
 - mongo：改为容器名称
2. mvn clean package -Dmaven.test.skip=true
3. docker build -t wg:1.0.0 .
4. docker run -d --rm -p9000:9000 --link=mysql:wg_base --link=redis:redis --link=mongodb:wg --name wg wg:1.0.0

- 关于 --net=host
  可以解决容器之间连接的问题，但无法将端口映射到宿主机，访问不到项目。

```





