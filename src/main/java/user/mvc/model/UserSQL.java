package user.mvc.model;

public class UserSQL {

  final static String JOIN = "insert into user(ID, PASSWORD, NAME, EMAIL, AVAILABLE,JOIN_DATE,NICKNAME) values(?,?,?,?,?,now(),?)";

  final static String SIGNUP = "select password from user where ID=?";
}
