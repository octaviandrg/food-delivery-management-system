package Business_Layer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    /**
     * Importa produsele din fisierul products.csv
     */
    void importProducts();

    /**
     * Adauga un produs in meniu
     * @param product
     * @pre product != null
     */
    void addProduct(MenuItem product);

    /**
     * Sterge un produs din meniu
     * @param product
     *  @pre product != null
     * @post isInMenu(product) = true
     * @invariant isWellFormed()
     */
    void deleteProduct(MenuItem product);

    /**
     * Editeaza un produs
     * @param product
     * @param newProduct
     * @pre product != null , newProduct != null
     * @post isInMenu(product) = false
     * @invariant isWellFormed()
     */
    void editProduct(MenuItem product, MenuItem newProduct);

    /**
     * Calculeaza pretul comenzii
     * @param currentOrder
     * @return result
     * @post result >= 0
     */
    int orderPrice(ArrayList<MenuItem> currentOrder);

    /**
     * Cauta un produs in lista dupa criteriu si filtru
     * @param crt criteriul de cautare : titlu / rating ...
     * @param filter filtrul de cautare
     * @return produsele gasite
     */
    Map<Integer, MenuItem> findProduct(String crt, String filter);
}
