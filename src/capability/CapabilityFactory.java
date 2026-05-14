package capability;

import java.util.HashMap;
import java.util.Set;
import identity.Role;
import request.*;
import resource.Resource;
import resource.Scope;

public class CapabilityFactory {

    // main dictionary: each role is mapped to its allowed actions per scope
    private final HashMap<Role, HashMap<Scope, Set<Class<? extends Action>>>> roleCapabilities = new HashMap<>();

    public CapabilityFactory() {

        // adding capabilities for GUEST
        HashMap<Scope, Set<Class<? extends Action>>> guestCapabilities = new HashMap<>();
        guestCapabilities.put(Scope.PUBLIC, Set.of(Read.class));

        // update main dictionary: mapping GUEST role is its capabilities
        roleCapabilities.put(Role.GUEST, guestCapabilities);

        // adding capabilities for STUDENT
        HashMap<Scope, Set<Class<? extends Action>>> studentCapability = new HashMap<>();
        studentCapability.put(Scope.PUBLIC, Set.of(Read.class));
        studentCapability.put(Scope.INTERNAL, Set.of(Read.class));
        studentCapability.put(Scope.CONFIDENTIAL, Set.of(Read.class));

        // update main dictionary: mapping GUEST role is its capabilities
        roleCapabilities.put(Role.STUDENT, studentCapability);

        // adding capabilities for TEACHER
        HashMap<Scope, Set<Class<? extends Action>>> teacherCapabilities = new HashMap<>();
        teacherCapabilities.put(Scope.PUBLIC, Set.of(Read.class));
        teacherCapabilities.put(Scope.INTERNAL, Set.of(Read.class, Write.class));
        teacherCapabilities.put(Scope.CONFIDENTIAL, Set.of(Read.class));

        roleCapabilities.put(Role.TEACHER, teacherCapabilities);

        // adding capabilities for ADMIN
        HashMap<Scope, Set<Class<? extends Action>>> adminCapabilities = new HashMap<>();
        adminCapabilities.put(Scope.PUBLIC, Set.of(Read.class));
        adminCapabilities.put(Scope.INTERNAL, Set.of(Read.class, Write.class));
        adminCapabilities.put(Scope.CONFIDENTIAL, Set.of(Read.class, Write.class));

        roleCapabilities.put(Role.ADMIN, adminCapabilities);
    }

    // token creation: issued if the role is authorised to read for the resource's scope
    public Capability<Read> createReadToken(Role role, Resource resource) {
        Scope scope = resource.getScope();
        HashMap<Scope, Set<Class<? extends Action>>> roleAccessRules = roleCapabilities.get(role);

        if (roleAccessRules != null && roleAccessRules.getOrDefault(scope, Set.of()).contains(Read.class)) {
            return new Capability<Read>(resource);
        }

        return null;
    }

    // token creation: issued if the role is authorised to write for the resource's scope
    public Capability<Write> createWriteToken(Role role, Resource resource) {
        Scope scope = resource.getScope();
        HashMap<Scope, Set<Class<? extends Action>>> roleAccessRules = roleCapabilities.get(role);

        if (roleAccessRules != null && roleAccessRules.getOrDefault(scope, Set.of()).contains(Write.class)) {
            return new Capability<Write>(resource);
        }

        return null;
    }
}
