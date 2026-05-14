package resource;

import identity.User;
import request.Action;

public interface Resource {
    Scope getScope();
    boolean requiresOwnership(Class<? extends Action> action);
    boolean isOwner(User user);
}