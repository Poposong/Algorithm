select b.animal_id, b.name
from animal_ins a
join
animal_outs b
on a.animal_id = b.animal_id
where a.datetime > b.datetime
order by a.datetime;
