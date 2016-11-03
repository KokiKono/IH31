/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:売上情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Earnings {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param earningId int 売上ID
	 */
	public int earningId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param customerId String 顧客ID
	 */
	public String customerId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param employmentId int 担当者ID
	 */
	public int employmentId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param shipmentDate CalendarByKoki 出荷日時
	 */
	public CalendarByKoki shipmentDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param noTaxTotalFee int 税抜合計金額
	 */
	public int noTaxTotalFee;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param taxFee int 消費税
	 */
	public int taxFee;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param note String 備考
	 */
	public String note;
}
