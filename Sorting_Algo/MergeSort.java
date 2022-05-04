package Sorting_Algo;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class MergeSort extends JFrame implements Runnable{

    public static Object lock;

    int array[] = new int[10];
    JButton next = new JButton();
    JButton simu = new JButton();
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JLabel unit[] = new JLabel[10];

    int arr[] =new int[10];

    JPanel resultCont = new JPanel();
    boolean simPhase = false;

    public MergeSort(int arrOld[]){
        super("Merge Sort");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // JFrame.EXIT_ON_CLOSE
        setLayout(null);
        setLocation(500, 10);
        setVisible(true);
        setSize(1000, 1000);

        next.setText("Next");
        next.setBounds(850 , 220, 100, 40);
        next.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                btnNext(evt);
            }
        });

        simu.setText("Simulate >>");
        simu.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimu(evt);
            }
        });
        simu.setBounds(700, 220, 120, 40);

        label.setText("Merge Sort");
        label.setFont(new java.awt.Font("Montserrat", 1, 36));
        label.setForeground(new Color(104, 33, 122));
        label.setBackground(Color.GRAY);
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

        resultCont.setBounds(70, 300, 850, 700);
        resultCont.setBackground(Color.DARK_GRAY);
        add(next);
        add(simu);
        add(panel);
        add(resultCont);
        
    }

    private void btnNext(java.awt.event.ActionEvent evt) {
        synchronized (lock) {
            lock.notify();
        }
        // System.out.println("next");
    }
    private void btnSimu(java.awt.event.ActionEvent evt) {
        /*synchronized (lock) {
            lock.notify();
        }*/
        simPhase=!simPhase;
        System.out.println("Simu");
    }

    public void run(){
        sort(arr,0,arr.length-1);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }

    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
 
            // Sort first and second halves

            synchronized (lock) {
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
            System.out.println("Left");
            sort(arr, l, m);
            System.out.println("Right");
            sort(arr, m + 1, r);
            System.out.println("Merge");
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
        System.out.println();

        for(int z=0;z<R.length;z++)
            System.out.print(L[z]+" ");

        System.out.println();
        
        for(int z=0;z<R.length;z++)
            System.out.print(R[z]+" ");
        
        System.out.println();
    }
}