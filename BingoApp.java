import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoApp extends JFrame {
    private JButton drawButton; 
    private JLabel numberLabel; 
    private JTextArea historyArea; 
    private List<Integer> numbers; 

    // コンストラクタ
    public BingoApp() {
        setTitle("Bingo Number Selector");

        // ウィンドウサイズを設定
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ボタンの作成（小さくする）
        drawButton = new JButton("Draw");
        drawButton.setFont(new Font("Arial", Font.BOLD, 16));  
        drawButton.setPreferredSize(new Dimension(100, 50)); 

        // ラベルの作成（選んだ数字を表示するスペースを大きく）
        numberLabel = new JLabel("Press to draw", SwingConstants.CENTER);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 120));  
        numberLabel.setForeground(Color.BLUE);  

        // テキストエリアの作成（履歴）
        historyArea = new JTextArea();
        historyArea.setEditable(false); 
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);  
        historyArea.setFont(new Font("Arial", Font.PLAIN, 40)); 

        // テキストエリアのスクロールペイン
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 400)); 
        
        // 数字のリストを初期化
        numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers); 
        // ボタンが押されたときのアクションを定義
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numbers.size() > 0) {
                    int drawnNumber = numbers.remove(0);  
                    numberLabel.setText(Integer.toString(drawnNumber)); 
                    historyArea.append(drawnNumber + "\n");  
                } else {
                    numberLabel.setText("No more numbers!");  
                    drawButton.setEnabled(false);  
                }
            }
        });

        // レイアウトの設定
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(drawButton);

        // 数字表示エリアを大きくしたレイアウト
        add(numberLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);  
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // メインメソッドからアプリを実行
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BingoApp();
            }
        });
    }
}
