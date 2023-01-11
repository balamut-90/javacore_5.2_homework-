package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.models.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main( String[] args ) throws IOException, ParseException {
        String fileName = "data.json";
        readString(fileName);
    }

    private static void readString(String fileName) throws IOException, ParseException {
        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        List<Employee> employeeList = jsonToList(stringBuilder.toString());
        employeeList.forEach(employee -> System.out.println(employee.toString()));
    }

    private static List<Employee> jsonToList(String toString) throws ParseException {
        List<Employee> employeeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(toString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        for (int i = 0; i < jsonArray.toArray().length; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);
            employeeList.add(employee);
        }

        return employeeList;
    }
}
