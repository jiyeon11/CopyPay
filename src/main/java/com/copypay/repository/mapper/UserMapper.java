package com.copypay.repository.mapper;

import com.copypay.model.User;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void updatePassword(Map<String, Object> params);
    String getOTPKeyById(String id);
}
