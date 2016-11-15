/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/14
 * 内容　　:請求処理を行う際の箱クラス。
 * *************************/
package dtd;

import common.Common.RecallManner;


public class DoClaim extends Earnings{
	/**
	 * 締日
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/14
	 * @param cutDay String
	 */
	@Request
	public String cutOfDay;

	public CutDay cutDay;

	/**
	 * 繰り越し金
	 * @auther 浩生
	 * 2016/11/14
	 * @param over_price int
	 */
	public int overPrice;

	/**
	 * 回収方法
	 *
	 * @auther 浩生
	 * 2016/11/14
	 * @param recallManner int
	 */
	public int recallManner;

	/**
	 * 回収方法を表示用で取得する。
	 * @auther 浩生
	 * 2016/11/14
	 * @return
	 */
	public String getRecallManner(){
		RecallManner manner=RecallManner.indexOf(this.recallManner);
		if(manner==null) return "登録されてない回収方法";
		return manner.value;
	}

	/**
	 * この売上、繰り越し情報の合算金額を取得します。
	 * 新規請求額（税抜）＋新規請求額（税）＋繰り越し金
	 * @auther 浩生
	 * 2016/11/14
	 * @return
	 */
	public int getAllTotal(){
		return this.noTaxTotalFee+this.taxFee+this.overPrice;
	}

	{
		this.cutOfDay=new String();
		this.cutDay=new CutDay();
		this.overPrice=0;
	}

}
