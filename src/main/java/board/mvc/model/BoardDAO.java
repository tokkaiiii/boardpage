package board.mvc.model;

import static board.mvc.model.BoardSQL.DELETE;
import static board.mvc.model.BoardSQL.INSERT;
import static board.mvc.model.BoardSQL.LIST;
import static board.mvc.model.BoardSQL.NICKNAME;
import static board.mvc.model.BoardSQL.SEARCHCONTENT;
import static board.mvc.model.BoardSQL.SEARCHTITLE;
import static board.mvc.model.BoardSQL.SELECT;
import static board.mvc.model.BoardSQL.SELECTCNT;
import static board.mvc.model.BoardSQL.SEQ;
import static board.mvc.model.BoardSQL.TOTAL;
import static board.mvc.model.BoardSQL.TOTALLIST;
import static board.mvc.model.BoardSQL.UPDATE;

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
import mvc.domain.Board;

class BoardDAO {


  DataSource dataSource;

  BoardDAO() {
    try {
      Context context = new InitialContext();
      Context envContext = (Context) context.lookup("java:comp/env");
      dataSource = (DataSource) envContext.lookup("jdbc/myDB");
    } catch (NamingException e) {
      System.out.println("DBCP(jdbc/myDB) could not be found)" + e.getMessage());
    }
  }

  // BoardDAO.java
  ArrayList<Board> boards() {
    ArrayList<Board> boards = new ArrayList<>();
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(LIST)
    ) {
      while (rs.next()) {
        int seq = rs.getInt(1);
        String nickname = rs.getString(3);
        String title = rs.getString(4);
        Date date = rs.getDate(8);
        Board board = Board.builder()
            .seq(seq)
            .nickname(nickname)
            .title(title)
            .date(date).build();
        boards.add(board);
      }
      return boards;
    } catch (SQLException e) {
      System.out.println("안됨" + e.getMessage());
      return null;
    }
  }

  ArrayList<Board> list() {
    ArrayList<Board> list = new ArrayList<>();
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(TOTALLIST)
    ) {
      while (rs.next()) {
        int seq = rs.getInt(1);
        String nickname = rs.getString(3);
        String title = rs.getString(4);
        String content = rs.getString(5);
        Date date = rs.getDate(8);
        Board board = Board.builder()
            .seq(seq)
            .nickname(nickname)
            .title(title)
            .content(content)
            .date(date).build();
        list.add(board);
      }
      return list;
    } catch (SQLException e) {
      System.out.println("안됨" + e.getMessage());
      return null;
    }
  }

  ArrayList<Board> searchTitle(String title) {
    ArrayList<Board> list = new ArrayList<>();
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCHTITLE)
    ) {
      ps.setString(1, "%"+title+"%");
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt(1);
          String nickname = rs.getString(3);
          String dbTitle = rs.getString(4);
          String content = rs.getString(5);
          Date date = rs.getDate(8);
          Board board = Board.builder()
              .seq(seq)
              .nickname(nickname)
              .title(dbTitle)
              .content(content)
              .date(date).build();
          list.add(board);
        }
      }
      return list;
    } catch (SQLException e) {
      System.out.println("안됨" + e.getMessage());
      return null;
    }
  }

  int insert(Board board) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(INSERT)
    ) {
      ps.setString(1, board.getId());
      ps.setString(2, board.getNickname());
      ps.setString(3, board.getTitle());
      ps.setString(4, board.getContent());
      ps.setString(5, board.getFname());
      ps.setString(6, null);
      return ps.executeUpdate();
    } catch (SQLException se) {
      System.out.println("안됨" + se.getMessage());
      return -1;
    }
  }

  int update(Board board) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE)
    ) {
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContent());
      ps.setInt(3, board.getSeq());
      System.out.println("seq: "+board.getSeq());
       int i = ps.executeUpdate();
      System.out.println(i+"개 업데이트");
       return i;
    } catch (SQLException se) {
      System.out.println("안됨" + se.getMessage());
      return -1;
    }
  }

  int select(Board board) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SELECT)
    ) {
      ps.setInt(1, board.getSeq());
      return ps.executeUpdate();
    } catch (SQLException se) {
      return -1;
    }
  }

  int delete(Board board) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(DELETE)
    ) {
      ps.setInt(1, board.getSeq());
      return ps.executeUpdate();
    } catch (SQLException se) {
      return -1;
    }
  }

  String nickname(String id) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(NICKNAME)
    ) {
      System.out.println(id);
      ps.setString(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return rs.getString(1);
        } else {
          return null;
        }
      }
    } catch (SQLException se) {
      System.out.println("안됨" + se.getMessage());
      return null;
    }
  }

  Board selectCnt(int seq) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SELECTCNT)
    ) {
      ps.setInt(1, seq);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          String id = rs.getString(2);
          String nickname = rs.getString(3);
          String title = rs.getString(4);
          String content = rs.getString(5);
          String fname = rs.getString(6);
          Date date = rs.getDate(8);
          return Board.builder()
              .seq(seq)
              .id(id)
              .nickname(nickname)
              .title(title)
              .content(content)
              .fname(fname)
              .date(date).build();
        }
      }
    } catch (SQLException se) {
    }
    return null;
  }

  int total() {
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(TOTAL)
    ) {
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException se) {
    }
    return 0;
  }

  ArrayList<Board> searchContent(String content) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCHCONTENT)
    ) {
      ps.setString(1, "%" + content + "%");
      ArrayList<Board> list = new ArrayList<>();
      Board board = null;
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt(1);
          String nickname = rs.getString(3);
          String title = rs.getString(4);
          String dbContent = rs.getString(5);
          Date date = rs.getDate(8);
          board = Board.builder()
              .seq(seq)
              .nickname(nickname)
              .title(title)
              .content(dbContent)
              .date(date).build();
          list.add(board);
        }
        return list;
      }
    } catch (SQLException se) {
      return null;
    }
  }

  ArrayList<Board> searchNickname(String nickname) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCHCONTENT)
    ) {
      ps.setString(1, "%" + nickname + "%");
      ArrayList<Board> list = new ArrayList<>();
      Board board = null;
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt(1);
          String dbNickname = rs.getString(3);
          String title = rs.getString(4);
          String dbContent = rs.getString(5);
          Date date = rs.getDate(8);
          board = Board.builder()
              .seq(seq)
              .nickname(dbNickname)
              .title(title)
              .content(dbContent)
              .date(date).build();
          list.add(board);
        }
        return list;
      }
    } catch (SQLException se) {
      return null;
    }

  }
}