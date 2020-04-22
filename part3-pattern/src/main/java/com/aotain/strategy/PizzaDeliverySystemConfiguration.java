package com.aotain.strategy;

/**
 * 枚举实现单例模式
 *
 * @author Administrator
 * @date 2020/04/21
 */
public enum PizzaDeliverySystemConfiguration {

    INSTANCE;

    PizzaDeliverySystemConfiguration(){}

    public static PizzaDeliverySystemConfiguration getInstance(){
        return INSTANCE;
    }

    public PizzaDeliveryStrategy getDeliveryStrategy(int strategyType) {
        return PizzaDeliveryStrategy.valueOf(strategyType);
    }
}
