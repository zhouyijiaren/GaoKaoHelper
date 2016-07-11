package cn.dreampie.resource.news;

import java.util.List;

import cn.dreampie.resource.ApiResource;
import cn.dreampie.resource.SigninValidator;
import cn.dreampie.resource.news.model.News;
import cn.dreampie.resource.user.model.User;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.security.Subject;

@API("/news")
public class NewsResource extends ApiResource{

	 @GET(des = "得到首页新闻")
	  public List getNews() {
	    String sql = "select * from t_news limit 0,10";
	    return News.dao.findColsAll("id,title,address,image");
	  }

	  
}
