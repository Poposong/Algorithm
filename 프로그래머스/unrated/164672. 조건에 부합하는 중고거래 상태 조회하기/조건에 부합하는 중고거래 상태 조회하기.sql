-- 코드를 입력하세요
SELECT board_id, writer_id, title, price, if(status = 'SALE', '판매중', if(status = 'RESERVED','예약중', '거래완료')) as STATUE
from used_goods_board
where CREATED_DATE = '2022-10-5'
order by board_id desc;