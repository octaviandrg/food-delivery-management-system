package Data_Layer;

import Business_Layer.MenuItem;
import Business_Layer.Order;

import java.io.File;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class Reports {


    /**
     * Genereaza raportul pentru comenzile care au fost plasate intr un anumit interval orar
     * @param minHour
     * @param maxHour
     * @param orderInfo
     * @pre minHour < maxHour
     */
    public static void generateReport1(int minHour, int maxHour, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        assert minHour < maxHour;
        Integer filterResult = Math.toIntExact(orderInfo.entrySet().
                stream().
                filter(x ->  x.getKey().getOrderDate().getHours() >= minHour &&
                        x.getKey().getOrderDate().getHours() <= maxHour).
                map(x -> x.getValue()).collect(Collectors.toList()).stream().count());

        String report = "There have been placed " + filterResult + " orders!";
        FileWriter.generateReportFile1(report);
    }

    /**
     * Genereaza raportul pentru prod. care au fost comandate de mai mult de k ori
     * @param k
     * @param products
     * @param orderInfo
     * @pre k>=0
     */
    public static void generateReport2(int k, HashMap<Integer, MenuItem> products, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        assert k >= 0;
        String report = "";
        Map<Integer, Long> result = orderInfo.entrySet().stream().map(x -> x.getValue()).
                reduce(new ArrayList<>(), (x, y) -> { x.addAll(y); return x; }).
                stream().
                collect(Collectors.groupingBy(MenuItem::hashCode, Collectors.counting()));
        for(Integer itemId: result.keySet()) {
            if (result.get(itemId) >= k) {
                report += products.get(itemId).toString() + "\n";
            }
        }
        FileWriter.generateReportFile2("Products ordered more than " + k + " times\n" + report);
    }

    /**
     * Genereaza raportul pt clientii care au comandat de mai mult de k ori
     * @param k
     * @param orderInfo
     * @pre k >= 0
     */
    public static void generateReport3(int k, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        assert k >= 0;
        String report = "";
        Map<Integer, Long> result = orderInfo.entrySet().
                stream().map(x -> x.getKey()).
                collect(Collectors.groupingBy(Order::getClientID, Collectors.counting()));
        for(Integer clientId: result.keySet()) {
            if (result.get(clientId) >= k) {
                report += "Client n." + clientId + "\n";
            }
        }
        FileWriter.generateReportFile3("Clients who ordered more than " + k + " times\n" + report);
    }

    /**
     * Genereaza raportul pentru produsele comandate intr-o anumita zi
     * @param day
     * @param orderInfo
     * @pre day >=1 && day <= 7
     */
    public static void generateReport4(int day, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        assert day >= 1 && day <= 7;
        day = day % 7 + 1;
        int finalDay = day;
        List<MenuItem> result = orderInfo.entrySet().
                stream().
                filter(x -> x.getKey().getOrderDate().getDay() == finalDay).
                map(Map.Entry::getValue).
                reduce(new ArrayList<>(), (x, y) -> { x.addAll(y); return x; }).
                stream().
                distinct().
                collect(Collectors.toList());
        String report = "";
        for(MenuItem menuItem: result) {
            report += menuItem + "\n";
        }
        FileWriter.generateReportFile4("Products bought " + DayOfWeek.of(day) + " \n" + report);
    }


}
