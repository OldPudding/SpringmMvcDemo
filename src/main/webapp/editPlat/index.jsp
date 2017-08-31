<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>生成平台</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/productPlat.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<script src="js/bootstrap.js"></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="img/平台.png" style="height: 32px;display:inline-block;"/><span style="padding-left: 5px;">生成平台</span></a>
				<!--<div style="margin-top: 20px;font-size: 20px;margin-left: 500px;position: relative;">
					<a  href="#" style="text-decoration: none;position: absolute;"><span>生产平台</span></a>
				<a  href="#" style="text-decoration: none;margin-left: 130px;"><span>分发平台</span></a>
				</div>-->


				<ul class="nav navbar-nav " style="margin-left: 450px;font-size: 20px;position: absolute;margin-top: 10px;">
					<li ><a  href="#"><span>通用平台</span></a></li>
					<li ><a  href="#"><span>分发平台</span></a></li>
					<li style="margin-left: 200px;font-size: 14px;" id="changeUser"><a href=""><span class="glyphicon glyphicon-user"></span>   admin</a></li>
					<li style="margin-left: 0px;font-size: 14px; margin-top: -14px;"><a href=""><span class="glyphicon glyphicon-envelope" style="margin-left: 5px;"></span><br />消息</a></li>
					<li style="margin-left: 0px;font-size: 14px;margin-top: -14px;"><a href=""><span class="glyphicon glyphicon-edit" style="margin-left: 20px;"></span><br />修改密码</a></li>
					<li style="margin-left: 0px;font-size: 14px;margin-top: -14px;"><a href=""><span class="glyphicon glyphicon-share" style="margin-left: 20px;"></span><br /> 安全退出</a></li>
				</ul>



			</div>
		</div><!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">

		<ul class="nav ">
			<li class=""><a href="index.html"><span class="glyphicon glyphicon-dashboard"></span> 导航菜单栏</a></li>
			<!--<li role="presentation" class="divider"></li>-->
			<li class="parent ">
				<ul class="children" id="sub-item-1">
					<li>
						<a class="" href="index.html">
							<span class="glyphicon glyphicon-share-alt"></span> 稿件上传
						</a>
					</li>
					<li>
						<a class="" href="editmanuscript.html">
							<span class="glyphicon glyphicon-share-alt"></span> 稿件制作
						</a>
					</li>
					<li>
						<a class="" href="checkmanuscript.html">
							<span class="glyphicon glyphicon-share-alt"></span> 稿件审核
						</a>
					</li>

				</ul>
			</li>
<!-- 			<li class=""><a href="index.html"><span class="glyphicon glyphicon-lock"></span> 用户权限管理</a></li> -->
	</div><!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<ul id="articleUpload" class="nav nav-tabs">
			<li><a href="#" id="tabhead" data-toggle="tab">稿件上传</a></li>
		</ul>
		<form role="form">
			<div class="form-group" style="margin-top:15px">
			    <label for="name">类型</label>
			    <label class="checkbox-inline">
				    <input type="radio" name="optionsRadiosinline" id="wordtype"
				         value="option1" checked> 文字
				</label>
				<label class="checkbox-inline">
				    <input type="radio" name="optionsRadiosinline" id="picturetype"
				         value="option2"> 图片
				</label>
			</div>
			<div class="form-group">
			    <label for="inputfile">选择</label>
			    <input type="file" id="inputfile" style="display:inline-block">
			    <label style="color:red;margin-left:20px;">选择资源文件大小不得超过xxx字节</label>
			</div>
			<div class="form-group">
			    <label for="name">名称</label>
			    <input type="text" class="form-control" id="name" style="display:inline-block">
			</div>
			<div class="form-group">
			    <label for="filetype">标签</label>
			    <input type="text" class="form-control" id="filetype">
			</div>
			<div class="form-group">
			    <label for="description">描述</label>
			    <textarea class="form-control" rows="10" id="description"></textarea>
			</div>
			<button type="submit" class="btn btn-default" style="width: 100px; margin-left: 70px;">上传</button>
		</form>
	</div>


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script>
		$('#calendar').datepicker({
		});

		!function ($) {
		    $(document).on("click","ul.nav li.parent > a > span.icon", function(){
		        $(this).find('em:first').toggleClass("glyphicon-minus");
		    });
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>
</body>

</html>
