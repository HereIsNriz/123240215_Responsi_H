package controller;

import dao.*;
import model.Kucing;
import view.AplikasiPenitipanView;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class KucingController {
    private final AplikasiPenitipanView view;
    private final KucingDAO dao;

    public KucingController(AplikasiPenitipanView view) {
        this.view = view;
        this.dao = new KucingDAOImpl();
        initEvent();
        loadData();
    }

    private void initEvent() {
        view.getBtnTambah().addActionListener(e -> tambah());
        view.getBtnUbah().addActionListener(e -> update());
        view.getBtnHapus().addActionListener(e -> delete());
        view.getBtnClear().addActionListener(e -> view.clearForm());
        view.getTable().getSelectionModel().addListSelectionListener(this::onTableSelect);
    }
    private void onTableSelect(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) return;
        int id = view.getSelectedIdFromTable();
        if (id == -1) return;

        Kucing kucing = dao.findById(id);
        if (kucing != null) {
            view.fillForm(kucing);
        }
    }
    private void loadData() {
        List<Kucing> list = dao.findAll();
        view.setTableData(list);
    }
    private void tambah() {
        Kucing kucing = view.createKucingFromForm();
        if (kucing == null) return;

        if (kucing.getNamaPemilik().isEmpty() || kucing.getNamaKucing().isEmpty()) {
            view.showError("Nama pemilik dan nama kucing tidak boleh kosong.");
            return;
        }

        boolean sukses = dao.insert(kucing);
        if (sukses) {
            view.showMessage("Data Kucing Berhasil Ditambahkan");
            loadData();
            view.clearForm();
        } else {
            view.showError("Data gagal ditambahkan.");
        }
    }
    private void update() {
        int id = view.getSelectedIdFromTable();
        if (id == -1) {
            view.showError("Pilih data dulu dari tabel.");
            return;
        }

        Kucing kucing = view.createKucingFromForm();
        if (kucing == null) return;

        kucing.setId(id);

        boolean sukses = dao.update(kucing);
        if (sukses) {
            view.showMessage("Data Kucing Berhasil Diupdate");
            loadData();
            view.clearForm();
        } else {
            view.showError("Data gagal diupdate.");
        }
    }
    private void delete() {
        int id = view.getSelectedIdFromTable();
        if (id == -1) {
            view.showError("Pilih data dulu dari tabel.");
            return;
        }

        boolean sukses = dao.delete(id);
        if (sukses) {
            view.showMessage("Data Kucing Berhasil Dihapus");
            loadData();
            view.clearForm();
        } else {
            view.showError("Data gagal dihapus.");
        }
    }
}