package Business_Layer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    void importProducts();
    void addProduct(MenuItem product);
    void deleteProduct(MenuItem product);
    void editProduct(MenuItem product, MenuItem newProduct);
    int computeOrderPrice(ArrayList<MenuItem> currentOrder);
    Map<Integer, MenuItem> findProduct(String crt, String filter);
}
