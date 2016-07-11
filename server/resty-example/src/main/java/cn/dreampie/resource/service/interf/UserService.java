package cn.dreampie.resource.service.interf;

import cn.dreampie.orm.transaction.Transaction;
import cn.dreampie.resource.user.model.User;

/**
 * Created by wangrenhui on 15/1/2.
 */
public interface UserService {
  @Transaction
  public User save(User u);

  @Transaction
  public User update(User u);
  
  @Transaction
  public User register(User u);

public boolean isHave(String username);
}
