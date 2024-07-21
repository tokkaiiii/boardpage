package reply.mvc.model;

class ReplySQL {

  final static String SELECT = "select * from REPLY where VALID = 1";
  final static String INSERT = "insert into REPLY(ID,NICKNAME,CONTENT,RDATE,UDATE,VALID,BOARD_SEQ) values(?,?,?,now(),now(),1,?)";

}
