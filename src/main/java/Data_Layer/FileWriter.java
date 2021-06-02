package Data_Layer;

import Business_Layer.MenuItem;
import Business_Layer.Order;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FileWriter {


    public static void generateBill(Order order, HashMap<Order, ArrayList<MenuItem>> orderInfo) {
        File billFile = new File("./bills/billOrder" + order.getOrderID() + ".txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(billFile);
            fileWriter.write(orderInfo.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateReportFile1(String ob){
        File reportFile = new File("./reports/reportFile1.txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(reportFile);
            fileWriter.write(ob);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void generateReportFile2(String ob){
        File reportFile = new File("./reports/reportFile2.txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(reportFile);
            fileWriter.write(ob);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateReportFile3(String ob){
        File reportFile = new File("./reports/reportFile4.txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(reportFile);
            fileWriter.write(ob);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateReportFile4(String ob){
        File reportFile = new File("./reports/reportFile3.txt");
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(reportFile);
            fileWriter.write(ob);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
