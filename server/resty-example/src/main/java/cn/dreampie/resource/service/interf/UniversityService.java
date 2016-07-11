package cn.dreampie.resource.service.interf;

import java.util.List;

import cn.dreampie.orm.transaction.Transaction;

public interface UniversityService {
	@Transaction
	public List getRecommendUniversity(String area, int score);
	
}
