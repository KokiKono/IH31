/*指定売上テーブルに決済IDを設定する。*/
UPDATE
 earnings_table
 SET
 earnings_table.settlement_id = SETTLTMENT_ID
 WHERE
 earnings_table.earning_id = EARNIG_ID
 ;