package ntou.x.calendar;

import java.io.*;
import java.util.*;
import java.lang.Thread;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
 
public class Cal extends javax.swing.JFrame
{
  private JDesktopPane jDesktopPane1;
  private JDesktopPane jDesktopPane2;
  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel2;
  private JLabel jLabel4;
  private JLabel jLabel3;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JComboBox jComboBox1;
  private JTextField jTextField1;
  private JButton jButton3;
  private JTextArea jTextArea1;
  private JLabel jLabel1;
 
  /*public static void main(String[] args) throws IOException
  {
    Cal inst = new Cal();
    inst.setVisible(true);
  }*/
  
  public Cal()
  {
    super();
    initGUI();
    this.setVisible(true);
  }
  
  public int[] getdate()
  {
    int[] date_array = new int[3];
    Calendar ca = new GregorianCalendar();  
    date_array[0] = ca.get(Calendar.YEAR);//年
    date_array[1] = ca.get(Calendar.MONTH)+1;//月
    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//日
    return date_array;
  }
 
  public void new_btn()
  {
    jTextArea1.setText("");
    int year,month;
    year = Integer.parseInt(jLabel5.getText().substring(0,4));
    month = Integer.parseInt(jLabel5.getText().substring(7,9));
    date_btn_create(year,month);
  }
  
  public void date_btn_create(int year,int month)
  {
    int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
    String syear,smonth,sday,filename;
    syear = String.valueOf(year);
    smonth = String.valueOf(month);
    if (smonth.length() == 1)
      smonth = "0"+smonth;
    
    jDesktopPane1.remove(jDesktopPane2);
    jDesktopPane2 = new JDesktopPane();
    jDesktopPane1.add(jDesktopPane2);
    jDesktopPane2.setBounds(0, 30, 252, 196);
    
    jDesktopPane2.setBackground(new java.awt.Color(148,205,176));
    switch(month)
    {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        date_acc = 31;
        break;
        
      case 4:
      case 6:
      case 9:
      case 11:
        date_acc = 30;
        break;
        
      case 2:
        if (leap_year(year))
          date_acc = 29;
        else
          date_acc = 28;
    }
    
    week_of_day = dow(year,month,1);
    
    switch(week_of_day)
    {
      case 0:
        week_add = 0;
        break;
      case 1:
        week_add = 34;
        break;
      case 2:
        week_add = 68;
        break;
      case 3:
        week_add = 102;
        break;
      case 4:
        week_add = 136;
        break;
      case 5:
        week_add = 170;
        break;
      case 6:
        week_add = 204;
        break;
    }
    JButton btn[] = new JButton[date_acc];
    for (int i=0;i<date_acc;i++)
    {
      btn[i] = new JButton();
      jDesktopPane2.add(btn[i]);
      btn[i].setText(String.valueOf(i+1));
      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
      {
        x=0;
        x_add=0;
        y++;
        y_add+=10;
        week_add=0;
      }
      //下面設定按鈕大小及加值(X為起始10+第幾個按鈕*橫向寬度25+間隔+當月第一天星期幾加值)
      btn[i].setBounds(10+x*25+x_add+week_add, y*20+y_add, 25, 20);//(Y為第幾個按鈕*高度20+換行加值)按鈕寬為25高為20
      btn[i].setFont(new java.awt.Font("Arial",1,12));
      btn[i].setBorder(BorderFactory.createTitledBorder(""));//設定按鈕文字不自動調整大小
      int[] now = new int[3];
      now = getdate();//取得當天日期
      if (year == now[0] && month == now[1] && i+1 == now[2])
        btn[i].setBackground(new java.awt.Color(233,238,164));//若為當天則設定按鈕為黃色
      else
        btn[i].setBackground(new java.awt.Color(148,205,176));//若不是當天則設定按鈕為綠色
        
      sday = String.valueOf(i+1);
      filename = syear+smonth+sday;//記事檔案名稱(年+月+日.txt)
      File file=new File(filename+".txt");//建立檔案物件
      if (file.exists())//若當天有記事檔案則設定按鈕字體顏色為藍色
        btn[i].setForeground(new java.awt.Color(0,0,255));
      
      btn[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnActionPerformed(evt);
        }
      });
      x++;
      x_add+=9;
    }
  }
  
  public boolean leap_year(int year)
  {
    boolean leap_year;
    if (year%4 == 0)
    {
      if (year%100 == 0)
      {
        if (year%400 == 0)
          leap_year = true;
        else
          leap_year = false;
      }
      else
        leap_year = true;
    }
    else
      leap_year = false;
    return leap_year;
  }
  
  public int dow(int y,int m,int d)//判斷星期幾
  {
    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//天文星體運行值
    int w;
    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//閏年設定
    if( ((y%4)==0) && (m<3) )//公式
    {
      w--;
      if((y%100)==0) w++;
      if((y%400)==0) w--;
    }
    return (w+d)%7;//回傳星期幾(0為星期日，1為星期一以此類推)
  }
  
  private void initGUI()//產生視覺化物件函數(Graph User Interface，圖形化使用者介面)
  {
    try
    {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//設定外框視窗主要功能列為標準(縮到最小，放到最大，關閉)
      {
        this.setTitle("行事曆");//設定視窗抬頭 
        jDesktopPane1 = new JDesktopPane();//建立一桌面
        getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(464, 267));//設定桌面大小
        jDesktopPane1.setBackground(new java.awt.Color(148,205,176));//設定桌面顏色
        {//建立桌面2開始(放日期按鈕的桌面)
          jDesktopPane2 = new JDesktopPane();
          jDesktopPane1.add(jDesktopPane2);
          jDesktopPane2.setBounds(0, 30, 252, 196);
          jDesktopPane2.setBackground(new java.awt.Color(148,205,176));
        }//建立桌面2結束
        {//建立星期日標籤開始
          jLabel1 = new JLabel();
          jDesktopPane1.add(jLabel1);
          jLabel1.setText("Sun");
          jLabel1.setBounds(14, 7, 21, 21);
          jLabel1.setFont(new java.awt.Font("Arial",0,11));
          jLabel1.setForeground(new java.awt.Color(255,0,0));
        }//建立星期日標籤結束
        {//建立記事框開始
          jTextArea1 = new JTextArea(252, 35);
          jDesktopPane1.add(jTextArea1);
          jTextArea1.setText("");//預設內容清空
          jTextArea1.setBounds(252, 42, 175, 182);//設定大小
          jTextArea1.setFont(new java.awt.Font("新細明體",0,11));//設定字體樣式大小
          jTextArea1.setLineWrap(true);//文字過長自動換行
          jTextArea1.setWrapStyleWord(true);//文字過長自動換行
          JScrollPane scrollPane = new JScrollPane(jTextArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          jDesktopPane1.add(scrollPane);//上面為建立scorollpane(視窗旁邊的移動棒);將記事框建立在scrollpane上
          scrollPane.setBounds(252, 42, 175, 182);//設定大小及位置
        }//建立記事框結束
        {//建立清除按鈕開始
          jButton1 = new JButton();
          jDesktopPane1.add(jButton1);
          jButton1.setText("清除");
          jButton1.setBounds(252, 231, 63, 21);
          jButton1.setFont(new java.awt.Font("新細明體",0,11));
          jButton1.setBorder(BorderFactory.createTitledBorder(""));
          jButton1.addActionListener(new ActionListener() {//設定清除按鈕監聽函數
            public void actionPerformed(ActionEvent evt) {
              jButton1ActionPerformed(evt);
            }
          });
        }//建立清除按鈕結束
        {//建立儲存按鈕開始
          jButton2 = new JButton();
          jDesktopPane1.add(jButton2);
          jButton2.setText("儲存");
          jButton2.setBounds(364, 231, 63, 21);
          jButton2.setFont(new java.awt.Font("新細明體",0,11));
          jButton2.setBorder(BorderFactory.createTitledBorder(""));
          jButton2.addActionListener(new ActionListener() {//設定儲存按鈕監聽函數
            public void actionPerformed(ActionEvent evt) {
              jButton2ActionPerformed(evt);
            }
          });
        }//建立儲存按鈕結束
        {//建立星期一~星期六標籤開始
          jLabel2 = new JLabel();
          jDesktopPane1.add(jLabel2);
          jLabel2.setText("Mon    Tue     Wed     Thu      Fri      Sat");
          jLabel2.setBounds(49, 7, 189, 21);
          jLabel2.setFont(new java.awt.Font("Arial",0,11));
        }//建立星期一~星期六標籤結束
        {//建立查詢年份輸入框開始
          jTextField1 = new JTextField();
          jDesktopPane1.add(jTextField1);
          jTextField1.setText("");
          jTextField1.setBounds(14, 231, 63, 21);
          jTextField1.setFont(new java.awt.Font("Arial",0,12));
        }//建立查詢年份輸入框結束
        {//建立狀態標籤開始
          jLabel6 = new JLabel();
          jDesktopPane1.add(jLabel6);
          jLabel6.setText("TEST");
          jLabel6.setBounds(357, 7, 70, 21);
          jLabel6.setFont(new java.awt.Font("新細明體",0,11));
          jLabel6.setForeground(new java.awt.Color(0,0,255));
        }//建立狀態標籤開始
        {//建立隱藏日期按鈕暫存標籤開始
          jLabel7 = new JLabel();
          jDesktopPane1.add(jLabel7);
          jLabel7.setText("");
          jLabel7.setBounds(0, 0, 0, 0);//設定大小為0
        }//建立隱藏日期按鈕暫存標籤結束
        {//建立目前選擇日期標籤開始
          jLabel8 = new JLabel();
          jDesktopPane1.add(jLabel8);
          jLabel8.setText("目前無選擇日期");
          jLabel8.setBounds(252, 28, 175, 14);
          jLabel8.setFont(new java.awt.Font("新細明體",0,11));
        }//建立目前選擇日期標籤結束
        {//建立月份下拉選單開始
          ComboBoxModel jComboBox1Model = new DefaultComboBoxModel
          (new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });//內容設定1~12
          jComboBox1 = new JComboBox();
          jDesktopPane1.add(jComboBox1);
          jComboBox1.setModel(jComboBox1Model);
          jComboBox1.setBounds(105, 231, 40, 21);
          jComboBox1.setFont(new java.awt.Font("Arial",0,11));
        }//建立月份下拉選單結束
        {//建立"月"標籤開始
          jLabel3 = new JLabel();
          jDesktopPane1.add(jLabel3);
          jLabel3.setText("月");
          jLabel3.setBounds(155, 231, 14, 21);
          jLabel3.setFont(new java.awt.Font("新細明體",0,12));
        }//建立"月"標籤結束
        {//建立"年"標籤開始
          jLabel4 = new JLabel();
          jDesktopPane1.add(jLabel4);
          jLabel4.setText("年");
          jLabel4.setFont(new java.awt.Font("新細明體", 0, 12));
          jLabel4.setBounds(84, 231, 14, 21);
        }//建立"年"標籤結束
        {//建立查詢按鈕開始
          jButton3 = new JButton();
          jDesktopPane1.add(jButton3);
          jButton3.setText("查詢");
          jButton3.setBounds(180, 231, 35, 21);
          jButton3.setBorder(BorderFactory.createTitledBorder(""));
          jButton3.setFont(new java.awt.Font("新細明體",0,11));
          jButton3.addActionListener(new ActionListener() {//查詢按鈕監聽函數
            public void actionPerformed(ActionEvent evt) {
              jButton3ActionPerformed(evt);
            }
          });
        }
        {
          int[] now = new int[3];
          now = getdate();
          String year5,smonth;
          year5 = String.valueOf(now[0]);
          smonth = String.valueOf(now[1]);
          if (smonth.length() == 1)
            smonth = "0"+smonth;
          
          jLabel5 = new JLabel();
          jDesktopPane1.add(jLabel5);
          jLabel5.setText(year5+" 年 "+smonth+" 月");
          jLabel5.setBounds(252, 7, 120, 21);
          jLabel5.setForeground(new java.awt.Color(255,255,255));
        }
        {
          int[] now = new int[3];
          now = getdate();
          date_btn_create(now[0],now[1]);
        }
      }
      pack();
      this.setSize(444, 296);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    jTextArea1.setText("");
    String year,month,day,filename,insert_str;
    year = jLabel5.getText().substring(0,4);
    month = jLabel5.getText().substring(7,9);
    day = jLabel7.getText();
    filename = year+month+day;
    File file=new File(filename+".txt");
    file.delete();
    new_btn();
    jLabel6.setText("行事曆已清除");
    jLabel7.setText("");
    jLabel8.setText("目前無選擇日期");
  }
  
  private void jButton2ActionPerformed(ActionEvent evt)
  {
    String year,month,day,filename,insert_str;
    year = jLabel5.getText().substring(0,4);
    month = jLabel5.getText().substring(7,9);
    day = jLabel7.getText();
    filename = year+month+day;
    insert_str = jTextArea1.getText();
    if (insert_str.length() != 0 && day.length() != 0)
    {
      try
      {
        FileWriter fw=new FileWriter(filename+".txt");
        BufferedWriter bfw=new BufferedWriter(fw);
        bfw.write(insert_str); 
        bfw.flush();
        fw.close();
        jLabel6.setText("行事曆已記錄");
        jLabel7.setText("");
        jLabel8.setText("目前無選擇日期");
        new_btn();
      }catch(IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (day.length() == 0)
        jLabel6.setText("日期未選擇");
      else
        jLabel6.setText("行事曆無內容");
    }
  }
  
  private void jButton3ActionPerformed(ActionEvent evt)
  {
    String syear,smonth;
    try
    {
      jTextArea1.setText("");
      jLabel6.setText("查詢日期");
      syear = jTextField1.getText();
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      if (syear == "" || Integer.parseInt(syear)<1582)
      {
        int[] now = new int[3];
        now = getdate();
        syear = String.valueOf(now[0]);
        //jLabel6.setText("請選1582以上");
      }
      jLabel5.setText(syear+" 年 "+smonth+" 月");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("目前無選擇日期");
    }catch(NumberFormatException e)
    {
      int[] now = new int[3];
      now = getdate();
      syear = String.valueOf(now[0]);
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      jLabel5.setText(syear+" 年 "+smonth+" 月");
      //jLabel6.setText("請選1582以上");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("目前無選擇日期");
    }
  }
  
  private void btnActionPerformed(ActionEvent evt)
  {
    jTextArea1.setText("");
    String year,month,btn_date,filename,read_str;
    year = jLabel5.getText().substring(0,4);
    month = jLabel5.getText().substring(7,9);
    btn_date = evt.getActionCommand();
    filename = year+month+btn_date;
    jLabel7.setText(btn_date);
    try
    {
      FileReader fr = new FileReader(filename+".txt");
      BufferedReader bfr = new BufferedReader(fr);
      boolean flag=false;//旗標
      while((read_str = bfr.readLine())!=null) 
      {
        if (flag)
          jTextArea1.append("\n");
        jTextArea1.append(read_str);
        flag=true;
        
      }
      jLabel6.setText("當天記事");
      jLabel8.setText("已選擇"+year+"年"+month+"月"+btn_date+"日");
      fr.close();
    }catch(FileNotFoundException e)
    {
      jLabel6.setText("當日無行事曆");
      jLabel8.setText("已選擇"+year+"年"+month+"月"+btn_date+"日");
    }catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}