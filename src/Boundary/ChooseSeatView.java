package Boundary;

import Entity.BookingInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChooseSeatView extends JPanel
{
    JPanel leftpanel;
    JPanel midpanel;
    JPanel rightpanel;
    JPanel uppanel;
    JPanel downpanel;


    public JButton bt[] = new JButton[100];
    public int a[];
    public ImageIcon whiteicon;
    public ImageIcon redicon;
    public ImageIcon greenicon;

    public int cnt;
    public JButton button1;//next step button
    public JButton button2;//back button
    private List<ActionListener> listeners;

    
    /** 
     * @param actionlistener
     */
    public void addActionListener(ActionListener actionlistener)
    {
        listeners.add(actionlistener);
    }

    public ChooseSeatView(int[] seatInf, int planemodel)
    {
        a = seatInf;
        whiteicon = new ImageIcon("./pictures/Seat.jpg");
        redicon = new ImageIcon("./pictures/Seat(selected).jpg");
        greenicon = new ImageIcon("./pictures/Seat(green).jpg");
        listeners = new ArrayList<ActionListener>();
        setLayout(new BorderLayout());
        leftpanel = new JPanel();
        midpanel = new JPanel();
        rightpanel = new JPanel();
        uppanel = new JPanel();
        downpanel = new JPanel();
        if (planemodel == 1)
        {
            // here is the plane planemodel[1]


            leftpanel.setLayout(new GridLayout(0, 4));
            rightpanel.setLayout(new GridLayout(0, 4));
            leftpanel.setPreferredSize(new Dimension(180, 150));
            rightpanel.setPreferredSize(new Dimension(180, 150));
            midpanel.setLayout(new GridLayout(0, 1));

            cnt = 0;

            JLabel aisle = new JLabel("AISLE", JLabel.CENTER); // middle part
            aisle.setForeground(Color.red);
            aisle.setBackground(Color.WHITE);
            JLabel planemodeltext = new JLabel("A220", JLabel.CENTER);
            planemodeltext.setForeground(Color.gray);
            planemodeltext.setBackground(Color.WHITE);
            aisle.setSize(80, 80);
            planemodeltext.setSize(80, 80);
            midpanel.add(aisle);
            midpanel.add(planemodeltext);

            uppanel.setLayout(new GridLayout(0, 10)); // 8 columns
            uppanel.setPreferredSize(new Dimension(0, 60));
            JLabel uplabel[] = new JLabel[10]; // A B C D E F
            int aisie = 0;
            for (int i = 0; i < 10; i++)
            {
                if (i == 3 || i == 4 || i == 5 || i == 6)
                {
                    uplabel[i] = new JLabel("               ", JLabel.CENTER);
                    uppanel.add(uplabel[i]);
                    aisie++;
                    continue;
                }

                String s = String.valueOf((char) ('A' + i - aisie));
                uplabel[i] = new JLabel(s, JLabel.CENTER);
                uplabel[i].setForeground(Color.BLUE);
                uplabel[i].setFont(new Font("console", Font.BOLD, 20));
                uppanel.add(uplabel[i]);
            }

            button1 = new JButton("Back");
            button2 = new JButton("Next");
            button1.setForeground(Color.black);
            button2.setForeground(Color.black);
            button1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    for (int i = 0; i < listeners.size(); ++i)
                    {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });
            button2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    for (int i = 0; i < listeners.size(); ++i)
                    {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });

            downpanel.add(button1);
            downpanel.add(button2);

            // add seat button on the left panel and the right panel

            int i1, line1 = 0;
            // here add one more column to set as the line index
            for (int index = 1; index <= 40; index++)
            {

                if (index % 4 == 0)
                {
                    line1++;
                    JButton j = new JButton(String.valueOf(line1));
                    leftpanel.add(j);
                    j.setMargin(new Insets(0, 0, 0, 0));
                    j.setEnabled(false);
                    j.setBackground(Color.lightGray);
                } else
                {
                    i1 = index - line1;
                    if (a[i1] == 0)
                        bt[i1] = new JButton(whiteicon); // 空闲位置是white
                    else
                        bt[i1] = new JButton(redicon); // 被占位置是red

                    bt[i1].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int i = 0; i < listeners.size(); ++i)
                            {
                                listeners.get(i).actionPerformed(e);
                            }
                        }
                    });
                    leftpanel.add(bt[i1]);
                    //here add the seat to the pannel

                }
            }
            int i2, line2 = 0;
            for (int index = 31; index <= 70; index++)
            {
                if ((index + 1) % 4 == 0)
                {
                    line2++;
                    JButton j = new JButton(String.valueOf(line2));
                    rightpanel.add(j);
                    j.setMargin(new Insets(0, 0, 0, 0));
                    j.setEnabled(false);
                    j.setBackground(Color.lightGray);
                } else
                {
                    i2 = index - line2;
                    if (a[i2] == 0)
                        bt[i2] = new JButton(whiteicon);
                    else
                        bt[i2] = new JButton(redicon);

                    bt[i2].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int i = 0; i < listeners.size(); ++i)
                            {
                                listeners.get(i).actionPerformed(e);
                            }
                        }
                    });
                    rightpanel.add(bt[i2]);

                }
            }
            add(leftpanel, BorderLayout.WEST);
            add(midpanel, BorderLayout.CENTER);
            add(rightpanel, BorderLayout.EAST);
            add(uppanel, BorderLayout.NORTH);
            add(downpanel, BorderLayout.SOUTH);
        } else if (planemodel == 2)
        {


            // here is the plane planemodel[2]
            leftpanel.setLayout(new GridLayout(0, 5));
            rightpanel.setLayout(new GridLayout(0, 5));
            leftpanel.setPreferredSize(new Dimension(240, 180));
            rightpanel.setPreferredSize(new Dimension(240, 180));
            // planemodel 2 (4+4)×12
            midpanel.setLayout(new GridLayout(0, 1));

            cnt = 0;

            JLabel aisle = new JLabel("AISLE", JLabel.CENTER); // middle part
            aisle.setForeground(Color.red);
            aisle.setBackground(Color.WHITE);
            JLabel planemodeltext = new JLabel("A320", JLabel.CENTER);
            planemodeltext.setForeground(Color.gray);
            planemodeltext.setBackground(Color.WHITE);
            aisle.setSize(80, 80);
            planemodeltext.setSize(80, 80);
            midpanel.add(aisle);
            midpanel.add(planemodeltext);

            uppanel.setLayout(new GridLayout(0, 12)); // 10 columns
            uppanel.setPreferredSize(new Dimension(0, 30));
            JLabel uplabel[] = new JLabel[12]; // A B C D E F G H
            int aisie = 0;
            for (int i = 0; i < 12; i++)
            {
                if (i == 4 || i == 5 || i == 6 || i == 7)
                {
                    uplabel[i] = new JLabel("             ", JLabel.CENTER);
                    uppanel.add(uplabel[i]);
                    aisie++;
                    continue;
                }

                String s = String.valueOf((char) ('A' + i - aisie));
                uplabel[i] = new JLabel(s, JLabel.CENTER);
                uplabel[i].setForeground(Color.BLUE);
                uplabel[i].setFont(new Font("console", Font.BOLD, 20));
                uppanel.add(uplabel[i]);
            }

            button1 = new JButton("Back");
            button2 = new JButton("Next");
            button1.setForeground(Color.black);
            button2.setForeground(Color.black);
            button1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    for (int i = 0; i < listeners.size(); ++i)
                    {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });
            button2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    for (int i = 0; i < listeners.size(); ++i)
                    {
                        listeners.get(i).actionPerformed(e);
                    }
                }
            });

            downpanel.add(button1);
            downpanel.add(button2);

            // add seat button on leftpanel and right panel
            int i1, line1 = 0;
            for (int index = 1; index <= 60; index++)
            {
                if (index % 5 == 0)
                {
                    line1++;
                    JButton j = new JButton(String.valueOf(line1));
                    leftpanel.add(j);
                    j.setMargin(new Insets(0, 0, 0, 0));
                    j.setEnabled(false);
                    j.setBackground(Color.lightGray);

                } else
                {
                    i1 = index - line1;
                    if (a[i1] == 0)
                        bt[i1] = new JButton(whiteicon); // 空闲位置是white
                    else
                        bt[i1] = new JButton(redicon); // 被占位置是red

                    bt[i1].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int i = 0; i < listeners.size(); ++i)
                            {
                                listeners.get(i).actionPerformed(e);
                            }
                        }
                    });
                    leftpanel.add(bt[i1]);
                }
            }


            int i2, line2 = 0;
            for (int index = 49; index <= 108; index++)
            {
                if ((index + 1) % 5 == 0)
                {
                    line2++;
                    JButton j = new JButton(String.valueOf(line2));
                    rightpanel.add(j);
                    j.setMargin(new Insets(0, 0, 0, 0));
                    j.setEnabled(false);
                    j.setBackground(Color.lightGray);

                } else
                {
                    i2 = index - line2;
                    if (a[i2] == 0)
                        bt[i2] = new JButton(whiteicon);
                    else
                        bt[i2] = new JButton(redicon);

                    bt[i2].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int i = 0; i < listeners.size(); ++i)
                            {
                                listeners.get(i).actionPerformed(e);
                            }
                        }
                    });
                    rightpanel.add(bt[i2]);
                }
            }

            add(leftpanel, BorderLayout.WEST);
            add(midpanel, BorderLayout.CENTER);
            add(rightpanel, BorderLayout.EAST);
            add(uppanel, BorderLayout.NORTH);
            add(downpanel, BorderLayout.SOUTH);
        }
    }

    
    /** 
     * @return boolean
     */
    public boolean checkValidity()
    {

        if (cnt != 1)
        {

            return false;
        } else
        {
            return true;
        }
    }

    /**
     *
     * @param planemodel 1 or 2 ,represent 2 different plane modle
     * @return "seatNo(type1),seatNo(type2)", same number in different type ,will be used in different place next
     */
    public String getSelectedSeatNo(int planemodel)
    {
        if (planemodel == 1)
        {
            int selectNo = 1;
            for (int i = 1; i <= 60; i++)
            {
                if (bt[i].getIcon() == whiteicon)
                    a[i] = 0;
                else
                    a[i] = 1;

                if (bt[i].getIcon() == greenicon)
                {
                    selectNo = i;
                }
            }

            if (selectNo <= 30)
            {
                switch (selectNo % 3)
                {
                    case 1:
                        return ((selectNo-1)/3+1) + "A,"+selectNo;
                    case 2:
                        return ((selectNo-1)/3+1) + "B,"+selectNo;
                    case 0:
                        return ((selectNo-1)/3+1) + "C,"+selectNo;
                }
            } else
            {
                switch (selectNo % 3)
                {
                    case 1:
                        return ((selectNo-1-30)/3+1) + "D,"+selectNo;
                    case 2:
                        return ((selectNo-1-30)/3+1) + "E,"+selectNo;
                    case 0:
                        return ((selectNo-1-30)/3+1) + "F,"+selectNo;
                }
            }
            return "None error!";
        } else if (planemodel == 2)
        {
            int selectNo = 1;
            for (int i = 1; i <= 96; i++)
            {
                if (bt[i].getIcon() == whiteicon)
                    a[i] = 0;
                else
                    a[i] = 1;

                if (bt[i].getIcon() == greenicon)
                {
                    selectNo = i;
                }
            }

            if (selectNo <= 48)
            {
                switch (selectNo % 4)
                {
                    case 1:
                        return ((selectNo-1)/4+1) + "A,"+selectNo;
                    case 2:
                        return ((selectNo-1)/4+1) + "B,"+selectNo;
                    case 3:
                        return ((selectNo-1)/4+1) + "C,"+selectNo;
                    case 0:
                        return ((selectNo-1)/4+1) + "D,"+selectNo;
                }
            } else
            {
                switch (selectNo % 4)
                {
                    case 1:
                        return ((selectNo-1-48)/4+1) + "E,"+selectNo;
                    case 2:
                        return ((selectNo-1-48)/4+1) + "F,"+selectNo;
                    case 3:
                        return ((selectNo-1-48)/4+1) + "G,"+selectNo;
                    case 0:
                        return ((selectNo-1-48)/4+1) + "H,"+selectNo;
                }
            }
            return "None error!";
        } else
        {
            return "None error!";
        }
    }
}

