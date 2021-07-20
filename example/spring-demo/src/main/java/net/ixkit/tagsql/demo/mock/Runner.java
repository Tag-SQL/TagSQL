/**
 * Copyright © 2020-2021 The sqljs Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ixkit.tagsql.demo.mock;

import net.ixkit.land.runtime.Tracer;
import net.ixkit.tagsql.demo.model.User;
import net.ixkit.tagsql.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @class:Runner
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 20/07/2021 
 * @version:0.1.0
 * @purpose:
 */
@Service
@Order(value = 1)
public class Runner implements CommandLineRunner {

    @Autowired
    DemoService service;

    public void testListByProvider() {

        List<User> list = service.listByProvider("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProvider Users?",list);
    }


    public void listByProviderSubFolder() {
        List<User> list = service.listByProviderSubFolder("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProvider Users?",list);
    }


    public void testListByProviderInResources() {
        List<User> list = service.listByProviderFileInResources("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProviderFileInResources Users?",list);
    }

    @Override
    public void run(String... args) throws Exception {
        this.testListByProvider();
    }
}
