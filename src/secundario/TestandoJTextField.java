package secundario;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestandoJTextField extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtExemplo;

    public static void main(String[] args) {
        TestandoJTextField field = new TestandoJTextField();
        field.testaJTextField();
    }

    private void testaJTextField() {
        this.setTitle("Exemplo");
        this.setSize(200, 180);

        //Definimos o tamanho padrão do JTextField
        txtExemplo = new JTextField(10);

        //Passamos para o construtor o número máximo de caracteres aceitos
        txtExemplo.setDocument(new TamanhoFixoJTextField(5));
        this.getContentPane().add(txtExemplo, "North");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
