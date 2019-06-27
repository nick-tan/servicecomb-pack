/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.pack.alpha.fsm;

import akka.actor.ActorSystem;
import com.google.common.eventbus.EventBus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.servicecomb.pack.alpha.fsm.event.consumer.SagaEventConsumer;
import org.apache.servicecomb.pack.alpha.fsm.spring.integration.eventbus.EventSubscribeBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"alpha.model.actor.enabled"})
public class FsmAutoConfiguration {

  @Bean
  public ActorSystem actorSystem() {
    ActorSystem system = ActorSystem.create("alpha-akka", akkaConfiguration());
    return system;
  }

  @Bean
  public Config akkaConfiguration() {
    return ConfigFactory.load();
  }

  @Bean(name = "sagaEventBus")
  public EventBus sagaEventBus() {
    return new EventBus();
  }

  @Bean
  public SagaEventConsumer sagaEventConsumer(){
    return new SagaEventConsumer();
  }

  @Bean
  public EventSubscribeBeanPostProcessor eventSubscribeBeanPostProcessor(){
    return new EventSubscribeBeanPostProcessor();
  }

}
