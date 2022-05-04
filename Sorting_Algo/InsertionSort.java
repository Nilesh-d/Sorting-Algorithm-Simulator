package Sorting_Algo;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;

public class InsertionSort extends JFrame implements Runnable {

    public static Object lock;

    JLabel label = new JLabel();
    JLabel unit[] = new JLabel[10];
    JLabel details[] = new JLabel[10];
    JLabel data[] = new JLabel[10];
    JLabel information = new JLabel();
    JLabel jIndex = new JLabel();

    JButton next = new JButton();
    JButton prev = new JButton();
    JButton simu = new JButton();

    JPanel panel = new JPanel();
    JPanel outCont = new JPanel();
    JPanel cont = new JPanel();

    int array[] = new int[10];
    boolean simPhase=false;

    private void btnPrev(java.awt.event.ActionEvent evt) {
        /*Nothing for now*/
    }

    private void btnNext(java.awt.event.ActionEvent evt) {
        synchronized (lock) {
            lock.notify();
        }
    }

    private void btnSimu(java.awt.event.ActionEvent evt) {
        if(simPhase){
            simPhase=false;
        }else{
            simPhase=true;
        }
    }

    public void run(){
        insertionSort(array);
    }

    public InsertionSort(int[] arr) {
        super("Sorting Algorithms");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // JFrame.EXIT_ON_CLOSE
        setLayout(null);
        setLocation(500, 10);
        setVisible(true);
        setSize(1000, 1000);
        array = arr;

        // Next Button
        next.setText("Next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext(evt);
            }
        });
        next.setBounds(850, 220, 100, 40);
        add(next);

        // Previous Button
        prev.setText("Previous");
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev(evt);
            }
        });
        prev.setBounds(50, 220, 100, 40);
        add(prev);

        // Simulate Button
        simu.setText("Simulate >>");
        simu.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimu(evt);
            }
        });
        simu.setBounds(700, 220, 120, 40);
        add(simu);

        //Header
        label.setText("Insertion Sort");
        label.setFont(new java.awt.Font("Montserrat", 1, 36));
        label.setForeground(new Color(104, 33, 122));
        label.setBounds(30, 40, 450, 40);
        add(label);
        panel.setBounds(50, 100, 900, 100);
        panel.setBackground(new Color(30, 30, 30));

        //generate random number between 1 to 100
        for(int i=0;i<10;i++){
            Random r = new Random();
            arr[i]=r.nextInt(90)+10;

        }

        //input values
        for (int i = 0; i < arr.length; i++) {
            unit[i] = new JLabel();
            unit[i].setBounds(10 * i, 10, 10, 10);
            unit[i].setForeground(new Color(245, 245, 245));
            unit[i].setFont(new java.awt.Font("Montserrat", 1, 24));
            unit[i].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            unit[i].setText("" + arr[i]);
            panel.add(unit[i]);
        }

        //Marker Index
        jIndex.setText("[P]");
        jIndex.setBounds(200, 350, 40, 40);
        jIndex.setFont(new java.awt.Font("Montserrat", 1, 20));
        jIndex.setForeground(Color.red);
        add(jIndex);
        add(panel);
        
        information.setText("Operation: Initialize array");
        information.setBounds(100, 400, 750, 60);
        information.setFont(new java.awt.Font("Montserrat",0,26));
        add(information);

        JPanel panel2 = new JPanel();
        panel2.setBounds(100, 300, 750, 60);
        panel2.setBackground(Color.lightGray);

        for (int i = 0; i < 10; i++) {
            data[i] = new JLabel();
            data[i].setText(arr[i] + "");
            data[i].setSize(20, 20);
            data[i].setBorder(BorderFactory.createEmptyBorder(10, 12, 0, 12));
            data[i].setFont(new java.awt.Font("Montserrat", 1, 24));
            panel2.add(data[i]);
        }
        add(panel2);
        // insertionSort(arr)
    }

    private void insertionSort(int arr[]) {

        //info container
        cont.setVisible(true);
        cont.setBounds(100, 500, 750, 500); 
        cont.setBackground(Color.LIGHT_GRAY); // color 104,33,122

        for(int i=0;i<3;i++){
            details[i] = new JLabel();
            details[i].setSize(700, 50);
            details[i].setFont(new java.awt.Font("Montserrat", 0, 26));
            details[i].setHorizontalAlignment(0);
        }

        details[0].setText("Step 1: Iterate from arr[0] to arr[n] over the array.");

        
        details[0].setBorder(BorderFactory.createEmptyBorder(100, 16, 10, 16));
        details[1].setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        details[2].setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        details[1].setText("<html>Step 2: If arr[i] > arr[i+1] Then <font color='blue'>Swap</font> ");
        details[2].setText("Step 3: repat Step 2 untill i=0  ");

        for(int i=0;i<3;i++){
            cont.add(details[i]);
        }
        add(cont);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                jIndex.setBounds(200 +(58*(i)), 350, 40, 40);
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j + 1 - 1];
                    arr[j + 1 - 1] = temp;

                    for(int z=0;z<arr.length;z++){
                        data[z].setForeground(Color.BLACK);
                    }
                    data[j].setForeground(Color.blue);
                    data[j+1].setForeground(Color.blue);
                    
                    synchronized (lock) {
                        try {
                            information.setText("Operation: arr[i] > arr[i+1] then Swap "+arr[j]+" with "+arr[j+1]);
                            if(simPhase){
                                lock.wait(400);
                            }else{
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    for (int z = 0; z < arr.length; z++) {
                        data[z].setText(arr[z] + "");
                    }
                    
                    System.out.println();
                }else{
                    synchronized (lock) {
                        try {
                            information.setText("Operation: arr[i] < arr[i+1] then Continue traverse");
                            if(simPhase){
                                lock.wait(400);
                            }else{
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                if(i==9&&j==0){
                    information.setText("Array is sorted");
                }
            }
        }
        for(int j=0;j<arr.length;j++){
            System.out.println(arr[j]);
        }
        information.setText("Array is sorted");
    }


}