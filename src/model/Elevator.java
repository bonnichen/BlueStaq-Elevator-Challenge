package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.TreeSet;

public class Elevator {

    /**
     * Elevator can move either up or down
     */
    public enum Direction {
        UP, DOWN
    }

    /**
     * A record whose first element is a list of passengers boarding at the floor and whose second
     * element is a list of passenger who leaves at the floor.
     */
    public record PassengerTransfer(ArrayList<Passenger> in, ArrayList<Passenger> out) {

    }

    /**
     * Maximum amount of people elevator can hold
     */
    private static final int MAX_CAPACITY = 5;
    /**
     * Current floor at which the elevator is at
     */
    public int currentFloor;
    /**
     * Current direction in which the elevator is moving in.
     */
    public Direction currentDirection;
    /**
     * Total amount of floors
     */
    public int totalFloors;
    /**
     * All floors the elevator can traverse through
     */
    public Floor[] floors;
    /**
     * Passengers currently on elevator
     */
    public TreeSet<Passenger> passengers;
    /**
     * All floors that all calling for the elevator
     */
    public Deque<Integer> floorCalls;

    /**
     * Returns the current floor at which the elevator is at
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Reverses given direction
     */
    private Direction reverseDirection(Direction currentDirection) {
        if (currentDirection == Direction.UP) {
            return Direction.DOWN;
        }
        return Direction.UP;
    }

    /**
     * Computes the next target floor the elevator moves towards. If there are passengers in the
     * elevator, the target floor is the destination of the passenger who wants to leave at a floor
     * closest to the current floor. Else, the target floor is the closest floor that is calling for
     * the elevator.
     */
    private int nextFloor() {
        int targetFloor;
        if (!passengers.isEmpty()) {
            targetFloor = currentDirection == Direction.UP ?
                    passengers.first().getDestination() : passengers.last().getDestination();
        } else {
            targetFloor = floorCalls.getFirst();
        }
        return targetFloor;
    }

    /**
     * Returns all passenger whose destination is the current floor.
     */
    private ArrayList<Passenger> processExit() {
        ArrayList<Passenger> output = new ArrayList<>();
        if (currentDirection == Direction.UP) {
            while (!passengers.isEmpty()
                    && passengers.first().getDestination() == currentFloor) {
                output.add(passengers.pollFirst());
            }
        } else {
            while (!passengers.isEmpty()
                    && passengers.last().getDestination() == currentFloor) {
                output.add(passengers.pollLast());
            }
        }
        return output;
    }

    /**
     * Remove all floors where there are no longer any passengers.
     */
    private void processCallLst() {
        if (floors[currentFloor].getNumPeople() == 0) {
            if (!floorCalls.isEmpty() && floorCalls.getFirst() == currentFloor) {
                floorCalls.removeFirst();
            } else if (!floorCalls.isEmpty() && floorCalls.getLast() == currentFloor) {
                floorCalls.removeLast();
            }
        }
    }

    /**
     * Move elevator up or down depending on current direction. Changes elevator direction when
     * reach final or bottom floor. Changes elevator direction when there are no other requests to
     * floors above or below current floor.
     */
    public PassengerTransfer move() {
        processCallLst();
        int targetFloor = nextFloor();
        PassengerTransfer output = new PassengerTransfer(new ArrayList<>(), new ArrayList<>());
        if (currentFloor < targetFloor) {
            currentDirection = Direction.UP;
            currentFloor++;
        } else if (currentFloor > targetFloor) {
            currentDirection = Direction.DOWN;
            currentFloor--;
        }
        output.out.addAll(processExit());
        if (currentFloor == totalFloors - 1 || currentFloor == 0) {
            currentDirection = reverseDirection(currentDirection);

        } else if (passengers.isEmpty() && !floorCalls.isEmpty()) {
            int nextCall = (currentDirection == Direction.UP) ? floorCalls.getLast()
                    : floorCalls.getFirst();
            if (currentFloor == nextCall && targetFloor == nextCall) {
                currentDirection = reverseDirection(currentDirection);
            }
        }

        output.in.addAll(boardPassengers());

        return output;
    }


    /**
     * Returns List of all passengers boarding on current floor
     */
    public ArrayList<Passenger> boardPassengers() {
        ArrayList<Passenger> passengerIn = new ArrayList<>();
        while (passengers.size() < MAX_CAPACITY) {
            Passenger passenger = floors[currentFloor].boardPassenger(currentDirection);
            if (passenger == null) {
                break;
            } else {
                passengerIn.add(passenger);
                passengers.add(passenger);
            }
        }
        return passengerIn;
    }

    /**
     * Tracks all current calls to the elevator.
     */
    private void renderFloorCalls() {
        for (Floor floor : floors) {
            if (floor.getButtonStatus() && !(floorCalls.contains(floor.floorNum))) {
                floorCalls.add(floor.floorNum);
            }
        }
    }

    /**
     * Creates a new elevator with the given floors.
     */
    public Elevator(Floor[] floors) {
        currentFloor = 0;
        this.totalFloors = floors.length;
        this.floors = floors;
        passengers = new TreeSet<>(Comparator.comparingInt(Passenger::getDestination)
                .thenComparing(Passenger::getName));
        floorCalls = new ArrayDeque<>();
        currentDirection = Direction.UP;
        renderFloorCalls();
        boardPassengers();
    }
}
