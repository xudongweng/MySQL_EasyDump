/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.log;

import com.controller.MongoDBController;
import com.model.MongoObj;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author xudong.weng
 */
public class JDLog extends javax.swing.JDialog {

    
    private MongoObj mongo=null;
    
    public JDLog(java.awt.Frame parent, boolean modal,MongoObj mongo) {
        super(parent, modal);
        initComponents();

        //this.jtaLogInfo.setLineWrap(true);
        this.jtaLogInfo.setWrapStyleWord(true);
        this.mongo=mongo;
        List listdate=MongoDBController.getLogInfo("date",mongo);
        
        if(listdate!=null){
            for(int i=0;i<listdate.size();i++){
                this.jcbDate.addItem(listdate.get(i).toString());
            }
        }
        List listlinkname=MongoDBController.getLogInfo("linkname",mongo);
        if(listlinkname!=null){
            for(int i=0;i<listlinkname.size();i++){
                if(!listlinkname.get(i).toString().equals(""))
                    this.jcbLinkname.addItem(listlinkname.get(i).toString());
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtaLogInfo = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jcbDate = new javax.swing.JComboBox<>();
        jbSearch = new javax.swing.JButton();
        jcbStatus = new javax.swing.JComboBox<>();
        jcbLinkname = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("运行日志");
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jtaLogInfo.setColumns(20);
        jtaLogInfo.setFont(new java.awt.Font("微软雅黑", 0, 13)); // NOI18N
        jtaLogInfo.setRows(5);
        jScrollPane1.setViewportView(jtaLogInfo);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jcbDate.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null" }));

        jbSearch.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbSearch.setText("查找");
        jbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSearchActionPerformed(evt);
            }
        });

        jcbStatus.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null", "Start backup", "Finished backup", "Backup failed", "Execute sql", "Disk infomation", "Start compress", "Finished compress", "All finished", "Is nothing", "Initialization" }));

        jcbLinkname.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbLinkname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null" }));

        jLabel13.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel13.setText("日志信息：");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jcbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbLinkname, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jbSearch)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jcbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSearch)
                    .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbLinkname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSearchActionPerformed
        if(this.jcbDate.getSelectedItem().equals("Null")&&this.jcbStatus.getSelectedItem().equals("Null")&&
                this.jcbLinkname.getSelectedItem().equals("Null")){
            JOptionPane.showMessageDialog(null, "所有选项不能都为Null", "提示", JOptionPane.ERROR_MESSAGE);
        }else{
            Document doc=null;
            if(!this.jcbDate.getSelectedItem().equals("Null")){
                doc=new Document("date",this.jcbDate.getSelectedItem().toString());
            }
            if(!this.jcbStatus.getSelectedItem().equals("Null")){
                if(doc==null)
                    doc=new Document("status",this.jcbStatus.getSelectedItem().toString());
                else
                    doc.append( "status",this.jcbStatus.getSelectedItem().toString());
            }
            if(!this.jcbLinkname.getSelectedItem().equals("Null")){
                if(doc==null)
                    doc=new Document("linkname",this.jcbLinkname.getSelectedItem().toString());
                else
                    doc.append("linkname",this.jcbLinkname.getSelectedItem().toString());
            }
            this.jtaLogInfo.setText(MongoDBController.getLog(doc,mongo));
        }
        
        
    }//GEN-LAST:event_jbSearchActionPerformed

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSearch;
    private javax.swing.JComboBox<String> jcbDate;
    private javax.swing.JComboBox<String> jcbLinkname;
    private javax.swing.JComboBox<String> jcbStatus;
    private javax.swing.JTextArea jtaLogInfo;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the mongo
     */
    public MongoObj getMongo() {
        return mongo;
    }

    /**
     * @param mongo the mongo to set
     */
    public void setMongo(MongoObj mongo) {
        this.mongo = mongo;
    }
    //</editor-fold>
    
    
    
    
    
 
  
}
