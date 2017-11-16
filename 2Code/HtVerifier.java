import java.util.ArrayList;
import java.util.List;

/**
 * Verifizierer von Chameleon-Hash-Verfahren
 * @author Yuguan Zhao
 */
public class HtVerifier {
	/*Wurzelwert von dem Hashtree t*/
	private String s;
	/*Instanz Hashtree t*/
	private HashTree t;
	/*aenderbare Indizies*/
	private List<Integer> changeableIndex;
	/*Nachrichtenbloecke*/
	private List<String> msg;
	/*Liste von Commitment-Komponenten*/
	private List<CommitmentSet> setList;
	/*Liste benoetigter Knoten um den urspruenlichen Baum wiederherzustellen*/
	private List<Node> reqNodeList;
	
	public HtVerifier (List<String> msg, List<CommitmentSet> setList, List<Node> reqNodeList) throws Exception {
		this.msg = msg;
		this.setList = setList;
		this.reqNodeList = reqNodeList;
		calcReceivedMsgRootValue();
	}
	
	public String calcReceivedMsgRootValue() throws Exception {
		this.t = new HashTree(this.msg, this.setList);
		this.t.replaceWithNewNodes(this.getReqNodeList());
		this.t.calcNewRootValue(t.getRoot());
		this.s = t.getRoot().getValue();
		return s;	
	}
	
	public List<String> getMsg() {
		return msg;
	}

	public String getS() {
		return s;
	}
	
	public String combineInfos() {
		String s = cocateAll();
		String size = Integer.toString(calcBlocks());
		String s2 =  padLeftZeros(size, 5);
		String s3 = this.s;
		String s4 = s + s2 + s3;
		return s4;
	}
	
	public String cocateAll() {
		String s = "";
		List<Integer> l = new ArrayList<Integer>(this.changeableIndex);
		for(int i : l) {
			s = s + i;
		}
		return s;
	}

	public int calcBlocks() {
		int i = msg.size();
		return i;
	}
	
	/**
     * Füllt Links von String s mit 0
     * Die max. Laenge betraegt i
     */
	public String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}

	public void setChangeableIndex(List<Integer> changeableIndex) {
		this.changeableIndex = changeableIndex;
	}

	public List<Node> getReqNodeList() {
		return reqNodeList;
	}

}
