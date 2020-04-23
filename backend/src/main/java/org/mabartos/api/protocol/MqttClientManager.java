package org.mabartos.api.protocol;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttClientManager {

    boolean initAllClients();

    boolean destroyAllClients();

    boolean initClient(Long idHome);

    boolean shutdownClient(Long idHome);

    BartMqttClient getMqttForHome(Long idHome);

    static void clearRetainedMessages(BartMqttClient client, String topic) {
        if (client != null) {
            MqttMessage message = new MqttMessage(new byte[0]);
            message.setRetained(true);
            client.publish(topic, message);
        }
    }
}
