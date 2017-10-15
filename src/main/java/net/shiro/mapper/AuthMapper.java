package net.shiro.mapper;

import java.util.List;
import net.shiro.bean.Auth;

public interface AuthMapper {
    int insert(Auth record);

    List<Auth> selectAll();
}