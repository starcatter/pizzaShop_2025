package pl.edu.uksw.java.lab2;

import java.util.ArrayList;
import java.util.List;

class Pizza {
    PizzaSize size;
    PizzaRecipe recipe;
    List<Ingredient> ingredientsOnPizza = new ArrayList<>();
    PizzaState state;

    public Pizza(PizzaSize size, PizzaRecipe recipe) {
        this.size = size;
        this.recipe = recipe;
        this.state = PizzaState.Ordered;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size=" + size +
                ", recipe=" + recipe +
                ", ingredientsOnPizza=" + ingredientsOnPizza +
                ", state=" + state +
                '}';
    }
}
