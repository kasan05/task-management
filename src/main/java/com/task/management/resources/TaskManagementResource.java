package com.task.management.resources;

import com.task.management.core.Task;
import com.task.management.db.TaskDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskManagementResource {

    private final TaskDAO taskDAO;

    public TaskManagementResource(final TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @POST
    @UnitOfWork
    public Task createTask(@Valid Task task) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        task.setCreated(dtf.format(now));
        Random r = new Random();
        task.setId(r.ints(10, (100000 + 1)).findFirst().getAsInt());
        return taskDAO.create(task);
    }

    @GET
    @UnitOfWork
    public List<Task> getAllTask() {
        return taskDAO.findAll();
    }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    public void deleteTask(@PathParam("id") int id) {
        taskDAO.delete(id);
    }

    @PUT
    @UnitOfWork
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }
}
