package by.tasktracker.repository;

import by.tasktracker.entity.Invite;
import by.tasktracker.repository.superclass.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InviteRepository extends CommonRepository<Invite>{
    Invite findByProjectIdAndUserName(String projectId, String userName);
    Page<Invite> findByUserId(String userId, Pageable pageable);
    Page<Invite> findByProjectId(String projectId, Pageable pageable);
}
