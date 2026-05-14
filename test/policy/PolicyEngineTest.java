package policy;

import capability.*;
import identity.*;
import request.*;
import resource.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolicyEngineTest {

    PolicyEngine underTest;
    CapabilityFactory capabilityFactory;

    // new CapabilityFactory & PolicyEngine objects before each test
    @BeforeEach
    void setUp() {
        capabilityFactory = new CapabilityFactory();
        underTest = new PolicyEngine(capabilityFactory);
    }

    @Test
    void requestReadShouldReturnATokenForStudentWithOwnershipToReadTheirRecord() {
        // given
        User studentOwner = new User(1, "student1", Role.STUDENT);
        Resource confidentialResource = new Resource() {
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
                return true;
            }
        };

        // when
        Capability<Read> validReadToken = underTest.requestRead(studentOwner, confidentialResource);

        // then
        assertNotNull(validReadToken);
    }

    @Test
    void requestReadShouldReturnNullForStudentWithoutOwnershipToReadARecord() {
        // given
        User studentNotOwner = new User(2, "student2", Role.STUDENT);
        Resource confidentialResource = new Resource() {
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
                return false;
            }
        };

        // when
        Capability<Read> invalidReadToken = underTest.requestRead(studentNotOwner, confidentialResource);

        // then
        assertNull(invalidReadToken);
    }

    @Test
    void requestReadShouldReturnATokenWhenOwnershipNotNeeded() {
        // given
        User teacher = new User(1, "teacher", Role.TEACHER);
        Resource publicResource = new Resource() {
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
        };

        // when
        Capability<Read> validToken = underTest.requestRead(teacher, publicResource);

        // then
        assertNotNull(validToken);
    }

    @Test
    void requestReadShouldReturnNullWhenTokenIsDeniedByCapabilityFactory() {
        // given
        User guest = new User(1, "guest", Role.GUEST);
        Resource internalResource = new Resource() {
            @Override
            public Scope getScope() {
                return Scope.INTERNAL;
            }

            @Override
            public boolean requiresOwnership(Class<? extends Action> action) {
                return false;
            }

            @Override
            public boolean isOwner(User user) {
                return false;
            }
        };

        // when
        Capability<Read> invalidToken = underTest.requestRead(guest, internalResource);

        // then
        assertNull(invalidToken);
    }

    @Test
    void requestWriteShouldReturnATokenForUserWithOwnership() {
        // given
        User admin = new User(1, "admin", Role.ADMIN);
        Resource confidentialResource = new Resource() {
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
                return true;
            }
        };

        // when
        Capability<Write> validReadToken = underTest.requestWrite(admin, confidentialResource);

        // then
        assertNotNull(validReadToken);
    }

    @Test
    void requestWriteShouldReturnNullForUserWithoutOwnership() {
        // given
        User teacher = new User(2, "teacher", Role.TEACHER);
        Resource confidentialResource = new Resource() {
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
                return false;
            }
        };

        // when
        Capability<Write> invalidReadToken = underTest.requestWrite(teacher, confidentialResource);

        // then
        assertNull(invalidReadToken);
    }

    @Test
    void requestWriteShouldReturnATokenWhenOwnershipNotNeeded() {
        // given
        User teacher = new User(1, "teacher", Role.TEACHER);
        Resource internalResource = new Resource() {
            @Override
            public Scope getScope() {
                return Scope.INTERNAL;
            }

            @Override
            public boolean requiresOwnership(Class<? extends Action> action) {
                return false;
            }

            @Override
            public boolean isOwner(User user) {
                return false;
            }
        };

        // when
        Capability<Write> validToken = underTest.requestWrite(teacher, internalResource);

        // then
        assertNotNull(validToken);
    }

    @Test
    void requestWriteShouldReturnNullWhenTokenIsDeniedByCapabilityFactory() {
        // given
        User admin = new User(1, "admin", Role.ADMIN);
        Resource publicResource = new Resource() {
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
        };

        // when
        Capability<Write> invalidToken = underTest.requestWrite(admin, publicResource);

        // then
        assertNull(invalidToken);
    }
}