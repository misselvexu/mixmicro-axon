/*
 * Copyright (c) 2022. Axon Framework
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.axonframework.spring.lifecycle;

import org.axonframework.lifecycle.Phase;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Event published during initialization of Axon through the Spring event mechanism. The different phases are defined in
 * the {@link Phase} class. Each of the phases has its own Spring event implementation.
 *
 * @author Mitchell Herrijgers
 * @since 4.6
 */
public abstract class AxonLifecycleStartEvent {

    private final int phase;

    /**
     * Constructs the event, taking in the {@link Phase} it represents.
     *
     * @param phase The {@link Phase} the event represents.
     */
    protected AxonLifecycleStartEvent(int phase) {
        this.phase = phase;
    }

    /**
     * @return The {@link Phase} associated with this event
     */
    public int getPhase() {
        return phase;
    }

    /**
     * Creates the appropriate event instances for the given {@link Phase}.
     *
     * @param phase The {@link Phase} to construct an event for
     * @return The {@link AxonLifecycleStartEvent} concrete class
     */
    public static List<AxonLifecycleStartEvent> createStartEventsForPhase(int phase) {
        switch (phase) {
            case Phase.EXTERNAL_CONNECTIONS:
                return singletonList(new AxonExternalConnectionsStartedEvent());
            case Phase.OUTBOUND_EVENT_CONNECTORS:
                return singletonList(new AxonOutboundEventConnectorsStartedEvent());
            case Phase.LOCAL_MESSAGE_HANDLER_REGISTRATIONS:
                return Arrays.asList(
                        new AxonLocalMessageHandlerRegistrationsFinishedEvent(),
                        new AxonOutboundCommandConnectorsStartedEvent(),
                        new AxonOutboundQueryConnectorsStartedEvent()
                );
            case Phase.INBOUND_COMMAND_CONNECTOR:
                return Arrays.asList(
                        new AxonInboundCommandConnectorsStartedEvent(),
                        new AxonInboundQueryConnectorsStartedEvent()
                );
            case Phase.INBOUND_EVENT_CONNECTORS:
                return singletonList(new AxonInboundEventConnectorsStartedEvent());
            case Phase.INSTRUCTION_COMPONENTS:
                return singletonList(new AxonInstructionComponentsStartedEvent());
            default:
                throw new IllegalArgumentException(String.format("Phase %d is unknown.", phase));
        }
    }
}
