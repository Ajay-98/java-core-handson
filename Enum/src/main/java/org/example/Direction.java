package org.example;

public enum Direction {
    NORTH, EAST, WEST, SOUTH;

    public void printAll() {
        System.out.println(Direction.values());
    }

    public Direction find( String s){
        return Direction.valueOf(s);
    }

    public Direction opposite(Direction d){
        return switch (d){
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case WEST -> EAST;
            case SOUTH -> NORTH;
        };
    }

}
