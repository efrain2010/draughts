import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class Client extends JFrame implements ActionListener {

    private Socket server = null;
    private JTextArea textArea;
    private ObjectOutputStream outputStream;
    private JTextField messageField;
    private JButton sendButton;
    private String name;

    public Client() {
        // this.setSize(800,500);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // name = JOptionPane.showInputDialog(this, "What's your name?");
        // JPanel panel = new JPanel(new BorderLayout());
        // textArea = new JTextArea(10, 40);
        // panel.add(new JScrollPane(textArea),BorderLayout.CENTER);
        // this.add(panel);

        // JPanel bottomPanel = new JPanel();
        // messageField = new JTextField(40);
        // sendButton = new JButton("Send");
        // sendButton.addActionListener(this);
        // bottomPanel.add(messageField);
        // bottomPanel.add(sendButton);
        // panel.add(bottomPanel,BorderLayout.SOUTH);

        this.setVisible(true);

        connect();

        try {
            outputStream = new ObjectOutputStream(server.getOutputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }

        ReadWorker rw = new ReadWorker(server,this);
        rw.execute();
        System.out.println("HERE");
    }

    private void connect() {
        try {
            server = new Socket("127.0.0.1",8765);
            System.out.println("Connected");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void display(Message m) {
        textArea.append(m.toString() + '\n');
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton) {
            String messageText = messageField.getText();
            try {
                outputStream.writeObject(new Message(messageText,name));
                messageField.setText("");
            }catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client();
    }
    
}