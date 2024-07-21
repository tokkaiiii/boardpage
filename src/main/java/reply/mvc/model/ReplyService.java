package reply.mvc.model;

import mvc.domain.Reply;

public class ReplyService {

  ReplyDAO dao;
  private static final ReplyService instance = new ReplyService();

  private ReplyService() {
    dao = new ReplyDAO();
  }
  public static ReplyService getInstance() {return instance;}

  public void insertS(Reply reply) {
    dao.insert(reply);
  }
}
