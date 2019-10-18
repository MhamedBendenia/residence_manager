/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application;

import javax.swing.JOptionPane;
import java.sql.*;
import app.connect;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Bendenia M'hamed
 */

public class Personne extends javax.swing.JFrame {
    private static final long serialVersionUID = 1;
    connect cnx=new connect();
    int  Id;
    String imgpath = "/app/icones/icons8-ajouter-un-groupe-d'utilisateurs-homme-homme-64.png";
    Statement stat;
    ResultSet rs;
    DefaultTableModel model=new DefaultTableModel();
    File f = null;
    FileInputStream fis;
    connect maconnexion = new connect();
    
    /**
     * Creates new form Personne
     */
    
    public static final Logger LOGGER = Logger.getLogger(Personne.class.getName());
    public Personne() {
        initComponents();
        start();
        this.setLocationRelativeTo(null);
    }   
    
    public void start(){
        model.addColumn("USERID");
        model.addColumn("CardNo");
        model.addColumn("name");
        model.addColumn("lastname");
        model.addColumn("nom_ar");
        model.addColumn("prénom_ar");
        model.addColumn("BIRTHDAY");
        model.addColumn("CITY");
        model.addColumn("EDUCATION");
        model.addColumn("TITLE");
        model.addColumn("Badgenumber");
        model.addColumn("année_bac");
        model.addColumn("street");
        model.addColumn("homeaddress");
        model.addColumn("STATE");
        model.addColumn("catégorie");
        model.addColumn("numchambre");
        model.addColumn("education_ar");
        model.addColumn("acc_startdate");
        model.addColumn("acc_enddate");
        model.addColumn("create_time");
        model.addColumn("etat");
                
        try{
            Date t = new Date();
            String sql="Select * from userinfo";
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery(sql);
            while(rs.next()){
                Date d = rs.getDate("acc_enddate");
                String e = rs.getString("etat");
                if((d.compareTo(t)<0) && (e.equals("Actif"))){
                    stat.executeUpdate("UPDATE userinfo SET  etat= 'Non actif' where CardNo='"+rs.getString("CardNo")+"'");
                }
                model.addRow(new Object[]{rs.getString("USERID"),rs.getString("CardNo"),rs.getString("name"),rs.getString("lastname"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("BIRTHDAY"),rs.getString("CITY"),rs.getString("EDUCATION"),rs.getString("TITLE"),rs.getString("Badgenumber"),rs.getString("année_bac"),rs.getString("street"),rs.getString("homeaddress"),rs.getString("STATE"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("education_ar"),rs.getDate("acc_startdate"),rs.getDate("acc_enddate"),rs.getString("create_time"),rs.getString("etat")});
                
            }
        }catch(SQLException ex){
            System.err.println(ex);
        }
        table.setModel(model);
        updatelist();
        actualiser();
    }
       
    
    @SuppressWarnings("unchecked")
    private void deplace(int i){
        try{


            id_cardt.setText(model.getValueAt(i,1).toString());
            nomt.setText(model.getValueAt(i,2).toString());
            prenomt.setText(model.getValueAt(i,3).toString());
            nom_art.setText(model.getValueAt(i,4).toString());
            prenom_art.setText(model.getValueAt(i,5).toString());
            dateNst.setDate((Date)model.getValueAt(i,6));
            lieut.setText(model.getValueAt(i,7).toString());
            filièret.setText(model.getValueAt(i,8).toString());
            niveaut.setText(model.getValueAt(i,9).toString());
            num_bact.setText(model.getValueAt(i,10).toString());
            année_bact.setText(model.getValueAt(i,11).toString());
            adresset.setText(model.getValueAt(i,12).toString());
            wilayat.setText(model.getValueAt(i,13).toString());
            nationalitét.setText(model.getValueAt(i,14).toString());
            catt.setSelectedItem(model.getValueAt(i,15).toString());
            numchambret.setSelectedValue(model.getValueAt(i,16), true);
            String sql="Select PHOTO from userinfo WHERE CardNo='"+id_cardt.getText()+"'";
            stat=cnx.obtenirconnexion().createStatement();
            seticonimage(stat.executeQuery(sql));
            filière_art.setText(model.getValueAt(i,17).toString());
            acc_startdatet.setDate((Date)model.getValueAt(i,18));
            acc_enddatet.setDate((Date)model.getValueAt(i,19));
            etatt.setSelectedItem(model.getValueAt(i,21).toString());
        }catch(Exception e){
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "erreur de déplacement "+e.getLocalizedMessage());
        }
    }
    public final void updatelist(){
        try{
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery("select * from document1");
            DefaultListModel<String> listModel = new DefaultListModel<>();
            while(rs.next()){
                listModel.addElement("Chambre: "+rs.getString("numchambre")+" | Pavillon:"+rs.getString("numbloc"));
            }
            numchambret.setModel(listModel);
        }catch(SQLException e){
                System.err.println(e);
                table.setModel(model);
            }
    }
    public void actualiser(){
        id_cardt.setText(null);
        nomt.setText(null);
        prenomt.setText(null);
        nom_art.setText(null);
        prenom_art.setText(null);
        dateNst.setDate(null);
        lieut.setText(null);
        filièret.setText(null);
        niveaut.setText(null);
        num_bact.setText(null);
        année_bact.setText(null);
        adresset.setText(null);
        wilayat.setText(null);
        nationalitét.setText(null);
        numchambret.setSelectedIndex(0);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgpath)));
        filière_art.setText(null);
        Date dt = new Date();    
        acc_startdatet.setDate(dt);
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        c.add(Calendar.YEAR, 1);
        dt = c.getTime();
        acc_enddatet.setDate(dt);
       
        try{
            model.setRowCount(0);
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery("select * from userinfo");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("USERID"),rs.getString("CardNo"),rs.getString("name"),rs.getString("lastname"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("BIRTHDAY"),rs.getString("CITY"),rs.getString("EDUCATION"),rs.getString("TITLE"),rs.getString("Badgenumber"),rs.getString("année_bac"),rs.getString("street"),rs.getString("homeaddress"),rs.getString("STATE"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("education_ar"),rs.getDate("acc_startdate"),rs.getDate("acc_enddate"),rs.getString("create_time"),rs.getString("etat")});
            }}catch(SQLException e){
                System.err.println(e);
                table.setModel(model);
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nom = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        telephone = new javax.swing.JLabel();
        adresse = new javax.swing.JLabel();
        categorie = new javax.swing.JLabel();
        nomt = new javax.swing.JTextField();
        lieut = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        prenomt = new javax.swing.JTextField();
        Close = new javax.swing.JLabel();
        adresset = new javax.swing.JTextField();
        catt = new javax.swing.JComboBox<>();
        departement = new javax.swing.JLabel();
        dateN = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Min = new javax.swing.JLabel();
        recherche = new javax.swing.JButton();
        recherchet = new javax.swing.JTextField();
        modifier = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        id_cardt = new javax.swing.JTextField();
        dateNst = new com.toedter.calendar.JDateChooser();
        actualiser = new javax.swing.JLabel();
        ajouterchambre = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        numchambret = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        printbutton = new javax.swing.JButton();
        filièret = new javax.swing.JTextField();
        niveaut = new javax.swing.JTextField();
        adresse1 = new javax.swing.JLabel();
        adresse2 = new javax.swing.JLabel();
        export = new javax.swing.JButton();
        Imprimer = new javax.swing.JButton();
        nom_art = new javax.swing.JTextField();
        prenom_art = new javax.swing.JTextField();
        telephone1 = new javax.swing.JLabel();
        wilayat = new javax.swing.JTextField();
        nationalitét = new javax.swing.JTextField();
        adresse3 = new javax.swing.JLabel();
        num_bact = new javax.swing.JTextField();
        année_bact = new javax.swing.JTextField();
        adresse4 = new javax.swing.JLabel();
        filière_art = new javax.swing.JTextField();
        Imprimer1 = new javax.swing.JButton();
        telephone2 = new javax.swing.JLabel();
        etatt = new javax.swing.JComboBox<>();
        telephone3 = new javax.swing.JLabel();
        telephone4 = new javax.swing.JLabel();
        acc_startdatet = new com.toedter.calendar.JDateChooser();
        jCheckBox1 = new javax.swing.JCheckBox();
        acc_enddatet = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        nom.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        nom.setText("Nom : *");

        prenom.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        prenom.setText("Prénom : *");

        telephone.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        telephone.setText("Wilaya :");

        adresse.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        adresse.setText("Adresse :");

        categorie.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        categorie.setText("Catégorie :");

        nomt.setText("FR");
        nomt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nomt.setNextFocusableComponent(prenomt);
        nomt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomtActionPerformed(evt);
            }
        });

        lieut.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lieut.setNextFocusableComponent(filièret);
        lieut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lieutActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-plus-24.png"))); // NOI18N
        jButton1.setText("Ajouter");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-ajouter-un-groupe-d'utilisateurs-homme-homme-64.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-lecture-16.png"))); // NOI18N
        jLabel10.setText("Resident");

        prenomt.setText("FR");
        prenomt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        prenomt.setNextFocusableComponent(nom_art);
        prenomt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomtActionPerformed(evt);
            }
        });

        Close.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        Close.setText(" X");
        Close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseMouseClicked(evt);
            }
        });

        adresset.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        adresset.setNextFocusableComponent(wilayat);
        adresset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adressetActionPerformed(evt);
            }
        });

        catt.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        catt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Etudiant", "Autre" }));
        catt.setNextFocusableComponent(numchambret);
        catt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cattActionPerformed(evt);
            }
        });

        departement.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        departement.setText("Chambre :");

        dateN.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        dateN.setText("Date et lieu naiss : *");

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jButton2.setBackground(new java.awt.Color(255, 102, 51));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-trash-can-26.png"))); // NOI18N
        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Min.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        Min.setText("_");
        Min.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinMouseClicked(evt);
            }
        });

        recherche.setBackground(new java.awt.Color(204, 204, 255));
        recherche.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        recherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-chercher-26.png"))); // NOI18N
        recherche.setText("Rechercher");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        recherchet.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        recherchet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherchetActionPerformed(evt);
            }
        });

        modifier.setBackground(new java.awt.Color(102, 204, 255));
        modifier.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        modifier.setForeground(new java.awt.Color(255, 255, 255));
        modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-réglages-horizontal-24.png"))); // NOI18N
        modifier.setText("Modifier");
        modifier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        id.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        id.setText("identifiant :");

        id_cardt.setNextFocusableComponent(nomt);
        id_cardt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_cardtActionPerformed(evt);
            }
        });

        dateNst.setNextFocusableComponent(lieut);

        actualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-rafraîchir-26.png"))); // NOI18N
        actualiser.setToolTipText("Actualiser");
        actualiser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualiser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualiserMouseClicked(evt);
            }
        });

        ajouterchambre.setBackground(new java.awt.Color(204, 204, 255));
        ajouterchambre.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        ajouterchambre.setForeground(new java.awt.Color(255, 255, 255));
        ajouterchambre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-plus-24.png"))); // NOI18N
        ajouterchambre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ajouterchambre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterchambreMouseClicked(evt);
            }
        });
        ajouterchambre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterchambreActionPerformed(evt);
            }
        });

        numchambret.setNextFocusableComponent(jButton1);
        jScrollPane2.setViewportView(numchambret);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-mises-à-jour-disponibles-26.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        printbutton.setBackground(new java.awt.Color(204, 204, 255));
        printbutton.setText("carte d'accés");
        printbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbuttonActionPerformed(evt);
            }
        });

        filièret.setText("FR");
        filièret.setNextFocusableComponent(filière_art);
        filièret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filièretActionPerformed(evt);
            }
        });

        niveaut.setNextFocusableComponent(num_bact);
        niveaut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niveautActionPerformed(evt);
            }
        });

        adresse1.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        adresse1.setText("Filière :");

        adresse2.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        adresse2.setText("Année :");

        export.setText("Exporter (xls)");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        Imprimer.setText("شهادة الإواء");
        Imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimerActionPerformed(evt);
            }
        });

        nom_art.setText("AR");
        nom_art.setNextFocusableComponent(prenom_art);
        nom_art.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_artActionPerformed(evt);
            }
        });

        prenom_art.setText("AR");
        prenom_art.setNextFocusableComponent(dateNst);

        telephone1.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        telephone1.setText("Nationalité :");

        wilayat.setNextFocusableComponent(nationalitét);

        nationalitét.setNextFocusableComponent(catt);

        adresse3.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        adresse3.setText("Niveau :");

        num_bact.setNextFocusableComponent(année_bact);

        année_bact.setNextFocusableComponent(adresset);

        adresse4.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        adresse4.setText("Numéro BAC :");

        filière_art.setText("AR");
        filière_art.setNextFocusableComponent(niveaut);

        Imprimer1.setText("شهادة عدم الإواء");
        Imprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Imprimer1ActionPerformed(evt);
            }
        });

        telephone2.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        telephone2.setText("Début :");

        etatt.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        etatt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Actif", "Non actif", "Bloqué" }));

        telephone3.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        telephone3.setText("Etat :");

        telephone4.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        telephone4.setText("Fin :");

        acc_startdatet.setNextFocusableComponent(lieut);

        jCheckBox1.setText("غير مقيم السنة الماضية");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        acc_enddatet.setNextFocusableComponent(lieut);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateN)
                            .addComponent(prenom)
                            .addComponent(id)
                            .addComponent(nom)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adresse3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adresse1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(recherchet, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recherche)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(id_cardt, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nomt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(niveaut, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(dateNst, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(prenomt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(filièret, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(prenom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lieut, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(filière_art, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(telephone)
                                            .addComponent(adresse)
                                            .addComponent(adresse4)
                                            .addComponent(departement))
                                        .addGap(6, 6, 6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(categorie)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(catt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(326, 326, 326))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(365, 365, 365))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(telephone1)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adresset)
                            .addComponent(wilayat)
                            .addComponent(nationalitét)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(num_bact, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adresse2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(année_bact))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telephone2)
                                    .addComponent(telephone3)
                                    .addComponent(telephone4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etatt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(acc_startdatet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(acc_enddatet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ajouterchambre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(printbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Imprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(Imprimer1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Min, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Close)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(export))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_cardt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id)
                            .addComponent(adresse4)
                            .addComponent(num_bact, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adresse2)
                            .addComponent(année_bact, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adresset)
                            .addComponent(nomt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nom)
                                .addComponent(nom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adresse))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Min, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(prenom)
                                .addComponent(prenomt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prenom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(wilayat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telephone))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etatt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone3))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(telephone2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(acc_startdatet, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acc_enddatet, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephone4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lieut, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateNst, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nationalitét, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephone1))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(filière_art, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filièret, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adresse1)
                                .addComponent(categorie))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(catt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(printbutton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Imprimer)
                            .addComponent(ajouterchambre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Imprimer1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(niveaut, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adresse3)
                                .addComponent(departement))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(dateN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(recherchet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(export)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nom_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_artActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_artActionPerformed

    private void ImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimerActionPerformed
        if(id_cardt.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Formulaire vide !");
        }else{

            String reportSource = "/application/attestation.jasper";
            InputStream reportFile = null;
            reportFile = getClass().getResourceAsStream(reportSource);
            Connection conn = cnx.obtenirconnexion();
            Map<String, Object> params = new HashMap<>();
            params.put("card_id", id_cardt.getText());
            params.put("year", Calendar.getInstance().get(Calendar.YEAR));
            params.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            try
            {
                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile, params, conn);
                JasperViewer.viewReport(jasperPrint,false);
            }
            catch (JRException ex)
            {
                JOptionPane.showMessageDialog(null,"Erreur ! "+ex.getMessage());
            }
        }
 
    }//GEN-LAST:event_ImprimerActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        FileWriter excel = null;
        try {
            File file = new File(System.getProperty("user.home")+"/Desktop/export.xls");
            excel = new FileWriter(file);
            for(int i = 0; i < model.getColumnCount(); i++){
                if(model.getColumnName(i).equalsIgnoreCase("prénom_ar")||model.getColumnName(i).equalsIgnoreCase("nom_ar")||model.getColumnName(i).equalsIgnoreCase("USERID")||model.getColumnName(i).equalsIgnoreCase("imgpath")||model.getColumnName(i).equalsIgnoreCase("cat"))
                    continue;
                excel.write(model.getColumnName(i) + "\t");
            }   excel.write("\n");
            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    if(model.getColumnName(j).equalsIgnoreCase("prénom_ar")||model.getColumnName(j).equalsIgnoreCase("nom_ar")||model.getColumnName(j).equalsIgnoreCase("USERID")||model.getColumnName(j).equalsIgnoreCase("imgpath")||model.getColumnName(j).equalsIgnoreCase("cat"))
                        continue;
                    excel.write(model.getValueAt(i,j).toString()+"\t");
                }
                excel.write("\n");
            }   excel.close();
        } catch (IOException ex) {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                excel.close();
            } catch (IOException ex) {
                Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportActionPerformed

    private void niveautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niveautActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_niveautActionPerformed

    private void filièretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filièretActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filièretActionPerformed

    private void printbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbuttonActionPerformed
        if(id_cardt.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Formulaire vide !");
        }else{

            try
            {
                
                String reportSource = "/application/id_card.jasper";
                InputStream reportFile = null;
                reportFile = getClass().getResourceAsStream(reportSource);
                Connection conn = cnx.obtenirconnexion();
                Map<String, Object> params = new HashMap<>();
                params.put("card_id", id_cardt.getText());
                String sql="Select PHOTO from userinfo WHERE CardNo='"+id_cardt.getText()+"'";
                stat=cnx.obtenirconnexion().createStatement();
                rs = stat.executeQuery(sql);
                rs.next();
                Blob blob = rs.getBlob("PHOTO");
                if(blob == null){
                    JOptionPane.showMessageDialog(null,"Pas de Photo ! ");
                }else{
                    params.put("img", blob.getBinaryStream());
                    try
                    {
                        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile, params, conn);
                        JasperViewer.viewReport(jasperPrint,false);
                    }
                    catch (JRException ex)
                    {
                        JOptionPane.showMessageDialog(null,"Erreur ! "+ex.getMessage());
                    }
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Personne.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
    }//GEN-LAST:event_printbuttonActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        updatelist();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void ajouterchambreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterchambreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajouterchambreActionPerformed

    private void ajouterchambreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterchambreMouseClicked
        Document document=new Document();
        document.setVisible(true);
    }//GEN-LAST:event_ajouterchambreMouseClicked

    private void actualiserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualiserMouseClicked
        actualiser();
    }//GEN-LAST:event_actualiserMouseClicked

    private void id_cardtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_cardtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_cardtActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir modifier ?",
            "Modifier lecteur",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        try{

            // les val oranges sont les val de la BD
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String DateN=sdf.format(dateNst.getDate());
            String startDate = sdf.format(acc_startdatet.getDate());
            
            if((etatt.getSelectedItem().toString().equals("Non actif") || etatt.getSelectedItem().toString().equals("Bloqué")) ){    
                Date dt = new Date();    
                Calendar c = Calendar.getInstance(); 
                c.setTime(dt); 
                c.add(Calendar.DAY_OF_MONTH, -1);
                dt = c.getTime();
                acc_enddatet.setDate(dt);
            }
        
            String endDate = sdf.format(acc_enddatet.getDate());            
            String sql="Select * from userinfo WHERE CardNo='"+id_cardt.getText()+"'";
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery(sql);
            if(rs.first() && !rs.getString("CardNo").equals(id_cardt.getText())){
                    JOptionPane.showMessageDialog(null,"Identifiant existant !");
            }else{
                stat.executeUpdate("UPDATE userinfo SET  etat='"+etatt.getSelectedItem().toString()+"', acc_enddate='"+endDate+"', acc_startdate='"+startDate+"', Education_ar='"+filière_art.getText()+"', CardNo='"+id_cardt.getText()+"', name='"+nomt.getText()+"', lastname='"+prenomt.getText()+"', nom_ar='"+nom_art.getText()+"', prénom_ar='"+prenom_art.getText()+"', BIRTHDAY='"+DateN+"', CITY='"+lieut.getText()+"', EDUCATION='"+filièret.getText()+"', TITLE='"+niveaut.getText()+"', Badgenumber='"+num_bact.getText()+"', année_bac='"+année_bact.getText()+"', street='"+adresset.getText()+"', homeaddress='"+wilayat.getText()+"', STATE='"+nationalitét.getText()+"', cat='"+catt.getSelectedItem().toString()+"',numchambre='"+numchambret.getSelectedValue()+"' where USERID='"+table.getValueAt(table.getSelectedRow(), 0)+"'");                
                
                if(f != null){
                    fis=new FileInputStream(f);
                    PreparedStatement ps= cnx.obtenirconnexion().prepareStatement("UPDATE userinfo SET PHOTO=? Where CardNo=?");

                    ps.setBinaryStream(1,fis,(int)f.length());
                    ps.setString(2, id_cardt.getText());
                    ps.executeUpdate();
                    f = null;
                    fis.close();
                }
            }            
        }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"Erreur de modification "+e.getMessage());
                System.err.println(e);
        }   catch (FileNotFoundException ex) {
                Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        actualiser();
    }//GEN-LAST:event_modifierActionPerformed

    private void recherchetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherchetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recherchetActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        try{
            model.setRowCount(0);
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery("select * from userinfo WHERE etat = '"+recherchet.getText()+"' OR CardNo = '"+recherchet.getText()+"' OR name = '"+recherchet.getText()+"' OR lastname = '"+recherchet.getText()+"' OR CITY = '"+recherchet.getText()+"' OR EDUCATION = '"+recherchet.getText()+"' OR TITLE = '"+recherchet.getText()+"' OR Badgenumber = '"+recherchet.getText()+"' OR année_bac = '"+recherchet.getText()+"' OR street = '"+recherchet.getText()+"' OR homeaddress = '"+recherchet.getText()+"' OR STATE = '"+recherchet.getText()+"' OR numchambre = '"+recherchet.getText()+"'");     
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("USERID"),rs.getString("CardNo"),rs.getString("name"),rs.getString("lastname"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("BIRTHDAY"),rs.getString("CITY"),rs.getString("EDUCATION"),rs.getString("TITLE"),rs.getString("Badgenumber"),rs.getString("année_bac"),rs.getString("street"),rs.getString("homeaddress"),rs.getString("STATE"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("education_ar"),rs.getDate("acc_startdate"),rs.getDate("acc_enddate"),rs.getString("create_time"),rs.getString("etat")});
            }
        }catch(SQLException e){
                System.err.println(e);
                table.setModel(model);
            }
        
    }//GEN-LAST:event_rechercheActionPerformed

    private void MinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinMouseClicked

        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir supprimer un resident ?",
            "Supprimer resident",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        try{

            if(nomt.getText().length() !=0){

                stat.executeUpdate("Delete from userinfo where CardNo ="+id_cardt.getText());

            }
            else
            JOptionPane.showMessageDialog(null, "Séléctionnez le lecteur à supprimer SVP !");
        }

        catch(HeadlessException | SQLException e){
            JOptionPane.showInternalMessageDialog(null,"Erreur de suppréssion "+e.getMessage());
        }
        }
        actualiser();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try{
            int i =table.getSelectedRow();
            deplace(i);
        }catch(Exception e){
            System.err.println(e);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void cattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cattActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cattActionPerformed

    private void adressetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adressetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adressetActionPerformed

    private void CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_CloseMouseClicked

    private void prenomtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomtActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

        JFileChooser choose = new JFileChooser();
        choose.showOpenDialog(null);
        f = choose.getSelectedFile();
        ImageIcon imageIcon = new ImageIcon(f.getPath());
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(64,64,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image); 
        jLabel9.setIcon(imageIcon);        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String CardNo=id_cardt.getText();
        String Nom=nomt.getText();
        String Prenom=prenomt.getText();
        String Nom_ar=nom_art.getText();
        String Prenom_ar=prenom_art.getText();
        String Lieu=lieut.getText();
        String Filiere=filièret.getText();
        String Niveau=niveaut.getText();
        String Num_bac=num_bact.getText();
        String Année_bac=année_bact.getText();
        String Adresse=adresset.getText();
        String Wilaya=wilayat.getText();
        String Nationalité=nationalitét.getText();
        Object Catégorie =catt.getSelectedItem();
        Object NumChambre =numchambret.getSelectedValue();
        Object Filière_ar = filière_art.getText();
        Object Etat =etatt.getSelectedItem();
            try{

                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                if(dateNst.toString().isEmpty())
                    dateNst.setDate(s.parse("2000-01-01"));
                String DateN = s.format(dateNst.getDate());
                String StartDate = s.format(acc_startdatet.getDate());
                String EndDate = s.format(acc_enddatet.getDate());
                Integer NowYear = Calendar.getInstance().get(Calendar.YEAR);
                
                String requete="insert into userinfo(CardNo, name, lastname, nom_ar, prénom_ar, BIRTHDAY, CITY, EDUCATION,TITLE,Badgenumber, année_bac, street, homeaddress, STATE, cat, numchambre, Education_ar, acc_startdate, acc_enddate,create_time, etat)VALUES('"+CardNo+"','"+Nom+"','"+Prenom+"','"+Nom_ar+"','"+Prenom_ar+"','"+DateN+"','"+Lieu+"','"+Filiere+"','"+Niveau+"','"+Num_bac+"','"+Année_bac+"','"+Adresse+"','"+Wilaya+"','"+Nationalité+"','"+Catégorie+"','"+NumChambre+"','"+Filière_ar+"','"+StartDate+"','"+EndDate+"','"+NowYear+"','"+Etat+"')";
                //verification d'identifiant.
                String sql="Select * from userinfo WHERE CardNo='"+id_cardt.getText()+"'";
                stat=cnx.obtenirconnexion().createStatement();
                rs= stat.executeQuery(sql);

                if(rs.first()){
                        JOptionPane.showMessageDialog(null,"Identifiant existant !");
                }else{
                
                    if (Nom.length()>=50||Prenom.length()>=50||Nationalité.length()>=50||Wilaya.length()>=50||Année_bac.length()>=50||Num_bac.length()>=50||Nom_ar.length()>=50||Prenom_ar.length()>=50||Lieu.length()>=50||Filiere.length()>=50||Niveau.length()>=50||Adresse.length()>=50)
                    {
                        JOptionPane.showMessageDialog(null,"Taille d'un champ trop grande !");
                    }else{
                        if (Nom.equals("")||Prenom.equals("")||DateN.equals(""))
                        {
                            JOptionPane.showMessageDialog(null,"Veuillez insérer vos données !");
                            if(Nom.equals(""))
                                nomt.setBackground(Color.red);
                            if(Prenom.equals(""))
                                prenomt.setBackground(Color.red);
                        }else{
                            if(DateN.equals("")){
                            dateNst.setBackground(Color.red);
                            }
                            else{
                                try{
                                    Sauvegarde sauvegarde =new Sauvegarde();
                                    sauvegarde.setVisible(true);
                                    stat.executeUpdate(requete);
                                    if(f != null){
                                        fis=new FileInputStream(f);

                                        PreparedStatement ps= cnx.obtenirconnexion().prepareStatement("UPDATE userinfo SET PHOTO=? Where CardNo=?");

                                        ps.setBinaryStream(1,fis,(int)f.length());
                                        ps.setString(2, CardNo);
                                        ps.executeUpdate();
                                        f = null;
                                        fis.close();
                                    }
                                    
                                    
                                }catch(SQLException ex){
                                    System.err.println(ex);
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }                       
                    }                            
                }                
                }
                catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null,"Veuillez insérer vos données !"+e.getMessage());
                    nomt.setBackground(Color.red);
                    prenomt.setBackground(Color.red);
                    dateNst.setBackground(Color.red);
                } catch (ParseException | SQLException ex) {
                    Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
                }
            // Pour vider les champs
            actualiser();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lieutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lieutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lieutActionPerformed

    private void nomtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomtActionPerformed

    private void Imprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Imprimer1ActionPerformed
        if(id_cardt.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Formulaire vide !");
        }else{

            String reportSource = "/application/attestation2.jasper";
            InputStream reportFile = null;
            reportFile = getClass().getResourceAsStream(reportSource);
            Connection conn = cnx.obtenirconnexion();
            Map<String, Object> params = new HashMap<>();
            params.put("card_id", id_cardt.getText());
            params.put("year", Calendar.getInstance().get(Calendar.YEAR));
            params.put("date", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            try
            {
                JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile, params, conn);
                JasperViewer.viewReport(jasperPrint,false);
            }
            catch (JRException ex)
            {
                JOptionPane.showMessageDialog(null,"Erreur ! "+ex.getMessage());
            }
        }
    }//GEN-LAST:event_Imprimer1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
            try{

                model.setRowCount(0);
                stat=cnx.obtenirconnexion().createStatement();
                if(jCheckBox1.isSelected()){
                    if(recherchet.getText().isEmpty()){
                        rs= stat.executeQuery("select * from userinfo WHERE create_time >= '"+Calendar.getInstance().get(Calendar.YEAR)+"'");     
                    }else{
                        rs= stat.executeQuery("select * from userinfo WHERE create_time >= '"+Calendar.getInstance().get(Calendar.YEAR)+"' AND (etat = '"+recherchet.getText()+"' OR CardNo = '"+recherchet.getText()+"' OR name = '"+recherchet.getText()+"' OR lastname = '"+recherchet.getText()+"' OR CITY = '"+recherchet.getText()+"' OR EDUCATION = '"+recherchet.getText()+"' OR TITLE = '"+recherchet.getText()+"' OR Badgenumber = '"+recherchet.getText()+"' OR année_bac = '"+recherchet.getText()+"' OR street = '"+recherchet.getText()+"' OR homeaddress = '"+recherchet.getText()+"' OR STATE = '"+recherchet.getText()+"' OR numchambre = '"+recherchet.getText()+"')");     
                    }
                }else{
                    if(recherchet.getText().isEmpty()){
                        rs= stat.executeQuery("select * from userinfo");     
                    }else{
                        rs= stat.executeQuery("select * from userinfo WHERE etat = '"+recherchet.getText()+"' OR CardNo = '"+recherchet.getText()+"' OR name = '"+recherchet.getText()+"' OR lastname = '"+recherchet.getText()+"' OR CITY = '"+recherchet.getText()+"' OR EDUCATION = '"+recherchet.getText()+"' OR TITLE = '"+recherchet.getText()+"' OR Badgenumber = '"+recherchet.getText()+"' OR année_bac = '"+recherchet.getText()+"' OR street = '"+recherchet.getText()+"' OR homeaddress = '"+recherchet.getText()+"' OR STATE = '"+recherchet.getText()+"' OR numchambre = '"+recherchet.getText()+"'");     
                    }
                }
                while(rs.next()){
                    model.addRow(new Object[]{rs.getString("USERID"),rs.getString("CardNo"),rs.getString("name"),rs.getString("lastname"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("BIRTHDAY"),rs.getString("CITY"),rs.getString("EDUCATION"),rs.getString("TITLE"),rs.getString("Badgenumber"),rs.getString("année_bac"),rs.getString("street"),rs.getString("homeaddress"),rs.getString("STATE"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("education_ar"),rs.getDate("acc_startdate"),rs.getDate("acc_enddate"),rs.getString("create_time"),rs.getString("etat")});
                }
            }catch(SQLException e){
                    System.err.println(e);
                    table.setModel(model);
            }        
    }//GEN-LAST:event_jCheckBox1ActionPerformed
        
    private void seticonimage(ResultSet resultset) throws SQLException, IOException{
        
        
        
        resultset.next();
        ImageIcon icon;
       
        Blob blob = resultset.getBlob("PHOTO");
        if(blob == null){
            jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgpath)));
        }else{
            BufferedImage image=ImageIO.read(blob.getBinaryStream());
            icon = new ImageIcon(image.getScaledInstance(64,64,Image.SCALE_DEFAULT));
            jLabel9.setIcon(icon);
        }
        
        
    }


     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Personne().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JButton Imprimer;
    private javax.swing.JButton Imprimer1;
    private javax.swing.JLabel Min;
    private com.toedter.calendar.JDateChooser acc_enddatet;
    private com.toedter.calendar.JDateChooser acc_startdatet;
    private javax.swing.JLabel actualiser;
    private javax.swing.JLabel adresse;
    private javax.swing.JLabel adresse1;
    private javax.swing.JLabel adresse2;
    private javax.swing.JLabel adresse3;
    private javax.swing.JLabel adresse4;
    private javax.swing.JTextField adresset;
    private javax.swing.JButton ajouterchambre;
    private javax.swing.JTextField année_bact;
    private javax.swing.JLabel categorie;
    private javax.swing.JComboBox<String> catt;
    private javax.swing.JLabel dateN;
    private com.toedter.calendar.JDateChooser dateNst;
    private javax.swing.JLabel departement;
    private javax.swing.JComboBox<String> etatt;
    private javax.swing.JButton export;
    private javax.swing.JTextField filière_art;
    private javax.swing.JTextField filièret;
    private javax.swing.JLabel id;
    private javax.swing.JTextField id_cardt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lieut;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField nationalitét;
    private javax.swing.JTextField niveaut;
    private javax.swing.JLabel nom;
    private javax.swing.JTextField nom_art;
    private javax.swing.JTextField nomt;
    private javax.swing.JTextField num_bact;
    private javax.swing.JList<String> numchambret;
    private javax.swing.JLabel prenom;
    private javax.swing.JTextField prenom_art;
    private javax.swing.JTextField prenomt;
    private javax.swing.JButton printbutton;
    private javax.swing.JButton recherche;
    private javax.swing.JTextField recherchet;
    private javax.swing.JTable table;
    private javax.swing.JLabel telephone;
    private javax.swing.JLabel telephone1;
    private javax.swing.JLabel telephone2;
    private javax.swing.JLabel telephone3;
    private javax.swing.JLabel telephone4;
    private javax.swing.JTextField wilayat;
    // End of variables declaration//GEN-END:variables
}
