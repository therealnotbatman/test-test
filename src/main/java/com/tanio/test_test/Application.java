package com.tanio.test_test;

import lombok.RequiredArgsConstructor;

// TODO: Use ArgumentCaptors
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
                return;
            }

            else {
                call.forwardToSupport();
                return;
            }
        }

        if (call.isForSales()) {
            if (agent.isSalesAgent()) {
                call.answer();
                phone.connectToAgent();
                return;
            }

            else {
                call.forwardToSales();
                return;
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

    public Boolean isSupportAgent() {
        return false;
    }

    public Boolean isSalesAgent() {
        return false;
    }
}

class PhoneCall {

    public Boolean isForSupport() {
        return false;
    }

    public void answer() {
        System.out.println("hello");
    }

    public void forwardToSupport() {

    }

    public Boolean isForSales() {
        return false;
    }

    public void forwardToSales() {
    }
}

class Anything{

    public void doSomething() {

    }
}