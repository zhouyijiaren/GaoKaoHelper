package cn.dreampie.resource.service;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.orm.Record;
import cn.dreampie.resource.service.interf.UniversityService;
import cn.dreampie.resource.university.model.UniversityArea;
import cn.dreampie.resource.university.model.UserUniversityGuanzhu;

public class UniversityServiceImpl implements UniversityService {

	

	public List getRecommendUniversity(String area, int score
			) {
		List<Integer> list = getAdviseScores(score);
		Record universityDao = new Record();
		List<Record> records = universityDao.
				find("select * from  t_university_area t1 left join t_university t2 on t1.university_id = t2.id where area_id=? and score_line>? and score_line<?"
				, area,list.get(0),list.get(1));
		List listUniversity = new ArrayList();
		for(Record r:records){
			listUniversity.add(r.getAttrs());
		}
		return listUniversity;
	}
	
	/**通过一系列算法  或者 今年的总体人数排名  推荐学校分数范围
	 * @return
	 */
	public List<Integer> getAdviseScores(int score){
		List ret = new ArrayList<Integer>(2);
		ret.add(score-10);
		ret.add(score+10);
		return ret;
	}

}
