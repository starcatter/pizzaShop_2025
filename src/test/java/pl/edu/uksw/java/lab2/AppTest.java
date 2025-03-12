package pl.edu.uksw.java.lab2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for pizza App.
 */
public class AppTest {
    PizzaShop shop = null;

    @BeforeEach
    public void resetShop(){
        shop = new PizzaShop(List.of(
                new PizzaRecipe("Margherita", List.of(Ingredient.Cheese)),
                new PizzaRecipe("Peperoni", List.of(Ingredient.Cheese, Ingredient.Peperoni))),
                1, 1, 1);
    }

    @Test
    public void testMargherita() {
        var menu = shop.getMenu();

        assertFalse(menu.isEmpty());

        var menuFirst = menu.getFirst();

        assertEquals("Margherita", menuFirst.name());

        var pizza = shop.placeOrder(menuFirst, PizzaSize.L);

        while (pizza.state != PizzaState.Boxed){
            shop.update();
            System.out.println(pizza);
        }

        assertEquals(1, pizza.ingredientsOnPizza.size());
        assertEquals(Ingredient.Cheese, pizza.ingredientsOnPizza.getFirst());
    }

    @Test
    public void testPeperoni() {
        var menu = shop.getMenu();

        assertFalse(menu.isEmpty());

        var pizzaRecipe = menu.get(1);

        assertEquals("Peperoni", pizzaRecipe.name());

        var pizza = shop.placeOrder(pizzaRecipe, PizzaSize.L);

        while (pizza.state != PizzaState.Boxed){
            shop.update();
            System.out.println(pizza);
        }

        assertEquals(2, pizza.ingredientsOnPizza.size());
        assertEquals(Ingredient.Cheese, pizza.ingredientsOnPizza.get(0));
        assertEquals(Ingredient.Peperoni, pizza.ingredientsOnPizza.get(1));
    }

    @Test
    public void testHotPizza() {
        PizzaShopCook pizzaShopWorker = (PizzaShopCook) shop.workers.get(0);

        var pizza = new Pizza(PizzaSize.L, null);
        pizza.state = PizzaState.Baking;

        pizzaShopWorker.workedPizza = pizza;

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                pizzaShopWorker::update,
                "Expected to throw, but didn't"
        );

        assertTrue(thrown.getMessage().contains("Hot pizza!"));
    }

}
