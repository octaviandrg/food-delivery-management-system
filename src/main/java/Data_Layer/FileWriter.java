package Data_Layer;

import Business_Layer.MenuItem;
import Business_Layer.Order;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileWriter {


    public static void generateBill(Order order, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        File billFile = new File("./bills/billOrder" + order.getOrderID() + ".txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(billFile);
            fileWriter.write(orderInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
