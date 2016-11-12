/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:全てのソースで使用する共通変数。
 * *************************/
package common;


public interface Common {
	/**
	 * 消費税
	 * @auther 浩生
	 * 2016/11/07
	 * @param TAX double
	 */
	double TAX=1.08;
	/**
	 * カード払い
	 * @auther 浩生
	 * 2016/11/08
	 * @param CARD int
	 */
	int CARD=0;
	/**
	 * 現金払い
	 * @auther 浩生
	 * 2016/11/08
	 * @param CASH int
	 */
	int CASH=1;
	/**
	 * 手形
	 * @auther 浩生
	 * 2016/11/08
	 * @param BILL int
	 */
	int BILL=2;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP0 int
	 */
	int STEP0=0;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP1 int
	 */
	int STEP1=1;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP2 int
	 */
	int STEP2=2;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP3 int
	 */
	int STEP3=3;
	/**
	 * システムエラーコード
	 * どうしようもないエラー
	 * @auther 浩生
	 * 2016/11/09
	 * @param EXECPTION int
	 */
	int EXECPTION=1;
	/**
	 * システムエラーコード
	 * SQLエラー
	 * @auther 浩生
	 * 2016/11/09
	 * @param SQLEXCEPTION int
	 */
	int SQLEXCEPTION=2;
	/**
	 * システムエラーコード
	 * DBが読み込めない
	 * @auther 浩生
	 * 2016/11/09
	 * @param CLASSNOTFOUNTEXCEPTION int
	 */
	int CLASSNOTFOUNTEXCEPTION=3;
}
