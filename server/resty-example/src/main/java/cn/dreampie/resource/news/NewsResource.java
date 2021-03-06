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
import cn.dreampie.utils.MyLogger;

@API("/news")
public class NewsResource extends ApiResource{

	 @GET(des = "得到首页新闻",value = "/:page")
	  public List getNews(int page) {
		MyLogger.log(page+"");
	    String sql = "select * from t_news limit ?,?";
	    return  News.dao.find(sql, page*10-10,10);
	  }
	 
	 @GET(des = "得到首页新闻第一页")
	  public List getNewsFirst() {
	    String sql = "select * from t_news limit ?,?";
	    return News.dao.find(sql, 0,10);
	  }
}
