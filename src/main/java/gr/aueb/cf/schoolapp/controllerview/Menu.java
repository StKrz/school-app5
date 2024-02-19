package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;

    public Menu() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 400);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEduQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblEduQuality1.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblEduQuality1.setBounds(12, 26, 486, 65);
        contentPane.add(lblEduQuality1);

        JLabel lblEdyQuality2 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblEdyQuality2.setForeground(new Color(0, 128, 0));
        lblEdyQuality2.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblEdyQuality2.setBounds(14, 28, 486, 65);
        contentPane.add(lblEdyQuality2);

        JButton teachersBtn = new JButton("");
        teachersBtn.setBounds(12, 132, 40, 30);
        teachersBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchForm().setVisible(true);
            }
        });
        contentPane.add(teachersBtn);

        JButton studentsBtn = new JButton("");
        studentsBtn.setBounds(12, 183, 40, 30);
        contentPane.add(studentsBtn);

        JSeparator separator = new JSeparator();
        separator.setBounds(22, 102, 450, 1);
        contentPane.add(separator);

        JLabel lblTeachers = new JLabel("Εκπαιδευτές");
        lblTeachers.setForeground(new Color(128, 0, 0));
        lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTeachers.setBounds(67, 127, 123, 40);
        contentPane.add(lblTeachers);

        JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
        lblStudents.setForeground(new Color(128, 0, 0));
        lblStudents.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStudents.setBounds(67, 178, 167, 40);
        contentPane.add(lblStudents);
    }
}
