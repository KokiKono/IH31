package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class SalesIndexServlet
 */
@WebServlet("/SalesIndexServlet")
public class SalesIndexServlet extends HttpServlet implements Database{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			DBManager dbManager=new DBManager(DBName);
			PrintWriter out=response.getWriter();
			PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this, "OrderRecodeList.sql"));
			out.println(statementByKoki.out());
			out.println("Statement_開始");
			statementByKoki.setString("CUSTOMER_ID", "00012");
			statementByKoki.setString("ORDER_DATE", "2016-11-04");
			statementByKoki.setString("USER_NAME", "河野%");
			out.println(statementByKoki.out());
			out.flush();
			out.close();
		}catch(Exception exception){
			exception.printStackTrace();
		}

	}

}
