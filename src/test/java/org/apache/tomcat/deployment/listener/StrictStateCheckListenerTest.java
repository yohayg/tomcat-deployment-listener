package org.apache.tomcat.deployment.listener;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.junit.Assert;
import org.junit.Test;


/**
 * <p>StrictStateCheckListenerTest class.</p>
 *
 * @author ygolan
 * @version $Id: $Id
 * @since 1.0.0
 */
public class StrictStateCheckListenerTest {

    /**
     * Should abort in case container is not in start mode
     */
    @Test(expected = IllegalStateException.class)
    public void lifecycleEventContainerFailed() {
        StandardServer lifecycle = new StandardServer();
        lifecycle.addService(getStandardService(LifecycleState.FAILED));
        lifecycle.addService(getStandardService(LifecycleState.STARTED));
        lifecycle.addService(getStandardService(LifecycleState.STARTED));
        LifecycleEvent event = new LifecycleEvent(lifecycle, Lifecycle.AFTER_START_EVENT, new Object());
        StrictStateCheckListener listener = new StrictStateCheckListener();

        listener.lifecycleEvent(event);

    }

    /**
     * Should abort in case container is not in start mode
     */
    @Test
    public void lifecycleEventStarted() {
        StandardServer lifecycle = new StandardServer();
        lifecycle.addService(getStandardService(LifecycleState.STARTED));
        lifecycle.addService(getStandardService(LifecycleState.STARTED));
        lifecycle.addService(getStandardService(LifecycleState.STARTED));
        LifecycleEvent event = new LifecycleEvent(lifecycle, Lifecycle.AFTER_START_EVENT, new Object());
        StrictStateCheckListener listener = new StrictStateCheckListener();

        listener.lifecycleEvent(event);
        Assert.assertTrue(true);

    }

    private StandardService getStandardService(LifecycleState state) {
        StandardService service = new StandardService();
        StateTestContainer container = new StateTestContainer();
        container.setName("8005");
        container.setTestState(state);
        service.setContainer(container);
        return service;
    }
}
