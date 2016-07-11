package cn.dreampie.resource.news.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

@Table(name = "t_news", primaryKey = {"id"}, cached = true)
public class News extends Model<News>{
	public static News dao = new News();
}
