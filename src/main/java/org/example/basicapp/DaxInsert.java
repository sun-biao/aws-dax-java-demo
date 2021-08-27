package org.example.basicapp;


import com.amazon.dax.client.dynamodbv2.AmazonDaxClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DaxInsert {

    public static void main(String[] args) throws Exception {
        AmazonDaxClientBuilder daxClientBuilder = AmazonDaxClientBuilder.standard()
                .withRegion("us-west-2")
                .withEndpointConfiguration("daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com:8111");
        AmazonDynamoDB client = daxClientBuilder.build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("kline");

        int year = 2015;
        String title = "The Big New Movie";

        Map<String, Object> infoMap = new HashMap<String, Object>();

         try {
            System.out.println(new Date());
            PutItemOutcome outcome = table
                        .putItem(new Item().withPrimaryKey("key", "k1", "ts", 222).withString("testfield", "aaa"));
            System.out.println("------------------");
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        }
        catch (Exception e) {
            //System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
     
            
            try {
                

            }
            catch (Exception e) {
                System.err.println("Unable to query movies from 1985");
                System.err.println(e.getMessage());
            }
            System.out.println(new Date());
            System.out.println("**************************");



    }

    static class ClientThread extends Thread  {

        AmazonDaxClientBuilder daxClientBuilder = AmazonDaxClientBuilder.standard()
                .withRegion("us-west-2")
                .withEndpointConfiguration("daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com:8111");
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dax://daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com","us-west-2"));
        AmazonDynamoDB client = daxClientBuilder.build();
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
            HashMap<String, String> nameMap = new HashMap<String, String>();
            nameMap.put("#yr", "year");
            HashMap<String, Object> valueMap = new HashMap<String, Object>();
            valueMap.put(":yyyy", 11009808);
            QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
                    .withValueMap(valueMap);
//            for(int i=0; i<2; i++) {
//                PutItemOutcome outcome = table
//                        .putItem(new Item().withPrimaryKey("year", Thread.currentThread().getId() * 10000 + i, "title", title).withMap("info", infoMap));
//
//                System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
//            }


            ItemCollection<QueryOutcome> items = null;
            Iterator<Item> iterator = null;
            Item item = null;

            try {
                items = table.query(querySpec);

                iterator = items.iterator();
                while (iterator.hasNext()) {
                    item = iterator.next();
                    System.out.println(item.getNumber("year") + ": " + item.getString("title"));
                }

            }
            catch (Exception e) {
                System.err.println("Unable to query movies from 1985");
                System.err.println(e.getMessage());
            }
            System.out.println(new Date());
            System.out.println("**************************");


        }
    }
}
