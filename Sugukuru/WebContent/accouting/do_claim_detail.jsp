<%@page import="dtd.EarningsDetail"%>
<%@page import="beans.InspectionValue"%>
<%@page import="dtd.DoClaim"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<DoClaim> printList=(ArrayList<DoClaim>)request.getAttribute("printList");

%>
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
<script src="<%=request.getContextPath()%>/accouting/js/printThis.js"></script>
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
<%for(DoClaim print:printList){ %>
<article class="sett">
<section class="claim_parent">
	<table class="table table-bordered">
		<tr><th colspan="5">請求書</th>
			<td rowspan="2">
				発行日：<%=print.printDate.outOfJP() %><br>
				請求No：<%=print.settlementId %><br>
				スグクル株式会社<br>
				　　経理部　河野　浩生固定<br>
			</td>
		</tr>
		<tr>
			<td class="title"><%=print.customerName %>　御中</td>
			<td colspan="4" class="title"></td>
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
			<td class="price"><%=InspectionValue.doLocaleJP(print.settlementBefore) %></td>
			<td class="price"><%=InspectionValue.doLocaleJP(print.payment)%></td>
			<td class="price"><%=InspectionValue.doLocaleJP(print.overPrice) %></td>
			<td class="price"><%=InspectionValue.doLocaleJP(print.noTaxTotalFee) %></td>
			<td class="price"><%=InspectionValue.doLocaleJP(print.taxFee) %></td>
			<td class="price"><%=InspectionValue.doLocaleJP(print.getAllTotal()) %></td>
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
				発行日：<%=print.printDate.outOfJP() %><br>
				請求No：<%=print.settlementId %><br>
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
		<%for(EarningsDetail detail:print.details){ %>
		<tr>
			<td><%=detail.buyDate.getMonth() %></td>
			<td><%=detail.buyDate.getDay() %></td>
			<td><%=detail.puroductName %></td>
			<td><%=detail.orderId %></td>
			<td><%=detail.soldAmount %></td>
			<td class="price"><%=detail.price %></td>
			<td class="price"><%=detail.getNoTaxTotalFee() %></td>
			<td class="price"><%=detail.getTaxTotal() %></td>
			<td class="price"><%=detail.getTotalFee() %></td>
		</tr>
		<%} %>
	</table>
</section>
</article>
<%} %>
<div class="print_button"><input type="button" name="print" class="btn btn-primary" value="印刷"></div>
</body>
</html>