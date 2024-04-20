package NIM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class NimGame extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 * Thanh B2110100
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[] pileFields;
	private JTextField playerField;
	private JButton[] buttons;
	private JButton modeButton;
	private int[] piles;
	private int currentPlayer;
	private boolean vsComputer;
	private final String SAVE_FILE = "nim_game_save.ser"; // File lưu trạng thái
	private SoundPlayer soundPlayer;
	private boolean soundOn = true;
	private JButton soundButton;
	// Khai báo nút chuyển về menu start
	private JButton backToStartButton;

	public NimGame(int[] piles, int currentPlayer, boolean vsComputer) {
		this.piles = piles;
		this.currentPlayer = currentPlayer;
		this.vsComputer = vsComputer;
	}
	// Các phương thức kiểm tra trạng thái chiến thắng
	// Các phương thức thực hiện hành động của người chơi

	
	public NimGame(int numPiles) {
		super("Nim Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);

		Dimension buttonSize1 = new Dimension(50, 50);
		// Tạo panel chứa nền
		JPanel backgroundPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon(
						"C:/Users/tnplu/eclipse-workspace/NIMGame/background/background2.png");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		backgroundPanel.setLayout(new GridBagLayout());
		setContentPane(backgroundPanel);

		// Panel chứa game và control
		JPanel gameAndControlPanel = new JPanel(new BorderLayout());
		gameAndControlPanel.setOpaque(false); // Đặt trong suốt để nền hiển thị

		// Panel chứa game
		// Khởi tạo gamePanel với GridBagLayout
		JPanel gamePanel = new JPanel(new GridBagLayout());

		// Khởi tạo GridBagConstraints để điều chỉnh các thành phần bên trong gamePanel
		GridBagConstraints gbc1 = new GridBagConstraints();
		gamePanel.setOpaque(false); // Đặt trong suốt để nền hiển thị
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setOpaque(false); // Đặt trong suốt để nền hiển thị
		controlPanel.setPreferredSize(new Dimension(1000, 200));

		pileFields = new JTextField[numPiles];
		buttons = new JButton[numPiles];

		Random random = new Random();
		piles = new int[numPiles];
		for (int i = 0; i < numPiles; i++) {
			piles[i] = random.nextInt(10) + 1;
			// Thiết lập vị trí của ô JTextField
			gbc1.gridx = 0;
			gbc1.gridy = i;
			// Thiết lập các ràng buộc để căn chỉnh ô JTextField
			gbc1.fill = GridBagConstraints.HORIZONTAL;
			gbc1.insets = new Insets(5, 30, 5, 30); // Thiết lập khoảng cách giữa các thành phần
			// Tạo ô JTextField
			pileFields[i] = new JTextField(String.valueOf(piles[i]));
			pileFields[i].setEditable(false);
			pileFields[i].setFont(new Font("Arial", Font.BOLD, 70)); // Thiết lập font chữ và kích thước
			pileFields[i].setForeground(Color.white);
			pileFields[i].setBorder(BorderFactory.createEmptyBorder());
			pileFields[i].setOpaque(false);
			// Thêm ô JTextField vào gamePanel với các ràng buộc đã thiết lập
			gamePanel.add(pileFields[i], gbc1);

			// Thiết lập vị trí của nút JButton
			gbc1.gridx = 1;
			// Tạo nút JButton
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(String.valueOf(i));
			buttons[i].setPreferredSize(buttonSize1);
			// Tạo biểu tượng từ tệp hình ảnh
			ImageIcon icon = new ImageIcon("C:/Users/tnplu/eclipse-workspace/NIMGame/button/take.png");
			// Thiết lập kích thước cho biểu tượng
			Image image = icon.getImage(); // Chuyển đổi biểu tượng thành hình ảnh
			Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newImage); // Tạo lại biểu tượng từ hình ảnh đã được co dãn

			// Đặt biểu tượng cho nút
			buttons[i].setIcon(newIcon);
			// Thêm nút JButton vào gamePanel với các ràng buộc đã thiết lập
			gamePanel.add(buttons[i], gbc1);
		}
		modeButton = new JButton("Bấm để chuyển chế độ !");
		modeButton.addActionListener(this);
		modeButton.setForeground(Color.white);
		modeButton.setFont(new Font("Arial", Font.BOLD, 20));
		modeButton.setPreferredSize(new Dimension(300, 50));

		playerField = new JTextField("Player 1");
		playerField.setForeground(Color.white);
		playerField.setEditable(false);
		playerField.setPreferredSize(new Dimension(100, 50));
		playerField.setFont(new Font("Arial", Font.BOLD, 20));
		playerField.setOpaque(false); // Đặt thành false để hiển thị nền trong suốt
		playerField.setBorder(null); // Xóa đường viền của JTextField
		playerField.setPreferredSize(new Dimension(200, 50));

		JLabel labelplayer = new JLabel("Người chơi hiện tại: ");
		labelplayer.setFont(new Font("Arial", Font.BOLD, 20));
		labelplayer.setForeground(Color.white);
		labelplayer.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(labelplayer);
		controlPanel.add(playerField);
		controlPanel.add(modeButton);

		gameAndControlPanel.add(gamePanel, BorderLayout.CENTER);
		gameAndControlPanel.add(controlPanel, BorderLayout.SOUTH);

		// Thêm panel chứa game và control vào panel chứa nền
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		backgroundPanel.add(gameAndControlPanel, gbc);

		JButton saveButton = new JButton("Lưu");
		saveButton.addActionListener(this);
		saveButton.setForeground(Color.white);
		saveButton.setFont(new Font("Arial", Font.BOLD, 20));
		saveButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(saveButton);

		soundPlayer = new SoundPlayer("C:/Users/tnplu/eclipse-workspace/NIMGame/music/backgroundmusic.wav");
		soundButton = new JButton("Âm Nhạc");
		soundButton.addActionListener(this);
		soundButton.setForeground(Color.white);
		soundButton.setFont(new Font("Arial", Font.BOLD, 20));
		soundButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(soundButton);

		backToStartButton = new JButton("Menu");
		backToStartButton.addActionListener(this);
		backToStartButton.setForeground(Color.white);
		backToStartButton.setFont(new Font("Arial", Font.BOLD, 20));
		backToStartButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(backToStartButton);
		// Set background cho các nút của controlPanel
		backToStartButton.setBackground(Color.pink);
		soundButton.setBackground(Color.pink);
		saveButton.setBackground(Color.pink);
		modeButton.setBackground(Color.pink);

	}

	private void updateFields() {
		for (int i = 0; i < piles.length; i++) {
			pileFields[i].setText(String.valueOf(piles[i]));
		}
		playerField.setText("Player " + (currentPlayer + 1));
		playerField.setFont(new Font("Arial", Font.BOLD, 20));
		playerField.setBackground(Color.pink);
		playerField.setPreferredSize(new Dimension(300, 55));
	}

	private void takeObjects(int pileIndex, int num) {
		// Kiểm tra xem chỉ số của đống có hợp lệ không và xem có đủ số đối tượng để
		// loại bỏ hay không
		while (true) {
			if (pileIndex >= 0 && pileIndex < piles.length && piles[pileIndex] >= num) {
				// Loại bỏ số đối tượng cần thiết từ đống
				piles[pileIndex] -= num;

				// Chuyển lượt chơi cho người chơi tiếp theo
				currentPlayer = (currentPlayer + 1) % 2;

				// Cập nhật trạng thái trò chơi
				updateFields();

				// Kiểm tra nếu có người chiến thắng sau nước đi này
				checkWin();

				// Nếu đang chơi với máy tính và là lượt của máy tính, thực hiện lượt chơi của
				// máy tính
				if (vsComputer && currentPlayer == 1) {
					computerTurn();
				}
				return;
			} else {
				// Hiển thị thông báo nếu nước đi không hợp lệ
				int option = JOptionPane.showConfirmDialog(this, "Nước đi không hợp lệ. Bạn có muốn nhập lại không?",
						"Lỗi", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.NO_OPTION) {
					// Bỏ qua nếu người dùng không muốn nhập lại
					return;
				}
				// Yêu cầu người dùng nhập lại
				String input = JOptionPane.showInputDialog(this, "Nhập lại chỉ số đống:");
				if (input == null) {
					// Bỏ qua nếu người dùng chọn cancel
					return;
				}
				pileIndex = Integer.parseInt(input);
			}
		}
	}

	private void checkWin() {
		// Kiểm tra xem tất cả các đống có rỗng không
		boolean allZeros = true;
		for (int pile : piles) {
			if (pile != 0) {
				allZeros = false;
				break;
			}
		}
		// Nếu tất cả các đống đều rỗng, người chơi hiện tại chiến thắng
		if (allZeros) {
			String winner = vsComputer && currentPlayer == 1 ? "Computer" : "Player " + (currentPlayer + 1);
			JOptionPane.showMessageDialog(this, winner + " thắng!");
			resetGame();
			return;
		}
		// Nếu đang ở chế độ chơi với máy và đến lượt người chơi, nhưng tất cả các đống
		// đều chỉ còn 1 viên sỏi
		// tức là người chơi lấy viên cuối cùng, thì máy chiến thắng
		if (vsComputer && currentPlayer == 0) {
			boolean allOnes = true;
			for (int pile : piles) {
				if (pile != 1) {
					allOnes = false;
					break;
				}
			}
			if (allOnes) {
				JOptionPane.showMessageDialog(this, "Máy thắng!");
				resetGame();
				return;
			}
		}
	}

	private void computerTurn() {
		// Tính toán tổng XOR của số đối tượng trong tất cả các đống
		int nimSum = 0;
		for (int pile : piles) {
			nimSum ^= pile;
		}
		// Tìm đống mà khi loại bỏ một số đối tượng từ đó sẽ làm cho tổng XOR về 0
		for (int i = 0; i < piles.length; i++) {
			int xorResult = piles[i] ^ nimSum;
			if (xorResult < piles[i]) {
				int numObjectsToRemove = piles[i] - xorResult;
				// Kiểm tra xem có thể chừa lại 1 viên cuối cùng hay không
				if (piles[i] - numObjectsToRemove > 0) {
					// Chừa lại 1 viên cuối cùng từ đống này
					takeObjects(i, piles[i] - numObjectsToRemove);
					return;
				}
			}
		}
		// Nếu không tìm được đống nào để chừa lại 1 viên cuối cùng, thực hiện một nước
		// đi ngẫu nhiên
		Random random = new Random();
		int pileIndex = random.nextInt(piles.length);
		while (piles[pileIndex] < 1) {
			pileIndex = random.nextInt(piles.length);
		}
		takeObjects(pileIndex, 1);
	}

	private void resetGame() {
		Random random = new Random();
		for (int i = 0; i < piles.length; i++) {
			piles[i] = random.nextInt(10) + 1; // Randomize piles again
		}
		currentPlayer = 0;
		updateFields();
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modeButton) {
			vsComputer = !vsComputer;
			if (vsComputer) {
				modeButton.setText("Chế độ chơi với máy");
			} else {
				modeButton.setText("Chế độ chơi 2 người");
			}
			resetGame();
//			saveGameStateToFile(); // Lưu trạng thái khi chế độ chơi được thay đổi
		} else if (e.getActionCommand().equals("Lưu")) {
			saveGameStateToFile();
		} else if (e.getSource() == soundButton) {
			toggleSound();
		} else if (e.getSource() == backToStartButton) {
			// Đóng cửa sổ hiện tại của NimGame và lưu trạng thái trò chơi
			saveGameStateToFile();
			soundPlayer.stopSound();
			dispose();
			// Tạo một thể hiện mới của StartMenu và hiển thị nó
			StartMenu startMenu = new StartMenu();
			startMenu.setVisible(true);
		} else {
			int pileIndex = Integer.parseInt(e.getActionCommand());
			String input = JOptionPane.showInputDialog("Bạn muốn lấy bao nhiêu viên từ đống " + (pileIndex + 1) + "?");
			// Kiểm tra nếu chuỗi nhập vào là một số
			if (input != null && input.matches("\\d+")) {
				int numObjects = Integer.parseInt(input);
				takeObjects(pileIndex, numObjects);
			} else if (input != null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ.");
			}
		}
	}

	private void saveGameStateToFile() {
		int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu trò chơi không?", "Xác nhận lưu",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			NimGame gameState = new NimGame(piles, currentPlayer, vsComputer);
			try (FileOutputStream fileOut = new FileOutputStream(SAVE_FILE);
					ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(gameState);
				System.out.println("Lưu trò chơi thành công !");
				soundPlayer.stopSound();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Không thể lưu trò chơi !");
				ex.printStackTrace();
			}
		}
	}

	boolean loadSavedGame(File saveFile) {
		// Các bước kiểm tra và tải trạng thái game giống như trước
		try (FileInputStream fileIn = new FileInputStream(saveFile);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			NimGame savedGame = (NimGame) in.readObject();

			this.piles = savedGame.piles;
			this.currentPlayer = savedGame.currentPlayer;
			this.vsComputer = savedGame.vsComputer;

			reinitializeGameComponents(piles.length); // Tái khởi tạo GUI với số lượng piles đã tải
			updateFields();

			JOptionPane.showMessageDialog(this, "Tải trò chơi thành công !");
			return true;
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Không tải được trò chơi !");
			e.printStackTrace();
			return false;
		}
	}

	private void toggleSound() {

		if (soundPlayer != null) {
			if (!soundOn) {
				soundPlayer.stopSound(); // Tắt âm thanh
				soundButton.setText("Nhạc: TẮT");
			} else {
				soundPlayer.playSound(); // Bật âm thanh
				soundButton.setText("Nhạc: MỞ");
			}
			soundOn = !soundOn; // Đảo ngược trạng thái của âm thanh
		} else {
			// Xử lý khi soundPlayer chưa được khởi tạo
			System.out.println("Trình phát âm thanh chưa được khởi tạo.");
		}
	}

	private void reinitializeGameComponents(int numPiles) {
		getContentPane().removeAll(); // Xóa các components cũ

		Dimension buttonSize1 = new Dimension(50, 50);
		// Tạo panel chứa nền
		JPanel backgroundPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon(
						"C:/Users/tnplu/eclipse-workspace/NIMGame/background/background2.png");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		backgroundPanel.setLayout(new GridBagLayout());
		setContentPane(backgroundPanel);

		// Panel chứa game và control
		JPanel gameAndControlPanel = new JPanel(new BorderLayout());
		gameAndControlPanel.setOpaque(false); // Đặt trong suốt để nền hiển thị

		// Panel chứa game
		// Khởi tạo gamePanel với GridBagLayout
		JPanel gamePanel = new JPanel(new GridBagLayout());

		// Khởi tạo GridBagConstraints để điều chỉnh các thành phần bên trong gamePanel
		GridBagConstraints gbc1 = new GridBagConstraints();
		gamePanel.setOpaque(false); // Đặt trong suốt để nền hiển thị
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setOpaque(false); // Đặt trong suốt để nền hiển thị
		controlPanel.setPreferredSize(new Dimension(1000, 200));

		pileFields = new JTextField[numPiles];
		buttons = new JButton[numPiles];

		for (int i = 0; i < numPiles; i++) {

			// Thiết lập vị trí của ô JTextField
			gbc1.gridx = 0;
			gbc1.gridy = i;
			// Thiết lập các ràng buộc để căn chỉnh ô JTextField
			gbc1.fill = GridBagConstraints.HORIZONTAL;
			gbc1.insets = new Insets(5, 30, 5, 30); // Thiết lập khoảng cách giữa các thành phần
			// Tạo ô JTextField
			pileFields[i] = new JTextField(String.valueOf(piles[i]));
			pileFields[i].setEditable(false);
			pileFields[i].setFont(new Font("Arial", Font.BOLD, 70)); // Thiết lập font chữ và kích thước
			pileFields[i].setForeground(Color.white);
			pileFields[i].setBorder(BorderFactory.createEmptyBorder());
			pileFields[i].setOpaque(false);
			// Thêm ô JTextField vào gamePanel với các ràng buộc đã thiết lập
			gamePanel.add(pileFields[i], gbc1);

			// Thiết lập vị trí của nút JButton
			gbc1.gridx = 1;
			// Tạo nút JButton
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(String.valueOf(i));
			buttons[i].setPreferredSize(buttonSize1);
			// Tạo biểu tượng từ tệp hình ảnh
			ImageIcon icon = new ImageIcon("C:/Users/tnplu/eclipse-workspace/NIMGame/button/take.png");
			// Thiết lập kích thước cho biểu tượng
			Image image = icon.getImage(); // Chuyển đổi biểu tượng thành hình ảnh
			Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newImage); // Tạo lại biểu tượng từ hình ảnh đã được co dãn

			// Đặt biểu tượng cho nút
			buttons[i].setIcon(newIcon);
			// Thêm nút JButton vào gamePanel với các ràng buộc đã thiết lập
			gamePanel.add(buttons[i], gbc1);
		}
		modeButton = new JButton("Bấm để chuyển chế độ !");
		modeButton.addActionListener(this);
		modeButton.setForeground(Color.white);
		modeButton.setFont(new Font("Arial", Font.BOLD, 20));
		modeButton.setPreferredSize(new Dimension(300, 50));

		playerField = new JTextField("Player 1");
		playerField.setForeground(Color.white);
		playerField.setEditable(false);
		playerField.setPreferredSize(new Dimension(100, 50));
		playerField.setFont(new Font("Arial", Font.BOLD, 20));
		playerField.setOpaque(false); // Đặt thành false để hiển thị nền trong suốt
		playerField.setBorder(null); // Xóa đường viền của JTextField

		playerField.setPreferredSize(new Dimension(200, 50));

		JLabel labelplayer = new JLabel("Người chơi hiện tại: ");
		labelplayer.setFont(new Font("Arial", Font.BOLD, 20));
		labelplayer.setForeground(Color.white);
		labelplayer.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(labelplayer);
		controlPanel.add(playerField);
		controlPanel.add(modeButton);

		gameAndControlPanel.add(gamePanel, BorderLayout.CENTER);
		gameAndControlPanel.add(controlPanel, BorderLayout.SOUTH);

		// Thêm panel chứa game và control vào panel chứa nền
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		backgroundPanel.add(gameAndControlPanel, gbc);

		JButton saveButton = new JButton("Lưu");
		saveButton.addActionListener(this);
		saveButton.setForeground(Color.white);
		saveButton.setFont(new Font("Arial", Font.BOLD, 20));
		saveButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(saveButton);

		soundPlayer = new SoundPlayer("C:/Users/tnplu/eclipse-workspace/NIMGame/music/backgroundmusic.wav");
		soundButton = new JButton("Âm Nhạc");
		soundButton.addActionListener(this);
		soundButton.setForeground(Color.white);
		soundButton.setFont(new Font("Arial", Font.BOLD, 20));
		soundButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(soundButton);

		backToStartButton = new JButton("Menu");
		backToStartButton.addActionListener(this);
		backToStartButton.setForeground(Color.white);
		backToStartButton.setFont(new Font("Arial", Font.BOLD, 20));
		backToStartButton.setPreferredSize(new Dimension(300, 50));
		controlPanel.add(backToStartButton);
		// Set background cho các nút của controlPanel
		backToStartButton.setBackground(Color.pink);
		soundButton.setBackground(Color.pink);
		saveButton.setBackground(Color.pink);
		modeButton.setBackground(Color.pink);

		// Thêm gamePanel và controlPanel vào frame


		// Cập nhật và hiển thị lại JFrame
		getContentPane().validate();
		getContentPane().repaint();
	}
}