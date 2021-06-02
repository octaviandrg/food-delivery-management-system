package Presentation_Layer;

import Business_Layer.DeliveryService;
import Data_Layer.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class LoginGUI extends JFrame {
    public AdministratorGUI frameAdmin;
    public ClientGUI frameClient;
    public EmployeeGUI frameEmployee;
    private JPanel loginPanel;
    private JTextField textFieldUser;
    private JTextField textFieldPass;
    public Integer currentClientId;
    DeliveryService ds;
    public LoginGUI(){
        initializePanel();
        setVisible(true);
        ds = new DeliveryService();
        frameAdmin = new AdministratorGUI(ds);
        frameClient = new ClientGUI(ds);
        frameEmployee = new EmployeeGUI();
        ds.addObserver((frameEmployee));

    }

    public int getCurrentClientId(){
        return currentClientId;
    }


    public void initializePanel(){
        setTitle("FOOD DELIVERY MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 440, 140);
        loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(loginPanel);
        loginPanel.setLayout(null);

        JLabel lblUser = new JLabel("User:");
        lblUser.setBounds(103, 14, 70, 15);
        loginPanel.add(lblUser);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(103, 40, 83, 15);
        loginPanel.add(lblPassword);

        textFieldUser = new JTextField();
        textFieldUser.setBounds(201, 12, 114, 19);
        loginPanel.add(textFieldUser);
        textFieldUser.setColumns(10);

        textFieldPass = new JTextField();
        textFieldPass.setBounds(201, 38, 114, 19);
        loginPanel.add(textFieldPass);
        textFieldPass.setColumns(10);



        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(arg0 -> {
            String user = textFieldUser.getText();
            String pass = textFieldPass.getText();
            if(user.equals("admin") && pass.equals("admin")){
                frameAdmin.setVisible(true);
                JOptionPane.showMessageDialog(this, "Welcome, admin!");
            }else
            if(user.equals("employee") && pass.equals("employee")){
                frameAdmin.setVisible(false);
                frameEmployee.setVisible(true);
                JOptionPane.showMessageDialog(this, "Welcome, employee!");
            }else {
                boolean exista = false;
                for (Account account : ds.clients.values()){
                    if(account.getUser().equals(user) && account.getPass().equals(pass)){
                        JOptionPane.showMessageDialog(this, "Welcome, " + account.getUser() + "!");
                        frameAdmin.setVisible(false);
                        frameClient.setVisible(true);
                        frameEmployee.setVisible(true);

                        exista = true;
                        currentClientId = account.getId();
                    }
                } if(!exista){
                    JOptionPane.showMessageDialog(this, "Wrong user / pass!");
                }

            }
        });
        btnLogin.setBounds(82, 67, 117, 25);
        loginPanel.add(btnLogin);

        JButton btnRegister = new JButton("REGISTER");
        btnRegister.setBounds(243, 67, 117, 25);
        btnRegister.addActionListener(arg0 -> {
            String user = textFieldUser.getText();
            String pass = textFieldPass.getText();
            Account newClient = new Account(user,pass);
            ds.clients.put(newClient.hashCode(), newClient);
        });
        loginPanel.add(btnRegister);


    }
}
