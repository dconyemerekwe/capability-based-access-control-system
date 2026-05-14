package capability;

import request.Action;
import resource.Resource;

public final class Capability<T extends Action> {
    private final Resource resource;

    public Capability(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
