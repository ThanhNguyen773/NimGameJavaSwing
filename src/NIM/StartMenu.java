package NIM;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartMenu extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 * Thanh B2110100
	 */
	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JButton resumeButton;
	private JButton guideButton;
	private JButton exitButton;
	private JButton changebgButton;
	private JTextField pileField;
	private JLabel background;
	
	public StartMenu() {
		// Khởi tạo JFrame và các thành phần

		super("Nim Game - Start Menu");
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Đặt kích thước cho Frame
		setSize(800, 800);

		// Đặt kích thước cho nút
		Dimension buttonSize = new Dimension(200, 50);
		

		background = new JLabel();
		ImageIcon backgroundImage = new ImageIcon("C:/Users/tnplu/eclipse-workspace/NIMGame/background/background2.png");
		// Đặt ImageIcon vào JLabel
		background.setIcon(backgroundImage);

		// Đặt kích thước của JLabel để phù hợp với kích thước của StartMenu
		background.setBounds(1920, 1080, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

		// Đặt JLabel làm nền cho StartMenu
		setContentPane(background);

		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbcTitleLabel = new GridBagConstraints();
		gbcTitleLabel.gridx = 0;
		gbcTitleLabel.gridy = 0;
		gbcTitleLabel.insets = new Insets(5, 5, 5, 5);

		JLabel titleLabel = new JLabel("NIM GAME");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 120));
		titleLabel.setForeground(Color.white);
		getContentPane().add(titleLabel, gbcTitleLabel);

		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 1;
		gbcLabel.insets = new Insets(5, 5, 5, 5);

		JLabel label = new JLabel("Mời bạn nhập số đống:");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Color.white);
		getContentPane().add(label, gbcLabel);

		GridBagConstraints gbcPileField = new GridBagConstraints();
		gbcPileField.gridx = 0;
		gbcPileField.gridy = 2;
		gbcPileField.insets = new Insets(5, 5, 5, 5);

		pileField = new JTextField(2);
		// hoặc sử dụng hình ảnh làm nền cho JTextField
		pileField.setOpaque(false);
		pileField.setBackground(new Color(0, 0, 0, 0)); // Đặt màu trong suốt
		pileField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white)); // Đặt đường viền
		// Đặt cỡ chữ và màu chữ
		pileField.setFont(new Font("Arial", Font.BOLD, 40)); // Đặt cỡ chữ và font
		pileField.setForeground(Color.white);
		pileField.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(pileField, gbcPileField);

		GridBagConstraints gbcStartButton = new GridBagConstraints();
		gbcStartButton.gridx = 0;
		gbcStartButton.gridy = 3;
		gbcStartButton.insets = new Insets(5, 5, 5, 5);

		startButton = new JButton("Bắt Đầu Chơi");
		startButton.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
		startButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		startButton.setForeground(Color.white);
		startButton.setBackground(Color.black);
		startButton.setPreferredSize(buttonSize);
		startButton.addActionListener(this);
		getContentPane().add(startButton, gbcStartButton);

		GridBagConstraints gbcResumeButton = new GridBagConstraints();
		gbcResumeButton.gridx = 0;
		gbcResumeButton.gridy = 4;
		gbcResumeButton.insets = new Insets(5, 5, 5, 5);

		resumeButton = new JButton("Tiếp Tục Chơi");
		resumeButton.addActionListener(this);
		resumeButton.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
		resumeButton.setForeground(Color.white);
		resumeButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		resumeButton.setPreferredSize(buttonSize);
		resumeButton.setBackground(Color.black);
		getContentPane().add(resumeButton, gbcResumeButton);

		GridBagConstraints gbcGuideButton = new GridBagConstraints();
		gbcGuideButton.gridx = 0;
		gbcGuideButton.gridy = 5;
		gbcGuideButton.insets = new Insets(5, 5, 5, 5);

		guideButton = new JButton("Hướng Dẫn");
		guideButton.setBackground(Color.black);
		guideButton.setForeground(Color.white);
		guideButton.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
		guideButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		guideButton.setPreferredSize(buttonSize);
		guideButton.addActionListener(this);
		getContentPane().add(guideButton, gbcGuideButton);

		changebgButton = new JButton("Đổi Hình Nền");
		changebgButton.setBackground(Color.black);
		changebgButton.setForeground(Color.white);
		changebgButton.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
		changebgButton.setPreferredSize(buttonSize);
		changebgButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		changebgButton.addActionListener(this);
		GridBagConstraints gbcChangebgButton = new GridBagConstraints();
		
		gbcChangebgButton.gridx = 0;
		gbcChangebgButton.gridy = 6;
		gbcChangebgButton.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(changebgButton, gbcChangebgButton);

		GridBagConstraints gbcExitButton = new GridBagConstraints();
		gbcExitButton.gridx = 0;
		gbcExitButton.gridy = 7;
		gbcExitButton.insets = new Insets(5, 5, 5, 5);
		exitButton = new JButton();
		ImageIcon originalIcon = new ImageIcon("C:/Users/tnplu/eclipse-workspace/NIMGame/button/exitbutton2.png");
		// Thay đổi kích thước của hình ảnh và tạo một ImageIcon mới
		ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
		exitButton.setIcon(scaledIcon);
		exitButton.setPreferredSize(new Dimension(100, 50));
		exitButton.addActionListener(this);
		getContentPane().add(exitButton, gbcExitButton);
		
		GridBagConstraints gbctrichdanLabel1 = new GridBagConstraints();
		gbctrichdanLabel1.gridx = 0;
		gbctrichdanLabel1.gridy = 8;
		gbctrichdanLabel1.insets = new Insets(5, 5, 5, 5);

		JLabel trichdanLabel1 = new JLabel(" ");
		trichdanLabel1.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		getContentPane().add(trichdanLabel1, gbctrichdanLabel1);
		trichdanLabel1.setForeground(Color.white);
		
		GridBagConstraints gbctrichdanLabel2 = new GridBagConstraints();
		gbctrichdanLabel2.gridx = 0;
		gbctrichdanLabel2.gridy = 9;
		gbctrichdanLabel2.insets = new Insets(5, 5, 5, 5);

		JLabel trichdanLabel2 = new JLabel(" ");
		trichdanLabel2.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		getContentPane().add(trichdanLabel2, gbctrichdanLabel2);
		trichdanLabel2.setForeground(Color.white);
		
		GridBagConstraints gbctrichdanLabel = new GridBagConstraints();
		gbctrichdanLabel.gridx = 0;
		gbctrichdanLabel.gridy =  GridBagConstraints.RELATIVE;
		gbctrichdanLabel.insets = new Insets(5, 5, 5, 5);

		JLabel trichdanLabel = new JLabel("@ Copy right by Thanh B2110100");
		trichdanLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		getContentPane().add(trichdanLabel, gbctrichdanLabel);
		trichdanLabel.setForeground(Color.white);
		// Kiểm tra xem có trò chơi đã lưu không
		if (!new File("nim_game_save.ser").exists()) {
			resumeButton.setEnabled(false);
		}
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Thay đổi background khi chuột đi vào button
				startButton.setBackground(Color.pink); // Hoặc thay đổi thành hình ảnh khác tùy ý
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Đặt lại background khi chuột rời khỏi button
				startButton.setBackground(Color.black);
			}
		});
		resumeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Thay đổi background khi chuột đi vào button
				resumeButton.setBackground(Color.pink); // Hoặc thay đổi thành hình ảnh khác tùy ý
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Đặt lại background khi chuột rời khỏi button
				resumeButton.setBackground(Color.black);
			}
		});
		guideButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Thay đổi background khi chuột đi vào button
				guideButton.setBackground(Color.pink); // Hoặc thay đổi thành hình ảnh khác tùy ý
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Đặt lại background khi chuột rời khỏi button
				guideButton.setBackground(Color.black);
			}
		});

		changebgButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Thay đổi background khi chuột đi vào button
				changebgButton.setBackground(Color.pink); // Hoặc thay đổi thành hình ảnh khác tùy ý
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Đặt lại background khi chuột rời khỏi button
				changebgButton.setBackground(Color.black);
			}
		});
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startNewGame();
		} else if (e.getSource() == resumeButton) {
			resumeGame();
		} else if (e.getSource() == guideButton) {
			guideGame();
		} else if (e.getSource() == exitButton) {
			exitGame();
		} else if (e.getSource() == changebgButton) {
			changeBGGame();
		}
	}

	private void changeBGGame() {
		// TODO Auto-generated method stub
		// Hiển thị hộp thoại cho phép người dùng chọn hình nền mới
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(".")); // Mở thư mục hiện tại
		int result = fileChooser.showOpenDialog(StartMenu.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Kiểm tra xem tệp đã chọn có tồn tại và có phải là hình ảnh không
			if (selectedFile.exists() && isImageFile(selectedFile)) {
				// Tạo ImageIcon mới từ tệp hình ảnh được chọn
				ImageIcon newBackgroundImage = new ImageIcon(selectedFile.getPath());
				// Cập nhật hình nền của StartMenu với hình ảnh mới
				background.setIcon(newBackgroundImage);
				// Cập nhật kích thước của JLabel background để phù hợp với kích thước mới của
				// hình nền
				background.setBounds(0, 0, newBackgroundImage.getIconWidth(), newBackgroundImage.getIconHeight());
				// Cập nhật lại giao diện người dùng để hiển thị hình nền mới
				revalidate();
				repaint();
			} else {
				// Nếu tệp không tồn tại hoặc không phải là hình ảnh, thông báo cho người dùng
				JOptionPane.showMessageDialog(StartMenu.this, "Vui lòng chọn một tệp hình ảnh hợp lệ.");
			}
		}
	}

	private void startNewGame() {
		String numPilesText = pileField.getText();
		try {
			int numPiles = Integer.parseInt(numPilesText);
			if (numPiles > 0 && numPiles <= 6) {
				NimGame nimGame = new NimGame(numPiles);
				nimGame.setVisible(true);
				
				dispose();
			} else {
				JOptionPane.showMessageDialog(this,
						"Vui lòng nhập số đống hợp lệ (lớn hơn 0 và nhỏ hơn hoặc bằng 6).");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Nội dung không hợp lệ. Vui lòng nhập một số.");
		}
	}

	private void resumeGame() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(".")); // Mở thư mục hiện tại
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Khởi tạo NimGame không cần số lượng piles
			NimGame nimGame = new NimGame(0); // Khởi tạo với 0 và sẽ cập nhật sau khi tải game
			if (nimGame.loadSavedGame(selectedFile)) {
				nimGame.setVisible(true);
				this.dispose(); // Đóng cửa sổ StartMenu sau khi khôi phục trạng thái
			} else {
				JOptionPane.showMessageDialog(this, "Không thể tiếp tục trò chơi.");
			}
		}
	}

	private void guideGame() {
		JOptionPane.showMessageDialog(this, "Chào mừng bạn đến với trò chơi Nim!\n"
							+"Phiên bản: 2.0v\n\n"
							+ "👍 Quy tắc:\n"
							+ "- Các người chơi lần lượt loại bỏ các đối tượng khỏi các đống.\n"
							+ "- Trên mỗi lượt, một người chơi có thể chọn loại bỏ bất kỳ số lượng đối tượng nào từ một đống.\n"
							+ "- Người chơi loại bỏ đối tượng cuối cùng sẽ thua cuộc.\n\n" 
							+ "💡 Cách chơi:\n"
							+ "- Nhập số lượng đống bạn muốn chơi và nhấp vào 'Bắt Đầu Chơi'.\n"
							+ "- Chọn một đống bạn muốn loại bỏ số lượng đối tượng .\n"
							+ "- Bấm vào Nút màu đỏ 'dấu -' sau đó nhập số lượng đối tượng bạn muốn loại bỏ và bấm 'OK' \n"
							+ "- Bạn có thể chuyển đổi giữa chế độ 2 Người chơi và Chế độ chơi với Máy tính bằng cách nhấp vào 'Chuyển Đổi Chế Độ'.\n"
							+ "- Để lưu trò chơi, nhấp vào 'Lưu Trò Chơi'. \n"
							+ "- Để trở về Menu Bắt Đầu bạn bấm vào nút Trở lại Menu Bắt Đầu. \n"
							+ "- Nút 'Âm Nhạc' có thể giúp bạn có thể tuỳ ý bật và tắt nhạc đấy! \n"
							+ "- Ngoài ra, bạn có thể thay đổi hình nền bằng cách nhấn vào nút 'Đổi Hình Nền' và chọn file hình nền bạn thích. \n"
							+ "Bạn đang chuẩn bị tiếp tục một trò chơi đã được lưu.\n\n" 
							+ "▶️ Để tiếp tục:\n"
							+ "- Nhấp vào nút 'Tiếp Tục' và chọn Tệp `nim_game_save.ser` \n"
							+ "- Nhấp vào Mở để tiếp tục trò chơi từ nơi bạn dừng lại.\n"
							+ "- Bạn có thể tiếp tục với các cài đặt và lối chơi trước đó của mình.\n\n"
							+ "😊 Cảm ơn bạn đã lựa chọn trò chơi Nim và dành thời gian để khám phá hướng dẫn.\n"
							+ "😊 Chúng tôi chúc bạn may mắn và hy vọng bạn có một phiên chơi game tuyệt vời!");
	}

	// Phương thức thoát trò chơi
	private void exitGame() {
		int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc bạn muốn thoát?", "Xác nhận Thoát"
				+ "",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Cảm ơn vì đã chơi!");
			System.exit(0);
		}
	}

	// Phương thức kiểm tra xem một tệp có phải là hình ảnh hay không
	private boolean isImageFile(File file) {
		String name = file.getName();
		int index = name.lastIndexOf('.');
		if (index > 0 && index < name.length() - 1) {
			String extension = name.substring(index + 1).toLowerCase();
			return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
					|| extension.equals("gif") || extension.equals("bmp");
		}
		return false;
	}
}
