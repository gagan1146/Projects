package org.gagan.basicapicreation.api.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberGenerationService {
    List<Number> numberList = new ArrayList<>();
    public List<Number> generateNumbers(){
        numberList.clear();
        for(int i=0;i<100;i++){
            numberList.add(i);
        }
        return numberList;
    }
}
