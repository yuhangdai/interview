package com.aotain.strategy;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/04/21
 */
public enum PizzaDeliveryStrategy {

    EXPRESS(Strategies.EXPRESS){
        @Override
        public void deliver(Pizza pizza){
            System.out.println("Pizza will be delivered in express mode");
        }
    },

    NORMAL(Strategies.NORMAL){
        @Override
        public void deliver(Pizza pizza){
            System.out.println("Pizza will be delivered in normal mode");
        }
    };

    private int strategy;

    PizzaDeliveryStrategy(final int strategy){
        this.strategy = strategy;
    }

    public abstract void deliver(Pizza pizza);

    public static PizzaDeliveryStrategy valueOf(int strategy) {
        for( PizzaDeliveryStrategy pizzaDeliveryStrategy : PizzaDeliveryStrategy.class.getEnumConstants()) {
            if(strategy == pizzaDeliveryStrategy.strategy)
                return pizzaDeliveryStrategy;
        }
        throw new IllegalArgumentException("Type:" + strategy + " is not a valid "
                + "Types.java value.");
    }

    public static void main(String[] args) {


    }
}
