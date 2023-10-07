-- 코드를 입력하세요
SELECT year(o.sales_date) as YEAR, month(o.sales_date) as MONTH, u.gender as GENDER, count(DISTINCT U.user_id) as USERS
from USER_INFO U join ONLINE_SALE o
on U.user_id = o.user_id
where u.gender is not null
group by `YEAR`, `MONTH`, `GENDER`
order by `YEAR`, `MONTH`, `GENDER`;
