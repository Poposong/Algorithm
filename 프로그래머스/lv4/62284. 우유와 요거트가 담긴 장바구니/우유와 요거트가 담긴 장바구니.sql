-- 코드를 입력하세요
# SELECT *
# from cart_products
# where cart_id in(
#     select cart_id
#     from cart_products
#     where name = 'milk' and name = 'yogurt'
# )
# group by cart_id;

select cart_id
from cart_products
where cart_id in(
    select cart_id
    from cart_products
    where name = 'Yogurt'
)
and
cart_id in(
    select cart_id
    from cart_products
    where name = 'Milk'
)

group by cart_id
order by cart_id;