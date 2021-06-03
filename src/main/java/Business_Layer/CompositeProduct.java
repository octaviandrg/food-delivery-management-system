package Business_Layer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> menuItems;

    public CompositeProduct(String title, ArrayList<MenuItem> menuItems) {
        super(  title,
                menuItems.stream().map(MenuItem::computeRating).reduce(0.0, Double::sum),
                menuItems.stream().map(MenuItem::computeNumberOfCalories).reduce(0, Integer::sum),
                menuItems.stream().map(MenuItem::computeNumberOfProteins).reduce(0, Integer::sum),
                menuItems.stream().map(MenuItem::computeNumberOfFats).reduce(0, Integer::sum),
                menuItems.stream().map(MenuItem::computeNumberOfSodium).reduce(0, Integer::sum),
                menuItems.stream().map(MenuItem::computePrice).reduce(0, Integer::sum)
        );
        this.menuItems = new ArrayList<>();
        this.menuItems.addAll(menuItems);
    }

   /* @Override
    public Double computeRating() {
        Double rating = new Double(0);
        for(MenuItem mi : menuItems){
            rating += mi.computeRating();
        }
        return rating;
    }

    @Override
    public Integer computeNumberOfFats() {
        int fats = 0;
        for(MenuItem mi : menuItems){
            fats += mi.computeNumberOfFats();
        }
        return fats;
    }

    @Override
    public Integer computeNumberOfProteins() {
        int proteins = 0;
        for(MenuItem mi : menuItems){
            proteins += mi.computeNumberOfProteins();
        }
        return proteins;
    }

    @Override
    public Integer computeNumberOfSodium() {
        int sodium = 0;
        for(MenuItem mi : menuItems){
            sodium += mi.computeNumberOfSodium();
        }
        return sodium;
    }

    @Override
    public Integer computeNumberOfCalories() {
        int calories = 0;
        for(MenuItem mi : menuItems){
            calories += mi.computeNumberOfCalories();
        }
        return calories;
    }

    public Integer computePrice(){
        int price = 0;
        for(MenuItem mi : menuItems){
            price += mi.computePrice();
        }
        return price;
    }

    @Override
    public String computeTitle() {
        StringBuilder title = new StringBuilder();
        for(MenuItem mi : menuItems){
            title.append(mi.computeTitle()).append(" + ");
        }
        return title.toString();
    }*/




}
