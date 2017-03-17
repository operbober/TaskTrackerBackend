package by.tasktracker.service;

import by.tasktracker.entity.InviteStatus;
import by.tasktracker.repository.InviteStatusRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InviteStatusServiceImpl extends NamedServiceImpl<InviteStatus, InviteStatusRepository> implements InviteStatusService {

}
