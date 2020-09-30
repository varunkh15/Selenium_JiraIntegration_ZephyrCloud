package com.thed.zephyr.cloud.rest.client;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;



import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

public class JWTGetExecutionList {

                public static void main(String[] args) throws IllegalStateException, IOException, URISyntaxException {
                                 
                		JWTGetExecutionList j = new JWTGetExecutionList();
                		j.JWTToken();
                }
                public String JWTToken() throws URISyntaxException
                {
                	  String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
                      String accessKey = "M2I5MTZiODgtYjUyZC0zODZiLThhOGUtOTk1YmJhNGFhMzQ3IDU1NzA1OCUzQWI0M2QwYTczLWI3NjMtNGRkZS05YTI2LTA1OTQ5NWIyYjIzZiBVU0VSX0RFRkFVTFRfTkFNRQ";
                      String secretKey = "yq_ULQ5Cht8qaNp8neZWG4vM4Awj5BomYK865V0SbPU";
                      String userName = "varunkh15@gmail.com";

                      ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
                      JwtGenerator jwtGenerator = client.getJwtGenerator();

                      String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions?issueId=10001&projectId=10000";
                      URI uri = new URI(createCycleUri);
                      int expirationInSec = 360;
                   String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
                 //   String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
                 //  String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);

                    //  System.out.println("FINAL API : " +uri.toString());
               //       System.out.println("JWT Token : " +jwt); 
                	return jwt;
                }
 }