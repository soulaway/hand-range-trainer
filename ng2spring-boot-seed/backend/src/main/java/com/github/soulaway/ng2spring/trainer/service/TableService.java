package com.github.soulaway.ng2spring.trainer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.soulaway.myRestApi.model.TableItem;
import com.github.soulaway.myRestApi.model.TableItems;
import com.github.soulaway.myRestApi.model.RangeTable;

/**
 * Table service represents a model of the Poker Ranges Table 
 * e.g.: http://www.pokerhandrange.com/ 
 * Consists of 3 arrays that describes a symmetric matrix: 
 * Vector for main diagonal pairs and both low and high triangle matrixes for unpaired hands
 * 
 * @author soul
 *
 */
@Service
public class TableService {
	
	private static final String[] cards = {"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
	private static final String RANGE_SEPARATOR = ",";
	private static final String RANGE_DELEMETER = "-";
	
	private List<String> r = new ArrayList<String>(cards.length);
	private List<List<String>> ro = new ArrayList<List<String>>(cards.length - 1);
	private List<List<String>> rs = new ArrayList<List<String>>(cards.length - 1);
	
	public TableService(){
		for (int i = 0; i < cards.length; i++){
			ro.add(new ArrayList<String>(cards.length - i + 1));
			rs.add(new ArrayList<String>(cards.length - i + 1));
			for (int j = i; j < cards.length; j++){
				if (i == j){
					r.add(cards[i]+cards[j]);
				} else {
					ro.get(i).add(cards[i]+cards[j]+"o");
					rs.get(i).add(cards[i]+cards[j]+"s");
				}
			}
		}		
	}
	
	public RangeTable generate(String range){
		// creating new range model 
		RangeTable mtx = new RangeTable();
		mtx.setMainDiag(readRanges(r));
		List<TableItems> roList = new ArrayList<>(ro.size());
		ro.stream().forEach(li -> {
			roList.add(readRanges(li));
		});
		List<TableItems> rsList = new ArrayList<>(rs.size());
		rs.stream().forEach(li -> {
			rsList.add(readRanges(li));
		});		
		mtx.setMtxO(roList);
		mtx.setMtxS(rsList);
		parseRanges(range, mtx);
		return mtx;
	}
/**
 * TODO refactor static
 */
	private static void parseRanges(String rangesLine, RangeTable mtx){
		int rank = 0;		
		int prev_rank = rank;
		System.out.println(rangesLine);
		String[] ranges = rangesLine.split(RANGE_SEPARATOR);
		for (String subrange : ranges) {
			
			String low = subrange;
			String high = null;
			boolean isRankedEnds = false;
			int rankMarkCount = subrange.length() - subrange.replace("[", "").length();
			switch (rankMarkCount) {
				// ranked diapason starts OR ends
				case 1: {
					// if end of ranked range
					if (low.contains("[/")){
						if (rank == Integer.valueOf(low.substring(low.indexOf("/") + 1, low.indexOf("]")))){ // TODO EH parse
							isRankedEnds = true;
						} else {
							// TODO EH synt
						}
						low = low.substring(0, low.indexOf("["));
					} else 
					// if start of ranked range
					if (low.contains("[")){
						prev_rank = rank;
						rank = Integer.valueOf(low.substring(low.indexOf("[") + 1, low.indexOf("]"))); // TODO EH
						low = low.substring(low.indexOf("]") + 1);
					}
					break;
				}
				// ranked diapason for single range (both starts and ends)
				case 2: {
					rank = Integer.valueOf(low.substring(low.indexOf("[") + 1, low.indexOf("]")));
					low = low.substring(low.indexOf("]") + 1, low.indexOf("[/"));
					break;
				}
				default: {
					break;
				}
			}
			
			// if diapason
			if (low.contains(RANGE_DELEMETER)){
				high = low.substring(low.indexOf(RANGE_DELEMETER) + 1, low.length());
				low = low.substring(0, low.indexOf(RANGE_DELEMETER));
			}
			
			System.out.println(low + " " + high + " " + rank + " " + prev_rank);	
			setRankOption(mtx, low, high, rank);
			if (isRankedEnds){
				rank = prev_rank;
				prev_rank = 0;
			}
		}
	}
	
	private static void setRankOption(RangeTable mtx, String low, String high, int rank){
		if (containsValue(mtx.getMainDiag().getValues(), low)){
			setOption(mtx.getMainDiag().getValues(), low, high, rank);
		} else {
			for (int i = 0; i < mtx.getMtxO().size(); i++){
				if (containsValue(mtx.getMtxO().get(i).getValues(), low)){
					setOption(mtx.getMtxO().get(i).getValues(), low, high, rank);
				} else if (containsValue(mtx.getMtxS().get(i).getValues(), low)){
					setOption(mtx.getMtxS().get(i).getValues(), low, high, rank);
				}
			}
		}
	}
	
	private static boolean containsValue(List<TableItem> items, String value){
		long count =  items.stream().filter(i -> i.getVal().equals(value)).count();
		if (count > 1){
			System.err.println("ERR: TableItem contains " + count + " values of " + value);
		}
		return count == 1;
	}
	
	private static void setOption(List<TableItem> items, String low, String high, int rank){
		if (high != null){
			if (containsValue(items, high)){
				boolean isRanked = false;
				for (TableItem i : items){
					if (isRanked || i.getVal().equals(low)){
						isRanked = true;
						i.setOption(rank);
						if (i.getVal().equals(high)){
							isRanked = false;
							break;
						}
					}
				}
			} else {
				System.err.println("ERR: TableItem not contains the high value of " + high);
			}
		} else {
			Optional<TableItem> oi = items.stream().filter(i -> i.getVal().equals(low)).findFirst();
			if (oi.isPresent()){
				oi.get().setOption(rank);
			} else {
				System.err.println("ERR: TableItem not contains the low value of " + low);
			}
		}
	}
	
	private static TableItems readRanges(List<String> ranges){
		TableItems result = new TableItems();
		ranges.stream().forEach(r -> {
			TableItem item = new TableItem();
			item.setVal(r);
			result.getValues().add(item);			
		});
		return result;
	}
	
	// creates a sequential list with TableItems, as it displays for user
	// already ported to front-end
	/*
	public List<TableItem> getTable(RangeTable mtx){
		List<TableItem> ret = new ArrayList<TableItem>();
		for (int i = 0; i < cards.length; i++){
			for (int j = 0; j < cards.length; j++){
				TableItem val;
				if (i == j){
					val = mtx.getMainDiag().getValues().get(i);
				} else {
					val = (i > j) 
							? mtx.getMtxO().get(j).getValues().get(i - j - 1)
							: mtx.getMtxS().get(i).getValues().get(j - (i + 1));
				}
				ret.add(val);
			}
		}
		return ret;
	}
	*/
}