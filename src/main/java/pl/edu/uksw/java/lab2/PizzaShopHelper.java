package pl.edu.uksw.java.lab2;

public class PizzaShopHelper implements PizzaShopWorker{
    PizzaShop shop;
    Pizza workedPizza = null;

    public PizzaShopHelper(PizzaShop shop) {
        this.shop = shop;
    }

    @Override
    public void update() {
        if (hasPizza()) {
            switch (workedPizza.state){
                case Ordered -> {
                    throw new RuntimeException("Not my job!");
                }
                case Dough -> {
                    throw new RuntimeException("Not my job!");
                }
                case Pie -> {
                    throw new RuntimeException("Not my job!");
                }
                case Filled -> {
                    for (PizzaOven oven : shop.ovens) {
                        if (oven.isFree()) {
                            loadPizzaInOven(oven);
                            break;
                        }
                    }
                }
                case Baking -> {
                    throw new RuntimeException("Hot pizza!");
                }
                case Baked -> {
                    workedPizza.state = PizzaState.Boxed;
                }
                case Boxed -> {
                    shop.pizzas.add(workedPizza);
                    workedPizza = null;
                }
            }


        } else if (!checkOven()) {
            pickUpOrder();
        }
    }

    private void loadPizzaInOven(PizzaOven pizzaOven) {
        pizzaOven.loadPizza(workedPizza);
        workedPizza = null;
    }

    private void pickUpOrder() {
        assert workedPizza == null;
        for (Pizza pizza : shop.pizzas) {
            if (pizza.state == PizzaState.Filled) {
                workedPizza = pizza;
                shop.pizzas.remove(workedPizza);
                break;
            }
        }
    }

    private boolean checkOven() {
        for (var pizzaOven : shop.ovens) {
            if (pizzaOven.contents != null) {
                if (pizzaOven.contents.state == PizzaState.Baked) {
                    workedPizza = pizzaOven.contents;
                    pizzaOven.contents = null;
                    return true;
                } else if (pizzaOven.contents.state == PizzaState.Burned) {
                    pizzaOven.contents = null;
                    System.out.println("Throwing away burnt pizza :(");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPizza() {
        return workedPizza != null;
    }
}
