# Hospital Management System (Java Console Project)

A beginner-friendly Java project to manage patient, doctor, and appointment records.

## Features
- Add and list patients; each patient has a treatment category.
- Add and list doctors; each doctor has a specialization.
- Schedule appointments with patient and doctor IDs.
- Cancel scheduled appointments.
- View all appointments.
- Prevent appointments with unknown patient/doctor IDs and prevent double-booking a doctor at the same date/time.

## Requirements
- JDK 17 or later (JDK 8+ should also work).
- Windows PowerShell / Command Prompt, or a terminal on macOS/Linux.

## Run on Windows PowerShell
Open PowerShell in the `HospitalManagementSystem` folder, then run:

```powershell
javac -d out src\model\Patient.java src\model\Doctor.java src\model\Appointment.java src\service\HospitalService.java src\main\Main.java
java -cp out main.Main
```

If PowerShell says `javac` or `java` is not recognized, install a JDK and add its `bin` folder to PATH.

## Run on macOS/Linux
From the project folder:

```bash
javac -d out src/model/Patient.java src/model/Doctor.java src/model/Appointment.java src/service/HospitalService.java src/main/Main.java
java -cp out main.Main
```

## Example workflow
1. Choose `1` and add a patient.
2. Choose `3` and add a doctor.
3. Choose `5` and schedule an appointment using the displayed IDs.
4. Choose `6` to list appointments.
5. Choose `7` to cancel an appointment by ID.

## Note
This is an educational console application. Records are kept in memory and are lost when the program exits. It is not intended for storing real patient data.
