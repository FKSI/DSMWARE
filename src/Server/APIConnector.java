package Server;

import java.util.List;

public abstract class APIConnector {

	public abstract List<String> getItems (String searchTerm) throws Exception;
}
