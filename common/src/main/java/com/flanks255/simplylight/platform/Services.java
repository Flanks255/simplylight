package com.flanks255.simplylight.platform;

import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.platform.services.IPlatformHelper;
import com.flanks255.simplylight.platform.services.IRegistrationHelper;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IRegistrationHelper REGISTRATION = load(IRegistrationHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        SimplyLightCommon.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}