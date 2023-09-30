-- 코드를 입력하세요
SELECT substr(product_code,1, 2) as 'CATEGORY', count(*) as 'PRODUCTS'
from product
group by substr(product_code,1, 2)
order by substr(product_code,1, 2);

