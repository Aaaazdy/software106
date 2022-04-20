package Boundary;

import Entity.BookingInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChooseSeatView extends JPanel {
    JPanel leftpanel;
    JPanel midpanel;
    JPanel rightpanel;
    JPanel uppanel;
    JPanel downpanel;
    public JButton bt[] = new JButton[63];

    static int a[] = new int[70];
    public ImageIcon whiteicon;
    public ImageIcon redicon;
    public ImageIcon greenicon;

    public int cnt;
    public JButton button1;//next step button
    public JButton button2;//back button
    private List<ActionListener> listeners;

    public void addActionListener(ActionListener actionlistener) {
        listeners.add(actionlistener);
    }

    public ChooseSeatView() {
        listeners = new ArrayList<ActionListener>();

        setLayout(new BorderLayout());
        leftpanel = new JPanel();
        midpanel = new JPanel();
        rightpanel = new JPanel();
        uppanel = new JPanel();
        downpanel = new JPanel();
        whiteicon = new ImageIcon("./pictures/Seat.jpg");
        redicon = new ImageIcon("./pictures/Seat(selected).jpg");
        greenicon = new ImageIcon("./pictures/Seat(green).jpg");

        leftpanel.setLayout(new GridLayout(0, 3));
        rightpanel.setLayout(new GridLayout(0, 3));
        leftpanel.setPreferredSize(new Dimension(180, 150));
        rightpanel.setPreferredSize(new Dimension(180, 150));
        midpanel.setLayout(new GridLayout(0, 1));

        cnt = 0;


        JLabel aisle = new JLabel("AISLE", JLabel.CENTER);        //middle part
        aisle.setForeground(Color.red);
        aisle.setBackground(Color.WHITE);
        midpanel.add(aisle);

        uppanel.setLayout(new GridLayout(0, 8));        //8 columns
        uppanel.setPreferredSize(new Dimension(0, 30));
        JLabel uplabel[] = new JLabel[8];        // A B C   D E F
        for (int i = 0; i < 8; i++) {
            if (i == 3 || i == 4) {
                uplabel[i] = new JLabel("     ", JLabel.CENTER);
                uppanel.add(uplabel[i]);
                continue;
            }

            String s = String.valueOf((char) ('A' + i));
            uplabel[i] = new JLabel(s, JLabel.CENTER);
            uplabel[i].setForeground(Color.BLUE);
            uplabel[i].setFont(new Font("宋体", Font.BOLD, 20));
            uppanel.add(uplabel[i]);
        }

        button1 = new JButton("Back");
        button2 = new JButton("Next");
        button1.setForeground(Color.black);
        button2.setForeground(Color.black);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeners.size(); ++i) {
                    listeners.get(i).actionPerformed(e);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeners.size(); ++i) {
                    listeners.get(i).actionPerformed(e);
                }
            }
        });

        downpanel.add(button1);
        downpanel.add(button2);


        //add seat button on leftpanel and right panel
        readStatus();
        for (int i = 1; i <= 30; i++) {
            if (a[i] == 0)
                bt[i] = new JButton(whiteicon);        //空闲位置是white
            else bt[i] = new JButton(redicon);        //被占位置是red

            bt[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < listeners.size(); ++i) {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });
            leftpanel.add(bt[i]);
        }
        for (int i = 31; i <= 60; i++) {
            if (a[i] == 0)
                bt[i] = new JButton(whiteicon);
            else bt[i] = new JButton(redicon);

            bt[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < listeners.size(); ++i) {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });
            rightpanel.add(bt[i]);
        }

        add(leftpanel, BorderLayout.WEST);
        add(midpanel, BorderLayout.CENTER);
        add(rightpanel, BorderLayout.EAST);
        add(uppanel, BorderLayout.NORTH);
        add(downpanel, BorderLayout.SOUTH);
    }

    /**
     * read and set the seat status ,if it is selected set a[i]=1, else set a[i]=0
     */
    public static void readStatus() {
        for (int i = 1; i <= 60; i++)
            a[i] = 0;
    }

    public boolean checkValidity() {

        if (cnt != 1) {

            return false;
        } else {
            return true;
        }
    }
    public String getSelectedSeatNo(){
        int selectNo=1;
        for (int i = 1; i <= 60; i++) {
            if (bt[i].getIcon() == whiteicon)
                a[i] = 0;
            else a[i] = 1;

            if(bt[i].getIcon() == greenicon){
                selectNo=i;
            }
        }


        if(selectNo<=30){
            switch (selectNo%3){
                case 1:
                    return selectNo+"A";
                case 2:
                    return selectNo+"B";
                case 0:
                    return selectNo+"C";
            }
        }
        else {
            switch (selectNo%3){
                case 1:
                    return selectNo+"D";
                case 2:
                    return selectNo+"E";
                case 0:
                    return selectNo+"F";
            }
        }
        return "None error!";

    }
}

