package mvc.domain;

import java.sql.Date;

public class Board {
  private int seq;
  private String id;
  private String nickname;
  private String title;
  private String content;
  private String fname;
  private String ofname;
  private Date date;
  private int valid;


  public Board(int seq, String id, String nickname, String title, String content, String fname,
      String ofname, Date date, int valid) {
    this.seq = seq;
    this.id = id;
    this.nickname = nickname;
    this.title = title;
    this.content = content;
    this.fname = fname;
    this.ofname = ofname;
    this.date = date;
    this.valid = valid;
  }
  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getOfname() {
    return ofname;
  }

  public void setOfname(String ofname) {
    this.ofname = ofname;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getValid() {
    return valid;
  }

  public void setValid(int valid) {
    this.valid = valid;
  }


  public Board() {
  }


}
