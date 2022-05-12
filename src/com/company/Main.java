package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater("Olympian", 8, 12);
//        List<Theater.Seat> seatCopy = new ArrayList<>(theater.seats);
//        printList(seatCopy);
//        seatCopy.get(1).reserve(); // Second element A02

        if(theater.reserveSeat("D12")){
            System.out.println("Please pay for D12");
        } else{
            System.out.println("Seat already reserved");
        }

        if(theater.reserveSeat("B13")){
            System.out.println("Please pay for B13");
        } else{
            System.out.println("Seat already reserved");
        }

        List<Theater.Seat> reverseSeats = new ArrayList<>(theater.getSeats()); // Adding the obtained seats to the new Array List
        Collections.reverse(reverseSeats);
        printList(reverseSeats);

        List<Theater.Seat> priceSeats = new ArrayList<>(theater.getSeats());
        priceSeats.add(theater.new Seat("B00", 13.00)); // instantiating an inner class
        priceSeats.add(theater.new Seat("A00", 13.00)); // instantiating an inner class
        Collections.sort(priceSeats, Theater.PRICE_ORDER);
        printList(priceSeats);

//        Collections.reverse(seatCopy);
//        System.out.println("Printing seatCopy");
//        printList(seatCopy);
//        System.out.println("Printing theater.seat");
//        printList(theater.seats);
//
//        Collections.shuffle(seatCopy);
//        System.out.println("Printing seatCopy");
//        printList(seatCopy);
//        System.out.println("Printing theater.seat");
//        printList(theater.seats);
//
//        Theater.Seat minSeat = Collections.min(seatCopy);
//        Theater.Seat maxSeat = Collections.max(seatCopy);
//        System.out.println("Min "+minSeat.getSeatNumber());
//        System.out.println("Max "+maxSeat.getSeatNumber());
//
//        sortList(seatCopy);
//        System.out.println("Printing a sorted list");
//        printList(seatCopy);
    }

    public static void printList(List<Theater.Seat> list){
        for(Theater.Seat seat : list){ //type var : array
            System.out.print(" "+seat.getSeatNumber()+ " Price: $"+ seat.getPrice() +" | ");
        }
        System.out.println();
        System.out.println("======================================================================================");
    }

    // A variation of the Bubble sort algorithm
    public static void sortList(List<? extends Theater.Seat> list){
        for(int i=0; i<list.size()-1; i++){
            for(int j=i+1; j<list.size(); j++){
                if(list.get(i).compareTo(list.get(j))>0){
                    Collections.swap(list, i, j);
                }
            }
        }
    }
}
