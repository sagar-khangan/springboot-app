package com.att.api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.json.simple.JSONArray;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.*;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

@RestController
@EnableAutoConfiguration

public class AppController {
	AppDAO a = new AppDAO();
	
	public AppController(){
			a.connect();
	
	}
	@GetMapping("/")
	    @ResponseBody
	    String get() {
	        return "Interface API!";
	    }
	
	@GetMapping("/intent")
	@ResponseBody
	String getIntent(){
//		IntentModel i = new IntentModel("test");
//		byte[] res = Bytes.getArray((ByteBuffer) a.getIntent()) ;
//		System.out.println(a.getIntent().all());
//		ResultSet resultSet  = a.getIntent();

		return a.getIntent();
		
		
}
	@GetMapping("/intentbyname")
	@ResponseBody
	IntentModel getIntentByName(@RequestParam("name") String name){
		IntentModel i = new IntentModel(name);
		a.getIntentByName(name);
		return i;
		
}
	@GetMapping("/entity")
	@ResponseBody
	EntityModel getEntity(){
		EntityModel e = new EntityModel(null);
		a.getEntity();
		return e;
		
}
	@GetMapping("/entitybykeyword")
	@ResponseBody
	EntityModel getEntityByKeyword(@RequestParam("keyword") String keyword){
		EntityModel e = new EntityModel(keyword);
		a.getEntityByKeyword(keyword);
		return e;
		
}


	@PostMapping("/intent")
	@ResponseBody
	String postIntent(@RequestParam("name") String name,@RequestParam("sentence") String sentence){
//		IntentModel intent = new IntentModel(name,sentence);
		a.insertIntent(name, sentence);
		return "intent posted";
}


	@PostMapping("/entity")
	@ResponseBody
	String postEntity(@RequestParam("keyword") String keyword,@RequestParam("type") String type){
//		EntityModel entity = new EntityModel(keyword,type);
		a.insertEntity(keyword, type);
		return "entity posted";
}

}
