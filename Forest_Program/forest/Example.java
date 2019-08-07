package forest;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * テキストファイルを読み取り樹状整列を実行するクラス。<br>
 * Makefileを用いた実行方法は以下の通りです。<br>
 * $ make test<br>
 * その後にファイル選択ウィンドウをから表示したいファイル選択してください<br>
 * @author Tsukumo
 */
public class Example extends Object
{
	/**
	 * ファイルチューザーでファイルを選択し、樹上整列を表示する準備をする
	 * @param arguments 引数はなく実行できるので不要
	 * @throws FileNotFoundException 指定されたパス名で示されるファイルが開けなかったことを通知
	 * 良好（2019年7月22日）
	 */
	public static void main(String[] arguments) throws FileNotFoundException
	{
		JFileChooser chooser = new JFileChooser();

		String currentDirectory = System.getProperty("user.dir");
		//String dataDirectory = currentDirectory +  File.separator + "src" + File.separator + "forest" + File.separator + "tree.txt";
		String dataDirectory = currentDirectory +  File.separator + "resource" + File.separator + "data" + File.separator + "tree.txt";

		//デフォルトの選択ファイルを指定
		chooser.setSelectedFile(new File(dataDirectory));

		FileFilter filter = new FileNameExtensionFilter("textファイル", "txt");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);

		chooser.setDialogTitle("ファイルを読み込む");

		//ダイアログを表示
		int selected = chooser.showOpenDialog(null);
		File aFile;
		if(selected == JFileChooser.APPROVE_OPTION){
			aFile = chooser.getSelectedFile();

			// MVCを作成する。
			ForestModel aModel = new ForestModel(aFile);
			ForestView aView = new ForestView(aModel);

			// ウィンドウを生成して開く。
			JFrame aWindow = new JFrame(aFile.getName());
			aWindow.getContentPane().add(aView);
			aWindow.setMinimumSize(new Dimension(400, 300));
			aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			aWindow.setSize(800, 600);
			aWindow.setLocationRelativeTo(null);
			aWindow.setVisible(true);

			// 樹状整列のアニメーションを行う。
			aModel.animate();
        }

		return;
	}
}
