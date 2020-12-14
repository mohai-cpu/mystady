package com.taikang.oauth2.repository;

import com.taikang.oauth2.entity.TitanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author:itw_zhangjian04
 * @Date： 2020/11/2514:17
 */
public interface TitanUserRepository extends JpaRepository<TitanUser, Long> {
   @Query(value = "SELECT * from titan_user  where username =?1", nativeQuery = true)
   TitanUser findByUserName(@Param("username") String username);
   @Query(value = "SELECT permission from titan_permission  where uid =?1", nativeQuery = true)
   List<String> findByUid(@Param("uid") String uid);
}
