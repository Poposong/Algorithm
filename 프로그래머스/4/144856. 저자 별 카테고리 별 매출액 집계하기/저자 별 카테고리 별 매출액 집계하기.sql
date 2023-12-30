-- 코드를 입력하세요
# SELECT b.author_id, a.author_name, b.category, b.price*s.sales as 'TOTAL_SALES'
# from Book b, Author a, Book_SALES s
# where s.book_id = b.book_id and a.author_id = b.author_id
# and date_format(s.sales_date, '%Y-%d') = '2022-01'
# group by b.author_id, b.category
# order by b.author_id asc, b.category desc;

SELECT b.author_id, a.author_name, b.category, sum(b.price*s.sales) as 'TOTAL_SALES'
from Book b, Author a, Book_SALES s
where s.book_id = b.book_id and a.author_id = b.author_id
and year(s.sales_date) = '2022' and month(s.sales_date) = '1'
group by  b.category, b.author_id
order by b.author_id asc, b.category desc;
