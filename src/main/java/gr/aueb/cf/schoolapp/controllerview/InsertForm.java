package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
//import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.xml.validation.Validator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class InsertForm extends JFrame {
    private static final long serialVersionUID = 1L;

    // Wiring
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private final JTextField firstnameTxt;
    private final JTextField lastnameTxt;

    public InsertForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setBackground(SystemColor.activeCaption);
        setTitle("Εισαγωγή Εκπαιδευτή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 540, 280);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lastnameLbl = new JLabel("Επίθετο");
        lastnameLbl.setForeground(new Color(153, 0, 0));
        lastnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lastnameLbl.setBounds(38, 52, 56, 16);
        contentPane.add(lastnameLbl);

        JLabel firstnameLbl = new JLabel("Όνομα");
        firstnameLbl.setForeground(new Color(153, 0, 0));
        firstnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstnameLbl.setBounds(38, 87, 56, 16);
        contentPane.add(firstnameLbl);

        JButton insertBtn = new JButton("Insert");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Map<String, String> errors = new HashMap<>();

                // Data Binding
                String inputLastname = firstnameTxt.getText().trim();
                String inputFirstname = lastnameTxt.getText().trim();

                // Validation
//                if (inputLastname.equals("") || inputFirstname.equals("")) {
//                    JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }



                try {
                    // Data Binding - Create DTO
                    TeacherInsertDTO insertDTO = new TeacherInsertDTO();
                    insertDTO.setFirstname(inputFirstname);
                    insertDTO.setLastname(inputLastname);

                    errors = TeacherValidator.validate(insertDTO);

                    if (!errors.isEmpty()) {
                        String firstnameMessage = (errors.get("firstname") != null) ? "Fistname: " + errors.get("firstname") : "";
                        String lastnameMessage = (errors.get("lastname") != null) ? "Lastname: " + errors.get("lastname") : "";
                        JOptionPane.showMessageDialog(null, firstnameMessage + " " + lastnameMessage , "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Teacher teacher = teacherService.insertTeacher(insertDTO);
                    if (teacher == null) {
                        JOptionPane.showMessageDialog(null, "Teacher not inserted", "INSERT", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Teacher " + teacher.getLastname() +
                            " was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
                } catch (TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        insertBtn.setForeground(new Color(0, 0, 153));
        insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));

        firstnameTxt = new JTextField();
        firstnameTxt.setColumns(45);
        firstnameTxt.setBounds(97, 51, 277, 22);
        contentPane.add(firstnameTxt);

        lastnameTxt = new JTextField();
        lastnameTxt.setBounds(97, 87, 277, 22);
        contentPane.add(lastnameTxt);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                firstnameTxt.setText("");
                lastnameTxt.setText("");
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(28, 168, 450, 1);
        contentPane.add(separator);
        insertBtn.setBounds(300, 183, 82, 25);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(true);
                Main.getInsertForm().setVisible(false);

            }
        });
        closeBtn.setForeground(new Color(0, 0, 153));
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));

        closeBtn.setBounds(392, 183, 95, 25);
        contentPane.add(closeBtn);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(18, 13, 469, 144);
        contentPane.add(panel);
    }
}