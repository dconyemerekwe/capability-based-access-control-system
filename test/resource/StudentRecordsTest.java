package resource;

import identity.*;
import request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentRecordsTest {

    StudentRecords underTest;
    User student1;
    User student2;

    // setting a student user and ownership id before each test
    @BeforeEach
    void setUp() {
        int ownerId = 5;
        underTest = new StudentRecords(ownerId);

        student1 = new User(ownerId, "student1", Role.STUDENT);
        student2 = new User(4, "student2", Role.STUDENT);
    }

    @Test
    void isOwnerShouldReturnTrueWhenMatchedWithRecordOwnerId() {
        // when & then
        assertTrue(underTest.isOwner(student1));
    }

    @Test
    void isOwnerShouldReturnFalseWhenUserIdDoesNotMatch() {
        // when & then
        assertFalse(underTest.isOwner(student2));
    }

    @Test
    void isOwnerShouldReturnTrueForTeacherRegardlessOfId() {
        // given
        User teacher = new User(10, "teacher", Role.TEACHER);

        // when & then
        assertTrue(underTest.isOwner(teacher));
    }

    @Test
    void isOwnerShouldReturnTrueForAdminRegardlessOfId() {
        // given
        User admin = new User(10, "admin", Role.ADMIN);

        // when & then
        assertTrue(underTest.isOwner(admin));
    }

    @Test
    void requiresOwnershipShouldBeTrueForReadAction() {
        // when & then
        assertTrue(underTest.requiresOwnership(Read.class));
    }

    @Test
    void requiresOwnershipShouldReturnTrueForWriteAction() {
        // when & then
        assertTrue(underTest.requiresOwnership(Write.class));
    }
}