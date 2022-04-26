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
package org.ixkit.tagsql.demo;

import org.ixkit.land.runtime.Tracer;
//import org.ixkit.tagsql.demo.config.FactoryConfig;
import org.ixkit.tagsql.demo.config.MyDataSourceConfig;
import org.ixkit.tagsql.demo.model.User;
import org.ixkit.tagsql.demo.service.DemoService;
import org.ixkit.tagsql.springboot.config.Options;
import org.ixkit.tagsql.springboot.config.meta.EnableTagSQLAutoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @class:DemoServiceTest
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 06/07/2021 
 * @version:0.1.0
 * @purpose:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"org.ixkit.tagsql.demo"})
@TestPropertySource(locations="classpath:application.yml")
@ContextConfiguration( classes = {MyDataSourceConfig.class},
        loader = AnnotationConfigContextLoader.class  )
@EnableConfigurationProperties({Options.class})
@EnableTagSQLAutoConfig
public class DemoServiceTestUnFixed {

    @Autowired
    DemoService service;

    @Test
    public void testList() {
        List<User> list = service.list("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"Users?",list);
    }

    @Test
    public void testListWithNullParam() {
        List<User> list = service.list("firstName",null,"beginDate","1888-1-1", "endDate","2222-12-30", "score",null);
        Tracer.debug(this,"Users?",list);
    }
    @Test
    public void testListWithNullDate() {
        List<User> list = service.list( "beginDate",null, "endDate",null);
        Tracer.debug(this,"Users?",list);
    }
    @Autowired
    private Options options ;
    ////
    @Test
    public void testListByProvider() {
        Tracer.debug(this,"options",options);
        List<User> list = service.listByProvider("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProvider Users?",list);
    }

    @Test
    public void listByProviderSubFolder() {
        List<User> list = service.listByProviderSubFolder("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProvider Users?",list);
    }

    @Test
    public void testListByProviderInResources() {
       List<User> list = service.listByProviderFileInResources("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
        Tracer.debug(this,"listByProviderFileInResources Users?",list);
    }
}
