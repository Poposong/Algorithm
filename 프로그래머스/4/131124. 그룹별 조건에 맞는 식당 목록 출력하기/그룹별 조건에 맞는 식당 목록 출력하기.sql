-- 코드를 입력하세요
-- 가장 큰 리뷰 값 -> 해당하는 회원들 -> 
# SELECT m.member_id, count(m.member_id)
# from member_profile m, rest_review r
# where m.member_id = r.member_id
# group by member_id
# having count(m.member_id) = (
#     SELECT count(mp.member_id)
#     from member_profile mp, rest_review rr
#     where mp.member_id = rr.member_id
#     group by mp.member_id
#     order by count(mp.member_id) desc limit 1
# );

# SELECT m.member_id, count(m.member_id)
# from member_profile m, rest_review r
# where m.member_id = r.member_id
# group by member_id
# order by count(m.member_id) desc;

select m.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d')
from member_profile m, rest_review r
where m.member_id = r.member_id
and m.member_id in(
    SELECT m.member_id
    from member_profile m, rest_review r
    where m.member_id = r.member_id
    group by member_id
    having count(m.member_id) = (
        SELECT count(mp.member_id)
        from member_profile mp, rest_review rr
        where mp.member_id = rr.member_id
        group by mp.member_id
        order by count(mp.member_id) desc limit 1
    )
)
order by r.review_date, r.review_text;
