package org.mabartos.protocols.mqtt.data.device;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mabartos.persistence.model.DeviceModel;
import org.mabartos.protocols.mqtt.data.capability.manage.CapabilityWholeData;
import org.mabartos.protocols.mqtt.data.general.MqttSerializable;
import org.mabartos.protocols.mqtt.utils.MqttSerializeUtils;

import java.util.Set;

@JsonPropertyOrder({"msgID", "resp", "id", "name"})
@JsonIgnoreProperties({"response"})
public class DeviceData implements ResponseData, MqttSerializable {

    @JsonProperty("msgID")
    private Long msgID;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("resp")
    private boolean isResponse;

    @JsonProperty("capabilities")
    private Set<CapabilityWholeData> capabilities;

    @JsonCreator
    public DeviceData(@JsonProperty("msgID") Long msgID,
                      @JsonProperty("id") Long id,
                      @JsonProperty("name") String name) {
        this.msgID = msgID;
        this.id = id;
        this.name = name;
    }

    public DeviceData(Long msgID, DeviceModel device) {
        this(msgID, device, false);
    }

    public DeviceData(Long msgID, DeviceModel device, boolean isResponse) {
        this.msgID = msgID;
        this.id = device.getID();
        this.name = device.getName();
        this.capabilities = CapabilityWholeData.fromModel(device.getCapabilities());
        this.isResponse = isResponse;
    }

    @JsonCreator
    public DeviceData(@JsonProperty("msgID") Long idMessage,
                      @JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("capabilities") Set<CapabilityWholeData> capabilities) {
        this(idMessage, id, name);
        this.capabilities = capabilities;
    }

    public Long getMsgID() {
        return msgID;
    }

    public void setMsgID(Long msgID) {
        this.msgID = msgID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CapabilityWholeData> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Set<CapabilityWholeData> capabilities) {
        this.capabilities = capabilities;
    }

    public static DeviceData fromJson(String json) {
        return MqttSerializeUtils.fromJson(json, DeviceData.class);
    }

    public DeviceModel toModel() {
        DeviceModel created = new DeviceModel(this.name, CapabilityWholeData.toModel(this.capabilities));
        created.setID(this.id);
        return created;
    }

    @Override
    public boolean isResponse() {
        return isResponse;
    }

    @Override
    public void setIsResponse(boolean state) {
        this.isResponse = state;
    }
}
