package user.mvc.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import mvc.domain.User;
import user.mvc.model.UserService;

@WebServlet("/user.do")
public class UserController extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String method = req.getParameter("method");
    if (method != null) {
      method = method.trim();
      switch (method) {
        case "signup":
          signup(req, resp);
          break;
          case "joinDo":
            joinDo(req, resp);break;
        case "join":
          join(req, resp);
          break;
      }
    } else {
      resp.sendRedirect("index.jsp");
    }
  }

  public void signup(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserService userService = UserService.getInstance();
    String id = req.getParameter("id");
    String password = req.getParameter("password");
    User user = new User(-1, id, password, null, null, -1, null, null);
    int signupValid = userService.signup(user);
    req.setAttribute("signupValid", signupValid);
    req.setAttribute("user", user);
    String view = "user/signup.jsp";
    req.getRequestDispatcher(view).forward(req, resp);
  }

public void logout(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    resp.sendRedirect("user/logout.jsp");
}
public void joinDo(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    resp.sendRedirect("user/join.jsp");
}
  public void join(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserService userService = UserService.getInstance();
    String id = req.getParameter("id");
    String password = req.getParameter("password");
    String name = req.getParameter("name");
    String email = req.getParameter("email");
    String nickname = req.getParameter("nickname");
    User user = new User(-1, id, password, name, email, -1, null, nickname);
    int joinValid = userService.join(user);
    req.setAttribute("joinValid", joinValid);
    req.setAttribute("user", user);
    String view = "user/join_test.jsp";
    req.getRequestDispatcher(view).forward(req, resp);
  }
}
