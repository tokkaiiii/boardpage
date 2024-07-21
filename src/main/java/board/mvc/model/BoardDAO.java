package board.mvc.model;

import static board.mvc.model.BoardSQL.DELETE;
import static board.mvc.model.BoardSQL.INSERT;
import static board.mvc.model.BoardSQL.LIST;
import static board.mvc.model.BoardSQL.NICKNAME;
import static board.mvc.model.BoardSQL.SEARCHCONTENT;
import static board.mvc.model.BoardSQL.SEARCHNICKNAME;
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
  ArrayList<Board> boards(int pageNumber) {
    ArrayList<Board> boards = new ArrayList<>();
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(LIST)
    ) {
      // (pageNumber - 1) * 10 + 1: 쿼리에서 시작할 SEQ 값을 지정
      ps.setInt(1, seq() - (pageNumber - 1) * 10);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt("seq");
          String nickname = rs.getString("nickname");
          String title = rs.getString("title");
          Date date = rs.getDate("date");
          Board board = new Board(seq, null, nickname, title, null, null, null, date, -1);
          boards.add(board);
        }
        return boards;
      }
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
        int seq = rs.getInt("seq");
        String id = rs.getString("id");
        String nickname = rs.getString("nickname");
        String title = rs.getString("title");
        String content = rs.getString("content");
        String fname = rs.getString("fname");
        String ofname = rs.getString("ofname");
        Date date = rs.getDate("date");
        int valid = rs.getInt("valid");
        Board board = new Board(seq, id, nickname, title, content, fname, ofname, date, valid);
        list.add(board);
      }
      return list;
    } catch (SQLException e) {
      System.out.println("안됨" + e.getMessage());
      return null;
    }
  }

  int seq() {
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SEQ)
    ) {
      if (rs.next()) {
        return rs.getInt("seq") + 1;
      }
    } catch (SQLException se) {
      return -1;
    }
    return 1;
  }

  int insert(Board board) {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(INSERT)
    ) {
      ps.setInt(1, seq());
      ps.setString(2, board.getId());
      ps.setString(3, board.getNickname());
      ps.setString(4, board.getTitle());
      ps.setString(5, board.getContent());
      ps.setString(6, board.getFname());
      ps.setString(7, null);
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
      return ps.executeUpdate();
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
      ps.setString(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return rs.getString("nickname");
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
          String nickname = rs.getString(3);
          String title = rs.getString(4);
          String content = rs.getString(5);
          String fname = rs.getString(6);
          Date date = rs.getDate(8);
          Board board = new Board(seq, null, nickname, title, content, fname, null, date, -1);
          return board;
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
    try(Connection con = dataSource.getConnection();
    PreparedStatement ps = con.prepareStatement(SEARCHCONTENT)
    ) {
      ps.setString(1, "%"+ content +"%");
      ArrayList<Board> list = new ArrayList<>();
      Board board = null;
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt("seq");
          String nickname = rs.getString("nickname");
          String title = rs.getString("title");
          String dbContent = rs.getString("content");
          String fname = rs.getString("fname");
          Date date = rs.getDate("date");
          int valid = rs.getInt("valid");
          board = new Board(seq,null,nickname,title,dbContent,fname,null,date,valid);
          list.add(board);
        }
        return list;
      }
    }catch (SQLException se) {
      return null;
    }
  }
  ArrayList<Board> searchNickname(String nickname) {
    try(Connection con = dataSource.getConnection();
    PreparedStatement ps = con.prepareStatement(SEARCHCONTENT)
    ) {
      ps.setString(1, "%"+ nickname +"%");
      ArrayList<Board> list = new ArrayList<>();
      Board board = null;
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt("seq");
          String dbNickname = rs.getString("nickname");
          String title = rs.getString("title");
          String dbContent = rs.getString("content");
          String fname = rs.getString("fname");
          Date date = rs.getDate("date");
          int valid = rs.getInt("valid");
          board = new Board(seq,null,dbNickname,title,dbContent,fname,null,date,valid);
          list.add(board);
        }
        return list;
      }
    }catch (SQLException se) {
      return null;
    }

  }
  ArrayList<Board> searchTitle(String title) {
    try(Connection con = dataSource.getConnection();
    PreparedStatement ps = con.prepareStatement(SEARCHCONTENT)
    ) {
      ps.setString(1, "%"+ title +"%");
      ArrayList<Board> list = new ArrayList<>();
      Board board = null;
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          int seq = rs.getInt("seq");
          String nickname = rs.getString("nickname");
          String dbTitle = rs.getString("title");
          String dbContent = rs.getString("content");
          String fname = rs.getString("fname");
          Date date = rs.getDate("date");
          int valid = rs.getInt("valid");
          board = new Board(seq,null,nickname,title,dbContent,fname,null,date,valid);
          list.add(board);
        }
        return list;
      }
    }catch (SQLException se) {
      return null;
    }

  }

}