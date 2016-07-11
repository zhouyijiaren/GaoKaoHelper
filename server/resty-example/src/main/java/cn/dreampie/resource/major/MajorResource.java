package cn.dreampie.resource.major;

import java.util.List;

import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.dreampie.resource.ApiResource;
import cn.dreampie.resource.major.model.UserMajorGuanzhu;
import cn.dreampie.resource.service.MajorServiceImpl;
import cn.dreampie.resource.service.UniversityServiceImpl;
import cn.dreampie.resource.service.interf.MajorService;
import cn.dreampie.resource.service.interf.UniversityService;
import cn.dreampie.resource.university.model.UserUniversityGuanzhu;
import cn.dreampie.resource.user.model.User;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.security.Principal;
import cn.dreampie.security.Subject;

@API("/major")
public class MajorResource extends ApiResource{
	private MajorService majorService = AspectFactory.newInstance(new MajorServiceImpl(), new TransactionAspect());
	@GET("/guanzhu")
	public List guanzhu(){
		Principal<User> principal = Subject.getPrincipal();
		if(principal==null){
			return null;
		}else{
			return UserMajorGuanzhu.dao.findBy("user_id = ?",(principal.getModel()).get("id"));
		}
		
	}
	
	@POST("/moni")
	public List moni(String area,int score){
		return majorService.getRecommendMajor(area,score);
	}
}
