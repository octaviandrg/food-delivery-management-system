package Business_Layer;

import Presentation_Layer.AdministratorGUI;
import Presentation_Layer.ClientGUI;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing,Serializable{

    public HashMap<Order, ArrayList<MenuItem>> orderInformations;
    public HashMap<Integer, MenuItem> products, productsFiltred;
    private HashMap<Integer, Integer> productsOrderCount;
    private ArrayList<MenuItem> productsForComposite, currentOrderProducts;
    private Order currentOrder;
    private Integer numberOfOrders;
    private Integer currentClient;


    public DeliveryService(){

        orderInformations = new HashMap<>();
        products = new HashMap<>();
        productsFiltred = new HashMap<>();
        currentClient = 0;
        numberOfOrders = 0;
        //productsOrderCount = new HashMap<>();

    }

    public boolean isInMenu(MenuItem product){
        return products.containsKey(product.hashCode());
    }

    private void placeOrder(){
        try{
            currentOrder = new Order(numberOfOrders++, currentClient++, new Date(System.currentTimeMillis()));
            orderInformations.put(currentOrder, currentOrderProducts);
            Data_Layer.FileWriter.generateBill(currentOrder, orderInformations);
            setChanged();
            notifyObservers(currentOrder);
            clearOrder();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToOrder(MenuItem product){
        currentOrderProducts.add(product);
        productsOrderCount.replace(product.hashCode(), productsOrderCount.get(product.hashCode()) + 1);
    }

    private void clearOrder(){
        currentOrderProducts = new ArrayList<>();
    }

    private void updateHashTables(MenuItem m){
        products.put(m.hashCode(), m);
    }

    private void addObs(JFrame frame){
        this.addObserver((Observer) frame);
    }

    @Override
    public void addProduct(MenuItem product){
        assert product != null;
        if(!isInMenu(product)) {
            updateHashTables(product);
        }
        else{
            System.out.println(product.toString() + " se afla deja in menu!\n");
        }
    }

    @Override
    public void editProduct(MenuItem product, MenuItem newProduct){
        assert product != null; assert newProduct != null;
        if(isInMenu(product)){
            products.put(newProduct.hashCode(), newProduct);
            deleteProduct(product);
        }
        assert product != newProduct;
    }

    @Override
    public void deleteProduct(MenuItem product){
        assert product != null;
        if(isInMenu(product)){
            products.remove(product.hashCode(), product);
        }
    }

    @Override
    public int computeOrderPrice(ArrayList<MenuItem> currentOrder){
        assert currentOrder != null;
        int result = currentOrder.stream().map(MenuItem::computePrice).reduce(0, Integer::sum);
        assert result >= 0;
        return result;
    }

    public static Function<String, MenuItem> mapToProducts = (line) -> {
        String[] p = line.split(",");
        return new BaseProduct(p[0], Double.parseDouble(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                Integer.parseInt(p[4]),Integer.parseInt(p[5]),Integer.parseInt(p[6]));
    };
    
    @Override
    public void importProducts(){
        int i = 0;
        List<MenuItem> products = new ArrayList<>();
        try {
            File inputF = new File("/home/octavian/Documents/TP/Tema 4/products.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            products = br.lines().skip(1).map(mapToProducts).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(MenuItem product : products) {
            if(!isInMenu(product)) {
                updateHashTables(product);
                System.out.println(product);
            }
        }

    }

    @Override
    public Map<Integer, MenuItem> findProduct(String crt, String filter){
        switch (crt) {
            case "title":
                return products.values().stream().filter(p -> p.computeTitle().contains(filter)).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "rating":
                return products.values().stream().filter(p -> p.computeRating().equals(Double.parseDouble(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "calories":
                return products.values().stream().filter(p -> p.computeNumberOfCalories().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "proteins":
                return products.values().stream().filter(p -> p.computeNumberOfProteins().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "fats":
                return products.values().stream().filter(p -> p.computeNumberOfFats().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "sodium":
                return products.values().stream().filter(p -> p.computeNumberOfSodium().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            default:
                System.out.println("Criterul de cautare este invalid");
                return null;
        }
    }



}
