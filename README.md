# 效果展示

访问我的博客：https://zouchanglin.cn/2020/11/19/58995.html

# 项目功能

## 已完成的功能

* 支持评论文章
* 支持文章打分
* 支持评论回复
* 支持评论点赞
* 支持评论点赞 / 回复通知（邮件的方式）
* 支持无登录评论
* 支持表情输入

## 未来会支持的特性

* 支持后台管理
* 支持自适应移动端(宽高度需调整)
* 支持Docker一键部署
* ……

# 运行步骤

## 1、搭建Java & Maven环境

默认：JDK1.8 、Maven3.6+

## 2、准备好MySQL环境

直接导入sql文件夹下的comment-box.sql即可，编码UTF8mb4

## 3、使用Maven构建工程

```
mvn package -Dmaven.test.skip=true
```

## 4、执行Jar包

```
java -jar comment-box-0.0.1-SNAPSHOT.jar
```

# 嵌入到Hexo博客

每个主题可能宽度需要调整，我自己的博客效果还可以！

```
<iframe id="comment-box" width="100%" height="800px" style="background-color: #eaeaea; marginLeft:10px" frameborder="0">
    您的浏览器不支持iframe，请升级
</iframe>
<script>
    document.getElementById('comment-box').src = 'http://localhost:8080/#/?url=' + window.location.href;
</script>
```


# 具体改进点
> 后端
* 采用数据库连接池
* 评论重叠BUG
* 评论信息校验
* 点赞邮件通知
* URL中具体文章与域名分离

> 前端
* 前端压缩传输
* UI美化，ElementUI不是做前端的料
