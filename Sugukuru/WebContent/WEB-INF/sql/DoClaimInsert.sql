/*決済テーブルにデータを登録後決済IDを取得し、売上IDに請求済み処理を行う。（インサート）*/
INSERT INTO settlement_table
(
    settlement_table.customer_id
   ,settlement_table.request_date
   ,settlement_table.total_fee
   ,settlement_table.consumption_tax
)VALUES(
    CUSTOMER_ID
   ,REQUEST_DATE
   ,TOTAL_FEE
   ,TAX
);