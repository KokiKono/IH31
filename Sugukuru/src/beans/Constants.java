/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/05
 * 内容　　:Viewで表示する項目名をDBから取得するクラス。
 * *************************/
package beans;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager.PreparedStatementByKoki;

import common.Database;

import dtd.Request;


public class Constants implements Database{
	/**
	 * 遷移元、遷移先、DB№を管理します。
	 * @author 浩生
	 *
	 */
	public enum Page{
		OrderList_jsp("order_list.jsp","StockOrderListServlet","02"),
		OrderList_ser("StockOrderListServlet","stock/order_list.jsp","02"),
		OrderRecodeList_jsp("order_recode_list.jsp","OrderRecodeListServlet","01"),
		OrderRecodeList_ser("OrderRecodeListServlet","sales/order_recode_list","01");
		/**
		 * 呼び出し元ページを示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param from String
		 */
		public String from;
		/**
		 * 遷移先ページを示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param to String
		 */
		public String to;
		/**
		 * DBの主キー２桁を示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param index String
		 */
		public String index;
		private Page(String view,String servlet,String index){
			this.from=view;
			this.to=servlet;
			this.index=index;
		}
		/**
		 * ページからそれに対応する定数を返します。
		 * null指定でcommon変数を変えします。
		 * @auther 浩生
		 * 2016/11/07
		 * @param from
		 * @return
		 */
		public static Page indexOf(String from){
			for(Page page:values()){
				if(page.from.equals(from)){
					return page;
				}
			}
			return null;
		}
	}
	private HttpServlet servlet;
	private Page page;
	private HttpServletRequest request;
	/**
	 * このクラスを呼び出したファイルを示します。
	 * @auther 浩生
	 * 2016/11/06
	 * @param from String
	 */
	private String from;
	private ArrayList<Constant> constantList;
	private Page now;
	/**
	 * 個々のコンスタント情報を持つ箱クラス。
	 * @author 浩生
	 *
	 */
	public class Constant{
		public String constantId;
		public String value;
		public String pgName;
		{
			this.constantId="";
			this.value="";
			this.pgName="";
		}
	}
	{
		//初期化
		this.constantList=new ArrayList<Constants.Constant>();
	}
	/**
	 * requestからファイル名を読み込み、各ページで定義される定数を持つConstantsを生成します。
	 * @param servlet
	 * @param request
	 */
	public Constants(HttpServlet servlet,HttpServletRequest request){
		this.servlet=servlet;
		this.request=request;
		//requestからPage.fromを取得しindex、toを取得します。
		this.page=Page.indexOf(getPage(request.getRequestURI()));
		//DBから値を取得し、コンスタントリストに格納する。
		getConstants();
	}
	/**
	 * xxxx/xxx/xxで示されたURIの末尾を返します。
	 * @param uri
	 * @return
	 */
	private String getPage(String uri){
		String uris[]=uri.split("/");
		return uris[uris.length-1];
	}
	/**
	 * DBからコンスタントリストを取得しフィールドに格納します。
	 */
	private void getConstants(){
		try{
			DBManager dbManager=new DBManager(DBName);
			PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this.servlet, "SelectConstants.sql"));
			statementByKoki.setString("CONSTANT_ID", this.page.index+"%");
			for(ArrayList<String> row:statementByKoki.select()){
				Constant constant=new Constant();
				constant.constantId=row.get(0);
				constant.value=row.get(1);
				constant.pgName=row.get(2);
				this.constantList.add(constant);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.constantList=null;
		}
	}
	/**
	 * コンスタントIDの下２桁からコンスタント情報を取得します。
	 * @param key　下２桁
	 * @return　エラー時は空のコンスタント情報を返します。
	 */
	public Constant getConstant(String key){
		try{
			if(this.constantList==null)new ConstantException();
			for(Constant constant:this.constantList){
				if(constant.constantId.equals(this.page.index+key)){
					return constant;
				}
			}
			new NotFoundConstant();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Constant();
	}
	public class ConstantException extends Exception{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "コンスタントリストが設定されていません。";
		}
	}
	public class NotFoundConstant extends ConstantException{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "指定keyではコンスタントは見つかりませんでした。";
		}
	}
	/**
	 * サーブレット内のみで使用できるメソッド
	 * 設定された遷移先にフォワードする処理。
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void forward(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String forwardUrl="http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+this.page.to;
		RequestDispatcher rd=request.getRequestDispatcher(forwardUrl);
		rd.forward(request, response);
	}
	/**
	 * requestからdtdインスタンスへ値をセットするメソッド
	 * このメソッドはRequestクラスの該当フィールド(String)にdtdアノテーションを付与し
	 * pgNameとフィールド名が一致しないとセットされません。
	 * @param object
	 * @return
	 */
	public Object decodeRequest(Object object){
		//オブジェクトクラスに変数設定
		object=settingField(object, object.getClass().getDeclaredFields());
		return object;
	}
	/**
	 * objectにfieledsを設定する。
	 * @param object
	 * @param fields
	 * @return
	 */
	private Object settingField(Object object,Field[] fields){
		for(Field field:fields){
			if(field.getAnnotation(Request.class)!=null){
				for(Constant constant:this.constantList){
					System.out.println(constant.pgName+"=="+field.getName());
					if(constant.pgName.equals(field.getName())){
						field.setAccessible(true);
						try {
							field.set(object, (String)this.request.getParameter(constant.pgName));
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}
		}
		return object;
	}

	/**
	 * superクラスがなくなるまで探索する。
	 * @param object
	 * @return
	 */
	public Object superDecodeRequest(Object object){
		object=settingField(object, object.getClass().getDeclaredFields());
		Class superClass=object.getClass().getSuperclass();
		while(superClass!=null && !superClass.equals(Object.class)){
			object=settingField(object, superClass.getDeclaredFields());
			superClass=superClass.getSuperclass();
		}
		//object=settingField(object, object.getClass().getSuperclass().getDeclaredFields());
		return object;
	}
	/**
	 * 送信先サーブレット先のURLを取得します。
	 * @auther 浩生
	 * 2016/11/07
	 * @return
	 * @throws UnknownHostException
	 */
	public String getServletUrl() throws UnknownHostException{
		return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+this.page.to;
	}

}
