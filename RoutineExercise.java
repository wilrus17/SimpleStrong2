package com.will.simplestrong;

public class RoutineExercise {

    private int _id;
    private int _day;
    private String _exercise;
    private int sets;
    private int reps;

    public RoutineExercise(int _id, int _day, String _exercise) {
        this._id = _id;
        this._day = _day;
        this._exercise = _exercise;
    }

    public RoutineExercise() {
    }

    // Setters

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_day(int _day) {
        this._day = _day;
    }

    public void set_exercise(String _exercise) {
        this._exercise = _exercise;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    // Getters

    public int get_id() {
        return _id;
    }

    public int get_day() {
        return _day;
    }

    public String get_exercise() {
        return _exercise;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }
}

