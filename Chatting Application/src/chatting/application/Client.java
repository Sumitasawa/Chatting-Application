
package chatting.application;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Client implements ActionListener {
        JTextField t1;
         static JPanel p2;
        static Box vertical=Box.createVerticalBox();
       static DataOutputStream dout; 
       static JFrame f=new JFrame();
        
    Client(){
         f.setLayout(null);
         JPanel p1=new JPanel();
         p1.setBackground(new Color(7,94,84));
         p1.setBounds(0,0,450,70);
          f.add(p1);
         
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
         Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel back=new JLabel(i3);
         back.setBounds(5, 20, 25, 25);
         p1.setLayout(null);
         p1.add(back);
         
         back.addMouseListener(new MouseAdapter(){
             public void mouseClicked(MouseEvent ae){
             System.exit(0);}
         } );
     
         ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
         Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
         ImageIcon i6=new ImageIcon(i5);
         JLabel profile=new JLabel(i6);
         profile.setBounds(40, 10, 50, 50);
         p1.setLayout(null);
         p1.add(profile);
  
         ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
         Image i8=i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i9=new ImageIcon(i8);
         JLabel video=new JLabel(i9);
         video.setBounds(300, 20, 25, 25);
         p1.setLayout(null);
         p1.add(video);
         
         
         ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
         Image i11=i10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i12=new ImageIcon(i11);
         JLabel phone=new JLabel(i12);
         phone.setBounds(350, 20, 25, 25);
         p1.setLayout(null);
         p1.add(phone);
         
         
         ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
         Image i14=i13.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i15=new ImageIcon(i14);
         JLabel moreop=new JLabel(i15);
         moreop.setBounds(400, 20, 25, 25);
         p1.setLayout(null);
         p1.add(moreop);
         
         JLabel name=new JLabel("Nikhil");
         name.setBounds(120, 15, 100, 10);
         name.setForeground(Color.WHITE);
         name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
         p1.add(name);
         
         JLabel status=new JLabel("Active Now");
         status.setBounds(120, 30, 100, 10);
         status.setForeground(Color.WHITE);
         status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
         p1.add(status);
         
         
          p2=new JPanel();
         p2.setBounds(5,75,440,580);
          f.add(p2);
          
         t1=new JTextField();
        t1.setBounds(5,665,310,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
         f.add(t1);
        
        
        JButton send=new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
         f.add(send);
        
         f.setSize(450,700);
         f.setLocation(1000,50);
         f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        
         f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
   try{
        String out=t1.getText();
 
    JPanel p3=formatLabel(out);

    p2.setLayout( new BorderLayout());
    JPanel right=new JPanel(new BorderLayout());
    right.add(p3, BorderLayout.LINE_END);
    vertical.add(right);
    vertical.add(Box.createVerticalStrut(15));
    p2.add(vertical,BorderLayout.PAGE_START);
    dout.writeUTF(out);
    t1.setText("");
     f.repaint();
     f.invalidate();
     f.validate();
   }catch(Exception e){
   e.printStackTrace();
   }
    }
    public static JPanel formatLabel(String out){
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    JLabel output=new JLabel("<html><p style=\"width:150 px\">"+out+"</p></html>");
    output.setFont(new Font("Tahom",Font.PLAIN,16));
    output.setBackground(new Color(37,211,102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15,15,15,50));
    panel.add(output);
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
    
    JLabel time=new JLabel("12:00");
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    return panel;
    }
    public static void main(String []args){
    new Client();
    try{
        Socket s=new Socket("127.0.0.1",6001);
        DataInputStream din=new DataInputStream(s.getInputStream());
       dout=new DataOutputStream(s.getOutputStream());
       
       while(true){
           p2.setLayout(new BorderLayout());
               String msg=din.readUTF();
               JPanel panel=formatLabel(msg);
               
               JPanel left=new JPanel(new BorderLayout());
               left.add(panel,BorderLayout.LINE_START);
               vertical.add(left);
               vertical.add(Box.createVerticalStrut(15));
               p2.add(vertical,BorderLayout.PAGE_START);
               f.validate();
           }
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    }

