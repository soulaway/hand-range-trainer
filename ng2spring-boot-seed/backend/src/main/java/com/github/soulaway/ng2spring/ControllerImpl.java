package com.github.soulaway.ng2spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.soulaway.myRestApi.model.Range;

import io.swagger.annotations.ApiParam;


@Controller()
@RequestMapping("/api")
public class ControllerImpl implements com.github.soulaway.myRestApi.api.RangeApi{
	
	private List <Range> rangesRep = new ArrayList<Range>();
	
    public ResponseEntity<Range> addRange(@ApiParam(value = "Range added",required=true ) @PathVariable("id") String id) {
        Range r = new Range();
        r.setName(id);
        r.setId(new Long(rangesRep.size()));
        rangesRep.add(r);
        return ResponseEntity.ok(r);
    }
    
    public ResponseEntity<Void> deleteRange(@ApiParam(value = "ID of the range to delete",required=true ) @PathVariable("id") Long id) {
    	Optional<Range> range = rangesRep.stream().filter(r -> r.getId().equals(id)).findFirst();
    	if (range.isPresent()){
    		rangesRep.remove(range.get());
    		return ResponseEntity.ok().build();
    	} else {
    		return ResponseEntity.unprocessableEntity().eTag(String.format("Entiity with Id %d was not found", id)).build();
    	}
    }

    public ResponseEntity<Range> findRangeById(@ApiParam(value = "ID of range to fetch",required=true ) @PathVariable("id") Long id) {
    	Optional<Range> range = rangesRep.stream().filter(r -> r.getId().equals(id)).findFirst();
    	if (range.isPresent()){
    		return ResponseEntity.ok(range.get());
    	} else {
    		return ResponseEntity.unprocessableEntity().eTag(String.format("Entiity with Id %d was not found", id)).body(null);
    	}
    }

    public ResponseEntity<List<Range>> findRanges() {
        return ResponseEntity.ok(rangesRep);
    }
}
