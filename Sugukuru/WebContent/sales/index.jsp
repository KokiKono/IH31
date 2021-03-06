<%@page import="beans.LoginEmployment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>営業部</title>
<!-- BootstrapのCSS読み込み -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="../js/bootstrap.min.js"></script>
<!-- テンプレート用CSSの読み込み -->
<link href="../css/template.css" rel="stylesheet">
<!-- サブメニュー用CSSの読み込み -->
<link href="../css/dropdowns-enhancement.css" rel="stylesheet">
<script src="../js/dropdowns-enhancement.js"></script>
<link href="css/index.css" rel="stylesheet">
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
				<a class="navbar-brand" href="#">営業部</a>
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
							<li><a href="http://localhost:8080/Sugukuru/SalesIndexServlet">ログアウト</a></li>
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
				<h2>受注書一覧</h2>
				<ul class="text-pager">
					<li class="text-page texter">1</li>
					<li class="text-par texter">/</li>
					<li class="text-all texter">24</li>
				</ul>
				<ul class="content-paging">
					<li class="paging-prev texter"><<</li>
					<li class="texter"><</li>
					<li class="texter">1</li>
					<li class="texter">2</li>
					<li class="texter">3</li>
					<li class="texter">></li>
					<li class="paging-next texter">>></li>
				</ul>

			</div>
			<div class="content-message content">メッセージ</div>
			<div class="content-search content">
				検索
				<form class="search_form">
					<div class="member_id search_content">
						<span class="member_id_text">顧客ID</span> <input type="text"
							size="10" class="input-text">
					</div>
					<div class="member_name search_content">
						<span class="member_name_text">略称</span> <input type="text"
							class="input-text">
					</div>
					<div class="create_date search_content">
						<span>作成日</span> <input type="text" size="4" placeholder="2016"><span>年</span>
						<input type="text" size="2" placeholder="2"><span>月</span>
						<input type="text" size="2" placeholder="2"><span>日</span>
					</div>
					<div class="drop_content">
						<div class="dispatch_state search_content">
							<div class="row">
								<div class="col-lg-12">
									<div class="button-group">
										<button type="button"
											class="btn btn-default btn-sm dropdown-toggle"
											data-toggle="dropdown">
											発送状態<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="#" class="small" data-value="option1"
												tabIndex="-1"><input type="checkbox" />完了</a></li>
											<li><a href="#" class="small" data-value="option1"
												tabIndex="-1"><input type="checkbox" />未完了</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="claim_state  search_content">
							<div class="row">
								<div class="col-lg-12">
									<div class="button-group">
										<button type="button"
											class="btn btn-default btn-sm dropdown-toggle"
											data-toggle="dropdown">
											請求状態<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="#" class="small" data-value="option1"
												tabIndex="-1"><input type="checkbox" />完了</a></li>
											<li><a href="#" class="small" data-value="option1"
												tabIndex="-1"><input type="checkbox" />未完了</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="search_submit">
						<button class="btn btn btn-primary">検索</button>
					</div>
				</form>
			</div>

		</div>
		<div class="container">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>顧客ID</th>
						<th>略称</th>
						<th>作成日</th>
						<th>発送状態</th>
						<th>請求状態</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月11日</td>
						<td>未完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>2</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月10日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>3</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>4</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>5</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>6</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>7</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>8</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>9</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
					<tr>
						<td>10</td>
						<td>38392101</td>
						<td>HAL大阪</td>
						<td>2016年07月4日</td>
						<td>完了</td>
						<td>未完</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>