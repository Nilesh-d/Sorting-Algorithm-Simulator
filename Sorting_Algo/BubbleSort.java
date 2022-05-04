package Sorting_Algo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Random;

public class BubbleSort extends JFrame implements Runnable {

    public static Object lock;

    JLabel label = new JLabel();
    JLabel unit[] = new JLabel[10];
    JLabel instruction = new JLabel();
    int array[] = new int[10];
    JPanel panel = new JPanel();
    JLabel stages[] = new JLabel[10];
    JPanel cont = new JPanel();
    JButton next = new JButton();
    JButton prev = new JButton();
    JButton simu = new JButton();
    boolean isNextEnabled = false;
    JLabel details[] = new JLabel[5];
    int data[][][] = new int[10][10][10];
    int row = 0;
    int column = -1;
    boolean simPhase =false;

    private void btnPrev(java.awt.event.ActionEvent evt) {
        
    }

    private void btnNext(java.awt.event.ActionEvent evt) {
        // cont.invalidate();
        // cont.validate();
        // cont.repaint();
        // if (row >= 9 && column >= 9) {
        //     row = 0;
        //     column = 0;
        // } else if (row!=9 && (row + column == 9 || column == 9)) {
        //     row++;
        //     column = 0;
        // } else {
        //     column++;
        // }
        // Drawer();
        // System.out.println("PreviousClicked");
        synchronized(lock){
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
    
	public BubbleSort(int[] arr){
        super("Sorting Algorithms");
        System.out.println(arr.length);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);    //JFrame.EXIT_ON_CLOSE
		setLayout(null);
		setLocation(500, 10);
		setVisible(true);
        setSize(1000,1000);
        array=arr;
        // Next Button
        next.setText("Next");
        next.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext(evt);
                }
        });
        next.setBounds(850,220,100,40);
        add(next);
        //  Previous Button
        prev.setText("Previous");
        prev.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev(evt);
                }
        });
        prev.setBounds(50,220,100,40);
        prev.setEnabled(false);
        add(prev);

        simu.setText("Simulate >>");
        simu.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimu(evt);
                }
        });
        simu.setBounds(700,220,120,40);
        add(simu);
        label.setText("Bubble Sorting");
        label.setFont(new java.awt.Font("Montserrat", 1, 36));
        label.setForeground(new Color(104,33,122));
        label.setBounds(30, 40, 450, 40);
        add(label);
        panel.setBounds(50,100,900,100);
        // panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(42, 209, 129)));
        panel.setBackground(new Color(30,30,30));

        //generate random number between 1 to 100
        for(int i=0;i<10;i++){
            Random r = new Random();
            arr[i]=r.nextInt(90)+10;

        }


        for(int i=0;i<arr.length;i++){
            unit[i]= new JLabel();
            unit[i].setBounds(10*i, 10, 10, 10);
            unit[i].setForeground(new Color(245,245,245));
            unit[i].setFont(new java.awt.Font("Montserrat", 1, 24));
            unit[i].setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            unit[i].setText(""+arr[i]);

            stages[i]=new JLabel();
            stages[i].setText(arr[i]+"");
            stages[i].setSize(10, 10);
            stages[i].setFont(new java.awt.Font("Montserrat", 0, 24));
            stages[i].setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
            cont.add(stages[i]);
            panel.add(unit[i]);
        }

        JPanel panel2 = new JPanel();
        panel2.setBounds(100,500,750,500);
        panel2.setBackground(Color.lightGray);
        // setContentPane(panel2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel step[] = new JLabel[10];
        step[0]=new JLabel("Step 1: Traverse over the array",4);
        step[0].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        step[0].setHorizontalAlignment(SwingConstants.RIGHT);
        step[0].setFont(new java.awt.Font("Montserrat", 0, 26));

        step[1]=new JLabel();
        step[1].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        step[1].setText("<html>Step 2: if current element > previous element then swap and move to next element</html");
        step[1].setSize(700, 100);
        step[1].setFont(new java.awt.Font("Montserrat", 0, 26));

        step[2]= new JLabel();
        step[2].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        step[2].setText("Step 3: repeat step 2 and decrease size of sorting list by 1");
        step[2].setFont(new java.awt.Font("Montserrat", 0, 26));
        step[0].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        step[1].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        step[2].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        panel2.add(step[0]);
        panel2.add(step[1]);
        panel2.add(step[2]);
        add(panel2);

        add(panel);

        instruction.setBounds(100, 400, 750, 60);
        instruction.setText("Operation: Initialize the array");
        instruction.setFont(new java.awt.Font("Montserrat",0,26));

        add(instruction);
        // bubbleSort(arr);
        cont.setVisible(true);
            cont.setBounds(100,300,750,60);
            // color 104,33,122
            cont.setBackground(Color.LIGHT_GRAY);
            add(cont);
            validate();
            repaint();
    }

    public void run(){
        System.out.println("Over");
        bubbleSort(array);
    }


    public void bubbleSort(int arr[]) 
    { 
        try{


        int n = arr.length; 
        
            
        for (int i = 0; i < n-1; i++)
	    { 
            isNextEnabled=false;
            // while(!isNextEnabled){
            //     Thread.sleep(400);
            // }
            for (int j = 0; j < n-i-1; j++) 
		    {
                
                synchronized(lock){
                    if(simPhase){
                        lock.wait(400);
                    }else{
                        lock.wait();
                    }
                }
                
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[j] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                    instruction.setText("Operation: Swap, since "+ arr[j+1] +" > "+arr[j]);
                    synchronized(lock){
                        if(simPhase){
                            lock.wait(400);
                        }else{
                            lock.wait();
                        }
                    }
                }else{
                    instruction.setText("Operation: Move to next element");
                    synchronized(lock){
                        if(simPhase){
                            lock.wait(400);
                        }else{
                            lock.wait();
                        }
                    }
                } 
                cont.removeAll();

                
                System.out.println();
                for(int z=0;z<10;z++){
                    // stages[z]=new JLabel();
                    stages[z].setText(arr[z]+"");
                    stages[z].setSize(10, 10);
                    stages[z].setFont(new java.awt.Font("Montserrat", 0, 24));
                    stages[z].setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
                    stages[z].setForeground(Color.BLACK);
                    stages[j+1].setForeground(Color.RED);
                    cont.add(stages[z]);
                }
                            for(int y=n-1;y>n-i-1;y--){
                                stages[y].setForeground(Color.BLUE);
                            }
                            if(i>8){
                                stages[0].setForeground(Color.BLUE);
                                stages[1].setForeground(Color.BLUE);
                            }
                add(cont);
                validate();
                repaint();
                
			    /*for(int k=0;k<n;k++){
                    if(arr[k]==0){
                        System.out.println("0 found");
                        break;
                    }
                    data[i][j][k]=arr[k];
                     System.out.print(arr[k]+" -");
                }
            /* System.out.println();*/
                    // JOptionPane.showMessageDialog(null, "Heyy");
        }
        
        System.out.println('\n'+"Pass "+(i+1)+" Completed");
        if(i<8){
            instruction.setText("Operation: Pass "+(i+1)+" is Completed");
        }else{
            instruction.setText("Array is Sorted");

        }
        cont.repaint();
        validate();
        repaint();
        }
        }catch(Exception ex){
            System.out.println(ex);
        }
    } 
  
    /* Prints the array */
    public void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 
  
}