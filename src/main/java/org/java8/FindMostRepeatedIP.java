package org.java8;
import java.util.*;

public class FindMostRepeatedIP {
    public String findMostRepeatedIP(List<String> logs) {
        Map<String, Integer> ipCount = new HashMap<>();
        for (String log : logs) {
            String ip = log.split(" ")[0];
            ipCount.put(ip, ipCount.getOrDefault(ip, 0) + 1);
        }
        return Collections.max(ipCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    public static void main(String[] args) {
        List<String> logs = Arrays.asList(
                "10.0.0.1 GET /home",
                "10.0.0.2 GET /login",
                "10.0.0.1 POST /pay",
                "10.0.0.3 GET /home",
                "10.0.0.1 GET /profile",
                "10.0.0.2 GET /home"
        );

        FindMostRepeatedIP obj = new FindMostRepeatedIP();
        System.out.println(obj.findMostRepeatedIP(logs));
    }
}
