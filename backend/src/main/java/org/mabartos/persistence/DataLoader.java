package org.mabartos.persistence;

import io.quarkus.runtime.StartupEvent;
import org.mabartos.api.service.AppServices;
import org.mabartos.persistence.model.HomeModel;
import org.mabartos.persistence.model.UserModel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DataLoader {

    @Inject
    AppServices services;

    @Inject
    public DataLoader() {
    }

    public void addRecords(@Observes StartupEvent start) {
        addUsers();
        addHomes();
        addUsersToHomes();
    }

    private void addUsers() {
        final int CNT = 10;
        for (int i = 0; i < CNT; i++) {
            UserModel user = new UserModel();
            user.setUsername("user" + i);
            user.setPassword("user" + i);
            user.setEmail("user" + i + "@localhost");
            user.setFirstname("FirstUser" + i);
            user.setLastname("LastUser" + i);
            services.users().create(user);
        }
    }

    private void addHomes() {
        final int CNT = 1;
        for (int i = 0; i < CNT; i++) {
            HomeModel home = new HomeModel();
            StringBuilder builder = new StringBuilder();
            home.setName(builder.append("home").append(i).toString());
            home.setBrokerURL("tcp://127.0.0.1:1883");
            services.homes().create(home);
        }
    }

    private void addUsersToHomes() {
        services.homes().addUserToHome((long)1, (long)12);
        //services.homes().addUserToHome((long)2, (long)24);
        //services.homes().addUserToHome((long)3, (long)22);
    }
}