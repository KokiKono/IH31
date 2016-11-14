package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Constants;
import beans.Constants.Action;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import beans.Message;

import common.Database;

import dtd.CutDay;
import dtd.DoClaim;

/**
 * Servlet implementation class DoClaimServlet
 */
@WebServlet("/DoClaimServlet")
public class DoClaimServlet extends HttpServlet implements Database{
	private static final long serialVersionUID = 1L;

	/**
	 * このサーブレット内で処理分岐で共通した処理時に使用するグローバルコンスタント。
	 * @auther 浩生
	 * 2016/11/14
	 * @param constants Constants
	 */
	private Constants constants;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoClaimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//jspに表示する締日を送信する。
		this.constants=new Constants(this,request);
		setCutDay();
		this.constants.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//コンスタントの生成
		this.constants=new Constants(this, request);
		//現在のモードを取得
		Action action=this.constants.getMode();
		//セレクトボックスの設定
		setCutDay();
		switch(action){
			case Search:
				actionSearch(response);
				break;
		}
	}
	/**
	 * 検索モードでの
	 * 検索処理
	 * @auther 浩生
	 * 2016/11/14
	 *
	 */
	private void actionSearch(HttpServletResponse response){
		DBManager dbManager=null;
		PreparedStatementByKoki statementByKoki=null;
		//検索条件の取得
		DoClaim search=new DoClaim();
		search=(DoClaim) this.constants.superDecodeRequest(search);
		Message message=new Message(this.constants);
		try{
			dbManager=new DBManager(DBName);
			statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this.constants.getServlet(), "SearchDoClaim.sql"));
			//------------入力チェック-----------
			//顧客IDのチェック
			if(search.customerId==null){
				//顧客IDが取得不能
				statementByKoki.toNull("CUSTOMER_ID");
			}else if(search.customerId.isEmpty()==true){
				//顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			}else{
				statementByKoki.setString("CUSTOMER_ID", search.customerId);
			}
			//締日
			if(search.cutOfDay==null){
				//締日が取得不能
				statementByKoki.toNull("CUT_DATE");
			}else if(search.cutOfDay.isEmpty()==true){
				//締日が未選択
				statementByKoki.toNull("CUT_DATE");
			}else{
				statementByKoki.setString("CUT_DATE", search.cutOfDay);
			}
			statementByKoki.cleanSql();
			System.out.println(statementByKoki.out());
			//SQLデータ
			ArrayList<ArrayList<String>> list=statementByKoki.select();
			//レスポンス用変数
			ArrayList<DoClaim> claims=new ArrayList<DoClaim>();
			//SQLデータをレスポンス用に加工
			for(ArrayList<String> row:list){
				DoClaim claim=new DoClaim();
				claim.customerId=row.get(0);
				claim.noTaxTotalFee=Integer.parseInt(row.get(1));
				claim.taxFee=Integer.parseInt(row.get(2));
				claim.customerName=row.get(3);
				claim.cutDay.value=row.get(4);
				claim.recallManner=Integer.parseInt(row.get(5));
				claim.overPrice=Integer.parseInt(row.get(6));
				claims.add(claim);
			}
			HttpServletRequest request =this.constants.getRequest();
			request.setAttribute("claimList", claims);
			//フォワード処理
			this.constants.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 締日のセレクトボックスデータをリクエストに保存するメソッド
	 * このメソッドを呼ぶ前にコンスタントをnewして下さい。
	 * @auther 浩生
	 * 2016/11/14
	 * @param servlet
	 * @param request
	 * @param response
	 * @see DoClaimServlet#constants
	 */
	private void setCutDay(){
		HttpServletRequest request=this.constants.getRequest();
		try{
			DBManager dbManager=new DBManager(DBName);
			ArrayList<CutDay> cutDays=new ArrayList<CutDay>();
			for(ArrayList<String> row:dbManager.runSelect(InspectionValue.readSql(this, "SelectCutDay.sql"))){
				CutDay cutDay=new CutDay();
				cutDay.value=row.get(0);
				cutDays.add(cutDay);
			}
			request.setAttribute("cutDays", cutDays);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
