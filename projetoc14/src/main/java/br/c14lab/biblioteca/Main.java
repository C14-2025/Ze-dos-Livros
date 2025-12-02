package br.c14lab.biblioteca;


import br.c14lab.biblioteca.controller.ControladoraLivros;
import br.c14lab.biblioteca.controller.ControladoraUsuarios;
import java.util.Scanner;
import br.c14lab.biblioteca.controller.*;
import br.c14lab.biblioteca.implementacao.EmprestimoIMPL;
import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;

public class Main {
    public static void main(String[] args) {

        //Instâncias -----------------------------------------------------------------------------------


        //------------------------------------------
        //Só serve para não dar nullpointerexception
        UsuarioIMPL usuarioIMPL = new UsuarioIMPL();
        LivroIMPL livroIMPL = new LivroIMPL();
        EmprestimoIMPL emprestimoIMPL = new EmprestimoIMPL(usuarioIMPL, livroIMPL);
        //------------------------------------------


        Scanner sc = new Scanner(System.in);

        ControladoraUsuarios controladoraUsuarios = new ControladoraUsuarios(usuarioIMPL, sc);
        ControladoraLivros controladoraLivros = new ControladoraLivros(livroIMPL, sc);
        ControladoraEmprestimos controladoraEmprestimos = new ControladoraEmprestimos(emprestimoIMPL, sc);

        int option = 99;
        //----------------------------------------------------------------------------------------------


        //Métodos -----------------------------------------------------------------------------------
        //Chama a primeira tela
        do
        {
            System.out.println("                    Gerenciador de Biblioteca");
            System.out.println("====================================================");
            System.out.println("[Oque deseja fazer?:]");
            System.out.println("[1 - Gerenciar Usuários]");
            System.out.println("[2 - Gerenciar Livros]");
            System.out.println("[3 - Gerenciar Emprestimos]");
            System.out.println("[0 - Sair]");
            System.out.println("====================================================");

            option = sc.nextInt();

            if (option == 1) {
                controladoraUsuarios.telaInicial();
            }
            else if (option == 2) {
                controladoraLivros.telaInicial();
            }
            else if (option == 3) {
                controladoraEmprestimos.telaInicial();
            }
            else if (option == 0) {
                System.out.println("Obrigado por usar o programa!");
            }
            else {
                System.out.println("Opcao invalida");
            }
        }
        while(option != 0);

        //----------------------------------------------------------------------------------------------

    }
}