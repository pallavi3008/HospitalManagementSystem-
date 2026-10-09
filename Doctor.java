package model;

public class Doctor {
    private final int id;
    private final String name;
    private final String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return "Doctor ID: " + id + " | Name: Dr. " + name
                + " | Specialization: " + specialization;
    }
}
