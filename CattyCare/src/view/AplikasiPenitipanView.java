package view;

import model.Kucing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AplikasiPenitipanView extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField txtPemilik = new JTextField(18);
    private final JTextField txtKucing = new JTextField(18);
    private final JTextField txtTelepon = new JTextField(18);
    private final JTextField txtWaktu = new JTextField(18);
    private final JButton btnTambah = new JButton("Tambah");
    private final JButton btnUbah = new JButton("Ubah");
    private final JButton btnHapus = new JButton("Hapus");
    private final JButton btnClear = new JButton("Clear");

    public AplikasiPenitipanView() {
        setTitle("CattyCare - Aplikasi Penitipan Kucing"); // Judul dari aplikasi yang muncul di pojok kiri atas
        setSize(960, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agar program berhenti ketika aplikasi ditutup 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("CattyCare - Aplikasi Penitipan Kucing Nomor 1 Di Indonesia", SwingConstants.LEFT);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 0));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama Pemilik", "Nama Kucing", "Nomor Telepon", "Lama Penitipan", "Biaya"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(530, 380));

        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(0));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 5));
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 15, 15));
        formPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        addFormRow(formPanel, gbc, y++, "Nama Pemilik: ", txtPemilik);
        addFormRow(formPanel, gbc, y++, "Nama Kucing: ", txtKucing);
        addFormRow(formPanel, gbc, y++, "Nomor Telepon", txtTelepon);
        addFormRow(formPanel, gbc, y++, "Lama Penitipan(Hari): ", txtWaktu);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 12, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUbah);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnHapus);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 4, 6, 4);
        formPanel.add(buttonPanel, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(245, 245, 245));
        rightPanel.add(formPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(560);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField field) {
        gbc.gridy = y;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
    public void tampilkan() {
        setVisible(true);
    }
    public JTable getTable() {
        return table;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public JTextField getTxtPemilik() {
        return txtPemilik;
    }
    public JTextField getTxtKucing() {
        return txtKucing;
    }
    public JTextField getTxtTelepon() {
        return txtTelepon;
    }
    public JTextField getTxtWaktu() {
        return txtWaktu;
    }
    public JButton getBtnTambah() {
        return btnTambah;
    }
    public JButton getBtnUbah() {
        return btnUbah;
    }
    public JButton getBtnHapus() {
        return btnHapus;
    }
    public JButton getBtnClear() {
        return btnClear;
    }
    public void setTableData(List<Kucing> list) {
        tableModel.setRowCount(0);
        for (Kucing kucing : list) {
            tableModel.addRow(new Object[]{
                kucing.getId(),
                kucing.getNamaPemilik(),
                kucing.getNamaKucing(),
                kucing.getNomorTelepon(),
                kucing.getLamaPenitipan(),
                kucing.getBiaya()
            });
        }
    }
    public void clearForm() {
        txtPemilik.setText("");
        txtKucing.setText("");
        txtTelepon.setText("");
        txtWaktu.setText("");
        table.clearSelection();
    }
    public void fillForm(Kucing kucing) {
        txtPemilik.setText(kucing.getNamaPemilik());
        txtKucing.setText(kucing.getNamaKucing());
        txtTelepon.setText(String.valueOf(kucing.getNomorTelepon()));
        txtWaktu.setText(String.valueOf(kucing.getLamaPenitipan()));
    }
    public int getSelectedIdFromTable() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return -1;
        return Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
    }
    public Kucing createKucingFromForm() {
        try {
            String namaPemilik = txtPemilik.getText().trim();
            String namaKucing = txtKucing.getText().trim();
            String nomorTelepon = txtTelepon.getText().trim();
            int lamaPenitipan = Integer.parseInt(txtWaktu.getText().trim());

            return new Kucing(namaPemilik, namaKucing, nomorTelepon, lamaPenitipan);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lama penitipan harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}