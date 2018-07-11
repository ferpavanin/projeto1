/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secundario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tabelas.Cliente;

/**
 *
 * @author ferpa
 */
public class Aplicativo001 extends javax.swing.JFrame {

    private int tela;
    private boolean existe;
    private Connection conn;
    private Cliente cliente;

    /**
     * Creates new form Aplicativo001
     */
    public Aplicativo001() {
        initComponents();
        this.setLocationRelativeTo(null);
        inicializa();
    }

    private void inicializa() {

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "SYSTEM",
                    "123");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "não conectado ao SGBD");
            System.exit(0);
        }

        tela = 0;
        trataTela();
    }

    private void trataTela() {

        if (tela == 0) {
            this.TxtIdCliente.setEnabled(true);
            this.TxtRazao.setEnabled(false);
            this.TxtCnpjCpf.setEnabled(false);
            this.BtnConf1.setEnabled(false);
            this.TxtIdCliente.setText("");
            this.TxtRazao.setText("");
            this.TxtCnpjCpf.setText("");
            this.TxtIdCliente.requestFocus();
        } else {
            this.TxtIdCliente.setEnabled(false);
            this.TxtRazao.setEnabled(true);
            this.TxtCnpjCpf.setEnabled(true);
            this.BtnConf1.setEnabled(true);
            this.TxtRazao.requestFocus();
        }

    }

    private void acaoConfirma0() {
        int id = 0;
        try {
            id = Integer.parseInt(this.TxtIdCliente.getText());
        } catch (NumberFormatException ex) {
        }

        if (id == 0) {
            JOptionPane.showMessageDialog(this, "id não preenchido");
            this.TxtIdCliente.requestFocus();
            return;
        }

        String sql = "select idcliente,razao,cnpjcpf from cliente where idcliente=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            cliente = null;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setRazao(rs.getString("razao"));
                cliente.setCnpjcpf(rs.getLong("cnpjcpf"));
            }
            existe = true;
            if (cliente == null) {
                existe = false;
                cliente = new Cliente();
                cliente.setIdCliente(id);
            }
            this.TxtRazao.setText(cliente.getRazao());
            this.TxtCnpjCpf.setText("" + cliente.getCnpjcpf());
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        tela = 1;
        trataTela();
    }

    private void acaoConfirma1() {
        long cnpj = 0;
        try {
            cnpj = Long.parseLong(this.TxtCnpjCpf.getText());
        } catch (NumberFormatException ex) {
        }
        if (cnpj == 0) {
            JOptionPane.showMessageDialog(this, "cnpj não preenchido");
            this.TxtCnpjCpf.requestFocus();
            return;
        }

        if (this.TxtRazao.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "razão não preenchido");
            this.TxtRazao.requestFocus();
            return;
        }

        cliente.setRazao(this.TxtRazao.getText());
        cliente.setCnpjcpf(Long.parseLong(this.TxtCnpjCpf.getText()));

        String sql;

        try {
            PreparedStatement ps;
            if (existe) {
                sql = "update cliente"
                        + " set razao=?"
                        + " ,cnpjcpf=?"
                        + " where idcliente = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, cliente.getRazao());
                ps.setLong(2, cliente.getCnpjcpf());
                ps.setInt(3, cliente.getIdCliente());
                ps.execute();
                ps.close();
            } else {
                sql = "insert into cliente"
                        + " (idcliente,razao,cnpjcpf)"
                        + " values "
                        + " (?,?,?)";//insere
                ps = conn.prepareStatement(sql);
                ps.setInt(1, cliente.getIdCliente());
                ps.setString(2, cliente.getRazao());
                ps.setLong(3, cliente.getCnpjcpf());
                ps.execute();
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        tela = 0;
        trataTela();
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
        jLabel1 = new javax.swing.JLabel();
        TxtIdCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtRazao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtCnpjCpf = new javax.swing.JTextField();
        BtnConf = new javax.swing.JButton();
        BtnFim = new javax.swing.JButton();
        BtnConf1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("idCliente");

        jLabel2.setText("Razao");

        jLabel3.setText("Cnpj _ Cpf");

        BtnConf.setMnemonic('c');
        BtnConf.setText("Confirmar");
        BtnConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfActionPerformed(evt);
            }
        });

        BtnFim.setMnemonic('f');
        BtnFim.setText("Fim");
        BtnFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFimActionPerformed(evt);
            }
        });

        BtnConf1.setMnemonic('x');
        BtnConf1.setText("Excluir");
        BtnConf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConf1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtRazao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnConf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(BtnConf1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(BtnFim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnConf, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnFim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnConf1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFimActionPerformed
        if (tela > 0) {
            tela--;
            trataTela();
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_BtnFimActionPerformed

    private void BtnConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfActionPerformed
        if (tela == 0) {
            acaoConfirma0();
        } else if (tela == 1) {
            acaoConfirma1();
        }
    }//GEN-LAST:event_BtnConfActionPerformed

    private void BtnConf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConf1ActionPerformed
        String sql;

        try {
            PreparedStatement ps;
            sql = "delete cliente where idcliente = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        tela = 0;
        trataTela();
    }//GEN-LAST:event_BtnConf1ActionPerformed

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
            java.util.logging.Logger.getLogger(Aplicativo001.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicativo001.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicativo001.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicativo001.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicativo001().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConf;
    private javax.swing.JButton BtnConf1;
    private javax.swing.JButton BtnFim;
    private javax.swing.JTextField TxtCnpjCpf;
    private javax.swing.JTextField TxtIdCliente;
    private javax.swing.JTextField TxtRazao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
