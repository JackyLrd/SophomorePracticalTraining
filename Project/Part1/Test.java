import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Calculator 
{
	Calculator()
	{
		Frame f;
		JButton plus;
		JButton minus;
		JButton multiply;
		JButton divide;
		JButton ok;
		JTextField num1;
		JTextField sign;
		JTextField num2;
		JTextField equal;
		JTextField result;
		f = new Frame("Easy Calculator");
		f.setBounds(800, 500, 500, 200);
		f.setLayout(new GridLayout(2, 5));
		plus = new JButton("+");
		minus = new JButton("-");
		multiply = new JButton("*");
		divide = new JButton("/");
		ok = new JButton("OK");
		num1 = new JTextField("1");
		sign = new JTextField(" ");
		num2 = new JTextField("1");
		equal = new JTextField("=");
		result = new JTextField(" ");
		result.setEditable(false);
		equal.setEditable(false);
		sign.setEditable(false);
		num1.setHorizontalAlignment(JTextField.CENTER);
		sign.setHorizontalAlignment(JTextField.CENTER);
		num2.setHorizontalAlignment(JTextField.CENTER);
		equal.setHorizontalAlignment(JTextField.CENTER);
		result.setHorizontalAlignment(JTextField.CENTER);
		f.add(num1);
		f.add(sign);
		f.add(num2);
		f.add(equal);
		f.add(result);
		f.add(plus);
		f.add(minus);
		f.add(multiply);
		f.add(divide);
		f.add(ok);
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		plus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sign.setText("+");
			}
		});
		minus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sign.setText("-");
			}
		});
		multiply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sign.setText("*");
			}
		});
		divide.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sign.setText("/");
			}
		});
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int a = Integer.parseInt(num1.getText());
				int b = Integer.parseInt(num2.getText());
				String x = sign.getText();
				int c = 0;
				if(x.charAt(0) == '+')
				{
					c = a + b;
				}
				else if(x.charAt(0) == '-')
				{
					c = a - b;
				}
				else if(x.charAt(0) == '*')
				{
					c = a * b;
				}
				else if(x.charAt(0) == '/')
				{
					c = a / b;
				}
				else
				{
					c = 0;
				}
				String s = String.valueOf(c);
				result.setText(s);
			}
		});
		f.setVisible(true);
	}
}

public class Test
{
	public static void main(String args[])
	{
		new Calculator();
	}
}

