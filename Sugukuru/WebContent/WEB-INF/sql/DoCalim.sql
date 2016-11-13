/*請求を行う顧客を一覧で表示する。その後、印刷と請求済みに変えていく。*/
/*また請求を行う売上は決算IDがnullのもの。*/
SELECT
 earnings_table.earning_id										/*売上ID*/
,earnings_table.order_id										/*受注ID*/
,earnings_table.customer_id										/*顧客ID*/
,earnings_table.employment_id									/*社員ID*/
,earnings_table.shipment_date									/*出荷日時*/
,earnings_table.no_tax_total_fee								/*税抜合計金額*/
,earnings_table.tax_fee											/*消費税*/
,earnings_table.note											/*備考*/
,corporation_customer_master.customer_name						/*顧客名*/
,corporation_customer_master.abbreviation_name					/*略称*/
,corporation_customer_master.cut_off_date						/*締日*/
,corporation_customer_master.recall_manner						/*回収方法*/
,corporation_customer_master.postal_code						/*郵便番号*/
,corporation_customer_master.address							/*請求先住所*/
 FROM
 earnings_table
 INNER JOIN corporation_customer_master
 ON earnings_table.customer_id = corporation_customer_master.customer_id
 WHERE
 earnings_table.settlement_id IS NULL
 
;