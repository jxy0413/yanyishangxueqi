package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.SearchTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<SearchTable, Integer> {
    List<SearchTable> findBySearchNameAndSearchType(String name, int type);
}
