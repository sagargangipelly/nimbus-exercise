/**
 *  Copyright 2016-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.antheminc.oss.nimbus.domain.model.state.event;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import com.antheminc.oss.nimbus.domain.EventHandler;
import com.antheminc.oss.nimbus.domain.defn.event.StateEvent.OnStateChange;
import com.antheminc.oss.nimbus.domain.defn.event.StateEvent.OnStateLoad;
import com.antheminc.oss.nimbus.domain.defn.event.StateEvent.OnTxnExecute;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.ExecutionModel;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.domain.model.state.ExecutionTxnContext;
import com.antheminc.oss.nimbus.domain.model.state.ParamEvent;

/**
 * @author Soham Chakravarti
 *
 */
public final class StateEventHandlers {

	@EventHandler(OnStateLoad.class)
	public interface OnStateLoadHandler<A extends Annotation> {
		
		public void handle(A configuredAnnotation, Param<?> param);
	}
	
	
	@EventHandler(OnStateChange.class)
	public interface OnStateChangeHandler<A extends Annotation> {
		
		public void handle(A configuredAnnotation, ExecutionTxnContext txnCtx, ParamEvent event);
	}
	
	
	@EventHandler(OnTxnExecute.class)
	public interface OnTxnExecuteHandler<A extends Annotation> {
		
		public void handleOnStart(A configuredAnnotation, ExecutionTxnContext txnCtx);
		
		public void handleOnStop(A configuredAnnotation, ExecutionTxnContext txnCtx, Map<ExecutionModel<?>, List<ParamEvent>> aggregatedEvents);
	}
}
