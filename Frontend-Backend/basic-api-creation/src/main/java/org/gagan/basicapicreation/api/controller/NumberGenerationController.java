package org.gagan.basicapicreation.api.controller;

import org.gagan.basicapicreation.api.service.NumberGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/api")
public class NumberGenerationController {

    private final NumberGenerationService numberGenerationService;

    public NumberGenerationController(NumberGenerationService numberGenerationService) {
        this.numberGenerationService = numberGenerationService;
    }

    List<Number> numberList = new ArrayList<>();
    @GetMapping("/getNumberList")
    public ResponseEntity<List<Number>> generateNumbers(){
        return ResponseEntity.ok(numberGenerationService.generateNumbers());
    }
}
