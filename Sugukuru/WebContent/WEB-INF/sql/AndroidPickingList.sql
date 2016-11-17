SELECT
  a.puroduct_id,
  b.puroduct_name,
  c.rack_name,
  a.necessary_amount,
  a.picked_amount,
  a.inspected_amount,
  (CASE
    WHEN a.necessary_amount <= a.picked_amount AND a.necessary_amount <= a.inspected_amount
      THEN '検品済'
    WHEN a.necessary_amount <= a.picked_amount
      THEN 'ピッキング済'
    ELSE '未作業'
  END) AS state 
FROM 
  working_table a 
 INNER JOIN puroduct_master b ON a.puroduct_id = b.puroduct_id
 INNER JOIN rack_master c ON b.rack_id = c.rack_id
 WHERE a.puroduct_id = PRODUCT_ID AND state = STATE
;