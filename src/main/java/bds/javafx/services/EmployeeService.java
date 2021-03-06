package bds.javafx.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bds.javafx.api.EmployeeBasicView;
import bds.javafx.api.EmployeeCreateView;
import bds.javafx.api.EmployeeDetailView;
import bds.javafx.api.EmployeeEditView;
import bds.javafx.data.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDetailView getEmployeeDetailView(Long id) {
        return employeeRepository.findEmployeeDetailedView(id);
    }

    public List<EmployeeBasicView> getEmployeesBasicView() {
        return employeeRepository.getEmployeesBasicView();
    }
    public List<EmployeeBasicView> getEmployeesByName(String name) {
        return employeeRepository.getEmployeesByName(name);
    }
    public void editEmployee(EmployeeEditView employeeEditView) {
        employeeRepository.editEmployee(employeeEditView);
    }
    public void createEmployee(EmployeeCreateView employeeCreateView) {
        char[] originalPassword = employeeCreateView.getPwd();
        char[] hashedPassword = hashPassword(originalPassword);
        employeeCreateView.setPwd(hashedPassword);

        employeeRepository.createEmployee(employeeCreateView);
    }
    public void deleteEmployee(EmployeeBasicView employeeBasicView)
    {
        employeeRepository.deleteEmployee(employeeBasicView);
    }


    /**
     * <p>
     * Note: For implementation details see: https://github.com/patrickfav/bcrypt
     * </p>
     *
     * @param password to be hashed
     * @return hashed password
     */
    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }

}