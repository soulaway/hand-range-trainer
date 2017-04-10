package com.github.soulaway.ng2spring.trainer.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.soulaway.myRestApi.model.Card;
import com.github.soulaway.myRestApi.model.Hand;
import com.github.soulaway.myRestApi.model.RangeStat;
import com.github.soulaway.ng2spring.trainer.enums.HandPack;
import com.github.soulaway.ng2spring.trainer.service.RangeService;

import io.swagger.annotations.ApiParam;

// TODO separate logic to HandSevice

@Controller()
@RequestMapping("/api")
@CrossOrigin
public class HandControllerImpl implements com.github.soulaway.myRestApi.api.HandApi{
	
	@Autowired
	private RangeService service;
	
	public ResponseEntity<Hand> genHand(@ApiParam(value = "Range Id of the new Hand being generated",required=true ) @PathVariable("rangeId") Long rangeId) {
        
		Hand h = new Hand();
        h.setRangeId(rangeId);
        
        HandPack first = HandPack.getByCode(getCard(null));
        
        Card l = new Card();
        l.setRank(first.getValue());
        l.setSuite(String.valueOf(first.getColor()));
        
        HandPack second = HandPack.getByCode(getCard(first.getCode()));
        
        Card r = new Card();
        r.setRank(second.getValue());
        r.setSuite(String.valueOf(second.getColor()));
        
        h.setLeft(l);
        h.setRight(r);
        
        return ResponseEntity.ok(h);
    }
	

    public ResponseEntity<RangeStat> solveHand(@ApiParam(value = "Hand to solve" ,required=true ) @RequestBody Hand hand) {
        RangeStat rs = service.getStat(hand.getRangeId());
        boolean result = service.validate(hand);
        rs.setTotal(rs.getTotal() + 1);
        rs.setLastTry(result);
        if (result) {
        	rs.setSuccessed(rs.getSuccessed() + 1);
        }
        return ResponseEntity.ok(rs);
    }

	// return a card-code that defers from given pairCode
	
    private Integer getCard(Integer pairCode){
    	Integer ret = new Random().nextInt(61);
    	if ((ret > 12 && ret < 16) || (ret > 28 && ret < 32) || (ret > 44 && ret < 48) || (pairCode != null && pairCode == ret)){
    		return getCard(pairCode);
    	} else {
    		return ret;
    	}
    }

}

