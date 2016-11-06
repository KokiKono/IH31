package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;

import common.Database;

/**
 * Servlet implementation class OrderRecodeListServlet
 */
@WebServlet("/OrderRecodeListServlet")
public class OrderRecodeListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderRecodeListServlet() {
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
		response.setCharacterEncoding("UTF-8");
		
		// 検索条件の取得
		// 顧客ID
		String strCustomerID = (String) request.getParameter("customer_id");
		// 顧客名
		String strCustomerName = (String) request.getParameter("customer_name");
		// 作成年
		String strCreate_year = (String) request.getParameter("create_year");
		// 作成月
		String strCreate_month = (String) request.getParameter("create_year");
		// 作成日
		String strCreate_day = (String) request.getParameter("create_day");
		// 発送状態
		String strDispatch_state = (String) request
				.getParameter("dispatch_state");
		// 請求状態
		String strClaim_state = (String) request.getParameter("claim_state");
		try {
		////////////////////////////////////////////////////////////////////
		// DB接続
		//-------------------------------------------------------------------
		DBManager dbManager=new DBManager(DBName);
		PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this, "OrderRecodeList.sql"));
			//////////////////////////////////////////////////
			// 入力チェック
			// -------------------------------------------------
			// 顧客IDの数値チェック
			boolean errFlg = false;
			if (strCustomerID.isEmpty()) {
				// 顧客IDが未入力の場合。whereを省く
				statementByKoki.toNullAll("CUSTOMER_ID");
				statementByKoki.toNull("2");

			} else if (!InspectionValue.inspectionInteger(strCustomerID)) {
				// エラー
				errFlg = true;
			}else{
				//成功時パラメータをセットする。
				statementByKoki.setString("CUSTOMER_ID", strCustomerID);
			}
			// 作成年月日の数値チェック
			if(!strCreate_year.isEmpty() && !strCreate_month.isEmpty() && !strCreate_day.isEmpty()){
				//空白でない時
				//作成年月日チェックフラグ：trueで成功
				boolean createFlg=true;
				if (!InspectionValue.inspectionInteger(strCreate_year)) {
					createFlg=false;
				}
				if (!InspectionValue.inspectionInteger(strCreate_month)) {
					createFlg=false;
				}
				if (!InspectionValue.inspectionInteger(strCreate_day)) {
					createFlg=false;
				}
				if(createFlg){
					//成功時パラメータを設定
					statementByKoki.setString("ORDER_DATE",strCreate_year+"-"+strCreate_month+"-"+strCreate_day);
				}else{
					//数値以外の値が来た場合、where削除
					statementByKoki.toNull("ORDER_DATE");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//ページ遷移処理
		}


	}

	private class ErrMessage {
		public String customerID;
		public String customerName;
		public String createYear;
		public String createMonth;
		public String createDay;
		public String dispatchState;
		public String claimState;
	}

}
