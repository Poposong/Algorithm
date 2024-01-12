select user.user_id, user.nickname, sum(board.price) as TOTAL_SALES
from USED_GOODS_USER user
join USED_GOODS_BOARD board on user.user_id = board.writer_id
where status = 'DONE'
group by board.writer_id
having sum(board.price) >= 700000
order by TOTAL_SALES;