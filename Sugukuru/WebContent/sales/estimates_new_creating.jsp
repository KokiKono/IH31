<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="../js/bootstrap.min.js"></script>
    <!-- テンプレート用CSSの読み込み -->
  	<link href="../css/template.css" rel="stylesheet">
  	<!-- サブメニュー用CSSの読み込み -->
  	<link href="../css/dropdowns-enhancement.css" rel="stylesheet">
  	<script src="../js/dropdowns-enhancement.js"></script>
  	<link href="css/order_drafting.css" rel="stylesheet">
  	<!-- bootstrap調整用css読み込み -->
  	<link href="css/estimates_new_creating.css" rel="stylesheet">
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
      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-menu-1">
        			<span class="sr-only">Toggle navigation</span>
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
       				<span class="icon-bar"></span>
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
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">その他<b class="caret"></b></a>
		          <ul class="dropdown-menu">
		            <li><a href="#">その他１</a></li>
		            <li><a href="#">その他２</a></li>
		            <li><a href="#">その他３</a></li>
		            <li class="divider"></li>
		            <li><a href="#">それとも？</a></li>
		            <li class="divider"></li>
		            <li><a href="#">モンスター？</a></li>
		          </ul>
		        </li>
		      </ul>
		      <!-- ログイン中のユーザー情報 -->
		      <ul class="nav navbar-nav navbar-right">
		    		<li class="dropdown">
		        		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
		            	河野浩生<span class="caret"></span>
		        		</a>
		        		<!-- 1.メニューの配置 -->
		        		<ul class="dropdown-menu">
		            		<li><a href="#">パスワード変更</a></li>
		            		<li class="divider"></li>
		            		<li><a href="#">ログアウト</a></li>
		        		</ul>
		    		</li>
				</ul>
			</div>
		  </div>
		</nav>
		<!-- 左側メニューのコンテンツ -->
		<div class="left-menu">
			<div class="accordion-group">
  				<div class="accordion-heading"><a class="accordion-toggle" data-toggle="collapse" href="#estimates_1">見積書作成</a></div>
  					<div id="estimates_1" class="accordion-body collapse">
    					<ul class="accordion-inner">
    						<li>検索</li>
    						<li class="accordion-group">
    							<div class="accordion-heading">
    								<a class="accordion-toggle" data-toggle="collapse" href="#estimates_1_1">管理機能</a>
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
				<div class="accordion-heading"><a class="accordion-toggle" data-toggle="collapse" href="#orders_1">受注書作成</a></div>
  				<div id="orders_1" class="accordion-body collapse">
    				<ul class="accordion-inner">
    					<li>検索</li>
    					<li class="accordion-group">
    						<div class="accordion-heading">
    							<a class="accordion-toggle" data-toggle="collapse" data-parent"#post" href="#orders_1_1">管理機能</a>
    						</div>
    						<div class="accordion-body collaose" id="orders_1_1">   								<ul class="accordion-inner">
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
					<h2>見積書作成</h2>
				</div>
				<div class="content-message content">
					メッセージ<br>
					N-20-N、N-20-N、N-20-N、N-20-N、N-20-N<br>
				</div>
			</div>
			<div class="container">
				<table class="table table-striped table-bordered table-up">
<!-- 					<thead> -->
<!-- 						<tr> -->
<!-- 							<th colspan="8" class="table-header">見積書</th> -->
<!-- 						</tr> -->
<!-- 					</thead> -->
						<tbody>
							<tr>
								<th>顧客ID</th>
								<td style="padding:0px"><input type="text" maxlength="6" class="form-control input-sm table-textbox-big"></td>
								<th class="width-100">顧客名</th>
								<td colspan="5" style="padding:0px"><input type="text" maxlength="50" class="form-control input-sm table-textbox-big" style="width: 650px;"></td>
								<th>与信オーバー額</th>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<th>商品代金</th>
								<td class="price">99,999</td>
								<th>手数料</th>
								<td class="price">99,999</td>
								<th>税抜合計</th>
								<td class="price">9,999,999,999</td>
								<th>税</th>
								<td class="price">99,999</td>
								<th>税込合計</th>
								<td class="price">999,999,999,999</td>
							</tr>
						</tbody>
				</table>
				<table class="table table-striped table-bordered table-up">
						<tbody>
							<tr>
								<th>ID</th>
								<th>単位</th>
								<th>数量</th>
								<th>在庫</th>
								<th>単価</th>
								<th>販売単価</th>
								<th>税抜き小計</th>
								<th>税</th>
								<th>税込み小計</th>
							</tr>
							<tr>
								<td style="padding:0px"><input type="text" maxlength="7" class="form-control input-sm text-center" style="height: 35px; font-size: 13px; padding:0px; border-radius: 0px"></td>
								<td>あいうえおあいうえお</td>
								<td class="price">999</td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td>9999999</td>
								<td>あいうえおあいうえお</td>
								<td class="price">999</td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td>2</td>
								<td>9-8-9</td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>999</td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td>3</td>
								<td><button class="btn btn-primary success-btn">選択</button></td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td><input type="text" placeholder="商品数" ></td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td colspan="8"><button class="btn btn-primary btn-place">＋</button></td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>小計</th>
								<td class="price">999,999,999</td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>消費税（8%）</th>
								<td class="price">9,999,999</td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>合計</th>
								<td class="price">9,999,999,999</td>
							</tr>
						</tbody>
					</table>
					<div class="button">
						<button type="button" class="btn btn-danger cancel-btn">キャンセル</button>
						<button type="button" class="btn btn-primary success-btn">確認</button>
					</div>
			</div>
		</div>
  </body>
</html>