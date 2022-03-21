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

/**
 * Spring event which is fired when external connections have been started.
 * See {@link Phase#EXTERNAL_CONNECTIONS} for more information.
 *
 * @since 4.6
 * @author Mitchell Herrijgers
 */
public class AxonLocalMessageHandlerRegistrationsFinishedEvent extends AxonLifecycleStartEvent {
    protected AxonLocalMessageHandlerRegistrationsFinishedEvent() {
        super(Phase.LOCAL_MESSAGE_HANDLER_REGISTRATIONS);
    }
}
