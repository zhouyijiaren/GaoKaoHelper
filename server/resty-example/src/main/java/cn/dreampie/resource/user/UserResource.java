package cn.dreampie.resource.user;

import static cn.dreampie.common.util.Checker.checkNotNull;
import cn.dreampie.common.http.exception.WebException;
import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.page.FullPage;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.dreampie.resource.ApiResource;
import cn.dreampie.resource.user.model.User;
import cn.dreampie.resource.service.UserServiceImpl;
import cn.dreampie.resource.service.interf.UserService;
import cn.dreampie.route.annotation.*;
import cn.dreampie.security.DefaultPasswordService;
import cn.dreampie.security.PasswordService;
import cn.dreampie.utils.MyLogger;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ice on 14-12-29.
 */
@API("/users")
public class UserResource extends ApiResource {
  //用于使用service层的 事务
  // @Transaction(name = {"default", "demo"})的注解需要写在service的接口上
  // 注意java的自动代理必须存在接口
  private UserService userService = AspectFactory.newInstance(new UserServiceImpl(), new TransactionAspect());
  
  
  
  //注册的时候用到password service
  public PasswordService getPasswordService() {
	    return DefaultPasswordService.instance();
	  }
  PasswordService passwordService = getPasswordService();
  
  
  //查询集合
  @GET
  public List<User> findAll() {
    return User.dao.findAll();
  }

  //查询单个user对象
  @GET("/:id")
  public User find(String id) {
    return User.dao.findById(id);
  }

  //全部对象分页 http://localhost:8081/api/v1.0/users/1/10
  @GET("/:pageNumber/:pageSize")
  public FullPage<User> paginate(int pageNumber, int pageSize) {
    return User.dao.fullPaginateAll(pageNumber, pageSize);
  }

  //按条件分页 http://localhost:8081/api/v1.0/users/1/10/x
  @GET("/:page_Number/:_page$Size/:$term")
  public FullPage<User> paginate(int page_Number, int _page$Size, String $term) {
    return User.dao.fullPaginateBy(page_Number, _page$Size, "username=?", $term);
  }

  //更新
  @PUT
  public User put(User user) {
    user.update();
    return user;
  }

  //保存
  @POST("/:id")
  public Set<User> save(int id, Set<User> users) {
    userService.save(users.iterator().next());
    return users;
  }
  
  //"Could not cast \"{    \"username\":\"zhoux3\",    \"password\":\"123\"    \"repassword\":\"123\"}\" to cn.dreampie.resource.user.model.User"
  //还没有写role的相关操作
  @POST("/register")
  public User register(String username,String password,String repassword,String avatar_url){
	  checkNotNull(username, "Username could not be null.");
		if(isHave(username)){
			throw new WebException("username has been registerred");
		}
		checkNotNull(password, "Password could not be null.");
		if(!password.equals(repassword)){
			throw new WebException("Password is not equal Repassword");
		}
		User user = new User();
		user.set("providername", "x");
		user.set("username",username);
		user.set("password",passwordService.crypto(password, "x"));
	  return userService.register(user);
  }
  
  /**判断username是不是已经有了
 * @param username
 * @return
 */
@GET("/isHave/:username")
  public boolean isHave(String username){
	  return userService.isHave(username);
  }
  

  //删除
  @DELETE("/:id")
  public boolean put(String id) {
    String xx = getParam("xx");
    return User.dao.deleteById(id);
  }


  @GET("/transactions")
//  @Transaction
  public User transaction() {
    User u = new User().set("username", "testtr").set("providername", "test").set("password", "123456").set("sid", "1").set("created_at", new Date());
//    UserInfo userInfo = null;
//    if (u.get("user_info") == null) {
//      userInfo = new UserInfo().set("gender", 0);
//    } else {
//      userInfo = u.get("user_info");
//    }
//    if (u.save()) {
//      userInfo.set("user_id", u.get("id"));
//      userInfo.save();

//      int[] a = new int[0];
//      System.out.println(a[2]);  报错 让事务回滚
//    }
//    u.set("id", u.get("id")).set("username", "x").update();
//    return u;
    //service层的事务
    userService.save(u);
    return null;
  }

}
