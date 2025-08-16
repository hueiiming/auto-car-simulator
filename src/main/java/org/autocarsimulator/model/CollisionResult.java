package org.autocarsimulator.model;

public class CollisionResult implements Result {
    private final String carName1;
    private final String carName2;
    private final Position collisionPosition;
    private final int stepsToCollision;
    private final boolean collisionOccurred;

    public CollisionResult(String carName1, String carName2, Position collisionPosition, int stepsToCollision) {
        if (carName1 == null || carName2 == null || collisionPosition == null) {
            throw new IllegalArgumentException("Car names and collision position cannot be null.");
        }
        this.carName1 = carName1;
        this.carName2 = carName2;
        this.collisionPosition = collisionPosition;
        this.stepsToCollision = stepsToCollision;
        this.collisionOccurred = true;
    }

    public CollisionResult() {
        this.carName1 = null;
        this.carName2 = null;
        this.collisionPosition = null;
        this.stepsToCollision = 0;
        this.collisionOccurred = false;
    }

    @Override
    public String getCarName1() { return carName1; }

    @Override
    public String getCarName2() { return carName2; }

    @Override
    public Position getCollisionPosition() { return collisionPosition; }

    @Override
    public int getStepsToCollision() { return stepsToCollision; }

    @Override
    public boolean isCollisionOccurred() { return this.collisionOccurred; }

    @Override
    public String getResult() {
        if (this.collisionOccurred) {
            return carName1 + " " + carName2 + "\n" + collisionPosition.getX() + " " + collisionPosition.getY() + "\n" + stepsToCollision;
        } else {
            return "no collision";
        }
    }

}
