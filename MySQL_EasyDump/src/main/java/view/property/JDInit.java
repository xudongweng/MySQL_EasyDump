/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.property;

import com.cfg.PublicPropertyTmp;
import com.controller.XMLController;
import com.helper.file.FileHelper;
import java.io.File;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author xudong.weng
 */
public class JDInit extends javax.swing.JDialog {

    /**
     * Creates new form JDInit
     */
    public JDInit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jtaBin.setLineWrap(true);
        this.getReadConfig();
        
    }
    
    private PublicPropertyTmp ppt=new PublicPropertyTmp();
    public void getReadConfig(){
        XMLController.readConfig("config", ppt);
        this.jtfMyIndex.setText(ppt.getMysqlIndex());
        this.jtfLaunch.setText(ppt.getLaunch());
        this.jtfMyError.setText(ppt.getErr());
        this.jtaBin.setText(ppt.getBin());
        this.jcbEncode.setSelectedItem(ppt.getEncoding());
        this.jcbBackupType.setSelectedIndex(ppt.getBackuptype());
        this.jsBackupThread.setValue(ppt.getBackuphread());
        this.jsZipThread.setValue(ppt.getZipthread());
        this.jsRemotePort.setValue(ppt.getRemoteport());
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbnOK = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jcbBackupType = new javax.swing.JComboBox();
        jcbEncode = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfMyError = new javax.swing.JTextField();
        jtfLaunch = new javax.swing.JTextField();
        jtfMyIndex = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbnCancel = new javax.swing.JButton();
        jbnBin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaBin = new javax.swing.JTextArea();
        jbnClear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jsBackupThread = new javax.swing.JSpinner();
        jsZipThread = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jsRemotePort = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("配置");
        setResizable(false);

        jbnOK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbnOK.setText("确定");
        jbnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnOKActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel5.setText("备份方式：");

        jcbBackupType.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbBackupType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "单库独立", "多库群体", "线程备份" }));

        jcbEncode.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jcbEncode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "utf8", "gbk", "gb2312" }));

        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel4.setText("编码：");

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setText("错误记录：");

        jtfMyError.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jtfMyError.setText("myerr");

        jtfLaunch.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jtfLaunch.setText("launch");

        jtfMyIndex.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jtfMyIndex.setText("mysqlindex");

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("索引：");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("过程记录：");

        jbnCancel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbnCancel.setText("取消");
        jbnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnCancelActionPerformed(evt);
            }
        });

        jbnBin.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jbnBin.setText("<html>bin路径<br>设置</html>");
        jbnBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnBinActionPerformed(evt);
            }
        });

        jtaBin.setEditable(false);
        jtaBin.setColumns(20);
        jtaBin.setRows(5);
        jScrollPane1.setViewportView(jtaBin);

        jbnClear.setText("清除");
        jbnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnClearActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel6.setText("备份线程：");

        jLabel7.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel7.setText("压缩线程：");

        jsBackupThread.setModel(new SpinnerNumberModel(1, 1, 8, 1));
        jsBackupThread.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N

        jsZipThread.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        jsZipThread.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel8.setText("远程端口：");

        jsRemotePort.setModel(new SpinnerNumberModel(1, 1, 65535, 1));
        jsRemotePort.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMyIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfLaunch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMyError)
                                    .addComponent(jcbEncode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jcbBackupType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jsBackupThread, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7))
                                .addComponent(jsRemotePort))
                            .addGap(18, 18, 18)
                            .addComponent(jsZipThread, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbnBin)
                                .addComponent(jbnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(jbnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfMyIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfLaunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfMyError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbEncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbBackupType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jsBackupThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jsZipThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jsRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbnBin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbnClear))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbnCancel)
                    .addComponent(jbnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        final JSpinner.NumberEditor editor=new JSpinner.NumberEditor(jsBackupThread,"0");
        jsBackupThread.setEditor(editor);
        final JFormattedTextField textField = ((JSpinner.NumberEditor)jsBackupThread.getEditor()).getTextField();
        textField.setEditable(true);
        // 开启输入的值的限制
        final DefaultFormatterFactory factory = (DefaultFormatterFactory)textField.getFormatterFactory();
        final NumberFormatter formatter = (NumberFormatter)factory.getDefaultFormatter();
        formatter.setAllowsInvalid(false);// 此处对输入的有效性进行控制。若改为true，则不控制有效性
        final JSpinner.NumberEditor editorzip=new JSpinner.NumberEditor(jsZipThread,"0");
        jsZipThread.setEditor(editorzip);
        final JFormattedTextField textFieldzip = ((JSpinner.NumberEditor)jsZipThread.getEditor()).getTextField();
        textFieldzip.setEditable(true);
        // 开启输入的值的限制
        final DefaultFormatterFactory factoryzip = (DefaultFormatterFactory)textFieldzip.getFormatterFactory();
        final NumberFormatter formatterzip = (NumberFormatter)factory.getDefaultFormatter();
        formatterzip.setAllowsInvalid(false);// 此处对输入的有效性进行控制。若改为true，则不控制有效性
        final JSpinner.NumberEditor editorport=new JSpinner.NumberEditor(jsRemotePort,"0");
        jsRemotePort.setEditor(editorport);
        final JFormattedTextField textFieldport = ((JSpinner.NumberEditor)jsRemotePort.getEditor()).getTextField();
        textFieldport.setEditable(true);
        // 开启输入的值的限制
        final DefaultFormatterFactory factoryport = (DefaultFormatterFactory)textFieldport.getFormatterFactory();
        final NumberFormatter formatterport = (NumberFormatter)factoryport.getDefaultFormatter();
        formatterport.setAllowsInvalid(false);// 此处对输入的有效性进行控制。若改为true，则不控制有效性

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnOKActionPerformed
        if(this.jtfMyIndex.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "索引不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.jtfLaunch.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "过程记录不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.jtfMyError.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "错误不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.jtaBin.getText().equals("")){
            if(!FileHelper.getFileExist(this.jtaBin.getText()+File.separator+"mysqldump")&&!FileHelper.getFileExist(this.jtaBin.getText()+File.separator+"mysqldump.exe")){
                JOptionPane.showMessageDialog(null, "未找到mysql执行路径。", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        PublicPropertyTmp ppt=new PublicPropertyTmp();
        ppt.setMysqlIndex(this.jtfMyIndex.getText());
        ppt.setLaunch(this.jtfLaunch.getText());
        ppt.setErr(this.jtfMyError.getText());
        ppt.setEncoding(this.jcbEncode.getSelectedItem().toString());
        ppt.setBackuptype(this.jcbBackupType.getSelectedIndex());
        ppt.setBin(this.jtaBin.getText());
        ppt.setBackuphread(Integer.valueOf(this.jsBackupThread.getValue().toString()));
        ppt.setZipthread(Integer.valueOf(this.jsZipThread.getValue().toString()));
        ppt.setRemoteport(Integer.valueOf(this.jsRemotePort.getValue().toString()));
        XMLController.updateConfig("config", ppt);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jbnOKActionPerformed

    private void jbnBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnBinActionPerformed
        String bin=FileHelper.getFilePath(this);
        if(!bin.equals("")){
            this.jtaBin.setText(bin);
        }
    }//GEN-LAST:event_jbnBinActionPerformed

    private void jbnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbnCancelActionPerformed

    private void jbnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnClearActionPerformed
        this.jtaBin.setText("");
    }//GEN-LAST:event_jbnClearActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbnBin;
    private javax.swing.JButton jbnCancel;
    private javax.swing.JButton jbnClear;
    private javax.swing.JButton jbnOK;
    private javax.swing.JComboBox jcbBackupType;
    private javax.swing.JComboBox jcbEncode;
    private javax.swing.JSpinner jsBackupThread;
    private javax.swing.JSpinner jsRemotePort;
    private javax.swing.JSpinner jsZipThread;
    private javax.swing.JTextArea jtaBin;
    private javax.swing.JTextField jtfLaunch;
    private javax.swing.JTextField jtfMyError;
    private javax.swing.JTextField jtfMyIndex;
    // End of variables declaration//GEN-END:variables
}
