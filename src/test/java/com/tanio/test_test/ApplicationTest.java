package com.tanio.test_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  @Test
  void handleNullPhoneCall() {
    Agent agent = new Agent();
    Phone phone = new Phone();
    Application application = new Application(agent, phone);
    assertThatThrownBy(() -> application.receiveCall(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Call cannot be null").doesNotHaveToString("Baba");
  }

  @Test
  void whenCallIsForSupportAndAgentIsSupportThenCallIsAnsweredAndPhoneConnectedToAgent() {

    PhoneCall phoneCall = Mockito.mock(PhoneCall.class);
    when(phoneCall.isForSupport()).thenReturn(true);

    Phone phone = Mockito.mock(Phone.class);

    Agent mockAgent = Mockito.mock(Agent.class);
    when(mockAgent.isSupportAgent()).thenReturn(true);

    Application application = new Application(mockAgent, phone);

    application.receiveCall(phoneCall);

    Mockito.verify(phoneCall).answer();
    Mockito.verify(phone).connectToAgent();

  }

}