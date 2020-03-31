package org.mabartos.controller.room;

import org.mabartos.api.controller.device.DevicesResource;
import org.mabartos.api.controller.room.RoomResource;
import org.mabartos.api.model.BartSession;
import org.mabartos.controller.device.DevicesResourceProvider;
import org.mabartos.persistence.model.RoomModel;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class RoomResourceProvider implements RoomResource {

    private final BartSession session;

    public RoomResourceProvider(BartSession session) {
        this.session = session;
        this.session.initEnvironment();
    }

    @GET
    public RoomModel getRoom() {
        return session.services().rooms().findByID(session.getActualRoom().getID());
    }

    @PATCH
    public RoomModel updateRoom(String JSON) {
        return session.services().rooms().updateFromJson(session.getActualRoom().getID(), JSON);
    }

    @DELETE
    public boolean deleteRoom() {
        return session.services().rooms().deleteByID(session.getActualRoom().getID());
    }

    @Path(DevicesResource.DEVICE_PATH)
    public DevicesResource forwardToDevices() {
        return new DevicesResourceProvider(session);
    }
}
