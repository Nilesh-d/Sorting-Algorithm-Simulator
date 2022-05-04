import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import Sorting_Algo.*;
import java.awt.event.*;

class Home extends JFrame {
    static int arr[] = { 99, 34, 25, 12, 22, 11, 78, 34, 62, 10 };
    private static Object lock = new Object();

    Home(){
        super("Sorting Algorithm Simulator");
        setDefaultCloseOperation(3);
         setLayout(null);
        setLocation(500, 10);
        setVisible(true);
        setSize(1000, 1000);

        Font fontDefault = new Font("Montserrat", 0, 32);

        // Heading
        JLabel heading = new JLabel();
        heading.setBounds(100, 50, 800, 100);
        heading.setText("<html><font text-align='center'><u>Sorting Algorithm Simulator</u></font></html");
        heading.setFont(new Font("Montserrat", 1, 45));
        heading.setForeground(new Color(104, 33, 122));
        add(heading);

        // Navigate Buttons
        JButton btnBubbleSort = new JButton();
        JButton btnSelectionSort = new JButton();
        JButton btnInsertionSort = new JButton();
        JButton btnQuickSort = new JButton();
        JButton btnMergeSort = new JButton();

        btnBubbleSort.setBounds(100, 300, 300, 150);
        btnBubbleSort.setText("Bubble Sort");
        btnBubbleSort.setFont(fontDefault);
        btnBubbleSort.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubbleSort();
            }
        });

        btnSelectionSort.setBounds(500, 300, 300, 150);
        btnSelectionSort.setText("Selection Sort");
        btnSelectionSort.setFont(fontDefault);
        btnSelectionSort.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionSort();
            }
        });

        btnInsertionSort.setBounds(100, 550, 300, 150);
        btnInsertionSort.setText("Insertion Sort");
        btnInsertionSort.setFont(fontDefault);
        btnInsertionSort.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertionSort();
            }
        });

        btnQuickSort.setBounds(500, 550, 300, 150);
        btnQuickSort.setText("Quick Sort");
        btnQuickSort.setFont(fontDefault);
        btnQuickSort.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickSort();
            }
        });

        /*btnMergeSort.setBounds(100, 800, 300, 150);
        btnMergeSort.setText("Merge Sort");
        btnMergeSort.setFont(fontDefault);
        btnMergeSort.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // JOptionPane.showMessageDialog(null, "Not Found");
                mergeSort();
            }
        });*/

        JLabel name = new JLabel("- NILESH KUMAR, MCA3, PRSU");
        name.setBounds(500, 90, 500, 100);
        name.setFont( new Font("Montserrat", 0, 25));

        add(btnBubbleSort);
        add(btnSelectionSort);
        add(btnInsertionSort);
        add(btnQuickSort);
        add(btnMergeSort);
        add(name);
        repaint();
        System.out.println("Loaded");

    }

    public static void main(String[] args) {
        Home home = new Home();
        System.out.println(arr);
        final Object lock = new Object();
        int arr[] = { 99, 34, 25, 12, 22, 11, 78, 34, 62, 10 };
        BubbleSort ob = new BubbleSort(arr);
        // Thread t1= new Thread(ob);
        // t1.start();
        ob.lock=lock;
        Thread t2 = new Thread(ob);
        t2.start();

        // ob.bubbleSort(arr);

        // System.out.println("Sorted array");
        // ob.printArray(arr);
        // SelectionSort sel = new SelectionSort(arr);
        // InsertionSort is = new InsertionSort(arr);

        // QuickSort qs = new QuickSort(arr);
        // qs.lock=lock;
        //     qs.run();
        
    }

    static void quickSort(){
        Object quickLocker = new Object();
        QuickSort qs = new QuickSort(arr);
        qs.lock=quickLocker;
        Thread t1 = new Thread(qs);
            // qs.run();
        t1.start();
        
    }

    static void insertionSort(){
        Object locker = new Object();
        InsertionSort is = new InsertionSort(arr);
        is.lock=locker;
        Thread t3= new Thread(is);
        t3.start();
    }

    void selectionSort(){
        new SelectionSort(arr);
    }

    static void bubbleSort(){
        BubbleSort ob = new BubbleSort(arr);
        // Thread t1= new Thread(ob);
        // t1.start();
        ob.lock=lock;
        Thread t2 = new Thread(ob);
        t2.start();
    }

    static void mergeSort(){
        MergeSort ms = new MergeSort(arr);
        ms.lock=lock;
        Thread t1 = new Thread(ms);
        t1.start();
    }


}
