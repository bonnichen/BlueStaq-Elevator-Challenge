package model;

import model.Elevator.Direction;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Each floor the elevator traverses through
 */
public class Floor {

    /**
     * The floor number
     */
    public int floorNum;
    /**
     * The number of passenger currently waiting for the elevator at the floor
     */
    public int numPeople;
    /**
     * Passengers who wants to travel upwards
     */
    public Queue<Passenger> upWaitLine = new LinkedList<>();
    /**
     * Passengers who wants to travel downwards
     */
    public Queue<Passenger> downWaitLine = new LinkedList<>();
    /**
     * Whether the elevator call button is currently pressed.
     */
    public boolean buttonPressed = false;

    /**
     * Returns floor number
     */
    public int getFloorNum() {
        return floorNum;
    }

    /**
     * Return total number of people waiting for elevator
     */
    public int getNumPeople() {
        return numPeople;
    }

    /**
     * Calls for elevator
     */
    public void pushButton() {
        buttonPressed = true;
    }

    /**
     * Returns whether button is pressed or not
     */
    public boolean getButtonStatus() {
        return buttonPressed;
    }

    /**
     * Returns the passenger who boards the elevator. If elevator is moving upwards, returns first
     * passenger from the up wait line. If elevator is moving downwards, returns first passenger
     * from the down wait line.
     */
    public Passenger boardPassenger(Direction elevatorDirection) {
        Passenger passenger;
        if (elevatorDirection == Direction.UP) {
            passenger = upWaitLine.poll();
        } else {
            passenger = downWaitLine.poll();
        }
        if (passenger != null) {
            numPeople--;
        }
        if (numPeople == 0) {
            buttonPressed = false;
        }
        return passenger;
    }

    /**
     * Sorts the passengers on this floor into their respective lines based on direction traveling.
     */
    public void setPassengers(Passenger[] people) {
        this.numPeople = people.length;
        for (Passenger person : people) {
            if (person.direction == Direction.UP) {
                upWaitLine.add(person);
            } else {
                downWaitLine.add(person);
            }
        }
    }

    /**
     * Initializes a new floor from a floor number
     */
    public Floor(int floorNum) {
        this.floorNum = floorNum;
    }
}
