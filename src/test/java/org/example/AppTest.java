package org.example;


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
                1, 1);
    }

    @Test
    public void testMargherita() {
        var m = shop.getMenu();

        assertFalse(m.isEmpty());

        var r = m.getFirst();

        assertEquals("Margherita", r.name());

        var p = shop.placeOrder(r, PizzaSize.L);

        while (p.state != PizzaState.Boxed){
            shop.update();
            System.out.println(p);
        }

        assertEquals(1, p.ingredientsOnPizza.size());
        assertEquals(Ingredient.Cheese, p.ingredientsOnPizza.getFirst());
    }

    @Test
    public void testPeperoni() {
        var m = shop.getMenu();

        assertFalse(m.isEmpty());

        var r = m.get(1);

        assertEquals("Peperoni", r.name());

        var p = shop.placeOrder(r, PizzaSize.L);

        while (p.state != PizzaState.Boxed){
            shop.update();
            System.out.println(p);
        }

        assertEquals(2, p.ingredientsOnPizza.size());
        assertEquals(Ingredient.Cheese, p.ingredientsOnPizza.get(0));
        assertEquals(Ingredient.Peperoni, p.ingredientsOnPizza.get(1));
    }

    @Test
    public void testHotPizza() {
        var w = shop.workers.get(0);

        var p = new Pizza(PizzaSize.L, null);
        p.state = PizzaState.Baking;

        w.workedPizza = p;

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                w::update,
                "Expected to throw, but didn't"
        );

        assertTrue(thrown.getMessage().contains("Hot pizza!"));
    }

}
