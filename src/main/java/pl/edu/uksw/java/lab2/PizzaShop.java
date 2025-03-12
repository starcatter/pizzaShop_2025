package pl.edu.uksw.java.lab2;

import java.util.ArrayList;
import java.util.List;

class PizzaShop {
    List<Pizza> pizzas = new ArrayList<>();
    List<PizzaRecipe> menu;
    List<PizzaShopWorker> workers = new ArrayList<>();
    List<PizzaOven> ovens = new ArrayList<>();

    public PizzaShop(List<PizzaRecipe> menu, int cook_cnt, int helper_cnt, int oven_cnt) {
        this.menu = menu;

        for (int i = 0; i < cook_cnt; i++) {
            workers.add(new PizzaShopCook(this));
        }

        for (int i = 0; i < helper_cnt; i++) {
            workers.add(new PizzaShopHelper(this));
        }

        for (int i = 0; i < oven_cnt; i++) {
            ovens.add(new PizzaOven(5));
        }
    }

    public Pizza placeOrder(PizzaRecipe recipe, PizzaSize size) {
        Pizza p = new Pizza(size, recipe);
        pizzas.add(p);
        return p;
    }

    public List<PizzaRecipe> getMenu() {
        return menu;
    }

    public void update() {
        for (PizzaShopWorker worker : workers) {
            worker.update();
        }

        for (PizzaOven pizzaOven : ovens) {
            pizzaOven.update();
        }
    }
}
