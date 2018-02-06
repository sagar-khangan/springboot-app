package com.att.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONArray;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.core.utils.UUIDs.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import flexjson.JSONSerializer;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;

public class AppDAO {

	   final CassandraHelper client = new CassandraHelper();
	   final String ipAddress ="localhost";
	   final int port =9042;
	   
	   void build_schema(){
		   client.getSession().execute("CREATE KEYSPACE IF NOT EXISTS  chatbot WITH replication = {"
				   + " 'class': 'SimpleStrategy', "
				   + " 'replication_factor': '3' "
				   + "};" );
		   client.getSession().execute("CREATE TABLE IF NOT EXISTS chatbot.intent(uuid uuid PRIMARY KEY, name text , sentence text)");
		   client.getSession().execute("CREATE TABLE IF NOT EXISTS chatbot.entity(uuid uuid PRIMARY KEY , keyword text , type text)");
	   }
	   public void connect(){
		   
		   System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		   client.connect(ipAddress, port);
		   build_schema();
		   
//		   client.close();
		   }
	   public String  getIntent(){
		   ResultSet results =  client.getSession().execute("SELECT * FROM chatbot.intent");
		   MappingManager manager = new MappingManager(client.getSession());

			Mapper<IntentModel> mapper = manager.mapper(IntentModel.class);
			Result<IntentModel> intent = mapper.map(results);
			ArrayList<IntentModel> i  = new ArrayList<IntentModel>();
			String s = "";
			for (IntentModel u : intent) {
			    System.out.println("User : " + u.getName());
		
			    i.add(u);
			
			}
		return i.toString();
			
  
	   }
	   public void getIntentByName(String name){
		   client.getSession().execute("SELECT * FROM chatbot.intent");
	   }
	   public void getEntity(){
		   client.getSession().execute("SELECT * FROM chatbot.entity");
	   }
	   public void getEntityByKeyword(String keyword){
		   client.getSession().execute("SELECT * FROM chatbot.entity");
	   }
	   public void insertIntent(String name, String sentence){
		   
		   PreparedStatement prepared = client.getSession().prepare(
				   "INSERT INTO chatbot.intent(uuid,name,sentence) VALUES(?,?,?)");

				 BoundStatement bound = prepared.bind(UUIDs.random(),name,sentence);
				 client.getSession().execute(bound);
	   }
	   public void insertEntity(String keyword, String type){
		   
		   
		   PreparedStatement prepared = client.getSession().prepare(
				   "INSERT INTO chatbot.entity(uuid,keyword,type) VALUES(?,?,?)");

				 BoundStatement bound = prepared.bind(UUIDs.random(),keyword,type);
				 client.getSession().execute(bound);
	   }
	   
	   
}
