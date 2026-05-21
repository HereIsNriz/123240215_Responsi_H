package dao;

import model.Kucing;
import java.util.List;

public interface KucingDAO {
    boolean insert(Kucing kucing);
    boolean update(Kucing kucing);
    boolean delete(int id);
    Kucing findById(int id);
    List<Kucing> findAll();
}