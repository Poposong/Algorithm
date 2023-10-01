select concat('/home/grep/src/',F.board_id,'/',F.file_id,F.file_name, F.file_ext) as FILE_PATH
from USED_GOODS_BOARD B join USED_GOODS_FILE F
on B.board_id = F.board_id
where B.board_id = (
    select board_id
    from USED_GOODS_BOARD
    order by views desc limit 1
)
order by F.FILE_ID desc;
