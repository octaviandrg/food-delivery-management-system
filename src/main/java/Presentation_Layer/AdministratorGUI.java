package Presentation_Layer;

import Business_Layer.BaseProduct;
import Business_Layer.DeliveryService;
import Business_Layer.MenuItem;
import Data_Layer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdministratorGUI extends JFrame {
    public AdministratorGUI(){
       // new LoginGUI();
       // this.products = products;
        initializePanel();
    }

    public void LoginGUI(){

    }


    private JPanel contentPane;
    private JTable tableAdmin;
    private JTextField textFieldTitleAddP;
    private JTextField textFieldRatingAddP;
    private JTextField textFieldCaloriesAddP;
    private JTextField textFieldProteinsAddP;
    private JTextField textFieldFatsAddP;
    private JTextField textFieldSodiumAddP;
    private JTextField textFieldPriceAddP;
    private JTextField textFieldTitleEditP;
    private JTextField textFieldRatingEditP;
    private JTextField textFieldCaloriesEditP;
    private JTextField textFieldProteinsEditP;
    private JTextField textFieldFatsEditP;
    private JTextField textFieldSodiumEditP;
    private JTextField textFieldPriceEditP;
    private JTextField textFieldProductsOrderedMoreThan;
    private JTextField textFieldProductsOrderedIn;
    private JTextField textFieldCompositeP;
    private JScrollPane scrollPaneTabelAdmin;
    DeliveryService ds = new DeliveryService();
    //private HashMap<Integer, MenuItem> products;


    public void createTable(HashMap<Integer, MenuItem> products){
        String[] coloane = {"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(coloane, 0);
        tableModel.setRowCount(0);
        tableAdmin = new JTable((tableModel));
        tableAdmin.setBounds(21, 360, 637, 483);
        if(products!=null) {
            for (Integer x : products.keySet()) {
                MenuItem menuItem = products.get(x);
                Object[] arr = {menuItem.computeTitle(), menuItem.computeRating(), menuItem.computeNumberOfCalories(),
                menuItem.computeNumberOfProteins(), menuItem.computeNumberOfFats(), menuItem.computeNumberOfSodium(),
                menuItem.computePrice()};
                tableModel.addRow(arr);
            }
            scrollPaneTabelAdmin = new JScrollPane(tableAdmin);
            scrollPaneTabelAdmin.setBounds(21, 360, 637, 483);
            scrollPaneTabelAdmin.setViewportView(tableAdmin);
            contentPane.add(scrollPaneTabelAdmin);
        }

    }

    public DeliveryService getDs(){
        return ds;
    }

    public void initializePanel(){
        setTitle("Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 873);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

       // createTable(products);

        JButton btnImportProducts = new JButton("Import products");
        btnImportProducts.setBounds(46, 12, 157, 25);
        btnImportProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ds.importProducts();
                createTable(ds.products);
            }
        });
        contentPane.add(btnImportProducts);

        JButton btnAddProduct = new JButton("Add product");
        btnAddProduct.setBounds(344, 102, 148, 25);
        btnAddProduct.addActionListener(arg0 -> {
            BaseProduct product = new BaseProduct(textFieldTitleAddP.getText(), Double.parseDouble(textFieldRatingAddP.getText()),
                    Integer.parseInt(textFieldCaloriesAddP.getText()), Integer.parseInt(textFieldProteinsAddP.getText()),Integer.parseInt(textFieldFatsAddP.getText()),
                    Integer.parseInt(textFieldSodiumAddP.getText()),Integer.parseInt(textFieldPriceAddP.getText()));
            ds.addProduct(product);
            createTable(ds.products);
        });
        contentPane.add(btnAddProduct);


        JButton btnSaveAdmin = new JButton("Save info");
        btnSaveAdmin.setBounds(700, 102, 148, 25);
        btnSaveAdmin.addActionListener(arg0 -> {
            Serializator.save(ds);
        });
        contentPane.add(btnSaveAdmin);


        JButton btnLoadAdmin = new JButton("Load info");
        btnLoadAdmin.setBounds(700, 190, 148, 25);
        btnLoadAdmin.addActionListener(arg0 -> {
            ds = Serializator.load();
            assert ds != null;
            createTable(ds.products);
        });
        contentPane.add(btnLoadAdmin);

        JButton btnEditProduct = new JButton("Edit product");
        btnEditProduct.setBounds(344, 197, 148, 25);
        btnEditProduct.addActionListener(arg0 -> {
            BaseProduct newProduct = new BaseProduct(textFieldTitleEditP.getText(), Double.parseDouble(textFieldRatingEditP.getText()),
                    Integer.parseInt(textFieldCaloriesEditP.getText()), Integer.parseInt(textFieldProteinsEditP.getText()),Integer.parseInt(textFieldFatsEditP.getText()),
                    Integer.parseInt(textFieldSodiumEditP.getText()),Integer.parseInt(textFieldPriceEditP.getText()));
            //createTable(ds.products);
            DefaultTableModel model = (DefaultTableModel)tableAdmin.getModel();
            int selectedRowIndex = tableAdmin.getSelectedRow();
            String title = model.getValueAt(selectedRowIndex, 0).toString();
            Double rating = Double.parseDouble(model.getValueAt(selectedRowIndex, 1).toString());
            Integer calories = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());
            Integer proteins = Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString());
            Integer fats = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
            Integer sodium = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
            Integer price = Integer.parseInt(model.getValueAt(selectedRowIndex, 6).toString());
            BaseProduct oldProduct = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
            ds.editProduct(oldProduct,newProduct);
            createTable(ds.products);
        });
        contentPane.add(btnEditProduct);

        JButton btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setBounds(46, 264, 157, 25);
        btnDeleteProduct.addActionListener(arg0 -> {
            DefaultTableModel model = (DefaultTableModel)tableAdmin.getModel();
            int selectedRowIndex = tableAdmin.getSelectedRow();
            String title = model.getValueAt(selectedRowIndex, 0).toString();
            Double rating = Double.parseDouble(model.getValueAt(selectedRowIndex, 1).toString());
            Integer calories = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());
            Integer proteins = Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString());
            Integer fats = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
            Integer sodium = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
            Integer price = Integer.parseInt(model.getValueAt(selectedRowIndex, 6).toString());
            BaseProduct toDeleteProduct = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
            ds.deleteProduct(toDeleteProduct);
            createTable(ds.products);
        });
        contentPane.add(btnDeleteProduct);

        JButton btnGenerateReports = new JButton("Generate reports");
        btnGenerateReports.setBounds(762, 711, 168, 25);
        contentPane.add(btnGenerateReports);

        String[] genR = {"First", "Second", "Third"};
        JComboBox comboBoxGenerateReports = new JComboBox(genR);
        comboBoxGenerateReports.setBounds(762, 662, 159, 24);
        contentPane.add(comboBoxGenerateReports);

        JButton btnCreateProduct = new JButton("Create product");
        btnCreateProduct.setBounds(220, 309, 157, 25);
        contentPane.add(btnCreateProduct);

        JButton btnLogoutAdmin = new JButton("Logout");
        btnLogoutAdmin.setBounds(670, 766, 128, 25);
        contentPane.add(btnLogoutAdmin);

        textFieldTitleAddP = new JTextField();
        textFieldTitleAddP.setBounds(56, 71, 114, 19);
        contentPane.add(textFieldTitleAddP);
        textFieldTitleAddP.setColumns(10);

        textFieldRatingAddP = new JTextField();
        textFieldRatingAddP.setColumns(10);
        textFieldRatingAddP.setBounds(199, 71, 60, 19);
        contentPane.add(textFieldRatingAddP);

        textFieldCaloriesAddP = new JTextField();
        textFieldCaloriesAddP.setColumns(10);
        textFieldCaloriesAddP.setBounds(289, 71, 60, 19);
        contentPane.add(textFieldCaloriesAddP);

        textFieldProteinsAddP = new JTextField();
        textFieldProteinsAddP.setColumns(10);
        textFieldProteinsAddP.setBounds(375, 71, 60, 19);
        contentPane.add(textFieldProteinsAddP);

        textFieldFatsAddP = new JTextField();
        textFieldFatsAddP.setColumns(10);
        textFieldFatsAddP.setBounds(470, 71, 60, 19);
        contentPane.add(textFieldFatsAddP);

        textFieldSodiumAddP = new JTextField();
        textFieldSodiumAddP.setColumns(10);
        textFieldSodiumAddP.setBounds(569, 71, 60, 19);
        contentPane.add(textFieldSodiumAddP);

        textFieldPriceAddP = new JTextField();
        textFieldPriceAddP.setColumns(10);
        textFieldPriceAddP.setBounds(661, 71, 60, 19);
        contentPane.add(textFieldPriceAddP);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(91, 56, 37, 15);
        contentPane.add(lblTitle);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setBounds(199, 56, 70, 15);
        contentPane.add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setBounds(289, 56, 70, 15);
        contentPane.add(lblCalories);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setBounds(375, 56, 70, 15);
        contentPane.add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setBounds(470, 56, 70, 15);
        contentPane.add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setBounds(574, 56, 70, 15);
        contentPane.add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(661, 56, 70, 15);
        contentPane.add(lblPrice);

        textFieldTitleEditP = new JTextField();
        textFieldTitleEditP.setColumns(10);
        textFieldTitleEditP.setBounds(56, 166, 114, 19);
        contentPane.add(textFieldTitleEditP);

        textFieldRatingEditP = new JTextField();
        textFieldRatingEditP.setColumns(10);
        textFieldRatingEditP.setBounds(199, 166, 60, 19);
        contentPane.add(textFieldRatingEditP);

        textFieldCaloriesEditP = new JTextField();
        textFieldCaloriesEditP.setColumns(10);
        textFieldCaloriesEditP.setBounds(289, 166, 60, 19);
        contentPane.add(textFieldCaloriesEditP);

        textFieldProteinsEditP = new JTextField();
        textFieldProteinsEditP.setColumns(10);
        textFieldProteinsEditP.setBounds(375, 166, 60, 19);
        contentPane.add(textFieldProteinsEditP);

        textFieldFatsEditP = new JTextField();
        textFieldFatsEditP.setColumns(10);
        textFieldFatsEditP.setBounds(470, 166, 60, 19);
        contentPane.add(textFieldFatsEditP);

        textFieldSodiumEditP = new JTextField();
        textFieldSodiumEditP.setColumns(10);
        textFieldSodiumEditP.setBounds(569, 166, 60, 19);
        contentPane.add(textFieldSodiumEditP);

        textFieldPriceEditP = new JTextField();
        textFieldPriceEditP.setColumns(10);
        textFieldPriceEditP.setBounds(661, 166, 60, 19);
        contentPane.add(textFieldPriceEditP);

        JLabel lblTitle_1 = new JLabel("Title");
        lblTitle_1.setBounds(91, 151, 37, 15);
        contentPane.add(lblTitle_1);

        JLabel lblRating_1 = new JLabel("Rating");
        lblRating_1.setBounds(199, 151, 70, 15);
        contentPane.add(lblRating_1);

        JLabel lblCalories_1 = new JLabel("Calories");
        lblCalories_1.setBounds(289, 151, 70, 15);
        contentPane.add(lblCalories_1);

        JLabel lblProteins_1 = new JLabel("Proteins");
        lblProteins_1.setBounds(375, 151, 70, 15);
        contentPane.add(lblProteins_1);

        JLabel lblFats_1 = new JLabel("Fats");
        lblFats_1.setBounds(470, 151, 70, 15);
        contentPane.add(lblFats_1);

        JLabel lblSodium_1 = new JLabel("Sodium");
        lblSodium_1.setBounds(574, 151, 70, 15);
        contentPane.add(lblSodium_1);

        JLabel lblPrice_1 = new JLabel("Price");
        lblPrice_1.setBounds(661, 151, 70, 15);
        contentPane.add(lblPrice_1);

        JLabel lblTimeInterval = new JLabel("Time interval:");
        lblTimeInterval.setBounds(800, 360, 114, 15);
        contentPane.add(lblTimeInterval);

        String[] minH = {"00", "01","02", "03","04", "05","06", "07", "08", "09", "10", "11","12", "13","14", "15"};
        JComboBox comboBoxMinTime = new JComboBox(minH);
        comboBoxMinTime.setBounds(783, 387, 50, 40);
        contentPane.add(comboBoxMinTime);

        String[] maxH = {"00", "01","02", "03","04", "05","06", "07", "08", "09", "10", "11","12", "13","14", "15"};
        JComboBox comboBoxMaxTime = new JComboBox(maxH);
        comboBoxMaxTime.setBounds(858, 387, 50, 40);
        contentPane.add(comboBoxMaxTime);

        JLabel label = new JLabel("-");
        label.setBounds(833, 387, 21, 15);
        contentPane.add(label);

        JLabel lblProductsOrderedMore = new JLabel("Products ordered more than:");
        lblProductsOrderedMore.setBounds(744, 454, 206, 15);
        contentPane.add(lblProductsOrderedMore);

        textFieldProductsOrderedMoreThan = new JTextField();
        textFieldProductsOrderedMoreThan.setBounds(833, 481, 37, 19);
        contentPane.add(textFieldProductsOrderedMoreThan);
        textFieldProductsOrderedMoreThan.setColumns(10);

        JLabel lblProductsOrderedIn = new JLabel("Products ordered in(DD/MM/YYYY):");
        lblProductsOrderedIn.setBounds(731, 527, 245, 15);
        contentPane.add(lblProductsOrderedIn);

        textFieldProductsOrderedIn = new JTextField();
        textFieldProductsOrderedIn.setColumns(10);
        textFieldProductsOrderedIn.setBounds(811, 554, 103, 19);
        contentPane.add(textFieldProductsOrderedIn);

        textFieldCompositeP = new JTextField();
        textFieldCompositeP.setBounds(71, 312, 114, 19);
        contentPane.add(textFieldCompositeP);
        textFieldCompositeP.setColumns(10);

        JLabel lblNewTitle = new JLabel("New Title");
        lblNewTitle.setBounds(100, 295, 70, 15);
        contentPane.add(lblNewTitle);
    }
}
