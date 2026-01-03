package com.fitapp.model;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CaloriesTracker {

    /**
     * This class uses JavaFX Property classes to implement and take advantage of the observer pattern.
     * The observer pattern is a design pattern in which an object, known as the subject,
     * maintains a list of its dependents, known as observers, and notifies them automatically of any state changes.
     * These property classes are observers under the hood. They wrap a value together with change notification.
     * They are objects which store a value, notify listeners on value changes and can be bound to other properties.
     * Properties = observable state, holds values
     * Binding = observable relationship, hold formulas
     *
     * Why do we use this:
     * Loose coupling: Observers are notified of state changes without needing to know anything about the subject
     * This makes it easy to add, remove, or modify observers without affecting the subject
     *
     * The observer pattern is highly flexible and can be used in a wide range of software applications.
     *
     * The observer pattern establishes a one-to-many relationship. We have one subject and multiple observers.
     * The subject in this scenario is the CaloriesTracker class more specifically its attributes (observable states).
     * The observers are anything which listens or binds to these attributes. So if the attributes ever change they
     * notify the observers.
     */

    // attributes
    // subjects of the observer pattern
    private final IntegerProperty consumed = new SimpleIntegerProperty(0);
    private final IntegerProperty dailyLimit;

    // constructor
    public CaloriesTracker(int dailyLimit) {
        // wrapping the raw int in an observable property
        this.dailyLimit = new SimpleIntegerProperty(dailyLimit);
    }

    // getters - returning the properties themselves not just the value
    public IntegerProperty getDailyLimit() {
        return dailyLimit;
    }
    public IntegerProperty getConsumed(){return consumed;}

    // methods
    public void addCalories(int calories) {
        if (calories < 0) {
            throw new NegativeCaloriesException();
        }
        if (consumed.get() + calories > dailyLimit.get()) {
            throw new CalorieLimitExceededException();
        }
        // observer trigger: JavaFX automatically notifies listeners and all bindings
        consumed.set(consumed.get() + calories);
    }

    public void reset() {
        consumed.set(0);
    }

    // returns a computed observable value, it is derived not stored
    // IntegerBinding is an observable, read-only integer value which is automatically recalculated whenever
    // one of its dependencies(dailyLimit and consumed) changes.
    public IntegerBinding remainingCaloriesProperty() {
        // subtract creates a binding which observes dailyLimit and consumed
        // whenever either changes the result updates automatically
        // IntegerBinding is defined by the expression dailyLimit.subtract(consumed)
        return (IntegerBinding) dailyLimit.subtract(consumed);
    }
}
