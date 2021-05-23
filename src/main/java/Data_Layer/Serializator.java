package Data_Layer;

import Business_Layer.DeliveryService;

import java.io.*;

public class Serializator {

    public static void save(DeliveryService deliveryService) {
        try {
            FileOutputStream outputStream = new FileOutputStream("deliveryService.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(deliveryService);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DeliveryService load() {
        try {
            FileInputStream inputStream = new FileInputStream("deliveryService.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (DeliveryService) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
