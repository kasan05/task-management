package com.task.management.db;

import com.task.management.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Task create(Task task) {
        return persist(task);
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("com.task.management.core.Task.findAll"));
    }

    public void delete(String id) {
       
    }
}
