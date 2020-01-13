package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.SearchRepository;
import cn.edu.bjfu.igarden.entity.SearchTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchImpl {
    @Autowired
    SearchRepository searchRepository;

    /**
     * 搜索记录
     *
     * @param name 搜索输入内容
     * @param type 搜索类型
     */
    public void setSearch(String name, int type) {
        SearchTable searchTable = new SearchTable();
        searchTable.setSearchName(name);
        searchTable.setSearchType(type);
        List<SearchTable> list = searchRepository.findBySearchNameAndSearchType(name, type);
        if (list.size() != 0) {
            searchTable.setId(list.get(0).getId());
            searchTable.setSearchTime(list.get(0).getSearchTime() + 1);
        } else {
            searchTable.setSearchTime(1);
        }
        searchRepository.save(searchTable);
    }
}
