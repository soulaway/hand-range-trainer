package com.github.soulaway.ng2spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<Range>(HttpStatus.OK).ok(r);
    }
    
    public ResponseEntity<Void> deleteRange(@ApiParam(value = "ID of the range to delete",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Range> findRangeById(@ApiParam(value = "ID of range to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Range>(HttpStatus.OK);
    }

    public ResponseEntity<List<Range>> findRanges() {
        System.out.println("FRONTEND !!!!");
        return new ResponseEntity<List<Range>>(HttpStatus.OK).ok(rangesRep);
    }
}
