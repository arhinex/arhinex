package ru.arhinex.security.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arhinex.baseentity.manager.BaseManager;
import ru.arhinex.baseentity.repository.BaseRepository;
import ru.arhinex.security.entity.User;
import ru.arhinex.security.repository.UserRepository;
import ru.arhinex.security.to.UserTO;

@RestController
@RequestMapping(value = "/employee")
public class UserManager extends BaseManager<User, UserTO> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseRepository<User> getBaseRepository() {
        return userRepository;
    }

    @Override
    public Class<? extends UserTO> getTOClass() {
        return UserTO.class;
    }

    @Override
    public Class<? extends User> getEntityClass() {
        return User.class;
    }
}
