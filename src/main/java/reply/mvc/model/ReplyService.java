package reply.mvc.model;

import java.util.ArrayList;
import mvc.domain.Reply;

public class ReplyService {

  ReplyDAO dao;
  private static final ReplyService instance = new ReplyService();

  private ReplyService() {
    dao = new ReplyDAO();
  }
  public static ReplyService getInstance() {return instance;}

  public ArrayList<Reply> listS(){
    return dao.list();
  }

  public void insertS(Reply reply) {
    dao.insert(reply);
  }
}
