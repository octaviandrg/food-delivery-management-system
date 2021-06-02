package Business_Layer;

import Data_Layer.Account;
import Presentation_Layer.AdministratorGUI;
import Presentation_Layer.ClientGUI;
import Presentation_Layer.EmployeeGUI;
import Presentation_Layer.LoginGUI;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**@invatiant isWellFormed() */

/** Clasa principala care leaga restul claselor intre ele*/
public class DeliveryService extends Observable implements IDeliveryServiceProcessing,Serializable{
    //private static final long serialVersionUID = -5947938895461248815L;

    /** Tine info despre comenzi, asociez comanda cu prod. din comanda */
    public HashMap<Order, ArrayList<MenuItem>> orderInformations;
    /** Tine info despre produsele aflate in meniu */
    public HashMap<Integer, MenuItem> products, productsFiltred;
    /** Tine info despre  produsele care urmeaza sa formeze un prod. compus / Tine info despre produsele afltate in comanda curenta */
    public ArrayList<MenuItem> productsForComposite, currentOrderProducts;
    /** Tine info despre conturile clientilor*/
    public HashMap<Integer, Account> clients;
    /** Tine info despre comanda curenta*/
    public Order currentOrder;
    /** Numarul total de comenzi */
    public Integer numberOfOrders;
    /** Id-ul clientului care urmeaza sa faca comanda*/
    public Integer cl;




    /** Constructorul clasei */
    public DeliveryService(){
        orderInformations = new HashMap<>();
        products = new HashMap<>();
        productsFiltred = new HashMap<>();
        numberOfOrders = 0;
        productsForComposite = new ArrayList<>();
        currentOrderProducts = new ArrayList<>();
        clients = new HashMap<>();
        cl = 0;

    }

    public boolean isWellFormed(){
        if(orderInformations == null || products == null)
            return false;
        return true;
    }


    /** Verifica daca un produs se afla in meniu*/
    public boolean isInMenu(MenuItem product){
        return products.containsKey(product.hashCode());
    }

    /** Metoda cu ajutorul careia se plaseaza o comanda */
    public void placeOrder(){
        try{
            currentOrder = new Order(numberOfOrders++, cl++, new Date(System.currentTimeMillis()), orderPrice(currentOrderProducts));
            orderInformations.put(currentOrder, currentOrderProducts);
            Data_Layer.FileWriter.generateBill(currentOrder, orderInformations);
            setChanged();
            notifyObservers(currentOrder);
            currentOrderProducts = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Metoda care adauga produse la comanda curenta*/
    public void addToOrder(MenuItem product){
        currentOrderProducts.add(product);
    }

    /** Metoda care actualizeaza hashmap-ul de produse*/
    private void updateHashTables(MenuItem m){
        products.put(m.hashCode(), m);
    }


    @Override
    public void addProduct(MenuItem product){
        assert product != null;
        assert  isWellFormed();
        if(!isInMenu(product)) {
            updateHashTables(product);
        }
        else{
            System.out.println(product.toString() + " se afla deja in menu!\n");
        }
        assert isInMenu(product);
    }

    @Override
    public void editProduct(MenuItem product, MenuItem newProduct){
        assert product != null; assert newProduct != null;
        assert  isWellFormed();
        if(isInMenu(product)){
            products.remove(product.hashCode());
            addProduct(newProduct);
        }

    }

    @Override
    public void deleteProduct(MenuItem product){
        assert product != null;
        if(isInMenu(product)){
            products.remove(product.hashCode());
        }
        else{
            System.out.println(product.toString() + " nu se poate sterge!\n");
        }
        assert !isInMenu(product);
    }

    @Override
    public int orderPrice(ArrayList<MenuItem> currentOrder){
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
            }
        }
    }

    @Override
    public Map<Integer, MenuItem> findProduct(String crt, String filter){
        switch (crt) {
            case "Title":
                return products.values().stream().filter(p -> p.computeTitle().contains(filter)).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "Rating":
                return products.values().stream().filter(p -> p.computeRating().equals(Double.parseDouble(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "Calories":
                return products.values().stream().filter(p -> p.computeNumberOfCalories().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "Proteins":
                return products.values().stream().filter(p -> p.computeNumberOfProteins().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "Fats":
                return products.values().stream().filter(p -> p.computeNumberOfFats().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            case "Sodium":
                return products.values().stream().filter(p -> p.computeNumberOfSodium().equals(Integer.parseInt(filter))).collect(Collectors.toMap(MenuItem::hashCode, p -> p));
            default:
                System.out.println("Criterul de cautare este invalid");
                return null;
        }
    }



}
