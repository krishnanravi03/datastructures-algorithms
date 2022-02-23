package com.datastructures;

import java.util.*;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import projects.Maze;
import projects.WordPuzzle;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
   
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        try {
            String httpmethod = apiGatewayProxyRequestEvent.getHttpMethod();
            switch(httpmethod){
                case "GET": generateResponse(apiGatewayProxyResponseEvent, "Hello use POST requests to generate maze/puzzle!!");
                            return apiGatewayProxyResponseEvent;
                case "POST": handlePOST(apiGatewayProxyRequestEvent, apiGatewayProxyResponseEvent);
                            return apiGatewayProxyResponseEvent;
                default: generateResponse(apiGatewayProxyResponseEvent, "Invalid http method: " + httpmethod + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiGatewayProxyResponseEvent;
    }

    private void generateResponse(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent, String requestMessage) {
        apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Access-Control-Allow-Origin", "*"));
        apiGatewayProxyResponseEvent.setStatusCode(200);
        apiGatewayProxyResponseEvent.setBody(requestMessage);
    }

    private void handlePOST(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent){

        try{
            String requestString = apiGatewayProxyRequestEvent.getBody();

            JSONParser parser = new JSONParser();
            JSONObject requestJsonObject = (JSONObject) parser.parse(requestString);
            String requestMessage = null;
            String responseMessage = null;
            if (requestJsonObject != null) {
                if (requestJsonObject.get("type") != null) {
                    String type = requestJsonObject.get("type").toString();
                    Integer rows = Integer.parseInt(requestJsonObject.get("rows").toString());
                    Integer columns = Integer.parseInt(requestJsonObject.get("columns").toString());
                    if(type.equals("maze")){
                        responseMessage = Maze.run(rows, columns);
                    }
                    else if(type.equals("puzzle")){
                        List<String> puzzle = WordPuzzle.run(rows, columns, false);
                        List<String> solution = new ArrayList<>();
                        for(int i=1; i<puzzle.size(); i++){
                            solution.add(puzzle.get(i));
                        }
                        JSONObject response = new JSONObject();
                        response.put("puzzle", puzzle.get(0));
                        response.put("solution", solution);
                        responseMessage = response.toJSONString();

                    }
                    else{
                        responseMessage = "Unsupported Type";
                    }
                }
            }
            else{
                responseMessage = "Request body is null, Can't process";
            }
            Map<String, String> responseBody = new HashMap<>();
            
            responseBody.put("responseMessage", responseMessage);
            responseMessage = new JSONObject(responseBody).toJSONString();
            generateResponse(apiGatewayProxyResponseEvent, responseMessage);

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        
    }


}
