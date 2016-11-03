/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:売上詳細情報を管理する
 * *************************/
package dtd;

public class EarningsDetail {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param earningId int 売上ID
	 */
	public int earningId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param num int 行番号
	 */
	public int num;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param puroductId String 商品ID
	 */
	public String puroductId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param puroductName String 商品名
	 */
	public String puroductName;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param price int　商品単価
	 */
	public int price;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param taxFee int 消費税
	 */
	public int taxFee;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param soldAmount int 売上数
	 */
	public int soldAmount;
}
