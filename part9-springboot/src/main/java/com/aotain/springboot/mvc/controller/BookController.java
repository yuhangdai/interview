package com.aotain.springboot.mvc.controller;

import com.aotain.springboot.mvc.domain.Book;
import org.assertj.core.util.Lists;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/09/17
 */
@RestController
@RequestMapping("/api")
public class BookController {

    List<Book> books = Lists.newArrayList();

    @GetMapping("/book/{id}")
    public ResponseEntity<List<Book>> getBookById(@PathVariable String id){
        return ResponseEntity.ok(books.stream().filter(book -> book.getIsbn().equals(id)).collect(Collectors.toList()));
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(books);
    }

    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody Book book){
        books.add(book);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        books.removeIf(book -> book.getIsbn().equals(id));
        return ResponseEntity.ok(books);
    }
}
