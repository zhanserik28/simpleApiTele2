package org.example.simpleApi.dao;

import org.example.simpleApi.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

}
