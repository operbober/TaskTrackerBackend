package by.tasktracker.spec;

import by.tasktracker.entity.TaskTag;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;


public class TaskTagSpecification {

    private final static String NAME = "name";
    private final static String ID = "id";
    private final static String PROJECT = "project";

    public static Specification<TaskTag> projectIdLike(String projectId) {
        return (tag, query, cb) -> Objects.nonNull(projectId)
                ? cb.like(tag.join(PROJECT).get(ID), projectId)
                : null;
    }

    public static Specification<TaskTag> nameLike(String name) {
        return (tag, criteriaQuery, cb) -> Objects.nonNull(name)
                ? cb.like(cb.upper(tag.get(NAME)),name.toUpperCase() + "%")
                : null;
    }

    public static Specification<TaskTag> nameEq(String name) {
        return (tag, criteriaQuery, cb) -> cb.like(cb.upper(tag.get(NAME)), name.toUpperCase());
    }
}
