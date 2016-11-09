/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:倉庫部が受注一覧を表示する際のDTDクラス。
 * *************************/
package dtd;

import java.util.ArrayList;

public class StockOrderList extends Order {
	/**
	 * 出荷年
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentYear
	 *            String
	 */
	@Request
	public String shipmentYear;
	/**
	 * 出荷月
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentMonth
	 *            String
	 */
	@Request
	public String shipmentMonth;
	/**
	 * 出荷日
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentDay
	 *            String
	 */
	@Request
	public String shipmentDay;
	/**
	 * 受注IDリクエスト用
	 *
	 * @auther 浩生 2016/11/07
	 * @param rOrderId
	 *            String
	 */
	@Request
	public String rOrderId;

	/**
	 * 受注詳細リスト
	 *
	 * @auther 浩生 2016/11/08
	 * @param orderDetails
	 *            ArrayList<OrderDetail>
	 */
	public ArrayList<OrderDetail> orderDetails;
	{
		this.orderDetails = new ArrayList<OrderDetail>();
	}

	/**
	 * 進捗状態を％データで返す。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public int stepParsent() {
		if (this.shipmentDate != null) {
			//出荷日時がある場合は出荷済みとする。
			return 100;
		}
		int pasent = 0;
		int count = 0;
		for (OrderDetail detail : this.orderDetails) {
			pasent = pasent + detail.stepPar();
			count++;
		}
		return pasent / count;
	}

	/**
	 * 受領状態を取得する。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public String getState() {
		for (OrderDetail detail : this.orderDetails) {
			if (detail.productDeliveredFlg == 1) {
				return "<span style=\"color:red\">拒否あり</span>";
			}
		}
		return "<span style=\"color:green\">受領</span>";
	}

	/**
	 * 納品日の画面用文字列を取得する。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public String getDeliveryDate() {
		if (this.deliveryDate == null) {
			return "未設定";
		}
		return this.deliveryDate.outOfJP();
	}

}
