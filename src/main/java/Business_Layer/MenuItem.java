package Business_Layer;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    private String title;
    private Double rating;
    private Integer calories;
    private Integer protein;
    private Integer fat;
    private Integer sodium;
    private Integer price;

    public MenuItem(String title, Double rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }



    public Integer computePrice(){
        return this.price;
    }

    public String computeTitle() {
        return title;
    }

    public Double computeRating() {
        return rating;
    }

    public Integer computeNumberOfCalories() {
        return calories;
    }

    public Integer computeNumberOfProteins() {
        return protein;
    }

    public Integer computeNumberOfFats() {
        return fat;
    }

    public Integer computeNumberOfSodium() {
        return sodium;
    }

    @Override
    public int hashCode(){
        return Objects.hash(title, price, rating, calories, protein, fat, sodium);
    }

    public String toString(){
        return computeTitle()+" "+computeRating()+" "+computeNumberOfCalories()+" "+computeNumberOfProteins()+
                " "+computeNumberOfFats()+" "+computeNumberOfSodium()+" "+computePrice();
    }

}
