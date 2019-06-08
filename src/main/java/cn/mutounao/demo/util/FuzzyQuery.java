package cn.mutounao.demo.util;

import cn.mutounao.demo.pojo.PagePojo;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

public class FuzzyQuery {

    private String splitParent = ",";
    private String splitChildren = ":";
    private String sqlLikeChart = "%";

    public Example Start(Class<?> entityClass, PagePojo pagePojo) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();

        if (pagePojo.getSearch() != null && pagePojo.getSearch().length() > 0 && checkValue(pagePojo.getSearch())) {
            HashMap<String, String> temp = getValueMap(pagePojo.getSearch());
            for (Map.Entry<String, String> entry : temp.entrySet()) {
                String t = sqlLikeChart + entry.getValue() + sqlLikeChart;
                criteria.andLike(entry.getKey(), t);
            }
        }
        return example;
    }

    private Boolean checkValue(String search) {
        boolean flag = false;
        if (search.contains(splitParent) || (search.contains(splitChildren) && search.split(splitChildren).length == 1)) {
            flag = true;
        }
        return flag;
    }

    private HashMap<String, String> getValueMap(String search) {
        HashMap<String, String> KeysMap = new HashMap<>();
        String[] keys = search.split(splitParent);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].contains(splitChildren)) {
                String[] keyValue = keys[i].split(splitChildren);
                KeysMap.put(keyValue[0], keyValue[1]);
            }
        }
        return KeysMap;
    }
}