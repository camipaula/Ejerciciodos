import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class browser1 extends JDialog {
    private JPanel contentPane;
    private JButton btnx;
   // private JButton buttonCancel;
    private JTextField pagina;
    private JButton buscar;
    private JButton back;
    private JButton fordward;
    private JTextArea mostrar;

    Stack<String> ingreso = new Stack<String>();
    Stack<String> backp = new Stack<String>();



    public browser1() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnx);

        btnx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        //buttonCancel.addActionListener(new ActionListener() {
            //public void actionPerformed(ActionEvent e) {
               // onCancel();
            //}
        //});

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               // onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        back.setEnabled(false);
        fordward.setEnabled(false);
        //CUANDO PRESIONAMOS BUSCAR
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            funcionIngresar();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionback();

            }
        });
        fordward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionfordward();
                back.setEnabled(true);
            }
        });
    }
private void funcionIngresar(){
        if(pagina.getText().substring(0,4).equals("www.")&&pagina.getText().substring(pagina.getText().length()-4,pagina.getText().length()).equals(".com")){

        ingreso.push(pagina.getText());
        while(backp.size()!=0){
            backp.pop();
        }
        fordward.setEnabled(false);
        System.out.println("Cadena ingreso" + ingreso);
        funcioncadena();
        back.setEnabled(true);
        } else {
        JOptionPane.showMessageDialog(null,"Ingrese un URL válido");
        }
}
private void funcionback(){
        if (ingreso.size()>1) {
            backp.push(ingreso.peek());
            ingreso.pop();
            System.out.println("back pila" + backp);
            funcioncadena();
            pagina.setText(ingreso.peek());
            fordward.setEnabled(true);


        } else {
            mostrar.setText(" ");
            back.setEnabled(false);
            backp.push(ingreso.peek());
            ingreso.pop();
            pagina.setText("");
        }
}
    private void funcionfordward(){
        if (backp.size()>0){
        ingreso.push(backp.peek());
        backp.pop();
        funcioncadena();
        pagina.setText(ingreso.peek());
        if (backp.size()==0){
            fordward.setEnabled(false);
        }
        }
    }
    private void onOK() {
        // add your code here
        dispose();
    }
    private void funcioncadena() {
        // add your code here if necessary
        String cad="";
        //Cad va acumulando lo que tiene i(ingresos) de la pila
        //El for each da un recorrido de la pila y almacena en i los elememtos de la pila
        for (String i:ingreso) {
            cad=cad+i.substring(4,i.length()-4)+", ";
        }
        mostrar.setText(cad);
        System.out.println("Cadena: "+ cad);
    }

    public static void main(String[] args) {
        browser1 dialog = new browser1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
