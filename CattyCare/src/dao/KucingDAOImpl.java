package dao;

import config.Koneksi;
import model.Kucing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KucingDAOImpl implements KucingDAO {
    private static final String TABLE_NAME = "penitipan";

    @Override
    public boolean insert(Kucing kucing) {
        String sql = "INSERT INTO " + TABLE_NAME + " (nama_pemilik, nama_kucing, nomor_telepon, lama_penitipan, biaya) " + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            kucing.refreshBiaya();

            ps.setString(1, kucing.getNamaPemilik());
            ps.setString(2, kucing.getNamaKucing());
            ps.setString(3, kucing.getNomorTelepon());
            ps.setInt(4, kucing.getLamaPenitipan());
            ps.setInt(5, kucing.getBiaya());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean update(Kucing kucing) {
        String sql = "UPDATE " + TABLE_NAME + " SET nama_pemilik = ?, nama_kucing = ?, nomor_telepon = ?, lama_penitipan = ?, biaya = ? " + "WHERE id = ?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            kucing.refreshBiaya();

            ps.setString(1, kucing.getNamaPemilik());
            ps.setString(2, kucing.getNamaKucing());
            ps.setString(3, kucing.getNomorTelepon());
            ps.setInt(4, kucing.getLamaPenitipan());
            ps.setInt(5, kucing.getBiaya());
            ps.setInt(6, kucing.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public Kucing findById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResult(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("FindById error: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<Kucing> findAll() {
        List<Kucing> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY id ASC";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResult(rs));
            }
        } catch (SQLException e) {
            System.out.println("FindAll error: " + e.getMessage());
        }

        return list;
    }
    private Kucing mapResult(ResultSet rs) throws SQLException {
        Kucing kucing = new Kucing();
        kucing.setId(rs.getInt("id"));
        kucing.setNamaPemilik(rs.getString("nama_pemilik"));
        kucing.setNamaKucing(rs.getString("nama_kucing"));
        kucing.setNomorTelepon(rs.getString("nomor_telepon"));
        kucing.setLamaPenitipan(rs.getInt("lama_penitipan"));
        kucing.refreshBiaya();
        return kucing;
    }
}