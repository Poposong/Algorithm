select category, sum(sales) as `TOTAL_SALES`
from book, book_sales
where book.book_id = book_sales.book_id
and year(sales_date) = 2022 and month(sales_date) = 1
group by category
order by category;