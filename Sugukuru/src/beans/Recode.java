/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/16
 * 内容　　:レコードのフォーマットを制御するクラス。
 * *************************/
package beans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;

import beans.Bank.Banks;
import beans.Bank.Store;
import beans.Bank.TypeCode;

import common.Common.RecallManner;


public class Recode {
	/**
	 * レコードのフォーマットを列挙します。
	 * @author 浩生
	 *
	 */
	public enum RecodeFormat {
		/**
		 * エンドレコード -----説明------- ファイルの終わりを表す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param End
		 *            Recode
		 */
		End("9", "end",80),
		/**
		 * トレーラーレコード -------説明------- レコードの終わりを表す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param Triler
		 *            Recode
		 */
		Triler("8", "trailer",20),
		/**
		 * データレコード ------説明------ 連絡・通知する情報の １単位を表す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param Deta
		 *            Recode
		 */
		Deta("2", "deta",20),
		/**
		 * ヘッダーレコード -----説明------ データレコードの 開始を表す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param Header
		 *            Recode
		 */
		Header("1", "header",20);

		/**
		 * データ区分を示す。
		 * @auther 浩生
		 * 2016/11/16
		 * @param code String
		 */
		public String code;
		/**
		 * レコード名
		 * @auther 浩生
		 * 2016/11/16
		 * @param pgName String
		 */
		public String pgName;
		/**
		 * レコードの長さ
		 * @auther 浩生
		 * 2016/11/16
		 * @param length int
		 */
		public int length;

		private RecodeFormat(String code, String pgName,int length) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code = code;
			this.pgName = pgName;
			this.length=length;
		}

		/**
		 * codeと同等のRecodeを返す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 * @return
		 */
		public RecodeFormat indexOf(String code) {
			for (RecodeFormat recode : RecodeFormat.values()) {
				if (code.equals(recode.code)) {
					return recode;
				}
			}
			return null;
		}
	}
	/**
	 * 現在の文字インデックスを保持します。
	 * @auther 浩生
	 * 2016/11/16
	 * @param point int
	 */
	private int point;
	/**
	 * 読み込んだ文字を
	 * @auther 浩生
	 * 2016/11/16
	 * @param fileValue char[]
	 */
	private char[] fileValue;

	/**
	 * 読み込んだファイルから何文字分を返します。 開始位置はこのクラスのpointに記録され その位置から読み込みます。
	 *
	 * @auther 浩生 2016/11/16
	 * @param length
	 * @return
	 * @see PaymentCheck#point
	 */
	private String readLength(int length) {
		StringBuffer sbRead = new StringBuffer();
		for (int count = this.point; count < length; count++) {
			sbRead.append(this.fileValue[count]);
		}
		return sbRead.toString();
	}
	/**
	 * WEB-INF/bank配下にあるファイルを読み込む
	 *
	 * @auther 浩生 2016/11/15
	 * @return
	 */
	private String readFile(HttpServlet httpServlet, String fileName) {
		String path = httpServlet.getServletContext().getRealPath(
				"/WEB-INF/bank/" + fileName);
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));
			String string = reader.readLine();
			while (string != null) {
				builder.append(string);
				string = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return builder.toString();
	}
	/**
	 * ファイル内容を持つRecodeを生成します。
	 * @auther 浩生
	 * 2016/11/16
	 * @param servlet
	 * @param fileName
	 */
	public Recode(HttpServlet servlet,String fileName) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.fileValue=this.readFile(servlet, fileName).toCharArray();
	}

	private RecodeFormat nowRecode;

	/**
	 * 現在のレコードから次のレコードタイプを返します。
	 * @auther 浩生
	 * 2016/11/16
	 * @return
	 */
	public RecodeType nextRecode(){
		//次のレコード判別
		String recode=String.valueOf(this.fileValue[this.point+1]);
		RecodeFormat recodeFormat=RecodeFormat.valueOf(recode);
		RecodeType recodeType=new RecodeType();
		recodeType.format=recodeFormat;
		//レコードの内容を取得
		recodeType.recodeVal=readLength(recodeFormat.length).toCharArray();
		//現在のレコードを設定
		setNowRecode(recodeType);
		return recodeType;
	}


	/**
	 * ファイル内文字列からヘッダーレコードを返します。
	 * @auther 浩生
	 * 2016/11/16
	 * @return
	 * @throws RecodeFormatException
	 */
	public RecodeType firstRecode() throws RecodeFormatException{
		if(String.valueOf(this.fileValue[0]).equals(RecodeFormat.Header.code)==false){
			//先頭がヘッダーレコードでない場合
			throw new RecodeFormatException("先頭がヘッダーレコードでありません。");
		}
		//戻り値の設定
		RecodeType recodeType=new RecodeType();
		recodeType.format=RecodeFormat.Header;
		recodeType.recodeVal=readLength(RecodeFormat.Header.length).toCharArray();
		//現在レコードの設定
		setNowRecode(recodeType);
		return recodeType;
	}


	private void setNowRecode(RecodeType recodeType){
		this.nowRecode=recodeType.format;
	}
	public class RecodeFormatException extends Exception{
		private String message;

		public RecodeFormatException(String message) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.message=message;
		}

		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return this.message;
		}
	}

	public RecodeFormat nowRecode(){
		return this.nowRecode;
	}

	/**
	 * 現在のレコードの種類とレコード内容を持ち
	 * レコードを制御するクラス。
	 * @author 浩生
	 *
	 */
	public class RecodeType{
		public RecodeFormat format;
		public char[] recodeVal;
		/**
		 * レコード内文字列のインデックス。
		 * @auther 浩生
		 * 2016/11/16
		 * @param point int
		 */
		private int point;
		/**
		 * 現在のポインタから指定文字数分を返します。
		 * @auther 浩生
		 * 2016/11/16
		 * @param length
		 * @return
		 */
		private String valLength(int length){
			StringBuffer sbRead = new StringBuffer();
			for (int count = 0; count < length; count++) {
				sbRead.append(this.recodeVal[count]);
			}
			//ポインター変更
			this.point=this.point+length;
			return sbRead.toString();
		}
		/**
		 * 左詰めregxを削除します。
		 * @auther 浩生
		 * 2016/11/16
		 * @param val
		 * @return
		 */
		private String deleteLeft(String val,String regx){
			int index=0;
			loop:for(int count=0;count<val.length();count++){
				if(!String.valueOf(val.charAt(count)).equals(regx)){
					index=count;
					break loop;
				}
			}
			return val.substring(index);
		}
	}
	/**
	 * ヘッダーレコード情報を取得します。
	 * @author 浩生
	 *
	 */
	public class HeaderRecode extends RecodeType{
		/**
		 * 顧客ID
		 * @auther 浩生
		 * 2016/11/16
		 * @param customerId String
		 */
		public String customerId;
		/**
		 * 会社名
		 * @auther 浩生
		 * 2016/11/16
		 * @param corporationName String
		 */
		public String corporationName;
		/**
		 * 入金日
		 * @auther 浩生
		 * 2016/11/16
		 * @param paymentDate String
		 */
		public CalendarByKoki paymentDate;
		/**
		 * 仕向け銀行
		 * @auther 浩生
		 * 2016/11/16
		 * @param fromBank Banks
		 */
		public Banks fromBank;
		/**
		 * 支店
		 * @auther 浩生
		 * 2016/11/16
		 * @param fromStore Store
		 */
		public Store fromStore;
		/**
		 * 預金種別
		 * @auther 浩生
		 * 2016/11/16
		 * @param depositType TypeCode
		 */
		public TypeCode depositType;
		/**
		 * 振込先口座番号
		 * @auther 浩生
		 * 2016/11/16
		 * @param toAccount String
		 */
		public String toAccount;
		/**
		 * 各データの長さを定義
		 * 先頭から
		 * 振込顧客ID、会社名、入金日、仕向け銀行、支店、預金種類、振込先口座番号、ダミー
		 * の順番
		 * @auther 浩生
		 * 2016/11/16
		 * @param FORMAT int[]
		 */
		private final int[] FORMAT={
			6,40,8,4,3,1,7,10
		};
		{
			//データ区分をとばす
			super.point=super.point+1;
			//各データを取得
			//顧客ID
			this.customerId=super.valLength(FORMAT[0]);
			//会社名
			this.corporationName=super.deleteLeft(super.valLength(FORMAT[1]), " ");
			//入金日
			this.paymentDate=new CalendarByKoki(super.valLength(FORMAT[2]));
			//仕向け銀行
			this.fromBank=Banks.indexOf(super.deleteLeft(super.valLength(FORMAT[3]), "0"));
			//支店
			this.fromStore=Store.indexOf(super.deleteLeft(super.valLength(FORMAT[4]), "0"));
			//預金種類
			this.depositType=TypeCode.indexOf(super.valLength(FORMAT[5]));
			//振込先口座番号
			this.toAccount=super.deleteLeft(super.valLength(FORMAT[6]), "0");
		}
	}
	/**
	 * データレコード
	 * @author 浩生
	 *
	 */
	public class DetaRecode extends RecodeType{
		/**
		 * 被仕向け銀行
		 * @auther 浩生
		 * 2016/11/16
		 * @param toBank Banks
		 */
		public Banks toBank;
		/**
		 * 支店
		 * @auther 浩生
		 * 2016/11/16
		 * @param store Store
		 */
		public Store store;
		/**
		 * 支払種別
		 * @auther 浩生
		 * 2016/11/16
		 * @param recallManner RecallManner
		 */
		public RecallManner recallManner;
		/**
		 * 支払額
		 * @auther 浩生
		 * 2016/11/16
		 * @param price String
		 */
		public String price;
		private final int[] FORMAT={
			1,4,3,1,10,1
		};
		{
			//被仕向け銀行
			this.toBank=Banks.indexOf(super.deleteLeft(super.valLength(FORMAT[0]), "0"));
			//支店
			this.store=Store.indexOf(super.deleteLeft(super.valLength(FORMAT[1]), "0"));
			//預金種類
			this.recallManner=RecallManner.indexOf(super.valLength(FORMAT[2]));
			//支払金額
			this.price=super.deleteLeft(super.valLength(FORMAT[3]), "0");
		}
	}
	/**
	 * トレーラーレコード
	 * @author 浩生
	 *
	 */
	public class TorailerRecode extends RecodeType{
		public String totalNumber;
		public String totalPrice;
		private final int[] FORMAT={
				6,12,1
		};
		{
			this.totalNumber=super.deleteLeft(super.valLength(FORMAT[0]), "0");
			this.totalPrice=super.deleteLeft(super.valLength(FORMAT[1]), "0");
		}
	}
}
