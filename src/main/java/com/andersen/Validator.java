package com.andersen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Validator {
    private static final String  START_DATE_VIOLATION = "start date";
    private static final String  PRICE_VIOLATION = "price";
    private static final String  TYPE_VIOLATION = "ticket type";

    private Map<String, Integer> violations = new HashMap<>();

    private int currentViolations;

    public Validator() {
        violations.put(START_DATE_VIOLATION, 0);
        violations.put(PRICE_VIOLATION, 0);
        violations.put(TYPE_VIOLATION, 0);
    }

    public void validateTicket(List<BusTicket> tickets){
        System.out.println("Total = {" + tickets.size() + "}");
        int validTickets = tickets.size();

        for(BusTicket ticket : tickets) {
            validatePrice(ticket);
            validateStartDate(ticket);
            validateTicketType(ticket);

            if(currentViolations > 0) {
                validTickets--;
            }
            currentViolations = 0;
        }

        System.out.println("Valid = {" + validTickets + "}");
        getTheMostPopularValidation();
    }

    private void validatePrice(BusTicket ticket) {
        if (ticket.getPrice() == null) {
            violations.put(PRICE_VIOLATION, violations.get(PRICE_VIOLATION) + 1);
            currentViolations++;
        } else {
            int intPrice = Integer.parseInt(ticket.getPrice());
            if (intPrice == 0 || intPrice % 2 != 0) {
                violations.put(PRICE_VIOLATION, violations.get(PRICE_VIOLATION) + 1);
                currentViolations++;
            }
        }
    }

    private void validateStartDate (BusTicket ticket) {
        List<String> typesWithStartDate = Arrays.asList("DAY", "WEEK", "YEAR");

        if(typesWithStartDate.contains(ticket.getTicketType()) && ticket.getStartDate() != null
                && !ticket.getStartDate().isEmpty()) {
            try {
                LocalDate startDate = LocalDate.parse(ticket.getStartDate());
                if(startDate.isAfter(LocalDate.now())){
                    violations.put(START_DATE_VIOLATION, violations.get(START_DATE_VIOLATION) + 1);
                    currentViolations++;
                }
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        } else {
            violations.put(START_DATE_VIOLATION, violations.get(START_DATE_VIOLATION) + 1);
            currentViolations++;
        }

    }

    private void validateTicketType(BusTicket ticket) {
        List<String> types = Arrays.asList("DAY", "MONTH", "WEEK", "YEAR");

        if (!types.contains(ticket.getTicketType())) {
            violations.put(TYPE_VIOLATION, violations.get(TYPE_VIOLATION) + 1);
            currentViolations++;
        }
    }

    private void getTheMostPopularValidation() {
        System.out.println("The most popular violation = {"
                + Collections.max(violations.entrySet(), Map.Entry.comparingByValue()).getKey()
                + "}");
    }
}
