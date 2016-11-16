SELECT a.puroduct_id, 
  b.puroduct_name, 
  c.rack_name, 
  a.necessary_amount, 
  (SELECT d.stock_amount 
    FROM stock_table d 
    WHERE d.puroduct_id = b.puroduct_id 
    ORDER BY d.entry_date 
    DESC LIMIT 1) AS stock_amount, 
  a.picked_amount 
FROM working_table a 
INNER JOIN puroduct_master b ON a.puroduct_id = b.puroduct_id 
INNER JOIN rack_master c ON b.rack_id = c.rack_id 
ORDER BY puroduct_id
;