/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/10
 * 内容　　:小分け情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Subdivision {
	/**
	 * @auther Tester
	 * 2016/11/10
	 * @param orderId int 受注ID
	 */
	public int orderId;
	/**
	 * @auther Tester
	 * 2016/11/10
	 * @param customreName String 顧客名
	 */
	public String customreName;
	/**
	 * @auther Tester
	 * 2016/11/10
	 * @param customerNameKana String 顧客名（カナ）
	 */
	public String customerNameKana;
	/**
	 * @auther Tester
	 * 2016/11/10
	 * @param orderDate CalendarByKoki 受注日時
	 */
	public CalendarByKoki orderDate;
	/**
	 * @auther Tester
	 * 2016/11/10
	 * @param orderState String 完了状態
	 */
	public String orderState;
}
