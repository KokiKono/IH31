/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:ピッキング情報を管理するマスタ。
 * *************************/
package dtd;

public class PickingList {
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickId String 行番号
	 */
	public String pickId;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param productId String 商品番号
	 */
	public String productId;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param productName String 商品名
	 */
	public String productName;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param rackNumber int 棚番号
	 */
	public int rackNumber;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param needs int 必要数
	 */
	public int needs;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickNum int ピッキング数
	 */
	public int pickNum;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickState int 作業ステップ
	 */
	public int pickState;
}
