package cn.dreampie.config;

import cn.dreampie.resource.permission.model.Permission;
import cn.dreampie.resource.user.model.User;
import cn.dreampie.security.AuthenticateService;
import cn.dreampie.security.PasswordService;
import cn.dreampie.security.Principal;
import cn.dreampie.security.credential.Credential;
import cn.dreampie.utils.MyLogger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ice on 15-1-7.
 */
public class MyAuthenticateService extends AuthenticateService {


  public PasswordService getPasswordService() {
    return super.getPasswordService();
  }

  /**
   * 查询用户信息  这儿new一个用户对象来模拟
   *
   * @param username 登录的用户名
   * @return 用户权限对象
   */
  public Principal getPrincipal(String username) {
    PasswordService passwordService = getPasswordService();

    User u = User.dao.findFirstBy("username = ?", username);
    if(u==null){
    	return null;
    }
    
    u.put("permissions", u.getPermissions());
    
//    User u = new User().set("username", username).set("password", passwordService.crypto("123", "x")).put("permissions", new HashSet<String>() {{
//      add("users");
//    }});
//    MyLogger.log(u.get("permissions").toString());
    Principal<User> principal = new Principal<User>(u.<String>get("username"), u.<String>get("password"), "x", new HashSet(u.getPermissions()), u);
    return principal;
  }

  /**
   * 加载"全部"的权限信息,跟用户个人权限没有关系
   *
   * @return 权限集合
   */
  public Set<Credential> getAllCredentials() {
	  Set<Credential> credentials = new HashSet<Credential>();
	  List<Permission> permissions = Permission.dao.findAll();
	  for(Permission p:permissions){
		  credentials.add(new Credential(p.get("httpMethod").toString(), "/api/v1.0"+p.get("url").toString(), p.get("value").toString()));
	  }
    
    return credentials;
  }
}
