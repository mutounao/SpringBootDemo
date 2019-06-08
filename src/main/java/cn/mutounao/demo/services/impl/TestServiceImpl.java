package cn.mutounao.demo.services.impl;

import cn.mutounao.demo.mapper.TestMapper;
import cn.mutounao.demo.pojo.PagePojo;
import cn.mutounao.demo.pojo.Test;
import cn.mutounao.demo.util.FuzzyQuery;
import com.github.pagehelper.PageHelper;
import cn.mutounao.demo.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Autowired(required = false)
    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }

    @Override
    public Test selectByPrimaryKey(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Test> getAll(PagePojo pagePojo) {
        if (pagePojo.getPage() != null && pagePojo.getRows() != null) {
            PageHelper.startPage(pagePojo.getPage(), pagePojo.getRows());
        }
        if (pagePojo.getSearch() != null) {
            Example example = new FuzzyQuery().Start(Test.class,pagePojo);
            return testMapper.selectByExample(example);
        }
        return testMapper.selectAll();
    }
}