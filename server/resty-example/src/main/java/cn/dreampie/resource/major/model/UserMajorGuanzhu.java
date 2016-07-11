package cn.dreampie.resource.major.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_user_major_guanzhu", primaryKey = {"id"}, cached = true)
public class UserMajorGuanzhu extends Model<UserMajorGuanzhu>{
	public static UserMajorGuanzhu dao = new UserMajorGuanzhu();
}
