package com.example.JavaApi.service;

import com.example.JavaApi.dto.BfhlRequest;
import com.example.JavaApi.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {


    private static final String FULL_NAME = "Jatin Sharma";
    private static final String DOB = "01012006";
    private static final String EMAIL = "jatin2026.be23@chitkara.edu.in";
    private static final String ROLL_NUMBER = "2310992026";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        try {
            List<String> data = request.getData();
            
            if (data == null) {
                data = new ArrayList<>();
            }

            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            List<Character> allAlphaChars = new ArrayList<>();
            long sum = 0;

            for (String item : data) {
                if (item == null || item.isEmpty()) {
                    continue;
                }

              
                if (isNumeric(item)) {
                    long number = Long.parseLong(item);
                    sum += number;
                    
                    if (number % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } 
       
                else if (isAlphabetic(item)) {
                    alphabets.add(item.toUpperCase());
                 
                    for (char c : item.toCharArray()) {
                        allAlphaChars.add(c);
                    }
                } 
            
                else {
                  
                    boolean hasAlpha = false;
                    boolean hasDigit = false;
                    
                    for (char c : item.toCharArray()) {
                        if (Character.isLetter(c)) {
                            hasAlpha = true;
                            allAlphaChars.add(c);
                        } else if (Character.isDigit(c)) {
                            hasDigit = true;
                        }
                    }
           
                    if (!hasAlpha && !hasDigit) {
                        specialCharacters.add(item);
                    } else if (hasAlpha) {
                
                        alphabets.add(item.toUpperCase());
                    }
                }
            }

            String concatString = generateConcatString(allAlphaChars);

            BfhlResponse response = new BfhlResponse(
                    true,
                    FULL_NAME + "_" + DOB,
                    EMAIL,
                    ROLL_NUMBER,
                    oddNumbers,
                    evenNumbers,
                    alphabets,
                    specialCharacters,
                    String.valueOf(sum),
                    concatString
            );

            return response;
        } catch (Exception e) {

            return new BfhlResponse(
                    false,
                    FULL_NAME + "_" + DOB,
                    EMAIL,
                    ROLL_NUMBER,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    "0",
                    ""
            );
        }
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabetic(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private String generateConcatString(List<Character> chars) {
        if (chars.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean uppercase = true;


        for (int i = chars.size() - 1; i >= 0; i--) {
            char c = chars.get(i);
            if (uppercase) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
            uppercase = !uppercase;
        }

        return result.toString();
    }
}
