<%@page import="beans.Constants.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="beans.Constants"%>
<<<<<<< HEAD
<script>
	$(document).ready(function() {
		$(".dropdown-menu").click(function(e) {
			e.stopPropagation();
		});
	});
</script>
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
						class="icon-bar"></span> <span class="icon-bar"></span>
						 <span class="icon-bar"></span>
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
=======
<% Constants menu=new Constants(this,request); %>
>>>>>>> da60ce739136e9ec71c518df4f30bb029d410565
	<!-- 左側メニューのコンテンツ -->
	<div class="left-menu">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#estimates_1">見積書管理</a>
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
								<li><a href="#">登録</a></li>
								<li>変更</li>
								<li>削除</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" href="#orders_1">受注書管理</a>
			</div>
			<div id="orders_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#orders_1_1">一覧機能</a>
						</div>
						<div class="accordion-body collaose" id="orders_1_1">
							<ul class="accordion-inner">
								<li><a href="<%=menu.getPageToUrl(Page.OrderList_ser)%>">営業部受注一覧</a></li>
								<li><a href="<%=menu.getPageToUrl(Page.OrderRecodeList_ser)%>">倉庫部受注一覧</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#delivery_1">納品書管理</a>
			</div>
			<div id="delivery_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li>検索</li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#delivery_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="delivery_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#deliveryget_1">納品受領書管理</a>
			</div>
			<div id="deliveryget_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li><a href="#">検索</a></li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#deliveryget_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="deliveryget_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#earnings_1">売上管理</a>
			</div>
			<div id="earnings_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li><a href="<%=menu.getPageToUrl(Page.EarningsList_ser)%>">検索</a></li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#earnings_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="earnings_1_1">
							<ul class="accordion-inner">
								<li>売上計上</li>
								<li>参照</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#accounting_1">請求書管理</a>
			</div>
			<div id="accounting_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li><a href="<%=menu.getPageToUrl(Page.DoClaim_ser)%>">登録</a></li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" href="#Payment_1">入金管理</a>
			</div>
			<div id="Payment_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#Payment_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="Payment_1_1">
							<ul class="accordion-inner">
								<li><a href="<%=menu.getPageToUrl(Page.ClaimList_ser)%>">消込</a></li>
								<li>参照</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>