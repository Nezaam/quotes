package com.krymov.quotes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

  @GetMapping("")
  public ResponseEntity<String> index() {
    return new ResponseEntity<>("<a href=\"/swagger-ui-custom.html\">Go to Swagger-UI</a>", HttpStatus.OK);
  }
}