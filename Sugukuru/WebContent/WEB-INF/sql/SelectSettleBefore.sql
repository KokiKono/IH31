/*顧客ごとの前回請求額と入金額を取得する。*/
SELECT
 SUM(settlement_table.total_fee+settlement_table.consumption_tax) AS sett /*前回請求額*/
,IFNULL((
    SELECT
     SUM(payment_table.paid_price)
    FROM
     payment_table
    WHERE
     (SELECT settlement_table2.settlement_id
       FROM settlement_table settlement_table2
       WHERE settlement_table2.customer_id=settlement_table.customer_id
      ORDER BY settlement_table.settlement_id DESC
      LIMIT 1
      ) = payment_table.settlement_id
 ),0) AS payment															/*入金額*/
 FROM settlement_table
 WHERE
  settlement_table.payment_date IS NULL
  AND settlement_table.customer_id = CUSTOMER_ID
  GROUP BY settlement_table.customer_id

;