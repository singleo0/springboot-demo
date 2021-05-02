package com.singleo.springboot.app.dao;

import com.singleo.springboot.app.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyDao {

    public List<Company> findAll();

    @Select("select * from company")
    public List<Company> selectAll();

    @Select("select id, name, description from company where id = #{id}")
    Company selectById(@Param("id") long id);

}
