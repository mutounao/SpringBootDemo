package cn.mutounao.demo.services;

import cn.mutounao.demo.pojo.PagePojo;
import cn.mutounao.demo.pojo.Test;

import java.util.List;

public interface TestService {
    List<Test> selectAll();
    Test selectByPrimaryKey(Integer id);
    List<Test> getAll(PagePojo pagePojo);
}
