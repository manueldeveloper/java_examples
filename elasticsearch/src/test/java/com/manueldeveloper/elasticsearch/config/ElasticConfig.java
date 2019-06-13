package com.manueldeveloper.elasticsearch.config;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

public class ElasticConfig {

    private static final String ELASTIC_SERVER_VERSION = "6.2.2";
    private static EmbeddedElastic embeddedElastic;
    private static Integer tcpPort;

    public static void tearDown() {

        if(embeddedElastic != null) {

            embeddedElastic.stop();
            embeddedElastic = null;
        }
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {

            try {

                tcpPort = getRandomPort();

                TestPropertyValues
                        .of("spring.data.elasticsearch.cluster-nodes=localhost:" + tcpPort)
                        .applyTo(configurableApplicationContext);

                embeddedElastic = EmbeddedElastic.builder()
                        .withElasticVersion(ELASTIC_SERVER_VERSION)
                        .withSetting(PopularProperties.TRANSPORT_TCP_PORT, tcpPort)
                        .build();

                embeddedElastic.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private int getRandomPort() throws IOException {

            ServerSocket socket = null;

            try {
                socket = new ServerSocket(0);
                return socket.getLocalPort();
            } finally {
                if(socket != null) {
                    socket.close();
                }
            }
        }
    }
}
