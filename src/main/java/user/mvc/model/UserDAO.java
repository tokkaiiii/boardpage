package user.mvc.model;

import static user.mvc.model.UserSQL.JOIN;
import static user.mvc.model.UserSQL.SIGNUP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mvc.domain.User;

class UserDAO {

  DataSource dataSource;

  UserDAO() {
    try {
      Context context = new InitialContext();
      Context envContext = (Context) context.lookup("java:comp/env");
      dataSource = (DataSource) envContext.lookup("jdbc/myDB");
    } catch (NamingException e) {
      System.out.println("DBCP(jdbc/myDB) could not be found)" + e.toString());
    }
  }

  int join(User user) {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(JOIN);
    ) {
      ps.setString(1, user.getId());
      ps.setString(2, user.getPassword());
      ps.setString(3, user.getName());
      ps.setString(4, user.getEmail());
      ps.setInt(5, 1);
      ps.setString(6, user.getNickname());
      return ps.executeUpdate();//가입성공
    } catch (SQLException e) {
      System.out.println("안됨"+e.toString());
      return -1;//동일 id
    }
  }

  int signup(User user) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SIGNUP);
    ) {
      ps.setString(1, user.getId());
      ResultSet rs = ps.executeQuery();
      System.out.println("user = " + user);
      if (rs.next()) {
        if (rs.getString("password").equals(user.getPassword())) {
          return 1;//가입 성공
        }
        return 0;//
      }
      return -1;
    } catch (SQLException e) {
      return -2;//동일한 id
    }
  }
}

