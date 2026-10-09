package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private final int id;
    private final int patientId;
    private final int doctorId;
    private final LocalDateTime dateTime;
    private String status;

    public Appointment(int id, int patientId, int doctorId, LocalDateTime dateTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = "SCHEDULED";
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }
    public void cancel() { status = "CANCELLED"; }

    @Override
    public String toString() {
        return "Appointment ID: " + id + " | Patient ID: " + patientId
                + " | Doctor ID: " + doctorId + " | Date/Time: "
                + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | Status: " + status;
    }
}
