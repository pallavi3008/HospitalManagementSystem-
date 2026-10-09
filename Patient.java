package model;

public class Patient {
    private final int id;
    private final String name;
    private final int age;
    private final String phone;
    private final String treatmentType;

    public Patient(int id, String name, int age, String phone, String treatmentType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.treatmentType = treatmentType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getTreatmentType() { return treatmentType; }

    @Override
    public String toString() {
        return "Patient ID: " + id + " | Name: " + name + " | Age: " + age
                + " | Phone: " + phone + " | Treatment: " + treatmentType;
    }
}
