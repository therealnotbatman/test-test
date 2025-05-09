package com.tanio.test_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  @Mock
  PhoneCall phoneCall;
  @Mock
  Phone phone;
  @Mock
  Agent agent;
  @InjectMocks
  Application sut;

  @Test
  void handleNullPhoneCall() {
    assertThatThrownBy(() -> sut.receiveCall(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Call cannot be null").doesNotHaveToString("Baba");
  }

  @Test
  void whenCallIsForSupportAndAgentIsSupportThenCallIsAnsweredAndPhoneConnectedToAgent() {
    when(phoneCall.isForSupport()).thenReturn(true);
    when(agent.isSupportAgent()).thenReturn(true);

    sut.receiveCall(phoneCall);

    verify(phoneCall).answer();
    verify(phone).connectToAgent();
  }
}