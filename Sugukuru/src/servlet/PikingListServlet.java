package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import common.Database;
import dtd.PickingList;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class PikingListServlet
 */
@WebServlet("/PikingListServlet")
public class PikingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PikingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		String SQL = "SELECT * from category_master";
//		String SQL = request.getParameter("sql");
		ArrayList<ArrayList<String>> list;
		PickingList pick = new PickingList();
		ArrayList<PickingList> returnJSON = new ArrayList<PickingList>();
		
		try{
			DBManager db = new DBManager(Database.DBName);
			list = db.runSelect(SQL);
			for(ArrayList<String> row:list){
				pick.pickId = row.get(0);
				pick.productId = row.get(1);
				pick.productName = row.get(2);
				pick.rackNumber = Integer.parseInt(row.get(3));
				pick.pickNum = Integer.parseInt(row.get(4));
				pick.pickState = row.get(5);
				returnJSON.add(pick);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JSON json = new JSON();
		PrintWriter out=response.getWriter();
		out.println(json.encode(returnJSON));
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
