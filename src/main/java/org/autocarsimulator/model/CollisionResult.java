package org.autocarsimulator.model;

public class CollisionResult implements Result {
    private final String carName1;
    private final String carName2;
    private final Position collisionPosition;
    private final int stepsToCollision;
    private final boolean collisionOccured;

    public CollisionResult(String carName1, String carName2, Position collisionPosition, int stepsToCollision, boolean collisionOccured) {
        this.carName1 = carName1;
        this.carName2 = carName2;
        this.collisionPosition = collisionPosition;
        this.stepsToCollision = stepsToCollision;
        this.collisionOccured = true;
    }

    public CollisionResult() {
        this.carName1 = null;
        this.carName2 = null;
        this.collisionPosition = null;
        this.stepsToCollision = 0;
        this.collisionOccured = false;
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
    public boolean isCollisionOccured() { return this.collisionOccured; }

    @Override
    public String getResult() {
        if (this.collisionOccured) {
            return carName1 + " " + carName2 + "\n" + collisionPosition.getX() + " " + collisionPosition.getY() + "\n" + stepsToCollision;
        } else {
            return "no collision";
        }
    }

}
