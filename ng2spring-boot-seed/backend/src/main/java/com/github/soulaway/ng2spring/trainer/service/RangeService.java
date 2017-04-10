package com.github.soulaway.ng2spring.trainer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.soulaway.myRestApi.model.Hand;
import com.github.soulaway.myRestApi.model.Range;
import com.github.soulaway.myRestApi.model.RangeStat;

@Service
public class RangeService {
	
	private List <Range> ranges = new ArrayList<Range>();
	
	private Map <Long, RangeStat> stats = new HashMap<Long, RangeStat>();

	public List <Range> getRanges() {
		return ranges;
	}

	public void addRanges(Range range) {
		this.ranges.add(range);
	}

	public RangeStat getStat(Long rangeId) {
		if (!stats.containsKey(rangeId)){
			RangeStat rs = new RangeStat();
			rs.setRangeId(rangeId);
			rs.setTotal(new Integer(0));
			rs.successed(new Integer(0));
			rs.setLastTry(false);
			stats.put(rangeId, rs);
		}
		return stats.get(rangeId);
	}

	public void updateStat(RangeStat stat) {
		stats.put(stat.getRangeId(), stat);
	}
	
	//TODO
	public boolean validate(Hand hand){
		String t = String.valueOf(System.currentTimeMillis());
		return (Integer.parseInt(t.substring(t.length()-2)) > 50) ? false : true;
	}
	
}
