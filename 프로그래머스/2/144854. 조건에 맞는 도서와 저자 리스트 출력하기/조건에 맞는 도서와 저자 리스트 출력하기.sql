-- 코드를 입력하세요
SELECT B.book_id, A.author_name, date_format(B.published_date, '%Y-%m-%d') as 'PUBLISHED_DATE'
from Book b, Author A
where b.author_id = a.author_id and category in('경제')
order by B.published_date;
