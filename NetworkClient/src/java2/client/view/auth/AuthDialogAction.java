package java2.client.view.auth;

import java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java2.client.view.auth.AuthDialog.*;

public class AuthDialogAction extends MyWindowAuth {

    public static final int WINDOW_SIZE_X= 300;
    public static final int WINDOW_SIZE_Y= 200;
    public static final int WINDOW_START_X = 400;
    public static final int WINDOW_START_Y = 400;

    private ClientController controller;


    public AuthDialogAction(ClientController controller) {
        this.controller = controller;
        setTitle("Authentication");
        setContentPane(contentPanel);
        getRootPane().setDefaultButton(ok);
        setBounds(WINDOW_START_X, WINDOW_START_Y, WINDOW_SIZE_X, WINDOW_SIZE_Y);
        setResizable(false);

        ok.addActionListener(e -> onOK());

        exit.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        String login = entryLogin.getText().trim();
        String pass = new String(entryPass.getPassword()).trim();
        controller.sendAuthMessage(login, pass);
    }

    private void onCancel() {
        System.exit(0);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);

    }
}
