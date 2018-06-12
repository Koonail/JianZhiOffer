package com.koonail.arithmetics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LianLianKan implements ActionListener
{
    JFrame mainFrame; //主面板
    Container thisContainer;
    JPanel centerPanel,southPanel,northPanel; //子面板
    JButton diamondsButton[][] = new JButton[6][5];//游戏按钮数组
    JButton exitButton,resetButton,newlyButton; //退出，重列，重新开始按钮
    JLabel fractionLable=new JLabel("0"); //分数标签
    JButton firstButton,secondButton; //分别记录两次被选中的按钮
    int grid[][] = new int[8][7];//储存游戏按钮位置
    static boolean pressInformation=false; //判断是否有按钮被选中
    int x0=0,y0=0,x=0,y=0,fristMsg=0,secondMsg=0,validateLV; //游戏按钮的位置坐标
    int i,j,k,n;//消除方法控制
    public void init(){
        mainFrame=new JFrame("JKJ连连看");
        thisContainer = mainFrame.getContentPane();
        thisContainer.setLayout(new BorderLayout());
        centerPanel=new JPanel();
        southPanel=new JPanel();
        northPanel=new JPanel();
        thisContainer.add(centerPanel,"Center");
        thisContainer.add(southPanel,"South");
        thisContainer.add(northPanel,"North");
        centerPanel.setLayout(new GridLayout(6,5));
        for(int cols = 0;cols < 6;cols++){
            for(int rows = 0;rows < 5;rows++ ){
                diamondsButton[cols][rows]=new JButton(String.valueOf(grid[cols+1][rows+1]));
                diamondsButton[cols][rows].addActionListener(this);
                centerPanel.add(diamondsButton[cols][rows]);
            }
        }
        exitButton=new JButton("退出");
        exitButton.addActionListener(this);
        resetButton=new JButton("重列");
        resetButton.addActionListener(this);
        newlyButton=new JButton("再来一局");
        newlyButton.addActionListener(this);
        southPanel.add(exitButton);
        southPanel.add(resetButton);
        southPanel.add(newlyButton);
        fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText())));
        northPanel.add(fractionLable);
        mainFrame.setBounds(280,100,500,450);
        mainFrame.setVisible(true);
    }
    public void randomBuild() {
        int randoms,cols,rows;
        for(int twins=1;twins<=15;twins++) {
            randoms=(int)(Math.random()*25+1);
            for(int alike=1;alike<=2;alike++) {
                cols=(int)(Math.random()*6+1);
                rows=(int)(Math.random()*5+1);
                while(grid[cols][rows]!=0) {
                    cols=(int)(Math.random()*6+1);
                    rows=(int)(Math.random()*5+1);
                }
                this.grid[cols][rows]=randoms;
            }
        }
    }
    public void fraction(){
        fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText())+100));
    }
    public void reload() {
        int save[] = new int[30];
        int n=0,cols,rows;
        int grid[][]= new int[8][7];
        for(int i=0;i<=6;i++) {
            for(int j=0;j<=5;j++) {
                if(this.grid[i][j]!=0) {
                    save[n]=this.grid[i][j];
                    n++;
                }
            }
        }
        n=n-1;
        this.grid=grid;
        while(n>=0) {
            cols=(int)(Math.random()*6+1);
            rows=(int)(Math.random()*5+1);
            while(grid[cols][rows]!=0) {
                cols=(int)(Math.random()*6+1);
                rows=(int)(Math.random()*5+1);
            }
            this.grid[cols][rows]=save[n];
            n--;
        }
        mainFrame.setVisible(false);
        pressInformation=false; //这里一定要将按钮点击信息归为初始
        init();
        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 5;j++ ){
                if(grid[i+1][j+1]==0)
                    diamondsButton[i][j].setVisible(false);
            }
        }
    }
    public void estimateEven(int placeX,int placeY,JButton bz) {
        if(pressInformation==false) {
            x=placeX;
            y=placeY;
            secondMsg=grid[x][y];
            secondButton=bz;
            pressInformation=true;
        }
        else {
            x0=x;
            y0=y;
            fristMsg=secondMsg;
            firstButton=secondButton;
            x=placeX;
            y=placeY;
            secondMsg=grid[x][y];
            secondButton=bz;
            if(fristMsg==secondMsg && secondButton!=firstButton){
                xiao();
            }
        }
    }
    public void xiao() { //相同的情况下能不能消去。仔细分析，不一条条注释
        if((x0==x &&(y0==y+1||y0==y-1)) || ((x0==x+1||x0==x-1)&&(y0==y))){ //判断是否相邻
            remove();
        }
        else{
            for (j=0;j<7;j++ ) {
                if (grid[x0][j]==0){ //判断第一个按钮同行哪个按钮为空
                    if (y>j) { //如果第二个按钮的Y坐标大于空按钮的Y坐标说明第一按钮在第二按钮左边
                        for (i=y-1;i>=j;i-- ){ //判断第二按钮左侧直到第一按钮中间有没有按钮
                            if (grid[x][i]!=0) {
                                k=0;
                                break;
                            }
                            else{ k=1; } //K=1说明通过了第一次验证
                        }
                        if (k==1) {
                            linePassOne();
                        }
                    }
                    if (y<j){ //如果第二个按钮的Y坐标小于空按钮的Y坐标说明第一按钮在第二按钮右边
                        for (i=y+1;i<=j ;i++ ){ //判断第二按钮左侧直到第一按钮中间有没有按钮
                            if (grid[x][i]!=0){
                                k=0;
                                break;
                            }
                            else { k=1; }
                        }
                        if (k==1){
                            linePassOne();
                        }
                    }
                    if (y==j ) {
                        linePassOne();
                    }
                }
                if (k==2) {
                    if (x0==x) {
                        remove();
                    }
                    if (x0<x) {
                        for (n=x0;n<=x-1;n++ ) {
                            if (grid[n][j]!=0) {
                                k=0;
                                break;
                            }
                            if(grid[n][j]==0 && n==x-1) {
                                remove();
                            }
                        }
                    }
                    if (x0>x) {
                        for (n=x0;n>=x+1 ;n-- ) {
                            if (grid[n][j]!=0) {
                                k=0;
                                break;
                            }
                            if(grid[n][j]==0 && n==x+1) {
                                remove();
                            }
                        }
                    }
                }
            }
            for (i=0;i<8;i++ ) { //列
                if (grid[i][y0]==0) {
                    if (x>i) {
                        for (j=x-1;j>=i ;j-- ) {
                            if (grid[j][y]!=0) {
                                k=0;
                                break;
                            }
                            else { k=1; }
                        }
                        if (k==1) {
                            rowPassOne();
                        }
                    }
                    if (x<i) {
                        for (j=x+1;j<=i;j++ ) {
                            if (grid[j][y]!=0) {
                                k=0;
                                break;
                            }
                            else { k=1; }
                        }
                        if (k==1) {
                            rowPassOne();
                        }
                    }
                    if (x==i) {
                        rowPassOne();
                    }
                }
                if (k==2){
                    if (y0==y) {
                        remove();
                    }
                    if (y0<y) {
                        for (n=y0;n<=y-1 ;n++ ) {
                            if (grid[i][n]!=0) {
                                k=0;
                                break;
                            }
                            if(grid[i][n]==0 && n==y-1) {
                                remove();
                            }
                        }
                    }
                    if (y0>y) {
                        for (n=y0;n>=y+1 ;n--) {
                            if (grid[i][n]!=0) {
                                k=0;
                                break;
                            }
                            if(grid[i][n]==0 && n==y+1) {
                                remove();
                            }
                        }
                    }
                }
            }
        }
    }
    public void linePassOne(){
        if (y0>j){ //第一按钮同行空按钮在左边
            for (i=y0-1;i>=j ;i-- ){ //判断第一按钮同左侧空按钮之间有没按钮
                if (grid[x0][i]!=0) {
                    k=0;
                    break;
                }
                else { k=2; } //K=2说明通过了第二次验证
            }
        }
        if (y0<j){ //第一按钮同行空按钮在与第二按钮之间
            for (i=y0+1;i<=j ;i++){
                if (grid[x0][i]!=0) {
                    k=0;
                    break;
                }
                else{ k=2; }
            }
        }
    }
    public void rowPassOne(){
        if (x0>i) {
            for (j=x0-1;j>=i ;j-- ) {
                if (grid[j][y0]!=0) {
                    k=0;
                    break;
                }
                else { k=2; }
            }
        }
        if (x0<i) {
            for (j=x0+1;j<=i ;j++ ) {
                if (grid[j][y0]!=0) {
                    k=0;
                    break;
                }
                else { k=2; }
            }
        }
    }
    public void remove(){
        firstButton.setVisible(false);
        secondButton.setVisible(false);
        fraction();
        pressInformation=false;
        k=0;
        grid[x0][y0]=0;
        grid[x][y]=0;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newlyButton){
            int grid[][] = new int[8][7];
            this.grid = grid;
            randomBuild();
            mainFrame.setVisible(false);
            pressInformation=false;
            init();
        }
        if(e.getSource()==exitButton)
            System.exit(0);
        if(e.getSource()==resetButton)
            reload();
        for(int cols = 0;cols < 6;cols++){
            for(int rows = 0;rows < 5;rows++ ){
                if(e.getSource()==diamondsButton[cols][rows])
                    estimateEven(cols+1,rows+1,diamondsButton[cols][rows]);
            }
        }
    }
    public static void main(String[] args) {
        LianLianKan llk = new LianLianKan();
        llk.randomBuild();
        llk.init();
    }
} 