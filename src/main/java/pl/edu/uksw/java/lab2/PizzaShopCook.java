package pl.edu.uksw.java.lab2;

class PizzaShopCook implements PizzaShopWorker {
    PizzaShop shop;
    Pizza workedPizza = null;

    public PizzaShopCook(PizzaShop shop) {
        this.shop = shop;
    }

    @Override
    public void update() {
        if (hasPizza()) {
            preparePizza();
        } else {
            pickUpOrder();
        }
    }

    private boolean hasPizza() {
        return workedPizza != null;
    }

    private void pickUpOrder() {
        assert workedPizza == null;
        for (Pizza pizza : shop.pizzas) {
            if (pizza.state == PizzaState.Ordered) {
                workedPizza = pizza;
                shop.pizzas.remove(workedPizza);
                break;
            }
        }
    }

    private void preparePizza() {
        assert hasPizza();
        switch (workedPizza.state) {
            case Ordered -> {
                workedPizza.state = PizzaState.Dough;
            }
            case Dough -> {
                workedPizza.state = PizzaState.Pie;
            }
            case Pie -> {
                if (workedPizza.ingredientsOnPizza.size() < workedPizza.recipe.ingredients().size()) {
                    workedPizza.ingredientsOnPizza.addAll(workedPizza.recipe.ingredients());
                } else {
                    workedPizza.state = PizzaState.Filled;
                }
            }
            case Filled -> {
                for (var pizzaOven : shop.ovens) {
                    if (pizzaOven.isFree()) {
                        pizzaOven.loadPizza(workedPizza);
                        workedPizza = null;
                        break;
                    }
                }
                if (hasPizza()){
                    shop.pizzas.add(workedPizza);
                    workedPizza = null;
                }
            }
            case Baking -> {
                throw new RuntimeException("Hot pizza!");
            }
            case Baked -> {
                throw new RuntimeException("Not my job!");
            }
            case Boxed -> {
                throw new RuntimeException("Not my job!");
            }
        }
    }
}
