package model;

import model.Elevator.Direction;

/**
 * Passengers Riding the Elevator
 */
public class Passenger {

    /**
     * Passenger's destination
     */
    public int destination;
    /**
     * Passenger's original floor
     */
    public Floor origin;
    /**
     * Direction passenger is moving
     */
    public Direction direction;
    /**
     * Passenger's name
     */
    public String name;

    /**
     * Call for elevator
     */
    private void pressButton() {
        origin.pushButton();
    }

    /**
     * Returns Passenger name
     */
    public String getName() {
        return name;
    }

    /**
     * Return Passenger destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Constructs a new passenger using a given origin and destination floor
     */
    public Passenger(Floor origin, int destination, String name) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        direction = (destination - origin.getFloorNum()) > 0 ? Direction.UP : Direction.DOWN;
        pressButton();
    }

    @Override
    public String toString() {
        return name + " " + direction.toString();
    }
}
