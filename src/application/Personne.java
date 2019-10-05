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
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
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
    
    connect maconnexion=new connect();
    
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
        model.addColumn("Id");
        model.addColumn("card_id");
        model.addColumn("nom");
        model.addColumn("prénom");
        model.addColumn("nom_ar");
        model.addColumn("prénom_ar");
        model.addColumn("dateNs");
        model.addColumn("lieu");
        model.addColumn("filière");
        model.addColumn("niveau");
        model.addColumn("num_bac");
        model.addColumn("année_bac");
        model.addColumn("adresse");
        model.addColumn("wilaya");
        model.addColumn("nationalité");
        model.addColumn("catégorie");
        model.addColumn("numchambre");
        model.addColumn("imgpath");
        
        try{
            String sql="Select * from Personne1";
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("Id"),rs.getString("card_id"),rs.getString("nom"),rs.getString("prénom"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("dateNs"),rs.getString("lieu"),rs.getString("filière"),rs.getString("niveau"),rs.getString("num_bac"),rs.getString("année_bac"),rs.getString("adresse"),rs.getString("wilaya"),rs.getString("nationalité"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("imgpath")});
            }
        }catch(SQLException ex){
            System.err.println(ex);
        }
        table.setModel(model);
        updatelist();
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
            imgpath = model.getValueAt(i,17).toString();
            seticonimage(imgpath);
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
                listModel.addElement("Num: "+rs.getString("numchambre")+" | Pavillon:"+rs.getString("numbloc"));
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
        catt.setSelectedItem(null);
        numchambret.setSelectedIndex(0);
        imgpath = "/app/icones/icons8-ajouter-un-groupe-d'utilisateurs-homme-homme-64.png";
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgpath)));

        try{
            model.setRowCount(0);
            stat=cnx.obtenirconnexion().createStatement();
            rs= stat.executeQuery("select * from personne1");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("Id"),rs.getString("card_id"),rs.getString("nom"),rs.getString("prénom"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("dateNs"),rs.getString("lieu"),rs.getString("filière"),rs.getString("niveau"),rs.getString("num_bac"),rs.getString("année_bac"),rs.getString("adresse"),rs.getString("wilaya"),rs.getString("nationalité"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("imgpath")});
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
        printbutton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        printbutton.setForeground(new java.awt.Color(255, 255, 255));
        printbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/icones/icons8-document-64 (1).png"))); // NOI18N
        printbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbuttonActionPerformed(evt);
            }
        });

        filièret.setNextFocusableComponent(niveaut);
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
                            .addComponent(nom)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(adresse3)
                                    .addComponent(adresse1))
                                .addGap(4, 4, 4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(filièret, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(dateNst, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                            .addComponent(prenomt, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(prenom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lieut, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(niveaut))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categorie, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(departement, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(telephone)
                                            .addComponent(telephone1)
                                            .addComponent(adresse)
                                            .addComponent(adresse4))
                                        .addGap(6, 6, 6))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(id_cardt, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nomt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recherchet, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recherche)
                        .addGap(61, 61, 61)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(adresset)
                                    .addComponent(jScrollPane2)
                                    .addComponent(wilayat)
                                    .addComponent(nationalitét)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(catt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(num_bact, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(adresse2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(année_bact, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(ajouterchambre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Imprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Min, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Close)))
                        .addGap(14, 14, 14))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(export)
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
                                .addComponent(adresse)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(prenom)
                                .addComponent(prenomt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prenom_art, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(wilayat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telephone)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(dateN))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(ajouterchambre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateNst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lieut, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nationalitét, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(telephone1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(filièret, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adresse1))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(catt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(categorie)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(adresse3)
                                            .addComponent(niveaut, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(Imprimer)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(recherchet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(departement)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Min, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(57, 57, 57)
                        .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(export)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                if(model.getColumnName(i).equalsIgnoreCase("imgpath")||model.getColumnName(i).equalsIgnoreCase("cat"))
                    continue;
                excel.write(model.getColumnName(i) + "\t");
            }   excel.write("\n");
            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    if(model.getColumnName(j).equalsIgnoreCase("imgpath")||model.getColumnName(i).equalsIgnoreCase("cat"))
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

            String reportSource = "/application/id_card.jasper";
            InputStream reportFile = null;
            reportFile = getClass().getResourceAsStream(reportSource);
            Connection conn = cnx.obtenirconnexion();
            Map<String, Object> params = new HashMap<>();
            params.put("card_id", id_cardt.getText());
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
            
            stat.executeUpdate("UPDATE personne1 SET  nom='"+nomt.getText()+"', prénom='"+prenomt.getText()+"', nom_ar='"+nom_art.getText()+"', prénom_ar='"+prenom_art.getText()+"', dateNs='"+DateN+"', lieu='"+lieut.getText()+"', filière='"+filièret.getText()+"', niveau='"+niveaut.getText()+"', num_bac='"+num_bact.getText()+"', année_bac='"+année_bact.getText()+"', adresse='"+adresset.getText()+"', wilaya='"+wilayat.getText()+"', nationalité='"+nationalitét.getText()+"', cat='"+catt.getSelectedItem().toString()+"',numchambre='"+numchambret.getSelectedValue()+"',imgpath='"+imgpath.replace("\\", "\\\\")+"'where card_id='"+id_cardt.getText()+"'");
        }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"Erreur de modification "+e.getMessage());
                System.err.println(e);
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
            rs= stat.executeQuery("select * from personne1 WHERE nom = '"+recherchet.getText()+"' OR prénom = '"+recherchet.getText()+"' OR filière = '"+recherchet.getText()+"' OR adresse = '"+recherchet.getText()+"' OR nationalité = '"+recherchet.getText()+"' OR wilaya = '"+recherchet.getText()+"' OR année_bac = '"+recherchet.getText()+"' OR num_bac = '"+recherchet.getText()+"' OR niveau = '"+recherchet.getText()+"'");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("Id"),rs.getString("card_id"),rs.getString("nom"),rs.getString("prénom"),rs.getString("nom_ar"),rs.getString("prénom_ar"),rs.getDate("dateNs"),rs.getString("lieu"),rs.getString("filière"),rs.getString("niveau"),rs.getString("num_bac"),rs.getString("année_bac"),rs.getString("adresse"),rs.getString("wilaya"),rs.getString("nationalité"),rs.getString("cat"),rs.getString("numchambre"),rs.getString("imgpath")});
            }}catch(SQLException e){
                System.err.println(e);
                table.setModel(model);
            }
            actualiser();
    }//GEN-LAST:event_rechercheActionPerformed

    private void MinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinMouseClicked

        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir supprimer un resident ?",
            "Supprimer resident",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        try{

            if(nomt.getText().length() !=0){

                stat.executeUpdate("Delete from personne1 where card_id ="+id_cardt.getText());

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
        File f = choose.getSelectedFile();
        imgpath = f.getAbsolutePath();
        seticonimage(imgpath);

    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String Card_id=id_cardt.getText();
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
        Object ImgPath = imgpath.replace("\\", "\\\\");

            try{

                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                if(dateNst.toString().isEmpty())
                    dateNst.setDate(s.parse("2000-01-01"));
                String DateN=s.format(dateNst.getDate());
                String requete="insert into Personne1(card_id, nom, prénom, nom_ar, prénom_ar, dateNs, lieu, filière, niveau, num_bac, année_bac, adresse, wilaya, nationalité, cat, numchambre, imgpath)VALUES('"+Card_id+"','"+Nom+"','"+Prenom+"','"+Nom_ar+"','"+Prenom_ar+"','"+DateN+"','"+Lieu+"','"+Filiere+"','"+Niveau+"','"+Adresse+"','"+Num_bac+"','"+Année_bac+"','"+Wilaya+"','"+Nationalité+"','"+Catégorie+"','"+NumChambre+"','"+ImgPath+"')";

                //verification d'identifiant.
                String sql="Select * from Personne1 WHERE card_id='"+id_cardt.getText()+"'";
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
                                }catch(SQLException ex){
                                    System.err.println(ex);
                                }
                            // Pour vider les champs
                            actualiser();
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
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lieutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lieutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lieutActionPerformed

    private void nomtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomtActionPerformed
    
    
    private void seticonimage(String path){
        
        ImageIcon icon = new ImageIcon(path);
        Image srcImg = icon.getImage();
        icon = new ImageIcon(getScaledImage(srcImg, 64, 64));
        jLabel9.setIcon(icon);
        
    }

    private Image getScaledImage(Image srcImg, int w, int h){

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;

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
            java.util.logging.Logger.getLogger(Personne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Personne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Personne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Personne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
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
    private javax.swing.JLabel Min;
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
    private javax.swing.JButton export;
    private javax.swing.JTextField filièret;
    private javax.swing.JLabel id;
    private javax.swing.JTextField id_cardt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField wilayat;
    // End of variables declaration//GEN-END:variables
}
