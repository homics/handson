insert into article_stock select * from (
select 1, 3 union
select 2, 1 union
select 3, 14 union
select 4, 5 union
select 5, 0 union
select 6, 5 union
select 7, 20 union
select 8, 5 union
select 9, 5 union
select 10, 5
) x where not exists(select * from article_stock);