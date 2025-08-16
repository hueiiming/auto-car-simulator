package org.autocarsimulator.model;

public interface Result {
    String getResult();

    default String getCarName1() { return null; }

    default String getCarName2() { return null; }

    default Position getCollisionPosition() { return null; }

    default int getStepsToCollision() { return 0; }

    default boolean isCollisionOccurred() { return false; }
}
