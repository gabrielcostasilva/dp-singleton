package com.gabrielcostasilva;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sfn.SfnClient;

public class AWSSingletonSDK {

    private static AWSSingletonSDK instance = null;
    private SfnClient client = null;

    private AWSSingletonSDK() {
        client = SfnClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public static synchronized SfnClient getInstance() {
        if (instance == null) {
            instance = new AWSSingletonSDK();
        }
        return instance.client;
    }
}
