package com.cooksys.ftd.assignments.collections.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Worker implements Employee {

    private final String name;
    private Manager manager;

    /**
     * @param name the name of the worker to be created
     */
    public Worker(String name) {
        this.name = name;
    }

    /**
     * @param name    the name of the worker to be created
     * @param manager the direct manager of the worker to be created
     */
    public Worker(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
    }

    /**
     * @return the name of the worker
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return {@code true} if this worker has a manager, or {@code false} otherwise
     */
    @Override
    public boolean hasManager() {
        return this.manager != null;
    }

    /**
     * @return the manager of this worker, or null if it has none
     */
    @Override
    public Manager getManager() {
        return this.manager;
    }

    /**
     * @return a {@code List<Manager>} that represents the worker's chain of command,
     */
    @Override
    public List<Manager> getChainOfCommand() {
        List<Manager> chainOfCommand = new LinkedList<>();
        Employee currentEmployee = this;
        while (currentEmployee.hasManager()) {
            chainOfCommand.add(currentEmployee.getManager());
            currentEmployee = currentEmployee.getManager();
        }
        return chainOfCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return name.equals(worker.name) && Objects.equals(manager, worker.manager);
    }
}
