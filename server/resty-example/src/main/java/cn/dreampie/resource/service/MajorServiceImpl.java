package cn.dreampie.resource.service;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.orm.Record;
import cn.dreampie.resource.service.interf.MajorService;

public class MajorServiceImpl implements MajorService {

	

	public List getRecommendMajor(String area, int score
			) {
		List<Integer> list = getAdviseScores(score);
		Record majorDao = new Record();
		List<Record> records = majorDao.
				find("select * from  (select university_id,major_id,score_line,major_score_line from t_major_area t3 LEFT JOIN t_university_area t4 on t3.university_area_id=t4.id where area_id=?) t1 left join t_major t2 on t1.major_id = t2.id where major_score_line>? and major_score_line<?"
				, area,list.get(0),list.get(1));
		List listMajor = new ArrayList();
		for(Record r:records){
			listMajor.add(r.getAttrs());
		}
		return listMajor;
	}
	
	/**通过一系列算法  或者 今年的总体人数排名  推荐专业分数范围
	 * @return
	 */
	public List<Integer> getAdviseScores(int score){
		List ret = new ArrayList<Integer>(2);
		ret.add(score-10);
		ret.add(score+10);
		return ret;
	}

}
