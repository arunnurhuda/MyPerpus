/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arun Nurhuda
 */
public class MenuUtama extends javax.swing.JFrame {
    private Connection con = new koneksi().open();
    koneksi kon= new koneksi();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode1;
    private DefaultTableModel tabmode2;
    private DefaultTableModel tabmode3;
    Vector originalTableModel,originalTableModel1;
    /**
     * Creates new form MenuUtama
     */
   
    public MenuUtama(java.awt.Frame parent, boolean modal) {
        initComponents();
        menukosong();
        datatbladmin();
        datatblanggota();  
        datatblbuku();
        datatblpinjam();
        comboidanggota();
        comboidbuku();
        originalTableModel = (Vector) ((DefaultTableModel) tblbuku1.getModel()).getDataVector().clone();
        originalTableModel1 = (Vector) ((DefaultTableModel) tblpinjam1.getModel()).getDataVector().clone();

    }
    
    public class koneksi {
     private Connection koneksi;
        public Connection open(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("koneksi berhasil");
        }
        catch(ClassNotFoundException ex){
            System.out.println("Koneksi Gagal"+ex);
        }
        String url = "jdbc:mysql://localhost/db_perpus";
        try{
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("koneksi database berhasil");
        }
        catch (SQLException ex){
            System.out.println("koneksi database gagal"+ex);
        }
        return koneksi;
        }
    }
    
    protected void menukosong(){
        menukosong.setVisible(true);
        panel_reg_admin.setVisible(false);
        panel_reg_anggota.setVisible(false);
        panel_input_buku.setVisible(false);
        panel_pinjam.setVisible(false);
        panel_pengembalian.setVisible(false);
        panel_caribuku.setVisible(false);
    }
    
    protected void clear(){
        f_idbuku.setText("");
        f_judul.setText("");
        f_penerbit.setText("");
        f_tahun.setText("");
        f_penulis.setText("");
        f_nis.setText("");
        f_nama.setText("");
        f_kelas.setText("");
        f_hp.setText("");
        btngroup_gender.clearSelection();
        btngroup_kategori.clearSelection();
        a.setText("");
        b.setText("");
        c.setText("");
        f_no1.setText("");
        f_idbuku2.setText("");
        f_idanggota2.setText("");
        f_judul1.setText("");
        f_judul2.setText("");
        tgl3.setText("");
        f_nama1.setText("");
        f_penerbit1.setText("");
        f_penerbit2.setText("");
        cbpinjam1.setSelectedItem("==pilih ID buku==");
        cbpinjam2.setSelectedItem("==pilih ID Anggota==");
        f_cari.setText("");
        tgl5.setText("");
    }
    
    
    
        protected void datatbladmin(){
            Object[] Baris ={"Nama","Username","Passowrd"};
            tabmode = new DefaultTableModel(null, Baris);
            tbladmin.setModel(tabmode);
            String sql = "select * from t_login";
            try{
                Statement stat = con.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    String a = hasil.getString("username");
                    String b = hasil.getString("password");
                    String c = hasil.getString("nama");
                    String[] data={c,a,"****"};
                //String[] data={a,b,c,"*****"};
                tabmode.addRow(data);
               
                }
           
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Gagal Melihat Tabel"+e);
            }
        
        }
        
        
        protected void datatblbuku(){
            Object[] Baris ={"ID","Judul","Penulis","Penerbit","Tahun","Kategori"};
            tabmode2 = new DefaultTableModel(null, Baris);
            tblbuku.setModel(tabmode2);
            tblbuku1.setModel(tabmode2);
            String sql = "select * from buku_master";
            try{
                Statement stat = con.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    String a = hasil.getString("id");
                    String b = hasil.getString("judul");
                    String c = hasil.getString("penulis");
                    String d = hasil.getString("penerbit");
                    String e = hasil.getString("tahun");
                    String f = hasil.getString("kategori");
                    String[] data={a,b,c,d,e,f};
                tabmode2.addRow(data); 
                
            }
            }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Gagal Melihat Tabel"+e);
            }
        }

        protected void datatblanggota(){
        Object[] Baris ={"NIS","Nama","Kelas","No.HP","Jenis Kelamin"};
        tabmode1 = new DefaultTableModel(null, Baris);
        tblanggota.setModel(tabmode1);
        String sql = "select * from anggota";
        try{
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nis");
                String b = hasil.getString("nama");
                String c = hasil.getString("kelas");
                String d = hasil.getString("hp");
                String e = hasil.getString("jenkel");
                String[] data1={a,b,c,d,e};
                tabmode1.addRow(data1); 
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Gagal Melihat Tabel"+e);
        }
    }

        
        protected void datatblpinjam(){
            Object[] Baris ={"No","ID Buku","Judul","Penerbit","ID Anggota","Nama","Tanggal Pinjam","Tanggal Kembali"};
            tabmode3 = new DefaultTableModel(null, Baris);
            tblpinjam.setModel(tabmode3);
            tblpinjam1.setModel(tabmode3);
            String sql = "select * from peminjaman";
            try{
                Statement stat = con.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    String a = hasil.getString("no");
                    String b = hasil.getString("idbuku");
                    String c = hasil.getString("judul");
                    String d = hasil.getString("penerbit");
                    String e = hasil.getString("idanggota");
                    String f = hasil.getString("nama");
                    String g = hasil.getString("tglpinjam");
                    String h = hasil.getString("tglkembali");
                    
                    String[] data3={a,b,c,d,e,f,g,h};
                tabmode3.addRow(data3); 
            }
            }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Gagal Melihat Tabel Pinjam"+e);
            }
        } 
        
        
    public void comboidbuku(){
        try{
            String sql = "select * from buku_master";
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                cbpinjam1.addItem(hasil.getString("id"));
            }
            hasil.last();
            int jumlahdata = hasil.getRow();
            hasil.first();
        }catch(SQLException e){
            
        }
    }
    
    public void comboidanggota(){
        try{
            String sql = "select * from anggota";
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                cbpinjam2.addItem(hasil.getString("nis"));
            }
            hasil.last();
            int jumlahdata = hasil.getRow();
            hasil.first();
        }catch(SQLException e){
            
        }
    } 
    
    
    
    public void tampildetailbuku(){
        String sql = "SELECT * FROM buku_master where id='"+cbpinjam1.getSelectedItem()+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("judul");
                String b = hasil.getString("penerbit");
                f_judul1.setText(a);
                f_penerbit1.setText(b);
                f_judul2.setText(a);
                f_penerbit2.setText(b);
            }
            
        }catch(SQLException e){
            
        }
    }
    
    
        public void tampilnama(){
        String sql = "SELECT * FROM anggota where nis='"+cbpinjam2.getSelectedItem()+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("nama");
                f_nama1.setText(a);
                tgl3.setText(a);
            }
            
        }catch(SQLException e){
            
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

        btngroup_gender = new javax.swing.ButtonGroup();
        btngroup_kategori = new javax.swing.ButtonGroup();
        panel_pengembalian = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        f_judul2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        cekdenda = new javax.swing.JButton();
        f_penerbit2 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        tgl3 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        tgl4 = new com.toedter.calendar.JDateChooser();
        f_idbuku2 = new javax.swing.JTextField();
        f_idanggota2 = new javax.swing.JTextField();
        denda = new javax.swing.JTextField();
        f_nama2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        simpanbalik = new javax.swing.JButton();
        tgl5 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btn_exit_panel_input2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblpinjam1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btncaripinjam = new javax.swing.JButton();
        f_cari1 = new javax.swing.JTextField();
        panel_caribuku = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_exit_panel_register1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblbuku1 = new javax.swing.JTable();
        f_cari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        panel_pinjam = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        f_judul1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        pinjam = new javax.swing.JButton();
        f_penerbit1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        editpinjam = new javax.swing.JButton();
        hapuspinjam = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        f_nama1 = new javax.swing.JTextField();
        cbpinjam1 = new javax.swing.JComboBox<>();
        cbpinjam2 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        tgl1 = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        tgl2 = new com.toedter.calendar.JDateChooser();
        jLabel56 = new javax.swing.JLabel();
        f_no1 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_exit_panel_input1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblpinjam = new javax.swing.JTable();
        panel_input_buku = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        f_penulis = new javax.swing.JTextField();
        f_judul = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        simpanbuku = new javax.swing.JButton();
        f_penerbit = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        f_tahun = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cbumum = new javax.swing.JRadioButton();
        cbsosial = new javax.swing.JRadioButton();
        cbagama = new javax.swing.JRadioButton();
        cbbahasa = new javax.swing.JRadioButton();
        cbsejarah = new javax.swing.JRadioButton();
        cbmipa = new javax.swing.JRadioButton();
        cbseni = new javax.swing.JRadioButton();
        cbtekno = new javax.swing.JRadioButton();
        jLabel39 = new javax.swing.JLabel();
        f_idbuku = new javax.swing.JTextField();
        editbuku = new javax.swing.JButton();
        hapusbuku = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_exit_panel_input = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblbuku = new javax.swing.JTable();
        panel_reg_anggota = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        f_kelas = new javax.swing.JTextField();
        f_nama = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tambahanggota = new javax.swing.JButton();
        hapusanggota = new javax.swing.JButton();
        f_nis = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        f_hp = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        rb_male = new javax.swing.JRadioButton();
        rb_female = new javax.swing.JRadioButton();
        editanggota = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_exit_panel_register = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblanggota = new javax.swing.JTable();
        panel_reg_admin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        b = new javax.swing.JTextField();
        c = new javax.swing.JPasswordField();
        a = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btambahadmin = new javax.swing.JButton();
        bhapusadmin = new javax.swing.JButton();
        beditadmin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btn_exit_panel_reg_admin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbladmin = new javax.swing.JTable();
        menukosong = new javax.swing.JPanel();
        sidepanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_side_reg_anggota = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_side_reg_admin = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_side_inputbuku = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btn_side_caribuku = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btn_side_pinjam = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btn_side_logout = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btn_side_kembali = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btn_side_cetak = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        banner = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_pengembalian.setBackground(new java.awt.Color(250, 250, 250));
        panel_pengembalian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_pengembalian.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_pengembalianComponentHidden(evt);
            }
        });
        panel_pengembalian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        f_judul2.setEditable(false);

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_30px.png"))); // NOI18N
        jLabel44.setText("Judul Buku");

        cekdenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        cekdenda.setText("Cek Denda");
        cekdenda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cekdenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekdendaActionPerformed(evt);
            }
        });

        f_penerbit2.setEditable(false);

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_stack_30px.png"))); // NOI18N
        jLabel45.setText("Penerbit");

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel51.setText("ID Buku");

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel52.setText("ID Anggota");

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel53.setText("Nama");

        tgl3.setEditable(false);

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel54.setText("R.Tgl.Kembali");

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel55.setText("Tanggal Kembali");

        tgl4.setDateFormatString("d MMMM yyyy");

        f_idbuku2.setEditable(false);
        f_idbuku2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_idbuku2ActionPerformed(evt);
            }
        });

        f_idanggota2.setEditable(false);

        denda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dendaActionPerformed(evt);
            }
        });

        f_nama2.setEditable(false);

        jLabel2.setText("Denda");

        simpanbalik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        simpanbalik.setText("Simpan");
        simpanbalik.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        simpanbalik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanbalikActionPerformed(evt);
            }
        });

        tgl5.setEditable(false);

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel57.setText("Tgl Pinjam");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51)
                            .addComponent(jLabel45)
                            .addComponent(jLabel53)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(f_judul2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(f_idbuku2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(f_penerbit2)
                            .addComponent(f_idanggota2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(f_nama2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(tgl4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(denda))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(48, 48, 48))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(simpanbalik, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(cekdenda))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(tgl5)
                                .addGap(55, 55, 55)))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(tgl3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(f_idbuku2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(f_judul2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(f_penerbit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(f_idanggota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(f_nama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tgl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tgl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(denda)
                    .addComponent(tgl4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cekdenda)
                    .addComponent(simpanbalik))
                .addGap(44, 44, 44))
        );

        panel_pengembalian.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 480));

        jPanel20.setBackground(new java.awt.Color(255, 169, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("FORM PENGEMBALIAN BUKU");

        btn_exit_panel_input2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_input2.setContentAreaFilled(false);
        btn_exit_panel_input2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_input2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit_panel_input2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_input2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_pengembalian.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tblpinjam1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblpinjam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpinjam1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblpinjam1);

        panel_pengembalian.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 530, 430));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("LIST PEMINJAMAN");
        panel_pengembalian.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        btncaripinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_search_30px.png"))); // NOI18N
        btncaripinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripinjamActionPerformed(evt);
            }
        });
        panel_pengembalian.add(btncaripinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, 40, -1));

        f_cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_cari1ActionPerformed(evt);
            }
        });
        panel_pengembalian.add(f_cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 170, 40));

        getContentPane().add(panel_pengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 540));

        panel_caribuku.setBackground(new java.awt.Color(250, 250, 250));
        panel_caribuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_caribuku.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_caribukuComponentHidden(evt);
            }
        });
        panel_caribuku.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 169, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("CARI BUKU");

        btn_exit_panel_register1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_register1.setContentAreaFilled(false);
        btn_exit_panel_register1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_register1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(392, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(369, 369, 369)
                .addComponent(btn_exit_panel_register1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_register1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_caribuku.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tblbuku1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbuku1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbuku1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblbuku1);

        panel_caribuku.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, 910, 400));
        panel_caribuku.add(f_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 210, -1));

        btncari.setText("CARI");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        panel_caribuku.add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        getContentPane().add(panel_caribuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 540));

        panel_pinjam.setBackground(new java.awt.Color(250, 250, 250));
        panel_pinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_pinjam.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_pinjamComponentHidden(evt);
            }
        });
        panel_pinjam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        f_judul1.setEditable(false);

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_30px.png"))); // NOI18N
        jLabel42.setText("Judul Buku");

        pinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        pinjam.setText("Pinjam");
        pinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinjamActionPerformed(evt);
            }
        });

        f_penerbit1.setEditable(false);

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_stack_30px.png"))); // NOI18N
        jLabel43.setText("Penerbit");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel46.setText("ID Buku");

        editpinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        editpinjam.setText("Edit");
        editpinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editpinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpinjamActionPerformed(evt);
            }
        });

        hapuspinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        hapuspinjam.setText("Hapus");
        hapuspinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hapuspinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapuspinjamActionPerformed(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel47.setText("ID Anggota");

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel48.setText("Nama");

        f_nama1.setEditable(false);

        cbpinjam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==pilih ID buku==" }));
        cbpinjam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbpinjam1ActionPerformed(evt);
            }
        });

        cbpinjam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==pilih ID Anggota==" }));
        cbpinjam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbpinjam2ActionPerformed(evt);
            }
        });

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel49.setText("Tanggal Peminjaman ");

        tgl1.setDateFormatString("d MMMM yyyy");
        tgl1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tgl1InputMethodTextChanged(evt);
            }
        });

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel50.setText("Rencana Tgl Kembali");

        tgl2.setDateFormatString("d MMMM yyyy");

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_30px.png"))); // NOI18N
        jLabel56.setText("No");

        f_no1.setEditable(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editpinjam)
                        .addGap(12, 12, 12)
                        .addComponent(hapuspinjam))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f_judul1)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbpinjam1, 0, 224, Short.MAX_VALUE)
                                            .addComponent(f_nama1)
                                            .addComponent(f_penerbit1)
                                            .addComponent(cbpinjam2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel49)
                                        .addComponent(jLabel50)
                                        .addComponent(tgl2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                        .addComponent(tgl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(f_no1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(f_no1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(cbpinjam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(f_judul1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(f_penerbit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(cbpinjam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_nama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pinjam)
                    .addComponent(editpinjam)
                    .addComponent(hapuspinjam))
                .addContainerGap())
        );

        panel_pinjam.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 480));

        jPanel19.setBackground(new java.awt.Color(255, 169, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("FORM PEMINJAMAN BUKU");

        btn_exit_panel_input1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_input1.setContentAreaFilled(false);
        btn_exit_panel_input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_input1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit_panel_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_pinjam.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tblpinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblpinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpinjamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblpinjam);

        panel_pinjam.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 530, 480));

        getContentPane().add(panel_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        panel_input_buku.setBackground(new java.awt.Color(250, 250, 250));
        panel_input_buku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_input_buku.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_input_bukuComponentHidden(evt);
            }
        });
        panel_input_buku.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_and_pencil_30px.png"))); // NOI18N
        jLabel35.setText("Penulis");

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_30px.png"))); // NOI18N
        jLabel36.setText("Judul Buku");

        simpanbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        simpanbuku.setText("Simpan Buku");
        simpanbuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        simpanbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanbukuActionPerformed(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_book_stack_30px.png"))); // NOI18N
        jLabel37.setText("Penerbit");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel38.setText("Tahun Terbit");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_course_30px.png"))); // NOI18N
        jLabel40.setText("Kategori Buku");

        btngroup_kategori.add(cbumum);
        cbumum.setText("Umum");
        cbumum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbumumActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbsosial);
        cbsosial.setText("Sosial");
        cbsosial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsosialActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbagama);
        cbagama.setText("Agama");
        cbagama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbagamaActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbbahasa);
        cbbahasa.setText("Bahasa");
        cbbahasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbahasaActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbsejarah);
        cbsejarah.setText("Sejarah");
        cbsejarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsejarahActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbmipa);
        cbmipa.setText("MIPA");
        cbmipa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmipaActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbseni);
        cbseni.setText("Seni");
        cbseni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbseniActionPerformed(evt);
            }
        });

        btngroup_kategori.add(cbtekno);
        cbtekno.setText("Teknologi");
        cbtekno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbteknoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jSeparator2))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbseni)
                    .addComponent(cbsejarah)
                    .addComponent(cbsosial)
                    .addComponent(cbumum))
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbagama)
                    .addComponent(cbbahasa)
                    .addComponent(cbmipa)
                    .addComponent(cbtekno))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbumum)
                    .addComponent(cbagama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbahasa)
                    .addComponent(cbsosial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsejarah)
                    .addComponent(cbmipa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbseni, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbtekno, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_december_30px.png"))); // NOI18N
        jLabel39.setText("ID Buku");

        editbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        editbuku.setText("Edit");
        editbuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbukuActionPerformed(evt);
            }
        });

        hapusbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        hapusbuku.setText("Hapus");
        hapusbuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hapusbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusbukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f_penulis, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f_judul, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f_idbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(1, 1, 1)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(simpanbuku)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editbuku)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hapusbuku)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(28, 28, 28))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_idbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(f_judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(f_penulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel40))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanbuku)
                    .addComponent(editbuku)
                    .addComponent(hapusbuku))
                .addGap(24, 24, 24))
        );

        panel_input_buku.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 410, 460));

        jPanel12.setBackground(new java.awt.Color(255, 169, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("INPUT BUKU BARU");

        btn_exit_panel_input.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_input.setContentAreaFilled(false);
        btn_exit_panel_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_inputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit_panel_input, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_input_buku.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tblbuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblbuku);

        panel_input_buku.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 490, 460));

        getContentPane().add(panel_input_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        panel_reg_anggota.setBackground(new java.awt.Color(250, 250, 250));
        panel_reg_anggota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_reg_anggota.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_reg_anggotaComponentHidden(evt);
            }
        });
        panel_reg_anggota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_classroom_30px.png"))); // NOI18N
        jLabel30.setText("Kelas");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_registration_30px.png"))); // NOI18N
        jLabel32.setText("Nama Lengkap");

        tambahanggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        tambahanggota.setText("Tambah");
        tambahanggota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tambahanggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahanggotaActionPerformed(evt);
            }
        });

        hapusanggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_delete_30px.png"))); // NOI18N
        hapusanggota.setText("Hapus");
        hapusanggota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hapusanggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusanggotaActionPerformed(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_identification_documents_30px.png"))); // NOI18N
        jLabel31.setText("NIS");

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_android_30px.png"))); // NOI18N
        jLabel33.setText("No. HP");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_gender_30px.png"))); // NOI18N
        jLabel34.setText("Jenis Kelamin");

        btngroup_gender.add(rb_male);
        rb_male.setText("Laki-laki");
        rb_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_maleActionPerformed(evt);
            }
        });

        btngroup_gender.add(rb_female);
        rb_female.setText("Perempuan");
        rb_female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_femaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb_male)
                .addGap(18, 18, 18)
                .addComponent(rb_female)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_male)
                    .addComponent(rb_female))
                .addGap(36, 36, 36))
        );

        editanggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_delete_30px.png"))); // NOI18N
        editanggota.setText("Edit");
        editanggota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editanggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editanggotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(f_nis, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(f_kelas)
                            .addComponent(f_nama)
                            .addComponent(f_hp))
                        .addGap(208, 208, 208))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(tambahanggota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(editanggota)
                                .addGap(18, 18, 18)
                                .addComponent(hapusanggota)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(f_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(f_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_hp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahanggota)
                    .addComponent(hapusanggota)
                    .addComponent(editanggota))
                .addGap(36, 36, 36))
        );

        panel_reg_anggota.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 400, 440));

        jPanel2.setBackground(new java.awt.Color(255, 169, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("TAMBAH ANGGOTA PERPUSTAKAAN");

        btn_exit_panel_register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_register.setContentAreaFilled(false);
        btn_exit_panel_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_registerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_exit_panel_register, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_reg_anggota.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tblanggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblanggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblanggotaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblanggota);

        panel_reg_anggota.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 500, -1));

        getContentPane().add(panel_reg_anggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 540));

        panel_reg_admin.setBackground(new java.awt.Color(250, 250, 250));
        panel_reg_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_reg_admin.setFocusable(false);
        panel_reg_admin.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                panel_reg_adminComponentHidden(evt);
            }
        });
        panel_reg_admin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_admin_settings_male_30px.png"))); // NOI18N
        jLabel28.setText("Username");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_password_30px_2.png"))); // NOI18N
        jLabel29.setText("Password");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_registration_30px.png"))); // NOI18N
        jLabel27.setText("Nama Lengkap");

        btambahadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_save_30px.png"))); // NOI18N
        btambahadmin.setText("Tambah Admin");
        btambahadmin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btambahadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahadminActionPerformed(evt);
            }
        });

        bhapusadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_delete_30px.png"))); // NOI18N
        bhapusadmin.setText("Hapus");
        bhapusadmin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bhapusadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusadminActionPerformed(evt);
            }
        });

        beditadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_delete_30px.png"))); // NOI18N
        beditadmin.setText("Edit");
        beditadmin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        beditadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditadminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel28))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                .addComponent(b))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btambahadmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(beditadmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bhapusadmin)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btambahadmin)
                    .addComponent(beditadmin)
                    .addComponent(bhapusadmin))
                .addGap(107, 107, 107))
        );

        panel_reg_admin.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 430, 420));

        jPanel3.setBackground(new java.awt.Color(255, 169, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("TAMBAH ADMIN PERPUSTAKAAN (PUSTAKAWAN)");

        btn_exit_panel_reg_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_close_window_30px.png"))); // NOI18N
        btn_exit_panel_reg_admin.setContentAreaFilled(false);
        btn_exit_panel_reg_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_panel_reg_adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(147, 147, 147)
                .addComponent(btn_exit_panel_reg_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exit_panel_reg_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_reg_admin.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 50));

        tbladmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbladmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbladminMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbladmin);

        panel_reg_admin.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, 420));

        getContentPane().add(panel_reg_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 546));

        menukosong.setFocusable(false);
        menukosong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepanel.setBackground(new java.awt.Color(82, 0, 106));
        sidepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(82, 50, 150));

        btn_side_reg_anggota.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_reg_anggota.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_reg_anggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_registration_30px.png"))); // NOI18N
        btn_side_reg_anggota.setText("Register Anggota");
        btn_side_reg_anggota.setToolTipText("");
        btn_side_reg_anggota.setBorderPainted(false);
        btn_side_reg_anggota.setContentAreaFilled(false);
        btn_side_reg_anggota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_reg_anggota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_reg_anggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_side_reg_anggotaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_side_reg_anggotaMouseExited(evt);
            }
        });
        btn_side_reg_anggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_reg_anggotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_side_reg_anggota, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_reg_anggota, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 300, 50));

        jPanel5.setBackground(new java.awt.Color(82, 82, 180));

        btn_side_reg_admin.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_reg_admin.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_reg_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_admin_settings_male_30px.png"))); // NOI18N
        btn_side_reg_admin.setText("Register Admin");
        btn_side_reg_admin.setToolTipText("");
        btn_side_reg_admin.setBorderPainted(false);
        btn_side_reg_admin.setContentAreaFilled(false);
        btn_side_reg_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_reg_admin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_reg_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_reg_adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btn_side_reg_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_reg_admin, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, 50));

        jPanel6.setBackground(new java.awt.Color(82, 50, 150));

        btn_side_inputbuku.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_inputbuku.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_inputbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_add_book_30px.png"))); // NOI18N
        btn_side_inputbuku.setText("Input Buku Baru");
        btn_side_inputbuku.setToolTipText("");
        btn_side_inputbuku.setBorderPainted(false);
        btn_side_inputbuku.setContentAreaFilled(false);
        btn_side_inputbuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_inputbuku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_inputbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_inputbukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btn_side_inputbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_inputbuku, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 300, 50));

        jPanel7.setBackground(new java.awt.Color(82, 82, 180));

        btn_side_caribuku.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_caribuku.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_caribuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_search_30px.png"))); // NOI18N
        btn_side_caribuku.setText("Cari Buku");
        btn_side_caribuku.setToolTipText("");
        btn_side_caribuku.setBorderPainted(false);
        btn_side_caribuku.setContentAreaFilled(false);
        btn_side_caribuku.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_caribuku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_caribuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_caribukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btn_side_caribuku, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_caribuku, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, 50));

        jPanel8.setBackground(new java.awt.Color(82, 50, 150));

        btn_side_pinjam.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_pinjam.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_pinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_borrow_book_30px.png"))); // NOI18N
        btn_side_pinjam.setText("Peminjaman");
        btn_side_pinjam.setToolTipText("");
        btn_side_pinjam.setBorderPainted(false);
        btn_side_pinjam.setContentAreaFilled(false);
        btn_side_pinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_pinjam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_pinjamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btn_side_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_pinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, 51));

        jPanel10.setBackground(new java.awt.Color(82, 50, 150));

        btn_side_logout.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_logout.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_sign_out_30px_1.png"))); // NOI18N
        btn_side_logout.setText("Logout");
        btn_side_logout.setToolTipText("");
        btn_side_logout.setBorderPainted(false);
        btn_side_logout.setContentAreaFilled(false);
        btn_side_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_logout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(btn_side_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(btn_side_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sidepanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 300, 51));

        jPanel9.setBackground(new java.awt.Color(82, 82, 180));

        btn_side_kembali.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_kembali.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_return_book_30px.png"))); // NOI18N
        btn_side_kembali.setText("Pengembalian");
        btn_side_kembali.setToolTipText("");
        btn_side_kembali.setBorderPainted(false);
        btn_side_kembali.setContentAreaFilled(false);
        btn_side_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_kembali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btn_side_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, -1, 50));

        jPanel11.setBackground(new java.awt.Color(82, 50, 150));

        btn_side_cetak.setBackground(new java.awt.Color(82, 82, 180));
        btn_side_cetak.setForeground(new java.awt.Color(240, 240, 240));
        btn_side_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_print_30px.png"))); // NOI18N
        btn_side_cetak.setText("Cetak Laporan");
        btn_side_cetak.setToolTipText("");
        btn_side_cetak.setBorderPainted(false);
        btn_side_cetak.setContentAreaFilled(false);
        btn_side_cetak.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_side_cetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_side_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_side_cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btn_side_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_side_cetak, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        sidepanel.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, -1, 51));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/image/icons8_male_user_96px.png"))); // NOI18N
        sidepanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 90));

        jLabel19.setForeground(new java.awt.Color(240, 240, 240));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("PUSTAKAWAN");
        sidepanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 130, 30));

        menukosong.add(sidepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 680));

        banner.setBackground(new java.awt.Color(205, 17, 59));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(240, 240, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("SISTEM INFORMASI PERPUSTAKAAN SMK LENTERA BANGSA II");

        javax.swing.GroupLayout bannerLayout = new javax.swing.GroupLayout(banner);
        banner.setLayout(bannerLayout);
        bannerLayout.setHorizontalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        bannerLayout.setVerticalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bannerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        menukosong.add(banner, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        header.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        menukosong.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        footer.setBackground(new java.awt.Color(255, 169, 0));
        footer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("MyPerpus App V0.1 | 2021");
        footer.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 590, -1));

        menukosong.add(footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 650, -1, 30));

        getContentPane().add(menukosong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_reg_anggotaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_reg_anggotaComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_reg_anggotaComponentHidden

    private void btn_exit_panel_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_registerActionPerformed
        // TODO add your handling code here:
        panel_reg_anggota.setVisible(false);
    }//GEN-LAST:event_btn_exit_panel_registerActionPerformed

    private void btn_side_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_logoutActionPerformed
        new Login().show();
        dispose();
        clear();
        
      
    }//GEN-LAST:event_btn_side_logoutActionPerformed

    private void btn_side_reg_anggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_reg_anggotaActionPerformed
        // TODO add your handling code here:
        menukosong();
        panel_reg_anggota.setVisible(true);
        panel_reg_admin.setVisible(false);
        panel_input_buku.setVisible(false);
        clear();
        datatblanggota();
    }//GEN-LAST:event_btn_side_reg_anggotaActionPerformed

    private void btn_side_reg_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_reg_adminActionPerformed
        // TODO add your handling code here:
        menukosong();
        panel_reg_admin.setVisible(true);
        panel_reg_anggota.setVisible(false);
        panel_input_buku.setVisible(false);
        datatbladmin();
        clear();
    }//GEN-LAST:event_btn_side_reg_adminActionPerformed

    private void btn_side_inputbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_inputbukuActionPerformed
        // TODO add your handling code here:
        menukosong();
        panel_input_buku.setVisible(true);
        panel_reg_admin.setVisible(false);
        panel_reg_anggota.setVisible(false);
        datatblbuku();
        clear();
    }//GEN-LAST:event_btn_side_inputbukuActionPerformed

    private void btn_side_caribukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_caribukuActionPerformed
        menukosong();
        panel_caribuku.setVisible(true);
        datatblbuku();
        clear();
    }//GEN-LAST:event_btn_side_caribukuActionPerformed

    private void btn_side_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_pinjamActionPerformed
        menukosong();
        panel_pinjam.setVisible(true);
        Date date1 = new Date();
        Date date3 = new Date();
        date3 = new Date(date3.getTime() + 7*24*60*60*1000); 
        tgl1.setDate(date1);
        tgl2.setDate(date3);
        datatblpinjam();
        clear();

    }//GEN-LAST:event_btn_side_pinjamActionPerformed

    private void btn_side_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_kembaliActionPerformed
        menukosong();
        panel_pengembalian.setVisible(true);
        datatblpinjam();
        clear();
    }//GEN-LAST:event_btn_side_kembaliActionPerformed

    private void btn_side_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_side_cetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_side_cetakActionPerformed

    private void tambahanggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahanggotaActionPerformed
        String sql = "insert into anggota value (?,?,?,?,?)";
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, f_nis.getText());
            stat.setString (2, f_nama.getText());
            stat.setString (3, f_kelas.getText());
            stat.setString (4, f_hp.getText());
            stat.setString (5, btngroup_gender.getSelection().getActionCommand());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Anggota Berhasil Di Simpan");
            clear();
            f_nis.requestFocus();
            datatblanggota();        
      } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan)"+e);
            clear();
        }
    }//GEN-LAST:event_tambahanggotaActionPerformed

    private void hapusanggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusanggotaActionPerformed
        int ok =JOptionPane.showConfirmDialog(null,"Hapus "+f_nama.getText()+"?","Konfirmasi Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from anggota where nis='"+f_nis.getText()+"'";
            try {
                PreparedStatement stat = con.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
                clear();
                a.requestFocus();
                datatblanggota();             
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data"+e);
                clear();
            }
        }
    }//GEN-LAST:event_hapusanggotaActionPerformed

    private void simpanbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanbukuActionPerformed
        String sql = "insert into buku_master value (?,?,?,?,?,?)";
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, f_idbuku.getText());
            stat.setString (2, f_judul.getText());
            stat.setString (3, f_penulis.getText());
            stat.setString (4, f_penerbit.getText());
            stat.setString (5, f_tahun.getText());
            stat.setString (6, btngroup_kategori.getSelection().getActionCommand());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Buku Berhasil Di Simpan");
            clear();
            f_idbuku.requestFocus();
            datatblbuku();        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan)"+e);
            
          }
                                                
    }//GEN-LAST:event_simpanbukuActionPerformed

    private void btn_exit_panel_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_inputActionPerformed
        clear();
        panel_input_buku.setVisible(false);
    }//GEN-LAST:event_btn_exit_panel_inputActionPerformed

    private void panel_input_bukuComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_input_bukuComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_input_bukuComponentHidden

    private void btn_side_reg_anggotaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_side_reg_anggotaMouseExited
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(82,50,150));
    }//GEN-LAST:event_btn_side_reg_anggotaMouseExited

    private void btn_side_reg_anggotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_side_reg_anggotaMouseEntered
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(82,50,99));
    }//GEN-LAST:event_btn_side_reg_anggotaMouseEntered

    private void editanggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editanggotaActionPerformed
        int bar = tblanggota.getSelectedRow();
        String nis = tabmode1.getValueAt(bar, 0).toString();
        try {
            String sql = "update anggota set nis=?, nama=?, kelas=?, hp=?, jenkel=? "
                    +"where nis='"+nis+"'";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, f_nis.getText());
            stat.setString (2, f_nama.getText());
            stat.setString (3, f_kelas.getText());
            stat.setString (4, f_hp.getText());
            stat.setString (5, btngroup_gender.getSelection().getActionCommand());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data berhasil Di Ubah");
            clear();
            f_nis.requestFocus();
            datatblanggota();        
      } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah)"+ex);
        }
     //datatable(); 
    }//GEN-LAST:event_editanggotaActionPerformed

    private void rb_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_maleActionPerformed
        rb_male.setActionCommand("Laki-laki");
            btngroup_gender.add(rb_male);
    }//GEN-LAST:event_rb_maleActionPerformed

    private void rb_femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_femaleActionPerformed
        rb_female.setActionCommand("Perempuan");
            btngroup_gender.add(rb_female);
    }//GEN-LAST:event_rb_femaleActionPerformed

    private void tblanggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblanggotaMouseClicked
        int bar = tblanggota.getSelectedRow();
        f_nis.setText(tabmode1.getValueAt(bar, 0).toString());
        f_nama.setText(tabmode1.getValueAt(bar, 1).toString());
        f_kelas.setText(tabmode1.getValueAt(bar, 2).toString());
        f_hp.setText(tabmode1.getValueAt(bar, 3).toString());
        String jenis_kelamin = tabmode1.getValueAt(bar, 4).toString();
        if(jenis_kelamin.equals("Laki-laki")){
            btngroup_gender.clearSelection();
            rb_male.setSelected(true);rb_male.setActionCommand("Laki-laki");
            rb_female.setSelected(false);
            rb_male.requestFocus();
        }else {
            btngroup_gender.clearSelection();
            rb_female.setSelected(true);rb_female.setActionCommand("Perempuan");
            rb_male.setSelected(false);
            rb_female.requestFocus();
        }
    }//GEN-LAST:event_tblanggotaMouseClicked

    
    
    private void editbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbukuActionPerformed
        int bar = tblbuku.getSelectedRow();
        String idbuku = tabmode2.getValueAt(bar, 0).toString();
        try {
            String sql = "update buku_master set id=?, judul=?, penulis=?, penerbit=?, tahun=?, kategori=? "
                    +"where id='"+idbuku+"'";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, f_idbuku.getText());
            stat.setString (2, f_judul.getText());
            stat.setString (3, f_penulis.getText());
            stat.setString (4, f_penerbit.getText());
            stat.setString (5, f_tahun.getText());
            stat.setString (6, btngroup_kategori.getSelection().getActionCommand());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Buku Derhasil Di Update");
            clear();
            f_idbuku.requestFocus();
            datatblbuku();
            f_idbuku.setText("");
      } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Update)"+ex);
        }
    }//GEN-LAST:event_editbukuActionPerformed

    private void hapusbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusbukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusbukuActionPerformed

    private void pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinjamActionPerformed
        String sql = "insert into peminjaman value (?,?,?,?,?,?,?,?)";
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm =new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl1.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl2.getDate()));
        String idbukucek = (String) cbpinjam1.getSelectedItem();
        try {
            Connection con = new koneksi().open();
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, null);
            stat.setString (2, (String) cbpinjam1.getSelectedItem());
            stat.setString (3, f_judul1.getText());
            stat.setString (4, f_penerbit1.getText());
            stat.setString (5, (String) cbpinjam2.getSelectedItem());
            stat.setString (6, f_nama1.getText());
            stat.setString (7, tanggal1);
            stat.setString (8, tanggal2);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Peminjaman Berhasil Di Simpan");
            clear();
            cbpinjam1.requestFocus();
            datatblpinjam();        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan, Buku dengan ID="+idbukucek+" sudah dipinjam\n atau cek eror "+e);
            
          }
    }//GEN-LAST:event_pinjamActionPerformed

    private void editpinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpinjamActionPerformed
        int bar = tblpinjam.getSelectedRow();
        String idbuku = tabmode3.getValueAt(bar, 1).toString();
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm =new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl1.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl2.getDate()));
        try {
            String sql = "update peminjaman set no=?, idbuku=?, judul=?, penerbit=?, idanggota=?, nama=?, tglpinjam=?, tglkembali=? "
                    +"where idbuku='"+idbuku+"'";
            
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString (1, f_no1.getText());
            stat.setString (2, (String)cbpinjam1.getSelectedItem());
            stat.setString (3, f_judul1.getText());
            stat.setString (4, f_penerbit1.getText());
            stat.setString (5, (String)cbpinjam2.getSelectedItem());
            stat.setString (6, f_nama1.getText());
            stat.setString (7, tanggal1);
            stat.setString (8, tanggal2);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Peminjaman Derhasil Di Update");
            clear();
            cbpinjam1.requestFocus();
            datatblpinjam();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Update "+ex);
          }
    }//GEN-LAST:event_editpinjamActionPerformed

    private void hapuspinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapuspinjamActionPerformed
        int ok =JOptionPane.showConfirmDialog(null,"Hapus ID Peminjaman "+f_no1.getText()+"?","Konfirmasi Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from peminjaman where no='"+f_no1.getText()+"'";
            try {
                PreparedStatement stat = con.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
                clear();
                a.requestFocus();
                datatblpinjam();             
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data"+e);
                clear();
            }
        }
    }//GEN-LAST:event_hapuspinjamActionPerformed

    private void btn_exit_panel_input1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_input1ActionPerformed
        menukosong();
    }//GEN-LAST:event_btn_exit_panel_input1ActionPerformed

    private void panel_pinjamComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_pinjamComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_pinjamComponentHidden

    private void tblbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseClicked
        int bar = 0;
        bar = tblbuku.getSelectedRow();
        f_idbuku.setText(tabmode2.getValueAt(bar, 0).toString());
        f_judul.setText(tabmode2.getValueAt(bar, 1).toString());
        f_penulis.setText(tabmode2.getValueAt(bar, 2).toString());
        f_penerbit.setText(tabmode2.getValueAt(bar, 3).toString());
        f_tahun.setText(tabmode2.getValueAt(bar, 4).toString());
        String kat = tabmode2.getValueAt(bar, 5).toString();
        if(kat.equals("Umum")){
            btngroup_kategori.clearSelection();
            cbumum.setSelected(true);cbumum.setActionCommand("Umum");
            cbumum.requestFocus();
        }else if (kat.equals("Sosial")){
            btngroup_kategori.clearSelection();
            cbsosial.setSelected(true);cbsosial.setActionCommand("Sosial");
            cbsosial.requestFocus();
        }else if (kat.equals("Sejarah")){
            btngroup_kategori.clearSelection();
            cbsejarah.setSelected(true);cbsejarah.setActionCommand("Sejarah");
            cbsejarah.requestFocus();
        }else if (kat.equals("Seni")){
            btngroup_kategori.clearSelection();
            cbseni.setSelected(true);cbseni.setActionCommand("Seni");
            cbseni.requestFocus();
        }else if (kat.equals("Agama")){
            btngroup_kategori.clearSelection();
            cbagama.setSelected(true);cbagama.setActionCommand("Agama");
            cbagama.requestFocus();
        }else if (kat.equals("Bahasa")){
            btngroup_kategori.clearSelection();
            cbbahasa.setSelected(true);cbbahasa.setActionCommand("Bahasa");
            cbbahasa.requestFocus();
        }else if (kat.equals("MIPA")){
            btngroup_kategori.clearSelection();
            cbmipa.setSelected(true);cbmipa.setActionCommand("MIPA");
            cbmipa.requestFocus();
        }else if (kat.equals("Teknologi")){
            btngroup_kategori.clearSelection();
            cbtekno.setSelected(true);cbtekno.setActionCommand("Teknologi");
            cbtekno.requestFocus();
        }else {
            btngroup_kategori.clearSelection();
        }
    }//GEN-LAST:event_tblbukuMouseClicked

    private void cbumumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbumumActionPerformed
        cbumum.setActionCommand("Umum");btngroup_gender.add(cbumum);
    }//GEN-LAST:event_cbumumActionPerformed

    private void cbsosialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsosialActionPerformed
        cbsosial.setActionCommand("Sosial");btngroup_gender.add(cbsosial);
    }//GEN-LAST:event_cbsosialActionPerformed

    private void cbsejarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsejarahActionPerformed
        cbsejarah.setActionCommand("Sejarah");btngroup_gender.add(cbsejarah);
    }//GEN-LAST:event_cbsejarahActionPerformed

    private void cbseniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbseniActionPerformed
        cbseni.setActionCommand("Seni");btngroup_gender.add(cbseni);
    }//GEN-LAST:event_cbseniActionPerformed

    private void cbagamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbagamaActionPerformed
        cbagama.setActionCommand("Agama");btngroup_gender.add(cbagama);
    }//GEN-LAST:event_cbagamaActionPerformed

    private void cbbahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbahasaActionPerformed
        cbbahasa.setActionCommand("Bahasa");btngroup_gender.add(cbbahasa);
    }//GEN-LAST:event_cbbahasaActionPerformed

    private void cbmipaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmipaActionPerformed
        cbmipa.setActionCommand("MIPA");btngroup_gender.add(cbmipa);
    }//GEN-LAST:event_cbmipaActionPerformed

    private void cbteknoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbteknoActionPerformed
        cbtekno.setActionCommand("Teknologi");btngroup_gender.add(cbtekno);
    }//GEN-LAST:event_cbteknoActionPerformed

    private void tblpinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpinjamMouseClicked
        int bar = tblpinjam.getSelectedRow();
        cbpinjam1.setSelectedItem(tabmode3.getValueAt(bar, 1).toString());
        f_no1.setText(tabmode3.getValueAt(bar, 0).toString());
        cbpinjam2.setSelectedItem(tabmode3.getValueAt(bar, 4).toString());
        tampildetailbuku();
        tampilnama();
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse((String)tabmode3.getValueAt(bar,6));
            tgl1.setDate(date);
            date = new SimpleDateFormat("dd-MM-yyyy").parse((String)tabmode3.getValueAt(bar,7));
            tgl2.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblpinjamMouseClicked

    private void cbpinjam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbpinjam1ActionPerformed
        tampildetailbuku();
    }//GEN-LAST:event_cbpinjam1ActionPerformed

    private void cbpinjam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbpinjam2ActionPerformed
        tampilnama();
    }//GEN-LAST:event_cbpinjam2ActionPerformed

    private void panel_reg_adminComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_reg_adminComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_reg_adminComponentHidden

    private void tbladminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbladminMouseClicked
        int bar = tbladmin.getSelectedRow();
        a.setText(tabmode.getValueAt(bar, 0).toString());
        b.setText(tabmode.getValueAt(bar, 1).toString());
        //c.setText(tabmode.getValueAt(bar, 2).toString());
        String sql = "select * from t_login where username='"+b.getText()+"'";
        try{
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String user = hasil.getString("username");
                String pass = hasil.getString("password");
                String nama = hasil.getString("nama");
                c.setText(pass);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Gagal Melihat Password"+e);
        }
    }//GEN-LAST:event_tbladminMouseClicked

    private void btn_exit_panel_reg_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_reg_adminActionPerformed
        // TODO add your handling code here:
        panel_reg_admin.setVisible(false);
    }//GEN-LAST:event_btn_exit_panel_reg_adminActionPerformed

    private void beditadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditadminActionPerformed
        try {
            if (c.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Silakan Masukkan Password Anda Kembali", "Pesan", JOptionPane.ERROR_MESSAGE);
            }else{
                String uname = b.getText();
                String sql = "update t_login set username=?, password=?, nama=? "
                + "where username='"+uname+"'";
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setString(1,b.getText());//username in form
                stat.setString(2,c.getText());//password in form
                stat.setString(3,a.getText());//nama in form
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                clear();
                a.requestFocus();
                datatbladmin();
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Terjadi Error / Data Tidak Benar", "Pesan",JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }//GEN-LAST:event_beditadminActionPerformed

    private void bhapusadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusadminActionPerformed
        int ok =JOptionPane.showConfirmDialog(null,"Hapus "+a.getText()+"?","Konfirmasi Dialog",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from t_login where username ='"+b.getText()+"'";
            try {
                PreparedStatement stat = con.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
                clear();
                a.requestFocus();
                datatbladmin();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data"+e);

            }
        }
    }//GEN-LAST:event_bhapusadminActionPerformed

    private void btambahadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahadminActionPerformed
        String sql = "insert into t_login value (?,?,?)";
        try {
            if (a.getText().equals("") ||
                b.getText().equals("") ||
                c.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong", "Pesan", JOptionPane.ERROR_MESSAGE);
                //hapuslayar();
            }else{
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setString(1,b.getText());
                stat.setString(2,c.getText());
                stat.setString(3,a.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
                clear();
                a.requestFocus();
                datatbladmin();
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Terjadi Error / Data Tidak Benar", "Pesan",JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }//GEN-LAST:event_btambahadminActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cActionPerformed

    private void cekdendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekdendaActionPerformed
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm =new SimpleDateFormat(tampilan);
        //String tgl0 = String.valueOf(fm.format(tgl3.getDate()));
        String tgl0 = tgl3.getText();
        String tgl1 = String.valueOf(fm.format(tgl4.getDate()));
        if (tgl0.compareTo(tgl1) >= 0){
            denda.setText("Bebas Denda");
        }else if (tgl0.compareTo(tgl1) == -1){
            denda.setText("1000");
        }else if (tgl0.compareTo(tgl1) == -2){
            denda.setText("2000");
        }else if (tgl0.compareTo(tgl1) == -3){
            denda.setText("3000");
        }else if (tgl0.compareTo(tgl1) == -4){
            denda.setText("4000");
        }else if (tgl0.compareTo(tgl1) == -5){
            denda.setText("5000");
        }else {
            denda.setText("10000");
        }
            
    }//GEN-LAST:event_cekdendaActionPerformed

    private void btn_exit_panel_input2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_input2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_exit_panel_input2ActionPerformed

    private void tblpinjam1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpinjam1MouseClicked
        int bar = tblpinjam1.getSelectedRow();
        f_idbuku2.setText(tabmode3.getValueAt(bar, 1).toString());
        f_judul2.setText(tabmode3.getValueAt(bar, 2).toString());
        f_penerbit2.setText(tabmode3.getValueAt(bar, 3).toString());
        f_idanggota2.setText(tabmode3.getValueAt(bar, 4).toString());
        f_nama2.setText(tabmode3.getValueAt(bar, 5).toString());
        tgl3.setText(tabmode3.getValueAt(bar, 7).toString());
        tgl5.setText(tabmode3.getValueAt(bar, 6).toString());
        Date date2 = new Date();
        date2 = new Date(date2.getTime()); 
        //date2 = new Date(date2.getTime() + 2*24*60*60*1000); 
        tgl4.setDate(date2);
        //tampildetailbuku();
        //tampilnama();
        //tgl3.setText((String)tabmode3.getValueAt(bar,7));
        //try {
        //    
            //tgl3.setDate(date);
        //    Date date2 = new Date();
        //    //date2 = new Date(date2.getTime() + 2*24*60*60*1000); 
        //    tgl4.setDate(date2);
        //} catch (ParseException ex) {
        //    Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_tblpinjam1MouseClicked

    private void panel_pengembalianComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_pengembalianComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_pengembalianComponentHidden

    private void f_idbuku2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_idbuku2ActionPerformed

    }//GEN-LAST:event_f_idbuku2ActionPerformed

    private void tgl1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tgl1InputMethodTextChanged

    }//GEN-LAST:event_tgl1InputMethodTextChanged

    private void dendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dendaActionPerformed

    private void btn_exit_panel_register1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_panel_register1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_exit_panel_register1ActionPerformed

    private void tblbuku1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbuku1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblbuku1MouseClicked

    private void panel_caribukuComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_caribukuComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_caribukuComponentHidden

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        searchTableContents(f_cari.getText());
    }//GEN-LAST:event_btncariActionPerformed

    private void simpanbalikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanbalikActionPerformed
        if(denda.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Silakan Cek Denda Terlebih Dahulu", "Pesan", JOptionPane.ERROR_MESSAGE);
            cekdenda.requestFocus();
    }//GEN-LAST:event_simpanbalikActionPerformed

    private void f_cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_cari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_cari1ActionPerformed

    private void btncaripinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripinjamActionPerformed
        searchTableContents1(f_cari1.getText());
    }//GEN-LAST:event_btncaripinjamActionPerformed

    public void searchTableContents(String searchString) {
    DefaultTableModel currtableModel = (DefaultTableModel)tblbuku1.getModel();
    //To empty the table before search
    currtableModel.setRowCount(0);
    //To search for contents from original table content
    for (Object rows : originalTableModel) {
        Vector rowVector = (Vector) rows;
        for (Object column : rowVector) {
            if (column.toString().contains(searchString)) {
                //content found so adding to table
                currtableModel.addRow(rowVector);
                break;
            }
        }

    }
}
    
    public void searchTableContents1(String searchString) {
    DefaultTableModel currtableModel = (DefaultTableModel)tblpinjam1.getModel();
    //To empty the table before search
    currtableModel.setRowCount(0);
    //To search for contents from original table content
    for (Object rows : originalTableModel1) {
        Vector rowVector = (Vector) rows;
        for (Object column : rowVector) {
            if (column.toString().contains(searchString)) {
                //content found so adding to table
                currtableModel.addRow(rowVector);
                break;
            }
        }

    }
}

    
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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama(null,false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    private javax.swing.JTextField b;
    private javax.swing.JPanel banner;
    private javax.swing.JButton beditadmin;
    private javax.swing.JButton bhapusadmin;
    private javax.swing.JButton btambahadmin;
    private javax.swing.JButton btn_exit_panel_input;
    private javax.swing.JButton btn_exit_panel_input1;
    private javax.swing.JButton btn_exit_panel_input2;
    private javax.swing.JButton btn_exit_panel_reg_admin;
    private javax.swing.JButton btn_exit_panel_register;
    private javax.swing.JButton btn_exit_panel_register1;
    private javax.swing.JButton btn_side_caribuku;
    private javax.swing.JButton btn_side_cetak;
    private javax.swing.JButton btn_side_inputbuku;
    private javax.swing.JButton btn_side_kembali;
    private javax.swing.JButton btn_side_logout;
    private javax.swing.JButton btn_side_pinjam;
    private javax.swing.JButton btn_side_reg_admin;
    private javax.swing.JButton btn_side_reg_anggota;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncaripinjam;
    private javax.swing.ButtonGroup btngroup_gender;
    private javax.swing.ButtonGroup btngroup_kategori;
    private javax.swing.JPasswordField c;
    private javax.swing.JRadioButton cbagama;
    private javax.swing.JRadioButton cbbahasa;
    private javax.swing.JRadioButton cbmipa;
    private javax.swing.JComboBox<String> cbpinjam1;
    private javax.swing.JComboBox<String> cbpinjam2;
    private javax.swing.JRadioButton cbsejarah;
    private javax.swing.JRadioButton cbseni;
    private javax.swing.JRadioButton cbsosial;
    private javax.swing.JRadioButton cbtekno;
    private javax.swing.JRadioButton cbumum;
    private javax.swing.JButton cekdenda;
    private javax.swing.JTextField denda;
    private javax.swing.JButton editanggota;
    private javax.swing.JButton editbuku;
    private javax.swing.JButton editpinjam;
    private javax.swing.JTextField f_cari;
    private javax.swing.JTextField f_cari1;
    private javax.swing.JTextField f_hp;
    private javax.swing.JTextField f_idanggota2;
    private javax.swing.JTextField f_idbuku;
    private javax.swing.JTextField f_idbuku2;
    private javax.swing.JTextField f_judul;
    private javax.swing.JTextField f_judul1;
    private javax.swing.JTextField f_judul2;
    private javax.swing.JTextField f_kelas;
    private javax.swing.JTextField f_nama;
    private javax.swing.JTextField f_nama1;
    private javax.swing.JTextField f_nama2;
    private javax.swing.JTextField f_nis;
    private javax.swing.JTextField f_no1;
    private javax.swing.JTextField f_penerbit;
    private javax.swing.JTextField f_penerbit1;
    private javax.swing.JTextField f_penerbit2;
    private javax.swing.JTextField f_penulis;
    private javax.swing.JTextField f_tahun;
    private javax.swing.JPanel footer;
    private javax.swing.JButton hapusanggota;
    private javax.swing.JButton hapusbuku;
    private javax.swing.JButton hapuspinjam;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel menukosong;
    private javax.swing.JPanel panel_caribuku;
    private javax.swing.JPanel panel_input_buku;
    private javax.swing.JPanel panel_pengembalian;
    private javax.swing.JPanel panel_pinjam;
    private javax.swing.JPanel panel_reg_admin;
    private javax.swing.JPanel panel_reg_anggota;
    private javax.swing.JButton pinjam;
    private javax.swing.JRadioButton rb_female;
    private javax.swing.JRadioButton rb_male;
    private javax.swing.JPanel sidepanel;
    private javax.swing.JButton simpanbalik;
    private javax.swing.JButton simpanbuku;
    private javax.swing.JButton tambahanggota;
    private javax.swing.JTable tbladmin;
    private javax.swing.JTable tblanggota;
    private javax.swing.JTable tblbuku;
    private javax.swing.JTable tblbuku1;
    private javax.swing.JTable tblpinjam;
    private javax.swing.JTable tblpinjam1;
    private com.toedter.calendar.JDateChooser tgl1;
    private com.toedter.calendar.JDateChooser tgl2;
    private javax.swing.JTextField tgl3;
    private com.toedter.calendar.JDateChooser tgl4;
    private javax.swing.JTextField tgl5;
    // End of variables declaration//GEN-END:variables
}
