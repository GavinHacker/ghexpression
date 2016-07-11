
import java.util.Hashtable;
import java.util.List;

public class GHTable {

	public List<GHColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<GHColumn> columns) {
		this.columns = columns;
	}

	public Hashtable<String, GHItem> getItems() {
		return items;
	}

	public void setItems(Hashtable<String, GHItem> items) {
		this.items = items;
	}
	
	private int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private List<GHColumn> columns = null;
	
	private Hashtable<String, GHItem> items = null;
	
	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
