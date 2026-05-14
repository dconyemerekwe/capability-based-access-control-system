package resource;

import identity.User;
import request.Action;

public class AuditLogs implements Resource {
    @Override
    public Scope getScope() {
        return Scope.CONFIDENTIAL;
    }

    @Override
    public boolean requiresOwnership(Class<? extends Action> action) {
        return false;
    }

    @Override
    public boolean isOwner(User user) {
        return false;
    }
}
