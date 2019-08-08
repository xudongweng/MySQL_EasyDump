
package com.view;

import com.helper.file.FileHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
import com.cfg.PublicProperty;
import com.controller.RPCInfoController;
import com.controller.XMLClientController;
import com.helper.NowString;
import com.model.IPPort;
import com.model.IPPortDic;
import com.model.MongoDic;
import com.view.log.JDLog;
import org.jdom2.Element;
import com.view.property.JDInit;
import com.view.sub.JDCreateIPPort;


public class JFManagementClient extends javax.swing.JFrame {


    private int width=0;
    private int height=0;
    private JDLog jdl=null;

    public JFManagementClient() {
        initComponents();
        addComponents();
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        
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
        jmConfig = new javax.swing.JMenu();
        jmiPro = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MySQL_EasyDump_Client 1.53");

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setName(""); // NOI18N

        jtLinks.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jtLinks.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jtLinks.setName(""); // NOI18N
        if(IPPortDic.getIPPorts()!=null){
            List<Map.Entry<String, IPPort>> listIPPorts=IPPortDic.getSortIPPorts();
            for(int i=0;i<listIPPorts.size();i++){
                DefaultMutableTreeNode trunknode = new DefaultMutableTreeNode(listIPPorts.get(i).getKey());
                treeNode1.add(trunknode);
                /*
                if(FileHelper.getFileExist(listIPPorts.get(i).getKey()+".xml")){
                    DatabaseDic databaseDic=LinkDic.getLink(listURLs.get(i).getKey());
                    List<Map.Entry<String,Database>> listDatabases=databaseDic.getSortDatabases();//调用排序
                    //遍历数据库名
                    for(int j=0;j<listDatabases.size();j++){
                        DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode(listDatabases.get(j).getKey());
                        trunknode.add(leafnode);
                    }
                }*/
            }
        }
        jtLinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLinksMouseClicked(evt);
            }
        });
        jtLinks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtLinksKeyReleased(evt);
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

        jmConfig.setText("配置");

        jmiPro.setText("属性");
        jmiPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProActionPerformed(evt);
            }
        });
        jmConfig.add(jmiPro);

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
        if(jdcipp==null){
            jdcipp=new JDCreateIPPort(this,true);
        }else{
            jdcipp.setValue();
        }

        int w=jdcipp.getWidth();
        int h=jdcipp.getHeight();
        jdcipp.setLocation( (width - w) / 2, (height - h) / 2);
        jdcipp.setVisible(true);
        if(!jdcipp.getIPPortkey().equals("")){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)jtLinks.getModel().getRoot();
            ((DefaultTreeModel) jtLinks.getModel()).insertNodeInto(new DefaultMutableTreeNode(jdcipp.getIPPortkey()), node, node.getChildCount());
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
                DefaultMutableTreeNode leafnode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
                if (leafnode == null)
                    return;
                System.out.println(leafnode.getUserObject().toString());
                //JOptionPane.showMessageDialog(null, node.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                DefaultMutableTreeNode parnetNode = (DefaultMutableTreeNode) node.getParent();//获取父节点
                //JOptionPane.showMessageDialog(null, parnetNode.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                IPPort ipp=IPPortDic.getIPPort(parnetNode.getUserObject().toString());
                this.jlblPath.setText(" PATH："+leafnode.getUserObject().toString());
                //System.out.println(RPCInfoController.getFilePropertyXml(ipp.getIp(), ipp.getPort(),leafnode.getUserObject().toString()));
                //显示表
                List<Element> listrpcfileinfo =XMLClientController.readRPCInfo(RPCInfoController.getFilePropertyXml(ipp.getIp(), ipp.getPort(),leafnode.getUserObject().toString()));

                String[] col={"文件名","文件大小","备份日期","备份状态"};
                Object[][] data=new Object[listrpcfileinfo.size()][4];
                int i=0;
                for (Element elListRpcfileinfo : listrpcfileinfo) {
                    data[i][0]=elListRpcfileinfo.getAttributeValue("filename");
                    data[i][1]=elListRpcfileinfo.getAttributeValue("filesize");
                    data[i][2]=elListRpcfileinfo.getAttributeValue("filedate");
                    data[i][3]=elListRpcfileinfo.getAttributeValue("backupstate");
                    i++;
                }
                //Arrays.sort(data);
                
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

    private void jtLinksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtLinksKeyReleased
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (node == null){
            return;
        }
        if(jtLinks.getSelectionPath().getPathCount()==3){
                DefaultMutableTreeNode leafnode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
                if (leafnode == null)
                    return;
                System.out.println(leafnode.getUserObject().toString());
                //JOptionPane.showMessageDialog(null, node.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                DefaultMutableTreeNode parnetNode = (DefaultMutableTreeNode) node.getParent();//获取父节点
                //JOptionPane.showMessageDialog(null, parnetNode.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                IPPort ipp=IPPortDic.getIPPort(parnetNode.getUserObject().toString());
                this.jlblPath.setText("PATH："+leafnode.getUserObject().toString());
                //System.out.println(RPCInfoController.getFilePropertyXml(ipp.getIp(), ipp.getPort(),leafnode.getUserObject().toString()));
                //显示表
                List<Element> listrpcfileinfo =XMLClientController.readRPCInfo(RPCInfoController.getFilePropertyXml(ipp.getIp(), ipp.getPort(),leafnode.getUserObject().toString()));

                String[] col={"文件名","文件大小","备份日期","备份状态"};
                Object[][] data=new Object[listrpcfileinfo.size()][4];
                int i=0;
                for (Element elListRpcfileinfo : listrpcfileinfo) {
                    data[i][0]=elListRpcfileinfo.getAttributeValue("filename");
                    data[i][1]=elListRpcfileinfo.getAttributeValue("filesize");
                    data[i][2]=elListRpcfileinfo.getAttributeValue("filedate");
                    data[i][3]=elListRpcfileinfo.getAttributeValue("backupstate");
                    i++;
                }
                //Arrays.sort(data);
                
                DefaultTableModel model = new DefaultTableModel(data, col);
                
                
                
                this.jtStatusLists.setModel(model);
                //设置排序
                RowSorter<TableModel> sorter = new TableRowSorter<>(model); 
                this.jtStatusLists.setRowSorter(sorter);
            }else{
                this.jtStatusLists.setModel(new DefaultTableModel());
                this.jlblPath.setText("PATH：");
            }
    }//GEN-LAST:event_jtLinksKeyReleased
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel jlblPath;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiNewlink;
    private javax.swing.JMenuItem jmiPro;
    private javax.swing.JTree jtLinks;
    private javax.swing.JTable jtStatusLists;
    // End of variables declaration//GEN-END:variables
    
    
    
    private JDCreateIPPort jdcipp=null;
    private JDInit jdi=null;
    // <editor-fold defaultstate="collapsed" desc="自定义弹出菜单">
    private JPopupMenu jpmPopMenu;
    private JMenuItem jmiAddLink;
    private JMenuItem jmiDelLink;
    private JMenuItem jmiActiveLink;
    private JMenuItem jmiLog;
    
    private void addComponents() {
        jpmPopMenu = new JPopupMenu();
        
        jmiActiveLink=new JMenuItem("打开连接");
        jmiActiveLink.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActiveLinkActionPerformed(evt);
            }
        });
        
        jmiAddLink=new JMenuItem("新建连接");
        jmiAddLink.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddLinkActionPerformed(evt);
            }
        });
        jmiDelLink = new JMenuItem("删除连接");
        jmiDelLink.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jmiDelLinkActionPerformed(evt);
            }
        });

        this.jmiLog=new JMenuItem("查看日志");
        jmiLog.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jmiLogActionPerformed(evt);
            }
        });
        
        jpmPopMenu.add(jmiActiveLink);
        jpmPopMenu.add(jmiAddLink);
        jpmPopMenu.add(jmiDelLink);
        jpmPopMenu.add(jmiLog);
    }
    
    private void jmiActiveLinkActionPerformed(java.awt.event.ActionEvent evt){
        DefaultMutableTreeNode trunkNode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (trunkNode == null)
            return;
        System.out.println(trunkNode.getUserObject().toString());
        IPPort ipp=IPPortDic.getIPPort(trunkNode.getUserObject().toString());
        try{
            List<Element> listrpclink =XMLClientController.readRPCInfo(RPCInfoController.getLinkPathXml(ipp.getIp(), ipp.getPort()));

            for (Element elRPCLink : listrpclink) {
                List<Element> listdbpath=elRPCLink.getChildren();
                for(Element eldbpath:listdbpath){
                    System.out.println(eldbpath.getAttributeValue("path"));
                    DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode(eldbpath.getAttributeValue("path"));
                    trunkNode.add(leafnode);
                }
            }
            
        }catch(Exception e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"JFManagementClient.JFManagementClient",e.getMessage());
            JOptionPane.showMessageDialog(rootPane,e.getMessage()+"无法进行远程连接", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void jmiAddLinkActionPerformed(java.awt.event.ActionEvent evt) { 
        if(jdcipp==null){
            jdcipp=new JDCreateIPPort(this,true);
        }else{
            jdcipp.setValue();
        }
        int w=jdcipp.getWidth();
        int h=jdcipp.getHeight();
        jdcipp.setLocation( (width - w) / 2, (height - h) / 2);
        jdcipp.setVisible(true);
        if(!jdcipp.getIPPortkey().equals("")){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)jtLinks.getModel().getRoot();
            ((DefaultTreeModel) jtLinks.getModel()).insertNodeInto(new DefaultMutableTreeNode(jdcipp.getIPPortkey()), node, node.getChildCount());
        }
        //URLDic.printURLs();
    }
    
    private void jmiDelLinkActionPerformed(java.awt.event.ActionEvent evt) { 
        DefaultMutableTreeNode trunkNode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (trunkNode == null)
            return;
        if(JOptionPane.showConfirmDialog(rootPane, "是否确定删除"+trunkNode.getUserObject().toString()+"?", "提示", JOptionPane.YES_NO_OPTION)==0){
            String ipport=trunkNode.getUserObject().toString();
            IPPort ipp=IPPortDic.getIPPort(ipport);
            //删除节点
            XMLClientController.delIPPort(ipp.getIp(),ipp.getPort(),PublicProperty.getMysqlClientIndex());
            IPPortDic.removeIPPort(ipport);
            MongoDic.removeMongo(ipport);
            ((DefaultTreeModel) jtLinks.getModel()).removeNodeFromParent(trunkNode);
        }
        //URLDic.printURLs();
    }
    static String node="";
    private void jmiLogActionPerformed(java.awt.event.ActionEvent evt){
        DefaultMutableTreeNode trunkNode = (DefaultMutableTreeNode) jtLinks.getLastSelectedPathComponent();
        if (trunkNode == null)
            return;
        //System.out.println(trunkNode.getUserObject().toString());
        
        if(MongoDic.getMongo(trunkNode.getUserObject().toString())!=null){
            if(this.jdl==null||!node.equals(trunkNode.getUserObject().toString())){
                node=trunkNode.getUserObject().toString();
                this.jdl=new JDLog(this,true,MongoDic.getMongo(trunkNode.getUserObject().toString()));
            }
            int w=jdl.getWidth();
            int h=jdl.getHeight();
            jdl.setLocation( (width - w) / 2, (height - h) / 2);
            jdl.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(rootPane,trunkNode.getUserObject().toString()+"未启用日志查看功能", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // </editor-fold>
}
