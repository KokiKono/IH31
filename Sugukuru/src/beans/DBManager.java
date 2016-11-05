/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2015/11/25
 * 内容　　:mysqlデータベース接続に関するクラス。
 * *************************
 * 更新日　:2015/12/08
 * 更新者　:k.koki
 * 内容　　:mysqlのautoCommit関連を制御するメソッドの追加とロールバック機能の追加。
 * *************************
 * 更新日　:2016/01/21
 * 更新者　:k.koki
 * 内容　　:<,>,",',&を含むSQLを置換し文字コードかする。
 * *************************
 * 更新日　:2016/02/04
 * 更新者　:k.koki
 * 内容　　:PreparedStatementに関するメソッドを追加
 * *************************
 * 更新日　:2016/11/03
 * 更新者　:k.koki
 * 内容　　:プリペアドステイトメント関係のクラス、メソッドを追加。
 * *************************/


package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
	private static String _forName = "com.mysql.jdbc.Driver";
	private static String _DriverURL = "jdbc:mysql://localhost:3306/";
	private static String _User = "root";
	private static String _DBName = "root";
	private static String _Password = "";
	private Connection _con;
	private Statement _st;
	private ResultSet _rs;
	private ResultSetMetaData _rsMeta;
	private int _ColumnCount;

	public void SetDB() throws ClassNotFoundException, SQLException {
		//closeDB();
		//System.out.println("forName:" + _forName);
		//System.out.println("DriverURL:" + _DriverURL);
			Class.forName(_forName);
			this._con = DriverManager.getConnection(_DriverURL + _DBName,
					DBManager._User, _Password);
			this._st = this._con.createStatement();
	}

	/**
	 * デフォルトコンストラクタ Class.forName("com.mysql.jdbs.Driver")
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名,"root","")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName) throws ClassNotFoundException, SQLException {
		_DBName = strDBName;
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバーURL")
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名,"root","")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName,String strForName) throws ClassNotFoundException, SQLException {
		_DBName = strDBName;
		_forName = strForName;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー名");
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名","任意のユーザー","")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName,String strForName,String strUser) throws ClassNotFoundException, SQLException {
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー名");
	 * DriverManager("任意のDriverManager/任意のDB名","任意のユーザー","")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName,String strForName,String strUser,String strDriverManager) throws ClassNotFoundException, SQLException {
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_DriverURL=strDriverManager;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー");
	 * DriverManager("任意のDriverManager/任意のDB名","任意のユーザー","任意のパスワード")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName,String strForName,String strUser,String strDriverManager,String strPassword) throws ClassNotFoundException, SQLException {
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_DriverURL=strDriverManager;
		_Password = strPassword;
		SetDB();
	}

	/**
	 * クローズ用メソッド
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException {
			if (this._rs != null) {
				this._rs.close();
			}
			if (this._st != null) {
				this._st.close();
			}
			if (this._con != null) {
				this._con.close();
			}
	}

	/**
	 * SELECT文実行用メソッド
	 *
	 * @return 二次元配列String型
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> runSelect(String strSQL) throws SQLException {
		//SQLインジェクション処理
		//strSQL=replaceSQL(strSQL);
		//SetDB();
		// 戻り値用二次元可変長配列の生成。
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
			this._rs = this._st.executeQuery(strSQL);
			_rsMeta = this._rs.getMetaData();
			// 列数の数
			this._ColumnCount = _rsMeta.getColumnCount();
			// データの取得
			while (this._rs.next()) {
				// 一時格納用可変長配列の生成。
				ArrayList<String> stockList = new ArrayList<String>();
				for (int i = 1; i <= this._ColumnCount; i++) {
					stockList.add(this._rs.getString(i));
				}
				returnList.add(stockList);
			}
			//returnList=getResultSet(this._rs);
		return returnList;
	}

	/**
	 * 更新系SQL文を行うメソッド。
	 *
	 * @param String
	 *            SQL文
	 * @param int 更新件数
	 * @throws SQLException
	 */
	public int update(String strSQL) throws SQLException {
		//SQLインジェクション処理
		//strSQL=replaceSQL(strSQL);
		//SetDB();
		int n = 0;
			n = this._st.executeUpdate(strSQL);
		//closeDB();
		return n;
	}

	/**
	 * 二次元配列String型をMap<int,ArrayList<String>>に変換する。 intは0から始まる。
	 */
	public Map<Integer, ArrayList<String>> aryOFmap(
			ArrayList<ArrayList<String>> ary) {
		// 戻り値用Map変数
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		int i = 0;
		for (ArrayList<String> list : ary) {
			map.put(i++, list);
		}
		return map;
	}

	// update k.koki 2015/12/08 begin
	/**
	 * mysqlのautoCommitのON,OFFを指定するメソッドです。
	 *
	 * @param ON
	 *            =true OFF=false
	 * @throws SQLException
	 */
	public void setautoCommit(boolean autoCommit) throws SQLException {
			this._con.setAutoCommit(autoCommit);
	}

	/**
	 * mysqlのautoCommitの状態を取得します。
	 * @throws SQLException
	 */
	public boolean getautoCommit() throws SQLException {
		boolean bl = false;
			bl=this._con.getAutoCommit();
		return bl;
	}

	/**
	 * mysqlのCommitを行います。
	 * autoCommitがONの時はOFFにし再度実行しONにします。
	 * @throws SQLException
	 */
	public void runCommit() throws SQLException {
			if (!getautoCommit()) {
				this._con.commit();
			}else{
				setautoCommit(false);
				runCommit();
			}

	}
	/**
	 * mysqlのロールバックを行う機能です。
	 * Connectionをcolseしていた場合又はautoCommitを自動にしていた場合何も処理されません。
	 * @throws SQLException
	 */
	public void rollback() throws SQLException{
		//System.out.println("ロールバック");
			if(!getautoCommit()){
				if(this._con!=null){
					this._con.rollback();
				}
			}
	}
	// update k.koki 2015/12/08 end
	//update k.koki 2016/01/21 begin
	public String replaceSQL(String strSQL){
		strSQL=strSQL.replaceAll("<","&lt;" );
		strSQL=strSQL.replaceAll(">", "&gt;");
		strSQL=strSQL.replaceAll("\"", "&quot;");
		strSQL=strSQL.replaceAll("&", "&amp;");
		strSQL=strSQL.replaceAll("'", "&rsquo;");
		return strSQL;
	}
	//update k.koki 2016/01/21 end
	//updata k.koki 2016/02/04 begin
	/**
	 * 作成日:2016/02/04 11:43:30
	 * 作成者:k.koki
	 * @param String SQLをPrepardStatement形式に変更します。
	 * @param strSQL
	 * @return SQLExceptionが起きた場合、nullをreturnします。
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String strSQL) throws SQLException{
		//SetDB();

		return this._con.prepareStatement(strSQL);

	}
	/**
	 * 作成日:2016/02/04 11:51:00
	 * 作成者:k.koki
	 * @param PrepardStatement更新処理を実行します。
	 * @param sql
	 * @return 更新件数
	 * @throws SQLException
	 */
	public int update(PreparedStatement sql) throws SQLException{
		int count=0;
		count= sql.executeUpdate();
		return count;
	}
	private ArrayList<ArrayList<String>> getResultSet(ResultSet rs) throws SQLException{
		// 戻り値用二次元可変長配列の生成。
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		ResultSetMetaData rsMeta = rs.getMetaData();
		// 列数の数
		int ColumnCount = rsMeta.getColumnCount();
		// データの取得
		while (rs.next()) {
			// 一時格納用可変長配列の生成。
			ArrayList<String> stockList = new ArrayList<String>();
			for (int i = 1; i <= this._ColumnCount; i++) {
				stockList.add(rs.getString(i));
			}
			returnList.add(stockList);
		}
		return returnList;
	}
	public ArrayList<ArrayList<String>> runSelect(PreparedStatement sql) throws SQLException{
		//SetDB();
		//戻り値用二次元配列の生成
		ArrayList<ArrayList<String>> returnList=new ArrayList<>();
		this._rs=sql.executeQuery();
		//returnList=getResultSet(this._rs);
		_rsMeta = this._rs.getMetaData();
		// 列数の数
		this._ColumnCount = _rsMeta.getColumnCount();
		// データの取得
		while (this._rs.next()) {
			// 一時格納用可変長配列の生成。
			ArrayList<String> stockList = new ArrayList<String>();
			for (int i = 1; i <= this._ColumnCount; i++) {
				stockList.add(this._rs.getString(i));
			}
			returnList.add(stockList);
		}
		//closeDB();
		return returnList;
	}
	//update k.koki 2016/02/04 end
	//update k.koki 2016/11/03 start
	/**
	 * 特定の文字列を変換します。基本的なSQLインジェクションは回避されます。
	 * @author 浩生
	 *
	 */
	public class PreparedStatementByKoki{
		private String sql;
		public PreparedStatementByKoki(String sql) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.sql=sql;
		}
		private void replace(String key,String val){
			val=replaceSQL(val);
			this.sql=sql.replaceAll(key, val);
		}
		public void setString(String key,String val){
			this.replace(key,"'"+val+"'");
		}
		public void setInt(String key,int val){
			this.replace(key, String.valueOf(val));
		}
		public ArrayList<ArrayList<String>> Select() throws SQLException{
			return runSelect(this.sql);
		}
		public int update() throws SQLException{
			return DBManager.this.update(sql);
		}
		public String out(){
			return this.sql;
		}
	}
	/**
	 * SQL文をPreparedStatementByKokiクラスに変換して返します。
	 * @param sql
	 * @return
	 */
	public PreparedStatementByKoki getStatementByKoki(String sql){
		return new PreparedStatementByKoki(sql);
	}

	//update k.koki 2016/11/03 end
}
