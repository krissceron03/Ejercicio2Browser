import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class browser extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtUrl;
    private JButton btnEnter;
    private JButton btnAtras;
    private JButton btnDelante;
    private JTextArea descripcion;
    Stack <String> visitados=new Stack<String>();
    Stack <String> back=new Stack<String>();







    public browser() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);



        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


btnAtras.setEnabled(false);
btnDelante.setEnabled(false);
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //descripcion.setText(txtUrl.getText());
                //String url=txtUrl.getText();
                btnAtras.setEnabled(true);


                String url=txtUrl.getText();
                if(url.substring(0,4).equals("www.")&& url.substring(url.length()-4,url.length()).equals(".com")){
                    visitados.push(txtUrl.getText());

                    System.out.println(visitados);


                    String cadena="";
                    for (String vis:visitados) {
                        cadena= cadena+vis.substring(4,vis.length()-4)+", ";

                    }
                    descripcion.setText(cadena);
                }else{

                        JOptionPane.showMessageDialog(null,"ERROR, INGRESE LA URL CORRECTAMENTE");

                    if (visitados.size()>0){
                        txtUrl.setText(visitados.peek());
                    }else{
                        txtUrl.setText(null);
                    }


                }

            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDelante.setEnabled(true);
                String cadena;
                if(visitados.size()>1) {
                    back.push(visitados.peek());
                    System.out.println(back);
                    visitados.pop();
                    cadena = " ";
                    for (String vis : visitados) {
                        cadena = cadena + vis.substring(4, vis.length() - 4) + ", ";

                    }
                    descripcion.setText(cadena);
                    txtUrl.setText(visitados.peek());
                }else {
                    back.push(visitados.peek());
                    visitados.pop();
                    txtUrl.setText(null);
                    descripcion.setText(null);
                    btnAtras.setEnabled(false);

                }

            }
        });
        btnDelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             btnAtras.setEnabled(true);
                String cadena;
                if (back.size()>0) {
                    visitados.push(back.peek());
                    back.pop();
                    if(back.size()==0){
                        btnDelante.setEnabled(false);
                    }
                    cadena = " ";
                    for (String vis : visitados) {
                        cadena = cadena + vis.substring(4, vis.length() - 4) + ", ";

                    }
                    descripcion.setText(cadena);
                    txtUrl.setText(visitados.peek());
                }


            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        browser dialog = new browser();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);


    }
}
