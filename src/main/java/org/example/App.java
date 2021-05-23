package org.example;

import Business_Layer.DeliveryService;
import Presentation_Layer.AdministratorGUI;
import Presentation_Layer.ClientGUI;
import Presentation_Layer.EmployeeGUI;
import Presentation_Layer.LoginGUI;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        //new LoginGUI();
        AdministratorGUI frameAdmin = new AdministratorGUI();
        frameAdmin.setVisible(true);
        ClientGUI frameClient = new ClientGUI();
        frameClient.setVisible(true);
        //EmployeeGUI frameEmployee = new EmployeeGUI();
        //frameEmployee.setVisible(true);

    }
}
