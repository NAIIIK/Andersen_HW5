package com.andersen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    private Scanner scanner;

    public ArrayList<BusTicket> parseData(String filePath) throws FileNotFoundException {
        scanner = new Scanner(new File(filePath));
        ArrayList<BusTicket> arrayList = new ArrayList<>();
        while(scanner.hasNextLine()){
            String busTicketJson;
            busTicketJson = scanner.nextLine();
            try {
                BusTicket busTicket = new ObjectMapper().readValue(busTicketJson, BusTicket.class);
                arrayList.add(busTicket);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return arrayList;
    }
}
