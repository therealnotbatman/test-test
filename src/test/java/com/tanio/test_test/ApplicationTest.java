package com.tanio.test_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  @Spy
  PhoneCall phoneCall = new PhoneCall();
  @Mock
  Phone phone;
  @Mock
  Agent agent;
  @InjectMocks
  Application sut;

  @Test
  void handleNullPhoneCall() {
    assertThatThrownBy(() -> sut.receiveCallInEnglish(null, "english"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Call cannot be null").doesNotHaveToString("Baba");
  }

  @Test
  void whenCallIsForSupportAndAgentIsSupportThenCallIsAnsweredAndPhoneConnectedToAgentInEnglish() {
    when(phoneCall.isForSupport()).thenReturn(true);
    when(agent.isSupportAgent("english")).thenReturn(true);

    sut.receiveCallInEnglish(phoneCall, "english");

    verify(phoneCall).answer();
    verify(phone).connectToAgent();
  }

  @Test
  void whenCallIsForSupportAndAgentIsSupportThenCallIsAnsweredAndPhoneConnectedToAgentInItalian() {
    when(phoneCall.isForSupport()).thenReturn(true);
    when(agent.isSupportAgent("italian")).thenReturn(true);

    sut.receiveCallInEnglish(phoneCall, "italian");

    verify(phoneCall).answer();
    verify(phone).connectToAgent();
  }

  @Test
  void whenCallIsForSupportAndAgentIsNotSupportThenCallIsForwardedToSupport() {
    when(phoneCall.isForSupport()).thenReturn(true);
    when(agent.isSupportAgent("english")).thenReturn(false);

    sut.receiveCallInEnglish(phoneCall, "english");

    verify(phoneCall).forwardToSupport();
    verify(phone, never()).connectToAgent(); //method is never called
    verifyNoInteractions(phone); // no interactions with phone object
  }

  @Test
  void whenCallIsSaleAndAgentIsSalesThenCallIsAnsweredAndPhoneConnectedToAgent() {
    when(phoneCall.isForSales()).thenReturn(true);
    when(agent.isSalesAgent()).thenReturn(true);

    sut.receiveCallInEnglish(phoneCall, "english");

    verify(phoneCall).answer();
    verify(phone).connectToAgent();
  }

  @Test
  void whenCallIsSaleAndAgentIsNotSalesThenCallIsForwardedToSales() {
    when(phoneCall.isForSales()).thenReturn(true);
    when(agent.isSalesAgent()).thenReturn(false);

    sut.receiveCallInEnglish(phoneCall, "english");

    verify(phoneCall).forwardToSales();
    verify(phone, never()).connectToAgent(); //method is never called
    verifyNoInteractions(phone); // no interactions with phone object
  }

  @Test
  void whenCallIsNotForSupportAndNotSalesThenExceptionIsThrown() {
    when(phoneCall.isForSupport()).thenReturn(false);
    when(phoneCall.isForSales()).thenReturn(false);

    assertThatThrownBy(() -> sut.receiveCallInEnglish(phoneCall, "english"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("We don't know where to forward this call");
  }
}