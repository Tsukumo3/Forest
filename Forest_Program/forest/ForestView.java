package forest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * ビュー 描画処理を行う
 * @author Tsukumo
 */
@SuppressWarnings("serial")
public class ForestView extends JPanel{

	/**
	 * 情報を握っているforestModelのインスタンスを束縛する。
	 * 束縛されるforestModelのインスタンスはpicture()というメッセージに応答できなければならない。
	 * 良好（2019年7月22日）
	 */
	protected ForestModel forestModel;

	/**
	 * 制御を司るforestControllerのインスタンスを束縛する。
	 * 良好（2019年7月22日）
	 */
	protected ForestController forestController;

	/**
	 * スクロール量としてPointのインスタンスを束縛する。
	 * 良好（2019年7月22日）
	 */
	private Point offset;

	/**
	 * インスタンスを生成して応答する。
	 * 指定されたモデルの依存物となり、コントローラを作り、モデルとビューを設定し、スクロール量を(0, 0)に設定する。
	 * @param aForestModel このビューのモデル
	 * 良好（2019年7月22日）
	 */
	public ForestView(ForestModel aForestModel)
	{
		super();
		forestModel = aForestModel;
		forestModel.addDependent(this);
		forestController = new ForestController();
		forestController.setForestModel(forestModel);
		forestController.setForestView(this);
		offset = new Point(0, 0);
		return;
	}

	/**
	 * インスタンスを生成して応答する。
	 * 指定されたモデルの依存物となり、指定されたコントローラにモデルとビューを設定し、スクロール量を(0, 0)に設定する。
	 * @param aForestModel このビューのモデル
	 * @param aForestController このビューのコントローラ
	 * 良好（2019年7月22日）
	 */
	public ForestView(ForestModel aForestModel, ForestController aForestController)
	{
		super();
		forestModel = aForestModel;
		forestModel.addDependent(this);
		forestController = aForestController;
		forestController.setForestModel(forestModel);
		forestController.setForestView(this);
		offset = new Point(0, 0);
		return;
	}

	/**
	 * 指定されたグラフィクスに背景色（明灰色）でビュー全体を塗り、その後にモデルの内容物を描画する。
	 * それはスクロール量（offset）を考慮してモデル画像（picture）をペイン（パネル）内に描画することである。
	 * @param aGraphics グラフィックス・コンテキスト
	 * 良好（2019年7月22日）
	 */
	public void paintComponent(Graphics aGraphics)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		aGraphics.setColor(Color.WHITE);
		aGraphics.fillRect(0, 0, width, height);
		if (forestModel == null) { return; }

		displayForest(aGraphics);

		return;
	}

	/**
	 * ノードのリストにある全てのノードを表示する。
	 * @param aGraphics グラフィックス・コンテキスト
	 */
	public void displayForest(Graphics aGraphics){
		int height = 16;

		for(Node node :forestModel.getNodes() ){

			Font font1 = new Font("Arial",Font.PLAIN,12);
			aGraphics.setFont(font1);
			aGraphics.setColor(Color.BLACK);
			aGraphics.drawString(node.getName(),
								(int)node.getViewPoint().getX()- offset.x,
								(int)node.getViewPoint().getY()-3- offset.y);
			aGraphics.drawRect((int)node.getViewPoint().getX()- offset.x,
								(int)node.getViewPoint().getY()-height- offset.y,
								node.getTextWidth(),height);

			for(Node parent : node.getParents()){
				aGraphics.drawLine((int)node.getViewPoint().getX() - offset.x,
									(int)node.getViewPoint().getY()- offset.y - 8,
									(int)parent.getViewPoint().getX()+ parent.getTextWidth()- offset.x,
									(int)parent.getViewPoint().getY()- offset.y -8);// + (int)0.5*height

			}
		}
	}

	/**
	 * スクロール量（offsetの逆向きの大きさ）を応答する。
	 * @return X軸とY軸のスクロール量を表す座標
	 * 良好（2019年7月22日）
	 */
	public Point scrollAmount()
	{
		int x = 0 - offset.x;
		int y = 0 - offset.y;
		return (new Point(x, y));
	}

	/**
	 * スクロール量を指定された座標分だけ相対スクロールする。
	 * @param aPoint X軸とY軸のスクロール量を表す座標
	 * 良好（2019年7月22日）
	 */
	public void scrollBy(Point aPoint)
	{
		int x = offset.x + aPoint.x;
		int y = offset.y + aPoint.y;
		this.scrollTo(new Point(x, y));
		return;
	}

	/**
	 * スクロール量を指定された座標に設定（絶対スクロール）する。
	 * @param aPoint X軸とY軸の絶対スクロール量を表す座標
	 * 良好（2019年7月22日）
	 */
	public void scrollTo(Point aPoint)
	{
		offset = aPoint;
		return;
	}

	/**
	 * このインスタンスを文字列にして応答する。
	 * @return 自分自身を表す文字列
	 * 良好（2019年7月22日）
	 */
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		Class<?> aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append("[forestModel=");
		aBuffer.append(forestModel);
		aBuffer.append(",offset=");
		aBuffer.append(offset);
		aBuffer.append("]");
		return aBuffer.toString();
	}

	/**
	 * ビューの全領域を再描画する。
	 * 良好（2019年7月22日）
	 */
	public void update()
	{
		this.repaint(0, 0, this.getWidth(), this.getHeight());
		return;
	}

}