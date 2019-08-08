/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import com.controller.XMLController;
import com.helper.file.FileHelper;
import com.helper.MySQLHelper;
import com.helper.UUIDHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import model.url.link.Database;
import model.url.link.DatabaseDic;
import model.url.link.LinkDic;

/**
 *
 * @author xudong.weng
 */
public class JDBackupPlan extends javax.swing.JDialog {

    private String linkname="";
    private String user="";
    private String password="";
    private String server="";
    private String port="";
    private final DefaultListModel dlmDBList = new DefaultListModel();//未被备份的数据列
    private final DefaultListModel dlmBackupList = new DefaultListModel();//已备份的数据库列
    private List<String> arrDBList=null;//与dlmBackupList同步，用于修改叶节点
    private DatabaseDic dcTemp=null;
    private String selectV="";//保存备份数据库的选择变量，防止焦点变化备份周期赋值错误
    
    private boolean buttonMark=false;//标记是否需要更新数据库叶节点
    
    public DatabaseDic getDcTemp(){
        return this.dcTemp;
    }
    
    public boolean getButtonMark(){
        return this.buttonMark;
    }
    public List<String> getDBList(){
        
        return this.arrDBList;
    }
    
    public JDBackupPlan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.disableOption();//设置选项初始化
    }
    
    public void setLinkname(String linkname){
        this.linkname=linkname;
    }
    public String getLinkname(){
        return this.linkname;
    }
    public String getUser(){
        return this.user;
    }
    public void setUser(String user){
        this.user=user;
    }
    //刷新现在数据库现在备份情况和未备份数据库的情况，但是如果备份数据库不存在，但仍然处于备份状态的话是不会在此处处理掉和显示的。
    public void dbRefresh(String user,String pwd,String server,String port,String linkname){
        this.buttonMark=false;
        this.setUser(user);
        this.setPassword(pwd);
        this.setServer(server);
        this.setPort(port);
        this.setLinkname(linkname);
        
        this.dlmDBList.removeAllElements();
        this.dlmBackupList.removeAllElements();
        RowSet rs=MySQLHelper.getAllDB(user, pwd, server,port);
        if(rs!=null){
            //判断是否存在linkname的关联文件
            if(LinkDic.getExist(linkname)){
                DatabaseDic databaseDic=LinkDic.getLink(linkname);             
                this.dcTemp=databaseDic.cloneDic();//克隆数据库相关备份数据
                try{
                    while (rs.next()) {
                        if(databaseDic.getExist(rs.getString(1)))//判断该数据库是否为备份数据库
                            this.dlmBackupList.addElement(rs.getString(1));
                        else
                            dlmDBList.addElement(rs.getString(1));
                    }
                }catch(SQLException sqlEx){
                    JOptionPane.showMessageDialog(null, sqlEx.getErrorCode()+sqlEx.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                this.dcTemp=new DatabaseDic();
                try{
                    while (rs.next()) {
                        //LinkDic.getLink(linkname).getDatabase(rs.getString(1))
                        dlmDBList.addElement(rs.getString(1));
                    }
                }catch(SQLException sqlEx){
                    JOptionPane.showMessageDialog(null, sqlEx.getErrorCode()+sqlEx.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "数据库无法连接。", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlDBList = new javax.swing.JList(dlmDBList);
        jScrollPane2 = new javax.swing.JScrollPane();
        jlBackupList = new javax.swing.JList(dlmBackupList);
        jbRightMove = new javax.swing.JButton();
        jbRightMoveAll = new javax.swing.JButton();
        jbLeftMove = new javax.swing.JButton();
        jbLeftMoveAll = new javax.swing.JButton();
        jbOK = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbRefresh = new javax.swing.JButton();
        jcbSpliteTable = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jsBackupDays = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("备份计划");
        setMinimumSize(new java.awt.Dimension(535, 491));
        setResizable(false);

        jlDBList.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jlDBList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlDBListFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jlDBList);

        jlBackupList.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jlBackupList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlBackupList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlBackupListFocusGained(evt);
            }
        });
        jlBackupList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlBackupListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jlBackupList);

        jbRightMove.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jbRightMove.setText(">");
        jbRightMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRightMoveActionPerformed(evt);
            }
        });

        jbRightMoveAll.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jbRightMoveAll.setText(">>");
        jbRightMoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRightMoveAllActionPerformed(evt);
            }
        });

        jbLeftMove.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jbLeftMove.setText("<");
        jbLeftMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLeftMoveActionPerformed(evt);
            }
        });

        jbLeftMoveAll.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jbLeftMoveAll.setText("<<");
        jbLeftMoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLeftMoveAllActionPerformed(evt);
            }
        });

        jbOK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbOK.setText("确定");
        jbOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOKActionPerformed(evt);
            }
        });

        jbCancel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbCancel.setText("取消");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("数据库列表：");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("需要备份的数据库：");

        jbRefresh.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbRefresh.setText("刷新");
        jbRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRefreshActionPerformed(evt);
            }
        });

        jcbSpliteTable.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbSpliteTable.setText("分表");
        jcbSpliteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbSpliteTableMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setText("备份周期");

        jsBackupDays.setModel(new SpinnerNumberModel(1, 1, 30, 1));
        jsBackupDays.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jsBackupDays.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsBackupDaysStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jbRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164)
                        .addComponent(jbOK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbRightMoveAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbLeftMoveAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbLeftMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbRightMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jsBackupDays)
                                    .addComponent(jcbSpliteTable, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
                            .addComponent(jLabel2))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jbRightMove)
                                .addGap(18, 18, 18)
                                .addComponent(jbRightMoveAll)
                                .addGap(18, 18, 18)
                                .addComponent(jbLeftMove)
                                .addGap(18, 18, 18)
                                .addComponent(jbLeftMoveAll))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbSpliteTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsBackupDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(317, 317, 317)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jbOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        final JSpinner.NumberEditor editor=new JSpinner.NumberEditor(jsBackupDays,"0");
        jsBackupDays.setEditor(editor);
        final JFormattedTextField textField = ((JSpinner.NumberEditor)jsBackupDays.getEditor()).getTextField();
        textField.setEditable(true);
        // 开启输入的值的限制
        final DefaultFormatterFactory factory = (DefaultFormatterFactory)textField.getFormatterFactory();
        final NumberFormatter formatter = (NumberFormatter)factory.getDefaultFormatter();
        formatter.setAllowsInvalid(false);// 此处对输入的有效性进行控制。若改为true，则不控制有效性

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        this.buttonMark=false;//标记不需要更新数据库叶节点
        this.dispose();
    }//GEN-LAST:event_jbCancelActionPerformed

    private void jbRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRefreshActionPerformed
        this.dbRefresh(this.getUser(),this.getPassword(),this.getServer(),this.getPort(),this.getLinkname());
    }//GEN-LAST:event_jbRefreshActionPerformed

    private void jbRightMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRightMoveActionPerformed
        //移动 数据库列表 > 需要备份数据库
        List selectedlist=this.jlDBList.getSelectedValuesList();
        for (Object list : selectedlist) {
            //JOptionPane.showMessageDialog(null, list, "错误", JOptionPane.ERROR_MESSAGE);
            this.dcTemp.setDatabase(list.toString(),new Database(UUIDHelper.createUUID()));
            this.dcTemp.getDatabase(list.toString()).setMark(1);
            dlmBackupList.addElement(list);
            this.dlmDBList.removeElement(list);
        }
        this.disableOption();
        this.buttonMark=true;
    }//GEN-LAST:event_jbRightMoveActionPerformed

    private void disableOption(){
        this.jcbSpliteTable.setEnabled(false);
        this.jcbSpliteTable.setSelected(true);
        this.jsBackupDays.setEnabled(false);
    }
    
    
    private void jbRightMoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRightMoveAllActionPerformed

        for(int i = 0; i < dlmDBList.getSize(); i++) {
            this.dcTemp.setDatabase(dlmDBList.getElementAt(i).toString(),new Database(UUIDHelper.createUUID()));
            this.dcTemp.getDatabase(dlmDBList.getElementAt(i).toString()).setMark(1);
            dlmBackupList.addElement(dlmDBList.getElementAt(i));
            //JOptionPane.showMessageDialog(null, dlmDBList.getElementAt(i), "错误", JOptionPane.ERROR_MESSAGE);
        }
        this.dlmDBList.removeAllElements();
        this.disableOption();
        this.buttonMark=true;
    }//GEN-LAST:event_jbRightMoveAllActionPerformed

    private void jbLeftMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLeftMoveActionPerformed
        List selectedlist=this.jlBackupList.getSelectedValuesList();
        for (Object list : selectedlist) {
            //JOptionPane.showMessageDialog(null, list, "错误", JOptionPane.ERROR_MESSAGE);
            if(this.dcTemp.getExist(list.toString())){
                this.dcTemp.removeDatabase(list.toString());
            }
            dlmDBList.addElement(list);
            this.dlmBackupList.removeElement(list);
        }
        this.disableOption();
        this.buttonMark=true;
    }//GEN-LAST:event_jbLeftMoveActionPerformed

    private void jbLeftMoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLeftMoveAllActionPerformed
        for(int i = 0; i < dlmBackupList.getSize(); i++) {
            dlmDBList.addElement(dlmBackupList.getElementAt(i));
            //JOptionPane.showMessageDialog(null, dlmDBList.getElementAt(i), "错误", JOptionPane.ERROR_MESSAGE);
        }
        this.dcTemp.clearAll();
        this.dlmBackupList.removeAllElements();
        
        this.disableOption();
        this.buttonMark=true;
    }//GEN-LAST:event_jbLeftMoveAllActionPerformed

    private void jbOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOKActionPerformed
        
        //清空叶节点内容
        if(arrDBList!=null){
            arrDBList.clear();
        }
        //对原数据库字典做清理
        if(FileHelper.getFileExist(linkname+".xml")){
            DatabaseDic databaseDic=LinkDic.getLink(linkname);
            databaseDic.clearAll();
        }
        //查找是否存在需要备份的数据库
        if(dlmBackupList.getSize()>0){
            //查找是否存在linkname.xml
            if(FileHelper.getFileExist(linkname+".xml")){
                XMLController.updateDBs(linkname, dcTemp);
                
            }else{
                //linkname.xml不存在，进行新建
                XMLController.createXML(linkname);
                XMLController.addLink(linkname);
                XMLController.updateDBs(linkname, dcTemp);
            }
            for(int i = 0; i < dlmBackupList.getSize(); i++) {
                if(arrDBList==null){
                    arrDBList=new ArrayList<>();
                    arrDBList.add(dlmBackupList.getElementAt(i).toString());
                }else{
                    arrDBList.add(dlmBackupList.getElementAt(i).toString());
                }
            }
            this.buttonMark=true;
        }else if(FileHelper.getFileExist(linkname+".xml")){
            if(arrDBList==null)
                arrDBList=new ArrayList<>();
            else{
                arrDBList.clear();
            }
            FileHelper.deleteFile(linkname+".xml");
        }
        
        if(this.dcTemp!=null){
            this.dcTemp.clearAll();
        }
        this.dispose();
    }//GEN-LAST:event_jbOKActionPerformed
    
    
    
    private void jlBackupListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBackupListMouseClicked

        //System.out.println(String.valueOf(this.jlBackupList.getSelectedValue()));
        this.selectV=this.jlBackupList.getSelectedValue().toString();
        if(this.dcTemp==null){
            this.dcTemp=new DatabaseDic();
        }else{
            Database db=this.dcTemp.getDatabase(this.jlBackupList.getSelectedValue().toString());
            if(db.getSplite()==1){
                this.jcbSpliteTable.setSelected(true);
            }
            else{
                this.jcbSpliteTable.setSelected(false);
            }
            this.jsBackupDays.setValue(db.getDay());
        }
        
        /*
        if(this.dcTemp.getExist(String.valueOf(this.jlBackupList.getSelectedValue()))){
            Database db=dcTemp.getDatabase(String.valueOf(this.jlBackupList.getSelectedValue()));
            if(db.getSplite()==1) 
                this.jcbSpliteTable.setSelected(true);
            else
                this.jcbSpliteTable.setSelected(false);
            this.jsBackupDays.setValue(db.getDay());
            
        }else if(FileHelper.getFileExist(linkname+".xml")){
            DatabaseDic dc=LinkDic.getLink(linkname);
            if(dc.getExist(String.valueOf(this.jlBackupList.getSelectedValue()))){ //查找是否存在于备份计划
                Database db=dc.getDatabase(String.valueOf(this.jlBackupList.getSelectedValue()));
                if(db.getSplite()==1) 
                    this.jcbSpliteTable.setSelected(true);
                else
                    this.jcbSpliteTable.setSelected(false);
                this.jsBackupDays.setValue(db.getDay());
            }else{
                this.jcbSpliteTable.setSelected(true);
                this.jsBackupDays.setValue(7);
            }
        }else{
            this.jcbSpliteTable.setSelected(true);
            this.jsBackupDays.setValue(7);
        }*/
    }//GEN-LAST:event_jlBackupListMouseClicked

    private void jlBackupListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlBackupListFocusGained
        this.jcbSpliteTable.setEnabled(true);
        this.jsBackupDays.setEnabled(true);
    }//GEN-LAST:event_jlBackupListFocusGained

    private void jlDBListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlDBListFocusGained
        this.disableOption();
    }//GEN-LAST:event_jlDBListFocusGained

    private void jcbSpliteTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbSpliteTableMouseClicked
        //System.out.println(this.jcbSpliteTable.isSelected());
        //System.out.println(this.jlBackupList.getSelectedValue().toString());
        Database db=this.dcTemp.getDatabase(this.jlBackupList.getSelectedValue().toString());
        if(this.jcbSpliteTable.isSelected()){
            db.setSplite(1);
        }else{
            db.setSplite(0);
        }
        db.setMark(1);
    }//GEN-LAST:event_jcbSpliteTableMouseClicked

    private void jsBackupDaysStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsBackupDaysStateChanged
//        System.out.println(this.jsBackupDays.getValue());
//        System.out.println(this.jlBackupList.getSelectedValue().toString());
        if(this.selectV.equals("")){
            Database db=this.dcTemp.getDatabase(this.jlBackupList.getSelectedValue().toString());
            db.setDay(Integer.valueOf(this.jsBackupDays.getValue().toString()));
            db.setMark(1);
        }else{
            Database db=this.dcTemp.getDatabase(selectV);
            db.setDay(Integer.valueOf(this.jsBackupDays.getValue().toString()));
            db.setMark(1);
        }
    }//GEN-LAST:event_jsBackupDaysStateChanged
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbLeftMove;
    private javax.swing.JButton jbLeftMoveAll;
    private javax.swing.JButton jbOK;
    private javax.swing.JButton jbRefresh;
    private javax.swing.JButton jbRightMove;
    private javax.swing.JButton jbRightMoveAll;
    private javax.swing.JCheckBox jcbSpliteTable;
    private javax.swing.JList jlBackupList;
    private javax.swing.JList jlDBList;
    private javax.swing.JSpinner jsBackupDays;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

   
    

}
