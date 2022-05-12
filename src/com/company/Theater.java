package com.company;

import java.util.*;

public class Theater {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();

    // ANONYMOUS INNER CLASS => COMPARATOR INTERFACE
    static final Comparator<Seat> PRICE_ORDER =new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.getPrice() < seat2.getPrice()){
                return -1;
            } else if(seat1.getPrice()> seat2.getPrice()){
                return 1;
            } else{
                return 0;
            }
        }
    };

    public Theater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;
        int lastRow = 'A'+(numRows - 1); // As index starts from 0
        for(char row = 'A'; row<=lastRow; row++){ // Creating rows
            // starts from A and is incremented further to the preceding letters in the alphabet
            for(int seatNum = 1; seatNum<=seatsPerRow; seatNum++){ // Allocating a seat number per seat
                double price = 12.00; // Base price

                if((row<'D') && (seatNum>=4 && seatNum<=9)){
                    // Premium Seats
                    price=14.00;
                } else if ((row > 'F') || (seatNum<4 || seatNum>9)){
                    // Discounted Seat Price
                    price=7.00;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat); // Adding it to the Array List
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }

    // BINARY SEARCH OPTIMIZED BELOW
    public boolean reserveSeat(String seatNumber){
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null); // Null is provided as we'll be using the comparable interface
        if(foundSeat>= 0){
            return seats.get(foundSeat).reserve(); // checking if the seat is reserved or not
        } else{
            System.out.println("There is no seat "+seatNumber);
            return false;
        }
    }

    // For testing purposes
    public Collection<Seat> getSeats(){
       return seats;
    }

    // PRIVATE CLASS == INNER CLASS OF THEATER
    public class Seat implements Comparable<Seat>{ // COMPARABLE INTERFACE
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
            // Positive integer, if the current object is greater than the specified object.
            // Negative integer, if the current object is less than the specified object.
            // Zero, if the current object is equal to the specified object.
        }

        public boolean reserve(){
            if(!this.reserved){ // Not equal to reserve
                this.reserved = true;
                System.out.println("Seat "+seatNumber+" reserved");
                return true;
            } else{
                return false; // Already reserved
            }
        }

        public boolean cancel(){
            if(this.reserved){
                this.reserved = false; // There is a reservation for this seat
                System.out.println("Reservation of seat "+seatNumber+ " cancelled");
                return true;
            } else{
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }
}
