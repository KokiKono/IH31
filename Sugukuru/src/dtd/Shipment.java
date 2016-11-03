/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:出荷情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Shipment {
	 /**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param shipmentDate CalendarByKoki 出荷日時
	 */
	public CalendarByKoki shipmentDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param shipmentFlg int 出荷フラグ
	 */
	public int shipmentFlg = 0;
	
}
