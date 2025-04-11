package com.tanio.test_test;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
class Application {
    private final Agent agent;
    private final Phone phone;

    void receiveCall(PhoneCall call) {
        if (call == null) {
            throw new IllegalArgumentException("Call cannot be null");
        }

        if (call.isForSupport()) {
            if (agent.isSupportAgent()) {
                call.answer();
                phone.connectToAgent();
            }

            else {
                call.forwardToSupport();
            }
        }

        if (call.isForSales()) {
            if (agent.isSalesAgent()) {
                call.answer();
                phone.connectToAgent();
            }

            else {
                call.forwardToSales();
            }
        }

        throw new IllegalArgumentException("We don't know where to forward this call");
    }
}

class Phone {

    public void connectToAgent() {

    }
}

class Agent {

    public boolean isSupportAgent() {
        return false;
    }

    public boolean isSalesAgent() {
        return false;
    }
}

class PhoneCall {

    public boolean isForSupport() {
        return false;
    }

    public void answer() {

    }

    public void forwardToSupport() {

    }

    public boolean isForSales() {
        return false;
    }

    public void forwardToSales() {
    }
}