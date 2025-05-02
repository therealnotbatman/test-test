package com.tanio.test_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    @Test
    void receiveCall() {
        Agent agent = new Agent();
        Phone phone = new Phone();
        Application application = new Application(agent, phone);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> application.receiveCall(null));
        assertEquals("Call cannot be null", e.getMessage());
    }

    @Test
    void receiveCall2() {
        Agent agent = new Agent();
        Phone phone = new Phone();
        Application application = new Application(agent, phone);
        try {
            application.receiveCall(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Call cannot be null", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void receiveCall3() {
        Agent agent = new Agent();
        Phone phone = new Phone();
        Application application = new Application(agent, phone);
       assertThatThrownBy(() -> application.receiveCall(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Call cannot be null").doesNotHaveToString("Baba");
    }
}