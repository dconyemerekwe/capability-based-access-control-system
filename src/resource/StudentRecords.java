package resource;

import identity.*;
import request.Action;

public class StudentRecords implements Resource {
    private int ownerUserId;

    public StudentRecords(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Override
    public Scope getScope() {
        return Scope.CONFIDENTIAL;
    }

    @Override
    public boolean requiresOwnership(Class<? extends Action> action) {
        return true;
    }

    @Override
    public boolean isOwner(User user) {
        Role role = user.getRole();

        if (role == Role.TEACHER || role == Role.ADMIN) {
            return true;
        }

        return ownerUserId == user.getUserId();
    }
}
