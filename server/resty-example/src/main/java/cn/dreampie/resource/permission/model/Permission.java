package cn.dreampie.resource.permission.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_permission", primaryKey = {"id"}, cached = true)
public class Permission extends Model<Permission>{
	public static Permission dao = new Permission();
}
