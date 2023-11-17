select order_id, product_id, date_format(out_date, '%Y-%m-%d'), 
case
    when month(OUT_DATE) is null then '출고미정'
    when month(OUT_DATE)<=4 then '출고완료'
    when month(OUT_DATE)=5 and day(OUT_DATE)=1 then '출고완료'
    else '출고대기'
end as '출고여부'
from food_order
order by order_id;

