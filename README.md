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

### 消息推送
##### 实现推送的方式
-  轮循，ajax的方式
- 长连接，如sse，websocket
- 老一点的DWR技术
```
    <dependency>  
        <groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-websocket</artifactId>  
    </dependency> 



<script> 
    var socket;  
    if(typeof(WebSocket) == "undefined") {  
        console.log("您的浏览器不支持WebSocket");  
    }else{  
        console.log("您的浏览器支持WebSocket");  
        	//实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接  
            //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");  
            socket = new WebSocket("${basePath}websocket/${cid}".replace("http","ws"));  
            //打开事件  
            socket.onopen = function() {  
                console.log("Socket 已打开");  
                //socket.send("这是来自客户端的消息" + location.href + new Date());  
            };  
            //获得消息事件  
            socket.onmessage = function(msg) {  
                console.log(msg.data);  
                //发现消息进入    开始处理前端触发逻辑
            };  
            //关闭事件  
            socket.onclose = function() {  
                console.log("Socket已关闭");  
            };  
            //发生了错误事件  
            socket.onerror = function() {  
                alert("Socket发生了错误");  
                //此时可以尝试刷新页面
            }  
            //离开页面时，关闭socket
            //jquery1.8中已经被废弃，3.0中已经移除
            // $(window).unload(function(){  
            //     socket.close();  
            //});  
    }
</script>

```
From [csdn](https://blog.csdn.net/moshowgame/article/details/80275084)
## deploy

##### 因为用的是容器数据库，用docker部署需要先修改yml配置文件,如 prod：
 - mysql：改为容器名称
 - redis：改为容器名称
 - mongo：改为容器名称
```
1. mvn clean package -Dmaven.test.skip=true
2. docker build -t wg:1.0.0 .
3. docker run -d --rm -p9000:9000 --link=mysql:wg_base --link=redis:redis --link=mongodb:wg --name wg wg:1.0.0
# --link=mysql:wg_base  其中mysql为容器名称，wg_base为数据库名称。
# --link=redis:redis  其中redis为容器名称，后面的redis为任意。
# --link=mongodb:wg  其中mongodb为容器名称，wg为数据库名称。
```
##### 关于 --net=host
  可以解决容器之间连接的问题，但无法将端口映射到宿主机，访问不到项目。




