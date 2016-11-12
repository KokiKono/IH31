/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:決済情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Settlement {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param settlementId int 決済ID
	 */
	public int settlementId;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param customerId String 顧客ID
	 */
	public String customerId;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param requestDate CalendarByKoki 請求日付
	 */
	public CalendarByKoki requestDate;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param totalFee int 合計金額(税抜)
	 */
	public int totalFee;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param consumptionTax int 消費税
	 */
	public int consumptionTax;


}
