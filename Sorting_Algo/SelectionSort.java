package Sorting_Algo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.*;

import java.util.Random;

import java.awt.Color;
import java.awt.event.ActionListener;

public class SelectionSort extends JFrame{
    int valueNull=0;
    int array[]=new int[10];
    int input[]=new int[10];
    int sorted[][]=new int[10][10];
    int column=-1;
    int minValueInColumn[]=new int[10];

    JButton prevBtn = new JButton();
    JButton nextBtn= new JButton();
    JButton simBtn = new JButton();

    JLabel label = new JLabel();
    JLabel unit[] = new JLabel[10];
    JLabel stages[] = new JLabel[10];
    JLabel passer[] = new JLabel[10];
    JLabel details[] = new JLabel[10];

    JPanel panel = new JPanel();
    JPanel cont = new JPanel();
    JPanel outCont[] = new JPanel[10];
    
    



    private void btnNext(java.awt.event.ActionEvent evt){
        cont.invalidate();
        cont.validate();
        cont.repaint();
        if(column<9){
            column++;
            drawer();
        }
        
    }
    private void btnPrev(java.awt.event.ActionEvent evt){
       if(column>=0){
        cont.remove(column);
        cont.invalidate();
        cont.validate();
        cont.repaint();
        column--;}
    }
    private void btnSimu(java.awt.event.ActionEvent evt){
        System.out.println("Move Next");
        final Timer timer = new Timer(600,null);
        timer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(column<9){
                    column++;
                    drawer();
                }else{
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public SelectionSort(int[] arr){
        super("Selection Sort");
        //Frame Layout
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);    //JFrame.EXIT_ON_CLOSE
		setLayout(null);
		setLocation(500, 10);
		setVisible(true);
        setSize(1000,1000);
        array=arr;

        // Next Button
        nextBtn.setText("Next");
        nextBtn.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext(evt);
                }
        });
        nextBtn.setBounds(850,220,100,40);
        add(nextBtn);

        //  Previous Button
        prevBtn.setText("Previous");
        prevBtn.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev(evt);
                }
        });
        prevBtn.setBounds(50,220,100,40);
        add(prevBtn);

        //Simulate Button
        simBtn.setText("Simulate >>");
        simBtn.addActionListener(new ActionListener(){ 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimu(evt);
                }
        });
        simBtn.setBounds(700,220,120,40);
        add(simBtn);

        //Heading
        label.setText("Selection Sorting");
        label.setFont(new java.awt.Font("Montserrat", 1, 30));
        label.setForeground(new Color(104,33,122));
        label.setBounds(30, 40, 450, 40);
        add(label);

        //generate random number between 1 to 100
        for(int i=0;i<10;i++){
            Random r = new Random();
            arr[i]=r.nextInt(90)+10;

        }

        //Input Container
        panel.setBounds(50,100,900,100);
        panel.setBackground(new Color(30,30,30));
        for(int i=0;i<arr.length;i++){
            unit[i]= new JLabel();
            unit[i].setBounds(10*i, 10, 10, 10);
            unit[i].setForeground(new Color(245,245,245));
            unit[i].setFont(new java.awt.Font("Montserrat", 1, 24));
            unit[i].setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            unit[i].setText(""+arr[i]);
            panel.add(unit[i]);
        }
        add(panel);
        selectionSort(arr);
    }

    private void selectionSort(int[] arr){
        System.out.println("array inserted");
        cont.setBackground(Color.LIGHT_GRAY);
        cont.setBounds(130,300,700,800);
        for(int i=0;i<arr.length;i++){
            int minVal=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[minVal]){
                    minVal=j;
                    minValueInColumn[i]=j;
                }
                
            }
            int temp=arr[minVal];
            arr[minVal]=arr[i];
            arr[i]=temp;
            for(int k=0;k<10;k++){
                // System.out.print(arr[k]+" ");
                sorted[i][k]=arr[k];
            }
            
        }
        
        // add(cont);
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                System.out.print(sorted[i][j]+" ");
            }
            System.out.println();
        }

        for(int i=0;i<10;i++){
            if(minValueInColumn[i]==0){
                minValueInColumn[i]=i;
            }
            System.out.print(minValueInColumn[i]+" ");
        }

        details[0]=new JLabel("<html><b>Step 1</b>: Set <font color='green'>MIN</font> to location 0</html>");
        details[1]=new JLabel();
        details[2]=new JLabel();
        details[3]=new JLabel();
        details[4]=new JLabel();

        // details[0].setText();
        details[0].setSize(700,50);
        details[1].setSize(700,50);
        details[2].setSize(700,50);
        details[3].setSize(700,50);
        details[4].setSize(700,50);

        details[0].setLayout(null);
        // details[0].setAlignmentX(SwingConstants.LEFT);

        details[0].setFont(new java.awt.Font("Montserrat",0,26));
        details[1].setFont(new java.awt.Font("Montserrat",0,26));
        details[2].setFont(new java.awt.Font("Montserrat",0,26));
        details[3].setFont(new java.awt.Font("Montserrat",0,26));
        details[4].setFont(new java.awt.Font("Montserrat",0,26));
        
        details[0].setBorder(BorderFactory.createEmptyBorder(100,16,10,16));
        details[1].setBorder(BorderFactory.createEmptyBorder(10,16,10,16));
        details[2].setBorder(BorderFactory.createEmptyBorder(10,16,10,16));
        details[3].setBorder(BorderFactory.createEmptyBorder(10,16,10,16));
        details[4].setBorder(BorderFactory.createEmptyBorder(10,16,10,16));

        details[1].setText("<html><b>Step 2</b>: Search the minimum element in the list </html>");
        details[2].setText("<html><b>Step 3</b>: Swap with value at location <font color='green'>MIN</font> </html");
        details[3].setText("<html><b>Step 4</b>: Increment <font color='green'>MIN</font> to point to next element  </html>");
        details[4].setText("<html><b>Step 5</b>: Repeat until list is <font color='orange'>sorted</font></html ");
        
            cont.add(details[0]);
        cont.add(details[1]);
        cont.add(details[2]);
        cont.add(details[3]);
        cont.add(details[4]);
        add(cont);
        cont.invalidate();
        cont.validate();
        cont.repaint();
    }

    private void drawer(){
        if(column==0){
            cont.removeAll();
        }
        outCont[column] = new JPanel();
        outCont[column].setBackground(Color.gray);
        outCont[column].setSize(40, 40);

        passer[column] = new JLabel();
        passer[column].setText("Pass "+(column+1));
        passer[column].setFont(new java.awt.Font("Montserrat", 1, 20));
        passer[column].setBounds(50,320 + (column*65),80,40);

        for(int i=0;i<sorted.length;i++){
            stages[i]= new JLabel();
            stages[i].setSize(10, 10);
            stages[i].setFont(new java.awt.Font("Montserrat", 0, 24));
            stages[i].setBorder(BorderFactory.createEmptyBorder(10,16,10,16));
            stages[i].setForeground(new Color(200,200,200));
            if(i<=column){
                stages[i].setForeground(Color.orange);
            }
            if(column!=9 && i==minValueInColumn[column+1]){
                stages[i].setForeground(Color.GREEN);
            }
            outCont[column].add(stages[i]);
            stages[i].setText(sorted[column][i]+"");
        }
        // cont.setBackground(Color.MAGENTA);
        cont.add(outCont[column]);
        add(cont);
        add(passer[column]);
        passer[column].invalidate();
        passer[column].validate();
        passer[column].repaint();
        cont.invalidate();
        cont.validate();
        cont.repaint();
    }
}