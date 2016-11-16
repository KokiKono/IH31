/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/16
 * 内容　　:請求済みを一覧する。
 * *************************/
package servlet;

import java.io.IOException;
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

import dtd.Settlement;

/**
 * Servlet implementation class ClaimListServlet
 */
@WebServlet("/ClaimListServlet")
public class ClaimListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClaimListServlet() {
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
		Constants constants = new Constants(this, request);
		// 検索条件の取得
		Settlement search = new Settlement();
		search = (Settlement) constants.decodeRequest(search);
		DBManager dbManager = null;
		// 検索条件チェック
		try {
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"SearchSettlement.sql"));
			// 顧客IDのチェック
			if (search.customerId == null) {
				// 顧客IDが取得不能
				statementByKoki.toNull("CUSTOMER_ID");
			} else if (search.customerId.isEmpty() == true) {
				// 顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			} else {
				statementByKoki.setString("CUSTOMER_ID", search.customerId);
			}
			//請求日のチェック
			if (search.settleYear.isEmpty() == false
					&& search.settleMonth.isEmpty() == false
					&& search.settleDay.isEmpty() == false) {
				CalendarByKoki shipmentCalendar = CalendarByKoki.newInstance(
						search.settleYear,
						search.settleMonth,
						search.settleDay);
				if (shipmentCalendar == null) {
					// 出荷日生成エラー
					statementByKoki.toNull("REQUEST_DATE");
				} else {
					statementByKoki.setString("REQUEST_DATE",
							shipmentCalendar.outSQLDate());
				}
			}else{
				statementByKoki.toNull("REQUEST_DATE");
			}

			//SQLクリーン
			statementByKoki.cleanSql();
			System.out.println(statementByKoki.out());
			//SQL実行
			ArrayList<ArrayList<String>> list=statementByKoki.select();
			if(list.size()==0){
				//検索結果なし
			}else{
				//レスポンス用変数
				ArrayList<Settlement> settlements=new ArrayList<Settlement>();
				for(ArrayList<String> row:list){
					Settlement settlement=new Settlement();
					settlement.settlementId=Integer.parseInt(row.get(0));
					settlement.customerId=row.get(1);
					settlement.totalFee=Integer.parseInt(row.get(3));
					settlement.consumptionTax=Integer.parseInt(row.get(4));
					settlement.requestDate=new CalendarByKoki(row.get(5));
					settlement.paymentDate=CalendarByKoki.newInstance(row.get(6));
					settlement.customerName=row.get(7);
					settlement.recallManner=Integer.parseInt(row.get(8));
					settlements.add(settlement);
				}
				request.setAttribute("claimList", settlements);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		constants.forward(request, response);
	}

}
