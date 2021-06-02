package Data_Layer;

import Business_Layer.DeliveryService;

import java.io.*;

public class Serializator {
    private static final long serialVersionUID = 1493350527497397524L;
    /*public static void save(DeliveryService deliveryService) {
        try {
            FileOutputStream outputStream = new FileOutputStream("deliveryService2.ser");
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
            FileInputStream inputStream = new FileInputStream("deliveryService2.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (DeliveryService) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public static void save(String file, Object o) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object load(String file) {
        Object o;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            o = objectInputStream.readObject();
            objectInputStream.close();
            return  o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
