package cn.mutounao.demo.controllers.apis;

import cn.mutounao.demo.pojo.PagePojo;
import cn.mutounao.demo.pojo.Test;
import cn.mutounao.demo.services.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "Hello 测试用")
@RestController
public class Hello {

    @Resource
    private TestService testService;


    @ApiOperation(value = "查询Test单条信息", notes = "需要ID")
    @ApiImplicitParam(name = "id", value = "Test ID", required = true, dataType = "Long")
    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public Test HelloWorld(@PathVariable Integer id) {
        return testService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查询Test所有信息", notes = "不需要参数")
    @RequestMapping(value = "/test/all", method = RequestMethod.GET)
    public List<Test> t() {
        return testService.selectAll();
    }

    @ApiOperation(value = "分页查询Test所有信息", notes = "需要三参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "rows", value = "每页长度", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "search", value = "模糊查询", required = false, dataType = "String")
    })
    @RequestMapping(value = "/test/page", method = RequestMethod.GET)
    public PageInfo<Test> getAll(PagePojo pagePojo) {
        List<Test> list = testService.getAll(pagePojo);
        return new PageInfo<Test>(list);
    }

}
