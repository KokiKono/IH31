/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/07
 * 内容　　:AndroidからSQLを受け取りDBの情報をJSONで返す
 * *************************/
package beans;

import java.util.ArrayList;

import common.Database;
import dtd.PickingList;

public class RequestPickingList {
	private static String DBName = Database.DBName;
	static PickingList pick = new PickingList();
	
	
	public ArrayList<String> runJson(String SQL){
		DBManager db = new DBManager(DBName);
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result = db.runSelect(SQL);
		
		
		
		db.closeDB();
		return json;
	}
	
}
