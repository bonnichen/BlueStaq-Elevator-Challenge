package model;

import java.util.ArrayList;
import java.util.List;
import model.Elevator.PassengerTransfer;

public class Simulation {

    /**
     * Elevator used in simulation
     */
    Elevator elevator;
    /**
     * total number of floors
     */
    int numFloors;
    /**
     * list of all floors
     */
    Floor[] floors;
    /**
     * total number of passengers that hasn't arrived at their destination
     */
    int totalPassenger;

    /**
     * list where each index corresponds to a floor and each element corresponds
     * to the passengers whose origin is the floor given by the index number.
     */
    List<ArrayList<Passenger>> floorToPassenger;

    /**
     * list of all passengers
     */
    List<Passenger> passengers = new ArrayList<>();

    /**
     * Returns total number of passengers that hasn't arrived at their destination
     */
    public int getTotalPassenger() {
        return totalPassenger;
    }

    /**
     * Sets up the floors for the simulation. Creates a floor for each number up to numFloors
     */
    public void setUpFloor() {
        Floor[] floors = new Floor[numFloors];
        for (int num = 0; num < numFloors; num++) {
            floorToPassenger.add(new ArrayList<>());
            floors[num] = new Floor(num);
        }
        this.floors = floors;
    }

    /**
     * Adds all passengers to their correct origin floor.
     */
    public void setUpPassenger() {
        int floorNum = 0;
        for (ArrayList<Passenger> passenger : floorToPassenger) {
            Passenger[] arr = passenger.toArray(new Passenger[0]);
            floors[floorNum].setPassengers(arr);
            floorNum++;
        }
    }

    /**
     * Creates passenger from a given origin and destination
     */
    public void createPassengers(int origin, int destination) {
        totalPassenger++;
        Passenger newPassenger = new Passenger(floors[origin], destination, "p" + totalPassenger);
        passengers.add(newPassenger);
        floorToPassenger.get(origin).add(newPassenger);
    }

    /**
     * Creates new elevator from floors
     */
    public void setUpElevator() {
        elevator = new Elevator(floors);
    }

    /**
     * Runs simulation. Elevator continuously moves up or down to
     * bring passengers to their correct floors. Stops when all passengers reach destination.
     */
    public void runElevator() {
        System.out.println("Starting simulation");
        while (totalPassenger > 0) {
            System.out.println("Current Passengers: " + elevator.passengers.toString());
            PassengerTransfer passengerTransfer = elevator.move();
            System.out.println("\nArriving at Floor " + (elevator.getCurrentFloor()));
            totalPassenger -= passengerTransfer.out().size();
            System.out.println("Passengers Boarded: " + passengerTransfer.in().toString());
            System.out.println("Passengers Arrived: " + passengerTransfer.out().toString());
        }
    }

    /**
     * Creates a new simulation from a given number of floors
     */
    public Simulation(int numFloor) {
        this.numFloors = numFloor;
        floorToPassenger = new ArrayList<>();
    }

}
