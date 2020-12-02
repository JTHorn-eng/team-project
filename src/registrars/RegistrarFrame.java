package registrars;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;
import guis.BaseFrame;
import guis.ModuleFrame;

import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.List;

/**
 * Generates JFrame for registrars. Registrars can add/remove students,
 * add/remove optional modules for students, check whether a student
 * registration is valid and check whether a student is properly accredited.
 * 
 */

public class RegistrarFrame extends BaseFrame {

	private JTextField textField_1;
	private static String selectedStudentInfo = "";
	private static JTextField searchField;
	private static String searchQuery = "";
	private static JLabel ireg = new JLabel("");
	private static JLabel iname = new JLabel("");
	private static JLabel iEmail = new JLabel("");
	private static JLabel iTutor = new JLabel("");

	public void loadStudents(DefaultListModel<String> model) {
		ArrayList<String> studentInfo = Database.getStudents();
		for (String x : studentInfo) {
			model.addElement(x);
		}
	}

	public RegistrarFrame(String username) {
		
		initBaseFrame(username);
		
	




		JPanel viewing = new JPanel();
		viewing.setBounds(10, 88, 378, 267);
		getContentPane().add(viewing);
		viewing.setLayout(null);

		JButton addRemove = new JButton("Add/Remove Student");
		addRemove.setIcon(null);
		addRemove.setBounds(10, 34, 162, 23);
		addRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ARStudent().display();
			}
		});

		viewing.add(addRemove);

		JLabel lblNewLabel_5 = new JLabel("Student");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 11, 162, 23);
		viewing.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("Check Registration");
		btnNewButton.setBounds(10, 133, 131, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.verifyStudent(selectedStudentInfo);
			}

		});

		viewing.add(btnNewButton);

		JLabel creditLabel = new JLabel();
		creditLabel.setBounds(10, 179, 250, 23);
		creditLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewing.add(creditLabel);

		JButton creditTotal = new JButton("Check Credit Total");
		creditTotal.setBounds(10, 156, 150, 23);
		creditTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals(""))
					creditLabel.setText(Database.checkCreditTotal(selectedStudentInfo));
				revalidate();
			}

		});

		viewing.add(creditTotal);

		textField_1 = new JTextField();
		textField_1.setBounds(57, 105, 152, 20);
		viewing.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Registration");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 80, 152, 14);
		viewing.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Email:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 108, 108, 14);
		viewing.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Optional Modules");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 167, 131, 14);
		viewing.add(lblNewLabel_8);

		JButton btnAddremoveOptionalModules = new JButton("Add/Remove Optional Modules");
		btnAddremoveOptionalModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!selectedStudentInfo.equals("")) {
					new OptionalModule(selectedStudentInfo).display();
				}
			}
		});
		btnAddremoveOptionalModules.setBounds(10, 192, 275, 23);
		viewing.add(btnAddremoveOptionalModules);

		JLabel optionalInfo = new JLabel("Select a student from the list before editing student information.");
		optionalInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		optionalInfo.setBounds(10, 10, 100, 100);
		genInfo.add(optionalInfo);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(398, 22, 600, 25);
		getContentPane().add(toolBar);

		JButton student = new JButton("Student");
		toolBar.add(student);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		list.setBounds(398, 60, 600, 407);

		loadStudents(listModel);
		getContentPane().add(list);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				selectedStudentInfo = list.getSelectedValue();
				displayStudentInfo(Database.selectStudentInfo(list.getSelectedValue()));
				revalidate();

			}
		});



		JButton moduleViewer = new JButton("View Modules");
		moduleViewer.setBounds(10, 189, 89, 23);
		genInfo.add(moduleViewer);

		moduleViewer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!searchQuery.equals("")) {
					System.out.println(selectedStudentInfo);
					new ModuleFrame(selectedStudentInfo.split(" ")[0]);
				}
			}
		});

		getContentPane().add(genInfo);

		JLabel infoStudyLevel = new JLabel("Study Level:");
		infoStudyLevel.setBounds(10, 150, 85, 14);
		genInfo.add(infoStudyLevel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(49, 50, 46, 14);
		genInfo.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(49, 75, 46, 14);
		genInfo.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(49, 100, 46, 14);
		genInfo.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(59, 125, 46, 14);
		genInfo.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(83, 150, 46, 14);
		genInfo.add(lblNewLabel_4);

		JList list_1 = new JList();
		list_1.setBounds(398, 466, 600, 407);
		getContentPane().add(list_1);
	}

	public void displayStudentInfo(String i) {
		System.out.println(i);
		String[] info = i.split(" ");
		ireg.setText(info[0]);
		iname.setText(info[1] + " " + info[2] + " " + info[3]);
		iEmail.setText(info[4]);
		iTutor.setText(info[5]);

		revalidate();
	}

	public void display() {
		setVisible(true);
	}
}