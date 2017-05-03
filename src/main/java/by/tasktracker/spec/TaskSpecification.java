package by.tasktracker.spec;

import by.tasktracker.entity.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;


public class TaskSpecification {

    private final static String NAME = "name";
    private final static String ID = "id";
    private final static String PROJECT = "project";
    private final static String OWNER = "owner";
    private final static String TAGS = "tags";

    public static Specification<Task> projectIdLike(String projectId) {
        return (task, query, cb) -> Objects.nonNull(projectId)
                ? cb.like(task.join(PROJECT).get(ID), projectId)
                : null;
    }

    public static Specification<Task> nameLike(String name) {
        return (task, criteriaQuery, cb) -> Objects.nonNull(name)
                ? cb.like(cb.upper(task.get(NAME)),"%" + name.toUpperCase() + "%")
                : null;
    }

    public static Specification<Task> ownerNameLike(String ownerName) {
        return (task, criteriaQuery, cb) -> Objects.nonNull(ownerName)
                ? cb.like(cb.upper(task.join(OWNER).get(NAME)), ownerName.toUpperCase())
                : null;
    }

    public static Specification<Task> tags(String[] tagNames) {
        return (task, query, cb) -> (Objects.nonNull(tagNames) && tagNames.length > 0)
                ? cb.upper(task.join(TAGS).get(NAME)).in(tagNames)
                : null;
    }
}
