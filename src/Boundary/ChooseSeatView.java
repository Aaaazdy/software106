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


    public JButton bt[] = new JButton[100];
    public int a[];
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

    public ChooseSeatView(int[] seatInf,int planemodel) {
        a=seatInf;
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
        if (planemodel == 1) {
            // here is the plane planemodel[1]



            leftpanel.setLayout(new GridLayout(0, 3));
            rightpanel.setLayout(new GridLayout(0, 3));
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

            uppanel.setLayout(new GridLayout(0, 8)); // 8 columns
            uppanel.setPreferredSize(new Dimension(0, 60));
            JLabel uplabel[] = new JLabel[8]; // A B C D E F
            int aisie = 0;
            for (int i = 0; i < 8; i++) {
                if (i == 3 || i == 4) {
                    uplabel[i] = new JLabel("     ", JLabel.CENTER);
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

            // add seat button on leftpanel and right panel

            for (int i = 1; i <= 30; i++) {
                if (a[i] == 0)
                    bt[i] = new JButton(whiteicon); // 空闲位置是white
                else
                    bt[i] = new JButton(redicon); // 被占位置是red

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
                else
                    bt[i] = new JButton(redicon);

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
        } else if (planemodel == 2) {

            // here is the plane planemodel[2]
            leftpanel.setLayout(new GridLayout(0, 4));
            rightpanel.setLayout(new GridLayout(0, 4));
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

            uppanel.setLayout(new GridLayout(0, 10)); // 10 columns
            uppanel.setPreferredSize(new Dimension(0, 30));
            JLabel uplabel[] = new JLabel[10]; // A B C D E F G H
            int aisie = 0;
            for (int i = 0; i < 10; i++) {
                if (i == 4 || i == 5) {
                    uplabel[i] = new JLabel("     ", JLabel.CENTER);
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

            // add seat button on leftpanel and right panel

            for (int i = 1; i <= 48; i++) {
                if (a[i] == 0)
                    bt[i] = new JButton(whiteicon); // 空闲位置是white
                else
                    bt[i] = new JButton(redicon); // 被占位置是red

                bt[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < listeners.size(); ++i) {
                            listeners.get(i).actionPerformed(e);
                        }
                    }
                });
                leftpanel.add(bt[i]);
            }
            for (int i = 49; i <= 96; i++) {
                if (a[i] == 0)
                    bt[i] = new JButton(whiteicon);
                else
                    bt[i] = new JButton(redicon);

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
    }

    public boolean checkValidity() {

        if (cnt != 1) {

            return false;
        } else {
            return true;
        }
    }

    public String getSelectedSeatNo(int planemodel) {
        if (planemodel == 1) {
            int selectNo = 1;
            for (int i = 1; i <= 60; i++) {
                if (bt[i].getIcon() == whiteicon)
                    a[i] = 0;
                else
                    a[i] = 1;

                if (bt[i].getIcon() == greenicon) {
                    selectNo = i;
                }
            }

            if (selectNo <= 30) {
                switch (selectNo % 3) {
                    case 1:
                        return selectNo + "A";
                    case 2:
                        return selectNo + "B";
                    case 0:
                        return selectNo + "C";
                }
            } else {
                switch (selectNo % 3) {
                    case 1:
                        return selectNo + "D";
                    case 2:
                        return selectNo + "E";
                    case 0:
                        return selectNo + "F";
                }
            }
            return "None error!";
        } else if (planemodel == 2) {
            int selectNo = 1;
            for (int i = 1; i <= 96; i++) {
                if (bt[i].getIcon() == whiteicon)
                    a[i] = 0;
                else
                    a[i] = 1;

                if (bt[i].getIcon() == greenicon) {
                    selectNo = i;
                }
            }

            if (selectNo <= 48) {
                switch (selectNo % 4) {
                    case 1:
                        return selectNo + "A";
                    case 2:
                        return selectNo + "B";
                    case 3:
                        return selectNo + "C";
                    case 0:
                        return selectNo + "D";
                }
            } else {
                switch (selectNo % 4) {
                    case 1:
                        return selectNo + "E";
                    case 2:
                        return selectNo + "F";
                    case 3:
                        return selectNo + "G";
                    case 0:
                        return selectNo + "H";
                }
            }
            return "None error!";
        } else {
            return "None error!";
        }
    }
}

