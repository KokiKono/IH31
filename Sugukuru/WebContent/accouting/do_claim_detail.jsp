<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- BootstrapのCSS読み込み -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<!-- テンプレート用CSSの読み込み -->
<link href="<%=request.getContextPath()%>/css/template.css"
	rel="stylesheet">
<!-- サブメニュー用CSSの読み込み -->
<link href="<%=request.getContextPath()%>/css/dropdowns-enhancement.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/dropdowns-enhancement.js"></script>
<link href="<%=request.getContextPath()%>/stock/css/index.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/accouting/css/do_claim_detail.css"
	rel="stylesheet">
<title>請求書印刷プレビュー</title>
<script src="js/printThis.js"></script>
<script type="text/javascript">
	$(function(){
		//印刷ボタンを押した時
		$('input[name=print]').on('click',function(){
			//プリントエリア取得
			$('.sett').printThis();
		});
	});
</script>
</head>
<body>
<!-- 顧客別請求書 -->
<article class="sett">
<section class="claim_parent">
	<table class="table table-bordered">
		<tr><th colspan="5">請求書</th>
			<td rowspan="2">
				発行日：２０１６年０５月２０日<br>
				請求No：００００１２<br>
				スグクル株式会社<br>
				　　経理部　河野　浩生<br>
			</td>
		</tr>
		<tr>
			<td class="title">学校法人HAL大阪　御中</td>
			<td colspan="4" class="title">(2016年５月分)</td>
		</tr>
		<tr>
			<th>前回請求額</th>
			<th>入金額</th>
			<th>繰り越し金額</th>
			<th>お買い上げ額</th>
			<th>消費税</th>
			<th>請求額</th>
		<tr>
		<tr>
			<td class="price">3,150,000</td>
			<td class="price">3,150,000</td>
			<td class="price">0</td>
			<td class="price">370,000</td>
			<td class="price">18,500</td>
			<td class="price">388,500</td>
		</tr>
	</table>
</section>
<!-- 顧客別請求書明細 -->
<section class="claim_detail">
	<table class="table table-bordered">
		<tr>
			<th colspan="4">請求の明細は次の通りです。
			<th colspan="3">請求明細書</th>
			<td colspan="2">
				発行日：２０１６年０５月２０日<br>
				請求No：００００１２<br>
			</td>
		</tr>
		<tr>
			<th>月</th>
			<th>日</th>
			<th>内容</th>
			<th>受注№</th>
			<th>数量</th>
			<th>単価</th>
			<th>税抜額</th>
			<th>消費税</th>
			<th>明細計</th>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>前月繰越</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>鉛筆</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>シャーペン</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>1</td>
			<td>上島</td>
			<td>100211</td>
			<td class="price">20</td>
			<td class="price">18,000</td>
			<td class="price">360,000</td>
			<td class="price">18,000</td>
			<td class="price">378,000</td>
		</tr>

	</table>
</section>
</article>
<div class="print_button"><input type="button" name="print" class="btn btn-primary" value="印刷"></div>
</body>
</html>