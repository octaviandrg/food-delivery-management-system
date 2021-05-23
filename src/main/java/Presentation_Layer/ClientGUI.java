package Presentation_Layer;

import Business_Layer.DeliveryService;
import Business_Layer.MenuItem;
import Data_Layer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class ClientGUI extends JFrame {
    public ClientGUI(){
        initializePanel();
    }

    private JPanel contentPane;
    private JTable tableClient;
    private JTextField textFieldSearch;
    public DeliveryService ds;

    public void createTable(HashMap<Integer, MenuItem> products){
        String[] coloane = {"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(coloane, 0);
        tableModel.setRowCount(0);
        tableClient = new JTable((tableModel));
        tableClient.setBounds(12, 289, 988, 542);
        if(products!=null) {
            for (Integer x : products.keySet()) {
                MenuItem menuItem = products.get(x);
                Object[] arr = {menuItem.computeTitle(), menuItem.computeRating(), menuItem.computeNumberOfCalories(),
                        menuItem.computeNumberOfProteins(), menuItem.computeNumberOfFats(), menuItem.computeNumberOfSodium(),
                        menuItem.computePrice()};
                tableModel.addRow(arr);
            }
            JScrollPane scrollPaneTableClient = new JScrollPane(tableClient);
            scrollPaneTableClient.setBounds(12, 289, 988, 542);
            scrollPaneTableClient.setViewportView(tableClient);
            contentPane.add(scrollPaneTableClient);
        }

    }

    public void initializePanel() {
        setTitle("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 873);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        //createTable(AdministratorGUI);

        JLabel lblSearch = new JLabel("Search for:");
        lblSearch.setBounds(30, 53, 85, 15);
        contentPane.add(lblSearch);

        textFieldSearch = new JTextField();
        textFieldSearch.setBounds(121, 51, 114, 19);
        contentPane.add(textFieldSearch);
        textFieldSearch.setColumns(10);

        JLabel lblBy = new JLabel("by");
        lblBy.setBounds(253, 53, 30, 15);
        contentPane.add(lblBy);

        String[] filters = {"Title","Rating","Calories","Proteins","Fats","Sodium","Price"};
        JComboBox comboBox = new JComboBox(filters);
        comboBox.setBounds(301, 48, 97, 24);
        contentPane.add(comboBox);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setBounds(478, 48, 117, 25);
        contentPane.add(btnFilter);

        JButton btnClearFilter = new JButton("Clear filter");
        btnClearFilter.setBounds(478, 114, 117, 25);
        contentPane.add(btnClearFilter);

        JButton btnPlaceOrder = new JButton("Place order");
        btnPlaceOrder.setBounds(301, 252, 117, 25);
        contentPane.add(btnPlaceOrder);


        JLabel lblSelectProducts = new JLabel("Select products");
        lblSelectProducts.setBounds(30, 262, 114, 15);
        contentPane.add(lblSelectProducts);


        JButton btnSaveClient = new JButton("Save info");
        btnSaveClient.setBounds(700, 102, 148, 25);
        btnSaveClient.addActionListener(arg0 -> {

        });
        contentPane.add(btnSaveClient);


        JButton btnLoadClient = new JButton("Load info");
        btnLoadClient.setBounds(700, 180, 148, 25);
        btnLoadClient.addActionListener(arg0 -> {
             ds = Serializator.load();
             createTable(ds.products);
        });
        contentPane.add(btnLoadClient);

    }
}
