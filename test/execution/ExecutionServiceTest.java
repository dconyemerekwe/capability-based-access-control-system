package execution;

import capability.*;
import identity.*;
import request.*;
import resource.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExecutionServiceTest {

    ExecutionService underTest;
    CapabilityFactory capabilityFactory;

    // new ExecutionService & CapabilityFactory objects before each test
    @BeforeEach
    void setUp() {
        underTest = new ExecutionService();
        capabilityFactory = new CapabilityFactory();
    }

    @Test
    void executeReadShouldSuccessfullyExecuteValidReadToken() {
        // given
        Resource testResource = new Resource() {
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
        Role testRole = Role.GUEST;

        Capability<Read> validReadToken = capabilityFactory.createReadToken(testRole, testResource);

        // when & then
        assertDoesNotThrow(() -> {
            underTest.executeRead(validReadToken);
        });
    }

    @Test
    void executeWriteShouldSuccessfullyExecuteValidWriteToken() {
        // given
        Resource testResource = new Resource() {
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
        Role testRole = Role.TEACHER;

        Capability<Write> validWriteToken = capabilityFactory.createWriteToken(testRole, testResource);

        String testWrite = "Writeable";

        // when & then
        assertDoesNotThrow(() -> {
            underTest.executeWrite(validWriteToken, testWrite);
        });
    }

    @Test
    void executeReadShouldThrownAnExceptionWhenReadTokenIsNull() {
        // given
        Capability<Read> invalidReadToken = null;

        // when & then
        assertThrows(NullPointerException.class, () -> {
            underTest.executeRead(invalidReadToken);
        });
    }

    @Test
    void executeWriteShouldThrownAnExceptionWhenWriteTokenIsNull() {
        // given
        Capability<Write> invalidWriteToken = null;
        String testWrite = "Writeable";

        // when & then
        assertThrows(NullPointerException.class, () -> {
            underTest.executeWrite(invalidWriteToken, testWrite);
        });
    }
}