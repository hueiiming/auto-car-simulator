package org.autocarsimulator.model;

public enum Direction {
    N, S, E, W;

    public Direction turnLeft() {
        return switch (this) {
            case N -> W;
            case S -> E;
            case E -> N;
            case W -> S;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case N -> E;
            case S -> W;
            case E -> S;
            case W -> N;
        };
    }
}
