package pl.edu.uksw.java.lab2;

class PizzaOven {
    final int pizzaBakeTime;
    Pizza contents = null;
    int timeLeft = 0;

    PizzaOven(int pizzaBakeTime) {
        this.pizzaBakeTime = pizzaBakeTime;
    }

    void update() {
        if (contents != null && timeLeft > 0) {
            timeLeft--;
        } else if (contents != null) {
            contents.state = PizzaState.Baked;
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
