package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.GameTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameTable, Integer> {
    @Override
    long count();
    long countAllByDeleteTime(long deleteTime);
    GameTable findByIdAndDeleteTime(int id, long deleteTime);
}
