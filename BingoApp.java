import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoApp extends JFrame {
    private JButton drawButton;  // 数字を選ぶボタン
    private JLabel numberLabel;  // 選んだ数字を表示するラベル
    private JTextArea historyArea;  // 選ばれた数字を記録するテキストエリア
    private List<Integer> numbers;  // 1から75の数字リスト

    // コンストラクタ
    public BingoApp() {
        setTitle("Bingo Number Selector");

        // ウィンドウサイズを設定
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ボタンの作成（小さくする）
        drawButton = new JButton("Draw");
        drawButton.setFont(new Font("Arial", Font.BOLD, 16));  // フォントサイズを小さめに
        drawButton.setPreferredSize(new Dimension(100, 50));  // ボタンのサイズを小さく

        // ラベルの作成（選んだ数字を表示するスペースを大きく）
        numberLabel = new JLabel("Press to draw", SwingConstants.CENTER);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 120));  // フォントサイズを大きく
        numberLabel.setForeground(Color.BLUE);  // 見やすく青色に変更

        // テキストエリアの作成（履歴）
        historyArea = new JTextArea();
        historyArea.setEditable(false);  // 履歴エリアは編集不可
        historyArea.setLineWrap(true);  // 自動で折り返し
        historyArea.setWrapStyleWord(true);  // 単語単位で折り返す
        historyArea.setFont(new Font("Arial", Font.PLAIN, 40));  // フォントサイズを大きくして視認性向上

        // テキストエリアのスクロールペイン
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 400));  // スクロールエリアのサイズ

        // 数字のリストを初期化
        numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);  // リストをシャッフル

        // ボタンが押されたときのアクションを定義
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numbers.size() > 0) {
                    int drawnNumber = numbers.remove(0);  // リストから数字を一つ取り出す
                    numberLabel.setText(Integer.toString(drawnNumber));  // ラベルに表示
                    historyArea.append(drawnNumber + "\n");  // 履歴に追加
                } else {
                    numberLabel.setText("No more numbers!");  // 数字が尽きたときのメッセージ
                    drawButton.setEnabled(false);  // ボタンを無効化
                }
            }
        });

        // レイアウトの設定
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(drawButton);

        // 数字表示エリアを大きくしたレイアウト
        add(numberLabel, BorderLayout.CENTER);  // 大きな数字表示を中央に配置
        add(buttonPanel, BorderLayout.EAST);  // ボタンを右側に配置
        add(scrollPane, BorderLayout.SOUTH);  // テキストエリアを下に配置

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