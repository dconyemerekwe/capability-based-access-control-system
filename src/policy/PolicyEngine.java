package policy;

import capability.*;
import identity.*;
import request.*;
import resource.Resource;
import java.util.logging.Logger;

public class PolicyEngine {

    private static final Logger logger = Logger.getLogger(PolicyEngine.class.getName());
    private final CapabilityFactory capabilityFactory;

    public PolicyEngine(CapabilityFactory capabilityFactory) {
        this.capabilityFactory = capabilityFactory;
    }

    // decision logic for reading resource
    public Capability<Read> requestRead(User user, Resource resource) {
        Role role = user.getRole();
        Capability<Read> readToken = capabilityFactory.createReadToken(role, resource);

        if (readToken == null) {
            logger.warning(Decision.DENY + ": " + user.getUserName()
                    + " attempted to read " + resource.getClass().getSimpleName());
            return null;
        }

        if (resource.requiresOwnership(Read.class)) {
            if (!resource.isOwner(user)) {
                logger.warning(Decision.DENY_OWNERSHIP_REQUIRED + ": " + user.getUserName()
                        + " attempted to read " + resource.getClass().getSimpleName());
                return null;
            }
        }

        logger.info(Decision.ALLOW + ": " + user.getUserName()
                + " granted read access to " + resource.getClass().getSimpleName());

        return readToken;
    }

    // decision logic for writing resource
    public Capability<Write> requestWrite(User user, Resource resource) {
        Role role = user.getRole();
        Capability<Write> writeToken = capabilityFactory.createWriteToken(role, resource);

        if (writeToken == null) {
            logger.warning(Decision.DENY + ": " + user.getUserName()
                    + " attempted to write to " + resource.getClass().getSimpleName());
            return null;
        }

        if (resource.requiresOwnership(Write.class)) {
            if (!resource.isOwner(user)) {
                logger.warning(Decision.DENY_OWNERSHIP_REQUIRED + ": " + user.getUserName()
                        + " attempted to write to " + resource.getClass().getSimpleName());
                return null;
            }
        }

        logger.info(Decision.ALLOW + ": " + user.getUserName()
                + " granted write access to " + resource.getClass().getSimpleName());

        return writeToken;
    }
}