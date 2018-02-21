package info.tritusk.dirtball;

import org.slf4j.Logger;

import java.io.File;

public class DirtballPlatform {

    private Logger logger;

    public static File bukkitPluginsDirectory;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }

    public void unimplementedWarning(String methodInfo) {
        logger.warn("{} is not implemented yet at this moment", methodInfo);
    }
}
