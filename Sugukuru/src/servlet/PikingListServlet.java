package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import common.Database;
import dtd.PickingList;
import dtd.Subdivision;
import net.arnx.jsonic.JSON;;

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
		
		String SQL = "";
//		String SQL = request.getParameter("sql");
		String method = "order";//request.getParameter("method");
		ArrayList<ArrayList<String>> list;
		PickingList pick = new PickingList();
		Subdivision sub = new Subdivision();
		ArrayList<PickingList> returnPick = new ArrayList<PickingList>();
		ArrayList<Subdivision> returnSub = new ArrayList<Subdivision>();
		HashMap<String, ArrayList<Subdivision>> date = new HashMap<String, ArrayList<Subdivision>>();
		switch(method){
			case "picking":
				SQL = "";
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
						returnPick.add(pick);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "order":
				SQL = "select order_id,costomer_name,order_date FROM order_table";
				try{
					DBManager db = new DBManager(Database.DBName);
					list = db.runSelect(SQL);
					for(ArrayList<String> row:list){
						sub = new Subdivision();
						sub.orderId = row.get(0);
						System.out.println(row.get(0));
						sub.customreName = row.get(1);
						System.out.println(row.get(1));
//						sub.customerNameKana = row.get(2);
//						System.out.println(row.get(2));
						sub.orderDate = row.get(2);
						System.out.println(row.get(2));
//						sub.orderState = 1;
//						System.out.println(row.get(4));
						returnSub.add(sub);
						
					}
					date.put("date", returnSub);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
		}
		
		
		JSON json = new JSON();
		PrintWriter out=response.getWriter();
		out.println(json.encode(date));
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
