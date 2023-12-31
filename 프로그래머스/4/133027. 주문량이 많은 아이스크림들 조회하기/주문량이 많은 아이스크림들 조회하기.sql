-- 코드를 입력하세요
SELECT f.flavor
from FIRST_HALF f, JULY j
where f.flavor = j.flavor
group by f.flavor
order by sum(f.total_order) - f.total_order * (count(f.total_order) - 1) + sum(j.total_order) desc
limit 3;
