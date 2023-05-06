# 文章参考
[Springboot+mybatis-plus+注解 实现数据权限隔离](https://juejin.cn/post/6981280887216275492#comment)
[关于Java如何扫描package下所有的类](http://www.taodudu.cc/news/show-455722.html)
[拦截器HandlerInterceptorAdapter使用方法](https://blog.csdn.net/web13985085406/article/details/123321657)

# 0
## 反射报错
object is not an instance of declaring class   说明Class没有实例化；

# 数据权限设计 理论基础
通过sql 拼接拦截语句

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

## mybatis的一些注解
@Signature 注解参数说明:
type：就是指定拦截器类型（ParameterHandler ，StatementHandler，ResultSetHandler ）
method：是拦截器类型中的方法，不是自己写的方法
args：是method中方法的入参



# 实现方式
https://www.processon.com/view/link/645497e0def66d68579b52a3


# 问题记录
## 2023年5月4日
1、无法拦截原生数据权限   方式一无法解决
2、拦截多表查询需要别名  已解决( 约定好 别名.字段名  不符合则不做任何处理 )
3、拦截方式：现在只有 in 方式
