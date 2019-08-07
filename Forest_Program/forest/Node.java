package forest;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * ノード ツリー構造はノード自身に親子それぞれのポインタを持たせることで実現
 * @author Tsukumo
 *
 */
 @SuppressWarnings("serial")
public class Node extends JFrame{

	/**
	 * ノードの名前をStringのインスタンスとして束縛する
	 */
	private String nodeName;
	/**
	 * ノードの番号をIntegerのインスタンスとして束縛する
	 */
	private Integer nodeNumber;

	/**
	 * ノードの親ノードをNodeリストのインスタンスとして束縛する
	 */
	private ArrayList<Node> parents;

	/**
	 * ノードの子ノードをNodeリストのインスタンスとして束縛する。
	 */
	private ArrayList<Node> children;

	/**
	 * model座標をPointのインスタンスとして束縛する。
	 */
	private Point point;
	/**
	 * view座標をPointのインスタンスとして束縛する。
	 */
	private Point viewPoint;

	/**
	 * ノードの名前を画面上で描画したときの長さをIntegerのインスタンスとして束縛する
	 */
	private int textWidth;

	/**
	 * ノードが探索されたかどうかをBooleanのインスタンスとして束縛する。
	 */
	private boolean isSearched;

	/**
	 * インスタンスを生成して応答する。
	 * @param aInteger ノードの番号
	 * @param aString ノードの名前
	 * @param aPoint ノードの初期位置
	 */
	public Node(Integer aInteger,String aString,Point aPoint){
		this.nodeName = aString;
		this.nodeNumber = aInteger;
		this.parents = new ArrayList<Node>();
		this.children = new ArrayList<Node>();
		this.point = aPoint;
		this.viewPoint = aPoint;
		this.isSearched = false;

		//描画時の文字列の長さ
		Font font1 = new Font("Arial",Font.PLAIN,12);
		this.textWidth = getFontMetrics(font1).stringWidth(aString);

	}

	/**
	 * ノードの名前を応答する
	 * @return ノードの名前
	 */
	public String getName(){
		return this.nodeName;
	}

	/**
	 * ノード名前を設定する
	 */
	public void setName(String aString){
		this.nodeName = aString;
	}

	/**
	 * ノードの番号を応答する
	 * @return ノードの番号
	 */
	public Integer getNumber(){
		return this.nodeNumber;
	}

	/**
	 * ノードの番号を設定する
	 * @param aInteger ノード番号
	 */
	public void setNumber(Integer aInteger){
		this.nodeNumber = aInteger;
	}

	/**
	 * 親ノードを応答する
	 * @return 親ノードのリスト
	 */
	public ArrayList<Node> getParents(){
		return this.parents;
	}

	/**
	 * 子ノードを応答する
	 * @return 子ノードのリスト
	 */
	public ArrayList<Node> getChildren(){
		return this.children;
	}

	/**
	 * ノードのModel座標を応答する
	 * @return ノードのModel座標
	 */
	public Point getPoint(){
		return this.point;
	}

	/**
	 * ノードのModel座標を設定する
	 * @param aPoint　ノードのModel座標
	 */
	public void setPoint(Point aPoint){
		this.point = aPoint;
	}

	/**
	 * ノードのView座標を設定する。
	 * @return ノードのView
	 */
	public Point getViewPoint(){
		return this.viewPoint;
	}

	/**
	 * ノードのView座標を設定する
	 * @param aPoint ノードのView座標
	 */
	public void setViewPoint(Point aPoint){
		this.viewPoint = aPoint;
	}

	/**
	 * ノードの名前のテキストの長さを応答する
	 * @return ノードの名前のテキストの長さ
	 */
	public int getTextWidth(){
		return this.textWidth;
	}

	/**
	 * ノードが探索済みかどうかを応答する
	 * @return ノードが探索済みかどうか
	 */
	public boolean getIsSearched() {
		return isSearched;
	}

	/**
	 * ノードが探索済みかどうかを設定する。
	 * @param isSearched ノードが探索済みかどうか
	 */
	public void setIsSearched(boolean isSearched) {
		this.isSearched = isSearched;
	}
}
