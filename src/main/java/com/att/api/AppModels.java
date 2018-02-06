package com.att.api;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "chatbot" ,name = "intent")
class IntentModel{
	private UUID uuid;
	private String name;
	private String sentence;
	
	IntentModel(){
		
	}
	
	public IntentModel(String n){
		this.name=n;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}


}

@Table(keyspace = "chatbot" ,name = "entity")
class EntityModel{
	private String keyword;
	private String type;
	
	public EntityModel(String keyword){
		this.keyword=keyword;
	
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
