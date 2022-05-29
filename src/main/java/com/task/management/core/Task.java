package com.task.management.core;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
@NamedQuery(
        name = "com.task.management.core.Task.findAll",
        query = "SELECT t FROM Task t"
)
public class Task {

    @Id
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created", nullable = false)
    private String created;

    public Task() {
    }

    public Task(long id, String name, String created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
