import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class Verifier {
	private String s3;
	private HashTree t3;
	private Editor editor;
	private List<Integer> changes;
	private List<String> msg;
	
	public Verifier (Editor editor) {
		this.editor = editor;
		this.changes = editor.getChanges();
		receiveMsg();
		calcHashTree(editor);
	}
	
	private List<Integer> notChangedIndex(Editor editor) {
		List<Integer> l = editor.getChanges();
		List<Integer> notChanged = new ArrayList<Integer>();
		int size = editor.getItemList2().size();
		for(int i = 0; i < size; i++) {
			notChanged.add(i);
		}
		notChanged.removeAll(l);
		return notChanged;
	}
	
	private String calcHashTree(Editor editor) {
		try {
			List<String> l = getMsg();
			this.t3 = new HashTree(l, editor.getT2().getSetList());
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
		this.msg = this.editor.sendMsgToVerifier();
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
	
	
}
