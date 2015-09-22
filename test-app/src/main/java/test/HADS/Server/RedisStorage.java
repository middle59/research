package HADS.Server;

import org.redisson.*;

import org.redisson.core.RMap;
import org.redisson.client.WriteRedisConnectionException;
import org.redisson.core.ClusterNode;
import org.redisson.core.Node;
import org.redisson.core.NodesGroup;

import java.util.Map;

public class RedisStorage extends StorageConnector
{

	public Redisson redisson;

	public RedisStorage()
	{
		initialize();
	}

	public boolean initialize()
	{
		try {
			System.out.println("Initializing Redisson Cluster...");

        		String redisAddress = System.getProperty("redisAddress");
			if (redisAddress == null) {
			    redisAddress = "127.0.0.1:6379";
			}
        		Config config = new Config();
        		config.useSingleServer().setAddress(redisAddress);
			redisson = Redisson.create(config);

			/*NodesGroup<Node> nodes = redisson.getNodesGroup();
			System.out.println("Redis: A node has joined the cluster.");
			System.out.println("Redis: Current Cluster Size is " +
				nodes.getNodes().size() + ".");*/
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public Object put(Object K, Object V)
	{
		RMap<Object, Object> redisMap = redisson.getMap("map");
        	return redisMap.put(K, V);
	}

	public Object get(Object K)
	{
		RMap<Object, Object> redisMap = redisson.getMap("map");
		return redisMap.get(K);
	}

}
