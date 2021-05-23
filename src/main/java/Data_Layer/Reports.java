package Data_Layer;

import Business_Layer.MenuItem;
import Business_Layer.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Reports {
    public static Set<Order> generateReport1(int min, int max, HashMap<Order, ArrayList<MenuItem>> orderInfo){
        return orderInfo.keySet().stream().filter(p -> p.getOrderDate().getHours() >= min &&
                p.getOrderDate().getTime() <= max).collect(Collectors.toSet());
    }

    public static Set<MenuItem> generateReport2(int min, HashMap<Integer, MenuItem> products, HashMap<Integer, Integer> productsOrderCount){
        return products.values().stream().filter(p -> productsOrderCount.get(p.hashCode()) > min).collect(Collectors.toSet());
    }

    public static Set<Order> generateReport3(Date date, HashMap<Order, ArrayList<MenuItem>> orderInfo){
        return orderInfo.keySet().stream().filter(p -> p.getOrderDate().compareTo(date)==0).collect(Collectors.toSet());

    }


}
