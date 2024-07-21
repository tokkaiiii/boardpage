package reply.mvc.model;

import static reply.mvc.model.ReplySQL.INSERT;
import static reply.mvc.model.ReplySQL.SELECT;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mvc.domain.Reply;

class ReplyDAO {

  DataSource dataSource;

  public ReplyDAO() {
    try {
      Context context = new InitialContext();
      Context envContext = (Context) context.lookup("java:comp/env");
      dataSource = (DataSource) envContext.lookup("jdbc/myDB");
    } catch (NamingException e) {
      System.out.println("DBCP(jdbc/myDB) could not be found)" + e.getMessage());
    }
  }

  ArrayList<Reply> list() {
    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT);
    ) {
      ArrayList<Reply> list = new ArrayList<>();
      Reply reply;
      while (rs.next()) {
        System.out.println("하고있음");
        int seq = rs.getInt(1);
        String id = rs.getString(2);
        String nickname = rs.getString(3);
        String content = rs.getString(4);
        Date udate = rs.getDate(6);
        reply = Reply.builder()
            .seq(seq)
            .id(id)
            .nickname(nickname)
            .content(content)
            .udate(udate).build();
        list.add(reply);
      }
      return list;
    } catch (SQLException e) {
      System.out.println("안됨"+e.getMessage());
    return null;
    }
  }

  void insert(Reply reply) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(INSERT)
    ) {
      System.out.println("id: "+reply.getId());
      ps.setString(1, reply.getId());
      ps.setString(2,reply.getNickname());
      ps.setString(3,reply.getContent());
      ps.setInt(4,reply.getBoard_seq());
      int i = ps.executeUpdate();
      System.out.println(i+" rows affected");
    } catch (SQLException e) {
      System.out.println("안됨"+e.getMessage());
    }
  }
}
