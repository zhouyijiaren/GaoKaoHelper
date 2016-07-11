package cn.dreampie.resource.service.interf;

import java.util.List;

import cn.dreampie.orm.transaction.Transaction;

public interface MajorService {
	@Transaction
	public List getRecommendMajor(String area, int score);
}
