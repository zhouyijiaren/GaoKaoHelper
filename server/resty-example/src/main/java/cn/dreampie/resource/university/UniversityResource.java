package cn.dreampie.resource.university;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.dreampie.resource.ApiResource;
import cn.dreampie.resource.service.UniversityServiceImpl;
import cn.dreampie.resource.service.UserServiceImpl;
import cn.dreampie.resource.service.interf.UniversityService;
import cn.dreampie.resource.service.interf.UserService;
import cn.dreampie.resource.university.model.University;
import cn.dreampie.resource.university.model.UserUniversityGuanzhu;
import cn.dreampie.resource.user.model.User;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.security.Principal;
import cn.dreampie.security.Subject;

@API("/university")
public class UniversityResource extends ApiResource{
	private UniversityService universityService = AspectFactory.newInstance(new UniversityServiceImpl(), new TransactionAspect());
	@GET("/guanzhu")
	public List guanzhu(){
		Principal<User> principal = Subject.getPrincipal();
		if(principal==null){
			return null;
		}else{
			return UserUniversityGuanzhu.dao.findBy("user_id = ?",(principal.getModel()).get("id"));
		}
		
	}
	
	@POST("/moni")
	public List moni(String area,int score){
		return universityService.getRecommendUniversity(area,score);
	}
	
	@GET("/:id")
	public University getById(int id){
		return University.dao.findById(id);
	}
	
	
}
