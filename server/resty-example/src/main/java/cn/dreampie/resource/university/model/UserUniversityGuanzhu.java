package cn.dreampie.resource.university.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_user_university_guanzhu", primaryKey = {"id"}, cached = true)
public class UserUniversityGuanzhu extends Model<UserUniversityGuanzhu>{
	public static UserUniversityGuanzhu dao = new UserUniversityGuanzhu();
}
