package by.tasktracker.service;

import by.tasktracker.dto.InviteDTO;
import by.tasktracker.entity.Invite;
import by.tasktracker.entity.InviteStatus;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.UserNowMemberException;
import by.tasktracker.repository.InviteRepository;
import by.tasktracker.service.supeclass.CommonServiceImpl;
import javassist.NotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class InviteServiceImpl extends CommonServiceImpl<Invite, InviteRepository> implements InviteService {

    @Autowired private InviteStatusService statusService;
    @Autowired private ProjectService projectService;
    @Autowired private UserService userService;

    @Override
    public Invite getByProjectIdAndUserName(String projectId, String userName) {
        return repository.findByProjectIdAndUserName(projectId, userName);
    }

    @Override
    public Page<Invite> getByUserId(String userId, int page, int size) {
        return repository.findByUserId(userId, new PageRequest(page, size));
    }

    @Override
    public Page<Invite> getByProjectId(String projectId, int page, int size) {
        return repository.findByProjectId(projectId, new PageRequest(page, size));
    }

    @Override
    public Invite save(InviteDTO inviteDTO) throws NotFoundException, UserNowMemberException {
        Project project = projectService.get(inviteDTO.getProjectId());
        User user = userService.getByName(inviteDTO.getUsername());

        if (project == null || user == null) {
            throw new NotFoundException("User or Project not found!");
        }

        Hibernate.initialize(project.getMembers());
        if (project.getMembers().contains(user)) {
            throw new UserNowMemberException();
        }

        Invite invite = getByProjectIdAndUserName(inviteDTO.getProjectId(), inviteDTO.getUsername());
        if (invite == null || !invite.getStatus().getName().equals(InviteStatus.OPEN)) {
            invite = new Invite();
            invite.setProject(project);
            invite.setUser(user);
            invite.setStatus(statusService.getByName(InviteStatus.OPEN));
            invite = save(invite);
        }
        return invite;
    }

    @Override
    @Transactional
    public Invite confirm(String inviteId) throws NotFoundException {

        Invite invite = get(inviteId);

        if (invite == null) {
            throw new NotFoundException("Invite not found");
        }

        if (invite.getStatus().getName().equals(InviteStatus.OPEN)) {
            Project project = invite.getProject();
            Hibernate.initialize(project.getMembers());
            Set<User> members = project.getMembers();
            members.add(invite.getUser());
            project.setMembers(members);
            projectService.save(project);

            return close(invite);
        } else {
            return invite;
        }
    }

    @Override
    public Invite close(String inviteId) throws NotFoundException {
        Invite invite = get(inviteId);

        if (invite == null) {
            throw new NotFoundException("Invite not found");
        }
        if (invite.getStatus().getName().equals(InviteStatus.OPEN)) {
            return close(invite);
        } else {
            return invite;
        }
    }

    private Invite close(Invite invite) {
        invite.setStatus(statusService.getByName(InviteStatus.CLOSE));
        return save(invite);
    }
}
