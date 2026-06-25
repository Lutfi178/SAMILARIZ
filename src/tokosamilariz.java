
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class tokosamilariz extends javax.swing.JFrame {

    /**
     * Creates new form tokosamilariz
     */
    public tokosamilariz() {
        initComponents();
        try {
        load_table(); // <-- Langsung isi data ke tabel saat form dibuka
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data: " + ex.getMessage());
    }
    }
    
    private void load_table() throws SQLException {
    DefaultTableModel model = new DefaultTableModel();
    
    // Kolom 'id' tetap ditambahkan, tapi akan disembunyikan
    model.addColumn("ID");               // Kolom untuk ID (disembunyikan)
    model.addColumn("NO");               // Nomor urut
    model.addColumn("NAMA BARANG");
    model.addColumn("HARGA SATUAN");
    model.addColumn("JUMLAH BARANG");
    model.addColumn("TOTAL BAYAR");
    model.addColumn("BONUS");

    int no = 1;
    String sql = "SELECT * FROM konsumen";
    java.sql.Connection conn = (Connection) config.configDB();
    java.sql.Statement stm = conn.createStatement();
    java.sql.ResultSet res = stm.executeQuery(sql);

    while (res.next()) {
        model.addRow(new Object[]{
            res.getInt("id"),           // ID (kolom 0)
            no++,                       // NO (kolom 1)
            res.getString("nama"),
            res.getDouble("harga"),
            res.getInt("jumlah_barang"),
            res.getDouble("total_bayar"),
            res.getString("bonus")
        });
    }

    jTable1.setModel(model);

    // Sembunyikan kolom ID (kolom 0)
    jTable1.getColumnModel().getColumn(0).setMinWidth(0);
    jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    jTable1.getColumnModel().getColumn(0).setWidth(0);
}

    private void kosong(){
        txtnama.setText(null);
        txtharga.setText(null);
        txtjumlah.setText(null);
        lbltotal.setText("-");
        lblbonus.setText("-");
        
    }
    
    private void exportToPDF(JTable table, String filename) {
    try {
        Document document = new Document(PageSize.A4.rotate()); // Ukuran A4 landscape
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();

        // Tambahkan Judul
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
        Paragraph title = new Paragraph("Data Transaksi SAMI LARIZ", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        // Siapkan tabel PDF (tanpa kolom ID - index ke 0)
        int columnCount = table.getColumnCount();
        PdfPTable pdfTable = new PdfPTable(columnCount - 1);
        pdfTable.setWidthPercentage(100);

        // Header Tabel (lewati kolom ke-0 = ID)
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        for (int i = 1; i < columnCount; i++) {
            PdfPCell headerCell = new PdfPCell(new Phrase(table.getColumnName(i), headerFont));
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(headerCell);
        }

        // Isi Data Baris (lewati kolom ID)
        Font cellFont = new Font(Font.FontFamily.HELVETICA, 11);
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 1; col < columnCount; col++) {
                Object value = table.getValueAt(row, col);
                pdfTable.addCell(new Phrase(value != null ? value.toString() : "", cellFont));
            }
        }

        document.add(pdfTable);
        document.close();

        // Buka PDF otomatis
        File pdfFile = new File(filename);
        if (pdfFile.exists() && Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(pdfFile);
        }

        JOptionPane.showMessageDialog(null, "PDF SAMI LARIZ berhasil diekspor dan dibuka!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal ekspor PDF: " + e.getMessage());
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bbaru = new javax.swing.JButton();
        bcetak = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        lblbonus = new javax.swing.JTextField();
        btambah = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(102, 255, 102));
        jPanel3.setForeground(new java.awt.Color(51, 255, 51));

        jTable1.setBackground(new java.awt.Color(255, 255, 0));
        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NO", "NAMA BARANG", "HARGA SATUAN", "JUMLAH BARANG", "TOTAL BAYAR", "BONUS"
            }
        ));
        jTable1.setAlignmentX(1.0F);
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bbaru.setText("BARU");
        bbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbaruActionPerformed(evt);
            }
        });

        bcetak.setText("CETAK");
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });

        jLabel5.setText("total bayar");

        jLabel6.setText("bonus");

        lbltotal.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 18)); // NOI18N
        jLabel1.setText("TOKO SAMI LARIZ");

        jLabel2.setText("nama barang");

        jLabel3.setText("harga satuan");

        jLabel4.setText("jumlah barang");

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });

        btambah.setText("TAMBAH");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bkeluar.setText("KELUAR");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblbonus, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bcetak))
                                    .addComponent(txtharga)
                                    .addComponent(txtjumlah)
                                    .addComponent(lbltotal)))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bedit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btambah, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bhapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bkeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(191, 191, 191))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bbaru)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblbonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(bcetak))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(bkeluar)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bhapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
        try {
                String nama = txtnama.getText();
                double harga = Double.parseDouble(txtharga.getText());
                int jumlah = Integer.parseInt(txtjumlah.getText());
                double total = harga * jumlah;
                String bonus = total >= 300000 ? "TAS CANTIK" : "VOUCHER";
                lbltotal.setText(String.valueOf(total));
                lblbonus.setText(bonus);

                
                String sql = "INSERT INTO konsumen (nama, harga, jumlah_barang, total_bayar, bonus) VALUES (?, ?, ?, ?, ?)";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setString(1, nama);
                pst.setDouble(2, harga);
                pst.setInt(3, jumlah);
                pst.setDouble(4, total);
                pst.setString(5, bonus);
                pst.executeUpdate();
                conn.close();

                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.");
                load_table();
                kosong();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        
        
           
    }//GEN-LAST:event_btambahActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int baris = jTable1.rowAtPoint(evt.getPoint());
        String nama= jTable1.getValueAt(baris,2).toString();
        txtnama.setText(nama);
        String harga= jTable1.getValueAt(baris,3).toString();
        txtharga.setText(harga);
        String jumlah= jTable1.getValueAt(baris,4).toString();
        txtjumlah.setText(jumlah);
        String total= jTable1.getValueAt(baris,5).toString();
        lbltotal.setText(total);
        String bonus= jTable1.getValueAt(baris,6).toString();
        lblbonus.setText(bonus);
    }//GEN-LAST:event_jTable1MouseClicked

    private void bbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbaruActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_bbaruActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        System.exit(0); 
    }//GEN-LAST:event_bkeluarActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        // TODO add your handling code here:
         int row = jTable1.getSelectedRow();
            if (row != -1) {
                try {
                    int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
                    String nama = txtnama.getText();
                    double harga = Double.parseDouble(txtharga.getText());
                    int jumlah = Integer.parseInt(txtjumlah.getText());
                    double total = harga * jumlah;
                    String bonus = total >= 300000 ? "TAS CANTIK" : "VOUCHER";
                    lbltotal.setText(String.valueOf(total));
                    lblbonus.setText(bonus);

                    
                    String sql = "UPDATE konsumen SET nama=?, harga=?, jumlah_barang=?, total_bayar=?, bonus=? WHERE id=?";
                    java.sql.Connection conn=(Connection)config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.setString(1, nama);
                    pst.setDouble(2, harga);
                    pst.setInt(3, jumlah);
                    pst.setDouble(4, total);
                    pst.setString(5, bonus);
                    pst.setInt(6, id);
                    pst.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Data berhasil diedit.");
                    load_table();
                    kosong();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
    }//GEN-LAST:event_beditActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
            if (row != -1) {
                try {
                    int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
                    String sql = "DELETE FROM konsumen WHERE id=?";
                    java.sql.Connection conn=(Connection)config.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                    load_table();
                    kosong();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        // TODO add your handling code here:
        String pdfTitle = "SAMI LARIZ.pdf";
        exportToPDF(jTable1, pdfTitle);
    }//GEN-LAST:event_bcetakActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tokosamilariz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tokosamilariz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tokosamilariz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tokosamilariz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tokosamilariz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbaru;
    private javax.swing.JButton bcetak;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton btambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lblbonus;
    private javax.swing.JTextField lbltotal;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables
}
