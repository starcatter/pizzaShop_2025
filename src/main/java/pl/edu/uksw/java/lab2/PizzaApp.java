package pl.edu.uksw.java.lab2;

import java.util.ArrayList;
import java.util.List;

public class PizzaApp {

    public static final List<PizzaRecipe> pizzaRecipes = List.of(
            new PizzaRecipe("Margherita", List.of(Ingredient.Cheese)),
            new PizzaRecipe("Peperoni", List.of(Ingredient.Cheese, Ingredient.Peperoni)),
            new PizzaRecipe("Double Peperoni", List.of(Ingredient.Cheese, Ingredient.Peperoni, Ingredient.Peperoni)),
            new PizzaRecipe("Capricciosa", List.of(Ingredient.Cheese, Ingredient.Ham,  Ingredient.Mushrooms))
    );

    public static void main(String[] args) {
        PizzaShop shop = new PizzaShop(pizzaRecipes,2, 2, 1);

        List<Pizza> orderedPizzas = new ArrayList<>();
        for (var recipe : shop.getMenu()) {
            orderedPizzas.add(shop.placeOrder(recipe, PizzaSize.L));
        }


        boolean allPizzasBoxed = false;
        while (!allPizzasBoxed) {
            shop.update();

            allPizzasBoxed = orderedPizzas.stream().anyMatch(
                    (Pizza p) -> p.state == PizzaState.Boxed
            );

            System.out.println(orderedPizzas);
        }

        System.out.println("All orders complete.");
    }

}
