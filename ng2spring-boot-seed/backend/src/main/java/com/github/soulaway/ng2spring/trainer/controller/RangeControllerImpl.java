package com.github.soulaway.ng2spring.trainer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.soulaway.myRestApi.model.Range;
import com.github.soulaway.myRestApi.model.RangeType;
import com.github.soulaway.ng2spring.trainer.service.RangeService;
import com.github.soulaway.ng2spring.trainer.service.TableService;

import io.swagger.annotations.ApiParam;


@Controller
@RequestMapping("/api")
@CrossOrigin
public class RangeControllerImpl implements com.github.soulaway.myRestApi.api.RangeApi{
	
	@Autowired
	private RangeService service;
	
	@Autowired
	private TableService tService;

    public ResponseEntity<Void> deleteRange(@ApiParam(value = "ID of the range to delete",required=true ) @PathVariable("rangeId") Long rangeId) {
    	Optional<Range> range = service.getRanges().stream().filter(r -> r.getRangeId().equals(rangeId)).findFirst();
    	if (range.isPresent()){
    		service.getRanges().remove(range.get());
    		return ResponseEntity.noContent().build();
    	} else {
    		return ResponseEntity.unprocessableEntity().eTag(String.format("Entiity with Id %d was not found", rangeId)).build();
    	}
    }

    public ResponseEntity<Range> findRangeById(@ApiParam(value = "ID of range to fetch",required=true ) @PathVariable("rangeId") Long rangeId) {
    	Optional<Range> range = service.getRanges().stream().filter(r -> r.getRangeId().equals(rangeId)).findFirst();
    	if (range.isPresent()){
    		return ResponseEntity.ok(range.get());
    	} else {
    		return ResponseEntity.unprocessableEntity().eTag(String.format("Entiity with Id %d was not found", rangeId)).body(null);
    	}
    }

    public ResponseEntity<List<Range>> findRanges() {
        return ResponseEntity.ok(service.getRanges());
    }
    
    public ResponseEntity<Range> updateRange(@ApiParam(value = "Range to update" ,required=true ) @RequestBody Range range) {
    	Optional<Range> repoRange = service.getRanges().stream().filter(r -> r.getRangeId().equals(range.getRangeId())).findFirst();
    	if (repoRange.isPresent()){
    		repoRange.get().setName(range.getName());
    		repoRange.get().setValue(range.getValue());
    		repoRange.get().setTable(tService.generate(range.getValue()));
    		return ResponseEntity.ok(repoRange.get());
    	} else {
    		return ResponseEntity.unprocessableEntity().eTag(String.format("Entiity with Id %d was not found", range.getRangeId())).body(null);
    	}
    }

    public ResponseEntity<Range> addRange(@ApiParam(value = "String range representation, e.g. \"AA-QQ,TT-77\"",required=true ) @PathVariable("value") String value,
            @ApiParam(value = "Range type" ,required=true ) @RequestBody RangeType type) {
        Range r = new Range();
        r.setRangeId(new Long(service.getRanges().size()));
        r.setValue(value);
        r.setTable(tService.generate(value));
        r.setType(type);
        r.setName(type.toString());
        service.addRanges(r);
        return ResponseEntity.ok(r);
	}
}