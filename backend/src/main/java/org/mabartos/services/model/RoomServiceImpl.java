package org.mabartos.services.model;

import io.quarkus.runtime.StartupEvent;
import org.mabartos.api.service.RoomService;
import org.mabartos.controller.data.RoomData;
import org.mabartos.general.RoomType;
import org.mabartos.persistence.model.RoomModel;
import org.mabartos.persistence.repository.RoomRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@Dependent
public class RoomServiceImpl extends CRUDServiceImpl<RoomModel, RoomRepository, Long> implements RoomService {

    @Inject
    RoomServiceImpl(RoomRepository repository) {
        super(repository);
    }

    public void start(@Observes StartupEvent event) {
    }

    @Override
    public Set<RoomModel> findByType(RoomType type) {
        return getRepository().find("type", type).stream().collect(Collectors.toSet());
    }

    @Override
    public RoomModel updateFromJson(Long roomID, String JSON) {
        RoomModel room = getRepository().findById(roomID);
        if (room != null) {
            RoomData data = RoomData.fromJson(JSON);
            room.setName(data.getName());
            return updateByID(roomID, room);
        }
        return null;
    }
}
