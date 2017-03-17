package by.tasktracker.utils;

import by.tasktracker.entity.Invite;
import by.tasktracker.entity.InviteStatus;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.repository.InviteStatusRepository;
import by.tasktracker.repository.UserRepository;
import by.tasktracker.service.InviteService;
import by.tasktracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InviteService inviteService;

    @Autowired
    private InviteStatusRepository inviteStatusRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... strings) throws Exception {

        InviteStatus openInvite = inviteStatusRepository.save(new InviteStatus(InviteStatus.OPEN));
        InviteStatus closeInvite = inviteStatusRepository.save(new InviteStatus(InviteStatus.CLOSE));

        User testUser = new User("testuser", "testemil@mailinator.com", passwordEncoder.encode("interOP@123"));
        testUser.setActivationCodeNull();
        userRepository.save(testUser);

        User ivan = new User("ivan", "ivan@mailinator.com", passwordEncoder.encode("interOP@123"));
        ivan.setActivationCodeNull();
        userRepository.save(ivan);

        Project testProject = projectService.save(new Project("testProject", "", ivan));
        System.out.println("test_project_id = " + testProject.getId());

        Invite invite = inviteService.save(new Invite(testUser, testProject, openInvite));
        System.out.println("invite_id = " + invite.getId());

/*        Role roleManager = roleService.save(new Role(Role.ROLE_MANAGER));
        Role roleDeveloper = roleService.save(new Role(Role.ROLE_DEVELOPER));

        TaskStatus taskStatusOpen = taskStatusService.save(new TaskStatus(TaskStatus.OPEN));
        TaskStatus taskStatusInProgress = taskStatusService.save(new TaskStatus(TaskStatus.IN_PROGRESS));
        TaskStatus taskStatusClosed = taskStatusService.save(new TaskStatus(TaskStatus.CLOSED));

        User manager = userService.save(new User("boss", "1234", roleManager));
        User backEndDev =  userService.save(new User("backendDeveloper", "backendonelove", roleDeveloper));
        User frontEndDev = userService.save(new User("frontendDeveloper", "frontendonelove", roleDeveloper));
        User fullStackDev = userService.save(new User("fullstackDeveloper", "fuckingfullstack", roleDeveloper));

        Project easyProject = projectService.save(new Project("Easy project", "very easy project", manager));
        Project hardProject = projectService.save(new Project("Hard project", "very hard project", manager));

        Task firstTaskEasyProject = taskService.save(new Task("Create simple back-end", "create database and simple rest server", manager, easyProject, backEndDev));
        Task secondTaskEasyProject = taskService.save(new Task("Create simple front-end", "create simple front-end application with ajax and css", manager, easyProject, frontEndDev));
        Task firstTaskHardProject = taskService.save(new Task("Create hardest back-end", "create hardest Restful spring boot project", manager, hardProject));
        Task secondTaskHardProject = taskService.save(new Task("Create hardest front-end", "create hardest application: react.js bootstrap", manager, hardProject, fullStackDev));

        commentService.save(new Comment(frontEndDev, secondTaskHardProject, "Its very hard task!!!"));
        commentService.save(new Comment(fullStackDev, secondTaskHardProject, "Yes, but I do it."));
        commentService.save(new Comment(frontEndDev, secondTaskHardProject, "Good."));

        commentService.save(new Comment(frontEndDev, firstTaskEasyProject, "Plz do it fast"));
        commentService.save(new Comment(backEndDev, firstTaskEasyProject, "OK, Easy."));*/
    }
}