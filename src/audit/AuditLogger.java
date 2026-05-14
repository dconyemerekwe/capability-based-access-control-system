package audit;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AuditLogger {
    private static final Logger logger = Logger.getLogger("");

    public static Logger setup() {
        try {
            FileHandler fileHandler = new FileHandler("audit_trail.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return logger;
    }

}
