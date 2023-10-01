package org.ljieshuo.lfservermod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("lfsm");

    @Override
    public void onInitialize() {
        LOGGER.info("LF-Server-Mod is loaded");

    }
}
