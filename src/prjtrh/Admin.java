package prjtrh;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Admin extends javax.swing.JFrame {

    Connection c ;File f;
    DefaultTableModel m ;
    public Admin() {
        initComponents();
        try {
            
            afficherDepartement();
            //afficherPoste();
            
            afficherEmploye();
            
            mesDonnes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //fonction qui affiche les departements 
    public void afficherDepartement() throws Exception{
        c = Connect.connecter();
        Statement requete = c.createStatement();
        String sql = "SELECT nom_dep FROM departement";
        ResultSet result = requete.executeQuery(sql);
        while (result.next()){
            departementList.addItem(result.getString(1));
        }
    }
    
    public void afficherPoste() throws Exception{

    }
    
    //fonction qui afficher tous les employés
    public void afficherEmploye()throws Exception{
        c=Connect.connecter();
        m=new DefaultTableModel(null, new String[]
        {"Numéro","Nom Prénom","email","Téléphone","Departement","Poste","Salaire"});
        
        
        
        Statement requete = c.createStatement();
        String sql ="SELECT * FROM employe";
        ResultSet result = requete.executeQuery(sql);
        while(result.next()){
            /*
            Blob imageBlob = result.getBlob(8);
            // Étape 2: Convertissez l'objet BLOB en un tableau d'octets
            byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            // Étape 3: Créez un flux d'entrée à partir du tableau d'octets
            InputStream in = new ByteArrayInputStream(imageBytes);
            // Étape 4: Créez une image à partir du flux d'entrée
             BufferedImage image = ImageIO.read(in);
            // byte[] imageData = result.getBytes(8);
           // BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
            //jLabel14.setIcon(new ImageIcon(image));   */
                   
            m.addRow(new Object[]{result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),
            result.getDouble(7)});
        }
        
        tableEmploye.setModel(m);
        
        TableColumnModel columnModel = tableEmploye.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(6).setPreferredWidth(40);
    }
    
    public void mesDonnes()throws Exception{
        try {
            emailAdmin.setEditable(false);telephoneAdmin.setEditable(false);
            PreparedStatement requete = c.prepareStatement("SELECT * FROM admin");
            ResultSet r =requete.executeQuery();
            r.next();
            nomAdmin.setText(r.getString("nomprenom"));
            telephoneAdmin.setText(r.getString("telephone"));
            emailAdmin.setText(r.getString("email"));
            InputStream flux = r.getBinaryStream("image");
            Image img = ImageIO.read(flux);
            Image img2 = img.getScaledInstance(photoAdmin.getWidth(), photoAdmin.getHeight(), Image.SCALE_SMOOTH);
            photoAdmin.setIcon(new ImageIcon(img2));
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        imagecharge = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ajoutBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nompemp = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        telephone = new javax.swing.JTextField();
        salaire = new javax.swing.JTextField();
        nomuser = new javax.swing.JTextField();
        motpasse = new javax.swing.JTextField();
        departementList = new javax.swing.JComboBox<>();
        posteList = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmploye = new javax.swing.JTable();
        deleteBtn = new javax.swing.JButton();
        num = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        photoAdmin = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nomAdmin = new javax.swing.JLabel();
        emailAdmin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        telephoneAdmin = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administrateur");

        jButton1.setText("Se Déconnecter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButton1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        imagecharge.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nom et Prénom: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("email: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("téléphone: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("département:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("poste:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("salaire:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("nom d'utilisateur:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("mot de passe: ");

        ajoutBtn.setBackground(new java.awt.Color(204, 102, 255));
        ajoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ajoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        ajoutBtn.setText("Ajouter Employe");
        ajoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutBtnActionPerformed(evt);
            }
        });

        jButton2.setText("Charger l'Image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        nompemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nompempActionPerformed(evt);
            }
        });

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        departementList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                departementListItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nompemp)
                    .addComponent(email)
                    .addComponent(telephone)
                    .addComponent(salaire)
                    .addComponent(nomuser)
                    .addComponent(motpasse)
                    .addComponent(departementList, 0, 136, Short.MAX_VALUE)
                    .addComponent(posteList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagecharge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ajoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(nompemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel4)
                                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5))
                                            .addComponent(telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(departementList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(posteList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8))
                                    .addComponent(salaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(nomuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addComponent(motpasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(imagecharge, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ajoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ajouter Employe", jPanel2);

        tableEmploye.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableEmploye.setRowHeight(50);
        tableEmploye.setRowMargin(1);
        jScrollPane1.setViewportView(tableEmploye);

        deleteBtn.setBackground(new java.awt.Color(204, 102, 255));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Supprimer");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numActionPerformed(evt);
            }
        });

        jLabel14.setText("Selectionner/Entrer le numéro de l'employé: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteBtn)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(182, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(deleteBtn)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Mes Employés", jPanel3);

        photoAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel11.setText("E-mail");

        emailAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        emailAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAdminActionPerformed(evt);
            }
        });

        jLabel13.setText("Téléphone");

        telephoneAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton3.setText("Déconnecter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(photoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(nomAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(emailAdmin)
                            .addComponent(telephoneAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addGap(69, 69, 69)))
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(87, 87, 87))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(photoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(nomAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telephoneAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Mon Compte", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed

    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        /*setVisible(false);
        Login log = new Login();
        log.setVisible(true); */
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void emailAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAdminActionPerformed

    private void numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        try {
            /******** Supression par selection *************/
            int[] indices = tableEmploye.getSelectedRows();
            for (int i = indices.length-1;i>=0;i--){
                int numeroselectionne = (Integer)tableEmploye.getValueAt(indices[i], 0);
                PreparedStatement requete = c.prepareStatement("DELETE FROM employe WHERE id_emp =?");
                requete.setInt(1, numeroselectionne);
                requete.executeUpdate();

                PreparedStatement requete2 = c.prepareStatement("DELETE FROM login WHERE id=?");
                requete2.setInt(1, numeroselectionne);
                requete2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Employe suprimé avec succès");
                afficherEmploye();
            }
            /********* Supression par saisie ***************/
            int numero = Integer.parseInt(num.getText());
            PreparedStatement requete = c.prepareStatement("DELETE FROM employe WHERE id_emp =?");
            requete.setInt(1, numero);
            requete.executeUpdate();

            PreparedStatement requete2 = c.prepareStatement("DELETE FROM login WHERE id=?");
            requete2.setInt(1, numero);
            requete2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employe suprimé avec succès");
            afficherEmploye();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void departementListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_departementListItemStateChanged
        posteList.removeAllItems();
        try {
            c = Connect.connecter();
            String nomdep=departementList.getSelectedItem().toString();
            /*String sql = "SELECT p.nom_poste"
            + "FROM departement d , poste p"
            + "WHERE d.id_dep=p.id_departement"
            + "AND d.nom_dep ='"+nomdep+"'";  */
            String sql ="SELECT p.nom_poste FROM departement d, poste p WHERE d.id_dep=p.id_departement AND d.nom_dep ='"+nomdep+"'";
            Statement requete = c.createStatement();
            ResultSet result = requete.executeQuery(sql);

            while (result.next()){
                //System.out.println(result.getString(1));
                posteList.addItem(result.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_departementListItemStateChanged

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void nompempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nompempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nompempActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            JFileChooser jf = new JFileChooser();
            jf.addChoosableFileFilter(new FileNameExtensionFilter("Images files","png", "jpg"));
            jf.showOpenDialog(this);
            f= jf.getSelectedFile();
            BufferedImage img = ImageIO.read(f);
            Image img2 = img.getScaledInstance(imagecharge.getWidth(),imagecharge.getHeight(), Image.SCALE_SMOOTH);
            imagecharge.setIcon(new ImageIcon(img2));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "impossible de charger l'image car : "+e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ajoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutBtnActionPerformed
        String nom = nompemp.getText();
        String mail = email.getText();
        String tel = telephone.getText();
        String dep= departementList.getSelectedItem().toString();
        String poste = posteList.getSelectedItem().toString();
        Double salair = Double.parseDouble(salaire.getText());

        String nomut = nomuser.getText();
        String motdepasse = motpasse.getText();

        try {
            c = Connect.connecter();
            String sql ="INSERT INTO login (nomutilisateur,password,type) VALUES(?,?,?)";
            PreparedStatement requete = c.prepareStatement(sql);
            requete.setString(1, nomut);
            requete.setString(2, motdepasse);
            requete.setString(3, "employe");
            requete.executeUpdate();

            String sql2="SELECT id FROM login WHERE nomutilisateur='"+nomut+"'";
            Statement req = c.createStatement();
            ResultSet r = req.executeQuery(sql2);
            r.next();
            int id_employe= r.getInt(1);

            String sql3="INSERT INTO employe VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement requete2 = c.prepareStatement(sql3);
            requete2.setInt(1, id_employe);
            requete2.setString(2, nom);
            requete2.setString(3, mail);
            requete2.setString(4, tel);
            requete2.setString(5, dep);
            requete2.setString(6, poste);
            requete2.setDouble(7, salair);

            //photo
            FileInputStream flux1  = new FileInputStream(f);
            byte[] pixels = new byte[flux1.available()];
            //ajout photo
            requete2.setBytes(8, pixels);

            requete2.executeUpdate();
            JOptionPane.showMessageDialog(this,"Employee ajoutée avec succès");
            nompemp.setText("");email.setText("");telephone.setText(tel);salaire.setText("");
            nomuser.setText("");motpasse.setText("");
            afficherEmploye();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_ajoutBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajoutBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<String> departementList;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emailAdmin;
    private javax.swing.JLabel imagecharge;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField motpasse;
    private javax.swing.JLabel nomAdmin;
    private javax.swing.JTextField nompemp;
    private javax.swing.JTextField nomuser;
    private javax.swing.JTextField num;
    private javax.swing.JLabel photoAdmin;
    private javax.swing.JComboBox<String> posteList;
    private javax.swing.JTextField salaire;
    private javax.swing.JTable tableEmploye;
    private javax.swing.JTextField telephone;
    private javax.swing.JTextField telephoneAdmin;
    // End of variables declaration//GEN-END:variables
}
