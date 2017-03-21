package com.github.soulaway.ng2spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.soulaway.myRestApi.model.Range;
import io.swagger.annotations.ApiParam;


@Controller()
@RequestMapping("/api")
public class ControllerImpl implements com.github.soulaway.myRestApi.api.RangeApi{
	 @Override 
	public ResponseEntity<Range> addRange(@ApiParam(value = "Pet to add to the store" ,required=true ) @RequestBody Range range) {
        // do some magic!
        return new ResponseEntity<Range>(HttpStatus.OK);
    }
	 @Override
    public ResponseEntity<Void> deleteRange(@ApiParam(value = "ID of the range to delete",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Range> findRangeById(@ApiParam(value = "ID of range to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Range>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<Range>> findRanges() {
        System.out.println("FRONTEND !!!!");
        return new ResponseEntity<List<Range>>(HttpStatus.OK).ok(new ArrayList<Range>());
    }
}
