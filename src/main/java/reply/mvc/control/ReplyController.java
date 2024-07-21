package reply.mvc.control;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import mvc.domain.Reply;
import mvc.domain.User;
import reply.mvc.model.ReplyService;

@WebServlet("/reply.do")
public class ReplyController extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    String method = req.getParameter("method");
    if (method != null) {
      switch (method) {
        case "list": list(req, res);break;
        case "insert": insert(req,res);break;
      }
    }
  }

  private void list(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

  }
  private void insert(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
    ReplyService service = ReplyService.getInstance();
    String seq = req.getParameter("seq");
    int board_seq = -1;
    if (seq != null) {
      board_seq = Integer.parseInt(seq);
    }
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");
    String id = user.getId();
    String nickname = req.getParameter("commentNickname");
    String commentContent = req.getParameter("commentContent");
    Reply reply = Reply.builder()
        .id(id)
        .nickname(nickname)
        .content(commentContent)
        .board_seq(board_seq).build();
    service.insertS(reply);
    res.sendRedirect("board/cntDetail.jsp");
  }
}
