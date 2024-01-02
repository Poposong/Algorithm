-- (1)
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


-- (2)
select T.member_name, B.review_text, date_format(B.review_date, '%Y-%m-%d') as review_date 
from (select R.MEMBER_ID, M.MEMBER_NAME, rank() over(order by `CNT` desc) as ranking
        from (select *, count(member_id) as CNT
        from rest_review
        group by member_id) as R, member_profile M
        where R.member_id = M.member_id) as T, rest_review B
where T.member_id = B.member_id and T.ranking = 1
order by B.review_date, B.review_text;

