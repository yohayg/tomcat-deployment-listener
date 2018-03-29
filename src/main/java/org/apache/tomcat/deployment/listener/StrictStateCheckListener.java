package org.apache.tomcat.deployment.listener;

import org.apache.catalina.*;

/**
 * This listener aborts tomcat if the a webapp is failed to be deployed
 * It is for docker container use as best practice to make sure that the container will
 * not start in case the webapp fail to deploy
 * This listener is for use in tomcat containers by adding it to:
 * ${CATALINA_HOME}/conf/server.xml
 */
public class StrictStateCheckListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        String type = event.getType();
        Lifecycle lifecycle = event.getLifecycle();


        if (lifecycle instanceof Server && type.equals(Lifecycle.AFTER_START_EVENT)) {
            Server server = (Server) lifecycle;
            boolean hasFailures = false;
            String containerState = null;

            // Check status of each container
            StringBuilder builder = new StringBuilder("Status:").append(System.lineSeparator());
            for (Service service : server.findServices()) {
                Container container = service.getContainer();
                String className = container.getClass().getSimpleName();
                String stateName = container.getStateName();
                String name = container.getName();
                if (LifecycleState.STARTED != container.getState()) {
                    hasFailures = true;
                    containerState = String.format("%s[%s]: %s%n", className, container.getName(), stateName);
                }

                builder.append(className).append("[").append(name).append("]: ").append(stateName).append(System.lineSeparator());
            }
            System.out.println(builder.toString());
            if (hasFailures) {
                /*
                 * The server will not be stopped by this listener. Indeed,
                 * an exception is raised to trigger shutdown hook. See:
                 * `org.apache.catalina.startup.Catalina`.
                 */
                throw new IllegalStateException(containerState);
            }
        }
    }
}
