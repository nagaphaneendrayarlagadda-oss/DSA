import java.util.*;

// HRPulse System
public class HRPulse {

    // =========================
    // Employee Class
    // =========================
    static class Employee {
        int id;
        String name;
        String department;
        int performanceScore;

        LinkedList<String> attendanceHistory = new LinkedList<>();

        public Employee(int id, String name, String department, int performanceScore) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.performanceScore = performanceScore;
        }

        void addAttendance(String record) {
            attendanceHistory.add(record);
        }

        void displayAttendance() {
            System.out.println("Attendance History for " + name);
            for (String r : attendanceHistory) {
                System.out.println(r);
            }
        }
    }

    // Payroll Stack
    static Stack<String> payrollCorrections = new Stack<>();

    // Leave Queue
    static Queue<String> leaveQueue = new LinkedList<>();

    // Grievance Class
    static class Grievance {
        int priority;
        String message;

        Grievance(int priority, String message) {
            this.priority = priority;
            this.message = message;
        }
    }

    // Grievance Priority Queue
    static PriorityQueue<Grievance> grievanceQueue =
            new PriorityQueue<>((a, b) -> b.priority - a.priority);

    // Employee HashMap
    static HashMap<Integer, Employee> employees = new HashMap<>();

    // Shift Schedule Array
    static String[] shiftSchedule = new String[7];

    // Add Employee
    static void addEmployee(int id, String name, String dept, int score) {
        Employee e = new Employee(id, name, dept, score);
        employees.put(id, e);
    }

    // Search Employee
    static void searchEmployee(int id) {
        if (employees.containsKey(id)) {
            Employee e = employees.get(id);
            System.out.println("Employee Found: " + e.name + " Dept: " + e.department);
        } else {
            System.out.println("Employee not found");
        }
    }

    // Sort Employees by Performance
    static void sortEmployeesByPerformance() {

        List<Employee> list = new ArrayList<>(employees.values());

        list.sort((a, b) -> b.performanceScore - a.performanceScore);

        System.out.println("\nEmployees Sorted by Performance:");

        for (Employee e : list) {
            System.out.println(e.name + " Score: " + e.performanceScore);
        }
    }

    // Leave Request
    static void requestLeave(String employeeName) {
        leaveQueue.add(employeeName);
    }

    static void approveLeave() {
        if (!leaveQueue.isEmpty()) {
            System.out.println("Leave Approved for: " + leaveQueue.poll());
        } else {
            System.out.println("No leave requests");
        }
    }

    // Payroll Correction Stack
    static void payrollCorrection(String correction) {
        payrollCorrections.push(correction);
    }

    static void undoPayrollCorrection() {
        if (!payrollCorrections.isEmpty()) {
            System.out.println("Undo: " + payrollCorrections.pop());
        } else {
            System.out.println("No corrections to undo");
        }
    }

    // Grievances
    static void addGrievance(int priority, String message) {
        grievanceQueue.add(new Grievance(priority, message));
    }

    static void processGrievance() {
        if (!grievanceQueue.isEmpty()) {
            Grievance g = grievanceQueue.poll();
            System.out.println("Processing grievance: " + g.message);
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Shift Schedule
        shiftSchedule[0] = "Morning";
        shiftSchedule[1] = "Evening";
        shiftSchedule[2] = "Night";

        // Add Employees
        addEmployee(101, "Ravi", "IT", 85);
        addEmployee(102, "Anita", "HR", 92);
        addEmployee(103, "Rahul", "Finance", 78);

        // Search Employee
        searchEmployee(102);

        // Attendance
        employees.get(101).addAttendance("01-Mar Present");
        employees.get(101).addAttendance("02-Mar Present");
        employees.get(101).displayAttendance();

        // Leave Requests
        requestLeave("Ravi");
        requestLeave("Anita");

        approveLeave();

        // Payroll Corrections
        payrollCorrection("Salary Updated for Ravi");
        payrollCorrection("Bonus Added for Anita");

        undoPayrollCorrection();

        // Grievances
        addGrievance(3, "Leave issue");
        addGrievance(5, "Harassment complaint");
        addGrievance(2, "Payroll error");

        processGrievance();

        // Sort Employees
        sortEmployeesByPerformance();
    }
}