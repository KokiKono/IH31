/***************************
 * 学籍番号:40281
 * 作成者　:m.haruka
 * 作成日　:2016/11/08
 * 内容　　:新規見積書作成クラス。
 * *************************/
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Constants;
import dtd.Estimate;
import dtd.EstimateDetail;

/**
 * Servlet implementation class EstimatesNewCreatingServlet
 */
@WebServlet("/EstimatesNewCreatingServlet")
public class EstimatesNewCreatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstimatesNewCreatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Constants constants = new Constants(this, request);
		Estimate est = new Estimate();
		EstimateDetail estDtl = new EstimateDetail();

		//SQL読み込み
		//PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this, "OrderRecodeList.sql"));

		/*
		 * 入力チェック
		 */



		/*
		 * 受注情報のInsert
		 */


		/*
		 * 受注明細のInsert
		 */


	}

}
