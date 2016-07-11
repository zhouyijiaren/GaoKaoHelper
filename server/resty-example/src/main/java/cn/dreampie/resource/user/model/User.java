package cn.dreampie.resource.user.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;
import cn.dreampie.utils.MyLogger;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**涉及到命名问题，sql中也有表名
 * Created by ice on 14-12-31.
 */
@Table(name = "t_user", primaryKey = {"id"}, cached = true)
public class User extends Model<User> {
  public static User dao = new User();

  // 默认 getXxx 的形式的方法 会被认为是属性 如果userInfos的值不存在 方法会被执行一次
  // json反转时  如果 getXxx的存在  会按 getXxx的返回值类型 进行转换 如：{userInfos:[{key:value,key1:value1}]} userInfos会被转换为  List<UserInfo>类型
  //  @JSONField(serialize = false) 如果getXxx的值不转为json  使用该注解
  // 注意属性名和GetXxx一致   如:属性userInfos的get方法为 getUserInfos
  // 支持驼峰和下划线 两种属性名字和驼峰方法的映射 (userInfos也可以使用下划线模式 user_infos全小写 也会映射到getUserInfos()方法)
  // 个人喜欢数据库和属性 都使用下划线的方式
  
  
//  @JSONField(serialize = false)
//  public List<UserInfo> getUserInfos() {
//	  MyLogger.logImpor("in   getUserInfos");
//    if (this.get("user_infos") == null && this.get("id") != null) {
//      this.put("user_infos", UserInfo.dao.findBy("user_id=?", this.get("id")));
//    }
//    return this.get("user_infos");
//  }

  public Long getRoleId() {
    if (this.get("role_id") == null && this.get("id") != null) {
    	MyLogger.log("in getRoleId");
      String sql = "SELECT user_role.role_id FROM t_user_role user_role WHERE user_role.user_id=?";
      this.put("role_id", queryFirst(sql, this.get("id")));
    }
    return this.get("role_id");
  }

  public List<String> getPermissions() {
    Long roleId = getRoleId();
    if (this.get("permissions") == null && roleId != null) {
      String sql = "SELECT permission.value FROM t_permission permission WHERE permission.id in(SELECT rolePermission.permission_id FROM t_role_permission rolePermission WHERE rolePermission.role_id=?)";
      this.put("permissions", query(sql, roleId));
    }
    return this.get("permissions");
  }

  public List<Long> getPermissionIds() {
    Long roleId = getRoleId();
    if (this.get("permission_ids") == null && roleId != null) {
      String sql = "SELECT permission.id FROM t_permission permission WHERE permission.id in(SELECT rolePermission.permission_id FROM t_role_permission rolePermission WHERE rolePermission.role_id=?)";
      this.put("permission_ids", query(sql, roleId));
    }
    return this.get("permission_ids");
  }
}
