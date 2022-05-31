package com.task.management.resources;

import com.task.management.core.Task;
import com.task.management.db.TaskDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TaskManagementResourceTest {
    private static final TaskDAO DAO = Mockito.mock(TaskDAO.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TaskManagementResource(DAO))
            .build();

    List<Task> l;
    private Task task;

    @BeforeEach
    void setup() {
        task = new Task();
        task.setName("LearnReact");
        task.setId(2355);
        task.setCreated("2025-02-24");
        l = new ArrayList<Task>();
        l.add(task);
    }

    @Test
    void createTask() {
        Mockito.when(DAO.create(task)).thenReturn(task);

        Response response = EXT.target("/task").request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(task));

        Assert.assertEquals(204, response.getStatus());

    }

    @Test
    void getAllTask() {
        Mockito.when(DAO.findAll()).thenReturn(l);
        Response r = EXT.target("/task").request().get();

        Assertions.assertTrue("LearnReact".equals(((LinkedHashMap) r.readEntity(List.class).get(0)).get("name")));

    }

    @Test
    void updateTask() {
        Mockito.when(DAO.updateTask(task)).thenReturn(task);

        Response response = EXT.target("/task").request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(task));

        Assert.assertEquals(204, response.getStatus());

    }

    @Test
    void deleteTask() {
        Mockito.doNothing().when(DAO).delete(task.getId());

        Response response = EXT.target("/task/" + task.getId()).request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .delete();

        Assert.assertEquals(204, response.getStatus());

    }
}
