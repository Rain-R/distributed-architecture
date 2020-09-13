package com.framework.samples.dispatchapp.mapper;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 18:00
 * @since JDK 1.8
 */

import com.framework.samples.dispatchapp.entity.DispatchEntity;
import org.apache.ibatis.annotations.Insert;


public interface DispatchMapper {

    /**
     * 新增派单任务
     */
    @Insert("INSERT into platoon values (null,#{orderId},#{takeoutUserId});")
    public int insertDistribute(DispatchEntity distributeEntity);

}