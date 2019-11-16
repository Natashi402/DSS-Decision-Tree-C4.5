package dss;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	public JComboBox tuoi = new JComboBox();
	@SuppressWarnings("rawtypes")
	public JComboBox tuoi_con = new JComboBox();
	@SuppressWarnings("rawtypes")
	public JComboBox chenh_lech = new JComboBox();
	@SuppressWarnings("rawtypes")
	public JComboBox hoan_canh = new JComboBox();
	@SuppressWarnings("rawtypes")
	public JComboBox choose_result = new JComboBox();

	private String tuoi_option[] = { " Tuổi <= 30", " Tuổi > 30" };

	private String tuoi_con_option[] = { " Tuổi con = 0", " Tuổi con < 7", " 7 <= Tuổi con < 18", " Tuổi con >= 18" };

	private String chenh_lech_option[] = { " 0 < Chênh lệch <= 5", " 5 < Chênh lệch <= 10", " Chênh lệch > 10" };

	private String hoan_canh_option[] = { " Mâu thuẫn gia đinh", " Bạo lực gia đình", " Một người ở nước ngoài",
			" Một người mất tích", " Ngoại tình", " Bệnh tật, không có con", " Mâu thuẫn kinh tế",
			" Do nghiện rượu, cờ bạc, ma túy" };

	private String result_option[] = { " ","\tĐoàn tụ gia đình", "\tThuận tình ly hôn", "\tMở phiên tòa xét xử" };

	private C45DecisionTree decision_tree;
	private final JButton btn_confirm = new JButton("Ghi nhận kết quả");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.decision_tree = new C45DecisionTree();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		tuoi.setModel(new DefaultComboBoxModel(tuoi_option));

		tuoi_con.setModel(new DefaultComboBoxModel(tuoi_con_option));

		chenh_lech.setModel(new DefaultComboBoxModel(chenh_lech_option));

		hoan_canh.setModel(new DefaultComboBoxModel(hoan_canh_option));
		
		choose_result.setModel(new DefaultComboBoxModel(result_option));

		JButton btn_result = new JButton("Kết quả");
		
		JButton btn_reset = new JButton("Chọn lại");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(choose_result, Alignment.LEADING, 0, 641, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(tuoi, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tuoi_con, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btn_result, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btn_reset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chenh_lech, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(34)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_confirm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(hoan_canh, 0, 161, Short.MAX_VALUE))))
					.addGap(42))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tuoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(hoan_canh, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(tuoi_con, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(chenh_lech, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_result, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_confirm, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(choose_result, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ActionListener actionResult = new action_result();
		btn_result.addActionListener(actionResult);
		
		ActionListener actionReset = new action_reset();
		btn_reset.addActionListener(actionReset);
		
		ActionListener actionConfirm = new action_confirm();
		btn_confirm.addActionListener(actionConfirm);
	}

	class action_result implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int a = Arrays.asList(tuoi_option).indexOf(tuoi.getSelectedItem());
			int b = Arrays.asList(tuoi_con_option).indexOf(tuoi_con.getSelectedItem());
			int c = Arrays.asList(chenh_lech_option).indexOf(chenh_lech.getSelectedItem());
			int d = Arrays.asList(hoan_canh_option).indexOf(hoan_canh.getSelectedItem());
			actionDT(a, b, c, d);
		}
	}
	
	class action_reset implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tuoi_con.setSelectedIndex(0);
			chenh_lech.setSelectedIndex(0);
			hoan_canh.setSelectedIndex(0);
			tuoi.setSelectedIndex(0);
			choose_result.setSelectedIndex(0);
		}
	}
	
	class action_confirm implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int a = Arrays.asList(tuoi_option).indexOf(tuoi.getSelectedItem());
			int b = Arrays.asList(tuoi_con_option).indexOf(tuoi_con.getSelectedItem());
			int c = Arrays.asList(chenh_lech_option).indexOf(chenh_lech.getSelectedItem());
			int d = Arrays.asList(hoan_canh_option).indexOf(hoan_canh.getSelectedItem());
			int result = Arrays.asList(result_option).indexOf(choose_result.getSelectedItem());
			
			String _tuoi = Integer.toString(a + 1);
			String _tuoi_con = Integer.toString(b + 1);
			String _chenh_lech = Integer.toString(c + 1);
			String _hoan_canh = Integer.toString(d + 1);
			String _result = Integer.toString(result + 1);
			
			String output = _tuoi + "," + _tuoi_con + "," + _chenh_lech + "," + _hoan_canh + "," + _result;
			
			FileWriter fw;
			try {
				fw = new FileWriter(decision_tree.data_train_path, true);
				fw.write(output);
				fw.close();
				JOptionPane.showMessageDialog(null, "Ghi nhận thành công");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	}

	private void actionDT(int a, int b, int c, int d) {
		String _tuoi = Integer.toString(a + 1);
		String _tuoi_con = Integer.toString(b + 1);
		String _chenh_lech = Integer.toString(c + 1);
		String _hoan_canh = Integer.toString(d + 1);
		String input = _tuoi + "," + _tuoi_con + "," + _chenh_lech + "," + _hoan_canh + ",?";
		int result_label = this.decision_tree.result_label(input);
		System.out.println(result_label);
		this.choose_result.setSelectedIndex(result_label+1);
	}
}
