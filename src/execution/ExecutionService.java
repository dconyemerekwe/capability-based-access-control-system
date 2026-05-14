package execution;

import capability.Capability;
import request.*;
import resource.Resource;
import java.util.logging.Logger;

public class ExecutionService {

    private static final Logger logger = Logger.getLogger(ExecutionService.class.getName());

    public void executeRead(Capability<Read> readToken) {
        Resource resource = readToken.getResource();

        logger.info("[EXECUTION] Reading from resource: " + resource.getClass().getSimpleName());
    }

    public void executeWrite(Capability<Write> writeToken, String userWrite) {
        Resource resource = writeToken.getResource();

        logger.info("[EXECUTION] Writing '" + userWrite + "' to resource: " + resource.getClass().getSimpleName());
    }
}