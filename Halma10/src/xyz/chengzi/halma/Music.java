package xyz.chengzi.halma;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Music {

    private static AudioClip now;

    public Music() {
        JFrame music = new JFrame("音乐播放器");
        music.setVisible(true);
        music.setSize(600, 100);
        music.setLocation(650, 100);
        //背景图设置
        ImageIcon img1 = new ImageIcon("C:\\田宇琼\\b.jpg");
        JLabel imgLabel1 = new JLabel(img1);
        music.getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
        imgLabel1.setBounds(0, 0, 600, 100);
        Container cp1 = music.getContentPane();
        cp1.setLayout(null);
        ((JPanel) cp1).setOpaque(false);

        File f1;
        File f2;
        File f3;
        File f4;
        File f5;

        try {
            f1 = new File("C:\\music\\崛川丽美 - I Left You Last.wav"); //绝对路径
            f2 = new File("C:\\music\\Goldmund - Alberta.wav");
            f3 = new File("C:\\music\\Piano Peace - Piano Serenade.wav");
            f4 = new File("C:\\music\\Tejal Yann - End Of Days.wav");
            f5 = new File("C:\\music\\出羽良彰 - Cry for the Moon.wav");

            AudioClip aau1 = Applet.newAudioClip(f1.toURI().toURL());
            AudioClip aau2 = Applet.newAudioClip(f2.toURI().toURL());
            AudioClip aau3 = Applet.newAudioClip(f3.toURI().toURL());
            AudioClip aau4 = Applet.newAudioClip(f4.toURI().toURL());
            AudioClip aau5 = Applet.newAudioClip(f5.toURI().toURL());
            AudioClip [] a = new AudioClip[5];
            a[0]=aau1;
            a[1]=aau2;
            a[2]=aau3;
            a[3]=aau4;
            a[4]=aau5;

            int n = (int) (Math.random()*5);
            a[n].play();
            now=a[n];
            System.out.println(n);

            JButton kaishi = new JButton("Random");
            kaishi.setBorder(null);
            kaishi.setContentAreaFilled(false);
            kaishi.setFont(new Font("华文隶书", Font.BOLD, 30));
            kaishi.setForeground(Color.white);
            music.add(kaishi);
            kaishi.setBounds(0, 0, 150, 50);
            kaishi.setVisible(true);
            kaishi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(now==aau1){
                        now.stop();
                    }else{
                        if(now==aau2) {
                            now.stop();
                        }else{
                            if(now==aau3) {
                                now.stop();
                            }else{
                                if(now==aau4) {
                                    now.stop();
                                }else{
                                    if(now==aau5) {
                                        now.stop();
                                    }
                                }
                            }
                        }
                    }
                    int n = (int) (Math.random()*5);
                    a[n].play();
                    now=a[n];
                    System.out.println(n);
                }
            });

            JButton ting = new JButton("Pause");
            ting.setBorder(null);
            ting.setContentAreaFilled(false);
            ting.setFont(new Font("华文隶书", Font.BOLD, 30));
            ting.setForeground(Color.white);
            music.add(ting);
            ting.setBounds(150, 0, 100, 50);
            ting.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(now==aau1){
                        now.stop();
                    }else{
                        if(now==aau2) {
                            now.stop();
                        }else{
                            if(now==aau3) {
                                now.stop();
                            }else{
                                if(now==aau4) {
                                    now.stop();
                                }else{
                                    if(now==aau5) {
                                        now.stop();
                                    }
                                }
                            }
                        }
                    }
                }
            });

            JButton shangyishou = new JButton("Prev");
            shangyishou.setBorder(null);
            shangyishou.setContentAreaFilled(false);
            shangyishou.setFont(new Font("华文隶书", Font.BOLD, 30));
            shangyishou.setForeground(Color.white);
            music.add(shangyishou);
            shangyishou.setBounds(250, 0, 100, 50);
            shangyishou.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    now.stop();
                    if(now==aau1){
                        now=aau5;
                        now.play();
                    }else{
                        if(now==aau2) {
                            now = aau1;
                            now.play();
                        }else{
                            if(now==aau3) {
                                now = aau2;
                                now.play();
                            }else{
                                if(now==aau4) {
                                    now = aau3;
                                    now.play();
                                }else{
                                    if(now==aau5) {
                                        now = aau4;
                                        now.play();
                                    }
                                }
                            }
                        }
                    }
                }
            });

            JButton xiayishou = new JButton("Next");
            xiayishou.setBorder(null);
            xiayishou.setContentAreaFilled(false);
            xiayishou.setFont(new Font("华文隶书", Font.BOLD, 30));
            xiayishou.setForeground(Color.white);
            music.add(xiayishou);
            xiayishou.setBounds(350, 0, 100, 50);
            xiayishou.setVisible(true);
            xiayishou.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    now.stop();
                    if(now==aau1){
                        now=aau2;
                        now.play();
                    }else{
                        if(now==aau2) {
                            now = aau3;
                            now.play();
                        }else{
                            if(now==aau3) {
                                now = aau4;
                                now.play();
                            }else{
                                if(now==aau4) {
                                    now = aau5;
                                    now.play();
                                }else{
                                    if(now==aau5) {
                                        now = aau1;
                                        now.play();
                                    }
                                }
                            }
                        }
                    }
                }
            });

            JButton xuanhuan = new JButton("Single");
            xuanhuan.setBorder(null);
            xuanhuan.setContentAreaFilled(false);
            xuanhuan.setFont(new Font("华文隶书", Font.BOLD, 30));
            xuanhuan.setForeground(Color.white);
            music.add(xuanhuan);
            xuanhuan.setBounds(450, 0, 100, 50);
            xuanhuan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(now==aau1){
                       now.loop();
                    }else{
                        if(now==aau2) {
                            now.loop();
                        }else{
                            if(now==aau3) {
                                now.loop();
                            }else{
                                if(now==aau4) {
                                    now.loop();
                                }else{
                                    if(now==aau5) {
                                        now.loop();
                                    }
                                }
                            }
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}