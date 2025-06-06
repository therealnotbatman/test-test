package com.tanio.test_test;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Application {
    private final Agent agent;
    private final Phone phone;

    void receiveCallInEnglish(PhoneCall call, String language) {
        if (call == null) {
            throw new IllegalArgumentException("Call cannot be null");
        }

        if (call.isForSupport()) {
            if (agent.isSupportAgent(language)) {
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

    public Boolean isSupportAgent(String language) {
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