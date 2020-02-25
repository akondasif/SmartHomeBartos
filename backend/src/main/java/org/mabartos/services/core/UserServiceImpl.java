package org.mabartos.services.core;

import io.quarkus.runtime.StartupEvent;
import org.mabartos.api.service.UserService;
import org.mabartos.persistence.model.UserModel;
import org.mabartos.persistence.repository.UserRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Dependent
public class UserServiceImpl extends CRUDServiceImpl<UserModel, UserRepository> implements UserService {

    public void start(@Observes StartupEvent event) {
    }

    @Inject
    UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public UserModel findByUsername(String username) {
        return getRepository().find("username", username).firstResult();
    }

    @Override
    public UserModel findByEmail(String email) {
        return getRepository().find("email", email).firstResult();
    }
}
