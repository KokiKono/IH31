/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:
 * *************************/
package dtd;

public class OrderDetail {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param num int 行番号
	 */
	public int num;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productName String 商品ID
	 */
	public String productId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productName String 商品名
	 */
	public String productName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param price int 商品単価
	 */
	public int price;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param cunsumntionTax int 消費税
	 */
	public /*int*/double cunsumntionTax;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param amount int 商品数量
	 */
	public int amount;
}
