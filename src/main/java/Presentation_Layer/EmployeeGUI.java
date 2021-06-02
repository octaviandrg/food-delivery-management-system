package Presentation_Layer;


import Business_Layer.DeliveryService;
import Business_Layer.MenuItem;
import Business_Layer.Order;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI  extends JFrame implements Observer {
    private JPanel contentPane;
    private JTable tableEmployee;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPaneEmployee;
    //private DeliveryService ds;
    //private Order order;


    public EmployeeGUI(){
       // this.ds = ds;
        initializePanel();
    }

    @Override
    public void update(Observable o, Object order) {
        System.out.println("AICI");
        System.out.println(order.toString());
        JOptionPane.showMessageDialog(this, order.toString());
    }

    private void initializePanel(){
        //ds.addObs(this);
        setTitle("Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 767, 416);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

//        String[] coloane = {"Order ID","Client ID","Order Date"};
//        tableModel = new DefaultTableModel(coloane, 0);
//        tableModel.setRowCount(0);
//        tableEmployee = new JTable((tableModel));
//        tableEmployee.setBounds(12, 12, 564, 362);
//
//        scrollPaneEmployee = new JScrollPane(tableEmployee);
//        scrollPaneEmployee.setBounds(12, 12, 564, 362);
//        scrollPaneEmployee.setViewportView(tableEmployee);
//        contentPane.add(scrollPaneEmployee);


        JButton btnLogoutEmployee = new JButton("Logout");
        btnLogoutEmployee.setBounds(616, 320, 117, 25);
        btnLogoutEmployee.addActionListener(arg0 ->{
            setVisible(false);
        });
        contentPane.add(btnLogoutEmployee);



    }

//    public void createTable(HashMap<Order, ArrayList<MenuItem>> orders){
//        tableModel.setRowCount(0);
//        if(orders != null){
//            for(Order o : orders.keySet()){
//                Object[] x = {o.getOrderID(), o.getClientID(), o.getOrderDate()};
//                tableModel.addRow(x);
//            }
//        }
//    }

}
