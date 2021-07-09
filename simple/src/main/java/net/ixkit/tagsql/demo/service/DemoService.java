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
package net.ixkit.tagsql.demo.service;

import net.ixkit.tagsql.core.TagSQLEngine;
import net.ixkit.tagsql.demo.dao.UserRowMapper;
import net.ixkit.tagsql.demo.model.User;
import net.ixkit.tagsql.demo.sql.DemoDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * @class:DemoService
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 02/07/2021 
 * @version:0.1.0
 * @purpose:Demo Service
 */
@Service
public class DemoService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<User> list(Object ... args){
        String buf = DemoDataProvider.list.getTagSql();

        String sql = TagSQLEngine.invoke(buf).fill(args).getSQL();

        List<User> result = jdbcTemplate.query(sql,new UserRowMapper());

        return  result;
    }
}
