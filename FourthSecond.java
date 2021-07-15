import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern; //
import java.util.regex.Matcher; //

public class StandardCalculator implements ActionListener{
	JLabel label, eq;
	JTextField val1, val2;
	JPanel jp1, jval1,jval2,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
	JButton bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,b10,b11,b12,b13,b14,b15,b16,bdot,beqn,bpm,bdiv,badd,bmult,bminus;
	String fVal,sVal,nVal,rst;
	// String myMatched = ".0";
	double result;
	// JButton b[] = new JButton[10];
	// int i,r,n1,n2
	
	public StandardCalculator(){
		JFrame frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // To close the console window on exit of the window
		frame.setLocation(new Point(400, 100)); // To set the default location to anywhere on the window
		frame.setSize(new Dimension(300, 270));
		frame.setTitle("Calculator"); // To set the title of the window;
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("../calculator.png"));
		frame.setLayout(new GridLayout(9,1));
		
		jp1 = new JPanel(new FlowLayout());
		label = new JLabel("STANDARD");
		jp1.add(label);
		frame.add(jp1);

		jval1 = new JPanel(new FlowLayout());
		val1 = new JTextField(30);
		val1.setEditable(false);
		val1.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        val1.setHorizontalAlignment(JTextField.RIGHT);
		val1.setBorder(null);
        // val1.setSize(new Dimension(20, 20));
        jval1.add(val1);
        frame.add(jval1);

        jval2 = new JPanel(new FlowLayout());
        val2 = new JTextField(20);
		val2.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        val2.setHorizontalAlignment(JTextField.RIGHT);
        val2.setBorder(null);
        // val1.setSize(new Dimension(20, 20));
        jval2.add(val2);
        frame.add(jval2);
        
		jp3 = new JPanel(new FlowLayout());
		jp3.setLayout(new GridLayout(1,4));
		b10 = new JButton("%");
		b10.setBorder(null);
		b10.addActionListener(this);
		jp3.add(b10);
		b11 = new JButton("√");
		b11.setBorder(null);
		b11.addActionListener(this);
		jp3.add(b11);
		b12 = new JButton("x^2");
		b12.setBorder(null);
		b12.addActionListener(this);
		jp3.add(b12);
		b13 = new JButton("1/x");
		b13.setBorder(null);
		b13.addActionListener(this);
		jp3.add(b13);
		frame.add(jp3);

		jp4 = new JPanel(new FlowLayout());
		jp4.setLayout(new GridLayout(1,4));
		b14 = new JButton("CE");
		b14.setBorder(null);
		b14.addActionListener(this);
		jp4.add(b14);
		b15 = new JButton("C");
		b15.setBorder(null);
		b15.addActionListener(this);
		jp4.add(b15);
		b16 = new JButton("Del");
		b16.setBorder(null);
		b16.addActionListener(this);
		jp4.add(b16);
		bdiv = new JButton("/");
		bdiv.addActionListener(this);
		bdiv.setBorder(null);
		jp4.add(bdiv);
		frame.add(jp4);

		jp5 = new JPanel(new FlowLayout());
		jp5.setLayout(new GridLayout(1,4));
		bt7 = new JButton("7");
		bt7.setSize(100, 100);
		bt7.setBorder(null);
		bt7.addActionListener(this);
		jp5.add(bt7);
		bt8 = new JButton("8");
		bt8.setBorder(null);
		bt8.addActionListener(this);
		jp5.add(bt8);
		bt9 = new JButton("9");
		bt9.setBorder(null);
		bt9.addActionListener(this);
		jp5.add(bt9);
		bmult = new JButton("*");
		bmult.setBorder(null);
		bmult.addActionListener(this);
		jp5.add(bmult);
		frame.add(jp5);

		jp6 = new JPanel(new FlowLayout());
		jp6.setLayout(new GridLayout(1,4));
		bt4 = new JButton("4");
		bt4.setBorder(null);
		bt4.addActionListener(this);
		jp6.add(bt4);
		bt5 = new JButton("5");
		bt5.setBorder(null);
		bt5.addActionListener(this);
		jp6.add(bt5);
		bt6 = new JButton("6");
		bt6.setBorder(null);
		bt6.addActionListener(this);
		jp6.add(bt6);
		bminus = new JButton("-");
		bminus.setBorder(null);
		bminus.addActionListener(this);
		jp6.add(bminus);
		frame.add(jp6);

		jp7 = new JPanel(new FlowLayout());
		jp7.setLayout(new GridLayout(1,4));
		bt1 = new JButton("1");
		bt1.setBorder(null);
		bt1.addActionListener(this);
		jp7.add(bt1);
		bt2 = new JButton("2");
		bt2.setBorder(null);
		bt2.addActionListener(this);
		jp7.add(bt2);
		bt3 = new JButton("3");
		bt3.setBorder(null);
		bt3.addActionListener(this);
		jp7.add(bt3);
		badd = new JButton("+");
		badd.setBorder(null);
		badd.addActionListener(this);
		jp7.add(badd);
		frame.add(jp7);

		jp8 = new JPanel(new FlowLayout());
		jp8.setLayout(new GridLayout(1,4));
		bpm = new JButton("±");
		bpm.setBorder(null);
		bpm.addActionListener(this);
		jp8.add(bpm);
		bt0 = new JButton("0");
		bt0.setBorder(null);
		bt0.addActionListener(this);
		jp8.add(bt0);
		bdot = new JButton(".");
		bdot.setBorder(null);
		bdot.addActionListener(this);
		jp8.add(bdot);
		beqn = new JButton("=");
		beqn.setBorder(null);
		beqn .addActionListener(this);
		jp8.add(beqn);
		frame.add(jp8);

		frame.setVisible(true);
		frame.pack();
	}

	public String removeLast(String str){
		str = str.substring(0, str.length() - 1);
		return str;
	}
	// public String removeDot(String resp){
	// 	String myMatched = ".0"; // Match any single character with digit and characters
	// 	Pattern text = Pattern.compile(myMatched, Pattern.LITERAL); // Match any single character except new line
	// 	Matcher found = text.matcher(resp);
	// 	boolean available = found.find();
	// 	if (available) {
	// 		resp = found.replaceFirst("");
	// 	}
	// 	return resp;
	// }

	public void actionPerformed(ActionEvent e){
		
		JButton bt = (JButton) e.getSource();
		String com = bt.getActionCommand().toString();
		String va1 = val1.getText();
		String va2 = val2.getText();
		try{
			// getting all the numbers and the . inside the text input;
			if((com.charAt(0) >= '0' && com.charAt(0) <= '9') || com.charAt(0) == '.'){
				if (va2.equals("0")) {
					va2 = com;	
				} else {
					va2 = va2 +  com;
				}
				val2.setText(va2);
			} else {
				// getting the operators inside the va2 textInput..;
				if(com.equals("+") || com.equals("-") || com.equals("/") || com.equals("*") ) {
					fVal = va2;
					val2.setText("");
					nVal = com;
					val1.setText(fVal + " " +nVal);
					
				} else if(com.equals("=")){
				// getting the results..;
					sVal = va2;
					if (nVal.equals("+")) {
						result = Double.parseDouble(fVal) + Double.parseDouble(sVal); 
					} else if (nVal.equals("-")) {
						result = Double.parseDouble(fVal) - Double.parseDouble(sVal); 
					} else if (nVal.equals("/")) {
						result = Double.parseDouble(fVal) / Double.parseDouble(sVal); 
					} else {
						result = Double.parseDouble(fVal) * Double.parseDouble(sVal); 
					}
					sVal = ""+result;
					// Pattern text = Pattern.compile(".", Pattern.LITERAL); // Match . character
					// Matcher found = text.matcher(pasteToDir+"\\"+output[ii]);
					// String nwtFile = found.replaceFirst("_copy.");
					// myMatched = ".0"; // Match any single character with digit and characters
					// // Pattern text = Pattern.compile(matchtext, Pattern.LITERAL); // Match any single character except new line
					// text = Pattern.compile(myMatched, Pattern.LITERAL); // Match any single character except new line
					// Matcher found = text.matcher(comment0);
					// boolean available = found.find();
					// if (available) {

					// 	output = found.replaceFirst("");
					// } else{
					// 	output = found.replaceFirst("It is not found");
					// }
					// Matcher found = text.matcher(sval);
					// boolean available = found.find();
					// if (found.find()) {
						
					// }
					// if (available) {
										
					val2.setText(sVal);
					// val2.setText(removeDot(sVal));

				} else{
					Double res = Double.parseDouble(va2);
					if(com.equals("%")) {
						 
					} else if (com.equals("√")) {
						val1.setText("√("+va2+")");
						res = Math.sqrt(Double.parseDouble(va2));
					} else if (com.equals("x^2")) {
						res = res * res;
					} else if (com.equals("1/x")) {

					} else if(com.equals("CE")){
						rst = res = "0";
						val1.setText(rst);
					} else if(com.equals("C")){
						rst = res = "0";
					} else if(com.equals("±")){
						int myres = 0 - Integer.parseInt(va2);
						// rst = ""+ myres;
					} else{
						rst = removeLast(va2);
					}
					rst = ""+ res;
					val2.setText(rst);
					// sVal = ""+result;
					// val2.setText(sVal);
				}
				
			}
		} catch(Exception er){
			System.out.println("Empty!");
		}

	}


	public static void main(String[] args){
		StandardCalculator sc = new StandardCalculator();
	}
}