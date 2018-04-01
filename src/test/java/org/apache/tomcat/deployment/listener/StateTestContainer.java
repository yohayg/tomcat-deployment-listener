package org.apache.tomcat.deployment.listener;

import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;

/**
 * <p>StateTestContainer class.</p>
 *
 * @author ygolan
 * @version $Id: $Id
 * @since 1.0.0
 */
public class StateTestContainer extends StandardContext {

    private LifecycleState state;

    /**
     * <p>setTestState.</p>
     *
     * @param state a {@link org.apache.catalina.LifecycleState} object.
     */
    public void setTestState(LifecycleState state) {

        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LifecycleState getState() {
        return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStateName() {
        return state.toString();
    }
}
