package com.aotain.springboot.mvc.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/09/17
 */
@Data
@Accessors(chain = true)
public class Book {

    private String name;
    private String isbn;
}
