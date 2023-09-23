package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:www.itheima.com
 * @Description:Brand的Dao
 *****/
public interface BrandMapper extends Mapper<Brand> {
    @Select("select tb.* from tb_category_brand tcb,tb_brand tb where" +
            " tcb.category_id=#{category} and tb.id=tcb.brand_id")
    List<Brand> findByCategory(Integer categoryid);
}
