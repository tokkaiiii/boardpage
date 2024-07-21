package board.mvc.model;

public class BoardSQL {
  public static String LIST = "select * from BOARD where VALID=1 and SEQ<? order by SEQ desc limit 10";
  public static String TOTALLIST = "select * from BOARD where VALID=1 order by SEQ desc";
  public static String TOTAL = "select ceil(count(seq)/10) from BOARD";
  public static String SELECT = "select SEQ,NICKNAME,TITLE,CONTENT,DATE from board where VALID = 1 and SEQ=?";
  public static String INSERT = "insert into board values(?,?,?,?,?,?,?,now(),1)";
  public static String UPDATE = "update board set TITLE = ?,CONTENT = ?,DATE = now() where SEQ = ?";
  public static String DELETE = "update board set VALID = 0 where SEQ = ?";
  public static String NICKNAME = "select NICKNAME from USER where ID=?";
  public static String SEQ = "select SEQ from board order by SEQ desc";
  //전체게시글
  public static String SELECTCNT = "select * from board where SEQ=? and VALID = 1";
  //서치
  public static String SEARCHTITLE = "select * from board where TITLE like ?";
  public static String SEARCHCONTENT = "select * from board where CONTENT like = ?";
  public static String SEARCHNICKNAME = "select * from board where NICKNAME like ?";
}
