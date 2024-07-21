package board.mvc.model;

class BoardSQL {

  final static String LIST = "select * from BOARD where VALID=1 order by SEQ";
  final static String TOTALLIST = "select * from BOARD where VALID=1 order by SEQ desc";
  final static String TOTAL = "select ceil(count(seq)/10) from BOARD";
  final static String SELECT = "select SEQ,NICKNAME,TITLE,CONTENT,DATE from board where VALID = 1 and SEQ=?";
  final static String INSERT = "insert into board(ID,NICKNAME,TITLE,CONTENT,FNAME,OFNAME,DATE,VALID) values(?,?,?,?,?,?,now(),1)";
  final static String UPDATE = "update board set TITLE = ?,CONTENT = ?,DATE = now() where SEQ = ?";
  final static String DELETE = "update board set VALID = 0 where SEQ = ?";
  final static String NICKNAME = "select NICKNAME from USER where ID=?";
  final static String SEQ = "select SEQ from board order by SEQ desc";
  //전체게시글
  final static String SELECTCNT = "select * from board where SEQ=? and VALID = 1";
  //서치
  final static String SEARCHTITLE = "select * from BOARD where TITLE like ?";
  final static String SEARCHCONTENT = "select * from board where CONTENT like = ?";
}
