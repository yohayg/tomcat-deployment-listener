package org.apache.tomcat.deployment.listener;

import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;

public class StateTestContainer extends StandardContext {

    private LifecycleState state;

    public void setTestState(LifecycleState state) {

        this.state = state;
    }

    @Override
    public LifecycleState getState() {
        return state;
    }

    @Override
    public String getStateName() {
        return state.toString();
    }
}
