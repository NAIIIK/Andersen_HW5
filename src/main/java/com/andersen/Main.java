package com.andersen;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/bus-tickets";
        FileReader fileReader = new FileReader();
        Validator validator = new Validator();

        try {
            List<BusTicket> tickets = fileReader.parseData(filePath);
            validator.validateTicket(tickets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
