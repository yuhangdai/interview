package com.aotain.strategy;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/04/21
 */
public class PizzaStrategy {

    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.setStatus(Pizza.PizzaStatus.READY);
        pizza.deliver(Strategies.NORMAL);
    }
}
