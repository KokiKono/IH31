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
import dtd.SubdivisionDetail;
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
		String value = "";
		value = request.getParameter("value");
		String method = request.getParameter("method");
		ArrayList<ArrayList<String>> list;
		HashMap<String, ArrayList<Subdivision>> date = new HashMap<String, ArrayList<Subdivision>>();
		HashMap<String, ArrayList<SubdivisionDetail>> date2 = new HashMap<String, ArrayList<SubdivisionDetail>>();
		JSON json = new JSON();
		PrintWriter out=response.getWriter();
		
		switch(method){
			case "picking":
				SQL = "";
				ArrayList<PickingList> returnPick = new ArrayList<PickingList>();
				PickingList pick = new PickingList();
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
				ArrayList<Subdivision> returnSub = new ArrayList<Subdivision>();
				Subdivision sub = new Subdivision();
				date = new HashMap<String, ArrayList<Subdivision>>();
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
				out.println(json.encode(date));
				break;
			case "orderDetail":
				SQL = "select num, puroduct_id, puroduct_name, amount, step from order_details_table where order_id = "+value+"";
				ArrayList<SubdivisionDetail> returnSubDeti = new ArrayList<SubdivisionDetail>();
				SubdivisionDetail subde = new SubdivisionDetail();
				try{
					DBManager db = new DBManager(Database.DBName);
					list = db.runSelect(SQL);
					for(ArrayList<String> row:list){
						subde = new SubdivisionDetail();
						subde.num = row.get(0);
						subde.productNumber = row.get(1);
						subde.productName = row.get(2);
						subde.amount = row.get(3);
						subde.setStep(row.get(4));
						returnSubDeti.add(subde);
						
					}
					date2.put("date", returnSubDeti);
				}catch(Exception e){
					e.printStackTrace();
				}
				out.println(json.encode(date2));
				break;

		}
		
		
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
