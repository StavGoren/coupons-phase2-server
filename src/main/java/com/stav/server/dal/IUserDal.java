//package com.stav.server.dal;
//
//import com.stav.server.beans.SuccessfulLoginData;
//import com.stav.server.entities.UserEntity;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.ArrayList;
//
//public interface IUserDal extends CrudRepository<UserEntity, Long> {
//
//    @Query("select u from users u where company_id =:u.companyId")
//    public ArrayList<UserEntity> getUsersByCompanyID(@Param("companyId") long companyId);
//
//    @Query("select u from users u where user_name =:userName and password = :password")
//    public SuccessfulLoginData login(@Param("userName") String userName, @Param("password") String password);
//
//    @Query("select u from users u where user_name =:userName")
//    public UserEntity isUserExist(@Param("userName") String userName);
//
//}