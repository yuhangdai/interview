package com.aotain.mybatis;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/27
 */
public class Main {

    public static void main(String[] args) {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMapper(MapperA.class);
        mapperRegistry.addMapper(MapperB.class);

        MapperA mapperA = mapperRegistry.getMapper(MapperA.class);
        mapperA.testA();
    }
}
