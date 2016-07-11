package cn.dreampie.resource.university.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_university", primaryKey = {"id"}, cached = true)
public class University extends Model<University>{

	public static University dao = new University();
	
	
}
