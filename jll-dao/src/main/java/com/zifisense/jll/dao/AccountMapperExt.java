package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.dto.AccountDTO;
import com.zifisense.jll.qo.AccountQo;
import com.zifisense.jll.vo.AccountDetailVo;
import com.zifisense.jll.vo.AccountShortVo;

/**
 * 用户信息Mapper
 * Created by DW on 2017/6/27.
 */
@Mapper
public interface AccountMapperExt extends AccountMapper{

  


    /**
     * 根据条件返回用户列表信息 (查询试图)
     * @param accountDTO
     * @return
     */
    List<AccountDTO> searchAccountDTOList(AccountDTO accountDTO);

    /**
     * 账户详情Bean （用户详情（右上角点击用户头像进入）  非超管用户）
     * @param accountId 账户id
     * @return
     */
    AccountDetailVo getAccountDetailVo(Long accountId);
    
    /**
     * 获取用户信息列表
     * @param accountQo
     * @return
     */
    List<AccountDetailVo>  findAccountInfoList(AccountQo accountQo);

    /**
     *  获取用户简称列表
     * @param identity（用户等级）
     * @return
     */
	List<AccountShortVo> getShortAcountList(@Param(value="identity") String identity,@Param(value="keys") String keys);
	
	/**
	 *获取可关联项目的账号
	 *过滤已经拥有三个项目的账号
	 * @return
	 */
	List<AccountShortVo> getProjectAcountAble();
}
