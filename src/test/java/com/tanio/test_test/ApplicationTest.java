package com.tanio.test_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  @Test
  void whenCallIsNullThenThrowException() {
    Agent agent = new Agent();
    Phone phone = new Phone();
    Application application = new Application(agent, phone);

    assertThatThrownBy(() -> application.receiveCall(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Call cannot be null");
  }
}