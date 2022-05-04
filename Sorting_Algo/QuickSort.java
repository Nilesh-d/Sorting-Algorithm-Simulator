package Sorting_Algo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;

public class QuickSort extends JFrame implements Runnable {

    public static Object lock;

    JLabel label = new JLabel();
    JLabel passer[] = new JLabel[10];
    JLabel unit[] = new JLabel[10];
    JLabel stages[] = new JLabel[10];
    JLabel details[] = new JLabel[10];
    // JLabel previewLable = new JLabel();
    // JLabel previewLable2 = new JLabel();
    JLabel data[] = new JLabel[10];
    JLabel navLeft = new JLabel();
    JLabel navRight = new JLabel();
    JLabel navPivot = new JLabel();
    JLabel info = new JLabel();

    JButton next = new JButton();
    // JButton prev = new JButton();
    JButton simu = new JButton();

    JPanel panel = new JPanel();
    JPanel outCont = new JPanel();
    JPanel cont = new JPanel();

    JPanel detailContainer = new JPanel();

    int array[] = new int[10];
    int column = -1;
    int row = 0;
    int sorted[][] = new int[100][10];
    int minValueInColumn[] = new int[10];
    int firstIndex = -1;
    int pivotvalue[] = new int[10];
    int pivotindex = -1;
    boolean flag = false;
    boolean simPhase = false;

    // private void btnPrev(java.awt.event.ActionEvent evt) {
    // for (int i = 0; i <= pivotindex; i++) {
    // System.out.print(pivotvalue[pivotindex] + " ");
    // }
    // }

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
    

    public QuickSort(int arr[]) {
        super("Sorting Algorithms");
        // System.out.println(arr.length);
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
        // prev.setText("Previous");
        // prev.addActionListener(new ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         btnPrev(evt);
        //     }
        // });
        // prev.setBounds(50, 220, 100, 40);
        // add(prev);

        simu.setText("Simulate >>");
        simu.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimu(evt);
            }
        });
        simu.setBounds(700, 220, 120, 40);
        add(simu);
        label.setText("Quick Sort");
        label.setFont(new java.awt.Font("Montserrat", 1, 36));
        label.setForeground(new Color(104, 33, 122));
        label.setBounds(30, 40, 450, 40);
        add(label);
        panel.setBounds(50, 100, 900, 100);
        // panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(42, 209,
        // 129)));
        panel.setBackground(new Color(30, 30, 30));

        //generate random number between 1 to 100
        for(int i=0;i<10;i++){
            Random r = new Random();
            arr[i]=r.nextInt(90)+10;

        }

        for (int i = 0; i < arr.length; i++) {
            unit[i] = new JLabel();
            unit[i].setBounds(10 * i, 10, 10, 10);
            unit[i].setForeground(new Color(245, 245, 245));
            unit[i].setFont(new java.awt.Font("Montserrat", 1, 24));
            unit[i].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            unit[i].setText("" + arr[i]);
            panel.add(unit[i]);
        }
        
        add(panel);
        quickSort(arr);
    }

    public void run() {
        // quickSort(array);
        System.out.println("Running quick sort thread");
        sort(array, 0, 9);
        System.out.println("Ended Sorting");
        flag=true;
        info.setText("Array is sorted!");
    }

    private void quickSort(int arr[]) {

        cont.setVisible(true);
        cont.setBounds(100, 300, 750, 60);
        // color 104,33,122
        cont.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 10; i++) {
            data[i] = new JLabel();
            data[i].setText("00");
            data[i].setFont(new java.awt.Font("Montserrat", 0, 18));
            data[i].setForeground(Color.white);
            data[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            outCont.add(data[i]);
        }
        outCont.setBackground(Color.darkGray);
        outCont.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        outCont.setSize(600, 50);

        navLeft.setText("[L]");
        // navLeft.setBounds(250, 700, 100, 100);
        // navRight.setSize(50, 50);
        // navRight.setBackground(Color.GREEN);
        // navRight.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        // navLeft.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        // navLeft.setSize(250,250);
        // navRight.setSize(250,250);

        // cont.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));

        // GroupLayout navRightLayout = new GroupLayout(navRight);
        // navRight.setLayout(navRightLayout);
        // navRightLayout.setHorizontalGroup(navRightLayout.createParallelGroup(
        // javax.swing.GroupLayout.Alignment.LEADING).addGap(0,10,Short.MAX_VALUE));
        // navRightLayout.setVerticalGroup(navRightLayout.createParallelGroup(
        // javax.swing.GroupLayout.Alignment.LEADING).addGap(0,10,Short.MAX_VALUE));

        // detailContainer.setSize(40, 40);
        detailContainer.setBounds(100,500,800,400);
        detailContainer.setBackground(Color.LIGHT_GRAY);
        detailContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        details[0] = new JLabel();
        details[1] = new JLabel();
        details[2] = new JLabel();
        details[3]=new JLabel();

        details[0].setText("Step 1: Make any element as pivot.");
        details[0].setSize(700, 50);
        details[1].setSize(700, 50);
        details[2].setSize(700, 50);
        details[3].setSize(700, 50);

        details[0].setFont(new java.awt.Font("Montserrat", 0, 26));
        details[1].setFont(new java.awt.Font("Montserrat", 0, 26));
        details[2].setFont(new java.awt.Font("Montserrat", 0, 26));
        details[3].setFont(new java.awt.Font("Montserrat", 0, 26));

        details[0].setHorizontalAlignment(0);
        details[1].setHorizontalAlignment(0);
        details[2].setHorizontalAlignment(0);
        details[3].setHorizontalAlignment(0);


        details[0].setBorder(BorderFactory.createEmptyBorder(100, 16, 10, 16));
        details[1].setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        details[2].setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        details[3].setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        details[1].setText("Step 2: Partition the array on the basis of pivot");
        details[2].setText("Step 3: Apply quick sort on left partition recursively ");
        details[3].setText("Step 4: Apply quick sort on right partition recursively ");
        cont.add(outCont);

        detailContainer.add(details[0]);
        detailContainer.add(details[1]);
        detailContainer.add(details[2]);
        detailContainer.add(details[3]);
        add(detailContainer);
        // 155 to 780 = 70 each
        navLeft.setBounds(155, 360, 20, 20);
        navRight.setBounds(780, 360, 20, 20);
        navPivot.setBounds(155, 360, 20, 20);

        navPivot.setForeground(Color.RED);
        navLeft.setForeground(Color.BLUE);
        navRight.setForeground(Color.orange);

        navPivot.setFont(new java.awt.Font("Montserrat", 0, 14));
        navLeft.setFont(new java.awt.Font("Montserrat", 0, 14));
        navRight.setFont(new java.awt.Font("Montserrat", 0, 14));

        navPivot.setText("[P]");
        navRight.setText("[R]");
        add(navLeft);
        add(navRight);
        add(navPivot);

        info.setText("Information reguarding quick sort");
        info.setFont(new java.awt.Font("Montserrat", 0, 24));
        info.setBounds(140, 400, 700, 100);
        // add(navLeft);
        add(cont);
        add(info);
        invalidate();
        validate();
        repaint();
        for (int z = 0; z < arr.length; z++) {
            data[z].setText(arr[z] + " ");
        }
        // sort(arr, 0, 9);
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }
    }

    int partition(int arr[], int low, int high) {
        int v, i, j, temp = 0;
        v = arr[low];
        pivotindex++;
        pivotvalue[pivotindex] = v;
        i = low;
        j = high + 1;
        navPivot.setBounds(155 + (70 * i), 360, 20, 20);
        navRight.setBounds(155 + (70 * (j - 1)), 360, 20, 20);
        do {
            do {
                // previewLable.setText(i + " ");
                i++;
                System.out.println(i + "");
                try {
                    // Thread.sleep(1000);
                    synchronized (lock) {
                        navLeft.setBounds(155 + (70 * i), 360, 20, 20);
                        info.setText("<html>Increment <font color='blue'>Left</font> until greater than <font color='red'>Pivot</font> (<font color='red'>" + arr[low] + "</font> > <font color='blue'>" + arr[i] + "</font>)"
                                + (i == high ? ", <br>and stop <font color='blue'>Left</font> because it's <u>last</u> element</html>" : "</html>"));
                        if(simPhase){
                            lock.wait(400);
                        }else{
                            lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } while (arr[i] < v && i < high);

            do {
                // previewLable2.setText(j + " ");
                j--;
                System.out.println(j + "");
                try {
                    synchronized (lock) {
                        navRight.setBounds(155 + (70 * j), 360, 20, 20);
                        info.setText("<html>Decrement <font color='orange'>Right</font> until smaller than <font color='red'>Pivot</font> (<font color='red'>" + arr[low] + "</font>   <font color='orange'>" + arr[j] +"</font>) "
                            +(j==low ? "<Br> and stop Right because it's <u>first</u> element</html>" : "</html>"));
                            if(simPhase){

                                lock.wait(400);
                            }else{
                                lock.wait();
                            }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } while (v < arr[j]);
            for (int z = 0; z < arr.length; z++) {
                data[z].setText(arr[z] + " ");
            }
            System.out.println();
            if (i < j) {
                System.out.print("Swap arr[i] to arr[j] i=" + i + " j=" + j + " " + arr[i] + " " + arr[j]);
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                synchronized (lock) {
                    navRight.setBounds(155 + (70 * j), 360, 20, 20);
                    info.setText("Swap left and right since i < j, i.e " + i + " > " + j);
                    try {
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
                    data[z].setText(arr[z] + " ");
                }
            }
        } while (i < j);

        System.out.println("______________");
        temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        navRight.setBounds(155 + (70 * j), 360, 20, 20);
        navLeft.setBounds(155 + (70 * i), 360, 20, 20);
        synchronized (lock) {
            info.setText("<html>Swap <font color='orange'>Right</font> with <font color='red'>Pivot</font></html>");
            try {
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
        System.out.println("Swap arr[low] arr[j] low="+low+" j="+j+" arr[low]="+arr[low]+" arr[j]="+arr[j]);
        for(int z =0;z<arr.length;z++){
            data[z].setText(arr[z]+" ");
        }
        return(j);
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    }

    public void Drawer(){
        System.out.print("heyy");
    }
}