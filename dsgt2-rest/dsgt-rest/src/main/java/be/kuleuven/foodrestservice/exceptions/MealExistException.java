package be.kuleuven.foodrestservice.exceptions;

public class MealExistException extends RuntimeException {

    public MealExistException(String id) {
        super("Could not find meal " + id);
    }
}
