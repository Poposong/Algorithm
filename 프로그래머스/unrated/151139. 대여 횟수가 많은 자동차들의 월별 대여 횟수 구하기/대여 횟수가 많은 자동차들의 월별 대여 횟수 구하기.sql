-- 코드를 입력하세요
SELECT month(start_date) as MONTH, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where date_format(start_date, '%Y-%m') IN ('2022-08', '2022-09', '2022-10') 
group by `MONTH`, car_id
having car_id in(
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where date_format(start_date, '%Y-%m') IN ('2022-08', '2022-09', '2022-10') 
    group by car_id
    having count(*) >= 5
)
order by `MONTH`, car_id desc;