package Presentation_Layer;

import Business_Layer.BaseProduct;
import Business_Layer.DeliveryService;
import Business_Layer.MenuItem;
import Data_Layer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientGUI extends JFrame  {
    DeliveryService ds;
    public ClientGUI(DeliveryService ds){
        this.ds = ds;
        initializePanel();
    }

    private JPanel contentPane;
    private JTable tableClient;
    private JTable updatedTableClient;
    private JScrollPane scrollPaneTableClient;
    private JTextField textFieldSearch;
    private DefaultTableModel tableModel;

    public void createTable(HashMap<Integer, MenuItem> products){
        tableModel.setRowCount(0);
        if(products!=null) {
            for (Integer x : products.keySet()) {
                MenuItem menuItem = products.get(x);
                Object[] arr = {menuItem.computeTitle(), menuItem.computeRating(), menuItem.computeNumberOfCalories(),
                        menuItem.computeNumberOfProteins(), menuItem.computeNumberOfFats(), menuItem.computeNumberOfSodium(),
                        menuItem.computePrice()};
                tableModel.addRow(arr);
            }
        }
    }



    public void initializePanel() {
        setTitle("Client");
        setBounds(100, 100, 1000, 873);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearch = new JLabel("Search for:");
        lblSearch.setBounds(30, 53, 85, 15);
        contentPane.add(lblSearch);

        textFieldSearch = new JTextField();
        textFieldSearch.setBounds(121, 51, 114, 19);
        contentPane.add(textFieldSearch);
        textFieldSearch.setColumns(10);

        String[] coloane = {"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};
        tableModel = new DefaultTableModel(coloane, 0);
        tableModel.setRowCount(0);
        tableClient = new JTable((tableModel));
        tableClient.setBounds(12, 289, 988, 542);

        scrollPaneTableClient = new JScrollPane(tableClient);
        scrollPaneTableClient.setBounds(12, 289, 988, 542);
        scrollPaneTableClient.setViewportView(tableClient);
        contentPane.add(scrollPaneTableClient);

        JLabel lblBy = new JLabel("by");
        lblBy.setBounds(253, 53, 30, 15);
        contentPane.add(lblBy);

        String[] filters = {"Title","Rating","Calories","Proteins","Fats","Sodium","Price"};
        JComboBox comboBox = new JComboBox(filters);
        comboBox.setBounds(301, 48, 97, 24);
        contentPane.add(comboBox);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setBounds(478, 48, 117, 25);
        btnFilter.addActionListener(arg0 -> {
            String selected = (String) comboBox.getSelectedItem();
            assert selected != null;
            ds.productsFiltred = new HashMap<>();
            ds.productsFiltred = (HashMap<Integer, MenuItem>) ds.findProduct(selected, textFieldSearch.getText());
            createTable(ds.productsFiltred);
        });
        contentPane.add(btnFilter);

        JButton btnClearFilter = new JButton("Clear filter");
        btnClearFilter.setBounds(478, 114, 117, 25);
        btnClearFilter.addActionListener(arg0 -> {
            contentPane.remove(tableClient);
            createTable(ds.products);
        });
        contentPane.add(btnClearFilter);

        JButton btnPlaceOrder = new JButton("Place order");
        btnPlaceOrder.setBounds(301, 252, 117, 25);
        btnPlaceOrder.addActionListener(arg0 -> {
            ds.placeOrder();
            JOptionPane.showMessageDialog(this,"Order placed!");
        });
        contentPane.add(btnPlaceOrder);


        JButton btnAddToBag = new JButton("Add to bag");
        btnAddToBag.setBounds(480, 252, 117, 25);
        btnAddToBag.addActionListener(arg0 -> {
            DefaultTableModel model = (DefaultTableModel)tableClient.getModel();
            int selectedRowIndex = tableClient.getSelectedRow();
            String title = model.getValueAt(selectedRowIndex, 0).toString();
            Double rating = Double.parseDouble(model.getValueAt(selectedRowIndex, 1).toString());
            Integer calories = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());
            Integer proteins = Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString());
            Integer fats = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
            Integer sodium = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
            Integer price = Integer.parseInt(model.getValueAt(selectedRowIndex, 6).toString());
            MenuItem addToBagProduct = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
            ds.addToOrder(addToBagProduct);
        });
        contentPane.add(btnAddToBag);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(670, 252, 117, 25);
        btnLogout.addActionListener(arg0 ->{
            setVisible(false);
        });
        contentPane.add(btnLogout);

        JLabel lblSelectProducts = new JLabel("Select products");
        lblSelectProducts.setBounds(30, 262, 114, 15);
        contentPane.add(lblSelectProducts);


        JButton btnSaveClient = new JButton("Save info");
        btnSaveClient.setBounds(700, 102, 148, 25);
        btnSaveClient.addActionListener(arg0 -> {
            Serializator.save("DeliveryService.ser", ds);
        });
        contentPane.add(btnSaveClient);


        JButton btnLoadClient = new JButton("Load info");
        btnLoadClient.setBounds(700, 180, 148, 25);
        btnLoadClient.addActionListener(arg0 -> {
            ds = (DeliveryService) Serializator.load("DeliveryService.ser");
            assert ds != null;
            createTable(ds.products);
            for(ArrayList<MenuItem> m : ds.orderInformations.values()){
                System.out.println(m.toString());
            }
        });
        contentPane.add(btnLoadClient);

    }
}
