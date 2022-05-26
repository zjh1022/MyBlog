package com.zjh.myblog.service;

import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.entity.Role;
import com.zjh.myblog.entity.User;
import com.zjh.myblog.entity.UserRole;
import com.zjh.myblog.execption.CustomException;
import com.zjh.myblog.mapper.RoleMapper;
import com.zjh.myblog.mapper.UserMapper;
import com.zjh.myblog.mapper.UserRoleMapper;
import com.zjh.myblog.security.LoginUser;
import com.zjh.myblog.security.service.TokenService;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**用户 业务层处理
 * @auther zhengjianghai
 * @create 2022-01-27 9:19
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    TokenService tokenService;

    /**
     * 根据条件分页查询用户列表
     * @param user
     * @return
     */
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     *根据用户ID查询用户所属角色组
     * @param userName
     * @return
     */
    public String selectUserRoleGroup(String userName) {
        List<Role> list = roleMapper.selectRolesByUserName(userName);
        StringBuilder idsStr = new StringBuilder();
        for (Role role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户名是否唯一
     * @param userName
     * @return
     */
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 查询电话是否唯一
     * @param user
     * @return
     */
    public String checkPhoneUnique(User user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        User info = userMapper.checkPhoneUnique(user.getPhone());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue()) {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 查询email是否唯一
     * @param user
     * @return
     */
    public String checkEmailUnique(User user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue()) {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     *校验用户是否允许操作
     * @param user
     */
    public void checkUserAllowed(User user) {
        if (StringUtils.isNotNull(user.getId()) && user.isAdmin()) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    
    @Transactional
    public int insertUser(User user) {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    
    @Transactional
    public int updateUser(User user) {
        Long userId = user.getId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        return userMapper.updateUser(user);
    }


    
    public int updateUserStatus(User user) {
        return userMapper.updateUser(user);
    }


    
    public int updateUserProfile(User user) {
        user.setId(SecurityUtils.getLoginUser().getUser().getId());
        int result = userMapper.updateUser(user);
        //更新redis缓存
        refreshTokenClaims(user.getId());
        return result;
    }

    /**
     * 同步刷新Redis缓存
     *
     * @param id sys_user id
     */
    private void refreshTokenClaims(Long id) {
        //更新redis缓存
        LoginUser loginUser = SecurityUtils.getLoginUser();
        loginUser.setUser(userMapper.selectUserById(id));
        tokenService.refreshToken(loginUser);
    }

    
    public int resetPwd(User user) {
        return userMapper.updateUser(user);
    }


    
    public int resetUserPwd(String userName, String password) {
        return userMapper.resetUserPwd(userName, password);
    }

    public void insertUserRole(User user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<UserRole> list = new ArrayList<>();
            for (Long roleId : roles) {
                UserRole ur = new UserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (!list.isEmpty()) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }


    
    @Transactional
    public int deleteUserByIds(String ids) {
        Long[] userIds = ConvertUtils.toLongArray(ids);
        for (Long userId : userIds) {
            checkUserAllowed(new User(userId));
            // 删除用户与角色关联
            userRoleMapper.deleteUserRoleByUserId(userId);
        }
        String loginUsername = SecurityUtils.getUsername();
        return userMapper.deleteUserByIds(userIds, loginUsername);
    }

}
