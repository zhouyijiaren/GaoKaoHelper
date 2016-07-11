package cn.dreampie.resource.university.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_university_area", primaryKey = {"id"}, cached = true)
public class UniversityArea extends Model<UniversityArea>{

	public static UniversityArea dao = new UniversityArea();
	
	
}
