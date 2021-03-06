package cn.kukaha.atcrowdfunding.manager.dao;

import cn.kukaha.atcrowdfunding.bean.MemberProjectFollow;
import java.util.List;

public interface MemberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberProjectFollow record);

    MemberProjectFollow selectByPrimaryKey(Integer id);

    List<MemberProjectFollow> selectAll();

    int updateByPrimaryKey(MemberProjectFollow record);
}