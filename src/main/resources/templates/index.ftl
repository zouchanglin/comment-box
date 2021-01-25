<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!--使用Bootstrap的js插件，必须先调入jQuery-->
    <script src="http://libs.baidu.com/jquery/1.9.0/jquery.min.js"></script>
    <!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body style="margin-top: 50px">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron" style="background-color: #f7e1b5; border: 5px solid #428bca; border-radius: 15px; padding: 20px">
                <h1>
                    @ ${userName}，你的评论有新回复啦~
                </h1>
                <p>
                    ${commentContent}
                </p>
                <p>
                    <a class="btn btn-primary btn-lg" href="${hrefUrl}" style="width: 200px; height: 50px; background-color: #5bc0de">去看看</a>
                    <br>
                    <br>
                    <br>
                    <span style="margin-top: 20px ;font-size: 30px; font-style: italic"><a href="${hrefUrl}">可点击链接查看${hrefUrl}</a></span>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
