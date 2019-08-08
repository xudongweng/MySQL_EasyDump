
package com.xml.view;

import com.xml.view.sub.JDEditURL;
import com.xml.view.sub.JDCreateURL;
import com.xml.view.sub.JDBackupPlan;
import com.xml.controller.XMLController;
import com.xml.helper.file.FileHelper;
import com.xml.helper.file.FileObjDic;
import com.xml.helper.file.object.FileObject;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import com.xml.model.url.link.Database;
import com.xml.model.url.link.DatabaseDic;
import com.xml.model.url.link.LinkDic;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import com.xml.view.property.JDInit;
import com.xml.view.property.JDRemote;
import com.xml.view.tools.JDMirror;

public class JFManagement extends javax.swing.JFrame {


    private int width=0;
    private int height=0;
    
    private TrayIcon trayIcon;//托盘图标
    private SystemTray systemTray;//系统托盘
    
    
    
    public JFManagement() {
        initComponents();
        addComponents();
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        
        // <editor-fold defaultstate="collapsed" desc="系统托盘">  
        systemTray = SystemTray.getSystemTray();//获得系统托盘的实例
        
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("download.png")));
            systemTray.add(trayIcon);//设置托盘的图标，0.gif与该类文件同一目录
        }
        catch (IOException|AWTException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"JFManagement.JFManagement",e.getMessage());
            JOptionPane.showMessageDialog(rootPane,e.getMessage(), "提示", JOptionPane.OK_OPTION);
            this.dispose();
        }
        
        trayIcon.addMouseListener(
        new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2)//双击托盘窗口再现
                setExtendedState(JFrame.NORMAL);
                setVisible(true);
            }
        });
        // </editor-fold>
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLinks = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtStatusLists = new javax.swing.JTable();
        jlblPath = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiNewlink = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiMirror = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmConfig = new javax.swing.JMenu();
        jmiPro = new javax.swing.JMenuItem();
        jmiRemote = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MySQL_EasyDump 1.33");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setName(""); // NOI18N

        jtLinks.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jtLinks.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jtLinks.setName(""); // NOI18N
        if(URLDic.getURLs()!=null){
            List<Map.Entry<String, URL>> listURLs=URLDic.getSortURLs();//调用排序
            //遍历连接名
            for(int i=0;i<listURLs.size();i++){
                DefaultMutableTreeNode trunknode = new DefaultMutableTreeNode(listURLs.get(i).getKey());
                treeNode1.add(trunknode);
                if(FileHelper.getFileExist(listURLs.get(i).getKey()+".xml")){
                    DatabaseDic databaseDic=LinkDic.getLink(listURLs.get(i).getKey());
                    List<Map.Entry<String,Database>> listDatabases=databaseDic.getSortDatabases();//调用排序
                    //遍历数据库名
                    for(int j=0;j<listDatabases.size();j++){
                        DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode(listDatabases.get(j).getKey());
                        trunknode.add(leafnode);
                    }
                }
            }
        }
        jtLinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLinksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtLinks);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtStatusLists.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jtStatusLists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtStatusLists);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jlblPath.setText(" PATH：");
        jlblPath.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jlblPath, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel1);

        jMenu1.setText("文件");

        jmiNewlink.setText("新建连接");
        jmiNewlink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewlinkActionPerformed(evt);
            }
        });
        jMenu1.add(jmiNewlink);
        jMenu1.add(jSeparator1);

        jmiExit.setText("退出");
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jMenu1.add(jmiExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("工具");

        jmiMirror.setText("同步对比");
        jmiMirror.setEnabled(false);
        jmiMirror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMirrorActionPerformed(evt);
            }
        });
        jMenu2.add(jmiMirror);
        jMenu2.add(jSeparator2);

        jmConfig.setText("配置");

        jmiPro.setText("属性");
        jmiPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProActionPerformed(evt);
            }
        });
        jmConfig.add(jmiPro);

        jmiRemote.setText("远程");
        jmiRemote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoteActionPerformed(evt);
            }
        });
        jmConfig.add(jmiRemote);

        jMenu2.add(jmConfig);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNewlinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewlinkActionPerformed
        if(jdcurl==null){
            jdcurl=new JDCreateURL(this,true);
        }else{
            jdcurl.setValue();
        }

        int w=jdcurl.getWidth();
        int h=jdcurl.getHeight();
        jdcurl.setLocation( (width - w) / 2, (height - h) / 2);
        jdcurl.setVisible(true);
        if(!jdcurl.getLinkname().equals("")){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)jtLinks.getModel().getRoot();
            ((DefaultTreeModel) jtLinks.getModel()).insertNodeInto(new DefaultMutableTreeNode(jdcurl.getLinkname()), node, node.getChildCount());
        }
    }//GEN-LAST:event_jmiNewlinkActionPerformed

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jmiExitActionPerformed

    private void jtLinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLinksMouseClicked
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (node == null){
            return;
        }
        if(evt.getButton()==1)//鼠标左键
        {
            if(jtLinks.getSelectionPath().getPathCount()==3){
                //JOptionPane.showMessageDialog(null, node.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                DefaultMutableTreeNode parnetNode = (DefaultMutableTreeNode) node.getParent();//获取父节点
                //JOptionPane.showMessageDialog(null, parnetNode.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                URL url=URLDic.getURL(parnetNode.toString());
                
                //获取路径下文件属性和状态
                FileObjDic fod=FileHelper.getFilesPro(url.getSavepath()+
                        PublicProperty.getPathmark()+parnetNode.toString()+
                        PublicProperty.getPathmark()+node.toString());
                this.jlblPath.setText(" PATH："+url.getSavepath()+
                        PublicProperty.getPathmark()+parnetNode.toString()+
                        PublicProperty.getPathmark()+node.toString());
                //显示表
                
                String[] col={"文件名","文件大小","备份日期","备份状态"};
                Object[][] data=null;
                //int i=0;
                if(fod!=null){
                    List<Map.Entry<String, FileObject>> filelist=fod.getSortFileObjects();
                    data=new Object[filelist.size()][4];
                    for(int i=0;i<filelist.size();i++){
                        FileObject fo= filelist.get(i).getValue();
                        data[i][0]=fo.getFilename();
                        data[i][1]=fo.getFilesize();
                        //data[i][2]=fo.getFilepath();
                        data[i][2]=fo.getFiledate();
                        data[i][3]=fo.getBackupstate();
                    }
                }
                
                DefaultTableModel model = new DefaultTableModel(data, col);
                this.jtStatusLists.setModel(model);
                //设置排序
                RowSorter<TableModel> sorter = new TableRowSorter<>(model); 
                this.jtStatusLists.setRowSorter(sorter);
            }else{
                this.jtStatusLists.setModel(new DefaultTableModel());
                this.jlblPath.setText(" PATH：");
            }
        }
        else if (evt.getButton() == 3) //鼠标右键
        {
            if(jtLinks.getSelectionPath().getPathCount()==1||jtLinks.getSelectionPath().getPathCount()==3){
                return;
            }
            TreePath path = jtLinks.getPathForLocation(evt.getX(), evt.getY()); // 关键是这个方法的使用
            jtLinks.setSelectionPath(path);
            jpmPopMenu.show(jtLinks, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtLinksMouseClicked

    private void jmiMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMirrorActionPerformed

        if(this.jdm==null){
            this.jdm=new JDMirror(this,true);
        }else{
            this.jdm.updateLink();
        }
        int w=jdm.getWidth();
        int h=jdm.getHeight();
        jdm.setLocation( (width - w) / 2, (height - h) / 2);
        jdm.setVisible(true);

    }//GEN-LAST:event_jmiMirrorActionPerformed

    private void jmiProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProActionPerformed
        if(this.jdi==null){
            jdi=new JDInit(this,true);
        }else{
            jdi.getReadConfig();
        }
        int w=jdi.getWidth();
        int h=jdi.getHeight();
        jdi.setLocation( (width - w) / 2, (height - h) / 2);
        jdi.setVisible(true);
    }//GEN-LAST:event_jmiProActionPerformed

    private void jmiRemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoteActionPerformed
        if(this.jdr==null){
            jdr=new JDRemote(this,true);
        }
        int w=jdr.getWidth();
        int h=jdr.getHeight();
        jdr.setLocation( (width - w) / 2, (height - h) / 2);
        jdr.setVisible(true);
    }//GEN-LAST:event_jmiRemoteActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        this.setVisible(false);//系统托盘最小化隐藏
    }//GEN-LAST:event_formWindowIconified
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel jlblPath;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiMirror;
    private javax.swing.JMenuItem jmiNewlink;
    private javax.swing.JMenuItem jmiPro;
    private javax.swing.JMenuItem jmiRemote;
    private javax.swing.JTree jtLinks;
    private javax.swing.JTable jtStatusLists;
    // End of variables declaration//GEN-END:variables
    
    
    
    private JDCreateURL jdcurl=null;
    private JDEditURL jdeurl=null;
    private JDBackupPlan jdbp=null;
    
    private JDMirror jdm=null;
    private JDInit jdi=null;
    private JDRemote jdr=null;
    // <editor-fold defaultstate="collapsed" desc="自定义弹出菜单">
    private JPopupMenu jpmPopMenu;
    private JMenuItem jmiAddLink;
    private JMenuItem jmiDelLink;
    private JMenuItem jmiEditLink;
    private JMenuItem jmiNewBackup;
    
    private void addComponents() {
        jpmPopMenu = new JPopupMenu();
        jmiAddLink=new JMenuItem("新建连接");
        jmiAddLink.addActionListener((java.awt.event.ActionEvent evt) -> {
            jmiAddLinkActionPerformed(evt);
        });
        jmiDelLink = new JMenuItem("删除连接");
        jmiDelLink.addActionListener((ActionEvent evt) -> {
            jmiDelLinkActionPerformed(evt);
        });
        jmiEditLink = new JMenuItem("修改连接");
        jmiEditLink.addActionListener((ActionEvent evt) -> {
            jmiEditLinkActionPerformed(evt);
        });
        
        jmiNewBackup=new JMenuItem("备份计划");
        jmiNewBackup.addActionListener((ActionEvent evt) -> {
            jmiBackupPlanActionPerformed(evt);
        });
        jpmPopMenu.add(jmiAddLink);
        jpmPopMenu.add(jmiDelLink);
        jpmPopMenu.add(jmiEditLink);
        jpmPopMenu.add(jmiNewBackup);
    }
    
    //备份计划
    private void jmiBackupPlanActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultMutableTreeNode trunkNode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (trunkNode == null)
            return;
        if(jdbp==null){
            jdbp=new JDBackupPlan(this,true);
        }
        String linkname=trunkNode.getUserObject().toString();
        URL url=URLDic.getURL(linkname);//获取URL生成数据库连接
        jdbp.dbRefresh(url.getUser(), url.getPwd(), url.getServer(),url.getPort(),linkname);
        int w=jdbp.getWidth();
        int h=jdbp.getHeight();
        jdbp.setLocation( (width - w) / 2, (height - h) / 2);
        jdbp.setResizable(false);//控制大小
        
        jdbp.setVisible(true);
        //更新节点
        if(jdbp.getButtonMark()==true){
            trunkNode.removeAllChildren();
            Collections.sort(jdbp.getDBList());//排序
            for(String dbname:jdbp.getDBList()){
                DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode(dbname);
                trunkNode.add(leafnode);
            }
            this.jtLinks.updateUI();
        }
        jdbp.getDcTemp().clearAll();
    }
    
    private void jmiAddLinkActionPerformed(java.awt.event.ActionEvent evt) { 
        if(jdcurl==null){
            jdcurl=new JDCreateURL(this,true);
        }else{
            jdcurl.setValue();
        }
        int w=jdcurl.getWidth();
        int h=jdcurl.getHeight();
        jdcurl.setLocation( (width - w) / 2, (height - h) / 2);
        jdcurl.setVisible(true);
        if(!jdcurl.getLinkname().equals("")){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)jtLinks.getModel().getRoot();
            ((DefaultTreeModel) jtLinks.getModel()).insertNodeInto(new DefaultMutableTreeNode(jdcurl.getLinkname()), node, node.getChildCount());
        }
        //URLDic.printURLs();
    }
    
    private void jmiEditLinkActionPerformed(java.awt.event.ActionEvent evt){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (node == null)
            return;
        if(jdeurl==null){
            jdeurl=new JDEditURL(this,true,node.getUserObject().toString());
        }else{
            jdeurl.setvalue(node.getUserObject().toString());
        }
        int w=jdeurl.getWidth();
        int h=jdeurl.getHeight();
        jdeurl.setLocation( (width - w) / 2, (height - h) / 2);
        jdeurl.setVisible(true);
        if(!jdeurl.getLinkname().equals("")){
            node.setUserObject(jdeurl.getLinkname());
            this.jtLinks.updateUI();
        }
        //URLDic.printURLs();
    }
    
    private void jmiDelLinkActionPerformed(java.awt.event.ActionEvent evt) { 
        DefaultMutableTreeNode trunkNode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (trunkNode == null)
            return;
        if(JOptionPane.showConfirmDialog(rootPane, "是否确定删除"+trunkNode.getUserObject().toString()+"?", "提示", JOptionPane.YES_NO_OPTION)==0){
            String linkname=trunkNode.getUserObject().toString();
            URLDic.removeURL(linkname);//删除URL
            LinkDic.removeLink(linkname);//删除linkname
            FileHelper.deleteFile(linkname+".xml");//删除linkname.xml
            //删除节点
            XMLController.delURL(trunkNode.getUserObject().toString(), PublicProperty.getMysqlIndex());
            ((DefaultTreeModel) jtLinks.getModel()).removeNodeFromParent(trunkNode);
        }
        //URLDic.printURLs();
    }

    // </editor-fold>
}
