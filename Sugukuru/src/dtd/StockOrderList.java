/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:倉庫部が受注一覧を表示する際のDTDクラス。
 * *************************/
package dtd;

public class StockOrderList extends Order{
	/**
	 * 出荷年
	 * @auther 浩生
	 * 2016/11/07
	 * @param shipmentYear String
	 */
	@Request
	public String shipmentYear;
	/**
	 * 出荷月
	 * @auther 浩生
	 * 2016/11/07
	 * @param shipmentMonth String
	 */
	@Request
	public String shipmentMonth;
	/**
	 * 出荷日
	 * @auther 浩生
	 * 2016/11/07
	 * @param shipmentDay String
	 */
	@Request
	public String shipmentDay;
	/**
	 * 受注IDリクエスト用
	 * @auther 浩生
	 * 2016/11/07
	 * @param rOrderId String
	 */
	@Request
	public String rOrderId;

}
