import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class HtVerifier {
	private String s3;
	private HashTree t3;
	private HtEditor htEditor;
	private List<Integer> changes;
	private List<Integer> changeableIndex;
	private List<String> msg;
	
	public HtVerifier (HtEditor htEditor) {
		this.htEditor = htEditor;
		this.changes = htEditor.getChanges();
		receiveMsg();
		calcHashTree(htEditor);
	}
	
	private List<Integer> notChangedIndex(HtEditor htEditor) {
		List<Integer> l = htEditor.getChanges();
		List<Integer> notChanged = new ArrayList<Integer>();
		int size = htEditor.getItemList2().size();
		for(int i = 0; i < size; i++) {
			notChanged.add(i);
		}
		notChanged.removeAll(l);
		return notChanged;
	}
	
	private String calcHashTree(HtEditor htEditor) {
		try {
			List<String> l = getMsg();
			this.t3 = new HashTree(l, htEditor.getT2().getSetList());
			for(int i = 0; i < this.changes.size(); i++) {
				int index = this.changes.get(i);
				t3.getNodeList().set(index , new Node(l.get(index)));
			}
			t3.calcHashTree();
			this.s3 = t3.getRoot().getValue();
			return s3;
		}
		catch(Exception e) {
			System.err.println("Message from Editor not correct!");
		}
		return null;
	}
	
	private void receiveMsg() {
		this.msg = new ArrayList<String>();
		this.msg = this.htEditor.sendMsgToVerifier();
	}
	
	public List<String> blancMsg() {
		List<String> l = this.msg;
		for(int i : changes) {
			l.set(i, "");
		}
		return l;
	}
	
	

	public List<String> getMsg() {
		return msg;
	}

	public String getS3() {
		return s3;
	}
	
	public String combineInfos() {
		String s = cocateAll();
		String size = Integer.toString(calcBlocks());
		String s2 =  padLeftZeros(size, 5);
		String s3 = this.s3;
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
}
