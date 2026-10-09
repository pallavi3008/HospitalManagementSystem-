package main;

import model.Patient;
import model.Doctor;
import model.Appointment;
import service.HospitalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final HospitalService hospital = new HospitalService();
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==================================");
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": addPatient(); break;
                    case "2": listPatients(); break;
                    case "3": addDoctor(); break;
                    case "4": listDoctors(); break;
                    case "5": scheduleAppointment(); break;
                    case "6": listAppointments(); break;
                    case "7": cancelAppointment(); break;
                    case "8":
                        running = false;
                        System.out.println("Thank you for using Hospital Management System.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-8.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error: Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("1. Add Patient");
        System.out.println("2. View Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View Doctors");
        System.out.println("5. Schedule Appointment");
        System.out.println("6. View Appointments");
        System.out.println("7. Cancel Appointment");
        System.out.println("8. Exit");
    }

    private static void addPatient() {
        System.out.print("Patient name: ");
        String name = requiredText();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        if (age < 0 || age > 125) throw new IllegalArgumentException("Please enter an age from 0 to 125.");
        System.out.print("Phone: ");
        String phone = requiredText();
        System.out.println("Treatment categories: General, Cardiology, Dental, Orthopedic, Dermatology, Neurology, Other");
        System.out.print("Treatment type: ");
        String treatment = requiredText();

        Patient patient = hospital.addPatient(name, age, phone, treatment);
        System.out.println("Patient added successfully. ID = " + patient.getId());
    }

    private static void listPatients() {
        if (hospital.getPatients().isEmpty()) {
            System.out.println("No patient records found.");
            return;
        }
        for (Patient patient : hospital.getPatients()) System.out.println(patient);
    }

    private static void addDoctor() {
        System.out.print("Doctor name (without Dr.): ");
        String name = requiredText();
        System.out.println("Specializations: General Physician, Cardiologist, Dentist, Orthopedic, Dermatologist, Neurologist, Other");
        System.out.print("Specialization: ");
        String specialization = requiredText();

        Doctor doctor = hospital.addDoctor(name, specialization);
        System.out.println("Doctor added successfully. ID = " + doctor.getId());
    }

    private static void listDoctors() {
        if (hospital.getDoctors().isEmpty()) {
            System.out.println("No doctor records found.");
            return;
        }
        for (Doctor doctor : hospital.getDoctors()) System.out.println(doctor);
    }

    private static void scheduleAppointment() {
        if (hospital.getPatients().isEmpty() || hospital.getDoctors().isEmpty()) {
            throw new IllegalArgumentException("Add at least one patient and one doctor first.");
        }
        listPatients();
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(sc.nextLine().trim());

        listDoctors();
        System.out.print("Enter doctor ID: ");
        int doctorId = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Appointment date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(sc.nextLine().trim(), INPUT_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Use date/time format yyyy-MM-dd HH:mm, for example 2026-12-20 14:30.");
        }

        Appointment appointment = hospital.scheduleAppointment(patientId, doctorId, dateTime);
        System.out.println("Appointment scheduled successfully. Appointment ID = " + appointment.getId());
    }

    private static void listAppointments() {
        if (hospital.getAppointments().isEmpty()) {
            System.out.println("No appointment records found.");
            return;
        }
        for (Appointment appointment : hospital.getAppointments()) {
            Patient patient = hospital.findPatient(appointment.getPatientId());
            Doctor doctor = hospital.findDoctor(appointment.getDoctorId());
            System.out.println(appointment + " | Patient: " + patient.getName()
                    + " | Doctor: Dr. " + doctor.getName());
        }
    }

    private static void cancelAppointment() {
        System.out.print("Enter appointment ID to cancel: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        hospital.cancelAppointment(id);
        System.out.println("Appointment cancelled successfully.");
    }

    private static String requiredText() {
        String value = sc.nextLine().trim();
        if (value.isEmpty()) throw new IllegalArgumentException("This field cannot be empty.");
        return value;
    }
}
