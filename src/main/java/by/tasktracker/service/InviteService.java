package by.tasktracker.service;

import by.tasktracker.dto.InviteDTO;
import by.tasktracker.entity.Invite;
import by.tasktracker.exceptions.UserNowMemberException;
import by.tasktracker.service.supeclass.CommonService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;


public interface InviteService extends CommonService<Invite> {
    Invite getByProjectIdAndUserName(String projectId, String userName);
    Page<Invite> getByUserId(String userId, int page, int size);
    Page<Invite> getByProjectId(String projectId, int page, int size);
    Invite save(InviteDTO inviteDTO) throws NotFoundException, UserNowMemberException;
    Invite confirm(String inviteId) throws NotFoundException;
    Invite close(String inviteId) throws NotFoundException;
}
