package com.amazonaws.lambda.survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.lambda.survey.model.SurveyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class LambdaFunctionHandler implements RequestStreamHandler {

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		JSONObject responseJson = new JSONObject();

		JSONObject responseBody = new JSONObject();

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			JSONObject event = (JSONObject) parser.parse(reader);
			JSONObject qps = (JSONObject) event.get("queryStringParameters");
			
			System.out.println("event: " + event.toJSONString());

			Session session = sessionFactory.openSession();
			List<SurveyResponse> response = new ArrayList<>();
			
			List<?> list = session.createQuery("Select f.preference, count(f.id) from Survey f group by f.preference").list();
			for(int i=0; i<list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				System.out.println(row[0]+", "+ row[1]);
				response.add(new SurveyResponse(String.valueOf(row[0]),String.valueOf(row[1])));
			}

			Gson gson2 = new Gson();
			JsonElement element = gson.toJsonTree(response, new TypeToken<List<SurveyResponse>>() {}.getType());

			if (! element.isJsonArray()) {
			// fail appropriately
			    throw new Exception("custom error");
			}

			JsonArray jsonArray = element.getAsJsonArray();

			responseBody.put("content", jsonArray);
			responseBody.put("numberOfElements", response.size());		
			responseJson.put("body", responseBody.toString());

			JSONObject headerJson = new JSONObject();
			headerJson.put("access-control-allow-headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
			headerJson.put("access-control-allow-methods", "GET,OPTIONS");
			headerJson.put("allow", "GET,OPTIONS");

			headerJson.put("x-custom-header", "sample lambda with api gateway");
			headerJson.put("content-type", "application/json");
			headerJson.put("Access-Control-Allow-Origin", "*");
			responseJson.put("headers", headerJson);
			responseJson.put("statusCode", 200);

		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("statusCode", 400);
			responseJson.put("exception", e);
		}

		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
		writer.write(responseJson.toString());
		writer.close();
		reader.close();
	}

}