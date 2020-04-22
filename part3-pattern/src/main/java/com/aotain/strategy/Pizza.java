package com.aotain.strategy;

/**
 * Demo class
 *
 * @author Administrator
 * @date 2020/04/21
 */
public class Pizza {

    private PizzaStatus status;

    public enum PizzaStatus {
        ORDERED(5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        private int timeToDelivery;

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public void deliver(int type) {
        if (isDeliverable()) {
            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy(type)
                    .deliver(this);
            this.setStatus(PizzaStatus.DELIVERED);
        }
    }
}
