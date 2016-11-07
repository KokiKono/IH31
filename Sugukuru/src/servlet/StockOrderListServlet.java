/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/06
 * 内容　　:倉庫部の受注一覧を生成するクラス。
 * *************************/
package servlet;

import java.io.IOException;

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
				statementByKoki.toNullAll("CUSTOMER_ID");
			} else {
				try {
					Integer.parseInt(stockOrderList.customerId);
					statementByKoki.setString("CUSTOMER_ID",
							stockOrderList.customerId);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					statementByKoki.toNullAll("CUSTOMER_ID");
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
		} catch (Exception e) {

		}
	}
}
