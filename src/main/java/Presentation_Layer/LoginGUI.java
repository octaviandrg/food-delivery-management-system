package Presentation_Layer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    public JFrame frameAdmin;
    public JFrame frameClient;
    public JFrame frameEmployee;
    public LoginGUI(){
        frameAdmin = new JFrame("Administrator");
        frameClient = new JFrame("Client");
        frameEmployee = new JFrame("Employee");

        //frameAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAdmin.setBounds(100, 100, 440, 140);
        JPanel administratorLoginPanel = new JPanel();
        administratorLoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(administratorLoginPanel);
        administratorLoginPanel.setLayout(null);

        JLabel lblUser = new JLabel("User:");
        lblUser.setBounds(103, 14, 70, 15);
        administratorLoginPanel.add(lblUser);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(103, 40, 83, 15);
        administratorLoginPanel.add(lblPassword);

        final JTextField textFieldUserAdmin = new JTextField();
        textFieldUserAdmin.setBounds(201, 12, 114, 19);
        administratorLoginPanel.add(textFieldUserAdmin);
        textFieldUserAdmin.setColumns(10);

        final JTextField textFieldPassAdmin = new JTextField();
        textFieldPassAdmin.setBounds(201, 38, 114, 19);
        administratorLoginPanel.add(textFieldPassAdmin);
        textFieldPassAdmin.setColumns(10);

        JButton btnLoginAdmin = new JButton("LOGIN");
        btnLoginAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textFieldUserAdmin.getText().equals("admin") && textFieldPassAdmin.getText().equals("admin")){
                    JOptionPane.showMessageDialog(frameAdmin,"Login success!");
                    frameAdmin.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(frameAdmin,"Wrong user / pass! \nTry again");
                }
            }
        });
        btnLoginAdmin.setBounds(159, 67, 117, 25);
        administratorLoginPanel.add(btnLoginAdmin);

        frameAdmin.add(administratorLoginPanel);

        //frameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameClient.setBounds(100, 100, 440, 140);
        JPanel clientLoginPanel = new JPanel();
        clientLoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(clientLoginPanel);
        clientLoginPanel.setLayout(null);

        JLabel lblUser2 = new JLabel("User:");
        lblUser2.setBounds(103, 14, 70, 15);
        clientLoginPanel.add(lblUser2);

        JLabel lblPassword2 = new JLabel("Password:");
        lblPassword2.setBounds(103, 40, 83, 15);
        clientLoginPanel.add(lblPassword2);

        final JTextField textFieldUserClient = new JTextField();
        textFieldUserClient.setBounds(201, 12, 114, 19);
        clientLoginPanel.add(textFieldUserClient);
        textFieldUserClient.setColumns(10);

        final JTextField textFieldPassClient = new JTextField();
        textFieldPassClient.setBounds(201, 38, 114, 19);
        clientLoginPanel.add(textFieldPassClient);
        textFieldPassClient.setColumns(10);

        JButton btnLoginClient = new JButton("LOGIN");
        btnLoginClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textFieldUserClient.getText().equals("client") && textFieldPassClient.getText().equals("client")){
                    JOptionPane.showMessageDialog(frameClient,"Login success!");
                    frameClient.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(frameClient,"Wrong user / pass! \nTry again");
                }
            }
        });
        btnLoginClient.setBounds(159, 67, 117, 25);
        clientLoginPanel.add(btnLoginClient);

        frameClient.add(clientLoginPanel);

        frameEmployee.setBounds(100, 100, 440, 140);
        JPanel employeeLoginPanel = new JPanel();
        employeeLoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(employeeLoginPanel);
        employeeLoginPanel.setLayout(null);

        JLabel lblUser3 = new JLabel("User:");
        lblUser3.setBounds(103, 14, 70, 15);
        employeeLoginPanel.add(lblUser3);

        JLabel lblPassword3 = new JLabel("Password:");
        lblPassword3.setBounds(103, 40, 83, 15);
        employeeLoginPanel.add(lblPassword3);

        final JTextField textFieldUserEmployee = new JTextField();
        textFieldUserEmployee.setBounds(201, 12, 114, 19);
        employeeLoginPanel.add(textFieldUserEmployee);
        textFieldUserEmployee.setColumns(10);

        final JTextField textFieldPassEmployee = new JTextField();
        textFieldPassEmployee.setBounds(201, 38, 114, 19);
        employeeLoginPanel.add(textFieldPassEmployee);
        textFieldPassEmployee.setColumns(10);

        JButton btnLoginEmployee = new JButton("LOGIN");
        btnLoginEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textFieldUserEmployee.getText().equals("employee") && textFieldPassEmployee.getText().equals("employee")){
                    JOptionPane.showMessageDialog(frameClient,"Login success!");
                    frameEmployee.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(frameClient,"Wrong user / pass! \nTry again");
                }
            }
        });
        btnLoginEmployee.setBounds(159, 67, 117, 25);
        employeeLoginPanel.add(btnLoginEmployee);

        frameEmployee.add(employeeLoginPanel);

        frameAdmin.setVisible(true);
        frameClient.setVisible(true);
        frameEmployee.setVisible(true);

    }
}
