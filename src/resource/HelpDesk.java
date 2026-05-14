package resource;

import identity.User;
import request.Action;

public class HelpDesk implements Resource {

    @Override
    public Scope getScope() {
        return Scope.PUBLIC;
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