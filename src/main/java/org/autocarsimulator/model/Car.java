package org.autocarsimulator.model;

import java.util.List;

public class Car {
    private Position pos;
    private final List<Command> commands;

    private final String name;
    public Car(String name, Position pos, List<Command> commands) {
        this.name = name;
        this.pos = pos;
        this.commands = commands;
    }

    public String getName() { return this.name; }

    public Position getPosition() { return this.pos; }

    public List<Command> getCommands() { return this.commands; }

    public void setPosition(Position pos) { this.pos = pos; }

}
