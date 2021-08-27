package org.example.basicapp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazon.dax.client.dynamodbv2.AmazonDaxClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class NewMovie {

    public static void main(String[] args) throws Exception {



        try {
            System.out.println(new Date());
            ClientThread c1 = new ClientThread();
            ClientThread c2 = new ClientThread();
            ClientThread c3 = new ClientThread();
            ClientThread c4 = new ClientThread();
            ClientThread c5 = new ClientThread();
            ClientThread c6 = new ClientThread();
            ClientThread c7 = new ClientThread();
            ClientThread c8 = new ClientThread();
            ClientThread c9 = new ClientThread();
            ClientThread cx = new ClientThread();
            c1.start();
            c2.start();
            c3.start();
            c4.start();
            c5.start();
            c6.start();
            c7.start();
            c8.start();
            c9.start();
            cx.start();

        }
        catch (Exception e) {
            //System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }

    }

    static class ClientThread extends Thread  {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.us-west-2.amazonaws.com", "us-west-2"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        int year = 2015;
        String title = "The Big New Movie";

        Map<String, Object> infoMap = new HashMap<String, Object>();

        @Override
        public void run(){
            System.out.println("aaaaa");
            infoMap.put("plot", "Nothing happens at all.");
            infoMap.put("rating", 0);
            System.out.println("Adding a new item...");
            for(int i=0; i<1000; i++) {
                PutItemOutcome outcome = table
                        .putItem(new Item().withPrimaryKey("year", Thread.currentThread().getId() * 10000 + i, "title", title).withMap("info", infoMap));

                System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
            }
            System.out.println(new Date());



        }
    }
}
