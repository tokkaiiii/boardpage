package mvc.domain;

import java.sql.Date;

public class Board {

  private final int seq;
  private final String id;
  private final String nickname;
  private final String title;
  private final String content;
  private final String fname;
  private final String ofname;
  private final Date date;
  private final int valid;


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

  public String getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getFname() {
    return fname;
  }

  public String getOfname() {
    return ofname;
  }

  public Date getDate() {
    return date;
  }

  public int getValid() {
    return valid;
  }

  public static BoardBuilder builder() {
    return new BoardBuilder();
  }

  public static class BoardBuilder {

    private int seq;
    private String id;
    private String nickname;
    private String title;
    private String content;
    private String fname;
    private String ofname;
    private Date date;
    private int valid;

    public BoardBuilder seq(int seq) {
      this.seq = seq;
      return this;
    }

    public BoardBuilder id(String id) {
      this.id = id;
      return this;
    }

    public BoardBuilder nickname(String nickname) {
      this.nickname = nickname;
      return this;
    }

    public BoardBuilder title(String title) {
      this.title = title;
      return this;
    }

    public BoardBuilder content(String content) {
      this.content = content;
      return this;
    }

    public BoardBuilder fname(String fname) {
      this.fname = fname;
      return this;
    }

    public BoardBuilder ofname(String ofname) {
      this.ofname = ofname;
      return this;
    }

    public BoardBuilder date(Date date) {
      this.date = date;
      return this;
    }

    public BoardBuilder valid(int valid) {
      this.valid = valid;
      return this;
    }
    public Board build() {
      return new Board(this.seq,this.id,this.nickname,this.title,this.content,this.fname,this.ofname,this.date,this.valid);
    }
  }

  @Override
  public String toString() {
    return "Board{" +
        "seq=" + seq +
        ", id='" + id + '\'' +
        ", nickname='" + nickname + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", fname='" + fname + '\'' +
        ", ofname='" + ofname + '\'' +
        ", date=" + date +
        ", valid=" + valid +
        '}';
  }
}
