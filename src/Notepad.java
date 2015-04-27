import javax.swing.*; // para o design da interface usando JFrame

import java.awt.*; // para implementar a GUI
import java.awt.event.*; // para o gerenciamento de eventos
import java.util.Scanner; //para a leitura de um arquivo
import java.io.*; //para escrever em um arquivo

public class Notepad extends JFrame implements ActionListener{
	
	private TextArea textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);

	private MenuBar menuBar = new MenuBar(); // primeiro, criando um item Menubar
	private Menu file = new Menu();
	private MenuItem openFile = new MenuItem(); // opcao de abrir o arquivo
	private MenuItem saveFile = new MenuItem(); // opcao para salvar o arquivo
	private MenuItem close = new MenuItem(); // opcao para fechar o app
	
	public Notepad() {
		
		this.setSize(500, 300); //Tamanho inicial da Janela
		this.setTitle("Bloco de Notas - Criado com Java");//titulo da Janela
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//
		this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));//Fonte padrão para a TextArea
		this.getContentPane().setLayout(new BorderLayout()); //o borderlayout preenche toda a janela com o conteúdo
		this.getContentPane().add(textArea);// adiciona a area de texto ao Frame
		
		//adiciona a barra de menu na GUI
		
		this.setMenuBar(this.menuBar);
		this.menuBar.add(this.file);
		
		//agora, o design da barra de menu per si, bem simples. 
		this.file.setLabel("Arquivo");
		
		//hora de trabalhar com o menu - abrir!
		this.openFile.setLabel("Abrir");
		this.openFile.addActionListener(this);
		this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
		this.file.add(this.openFile); //adiciona ao menu
		
		//e.. salvar!
		this.saveFile.setLabel("Salvar");
		this.saveFile.addActionListener(this);
		this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		this.file.add(this.saveFile);
		
		//e... fechar!
		
		this.close.setLabel("Fechar");
		this.close.addActionListener(this);
		this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		this.file.add(this.close);
		
	}
	
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource() == this.close){
		this.dispose();}
		else if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser();
			int option = open.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				
				this.textArea.setText("");
				try {
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext())
						this.textArea.append(scan.nextLine()+"\n");
				}catch (Exception ex){
					System.out.println(ex.getMessage());
				}}
			else if (e.getSource() == this.saveFile) {
				JFileChooser save = new JFileChooser();
				
				int op = save.showSaveDialog(this);
				
				if (op == JFileChooser.APPROVE_OPTION){
					try{
						BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
						out.write(this.textArea.getText());
						out.close();
						
					}catch(Exception ex) {
						
						System.out.println(ex.getMessage());					}
					
				}
			}
			
			
		}
	


	
		
	}

	public static void main(String[] args) {
		
		Notepad notepad = new Notepad();
		notepad.setVisible(true);
		
	}
}

