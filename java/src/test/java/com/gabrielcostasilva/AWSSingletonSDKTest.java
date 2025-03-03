package com.gabrielcostasilva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.sfn.SfnClient;

import static org.junit.jupiter.api.Assertions.assertSame;

public class AWSSingletonSDKTest {

    @Test
    @DisplayName("Test returns the same instance")
    public void testSingletonSameInstance() {
        var instance1 = AWSSingletonSDK.getInstance();
        var instance2 = AWSSingletonSDK.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    @DisplayName("Test not null")
    public void testSingletonNotNullInstance() {
        var instance = AWSSingletonSDK.getInstance();
        assertTrue(instance != null, "The operation should return true");
    }

    @Test
    @DisplayName("Test the AWS Step Functions client")
    public void testSFClient() {
        var instance = AWSSingletonSDK.getInstance();
        assertTrue(instance instanceof SfnClient, "The operation should return true");
    }
}
