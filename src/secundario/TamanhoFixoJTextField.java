/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secundario;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author ferpa
 */
public class TamanhoFixoJTextField extends PlainDocument {

    private final int tamMax;

    public TamanhoFixoJTextField(int tamMax) {
        super();
        this.tamMax = tamMax;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

        if (str == null) {
            return;
        }

        //Define a condição para aceitar qualquer número de caracteres
        if (tamMax <= 0) {
            super.insertString(offset, str, attr);
            return;
        }

        int tam = (getLength() + str.length());

        //Se o tamanho final for menor, chama insertString() aceitando a String
        if (tam <= tamMax) {
            super.insertString(offset, str, attr);
        }
    }
}
