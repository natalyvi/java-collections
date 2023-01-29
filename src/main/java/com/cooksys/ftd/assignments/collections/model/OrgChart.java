package com.cooksys.ftd.assignments.collections.model;

import java.util.*;

public class OrgChart {

    private Set<Employee> employees;

    /**
     * @param employee the {@code Employee} to add to the {@code OrgChart}
     * @return true if the {@code Employee} was added successfully, false otherwise
     */
    public boolean addEmployee(Employee employee) {
        if (employee == null) return false;
        if (this.employees == null) this.employees = new HashSet<>();

        if (this.employees.contains(employee)) {
            return false;
        }

        if (!employee.hasManager()) {
            if (employee.getClass() == Worker.class) return false;
            if (employee.getClass() == Manager.class) {
                this.employees.add(employee);
                return true;
            }
        }

        Manager manager = employee.getManager();

        if (!this.employees.contains(manager)) {
            addEmployee(manager);
        }
        this.employees.add(employee);
        return true;
    }

    /**
     * @param employee the {@code Employee} to search for
     * @return true if the {@code Employee} has been added to the {@code OrgChart}, false otherwise
     */
    public boolean hasEmployee(Employee employee) {
        if (this.employees == null) return false;
        return this.employees.contains(employee);
    }

    /**
     * @return all {@code Employee}s in the {@code OrgChart}, or an empty {@code Set} if no {@code Employee}s have
     * been added to the {@code OrgChart}
     */
    public Set<Employee> getAllEmployees() {
        if (this.employees == null) {
            return new HashSet<>();
        } else {
            return new HashSet<>(this.employees);
        }
    }

    /**
     * @return all {@code Manager}s in the {@code OrgChart}, or an empty {@code Set} if no {@code Manager}s
     * have been added to the {@code OrgChart}
     */
    public Set<Manager> getAllManagers() {
        Set<Manager> managers = new HashSet<>();
        if (this.employees == null) return managers;
        for (Employee employee :
                this.employees) {
            if (employee.getClass() == Manager.class) {
                managers.add((Manager) employee);
            }
        }
        return managers;
    }

    /**
     * @param manager the {@code Manager} whose direct subordinates need to be returned
     * @return all {@code Employee}s in the {@code OrgChart} that have the given {@code Manager} as a direct
     * parent, or an empty set if the {@code Manager} is not present in the {@code OrgChart}
     * or if there are no subordinates for the given {@code Manager}
     */
    public Set<Employee> getDirectSubordinates(Manager manager) {
        Set<Employee> directSubordinates = new HashSet<>();
        if (this.employees == null) return directSubordinates;
        if (!this.employees.contains(manager)) return directSubordinates;
        for (Employee employee :
                this.employees) {
            if (employee.hasManager() && employee.getManager().equals(manager)) directSubordinates.add(employee);
        }
        return directSubordinates;
    }

    /**
     * @return a map in which the keys represent the parent {@code Manager}s in the
     * {@code OrgChart}, and the each value is a set of the direct subordinates of the
     * associated {@code Manager}, or an empty map if the {@code OrgChart} is empty.
     */
    public Map<Manager, Set<Employee>> getFullHierarchy() {
        Map<Manager, Set<Employee>> fullHierarchy = new HashMap<>();
        if (this.employees == null) return fullHierarchy;
        for (Employee employee :
                this.employees) {
            if (employee.getClass() == Manager.class) {
                Set<Employee> subordinates = getDirectSubordinates((Manager) employee);
                fullHierarchy.put((Manager) employee, subordinates);
            }
        }
        return fullHierarchy;
    }

}
