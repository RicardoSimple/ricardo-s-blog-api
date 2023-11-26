package com.ricardo.web.service.impl;

import com.ricardo.web.dao.TalentUserDAO;
import com.ricardo.web.dao.TeamUserDAO;
import com.ricardo.web.dto.TalentUserDO;
import com.ricardo.web.dto.TeamUserDO;
import com.ricardo.web.model.Result;
import com.ricardo.web.model.TalentUser;
import com.ricardo.web.model.TeamUser;
import com.ricardo.web.model.param.TokenInfo;
import com.ricardo.web.model.param.UserRegisterRequest;
import com.ricardo.web.service.UserService;
import com.ricardo.web.util.Code;
import com.ricardo.web.util.Const;
import com.ricardo.web.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TalentUserDAO talentUserDAO;

    @Autowired
    private TeamUserDAO teamUserDAO;

    @Override
    public Result registerUser(UserRegisterRequest request) {
        // 实现用户注册逻辑
        if (request.getPhone().isBlank()){
            return Result.fail(Code.FAIL_DUPLICATE,"需要输入手机号");
        }
        // 1. 判断用户类型
        if (request.getUserType().equals(Const.TALENT_TYPE)) {
            // 人才类型
            TalentUserDO talentUserDO = talentUserDAO.findTalentUserByPhone(request.getPhone());
            if(talentUserDO!=null&&talentUserDO.getPhone().equals(request.getPhone())){
                return Result.fail(Code.FAIL_DUPLICATE,"手机号重复");
            }
            talentUserDO = new TalentUserDO();
            talentUserDO.setAvatar(request.getAvatar());
            talentUserDO.setEducation(request.getEducation());
            talentUserDO.setPhone(request.getPhone());
            talentUserDO.setEmail(request.getEmail());
            talentUserDO.setName(request.getName());
            // 加密
            String encrypt = PasswordUtil.encrypt(request.getPwd(), Const.PASSWORD, PasswordUtil.getStaticSalt());

            talentUserDO.setPwd(encrypt);
            talentUserDO.setNickName(request.getNickName());
            talentUserDO.setStatus(request.getStatus());

            talentUserDAO.insertTalentUser(talentUserDO);
            return Result.success(null);
        }
        // team类型
        TeamUserDO teamUserDO = teamUserDAO.findTeamUserByPhone(request.getPhone());
        if(teamUserDO!=null&&teamUserDO.getPhone().equals(request.getPhone())){
            return Result.fail(Code.FAIL_DUPLICATE,"手机号重复");
        }
        teamUserDO = new TeamUserDO();
        teamUserDO.setAvatar(request.getAvatar());
        teamUserDO.setEmail(request.getEmail());
        teamUserDO.setPhone(request.getPhone());
        teamUserDO.setName(request.getName());
        // 加密
        String encrypt = PasswordUtil.encrypt(request.getPwd(), Const.PASSWORD, PasswordUtil.getStaticSalt());

        teamUserDO.setPwd(encrypt);
        teamUserDO.setNickName(request.getNickName());
        teamUserDO.setTeamId(Long.valueOf(request.getTeamId()));
        teamUserDO.setRole(request.getRole());

        teamUserDAO.insertTeamUser(teamUserDO);

        return Result.success(null);
    }

    @Override
    public Result getAllUsersByType(String type) {
        // 1. 判断用户类型
        if (type==Const.TALENT_TYPE){
            List<TalentUser> result = new ArrayList<>();
            // 2. 查
            List<TalentUserDO> talentUserDOs = talentUserDAO.findAllTalentUsers();
            // 3. 返回
            for (int i = 0; i < talentUserDOs.size(); i++) {
                result.add(talentUserDOs.get(i).toUser());
            }
            return Result.success(result);
        }
        List<TeamUser> result = new ArrayList<>();
        List<TeamUserDO> teamUserDOs = teamUserDAO.findAllTeamUsers();
        // 3. 返回
        for (int i = 0; i < teamUserDOs.size(); i++) {
            result.add(teamUserDOs.get(i).toUser());
        }
        return Result.success(result);

    }

    @Override
    public Object login(String type,String phone,String pwd) {
        if(type.equals(Const.TALENT_TYPE)){
            TalentUserDO user = talentUserDAO.findTalentUserByPhone(phone);
            if (user==null){
                return null;
            }
            // 解密
            String decrypt = PasswordUtil.decrypt(user.getPwd(), Const.PASSWORD, PasswordUtil.getStaticSalt());

            if(decrypt.equals(pwd)){
                return TokenInfo.getInfoByUser(user.toUser(),Const.TALENT_TYPE);
            }
            return null;
        }
        TeamUserDO user = teamUserDAO.findTeamUserByPhone(phone);
        if(user==null){
            return null;
        }
        // 解密
        String decrypt = PasswordUtil.decrypt(user.getPwd(), Const.PASSWORD, PasswordUtil.getStaticSalt());

        if(decrypt.equals(pwd)){
            return TokenInfo.getInfoByUser(user.toUser(),Const.TEAM_TYPE,user.getTeamId());
        }
        return null;
    }

    @Override
    public Result getUserByIdAndType(String userType, Long id) {
        if(userType.equals(Const.TALENT_TYPE)){
            TalentUserDO talentUserById = talentUserDAO.findTalentUserById(id);
            if(talentUserById==null){
                return Result.fail(Code.FAIL_NO_DATA,"id错误");
            }
            return Result.success(talentUserById.toUser());
        }
        TeamUserDO teamUserById = teamUserDAO.findTeamUserById(id);
        if(teamUserById==null){
            return Result.fail(Code.FAIL_NO_DATA,"id错误");
        }
        return Result.success(teamUserById.toUser());
    }

    @Override
    public Result updateUserByUserType(UserRegisterRequest params, String userType) {
        if(userType.equals(Const.TALENT_TYPE)){
            talentUserDAO.updateTalentUser(params.toTalentUserDO());
            return Result.success(null);
        }
        teamUserDAO.updateTeamUser(params.toTeamUserDO());
        return Result.success(null);
    }
}
