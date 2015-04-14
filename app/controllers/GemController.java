package controllers;


import java.util.ArrayList;

import models.Gem;
import models.GemList;
import models.Person;
import models.PersonList;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GemController extends Controller{
	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeGem(){
		ObjectMapper mapper = new ObjectMapper();
		 try {
			 System.err.println("POST Data");
			 JsonNode json = request().body().asJson();
			 System.err.println("json payload: " + json);
			 Gem newGem = mapper.readValue(json.toString(), Gem.class);
			 GemList gemList = GemList.getInstance(); 
			 gemList.addGem(newGem);
			 ObjectNode result = Json.newObject();
			 result.set("Gem", Json.toJson(newGem));
			 return created(result);
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return badRequest("Missing information");
		 }
		
	}
	
	public static Result getGem(Integer id){
		// DEBUG 
		System.err.println("GET on id: "+ id);
		
		ObjectNode result = Json.newObject();
		GemList theList = GemList.getInstance();
		Gem P = theList.getGemById(id);
		if (P == null){
			return notFound(); // 404
		}
		else {
			result.set("Gem", Json.toJson(P));
			return ok(result);
	
		}
	}
	
	public static Result deleteGem(Long id){
		GemList theList = GemList.getInstance();
		boolean erased = theList.deleteGem(id);
		System.err.println("Trying to delete a Gem");
		if (erased){
			// This is code 204 - OK with no content to return
			return noContent();
		}
		else {
			return notFound("Person Not Found");
		}

	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateGem(Long id){
		ObjectMapper mapper = new ObjectMapper();
		try {

			 JsonNode json = request().body().asJson();
			 Gem updGem = mapper.readValue(json.toString(), Gem.class);
			 GemList theList = GemList.getInstance(); 
			 updGem = theList.updateGem(updGem);
			 if (updGem == null){
				return notFound("Gem Not Found"); // 404 
			 }
			 else {
				 ObjectNode result = Json.newObject();
				 result.put("Gem", Json.toJson(updGem));
				 return ok(result);
			 }
		}
		catch(Exception e){
			 e.printStackTrace();
			 return badRequest("Missing information");			
		}
	}
	
	//Add more controllers i.e getAll Gems to populate page
	public static Result getGems(){
		System.err.println("Get all gems");
		//ArrayList<Gem> newList = new ArrayList<Gem>();
		GemList theList = GemList.getInstance();
//		for(int i = 1; i<theList.size();i++){
//			newList.add(theList.getGemById(i));
//		}
		Gem[] newerList = theList.getAllGems();
		JsonNode result = Json.toJson(newerList);
		return ok(result);
		
//		System.err.println("GET all Gems");
//		
//		ObjectNode result = Json.newObject();
//		GemList theList = GemList.getInstance();
//		Gem[] P = theList.getAllGems();
//		if (P == null){
//			return notFound(); // 404
//		}
//		else {
//			result.put("Gem", Json.toJson(P));
//			return ok(result);
//	
//		}
	}
}
