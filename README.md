# 文章参考
[Springboot+mybatis-plus+注解 实现数据权限隔离](https://juejin.cn/post/6981280887216275492#comment)

[关于Java如何扫描package下所有的类](http://www.taodudu.cc/news/show-455722.html)

# 0
## 反射报错
object is not an instance of declaring class   说明Class没有实例化；

# 数据权限设计
通过sql 拼接拦截语句

## 设计方案
1、注解打在类上面    
2、注解打在方法上面  
3、



## pagehelper 使用
```java
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper-spring-boot-starter</artifactId>
			<version>1.3.0</version>
		</dependency>
```

```java
        PageHelper.startPage(1,1,true);

        List<DaySaleDO> users = dataMapper.getDaySale();
        PageInfo<DaySaleDO> pageInfo = new PageInfo<>(users);
        return pageInfo.getList();
```