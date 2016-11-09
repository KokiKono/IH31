/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/09
 * 内容　　:入金、振り込みなどの銀行処理を管理するスーパークラス
 * *************************/
package beans;

public class Bank {
	/**
	 * 全銀行を列挙する。
	 * @author 浩生
	 *
	 */
	public enum Banks{
		UFJ("三菱東京UFJ銀行","ﾐﾂﾋﾞｼﾄｳｷｮｳUFJ","0005");

		public String name;
		public String kana;
		public String code;
		Banks(String name,String kana,String code){
			this.name=name;
			this.kana=kana;
			this.code=code;
		}
	}
	public enum Store{
		UFJ_HONTEN("ﾎﾝﾃﾝ","ﾎﾝﾃﾝ","001");
		public String name;
		public String kana;
		public String code;
		Store(String name,String kana,String code){
			this.name=name;
			this.kana=kana;
			this.code=code;
		}
	}


}
