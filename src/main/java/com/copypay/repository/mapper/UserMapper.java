package com.copypay.repository.mapper;

import com.copypay.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User findEmailByUsername(String email);
    void updatePassword(Map<String, Object> params);
}
