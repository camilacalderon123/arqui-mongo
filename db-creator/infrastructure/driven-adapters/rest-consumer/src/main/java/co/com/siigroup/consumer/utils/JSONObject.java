package co.com.siigroup.consumer.utils;

import co.com.siigroup.consumer.ObjectResponse;
import co.com.siigroup.model.employee.Employee;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class JSONObject {
    public static Object jsonToObject(String json, Class cls) {
        try {
            Object temp = new Gson().fromJson(json, cls);
            return temp;
        } catch (Exception e) {
            System.out.println(e);
            return ObjectResponse.builder().data("Error en la conversion").build();
        }
    }

    public static Employee stringToEmployee(String str){
        if(str.equals("error al comsumir")){
            return Employee.builder().build();
        }
        String [] clean=str.replaceAll("[{}]", "")
                .split(",");
        HashMap<String, String> employee = new LinkedHashMap<>();
        for(String x: clean){
            System.out.println(x);
            String [] y = x.split("=");

          try{
              System.out.println(y[0] + "///"+ y[1]);
                y[1]= y[1].replaceAll("\\W", "");
                employee.put(y[0].replaceAll("\\s+|\\W",""), y[1]);
            }
            catch(Exception e)
            {employee.put(y[0], "");}
        }
        System.out.println(employee.get("employee_name"));
        System.out.println(employee.get("employee_age"));
        System.out.println(employee.get("employee_salary"));
        return Employee.builder()
                .employeeName(employee.get("employee_name"))
                .employeeAge(employee.get("employee_age")!=null?Integer.parseInt(employee.get("employee_age")):null)
                .employeeSalary(employee.get("employee_salary")!=null?Float.parseFloat(employee.get("employee_salary")):null)
                .id(employee.get("id"))
                .profileImage(employee.get("profile_image")).build();

    }

    public static List<Employee> covertToListEmployee(String str){
        List<Employee> employees = new ArrayList<>();
        String [] clean = str.replaceFirst("[|]", "")
                .split("},");

        for(String x : clean){
            employees.add(JSONObject.stringToEmployee(x.replaceAll("[{]", "")));
        }
        return employees;
    }
}
