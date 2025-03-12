package pl.edu.uksw.java.lab2;

import java.util.ArrayList;
import java.util.List;

public class PizzaApp {

    public static void main(String[] args) {
        List<PizzaRecipe> pizzaRecipes = List.of(
                new PizzaRecipe("Margherita", List.of(Ingredient.Cheese)),
                new PizzaRecipe("Peperoni", List.of(Ingredient.Cheese, Ingredient.Peperoni)));

        PizzaShop shop = new PizzaShop(pizzaRecipes,2, 2, 1);

        List<Pizza> orderedPizzas = new ArrayList<>();
        for (var recipe : shop.getMenu()) {
            orderedPizzas.add(shop.placeOrder(recipe, PizzaSize.L));
        }

        do {
            shop.update();

            boolean allPizzasBoxed = orderedPizzas.stream().anyMatch(
                    (Pizza p) -> p.state == PizzaState.Boxed
            );

            System.out.println(orderedPizzas);

            if (allPizzasBoxed) {
                break;
            }
        } while (true);

    }
}
