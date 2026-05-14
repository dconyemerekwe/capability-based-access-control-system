package capability;

import identity.*;
import request.*;
import resource.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class CapabilityFactoryTest {

    CapabilityFactory underTest;

    // new CapabilityFactory object before each test
    @BeforeEach
    void setUp() {
        underTest = new CapabilityFactory();
    }

    @Nested
    class GuestTests {

        @Test
        void guestPublicReadTokenShouldNotBeNull() {
            // given
            Role guestRole = Role.GUEST;

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
                public boolean isOwner(identity.User user) {
                    return false;
                }
            };

            // when
            Capability<Read> guestPublicReadToken = underTest.createReadToken(guestRole, publicResource);

            // then
            assertNotNull(guestPublicReadToken);
        }

        @Test
        void guestPublicWriteTokenShouldBeNull() {
            // given
            Role guestRole = Role.GUEST;

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
            Capability<Write> guestPublicWriteToken = underTest.createWriteToken(guestRole, publicResource);

            // then
            assertNull(guestPublicWriteToken);
        }

        @Test
        void guestInternalReadTokenShouldBeNull() {
            // given
            Role guestRole = Role.GUEST;

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
            Capability<Read> guestInternalReadToken = underTest.createReadToken(guestRole, internalResource);

            // then
            assertNull(guestInternalReadToken);
        }

        @Test
        void guestInternalWriteTokenShouldBeNull() {
            // given
            Role guestRole = Role.GUEST;

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
            Capability<Write> guestInternalWriteToken = underTest.createWriteToken(guestRole, internalResource);

            // then
            assertNull(guestInternalWriteToken);
        }

        @Test
        void guestConfidentialReadTokenShouldBeNull() {
            // given
            Role guestRole = Role.GUEST;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Read> guestConfidentialReadToken = underTest.createReadToken(guestRole, confidentialResource);

            // then
            assertNull(guestConfidentialReadToken);
        }
        @Test
        void guestConfidentialWriteTokenShouldBeNull() {
            // given
            Role guestRole = Role.GUEST;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Write> guestConfidentialWriteToken = underTest.createWriteToken(guestRole, confidentialResource);

            // then
            assertNull(guestConfidentialWriteToken);
        }
        }

    @Nested
    class StudentTests {

        @Test
        void studentPublicReadTokenShouldNotBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
            Capability<Read> studentPublicReadToken = underTest.createReadToken(studentRole, publicResource);

            // then
            assertNotNull(studentPublicReadToken);
        }

        @Test
        @DisplayName("No role is granted access to write to public scope to protect public resources")
        void studentPublicWriteTokenShouldBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
            Capability<Write> studentPublicWriteToken = underTest.createWriteToken(studentRole, publicResource);

            // then
            assertNull(studentPublicWriteToken);
        }

        @Test
        void studentInternalReadShouldNotBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
            Capability<Read> studentInternalReadToken = underTest.createReadToken(studentRole, internalResource);

            // then
            assertNotNull(studentInternalReadToken);
        }

        @Test
        void studentInternalWriteTokenShouldBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
            Capability<Write> studentInternalWriteToken = underTest.createWriteToken(studentRole, internalResource);

            // then
            assertNull(studentInternalWriteToken);
        }

        @Test
        void studentConfidentialReadOwnTokenShouldNotBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
                    return true; // ownership attributed
                }
            };

            // when
            Capability<Read> studentConfidentialOwnToken = underTest.createReadToken(studentRole, confidentialResource);

            // then
            assertNotNull(studentConfidentialOwnToken);
        }

        @Test
        @DisplayName("CapabilityFactory is the first gate of security, only providing RBAC; " +
                "Ownership checks are done later in the PolicyEngine.")
        void studentConfidentialReadNotOwnTokenShouldNotBeNull() {
            // given
            Role studentRole = Role.STUDENT;

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
            Capability<Read> studentConfidentialNotOwnReadToken = underTest.createReadToken(studentRole, confidentialResource);

            // then
            assertNotNull(studentConfidentialNotOwnReadToken);
        }

        @Test
        void studentConfidentialWriteTokenShouldBeNull() {
            // given
            Role studentRole = Role.STUDENT;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Write> studentConfidentialWriteToken = underTest.createWriteToken(studentRole, confidentialResource);

            // then
            assertNull(studentConfidentialWriteToken);
        }
    }

    @Nested
    class TeacherTests {

        @Test
        void teacherPublicReadTokenShouldNotBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

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
            Capability<Read> teacherPublicReadToken = underTest.createReadToken(teacherRole, publicResource);

            // then
            assertNotNull(teacherPublicReadToken);
        }

        @Test
        @DisplayName("No role is granted access to write to a public scope to protect public resources.")
        void teacherPublicWriteTokenShouldBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

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
            Capability<Write> teacherPublicWriteToken = underTest.createWriteToken(teacherRole, publicResource);

            // then
            assertNull(teacherPublicWriteToken);
        }

        @Test
        void teacherInternalReadTokenShouldNotBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

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
            Capability<Read> teacherInternalReadToken = underTest.createReadToken(teacherRole, internalResource);

            // then
            assertNotNull(teacherInternalReadToken);
        }

        @Test
        void teacherInternalWriteTokenShouldNotBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

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
            Capability<Write> teacherInternalWriteToken = underTest.createWriteToken(teacherRole, internalResource);

            // then
            assertNotNull(teacherInternalWriteToken);
        }

        @Test
        void teacherConfidentialReadTokenShouldNotBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

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
            Capability<Read> teacherConfidentialReadToken = underTest.createReadToken(teacherRole, confidentialResource);

            // then
            assertNotNull(teacherConfidentialReadToken);
        }

        @Test
        void teacherConfidentialWriteTokenShouldBeNull() {
            // given
            Role teacherRole = Role.TEACHER;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Write> teacherConfidentialWriteToken = underTest.createWriteToken(teacherRole, confidentialResource);

            // then
            assertNull(teacherConfidentialWriteToken);
        }
    }

    @Nested
    class AdminTests {

        @Test
        void adminPublicReadTokenShouldNotBeNull() {
            // given
            Role adminRole = Role.ADMIN;

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
            Capability<Read> adminPublicReadToken = underTest.createReadToken(adminRole, publicResource);

            // then
            assertNotNull(adminPublicReadToken);
        }

        @Test
        @DisplayName("No role is granted access to write to public scope to protect public resources.")
        void adminPublicWriteTokenShouldBeNull() {
            // given
            Role adminRole = Role.ADMIN;

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
            Capability<Write> adminPublicWriteToken = underTest.createWriteToken(adminRole, publicResource);

            // then
            assertNull(adminPublicWriteToken);
        }

        @Test
        void adminInternalReadTokenShouldNotBeNull() {
            // given
            Role adminRole = Role.ADMIN;

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
            Capability<Read> adminInternalReadToken = underTest.createReadToken(adminRole, internalResource);

            // then
            assertNotNull(adminInternalReadToken);
        }

        @Test
        void adminInternalWriteTokenShouldNotBeNull() {
            // given
            Role adminRole = Role.ADMIN;

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
            Capability<Write> adminInternalWriteToken = underTest.createWriteToken(adminRole, internalResource);

            // then
            assertNotNull(adminInternalWriteToken);
        }

        @Test
        void adminConfidentialReadTokenShouldNotBeNull() {
            // given
            Role adminRole = Role.ADMIN;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Read> adminConfidentialReadToken = underTest.createReadToken(adminRole, confidentialResource);

            // then
            assertNotNull(adminConfidentialReadToken);
        }

        @Test
        void adminConfidentialWriteTokenShouldNotBeNull() {
            // given
            Role adminRole = Role.ADMIN;

            Resource confidentialResource = new Resource() {
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
            };

            // when
            Capability<Write> adminConfidentialWriteToken = underTest.createWriteToken(adminRole, confidentialResource);

            // then
            assertNotNull(adminConfidentialWriteToken);
        }
    }
}