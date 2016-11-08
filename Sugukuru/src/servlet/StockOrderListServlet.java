/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/06
 * 内容　　:倉庫部の受注一覧を生成するクラス。
 * *************************/
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CalendarByKoki;
import beans.Constants;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;

import common.Database;

import dtd.OrderDetail;
import dtd.StockOrderList;

/**
 * Servlet implementation class StockOrderListServlet
 */
@WebServlet("/StockOrderListServlet")
public class StockOrderListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockOrderListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Constants constants=new Constants(this,request);
		constants.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Constants constants = new Constants(this, request);
		StockOrderList stockOrderList = new StockOrderList();
		// 検索条件の取得
		stockOrderList = (StockOrderList) constants
				.decodeRequest(stockOrderList);
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"StockOrderList.sql"));
			// /////////////////
			// 入力チェック
			// --------------------
			// 受注ID
			if (stockOrderList.rOrderId == null) {
				// 受注ID取得不能
				statementByKoki.toNull("ORDER_ID");
			} else {
				// 受注ID不正値
				try {
					statementByKoki.setInt("ORDER_ID",
							Integer.parseInt(stockOrderList.rOrderId));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					statementByKoki.toNull("ORDER_ID");
				}
			}
			// 顧客ID
			if (stockOrderList.customerId == null) {
				// 顧客ID取得ID
				statementByKoki.toNull("CUSTOMER_ID");
			} else {
				try {
					Integer.parseInt(stockOrderList.customerId);
					statementByKoki.setString("CUSTOMER_ID",
							stockOrderList.customerId);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					statementByKoki.toNull("CUSTOMER_ID");
				}
			}
			// 出荷日時
			CalendarByKoki shipmentCalendar = CalendarByKoki.newInstance(
					stockOrderList.shipmentYear, stockOrderList.shipmentMonth,
					stockOrderList.shipmentDay);
			if (shipmentCalendar == null) {
				// 出荷日生成エラー
				statementByKoki.toNull("DELIVERY_DATE");
			} else {
				statementByKoki.setString("DELIVERY_DATE",
						shipmentCalendar.outSQLDate());
			}
			// SQLクリーン
			statementByKoki.cleanSql();
			System.out.println(statementByKoki.out());
			// SQL実行
			ArrayList<ArrayList<String>> list = statementByKoki.select();
			if (list.size() == 0) {
				// 検索結果無し
			} else {
				// レスポンス用リスト変数
				ArrayList<StockOrderList> stockOrderLists = new ArrayList<StockOrderList>();
				// 検索結果を成形格納
				try {
					for (int rowCount = 0; rowCount < list.size(); rowCount++) {
						StockOrderList stockOrderList2=new StockOrderList();
						stockOrderList2.orderId = Integer.parseInt(list.get(
								rowCount).get(0));
						stockOrderList2.customerId = list.get(rowCount).get(1);
						stockOrderList2.customerName = list.get(rowCount).get(2);
						details: while (stockOrderList2.orderId == Integer
								.parseInt(list.get(rowCount).get(0))) {
							// 詳細を格納
							OrderDetail detail = new OrderDetail();
							detail.orderId = stockOrderList2.orderId;
							detail.num = Integer.parseInt(list.get(rowCount)
									.get(3));
							detail.productId = list.get(rowCount).get(4);
							detail.productName = list.get(rowCount).get(5);
							detail.price = Integer.parseInt(list.get(rowCount)
									.get(6));
							detail.cunsumntionTax = Double.parseDouble(list
									.get(rowCount).get(7));
							detail.amount = Integer.parseInt(list.get(rowCount)
									.get(8));
							detail.step = Integer.parseInt(list.get(rowCount)
									.get(9));
							if (list.get(rowCount).get(10) == null) {
								// 納品日がnull（未納品）の場合
								detail.deliveredDate = null;
							} else {
								detail.deliveredDate = new CalendarByKoki(list
										.get(rowCount).get(10));
							}
							detail.productDeliveredFlg = Integer.parseInt(list
									.get(rowCount).get(11));
							detail.note = list.get(rowCount).get(12);
							stockOrderList2.orderDetails.add(detail);
							if (rowCount+1 == list.size()) {
								// 最後の行処理
								stockOrderList2.orderDetails.add(detail);
								break details;
							}
							rowCount++;
						}
						stockOrderLists.add(stockOrderList2);
					}
				} catch (Exception e) {
					// 格納失敗
					e.printStackTrace();
				}
				if(stockOrderLists.size()>0){
					//リクエスト格納
					request.setAttribute("stockOrderList", stockOrderLists);
				}
			}
		} catch (SQLException e) {
			// SQLエラー
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// ロードエラー
			e.printStackTrace();
		} catch (Exception e) {
			// どうしようもないエラー
			e.printStackTrace();
		}finally{
			//フォワード処理
			constants.forward(request, response);
		}

	}
}
