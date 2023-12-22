/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prjtrh;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrateur
 */
public class Employe extends javax.swing.JFrame {

    Connection c ;
    DefaultTableModel m ;
    public Employe() {
        initComponents();
    }
    public Employe(int id) {
        initComponents();
        //name.setText(id+"");
        try {
            c =Connect.connecter();
            
            String sql = "SELECT * From employe WHERE id_emp=?";
            PreparedStatement requete = c.prepareStatement(sql);
            requete.setInt(1, id);
            ResultSet result = requete.executeQuery();
            result.next();
            
            numero.setText(result.getInt(1)+"");
            nomprenom.setText(result.getString(2));
            email.setText(result.getString(3));
            telephone.setText(result.getString(4));
            dep.setText(result.getString(5));
            poste.setText(result.getString(6));
            salaire.setText(result.getDouble(7)+" dhs");
            /*
             Blob imageBlob = result.getBlob(8);
             byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
             InputStream in = new ByteArrayInputStream(imageBytes);
             BufferedImage image = ImageIO.read(in);
             //Image img = image.getScaledInstance(photoEmp.getWidth(), photoEmp.getHeight(), Image.SCALE_SMOOTH); 
             //photoEmp.setIcon(new ImageIcon(img));
            */
             
            //InputStream flux = result.getBinaryStream("photo");
             //Image img = ImageIO.read(flux);
             //Image img2 = img.getScaledInstance(photoEmp.getWidth(), photoEmp.getHeight(), Image.SCALE_SMOOTH);
             //photoEmp.setIcon(new ImageIcon(img));
             
             //gestion de Congé 
             String depar = result.getString(5);
             String posteEmploye = result.getString(6);
             if(posteEmploye.equalsIgnoreCase("chef_dep")){
                // javax.swing.JLabel jLabel1 = new JLabel(posteEmploye+" du departement " +depar);
                    // jLabel1.setText(posteEmploye+" du departement " +depar);
                    //jPanel3.add(jLabel1);
                    titre1.setVisible(false);titre2.setVisible(false);
                    separateur.setVisible(false);date.setVisible(false);
                    datecon.setVisible(false);nbrjours.setVisible(false);
                    nombre.setVisible(false);demandeBtn.setVisible(false);
                   // mesdemandes.setVisible(false);
                   m=new DefaultTableModel(null, new String[]{"num employe","nom","Num conge","Dep","date conge","etat"});
                   
                   String s = "En cours d'Execution" ;
                   
                   //String mesEmploye = "SELECT * FROM employe WHERE departement='"+depar+"'";
                   
                   String decla = "SELECT c.id_employe,e.nomprenom,c.id_conge,e.departement,c.date_conge,c.etat FROM employe e,conge c WHERE e.id_emp=c.id_employe AND e.departement=? AND c.etat=?";
                  // String decla = "SELECT c.id_employe,e.nomprenom,c.id_conge,c.date_conge,c.etat "
                  // + "FROM employe e,conge c WHERE e.departement='"+depar+"' AND c.etat LIKE '"+s+"%'";
                   PreparedStatement r = c.prepareStatement(decla);
                   r.setString(1, depar);r.setString(2, s);
                   ResultSet resultat = r.executeQuery();
                   while(resultat.next()){
                       m.addRow(new Object[]{resultat.getInt(1),resultat.getString(2),resultat.getInt(3),resultat.getString(4),resultat.getDate(5),resultat.getString(6)});
                       
                   } 
                   
                   mesdemandes.setModel(m);
                 //System.out.println();
             }else{
                 //jLabel1.setText(posteEmploye+" du departement " +depar);
                 titre3.setVisible(false); acceptBtn.setVisible(false);refuseBtn.setVisible(false);
                 
                  demandeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                Date d =new java.sql.Date(datecon.getDate().getTime());
                int nbr = Integer.parseInt(nbrjours.getText() );
                String s = "En cours d'Execution" ;
                String sql = "INSERT INTO conge (id_employe,date_conge,nombrejour,etat) VALUES(?,?,?,?)";
                try {
                    PreparedStatement requete = c.prepareStatement(sql);
                    requete.setInt(1, id);
                    requete.setDate(2, d);
                    requete.setInt(3, nbr);
                    requete.setString(4, s);
                    requete.executeUpdate(); 
                    
                    //System.out.println("Bouton cliqué !");
                    JOptionPane.showMessageDialog(null," Votre Demande en cours d'exécution !!");
                    datecon.setDate(null); nbrjours.setText("");
                    
                    
                    /*
                    m = new DefaultTableModel(null, new String[]{"Numéro Congé","Date Congé","Nombre Congé","Etat"});
                    PreparedStatement rqt = c.prepareStatement("SELECT * FROM conge WHERE id_employe=?");
                    rqt.setInt(1, id);
                    ResultSet rs = rqt.executeQuery();
                    while(rs.next()){
                        m.addRow(new Object[]{rs.getInt("id_conge"),rs.getDate("date_conge"),rs.getInt("nombrejour"),rs.getString("etat")});
                    }
                    
                    mesdemandes.setModel(m);   */
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
                m = new DefaultTableModel(null, new String[]{"Numéro Congé","Date Congé","Nombre Congé","Etat"});
                    PreparedStatement rqt = c.prepareStatement("SELECT * FROM conge WHERE id_employe=?");
                    rqt.setInt(1, id);
                    ResultSet rs = rqt.executeQuery();
                    while(rs.next()){
                        m.addRow(new Object[]{rs.getInt("id_conge"),rs.getDate("date_conge"),rs.getInt("nombrejour"),rs.getString("etat")});
                    }
                    
                    mesdemandes.setModel(m);
                //mesdemandes.setModel(m);   
                  /* 
                  demandeBtn.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         try {
                             Date d =new java.sql.Date(datecon.getDate().getTime());
                             int nbr = Integer.parseInt(nbrjours.getText() );
                             String s = "En cours d'Execution" ;
                             //Date d = datecon.getDate();
                             String sql = "INSERT INTO conge VALUES(?,?,?,?)";
                             PreparedStatement requete = c.prepareStatement(sql);
                             requete.setInt(1, id);
                             requete.setDate(2, d);
                             requete.setInt(3, nbr);
                             requete.setString(4, s);
                             requete.executeUpdate();  
                             
                             System.out.println("le bouton a cliqué");
                         } catch (Exception ex) {
                             ex.printStackTrace();
                         }
                         
                     }
                 });*/
                 
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nomprenom = new javax.swing.JLabel();
        sedeconnecter = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        telephone = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        salaire = new javax.swing.JTextField();
        photoEmp = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dep = new javax.swing.JTextField();
        poste = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        demandeBtn = new javax.swing.JButton();
        separateur = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        mesdemandes = new javax.swing.JTable();
        datecon = new com.toedter.calendar.JDateChooser();
        nbrjours = new javax.swing.JTextField();
        titre2 = new javax.swing.JLabel();
        titre1 = new javax.swing.JLabel();
        titre3 = new javax.swing.JLabel();
        acceptBtn = new javax.swing.JButton();
        refuseBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));

        nomprenom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nomprenom.setForeground(new java.awt.Color(255, 255, 255));
        nomprenom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        sedeconnecter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sedeconnecter.setForeground(new java.awt.Color(204, 102, 255));
        sedeconnecter.setText("Se déconnecter");
        sedeconnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sedeconnecterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomprenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(sedeconnecter)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(nomprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(sedeconnecter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Numéro:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Téléphone: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Salaire:");

        numero.setEditable(false);

        telephone.setEditable(false);

        email.setEditable(false);

        salaire.setEditable(false);

        photoEmp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Département:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Poste: ");

        dep.setEditable(false);

        poste.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telephone)
                    .addComponent(dep)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(email)
                    .addComponent(poste)
                    .addComponent(salaire, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(photoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(photoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(42, 42, 42)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(salaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(poste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Mon Compte", jPanel2);

        date.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        date.setText("Date de Congé: ");

        nombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nombre.setText("Nombre de jours: ");

        demandeBtn.setBackground(new java.awt.Color(204, 102, 255));
        demandeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        demandeBtn.setForeground(new java.awt.Color(255, 255, 255));
        demandeBtn.setText("Demander Congé");
        demandeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demandeBtnActionPerformed(evt);
            }
        });

        mesdemandes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(mesdemandes);

        titre2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre2.setText("Mes Demandes");

        titre1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre1.setText("Demander Un Congé");

        titre3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre3.setText("Gérer les Demandes");

        acceptBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        acceptBtn.setForeground(new java.awt.Color(0, 204, 0));
        acceptBtn.setText("Accepter");
        acceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBtnActionPerformed(evt);
            }
        });

        refuseBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refuseBtn.setForeground(new java.awt.Color(255, 0, 0));
        refuseBtn.setText("Refuser");
        refuseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refuseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separateur, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(demandeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datecon, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrjours, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 39, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addComponent(titre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titre3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refuseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(titre1)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(date)
                        .addComponent(datecon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nombre)
                        .addComponent(nbrjours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(demandeBtn)
                .addGap(2, 2, 2)
                .addComponent(titre3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separateur, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titre2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptBtn)
                    .addComponent(refuseBtn))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Congé", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
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

    private void sedeconnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sedeconnecterActionPerformed
        setVisible(false);
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_sedeconnecterActionPerformed

    private void demandeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demandeBtnActionPerformed

    }//GEN-LAST:event_demandeBtnActionPerformed

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBtnActionPerformed
        int indice = mesdemandes.getSelectedRow();
        int numeroSelectionne = (Integer)mesdemandes.getValueAt(indice, 2);
        c= Connect.connecter();
        try {
            String sql = "UPDATE conge SET etat ='Accepte' WHERE id_conge="+numeroSelectionne;
            Statement requete = c.createStatement();
            requete.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_acceptBtnActionPerformed

    private void refuseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refuseBtnActionPerformed
        int indice = mesdemandes.getSelectedRow();
        int numeroSelectionne = (Integer)mesdemandes.getValueAt(indice, 2);
        c= Connect.connecter();
        try {
            String sql = "UPDATE conge SET etat ='Refuse' WHERE id_conge="+numeroSelectionne;
            Statement requete = c.createStatement();
            requete.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_refuseBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptBtn;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser datecon;
    private javax.swing.JButton demandeBtn;
    private javax.swing.JTextField dep;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable mesdemandes;
    private javax.swing.JTextField nbrjours;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel nomprenom;
    private javax.swing.JTextField numero;
    private javax.swing.JLabel photoEmp;
    private javax.swing.JTextField poste;
    private javax.swing.JButton refuseBtn;
    private javax.swing.JTextField salaire;
    private javax.swing.JButton sedeconnecter;
    private javax.swing.JSeparator separateur;
    private javax.swing.JTextField telephone;
    private javax.swing.JLabel titre1;
    private javax.swing.JLabel titre2;
    private javax.swing.JLabel titre3;
    // End of variables declaration//GEN-END:variables
}
