<%@page import="beans.Message"%>
<%@page import="dtd.StockOrderList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtd.OrderRecodeList"%>
<%@page import="beans.Constants.Page"%>
<%@page import="java.io.File"%>
<%@page import="beans.Constants"%>
<%@page import="common.ActionInterface"%>
<%@page import="beans.LoginEmployment"%>
<%
	//コンスタント作成。
	Constants constants=new Constants(this,request);
	//検索結果の取得
	ArrayList<StockOrderList> list=(ArrayList<StockOrderList>)request.getAttribute("stockOrderList");
	if(list==null)list=new ArrayList<StockOrderList>();
	//メッセージを取得
	Message message=(Message)request.getAttribute("message");
	//検索条件の取得
	StockOrderList search=(StockOrderList)request.getAttribute("search");
	if(search==null)search=new StockOrderList();


%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=constants.getConstant("01").value %></title>
<!-- BootstrapのCSS読み込み -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<!-- テンプレート用CSSの読み込み -->
<link href="<%=request.getContextPath() %>/css/template.css" rel="stylesheet">
<!-- サブメニュー用CSSの読み込み -->
<link href="<%=request.getContextPath() %>/css/dropdowns-enhancement.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/js/dropdowns-enhancement.js"></script>
<link href="<%=request.getContextPath() %>/stock/css/index.css" rel="stylesheet">
<script>
	$(document).ready(function() {
		$(".dropdown-menu").click(function(e) {
			e.stopPropagation();
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- スマートフォンサイズで表示されるメニューボタンとテキスト -->
			<div class="navbar-header">
				<!--
        メニューボタン
        data-toggle : ボタンを押したときにNavbarを開かせるために必要
        data-target : 複数navbarを作成する場合、ボタンとナビを紐づけるために必要
		     -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#nav-menu-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!-- タイトルなどのテキスト -->
				<a class="navbar-brand" href="#"><%=Constants.getCommon("01").value %></a>
			</div>

			<!-- グローバルナビの中身 -->
			<div class="collapse navbar-collapse" id="nav-menu-1">

				<!-- 各ナビゲーションメニュー -->
				<ul class="nav navbar-nav">

					<!-- 通常のリンク -->
					<li class="active"><a href="#">経理部からのお知らせ</a></li>
					<li><a href="#">倉庫部からのお知らせ</a></li>
					<!-- ドロップダウンのメニューも配置可能 -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">その他<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">その他１</a></li>
							<li><a href="#">その他２</a></li>
							<li><a href="#">その他３</a></li>
							<li class="divider"></li>
							<li><a href="#">それとも？</a></li>
							<li class="divider"></li>
							<li><a href="#">モンスター？</a></li>
						</ul></li>
				</ul>
				<!-- ログイン中のユーザー情報 -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 河野浩生<span class="caret"></span>
					</a> <!-- 1.メニューの配置 -->
						<ul class="dropdown-menu">
							<li><a href="#">パスワード変更</a></li>
							<li class="divider"></li>
							<li><a
								href="http://localhost:8080/Sugukuru/SalesIndexServlet">ログアウト</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 左側メニューのコンテンツ -->
	<div class="left-menu">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#estimates_1">見積書作成</a>
			</div>
			<div id="estimates_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li>検索</li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								href="#estimates_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="estimates_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
								<li>削除</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" href="#orders_1">受注書作成</a>
			</div>
			<div id="orders_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li>検索</li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#orders_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="orders_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
								<li>削除</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="center-content">
		<div class="center-content-header">
			<div class="center-content-title">
				<h2>受注書詳細</h2>
			</div>
			<div class="content-message content">メッセージ
			<%if(message!=null){ %>
			<%=message.getMessageArrayToStr("<span style=\"color:red\">", "</span>") %>
			<%} %>
			</div>
			<div class="content-search content">

			</div>
		</div>
		<div class="container">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th><%=constants.getConstant("08").value %></th>
						<th><%=constants.getConstant("02").value %></th>
						<th><%=constants.getConstant("03").value %></th>
						<th><%=constants.getConstant("09").value %></th>
						<th><%=constants.getConstant("10").value %></th>
						<th><%=constants.getConstant("11").value %></th>
					</tr>
				</thead>
				<tbody>
				<%if(list.size()==0){ %>
					<tr>
						<td colspan="7">検索結果がありませんでした。</td>
					</tr>
				<%}else{ %>
					<%int count=1; list:for(StockOrderList recodeList:list){ %>
					<%System.out.println(recodeList.ifDelFlgShow(search)); %>
					<% if(recodeList.ifDelFlgShow(search).equals("no") ) continue list;%>
					<tr>
						<td><%=count++ %></td>
						<td><%=recodeList.orderId %></td>
						<td><%=recodeList.customerId %></td>
						<td><%=recodeList.customerName %></td>
						<td><%=recodeList.getDeliveryDate() %></td>
						<td><%=recodeList.stepParsent() %></td>
						<td><%=recodeList.getState() %></td>
					</tr>
					<%}} %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>