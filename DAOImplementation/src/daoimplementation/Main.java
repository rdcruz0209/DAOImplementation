package daoimplementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int choice;

    private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        // Employee employee = new Employee(1, 123456, "Robert", "Dela Cruz", 3);
        // System.out.println(employee);

        do {

            System.out.print("Please choose what to do:" +
                    "\n1) Insert new employee details" +
                    "\n2) Delete employee details using id" +
                    "\n3) Update existing employee info" +
                    "\n4) Print an employee detail" +
                    "\n5) Print all employees detail" +
                    "\n6) Quit" +
                    "\nEnter your choice: ");
            choice = Integer.parseInt(input.nextLine());
            switch (choice) {

                case 1:

                    System.out.print("Please enter employee ID: ");
                    int employeeId = Integer.parseInt(input.nextLine());
                    System.out.print("Please enter first name: ");
                    String firstName = input.nextLine();
                    System.out.print("Please enter last name: ");
                    String lastName = input.nextLine();
                    System.out.print("Please enter department ID: ");
                    int deptId = Integer.parseInt(input.nextLine());

                    Employee employee = new Employee(0, employeeId, firstName, lastName, deptId);

                    int result = employeeDAO.insert(employee);
                    System.out.println("Number of inserted employee object: " + result);
                    continue;

                case 2:

                    while (true) {
                        try {
                            System.out.print("Please input id of employee you wish to delete: ");
                            Employee targetDelEmployee = employeeDAO.get(Integer.parseInt(input.nextLine()));
                            System.out.println(targetDelEmployee);
                            int deleteResult = employeeDAO.delete(targetDelEmployee);

                            if (deleteResult != 0) {
                                System.out.println("Employee object successfully deleted");
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("No employee object found with the ID provided");
                            continue;
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        try {
                            System.out.print("Please input id of employee you wish to update: ");
                            Employee employeeTargetObj = employeeDAO.get(Integer.parseInt(input.nextLine()));
                            int updateResult = employeeDAO.update(employeeTargetObj);

                            if (updateResult != 0) {
                                System.out.println("Employee " + employeeTargetObj.getFirstName() + " "
                                        + employeeTargetObj.getLastName() + "  successfully updated");
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("No employee object found with the ID provided");
                            continue;
                        }
                    }
                    break;
                case 4:

                    while (true) {
                        try {
                            System.out.print("Please input id of employee you wish to print the details: ");
                            Employee getEmployeeObj = employeeDAO.get(Integer.parseInt(input.nextLine()));

                            if (getEmployeeObj == null) {
                                System.out.println("No employee object found with the ID provided");
                                continue;
                            }
                            System.out.println(getEmployeeObj);
                            break;
                        } catch (Exception e) {
                            System.out.println("No employee object found with the ID provided");
                            continue;
                        }
                    }
                    break;
                case 5:
                    while (true) {

                        try {
                            List<Employee> printEmployeeList = employeeDAO.getAll();

                            for (Employee printEmployee : printEmployeeList) {
                                System.out.println(printEmployee);
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("No existing employee to print");
                            continue;
                        }
                    }
                    break;
                case 6:
                    input.close();
                    break;

                default:
                    System.out.println("Not a valid choice input. Please try again.");
                    continue;
            }
        } while (choice != 6);

        // if (conn != null) {
        // System.out.println("Database Connection Successful!");

        // }
        // EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        // Employee employee = employeeDAO.get(10);
        // Employee employee2 = employeeDAO.get(8);

        // System.out.println(employee);
        // System.out.println(employee2);

        // List<Employee> employees;

        // EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        // employees = employeeDAO.getAll();

        // for (Employee employee : employees) {
        // System.out.println(employee);

        // }

    }

}
