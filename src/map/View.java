/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import obs.CoinPile;
import tank.TankServer;

/**
 *
 * @author Supun
 */
public class View extends javax.swing.JFrame {
     
    
    /**
     * Creates new form View
     */
   JLabel tank_lable;
   
    public View(String x[][],int max) {
      initComponents();
        
        JLabel grids[][]={{grid_1,grid_2,grid_3,grid_4,grid_5,grid_6,grid_7,grid_8,grid_9,grid_10},{grid_11,grid_12,
            grid_13,grid_14,grid_15,grid_16,grid_17,grid_18,grid_19,grid_20},{grid_21,grid_22,grid_23,grid_24,grid_25,grid_26,grid_27,grid_28,grid_29,grid_30},
            {grid_31,grid_32,grid_33,grid_34,grid_35,grid_36,grid_37,grid_38,grid_39,grid_40},{grid_41,grid_42,grid_43,grid_44,grid_45,grid_46,grid_47,grid_48,grid_49,grid_50}
        ,{grid_51,grid_52,grid_53,grid_54,grid_55,grid_56,grid_57,grid_58,grid_59,grid_60},{grid_61,grid_62,grid_63,grid_64,grid_65,grid_66,grid_67,grid_68,grid_69,grid_70}
        ,{grid_71,grid_72,grid_73,grid_74,grid_75,grid_76,grid_77,grid_78,grid_79,grid_80},{grid_81,grid_82,grid_83,grid_84,grid_85,grid_86,grid_87,grid_88,grid_89,grid_90}
        ,{grid_91,grid_92,grid_93,grid_94,grid_95,grid_96,grid_97,grid_98,grid_99,grid_100}};  
        
        for(int i=0;i<max;i++){
            for(int j=0;j<max;j++){
            if(x[i][j].equals("1")){
        grids[i][j].setIcon(new ImageIcon(getClass().getResource("/res/brick.jpg")));
            }else if(x[i][j].equals("2")){
                grids[i][j].setIcon(new ImageIcon(getClass().getResource("/res/stone2.jpg")));
            }else if(x[i][j].equals("3")){
                grids[i][j].setIcon(new ImageIcon(getClass().getResource("/res/water.jpg")));
            }

           

            else if(x[i][j].equals("P0")){
                grids[i][j].setIcon(new ImageIcon(getClass().getResource("/res/tank_green.jpg")));
            }
            else if(x[i][j].equals("P1")){
                grids[i][j].setIcon(new ImageIcon(getClass().getResource("/res/tank_grey.jpg")));
            }

            
            }
        }
    }
    
    int initialX=0,iniitialY=0;
    public void updateTank(int x,int y,int dires){
        
        JLabel grids[][]={{grid_1,grid_2,grid_3,grid_4,grid_5,grid_6,grid_7,grid_8,grid_9,grid_10},{grid_11,grid_12,
            grid_13,grid_14,grid_15,grid_16,grid_17,grid_18,grid_19,grid_20},{grid_21,grid_22,grid_23,grid_24,grid_25,grid_26,grid_27,grid_28,grid_29,grid_30},
            {grid_31,grid_32,grid_33,grid_34,grid_35,grid_36,grid_37,grid_38,grid_39,grid_40},{grid_41,grid_42,grid_43,grid_44,grid_45,grid_46,grid_47,grid_48,grid_49,grid_50}
        ,{grid_51,grid_52,grid_53,grid_54,grid_55,grid_56,grid_57,grid_58,grid_59,grid_60},{grid_61,grid_62,grid_63,grid_64,grid_65,grid_66,grid_67,grid_68,grid_69,grid_70}
        ,{grid_71,grid_72,grid_73,grid_74,grid_75,grid_76,grid_77,grid_78,grid_79,grid_80},{grid_81,grid_82,grid_83,grid_84,grid_85,grid_86,grid_87,grid_88,grid_89,grid_90}
        ,{grid_91,grid_92,grid_93,grid_94,grid_95,grid_96,grid_97,grid_98,grid_99,grid_100}};  
      
        if(initialX!=x ||iniitialY!=y){
            grids[iniitialY][initialX].setIcon(new ImageIcon(getClass().getResource("/res/prt.jpg")));
            initialX=x; iniitialY=y;
        }
        else if(dires==0){
            grids[y][x].setIcon(new ImageIcon(getClass().getResource("/res/tank_red.jpg")));
        }else if(dires==1){
            grids[y][x].setIcon(new ImageIcon(getClass().getResource("/res/tank_red_east.jpg")));
        }else if(dires==2){
            grids[y][x].setIcon(new ImageIcon(getClass().getResource("/res/tank_red_south.jpg")));
        }else if(dires==3){
            grids[y][x].setIcon(new ImageIcon(getClass().getResource("/res/tank_red_west.jpg")));
        }
        tank_lable=grids[y][x];
        
        
    }
    
    public void updateCoin(int x,int y,String status){
         JLabel grids[][]={{grid_1,grid_2,grid_3,grid_4,grid_5,grid_6,grid_7,grid_8,grid_9,grid_10},{grid_11,grid_12,
            grid_13,grid_14,grid_15,grid_16,grid_17,grid_18,grid_19,grid_20},{grid_21,grid_22,grid_23,grid_24,grid_25,grid_26,grid_27,grid_28,grid_29,grid_30},
            {grid_31,grid_32,grid_33,grid_34,grid_35,grid_36,grid_37,grid_38,grid_39,grid_40},{grid_41,grid_42,grid_43,grid_44,grid_45,grid_46,grid_47,grid_48,grid_49,grid_50}
        ,{grid_51,grid_52,grid_53,grid_54,grid_55,grid_56,grid_57,grid_58,grid_59,grid_60},{grid_61,grid_62,grid_63,grid_64,grid_65,grid_66,grid_67,grid_68,grid_69,grid_70}
        ,{grid_71,grid_72,grid_73,grid_74,grid_75,grid_76,grid_77,grid_78,grid_79,grid_80},{grid_81,grid_82,grid_83,grid_84,grid_85,grid_86,grid_87,grid_88,grid_89,grid_90}
        ,{grid_91,grid_92,grid_93,grid_94,grid_95,grid_96,grid_97,grid_98,grid_99,grid_100}};  
        if(status.equals("NEW")){
            grids[x][y].setIcon(new ImageIcon(getClass().getResource("/res/coin.jpg")));
        }else if(status.equals("REMOVE")){
            grids[x][y].setIcon(new ImageIcon(getClass().getResource("/res/prt.jpg")));
        }
    }
    public void updateLifePack(int x,int y,String status){
         JLabel grids[][]={{grid_1,grid_2,grid_3,grid_4,grid_5,grid_6,grid_7,grid_8,grid_9,grid_10},{grid_11,grid_12,
            grid_13,grid_14,grid_15,grid_16,grid_17,grid_18,grid_19,grid_20},{grid_21,grid_22,grid_23,grid_24,grid_25,grid_26,grid_27,grid_28,grid_29,grid_30},
            {grid_31,grid_32,grid_33,grid_34,grid_35,grid_36,grid_37,grid_38,grid_39,grid_40},{grid_41,grid_42,grid_43,grid_44,grid_45,grid_46,grid_47,grid_48,grid_49,grid_50}
        ,{grid_51,grid_52,grid_53,grid_54,grid_55,grid_56,grid_57,grid_58,grid_59,grid_60},{grid_61,grid_62,grid_63,grid_64,grid_65,grid_66,grid_67,grid_68,grid_69,grid_70}
        ,{grid_71,grid_72,grid_73,grid_74,grid_75,grid_76,grid_77,grid_78,grid_79,grid_80},{grid_81,grid_82,grid_83,grid_84,grid_85,grid_86,grid_87,grid_88,grid_89,grid_90}
        ,{grid_91,grid_92,grid_93,grid_94,grid_95,grid_96,grid_97,grid_98,grid_99,grid_100}};  
        if(status.equals("NEW")){
            grids[x][y].setIcon(new ImageIcon(getClass().getResource("/res/health.jpg")));
        }else if(status.equals("REMOVE")){
            grids[x][y].setIcon(new ImageIcon(getClass().getResource("/res/prt.jpg")));
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        grid_2 = new javax.swing.JLabel();
        grid_3 = new javax.swing.JLabel();
        grid_1 = new javax.swing.JLabel();
        grid_4 = new javax.swing.JLabel();
        grid_5 = new javax.swing.JLabel();
        grid_6 = new javax.swing.JLabel();
        grid_7 = new javax.swing.JLabel();
        grid_8 = new javax.swing.JLabel();
        grid_9 = new javax.swing.JLabel();
        grid_10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        grid_12 = new javax.swing.JLabel();
        grid_13 = new javax.swing.JLabel();
        grid_11 = new javax.swing.JLabel();
        grid_14 = new javax.swing.JLabel();
        grid_15 = new javax.swing.JLabel();
        grid_16 = new javax.swing.JLabel();
        grid_17 = new javax.swing.JLabel();
        grid_18 = new javax.swing.JLabel();
        grid_19 = new javax.swing.JLabel();
        grid_20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        grid_22 = new javax.swing.JLabel();
        grid_23 = new javax.swing.JLabel();
        grid_21 = new javax.swing.JLabel();
        grid_24 = new javax.swing.JLabel();
        grid_25 = new javax.swing.JLabel();
        grid_26 = new javax.swing.JLabel();
        grid_27 = new javax.swing.JLabel();
        grid_28 = new javax.swing.JLabel();
        grid_29 = new javax.swing.JLabel();
        grid_30 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        grid_32 = new javax.swing.JLabel();
        grid_33 = new javax.swing.JLabel();
        grid_31 = new javax.swing.JLabel();
        grid_34 = new javax.swing.JLabel();
        grid_35 = new javax.swing.JLabel();
        grid_36 = new javax.swing.JLabel();
        grid_37 = new javax.swing.JLabel();
        grid_38 = new javax.swing.JLabel();
        grid_39 = new javax.swing.JLabel();
        grid_40 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        grid_42 = new javax.swing.JLabel();
        grid_43 = new javax.swing.JLabel();
        grid_41 = new javax.swing.JLabel();
        grid_44 = new javax.swing.JLabel();
        grid_45 = new javax.swing.JLabel();
        grid_46 = new javax.swing.JLabel();
        grid_47 = new javax.swing.JLabel();
        grid_48 = new javax.swing.JLabel();
        grid_49 = new javax.swing.JLabel();
        grid_50 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        grid_52 = new javax.swing.JLabel();
        grid_53 = new javax.swing.JLabel();
        grid_51 = new javax.swing.JLabel();
        grid_54 = new javax.swing.JLabel();
        grid_55 = new javax.swing.JLabel();
        grid_56 = new javax.swing.JLabel();
        grid_57 = new javax.swing.JLabel();
        grid_58 = new javax.swing.JLabel();
        grid_59 = new javax.swing.JLabel();
        grid_60 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        grid_62 = new javax.swing.JLabel();
        grid_63 = new javax.swing.JLabel();
        grid_61 = new javax.swing.JLabel();
        grid_64 = new javax.swing.JLabel();
        grid_65 = new javax.swing.JLabel();
        grid_66 = new javax.swing.JLabel();
        grid_67 = new javax.swing.JLabel();
        grid_68 = new javax.swing.JLabel();
        grid_69 = new javax.swing.JLabel();
        grid_70 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        grid_72 = new javax.swing.JLabel();
        grid_73 = new javax.swing.JLabel();
        grid_71 = new javax.swing.JLabel();
        grid_74 = new javax.swing.JLabel();
        grid_75 = new javax.swing.JLabel();
        grid_76 = new javax.swing.JLabel();
        grid_77 = new javax.swing.JLabel();
        grid_78 = new javax.swing.JLabel();
        grid_79 = new javax.swing.JLabel();
        grid_80 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        grid_82 = new javax.swing.JLabel();
        grid_83 = new javax.swing.JLabel();
        grid_81 = new javax.swing.JLabel();
        grid_84 = new javax.swing.JLabel();
        grid_85 = new javax.swing.JLabel();
        grid_86 = new javax.swing.JLabel();
        grid_87 = new javax.swing.JLabel();
        grid_88 = new javax.swing.JLabel();
        grid_89 = new javax.swing.JLabel();
        grid_90 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        grid_92 = new javax.swing.JLabel();
        grid_93 = new javax.swing.JLabel();
        grid_91 = new javax.swing.JLabel();
        grid_94 = new javax.swing.JLabel();
        grid_95 = new javax.swing.JLabel();
        grid_96 = new javax.swing.JLabel();
        grid_97 = new javax.swing.JLabel();
        grid_98 = new javax.swing.JLabel();
        grid_99 = new javax.swing.JLabel();
        grid_100 = new javax.swing.JLabel();
        btnUP = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        btnRight = new javax.swing.JButton();
        btnLeft = new javax.swing.JButton();
        btnShoot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Game View"));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        grid_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_2, gridBagConstraints);

        grid_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_3, gridBagConstraints);

        grid_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 0);
        jPanel2.add(grid_1, gridBagConstraints);

        grid_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_4, gridBagConstraints);

        grid_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_5, gridBagConstraints);

        grid_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_6, gridBagConstraints);

        grid_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_7, gridBagConstraints);

        grid_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_8, gridBagConstraints);

        grid_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 0);
        jPanel2.add(grid_9, gridBagConstraints);

        grid_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 11, 10);
        jPanel2.add(grid_10, gridBagConstraints);

        grid_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_40)
                    .addComponent(grid_39)
                    .addComponent(grid_38)
                    .addComponent(grid_37)
                    .addComponent(grid_36)
                    .addComponent(grid_35)
                    .addComponent(grid_34)
                    .addComponent(grid_33)
                    .addComponent(grid_32)
                    .addComponent(grid_31)))
        );

        grid_42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_60)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_60)
                    .addComponent(grid_59)
                    .addComponent(grid_58)
                    .addComponent(grid_57)
                    .addComponent(grid_56)
                    .addComponent(grid_55)
                    .addComponent(grid_54)
                    .addComponent(grid_53)
                    .addComponent(grid_52)
                    .addComponent(grid_51)))
        );

        grid_62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_80)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_80)
                    .addComponent(grid_79)
                    .addComponent(grid_78)
                    .addComponent(grid_77)
                    .addComponent(grid_76)
                    .addComponent(grid_75)
                    .addComponent(grid_74)
                    .addComponent(grid_73)
                    .addComponent(grid_72)
                    .addComponent(grid_71)))
        );

        grid_82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_90)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_90)
                    .addComponent(grid_89)
                    .addComponent(grid_88)
                    .addComponent(grid_87)
                    .addComponent(grid_86)
                    .addComponent(grid_85)
                    .addComponent(grid_84)
                    .addComponent(grid_83)
                    .addComponent(grid_82)
                    .addComponent(grid_81)))
        );

        grid_92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        grid_100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/prt.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_100)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_100)
                    .addComponent(grid_99)
                    .addComponent(grid_98)
                    .addComponent(grid_97)
                    .addComponent(grid_96)
                    .addComponent(grid_95)
                    .addComponent(grid_94)
                    .addComponent(grid_93)
                    .addComponent(grid_92)
                    .addComponent(grid_91)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_70)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_70)
                    .addComponent(grid_69)
                    .addComponent(grid_68)
                    .addComponent(grid_67)
                    .addComponent(grid_66)
                    .addComponent(grid_65)
                    .addComponent(grid_64)
                    .addComponent(grid_63)
                    .addComponent(grid_62)
                    .addComponent(grid_61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_50)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_50)
                    .addComponent(grid_49)
                    .addComponent(grid_48)
                    .addComponent(grid_47)
                    .addComponent(grid_46)
                    .addComponent(grid_45)
                    .addComponent(grid_44)
                    .addComponent(grid_43)
                    .addComponent(grid_42)
                    .addComponent(grid_41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_30)
                    .addComponent(grid_29)
                    .addComponent(grid_28)
                    .addComponent(grid_27)
                    .addComponent(grid_26)
                    .addComponent(grid_25)
                    .addComponent(grid_24)
                    .addComponent(grid_23)
                    .addComponent(grid_22)
                    .addComponent(grid_21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid_11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grid_20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grid_20)
                    .addComponent(grid_19)
                    .addComponent(grid_18)
                    .addComponent(grid_17)
                    .addComponent(grid_16)
                    .addComponent(grid_15)
                    .addComponent(grid_14)
                    .addComponent(grid_13)
                    .addComponent(grid_12)
                    .addComponent(grid_11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUP.setText("UP");
        btnUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPActionPerformed(evt);
            }
        });

        btnDown.setText("DOWN");
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });

        btnRight.setText("RIGHT");
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });

        btnLeft.setText("LEFT");
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });

        btnShoot.setText("SHOOT");
        btnShoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShootActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addComponent(btnRight))
                    .addComponent(btnShoot, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnShoot, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnUP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPActionPerformed
        // TODO add your handling code here:
        TankServer.moveTank("UP");
    }//GEN-LAST:event_btnUPActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        // TODO add your handling code here:
        TankServer.moveTank("DOWN");
    }//GEN-LAST:event_btnDownActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        // TODO add your handling code here:
        TankServer.moveTank("RIGHT");
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        // TODO add your handling code here:
        TankServer.moveTank("LEFT");
    }//GEN-LAST:event_btnLeftActionPerformed

    private void btnShootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShootActionPerformed
        // TODO add your handling code here:
        TankServer.moveTank("SHOOT");
    }//GEN-LAST:event_btnShootActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnLeft;
    private javax.swing.JButton btnRight;
    private javax.swing.JButton btnShoot;
    private javax.swing.JButton btnUP;
    private javax.swing.JLabel grid_1;
    private javax.swing.JLabel grid_10;
    private javax.swing.JLabel grid_100;
    private javax.swing.JLabel grid_11;
    private javax.swing.JLabel grid_12;
    private javax.swing.JLabel grid_13;
    private javax.swing.JLabel grid_14;
    private javax.swing.JLabel grid_15;
    private javax.swing.JLabel grid_16;
    private javax.swing.JLabel grid_17;
    private javax.swing.JLabel grid_18;
    private javax.swing.JLabel grid_19;
    private javax.swing.JLabel grid_2;
    private javax.swing.JLabel grid_20;
    private javax.swing.JLabel grid_21;
    private javax.swing.JLabel grid_22;
    private javax.swing.JLabel grid_23;
    private javax.swing.JLabel grid_24;
    private javax.swing.JLabel grid_25;
    private javax.swing.JLabel grid_26;
    private javax.swing.JLabel grid_27;
    private javax.swing.JLabel grid_28;
    private javax.swing.JLabel grid_29;
    private javax.swing.JLabel grid_3;
    private javax.swing.JLabel grid_30;
    private javax.swing.JLabel grid_31;
    private javax.swing.JLabel grid_32;
    private javax.swing.JLabel grid_33;
    private javax.swing.JLabel grid_34;
    private javax.swing.JLabel grid_35;
    private javax.swing.JLabel grid_36;
    private javax.swing.JLabel grid_37;
    private javax.swing.JLabel grid_38;
    private javax.swing.JLabel grid_39;
    private javax.swing.JLabel grid_4;
    private javax.swing.JLabel grid_40;
    private javax.swing.JLabel grid_41;
    private javax.swing.JLabel grid_42;
    private javax.swing.JLabel grid_43;
    private javax.swing.JLabel grid_44;
    private javax.swing.JLabel grid_45;
    private javax.swing.JLabel grid_46;
    private javax.swing.JLabel grid_47;
    private javax.swing.JLabel grid_48;
    private javax.swing.JLabel grid_49;
    private javax.swing.JLabel grid_5;
    private javax.swing.JLabel grid_50;
    private javax.swing.JLabel grid_51;
    private javax.swing.JLabel grid_52;
    private javax.swing.JLabel grid_53;
    private javax.swing.JLabel grid_54;
    private javax.swing.JLabel grid_55;
    private javax.swing.JLabel grid_56;
    private javax.swing.JLabel grid_57;
    private javax.swing.JLabel grid_58;
    private javax.swing.JLabel grid_59;
    private javax.swing.JLabel grid_6;
    private javax.swing.JLabel grid_60;
    private javax.swing.JLabel grid_61;
    private javax.swing.JLabel grid_62;
    private javax.swing.JLabel grid_63;
    private javax.swing.JLabel grid_64;
    private javax.swing.JLabel grid_65;
    private javax.swing.JLabel grid_66;
    private javax.swing.JLabel grid_67;
    private javax.swing.JLabel grid_68;
    private javax.swing.JLabel grid_69;
    private javax.swing.JLabel grid_7;
    private javax.swing.JLabel grid_70;
    private javax.swing.JLabel grid_71;
    private javax.swing.JLabel grid_72;
    private javax.swing.JLabel grid_73;
    private javax.swing.JLabel grid_74;
    private javax.swing.JLabel grid_75;
    private javax.swing.JLabel grid_76;
    private javax.swing.JLabel grid_77;
    private javax.swing.JLabel grid_78;
    private javax.swing.JLabel grid_79;
    private javax.swing.JLabel grid_8;
    private javax.swing.JLabel grid_80;
    private javax.swing.JLabel grid_81;
    private javax.swing.JLabel grid_82;
    private javax.swing.JLabel grid_83;
    private javax.swing.JLabel grid_84;
    private javax.swing.JLabel grid_85;
    private javax.swing.JLabel grid_86;
    private javax.swing.JLabel grid_87;
    private javax.swing.JLabel grid_88;
    private javax.swing.JLabel grid_89;
    private javax.swing.JLabel grid_9;
    private javax.swing.JLabel grid_90;
    private javax.swing.JLabel grid_91;
    private javax.swing.JLabel grid_92;
    private javax.swing.JLabel grid_93;
    private javax.swing.JLabel grid_94;
    private javax.swing.JLabel grid_95;
    private javax.swing.JLabel grid_96;
    private javax.swing.JLabel grid_97;
    private javax.swing.JLabel grid_98;
    private javax.swing.JLabel grid_99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the msgArea
     */
    
}
