import audit.AuditLogger;
import capability.*;
import execution.*;
import identity.*;
import policy.*;
import request.*;
import resource.*;
import java.util.logging.Logger;

public class Main {
    // start log for scenarios
    private static final Logger logger = AuditLogger.setup();

    public static void main(String[] args) {
        // system init
        CapabilityFactory capabilityFactory = new CapabilityFactory();
        PolicyEngine policyEngine = new PolicyEngine(capabilityFactory);
        ExecutionService executionService = new ExecutionService();

        // test data
        User student1 = new User(1, "student1", Role.STUDENT);
        User teacher = new User(2, "teacher", Role.TEACHER);
        User student2 = new User(3, "student2", Role.STUDENT);
        User guest = new User(4, "guest", Role.GUEST);

        StudentRecords studentRecords = new StudentRecords(student1.getUserId());
        PublicAnnouncements publicAnnouncements = new PublicAnnouncements();

        Capability<Read> student1ReadToken = policyEngine.requestRead(student1, publicAnnouncements);

        if (student1ReadToken != null) {
            executionService.executeRead(student1ReadToken);
        }

        Capability<Read> guestReadToken = policyEngine.requestRead(guest, publicAnnouncements);

        if (guestReadToken != null) {
            executionService.executeRead(guestReadToken);
        }

        Capability<Read> teacherReadToken = policyEngine.requestRead(teacher, publicAnnouncements);

        if (teacherReadToken != null) {
            executionService.executeRead(teacherReadToken);
        }
    }
}