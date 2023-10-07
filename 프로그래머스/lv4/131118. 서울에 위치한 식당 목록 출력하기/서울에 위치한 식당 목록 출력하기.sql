-- 서울에 위치한 식당들의  식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
SELECT i.rest_id, i.rest_name, i.food_type, i.favorites, i.address, round(avg(r.review_score) ,2) as score
from rest_info i join rest_review r
on i.rest_id = r.rest_id
group by i.rest_id
having i.address like '서울%'
order by `score` desc, i.favorites desc;