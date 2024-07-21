package user.mvc.model;

import mvc.domain.User;

public class UserService {

  private UserDAO dao;

  private static final UserService instance = new UserService();

  private UserService() {dao = new UserDAO();}

  public static UserService getInstance() {return instance;}


  public int join(User user) {return dao.join(user);}

  public int signup(User user) {return dao.signup(user);}
}
