package org.ljieshuo.lfservermod.config;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;

public class LFModConfig {
    private final Gson gson = new Gson();
    private Config config;
    public void write() {
        Path path = FabricLoader.getInstance().getConfigDir();
        File config_File = new File(path.toString().concat("lfmod.json"));
        if(config_File.canWrite()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(config_File);
                fileOutputStream.write(gson.toJson(config).getBytes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void read() {
        Path path = FabricLoader.getInstance().getConfigDir();
        File config_File = new File(path.toString().concat("lfmod.json"));
        if(config_File.canRead()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(config_File);
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                config = gson.fromJson(new String(bytes), Config.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Config {
    List<User> users;
    class User {
        String username;
        String password;
        int level;
    }
}