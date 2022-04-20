package mypackge;

import Boundary.MealSelect;
import Boundary.Tip;
import Entity.BookingInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/*
 * 选座位界面
 */
public class ChooseSeat
{
    JFrame frame = new JFrame("Choose Seat");
    JPanel leftpanel = new JPanel();
    JPanel midpanel = new JPanel();
    JPanel rightpanel = new JPanel();
    JPanel uppanel = new JPanel();
    JPanel downpanel = new JPanel();
    JPanel mainpanel = new JPanel();
    JButton bt[] = new JButton[63];
    JButton confirm = new JButton();
    JButton cancel = new JButton();
    ImageIcon whiteicon = new ImageIcon("./pictures/Seat.jpg");
    ImageIcon redicon = new ImageIcon("./pictures/Seat(selected).jpg");
    ImageIcon greenicon = new ImageIcon("./pictures/Seat(green).jpg");

    static int a[] = new int[70];
    int cnt;				//当前选了几个座位
    int judge;
    Vector<Integer> seat = new Vector<Integer>();
    public ChooseSeat()
    {
        cnt = 0;
        readStatus();

        leftpanel.setLayout( new GridLayout(0, 3) );
        rightpanel.setLayout( new GridLayout(0,3) );
        leftpanel.setPreferredSize( new Dimension(180,150 ) );
        rightpanel.setPreferredSize( new Dimension(180, 150) );
        midpanel.setLayout( new GridLayout(0,1) );
        JLabel aisle = new JLabel("AISLE",JLabel.CENTER);		//中间部分的设置

        uppanel.setLayout(new GridLayout(0,8) );		//上面有8个
        uppanel.setPreferredSize( new Dimension(0,30) );
        JLabel uplabel[] = new JLabel[8];		//上面 ABC DEF
        for( int i = 0 ; i < 8 ; i++ )
        {
            if( i == 3 || i == 4 )
            {
                uplabel[i] = new JLabel("     ",JLabel.CENTER);
                uppanel.add(uplabel[i]);
                continue;
            }

            String s = String.valueOf( (char)('A'+ i ) );
            uplabel[i] = new JLabel(s,JLabel.CENTER);
            uplabel[i].setForeground(Color.BLUE);
            uplabel[i].setFont( new Font("宋体",Font.BOLD,20) );
            uppanel.add(uplabel[i]);
        }

        confirm.setText("Confirm");
        cancel.setText("Return");  			// 下面有两个按钮 确定  和 返回
        confirm.setForeground(Color.black);
        cancel.setForeground(Color.black);
        downpanel.add(confirm);
        downpanel.add(cancel);
        aisle.setForeground( Color.red );
        aisle.setBackground(Color.WHITE);
        midpanel.add(aisle);


        addbutton();
        mainpanel.setLayout( new BorderLayout() );
        mainpanel.add(leftpanel,BorderLayout.WEST);
        mainpanel.add(midpanel, BorderLayout.CENTER);
        mainpanel.add(rightpanel,BorderLayout.EAST);
        mainpanel.add(uppanel, BorderLayout.NORTH);
        mainpanel.add(downpanel, BorderLayout.SOUTH);

        frame.add(mainpanel);
        frame.setVisible(true);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    public void addbutton()
    {
        for( int i = 1 ; i <= 30 ; i++ )
        {
            if( a[i] == 0 )
                bt[i] = new JButton(whiteicon);		//空闲位置是white
            else bt[i] = new JButton(redicon);		//被占位置是red

            bt[i].addActionListener( getlistener() );
            leftpanel.add(bt[i]);
        }
        for( int i = 31 ; i <= 60 ; i++ )
        {
            if( a[i] == 0 )
                bt[i] = new JButton(whiteicon);
            else bt[i] = new JButton(redicon);

            bt[i].addActionListener( getlistener() );
            rightpanel.add(bt[i]);
        }

        confirm.addActionListener( new ActionListener() 	//点击确定 的事件
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                for( int i = 1 ; i <= 60 ; i++ ) {
                    if( bt[i].getIcon() == whiteicon )
                        a[i] = 0;
                    else a[i] = 1;
                }
                if( cnt != 1)
                    new Tip("You can only choose one seat");
                else
                {
                    BookingInformation c1=new BookingInformation();	//进入信息填写界面
                    frame.dispose();
                    new MealSelect(c1).setVisible(true);
                }
            }
        });
        cancel.addActionListener( new ActionListener()		//点击返回按钮
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                frame.dispose();
            }
        });
    }

    public static void readStatus() //读取该航班的座位状态
    {
        for( int i = 1 ; i <= 60 ; i++ )
            a[i] = 0;
    }
    public ActionListener getlistener()			//座位按钮， 点击之后改变颜色
    {
        return new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                for( int i = 1 ; i <= 60 ; i++ )
                    if( e.getSource() == bt[i] )
                    {
                        if( bt[i].getIcon() == whiteicon )	//之前是白的
                        {
                            bt[i].setIcon(greenicon);
//                          seat.add(i);
                            cnt++;
                        }
                        else if(bt[i].getIcon() == greenicon)	//之前是绿的（取消所选）
                        {
                            bt[i].setIcon(whiteicon);
//                          System.out.print(i+"hh");
//                          System.out.print(seat.toString());
//                          seat.remove(i);
                            cnt--;
                        }
                        break;
                    }
            }
        };
    }

    public static void main(String[] args) {
        new ChooseSeat();
    }
}
