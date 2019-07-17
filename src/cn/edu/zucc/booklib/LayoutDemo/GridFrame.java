package cn.edu.zucc.booklib.LayoutDemo;

import java.awt.*;
import javax.swing.*;

class GridFrame extends JFrame {
	// ������壬������Ϊ���񲼾֣�4��4�У����ˮƽ����ֱ����Ϊ3
	JPanel p = new JPanel(new GridLayout(4, 4, 3, 3));
	JTextArea t = new JTextArea(); // �����ı���
	// �����ַ������飬Ϊ��ť����ʾ�ı���ֵ
	// ע���ַ�Ԫ�ص�˳����ѭ����Ӱ�ť����һ��
	String str[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3",
			"-", "0", ".", "=", "+" };

	public GridFrame(String s) {
		super(s); // Ϊ�������Ƹ�ֵ
		setLayout(new BorderLayout()); // ���崰�岼��Ϊ�߽粼��
		JButton btn[]; // ������ť����
		btn = new JButton[str.length]; // ������ť����
		// ѭ�����尴ť������ӵ������
		for (int i = 0; i < str.length; i++) {
			btn[i] = new JButton(str[i]);
			p.add(btn[i]);
		}
		// ���ı�������ڴ���NORTHλ��
		getContentPane().add(t, BorderLayout.NORTH);
		// ���������ڴ���CENTERλ��
		getContentPane().add(p, BorderLayout.CENTER);
		setVisible(true);
		setSize(250, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // �ô��������ʾ
	}

	public static void main(String[] args) {
		GridFrame gl = new GridFrame("���񲼾ּ������");
	}
}
