package pl.edu.uksw.java.lab2;

class PizzaOven {
    final int pizzaBakeTime;
    Pizza contents = null;
    int timeLeft = 0;

    PizzaOven(int pizzaBakeTime) {
        this.pizzaBakeTime = pizzaBakeTime;
    }

    void update() {
        if (contents != null) {
            switch (contents.state) {
                case Ordered:
                case Dough:
                case Pie:
                    throw new RuntimeException("Pizza not ready to bake");
                case Filled:
                    throw new RuntimeException("Should already be baking");
                case Baking:
                    if (timeLeft > 0) {
                        timeLeft--;
                    } else {
                        contents.state = PizzaState.Baked;
                    }
                    break;
                case Baked:
                    contents.state = PizzaState.Burned;
                    break;
                case Boxed:
                    throw new RuntimeException("Invalid state");
            }

        }
    }

    public void loadPizza(Pizza workedPizza) {
        contents = workedPizza;
        contents.state = PizzaState.Baking;
        timeLeft = pizzaBakeTime;
    }

    boolean isFree(){
        return contents == null;
    }
}
